package jce;/*
 * JCE algorithm lister, for use with IBM's JCE.
 *
 *
 * (C) Copyright IBM Corp. 2002. All Rights Reserved
 *
 */

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class JCEAlgorithms {

    public static void main(String[] args) {

        // Get JCE entry set (algorithms and aliases)
        //
        // Provider jceProvider = Security.getProvider("IBMJCE");
        Provider jceProvider = Security.getProvider("SunJCE");

        Set e = jceProvider.entrySet();

        TreeSet aliasSet = new TreeSet();
        TreeSet algSet = new TreeSet();
        aliasSet.clear();
        algSet.clear();

        Iterator it = e.iterator();
        String line;

        // Sort algorithms and aliases separately
        //
        while (it.hasNext()) {
            line = it.next().toString();
            if (line.startsWith("Alg.Alias.")) {
                aliasSet.add(line.substring(10));
            } else {
                algSet.add(line);
            }
        }

        // Print what we found.
        //
        System.out.println("JCE Provider algorithms :");
        System.out.println();

        System.out.println("Aliases :");

        Iterator aliasIt = aliasSet.iterator();

        while (aliasIt.hasNext()) {
            System.out.println(aliasIt.next().toString().replaceFirst("=", " = "));
        }

        System.out.println();

        System.out.println("Algorithms :");

        Iterator algIt = algSet.iterator();
        String algName;

        // Remove anything after the "=" for clarity
        //
        while (algIt.hasNext()) {
            algName = algIt.next().toString();
            System.out.println(algName.substring(0, algName.indexOf("=")));
        }

    }
}