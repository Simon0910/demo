package com.mtf.mpa;

import com.tr.core.encrypt.utils.EncryptionUtils;
import com.tr.core.model.mpa.RemoteNotificationMessageRequest;
import com.tr.service.DecryptService;
import com.tr.utils.EncodingUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-04 16:08
 */
public class RemoteNotifyMessageControllerTest {
    public static void main(String[] args) throws Exception {
        step02();
    }

    /**
     * pkCertificateUrl:
     *      /mtf/pkCertificate.cer
     *
     *
     */
    private static void step02() throws IOException, GeneralSecurityException {
        String registrationCode = "6cc063fb-766d-4849-930d-eb0fdf4e15a5";
        String publicKey = "4E4F54205245414C2044415441202D20746869732077696C6C2062652061207075626C6963206B65792070726F7669646564206279207468652043726564656E7469616C73204D616E6167656D656E74202844656469636174656429";
        String encryptedData = "Tk9UIFJFQUwgREFUQSAtIHRoaXMgd2lsbCBiZSB0aGUgYWN0dWFsIGRhdGEgZW5jcnlwdGVkIHVzaW5nIHRoZSBNb2JpbGUgS2V5cyA=";

        byte[] bytes = EncodingUtils.hexDecode(publicKey);
        String decodePublicKey = new String(bytes, "UTF-8");
        System.out.println(decodePublicKey);

        byte[] bytes2 = EncodingUtils.base64Decode(encryptedData);
        String decodeEncryptedData = new String(bytes2, "UTF-8");
        System.out.println(decodeEncryptedData);

        InputStream in = RemoteNotifyMessageControllerTest.class.getClassLoader().getResourceAsStream("mtf/pkCertificate.cer");
        Certificate certificate = EncryptionUtils.loadEncryptionCertificate(in);
        PublicKey mastercardPublicKey = certificate.getPublicKey();
        System.out.println(mastercardPublicKey);

        // DecryptService.decrypt(privateKey, encryptedData);
    }

    /**
     * {
     *   "responseHost": "site1.mastercard.com",
     *   "registrationData": {
     *     "registrationCode": "6cc063fb-766d-4849-930d-eb0fdf4e15a5",
     *     "publicKey":
     *     "4E4F54205245414C2044415441202D20746869732077696C6C2062652061207075626C6963206B65792070726F7669646564206279207468652043726564656E7469616C73204D616E6167656D656E74202844656469636174656429",
     *     "pkCertificateUrl": "https://services.mastercard.com/mdes/paymentapp/1/0/pkCertificate"
     *   },
     *   "mobileKeysetId": "e279760f-f4cd-4683-ba42-4d8053817a69",
     *   "encryptedData":
     *   "Tk9UIFJFQUwgREFUQSAtIHRoaXMgd2lsbCBiZSB0aGUgYWN0dWFsIGRhdGEgZW5jcnlwdGVkIHVzaW5nIHRoZSBNb2JpbGUgS2V5cyA="
     * }
     *
     *
     * @throws UnsupportedEncodingException
     */
    private static void step01() throws UnsupportedEncodingException {
        RemoteNotificationMessageRequest request = new RemoteNotificationMessageRequest();
        request.setPaymentAppInstanceId("111111");
        request.setRequestId("123456");
        request.setNotificationData(
                "ew0KCSJyZXNwb25zZUhvc3QiIDogInNpdGUxLm1hc3RlcmNhcmQuY29tIiwNCgkicmVnaXN0cmF0aW9uRGF0" +
                        "YSIgOiB7DQoJCSJyZWdpc3RyYXRpb25Db2RlIiA6ICI2Y2MwNjNmYi03NjZkLTQ4NDktOTMwZC1lYjBmZGY0Z" +
                        "TE1YTUiLCANCgkJInB1YmxpY0tleSIgOiAiNEU0RjU0MjA1MjQ1NDE0QzIwNDQ0MTU0NDEyMDJEMjA3NDY4Nj" +
                        "k3MzIwNzc2OTZDNkMyMDYyNjUyMDYxMjA3MDc1NjI2QzY5NjMyMDZCNjU3OTIwNzA3MjZGNzY2OTY0NjU2NDI" +
                        "wNjI3OTIwNzQ2ODY1MjA0MzcyNjU2NDY1NkU3NDY5NjE2QzczMjA0RDYxNkU2MTY3NjU2RDY1NkU3NDIwMjg0" +
                        "NDY1NjQ2OTYzNjE3NDY1NjQyOSIsDQogICAgICAgICAgICAgICJwa0NlcnRpZmljYXRlVXJsIiA6ICJodHRwc" +
                        "zovL3NlcnZpY2VzLm1hc3RlcmNhcmQuY29tL21kZXMvcGF5bWVudGFwcC8xLzAvcGtDZXJ0aWZpY2F0ZSINCg" +
                        "l9LA0KCSJtb2JpbGVLZXlzZXRJZCIgOiAiZTI3OTc2MGYtZjRjZC00NjgzLWJhNDItNGQ4MDUzODE3YTY5Iiw" +
                        "NCgkiZW5jcnlwdGVkRGF0YSIgOiAiVGs5VUlGSkZRVXdnUkVGVVFTQXRJSFJvYVhNZ2QybHNiQ0JpWlNCMGFH" +
                        "VWdZV04wZFdGc0lHUmhkR0VnWlc1amNubHdkR1ZrSUhWemFXNW5JSFJvWlNCTmIySnBiR1VnUzJWNWN5QT0iD" +
                        "Qp9DQo=");

        byte[] bytes = EncodingUtils.base64Decode(request.getNotificationData());
        String notificationData = new String(bytes, "UTF-8");
        System.out.println(notificationData);
    }
}
