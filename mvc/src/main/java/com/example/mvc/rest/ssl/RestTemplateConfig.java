package com.example.mvc.rest.ssl;

import com.example.mvc.rest.ExtractRestTemplate;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public ExtractRestTemplate httpsRestTemplate(HttpComponentsClientHttpRequestFactory requestFactory) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                // .requestFactory((Supplier<ClientHttpRequestFactory>) requestFactory)
                .errorHandler(new CustomHttpResponseErrorHandler())
                .build();
        restTemplate.setRequestFactory(requestFactory);
        List<HttpMessageConverter<?>> httpMessageConverters = restTemplate.getMessageConverters();
        httpMessageConverters.stream().forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter messageConverter = (StringHttpMessageConverter) httpMessageConverter;
                messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
            }
        });
        return new ExtractRestTemplate(restTemplate);
    }


    @Bean(name = "requestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        SSLContext sslcontext = SSLUtil.getSSLContext();
        // Allow TLSv1 protocol only
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1.2"},
                null,
                hostnameVerifier);
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslsf)
                .build();
        // 长链接保持时间长度20秒
        // PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new
        // PoolingHttpClientConnectionManager(20, TimeUnit.SECONDS);
        // 开始设置连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(2 * getMaxCpuCore() + 3);
        // 同路由并发数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(2 * getMaxCpuCore());
        HttpClientBuilder builder = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                // 重试次数3次，并开启
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                ;
        // 保持长链接配置，keep-alive
        // builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        //创建自定义的httpclient对象
        CloseableHttpClient httpclient = builder.build();

        // CloseableHttpClient httpclient = HttpClients.custom()
        //         .setSSLSocketFactory(sslsf)
        //         .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);
        // 链接超时配置 16秒
        requestFactory.setConnectTimeout(16000);
        // 连接读取超时配置
        requestFactory.setReadTimeout(16000);
        // 连接池不够用时候等待时间长度设置，分词那边 500毫秒 ，我们这边设置成3秒
        requestFactory.setConnectionRequestTimeout(3000);
        // 缓冲请求数据，POST大量数据，可以设定为true 我们这边机器比较内存较大
        requestFactory.setBufferRequestBody(true);
        return requestFactory;
    }


    private static int getMaxCpuCore() {
        int cpuCore = Runtime.getRuntime().availableProcessors();
        return cpuCore;
    }
}
