// package jce;
//
//
// import org.apache.commons.codec.binary.Base64;
//
// import javax.crypto.Cipher;
// import javax.crypto.SecretKey;
// import javax.crypto.SecretKeyFactory;
// import javax.crypto.spec.IvParameterSpec;
// import javax.crypto.spec.PBEKeySpec;
// import javax.crypto.spec.PBEParameterSpec;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.PushbackInputStream;
// import java.util.Arrays;
//
// /**
//  * @author liuzhipeng
//  * @description
//  * @create 2019-08-29 14:44
//  */
// public class Code {
//     /**
//      * Reads user password from given input stream.
//      */
//     public static char[] readPasswd(InputStream in) throws IOException {
//         char[] lineBuffer;
//         char[] buf;
//         int i;
//
//         buf = lineBuffer = new char[128];
//
//         int room = buf.length;
//         int offset = 0;
//         int c;
//
//         loop:
//         while (true) {
//             switch (c = in.read()) {
//                 case -1:
//                 case '':
//                     break loop;
//                 case '\r':
//                     int c2 = in.read();
//                     if ((c2 != '') && (c2 != -1)) {
//                         if (!(in instanceof PushbackInputStream)) {
//                             in = new PushbackInputStream(in);
//                         }
//                         ((PushbackInputStream) in).unread(c2);
//                     } else {
//                         break loop;
//                     }
//                 default:
//                     if (--room < 0) {
//                         buf = new char[offset + 128];
//                         room = buf.length - offset - 1;
//                         System.arraycopy(lineBuffer, 0, buf, 0, offset);
//                         Arrays.fill(lineBuffer, ' ');
//                         lineBuffer = buf;
//                     }
//                     buf[offset++] = (char) c;
//                     break;
//             }
//         }
//
//         if (offset == 0) {
//             return null;
//         }
//
//         char[] ret = new char[offset];
//         System.arraycopy(buf, 0, ret, 0, offset);
//         Arrays.fill(buf, ' ');
//
//         return ret;
//     }
//
//     public static void main(String[] args) throws Exception {
//         PBEKeySpec pbeKeySpec;
//         PBEParameterSpec pbeParamSpec;
//         SecretKeyFactory keyFac;
//
//         // Salt
//         byte[] salt = {
//                 (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
//                 (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99
//         };
//
//         // Iteration count
//         int count = 20;
//
//         // Create PBE parameter set
//         pbeParamSpec = new PBEParameterSpec(salt, count);
//
//         // Prompt user for encryption password.
//         // Collect user password as char array (using the
//         // "readPasswd" method from earlier code), and convert
//         // it into a SecretKey object, using a PBE key
//         // factory.
//         System.out.print("Enter encryption password: ");
//         System.out.flush();
//         pbeKeySpec = new PBEKeySpec(readPasswd(System.in));
//         keyFac = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
//         SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
//
//         // Create PBE Cipher
//         Cipher pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
//
//         // Initialize PBE Cipher with key and parameters
//         pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
//
//         // Our cleartext
//         byte[] cleartext = "This is another example".getBytes("UTF-8");
//
//         // Encrypt the cleartext
//         byte[] ciphertext = pbeCipher.doFinal(cleartext);
//         String s = Base64.encodeBase64String(ciphertext);
//         System.out.println(s);
//
//         byte[] iv = pbeCipher.getIV();
//         IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//
//
//         System.out.println("=========================================");
//         // Create PBE parameter set
//         pbeParamSpec = new PBEParameterSpec(salt, count);
//
//         // Prompt user for encryption password.
//         // Collect user password as char array (using the
//         // "readPasswd" method from earlier code), and convert
//         // it into a SecretKey object, using a PBE key
//         // factory.
//         System.out.print("Enter encryption password: ");
//         System.out.flush();
//         pbeKeySpec = new PBEKeySpec(readPasswd(System.in));
//         keyFac = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_128");
//         pbeKey = keyFac.generateSecret(pbeKeySpec);
//
//         // Create PBE Cipher
//         pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_128");
//
//         pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
//         byte[] bytes = pbeCipher.doFinal(ciphertext);
//         System.out.println(new String(bytes));
//
//
//     }
// }
