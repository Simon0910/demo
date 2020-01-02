package com.lzp.mastercard.crypto.rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-27 14:59
 */
public class Demo {
    private static final String PKCS_1_PEM_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PKCS_1_PEM_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String PKCS_8_PEM_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PKCS_8_PEM_FOOTER = "-----END PRIVATE KEY-----";

    private static final String PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    private static final String PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";



    public static void main(String[] args) throws GeneralSecurityException, IOException {
        String privateKeyStr = "-----BEGIN PRIVATE KEY-----" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5953DRRCpB2q9" +
                "dHB8Gt4sxDDPu0HzaHCO1GApITKowGTWauwAndLy2X4jyotEx8FwBk9MQtxrXgaY" +
                "MJllcNn3v/6inyVD5xFZtykIcaIfHWEnpiOpTaKmICSg6OUBZezLQ0hufPA9DGmv" +
                "I2AN2XrMuaUJFVOdZB+IoFK8ttEFhhlnigsKHjwii45VI4fKtdno1kH/hRBqGtle" +
                "pi2a/9t96Vve2wW0/DpX1v7fjqR8wQDt2nYjg2BsRPmLDClvft0rmswqLDaPWm42" +
                "PoJ7Zo7ANhL6efs5lxyG/Ohl1fFJwxwnEeGMVNdCXjM8uyX0fxhhi3TGN3CNcFmZ" +
                "cVwfQqWHAgMBAAECggEAPADzfIAaYg+slzis2r5olZUwErbfDsTSHKPV6y91AxIg" +
                "9zZEs+Y4nHckxjOiQC0b8yN2Dqs8J8CM7CoyNJL3IYNni91BzukNph79NGZF4zg9" +
                "kud+djBAnM2eQ/UhfxP+FY++HThe47PPnd0SLyXPA6Oa5pVlB1G5AwKH77AlPVx8" +
                "Av0yli/mnUZGn9yOKYM0hO5g2WDGeD4518P5Ho5RW3SjuFvvNtjbjCjGKYFVriOr" +
                "7NkLaWL11YoF7kQlxDuwRvC+DviWrpir+ITGXevQFPtkbtRsvsN1Myj0APtJ1Wh0" +
                "WqWpOwgVj0bo49/+eRvgYZ9mA5BwuNLMpTxazXXKkQKBgQDv0DLwl4tIlr2Au5A6" +
                "dqkxEoMcf86Xh1NdjT+js31kOR+0UdzXwGRVEphDxbL/UUWTO5amFMnTnCf5BhG5" +
                "gjALEfERTzSfiEXZUkcMgmnDXJ1T8qeAmC+F1+3sELWG9vdmnindJrXFsrI3iVYI" +
                "6O9XgHCRy4tIYeC7LCOjfmOoGwKBgQDGhP8k3c5Jaf5JL3xS8d/8oqsdE2oPHOGP" +
                "QsQofZYURBwbwlyW9MMP2SG+lZD7vH/VnFEHmJZB6tJQAYbrHb1ejM5Nllc75rdI" +
                "lQZEqtO0pM6/V7ajwNq4VPYnVb2RzkbPFwjBDaQZAJHOrhTqxDPlnazxCPoFbxiF" +
                "/vqTGeDnBQKBgBHPMWlCp9pdL3eW9lARLeFyEYCVUT9ZkmxZKAe9NauyvlMC3Va8" +
                "d/cFaX9m+DMT0wnrG/X7hz2K1EEfHUPckjrQO0ngyvSqBx3riVE5gic7AogQdjYL" +
                "l0PmVEn6t7BtONRLHKrKzCcBbVDIhzYkLloeNL3vEsnuUuUzkvX6VeZPAoGBALwG" +
                "C4JncpKmCWpXx3DoFjW5/Ee1s6ZMvHZrzk5rZFIdkGHaXMe9W2MPTJ6uGnvTtWAk" +
                "hbqkEuyr9DZCu/7EVC/RPTTPSCA8DSx5uU4gXhOiZQ9ljF7cskwUbHAlDT+kF/zc" +
                "3DW9Lcdgerk+uCEDZAP+q2ZAEk5DQv7m2X2p7RHhAoGBALkpN4H2xT2VgIHw9UMp" +
                "Iih22bn/nX237gqedVIDQrVJFXsdZR7d/OjYIn3jtorxKPosqIu3qgkl0rG/zU1Y" +
                "Q2mZw81U3RMGS/9ByIdjhJfEdOj/RcXvkKHzdVKUvYTCpBwyZUo/oyCKw9bleulz" +
                "ggbtJ28XwfOvJNJuoT0mg8CV" +
                "-----END PRIVATE KEY-----";
        privateKeyStr = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChveB4Q1oxKqyv\n" +
                "R/pOk03JcKRRIgcFuC8hyANUqDnKJ+Sv6V7OigILDLKH6twfmy5xmRVbmdPcHRVU\n" +
                "wxthvVc0isSdf5SYiRSRWYiomKg63U5phe4WzKrrXyWaUBaEuKLYXoudawFYgwic\n" +
                "kuGBXHDLk7rkhquGWgeUNJd/mBKZ80iYhie6q4abWQi4C6fmJSozO1LtnyQMlRSu\n" +
                "Detgfaqdcha01fBd/XmHfcjKij0Ez26TmS3oUSPSACpmjqO4QrqQnSAtekoCp3YQ\n" +
                "8bn05qqHf2VSoU2TbM+qDthh41Wmd7ErbBmz0+K33b4opMOdloFOzRLtu6cekR1Y\n" +
                "GX8EUz4dAgMBAAECggEANyVvSHHmaYccU7Md4/aTehA+5i7M6CVRZJo2kIG/Gm8Z\n" +
                "qOTP32nsomnU4USxgtE9N5wzTQUY2B7ObBUN2/hbqF18Bhs+xfdPHPPDBYDvMwrL\n" +
                "tmzkrb9gs1nAj2mpE92EIHOz9JtMXSpoIEJwQtQK3U5kVZCZIPjHRr/bu7RU+0oN\n" +
                "3/uEn5HN5+lWz7OGeHAAK0x7XOeioeTZuPAWdwpOk5DBUrUuvtenCDHAifgA9ph0\n" +
                "+7NiCU3KuEWdnPzfr33vw7+or/Yk9L963XFQajD6VrpXRoxyVqJTSi6gs5U58zOS\n" +
                "1SR1ANCrKaGfIjDBi10/HnaRnXwAtCAainB1DKfcAQKBgQDUMJEewlx46SOvUMEC\n" +
                "sFKYEk1dLuy9p7HmZC/oTtvtIvayDXLllXUvcuAGeJFrlwYX0sPI43pfSVKI4sTH\n" +
                "WhdWWhz42/7BXASpGY7eXDOwFdRdPe8H/xR1tQq9eIPeTEERjGdxL3c4lISF6V3L\n" +
                "y4JdeYSHl1Do6+h/befQleMJAQKBgQDDItiJCC7SAo0rA8w3+4OU1DguKiwWP5cf\n" +
                "yWq39Q+3qO9kwGzpoJnpRe2pQZI5Xl9VuGigsGHRQvXa86hajDrSGQSE8mHlBwzw\n" +
                "oceWNXrG8CRtPqpbRB4hGmZzZphyHJ27wxePZpzYZZUJsHWQxCZT4shvPsB1uKiQ\n" +
                "OrNJEpo5HQKBgQC/eZAK5LLTfPZxpnp1Na1igumDE+wX/HKBh5jflgHJjIp3A8HR\n" +
                "wLu9dHkJ/dSj9OVPrCZ2aiesQpsS4SdXCUoT5z9LpiHOVH30JhcROrx9wkUfWtsP\n" +
                "KTRKP3OQpGbCu6bf8gYKUh3QVdz8hijY1S0vso9QV9BwTyk/jGrqg8cbAQKBgDg4\n" +
                "l+6TPvoe5hFYnMQcYQLGShFy8RTP6XitDWRG5Opjb+W1UyvUq8N79Edprvl8aTdH\n" +
                "VE3Hw8cI5dDZls3PzTqvHpA3TWExMNc7pUdvzRn4ImafQXHeJFghRRdGnfFysTdh\n" +
                "tC4KwHHQgqox/w1gljJhCqKSj9zbASXc477P70M5AoGAVeEbC5qz6+EhJGagL7Pj\n" +
                "MqpIZP4gK1Q4nCXXN9I5Fq6gF1zeSdTdHB0Wv3K3jJGJZS8qtIo18yyUu9ZhaBHD\n" +
                "13UBCXEkoj2geTQoctXQIvMOH77VGDiwdR/gNi6V+s6+q4lzG9tEZ2Gjhnn/KaDp\n" +
                "N2QzYI8hn6X9QVHPynvq9Fw=\n" +
                "-----END PRIVATE KEY-----\n";
        privateKeyStr="-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC81gw7UwnKUP0x\n" +
                "8734o47ygXAX8yJNlgkgx5eHV85ztLPHquPmil+gDjgiEIaKFbrBWWBIbsFFHyHy\n" +
                "+bIKs7A5bXDDK/9nTBcTt3DyEU0VtftCWWClbyAC1RjXHKWPvo0+0E05RWa778rb\n" +
                "3ELDunG5asgzDbUDKNqvASA8qmUYayNhnNUT9uniO1PHl4G8/Eg+lDHgj3jKT4QY\n" +
                "iLY/XD8iu7LJiB0H8Ov2BRlkrwTrPqaRfbgFgd7fNAS+Pb1qolVY/UlZdDCJ5T9e\n" +
                "tOG86bncFViUz/LfCrjvTRuir8lqFhcBkQ8xM3dyWLRy3y78IeVDiwMeBMCO2xiw\n" +
                "EF1gC4nBAgMBAAECggEAcCyq+Xa7gx6XOdw0BoaKfQ5dGy0IPJSa+Yj3P6PudRds\n" +
                "EHk4hpO+eGLZ/4pXkaGU3hSMuJpqXvg+f4rKs8bIMWUMrjPttVwi/UhkZLfYVcRT\n" +
                "7LcRTVT07BnQdsCCrSAh4aV1qkbK32tz0yVF1+VidyWypXlIBQd42KrPjpoeEeFU\n" +
                "0R4W3NiIQ470o7yc6HbKSBbj64piLHKAL5VrooMFA8vHTCuPCG7XNmFev4Ov2jAT\n" +
                "KzNI0g/YME9Xjd0a/xp9VVlPMpPX0w+Kt2BECEjMKjn05P81udeoXGvR+pGlArke\n" +
                "EV3V0PQTCJu4s0jy9K1WiU+6mWVFXhamjlMdMnp7QQKBgQDwZSrvpNyWhovGmT8m\n" +
                "TeBQf3gA2nUlVJGDPZkx5DJMnXPKT5ifDXJozRf1ynl05LCh+/2JCt9B79O9s9q2\n" +
                "WbkH61J0cAkDudPq47liTF3P8hqLdG0t98FTokLRU2Esh493SpSK4G8Aw0ujvWeY\n" +
                "Miu4bRfulYxmnZFOtTTnTvKNCQKBgQDJGBVWFwZauO2huBXfSkl/9QYBQT42MUO+\n" +
                "OrNeCH1I5pVbRO+fTevgMSK18+ZADxPP5At/NsJmA3Rz58z6DtEqqC45dk203zjs\n" +
                "icwz0lXfEggdyOh5taaIeVpXNXXYsFNrhR3ceKFZaSOwureF+HunlSuiqrRKbZHN\n" +
                "XdgQj1B8+QKBgQDNp57duQpFdAEr+3o8yK637vS4tis1rm11+0KNng0x4hlm4EVO\n" +
                "/G/+HEb9LSROzj4VtcAmdzib7hIU3kKjcYqLrdOybQ4pM9aJwhucbnxrI1F2MDoe\n" +
                "8y9e3xeOgGhHcglYImOFk48i9fBFuglf+LvSYPHyV9nvB6mBzh/eKnnyEQKBgAYg\n" +
                "qr3F09a93dfYsSntrJGumLE/GLM0Cxln1oFeBttK03BFiHu1NyPMu38J4JFBJFxo\n" +
                "WYVV2NSc9FZ30MIp42vGVipAFZwWVTi39jX6Cc3TUdX+gj77Lwjnkb4ojd+9d71G\n" +
                "ACPwtRHxZzoCZoc5jCMOI5dAVmASOKgmXOgcRdqZAoGAfUXOHmO97rcg3XKhF+H1\n" +
                "xXx2oqbz3VJkTjUBSIKvwy/Jyu92Poxko6Gfd1dtbSm0RgYTVGtljEcEWsKquScD\n" +
                "7AqH4S9wW1tSoUtu1POPElJ6zNwxj/pQe81Dfy4HziqK/NykGsobI3aRp8IjlaP/\n" +
                "KT1TdvkmfSztRNsZzooP8S4=\n" +
                "-----END PRIVATE KEY-----\n";
        privateKeyStr = "-----BEGIN PRIVATE KEY-----\n" +
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM1PmgOpxvFkni42\n" +
                "7RMm04LWrQAbERM0Cx5Tex8VxHEsw/nTJ/8hS25/dRwxiaf4sZiZkHV3aufbgQsG\n" +
                "JZgwamF/eaIMZ62iKJiENEeb+64K17CX4C/3Yye5FECJMcxiBHlY78o4x6icwY9m\n" +
                "//4mX/8GE1/HEwOEAhBPVNADybMzAgMBAAECgYA+8XSKQvaW8v37tSMhh/m4VxBa\n" +
                "w/GMLZxrA7TvZsD+4XaDlNTiRtbVXYYNyrIlKP1ph0reeGnyAz2gn5XO/qjeBoPD\n" +
                "xciFLZXiWchp5uh5xt6pScoaWu8MuZYT5C2IC3Tvkd8M6V+2X0cMsLwNek2VXS0G\n" +
                "nK7Zcsysrvskt0sM4QJBAO6S/OisSQJxGNYI6pfB4HE9OlP1YGdkApMwE7Ssj6+J\n" +
                "ClBzFtThwGPV77Rsx/xRMHNfGhEmpK9LhsUm30hEwb0CQQDcTqL6s3SmtgWFKGlO\n" +
                "zx+cp90otZnU4lqaT9i1T7ht0a4Ksjrm+Tnu2W31OoDZi1HtV2pvXk1hMrIp6GBQ\n" +
                "5v+vAkALXIKgzfuYGyy0nK4XVgtLOUA62Vei9NdOjR6THxikomEUXba7opiX6ItG\n" +
                "y1tmv7MP+3pxUKbNFkqvvt8pQOT9AkBWBqLfkGmxm4wmRfRxILHwYyAaAGe0WDsW\n" +
                "3Kz7h8i1oVX7uUME5Db6DI9CXfjoed2g9yyJlmJS3Q/zC1lo3frjAkEA6L5d7KcO\n" +
                "g0r9QFWcSV1tyAE/fvrKjIQRHhmZoJUrH2V4mLdxbT8Pwps/SMRy1gtY9TF9tbL1\n" +
                "mSePc91qHHLFnQ==\n" +
                "-----END PRIVATE KEY-----\n";
        String publicKeyStr = "-----BEGIN PUBLIC KEY-----" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufedw0UQqQdqvXRwfBre" +
                "LMQwz7tB82hwjtRgKSEyqMBk1mrsAJ3S8tl+I8qLRMfBcAZPTELca14GmDCZZXDZ" +
                "97/+op8lQ+cRWbcpCHGiHx1hJ6YjqU2ipiAkoOjlAWXsy0NIbnzwPQxpryNgDdl6" +
                "zLmlCRVTnWQfiKBSvLbRBYYZZ4oLCh48IouOVSOHyrXZ6NZB/4UQahrZXqYtmv/b" +
                "felb3tsFtPw6V9b+346kfMEA7dp2I4NgbET5iwwpb37dK5rMKiw2j1puNj6Ce2aO" +
                "wDYS+nn7OZcchvzoZdXxScMcJxHhjFTXQl4zPLsl9H8YYYt0xjdwjXBZmXFcH0Kl" +
                "hwIDAQAB" +
                "-----END PUBLIC KEY-----";
        publicKeyStr = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAob3geENaMSqsr0f6TpNN\n" +
                "yXCkUSIHBbgvIcgDVKg5yifkr+lezooCCwyyh+rcH5sucZkVW5nT3B0VVMMbYb1X\n" +
                "NIrEnX+UmIkUkVmIqJioOt1OaYXuFsyq618lmlAWhLii2F6LnWsBWIMInJLhgVxw\n" +
                "y5O65IarhloHlDSXf5gSmfNImIYnuquGm1kIuAun5iUqMztS7Z8kDJUUrg3rYH2q\n" +
                "nXIWtNXwXf15h33Iyoo9BM9uk5kt6FEj0gAqZo6juEK6kJ0gLXpKAqd2EPG59Oaq\n" +
                "h39lUqFNk2zPqg7YYeNVpnexK2wZs9Pit92+KKTDnZaBTs0S7bunHpEdWBl/BFM+\n" +
                "HQIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";
        publicKeyStr = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvNYMO1MJylD9MfO9+KOO\n" +
                "8oFwF/MiTZYJIMeXh1fOc7Szx6rj5opfoA44IhCGihW6wVlgSG7BRR8h8vmyCrOw\n" +
                "OW1wwyv/Z0wXE7dw8hFNFbX7QllgpW8gAtUY1xylj76NPtBNOUVmu+/K29xCw7px\n" +
                "uWrIMw21AyjarwEgPKplGGsjYZzVE/bp4jtTx5eBvPxIPpQx4I94yk+EGIi2P1w/\n" +
                "IruyyYgdB/Dr9gUZZK8E6z6mkX24BYHe3zQEvj29aqJVWP1JWXQwieU/XrThvOm5\n" +
                "3BVYlM/y3wq4700boq/JahYXAZEPMTN3cli0ct8u/CHlQ4sDHgTAjtsYsBBdYAuJ\n" +
                "wQIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";
        publicKeyStr = "-----BEGIN PUBLIC KEY-----\n" +
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNT5oDqcbxZJ4uNu0TJtOC1q0A\n" +
                "GxETNAseU3sfFcRxLMP50yf/IUtuf3UcMYmn+LGYmZB1d2rn24ELBiWYMGphf3mi\n" +
                "DGetoiiYhDRHm/uuCtewl+Av92MnuRRAiTHMYgR5WO/KOMeonMGPZv/+Jl//BhNf\n" +
                "xxMDhAIQT1TQA8mzMwIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";

        PrivateKey privateKey = loadDecryptionKey(privateKeyStr);
        PublicKey publicKey = loadPublicKey(publicKeyStr);

        String originStr = "i am an chinees";

        String encrypt = RSACipher2.encrypt(originStr, publicKey);
        System.out.println("encrypt = " + encrypt);

        String decrypt = RSACipher2.decrypt(encrypt, privateKey);
        System.out.println("decrypt = " + decrypt);
    }



    public static PublicKey loadPublicKey(String publicKey) {
        publicKey = filterHeaderAndFooter(publicKey);
        Key key = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new X509EncodedKeySpec(base64Decode(publicKey));
            key = keyFac.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (PublicKey) key;
    }


    public static String filterHeaderAndFooter(String keyDataString) {
        keyDataString = keyDataString.replace(PUBLIC_KEY_HEADER, "");
        keyDataString = keyDataString.replace(PUBLIC_KEY_FOOTER, "");
        return keyDataString;
    }


    /**
     * Load a RSA decryption key from a file (PEM or DER).
     */
    public static PrivateKey loadDecryptionKey(String keyDataString) throws GeneralSecurityException, IOException {
        // byte[] keyDataBytes = Files.readAllBytes(Paths.get(keyFilePath));
        // String keyDataString = new String(keyDataBytes, StandardCharsets.UTF_8);

        if (keyDataString.contains(PKCS_1_PEM_HEADER)) {
            // OpenSSL / PKCS#1 Base64 PEM encoded file
            keyDataString = keyDataString.replace(PKCS_1_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_1_PEM_FOOTER, "");
            return readPkcs1PrivateKey(base64Decode(keyDataString));
        }

        if (keyDataString.contains(PKCS_8_PEM_HEADER)) {
            // PKCS#8 Base64 PEM encoded file
            keyDataString = keyDataString.replace(PKCS_8_PEM_HEADER, "");
            keyDataString = keyDataString.replace(PKCS_8_PEM_FOOTER, "");
            return readPkcs8PrivateKey(base64Decode(keyDataString));
        }

        // We assume it's a PKCS#8 DER encoded binary file
        return readPkcs8PrivateKey(base64Decode(keyDataString));
    }


    /**
     * Create a PrivateKey instance from raw PKCS#8 bytes.
     */
    private static PrivateKey readPkcs8PrivateKey(byte[] pkcs8Bytes) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SunRsaSign");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8Bytes);
        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException("Unexpected key format!", e);
        }
    }

    private static byte[] join(byte[] byteArray1, byte[] byteArray2){
        byte[] bytes = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, bytes, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, bytes, byteArray1.length, byteArray2.length);
        return bytes;
    }
    /**
     * Create a PrivateKey instance from raw PKCS#1 bytes.
     */
    private static PrivateKey readPkcs1PrivateKey(byte[] pkcs1Bytes) throws GeneralSecurityException {
        // We can't use Java internal APIs to parse ASN.1 structures, so we build a PKCS#8 key Java can understand
        int pkcs1Length = pkcs1Bytes.length;
        int totalLength = pkcs1Length + 22;
        byte[] pkcs8Header = new byte[] {
                0x30, (byte) 0x82, (byte) ((totalLength >> 8) & 0xff), (byte) (totalLength & 0xff), // Sequence + total length
                0x2, 0x1, 0x0, // Integer (0)
                0x30, 0xD, 0x6, 0x9, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0xD, 0x1, 0x1, 0x1, 0x5, 0x0, // Sequence: 1.2.840.113549.1.1.1, NULL
                0x4, (byte) 0x82, (byte) ((pkcs1Length >> 8) & 0xff), (byte) (pkcs1Length & 0xff) // Octet string + length
        };
        byte[] pkcs8bytes = join(pkcs8Header, pkcs1Bytes);
        return readPkcs8PrivateKey(pkcs8bytes);
    }


    private static final int[] b64ints = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
            55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2,
            3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

    /**
     * <dependency>
     *             <groupId>com.mastercard.developer</groupId>
     *             <artifactId>client-encryption</artifactId>
     *             <version>1.3.3</version>
     *         </dependency>
     * @param value
     * @return
     */
    public static byte[] base64Decode(String value) {
        if (null == value) {
            throw new IllegalArgumentException("Can't base64 decode a null value!");
        }
        byte[] valueBytes = value.getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int i = 0; i < valueBytes.length;) {
            int b;
            if (b64ints[valueBytes[i]] != -1) {
                b = (b64ints[valueBytes[i]] & 0xFF) << 18;
            }
            // skip unknown characters
            else {
                i++;
                continue;
            }

            int num = 0;
            if (i + 1 < valueBytes.length && b64ints[valueBytes[i + 1]] != -1) {
                b = b | ((b64ints[valueBytes[i + 1]] & 0xFF) << 12);
                num++;
            }
            if (i + 2 < valueBytes.length && b64ints[valueBytes[i + 2]] != -1) {
                b = b | ((b64ints[valueBytes[i + 2]] & 0xFF) << 6);
                num++;
            }
            if (i + 3 < valueBytes.length && b64ints[valueBytes[i + 3]] != -1) {
                b = b | (b64ints[valueBytes[i + 3]] & 0xFF);
                num++;
            }

            while (num > 0) {
                int c = (b & 0xFF0000) >> 16;
                outputStream.write((char)c);
                b <<= 8;
                num--;
            }
            i += 4;
        }
        return outputStream.toByteArray();
    }
}
