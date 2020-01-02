package com.lzp.mastercard.crypto;


import com.lzp.mastercard.security.mdes.MDESCryptography;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-08-29 15:42
 */
public class MyMesCryptography {

    public static void main(String[] args) throws FileNotFoundException {

        //证书文件路径
        InputStream publicCertificate = MyMesCryptography.class.getClassLoader().getResourceAsStream("crypto/publickey.cer");
        //证书库文件路径
        InputStream keystore = MyMesCryptography.class.getClassLoader().getResourceAsStream("crypto/mykeystore.p12");
        //和keystore关联的别名，这个alias通常不区分大小写
        String privateKeyAlias = "mykey";
        //密钥库文件格式PKCS12 密钥库和私钥用相同密码进行保护
        String privateKeyPassword = "password";
        MDESCryptography mdesCryptography = new MDESCryptography(publicCertificate, keystore, privateKeyAlias, privateKeyPassword);

        // InputStream publicCertificate = MyMesCryptography.class.getClassLoader().getResourceAsStream("file/Public Key-Encrypt.crt");
        // InputStream masterCardPrivateKey = MyMesCryptography.class.getClassLoader().getResourceAsStream("file/Private Key-Decrypt.key");
        // String publicKeyFingerprint = "8FC11150A7508F14BACA07285703392A399CC57C";
        // MDESCryptography mdesCryptography = new MDESCryptography(publicCertificate, myPrivateKey, publicKeyFingerprint);


        Map<String, Object> encryptedData = new HashMap<>();
        encryptedData.put("encryptedData", "v1");
        Map<String, Object> encryptedPayload = new HashMap<>();
        encryptedPayload.put("encryptedPayload", encryptedData);
        // Map<String, Object> pushFundingAccount = new HashMap<>();
        // pushFundingAccount.put("pushFundingAccount", encryptedPayload);

        /*
        {
            encryptedPayload : {
            "publicKeyFingerprint" : "4911531d9f06121a36ee0af1b1b5bb11fa1b42eb86841ebe136d9561ed15fd22",
            "encryptedKey" : "21ba184711640cc4d9be86c9f5d144d810bfb266fc7e38dc2ed3049cddd9fb7cb26f456ac27e0b1ae4f401a4c475efbfaca2dd863b99d60dc05b4ae9e781e7d2f31a14759444ede49ac3f823193b3d5f13873c8f460473ff5e3938358ed249b5af4fadd56d24733d55e22af68bd4421e6ecca69dbfb4d1ec59447c38baee5b632c439588175e8f2ba2790a79ca319ad7f27b30bbfb04b459be137a5cfd120f3e267e7ea672977a90223d6127a9158c662aeebeab57bf909623274c45f898147e650b19cb88f59290193b847ab2847c3338661a7d93b8c9b8c07797364e08f150720e440bddb0654264adc4b48d5cd90388d4dae7f4f0c284eaacfe8cb0a2ed8d",
            "encryptedData" : "6d58e7790332ff4da9e0bdf407981eca",
            "oaepHashingAlgorithm" : "SHA512",
            "iv" : "edcc5a0fd81a23b13293515919a590ac"
            }
        }
         */
        Map<String, Object> encrypt = mdesCryptography.encrypt(encryptedPayload);
        System.out.println(encrypt);

        Map<String, Object> decrypt = mdesCryptography.decrypt(encrypt);
        System.out.println(decrypt);
    }
}
