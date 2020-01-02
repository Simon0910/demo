package jce;/*
 * %Z%%M%    %I%  %W%  %G%  %U%
 * ===========================================================================
 * Licensed Materials - Property of IBM
 * IBM Security Software Development Kit, Java (tm) Technology Edition
 *
 * (C) Copyright IBM Corp. 2000 All Rights Reserved.
 *
 *  US Government Users Restricted Rights - Use, duplication or
 *  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 * ===========================================================================
 */

import java.io.*;
import java.security.Provider;
import java.security.Security;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrates using CertPath APIs to find, build and validate a certificate chain.
 */
public class sample {

    public static void main(String[] args) {
        try {
            Provider[] providers = Security.getProviders();
            boolean findProvider = false;
            for (int i = 0; i < providers.length; i++) {
                if (providers[i].getName().equals("IBMCertPath")) {
                    findProvider = true;
                }
                System.out.println("Provider(" + i + ")" + providers[i]);
            }
            if (!findProvider) {
                System.out.println("IBMCertPath provider is not registered.");
                System.exit(0);
            }
            //get an instance of java.security.cert.CertificateFactory
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "IBMCertPath");
            //read in certificates from current directory
            ArrayList certs = getCertsFromDir(".", cf);
            //get the trusted anchor from current directory
            X509Certificate trustedcert = getTrustedCert("TrustAnchor.cer", cf);
            if (trustedcert == null) {
                throw new NullPointerException("trust anchor not found");
            }
            TrustAnchor ata = new TrustAnchor(trustedcert, null);
            Set anchors = new HashSet();
            anchors.add(ata);
            X509CertSelector selector = new X509CertSelector();
            //select only certificates issued to 'CN=User Certificate, OU=Java Security, O=IBM, C=US'
            selector.setSubject("CN=User Certificate, OU=Java Security, O=IBM, C=US");
            //select unexpired certificates
            selector.setCertificateValid(new Date());
            PKIXBuilderParameters params = new PKIXBuilderParameters(anchors, selector);
            //create collection certstore
            CollectionCertStoreParameters storeParams = new CollectionCertStoreParameters(certs);
            CertStore certStore = CertStore.getInstance("Collection", storeParams, "IBMCertPath");
            ArrayList stores = new ArrayList();
            stores.add(certStore);
            //set certstore in PKIXBuilderParameters
            params.setCertStores(stores);
            //disable revocation status check
            params.setRevocationEnabled(false);
            //generate a cert path
            CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", "IBMCertPath");
            System.out.println("Building certification path ...");
            CertPathBuilderResult result = builder.build(params);
            System.out.println("Successfully build a certification path.");
            System.out.println("This certification path is - ");
            //validate cert path
            CertPath cpath = result.getCertPath();
            System.out.println(cpath.toString());

            PKIXParameters ps = new PKIXParameters(anchors);
            ps.setRevocationEnabled(false);
            ps.setCertStores(stores);
            CertPathValidator validator = CertPathValidator.getInstance("PKIX", "IBMCertPath");
            System.out.println("Verifying the certification path ...");
            CertPathValidatorResult validatorResult = validator.validate(cpath, ps);
            System.out.println("Successfully validate this certification path.");
        } catch (Exception e) {
            System.out.println("Exception caught");
            e.printStackTrace();
        }

    }

    static X509Certificate getTrustedCert(String filename, CertificateFactory cf) {
        try {
            InputStream is = new FileInputStream(filename);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
            is.close();
            return cert;
        } catch (Exception e) {
            return null;
        }
    }

    static ArrayList getCertsFromDir(String directory, CertificateFactory cf) throws IOException {
        ArrayList list = new ArrayList();
        File dir = new File(directory);
        String fullPath = dir.getCanonicalPath();
        String[] filelist = dir.list();
        FileInputStream fos = null;
        X509Certificate cert = null;

        for (int i = 0; i < filelist.length; i++) {
            try {
                fos = new FileInputStream(fullPath + File.separator + filelist[i]);
                cert = (X509Certificate) cf.generateCertificate(fos);
                list.add(cert);
            } catch (FileNotFoundException e) {
                continue;
            } catch (IOException e) {
            } catch (CertificateException e) {
            }
        }
        return list;
    }


}
