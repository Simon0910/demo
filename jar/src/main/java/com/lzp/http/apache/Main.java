package com.lzp.http.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-16 17:19
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 1. MTF Stubs - Digitization API (authenticated):
        String url = "https://services.mastercard.com/virtual/mdes/digitization/1/0/checkEligibility";
        // 2. MTF Stubs - Transaction Details API (unauthenticated):
        // url = "https://services.mastercard.com/virtual/mdes/tds/1/0/getRegistrationCode";
        // 3. MTF:
        url = "https://mtf.services.mastercard.com/mtf/mdes/digitization/1/0/checkEligibility";
        // 4. Production:
        // url = "https://services.mastercard.com/mdes/digitization/1/0/checkEligibility";

        CloseableHttpClient httpclient = HttpClientFactory.getCloseableHttpClient();

        HttpPost httppost = new HttpPost(url);
        System.out.println("ExecutingRequest:" + httppost.getRequestLine());

        url = "https://mtf.services.mastercard.com/mtf/mdes/assets/1/0/asset/3789637f-32a1-4810-a138-4bf34501c509";
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        System.out.println("-------------------------------------");
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(entity));
        EntityUtils.consume(entity);

        response.close();
        httpclient.close();

    }
}
