package com.lzp.mastercard.crypto.cert;


import com.lzp.mastercard.crypto.util.CryptUtil;
import com.lzp.mastercard.crypto.util.DataEncoding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * keytool -genkey -alias mykey -keyalg RSA -keystore mykeystore.keystore -keysize 2048 -validity 90 -storepass storepass -keypass keypass -dname "CN=localhost, OU=localhost, O=localhost, L=BJ, ST=BJ, C=CN"
 * <p>
 * keytool -importkeystore -alias mykey -srckeystore mykeystore.keystore -destkeystore mykeystore.p12 -srcstoretype JKS -deststoretype PKCS12 -srcstorepass storepass -srckeypass keypass -deststorepass password -destkeypass password
 * <p>
 * keytool -export -alias mykey -keystore mykeystore.p12 -storepass password -file publickey.cer
 *
 * @author liuzhipeng
 * @description
 * @create 2019-08-28 11:36
 */
public class Cerdemo {

    public static void main(String[] args) throws Exception {
        System.out.println("从证书获取的公钥为:" + getPublicKey());
        System.out.println("从证书获取的私钥为:" + getPrivateKey());
    }

    public static String getPublicKey() throws Exception {
        //证书文件路径
        String cerPath = "E:\\git-repo\\rsa\\src\\main\\java\\com\\lzp\\security\\demo\\publickey.cer";

        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        FileInputStream fis = new FileInputStream(cerPath);
        X509Certificate Cert = (X509Certificate) certificatefactory.generateCertificate(fis);
        PublicKey pk = Cert.getPublicKey();
        // String publicKey = new BASE64Encoder().encode(pk.getEncoded());
        String publicKey = CryptUtil.byteArrayToString(pk.getEncoded(), DataEncoding.HEX);
        return publicKey;
    }

    public static String getPrivateKey() throws Exception {
        //证书库文件路径
        // String storePath = "E:\\git-repo\\rsa\\src\\main\\java\\com\\lzp\\security\\demo\\mykeystore.keystore";
        String storePath = "E:\\git-repo\\rsa\\src\\main\\java\\com\\lzp\\security\\demo\\mykeystore.p12";

        //证书别名
        String alias = "mykey";
        //证书库密码
        String storePw = "password";
        //证书密码
        String keyPw = "password";

        FileInputStream is = new FileInputStream(storePath);
        // KeyStore ks = KeyStore.getInstance("JKS");
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(is, storePw.toCharArray());
        is.close();
        PrivateKey key = (PrivateKey) ks.getKey(alias, keyPw.toCharArray());
        // String privateKey = new BASE64Encoder().encode(key.getEncoded());
        String privateKey = CryptUtil.byteArrayToString(key.getEncoded(), DataEncoding.HEX);
        return privateKey;
    }


    public static X509Certificate getCert(String cerPath) {
        CertificateFactory certificatefactory = null;
        try {
            certificatefactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(cerPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        X509Certificate Cert = null;
        try {
            Cert = (X509Certificate) certificatefactory.generateCertificate(fis);
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        byte[] signature = Cert.getSignature();

        System.out.println(Cert.toString());

        return Cert;
    }

}
