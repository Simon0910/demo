package com.service;

import com.alibaba.fastjson.JSON;
import com.tr.core.model.inbound.NotifyTokenUpdatedRequest;
import com.tr.service.DecryptService;
import com.tr.service.EncryptedPayload;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-17 16:37
 */
public class DecryptServiceTest {

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        DecryptService ds = new DecryptService();

        String plaintext = ds.decrytEncryptedPayload(buildEncryptedPayload());

        System.out.println(plaintext);

        NotifyTokenUpdatedRequest notifyTokenUpdatedRequest = JSON.parseObject(plaintext,  NotifyTokenUpdatedRequest.class);
        System.out.println(notifyTokenUpdatedRequest);
    }

    private void test01() {
        String str = "sgoLD3Ac63fBAQr7nXI+5LV0HbFgjxqnfPykoyW4Ge6dxeiYmPELURVY2CiAPxG0L0i8bVjwiUNM7UddoNIFhYdsGwuzl2SXaElxVua0dYc/qiI1DFF82Qo8qMIn3K1YezU9NV15nylPqlTjjvoBiK2fGRAZjVjMVUnPNRmaFgD7L2MJjxwtiUHsjkf64Tac+tV0ptqazlKYdVZNJPrpRqpYLbyGYz5MncnKB7gq15VAAa39aUiGT6yVuYyt5PTD61CnusQTinQ894O4TuDMWUMK3fuVvPUGm0EqaQ1wNBYDaSgxJbsB7rqP9PWWJy2K0ufQFpa+YeYTGttzHam7W1u0GhrlER7ERMMbiC9RjNnjI/KUtccVsxDk+Ssi6VhAaY8Z+IPg4W8=";
        DecryptService ds = new DecryptService();
    }

    private static EncryptedPayload buildEncryptedPayload() {
        EncryptedPayload encryptedPayload = new EncryptedPayload();
        encryptedPayload.setPublicKeyFingerprint("ccc5f29ed75bd44bb4a8727aad85d95801dc54c8");
        encryptedPayload.setEncryptedKey("16c2fcc550e1cb7647f8965974f021372f2eb66ae7253822359327751b9b50581dcc56202963e77055caa15af8e26c58a1cd087cb2e891df211c1d8fae95216294306e6f0d916148f7c0c61d1dbf85f94f69debc34a9c8b9e675c3ee270238909ce37630411ef67ad975583442f44c05b7aa18d65ec5f104cac93f5ce40035c36ea84096f3cf1a0ae0e6519336b571fe9a1f57632dcb691e73407c142b96fe582fa16305b54a81ceaa3983d103724c39dbe6a90aff28049ce6ce56df74d3bcbfb38cea567ea579663a4b62ea31959780491227d892bdfd16aef54933e49003454fa7098a0b66362e67de2122018c4bb14a3a79f468861319ac28e9defc4452f7");
        encryptedPayload.setIv("4376d2cb9ba9ba872725600b1458444b");
        encryptedPayload.setOaepHashingAlgorithm("SHA512");
        encryptedPayload.setEncryptedData("f2586b783718f1e3e1cd6d3647b0f4dfac2dbd3bf37a77dfd31203b932bdaee3f0d8b504951fa92891ad746fe0a6a40efdaad19cf13078581960294ffa045b61e1aca29df9aa5e715e01228a07e028b3bc8f3d53579b024d92beb2c0281efc4bf084f4f81551a228ee0e3028ad7082dd4f2630fa567e7950b234ae85388ed4e53a195bca2fd725df930a22257e9f6c90668eb08c4f13359d76ca14198f6445df5a47beb8f339d9494d4576574d70d263ccef74a19c17f50ed76386c71d47283c7554e12a1c0188ee453cb2402ab30ef9995b4c78a1a4d21ce97e134de533c72b14687a95554af1b41de4d9c5e941b194");
        return encryptedPayload;
    }
}
