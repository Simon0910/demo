package com.lzp.http.okhttp;

import com.lzp.http.okhttp.interceptor.RetryIntercepter;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-10 11:42
 */
public class RetryTest {
    String mUrl = "https://www.baidu.com/";
    OkHttpClient mClient;

    @Before
    public void setUp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(new RetryIntercepter(3))//重试
                .addInterceptor(logging)//网络日志
                .addInterceptor(new TestInterceptor())//模拟网络请求
                .build();
    }


    @Test
    public void testRequest() throws IOException {
        Request request = new Request.Builder()
                .url(mUrl)
                .build();
        Response response = mClient.newCall(request).execute();
        System.out.println("onResponse:" + response.body().string());
    }


    class TestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            System.out.println("url=" + url);
            Response response = null;
            if (url.equals(mUrl)) {
                String responseString = "{\"message\":\"我是模拟的数据\"}";//模拟的错误的返回值
                response = new Response.Builder()
                        .code(503)
                        .request(request)
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                        .addHeader("content-type", "application/json")
                        .message("message")
                        .build();
            } else {
                response = chain.proceed(request);
            }
            return response;
        }
    }

}

