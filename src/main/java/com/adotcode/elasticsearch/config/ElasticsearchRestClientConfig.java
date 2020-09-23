package com.adotcode.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

/**
 * ES连接客户端配置
 *
 * @author risfeng
 * @date 2020/9/19
 */
@Slf4j
@Configuration
public class ElasticsearchRestClientConfig {

    /**
     * 地址分割长度
     */
    private static final int ADDRESS_SPLIT_LENGTH = 2;

    /**
     * 权限验证
     */
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

    /**
     * 使用冒号隔开ip和端口1
     */
    @Value("${elasticsearch.address}")
    private String[] ipAddress;

    /**
     * Http协议
     */
    @Value("${elasticsearch.scheme}")
    private String scheme;

    /**
     * 用户名
     */
    @Value("${elasticsearch.username}")
    private String username;

    /**
     * 密码
     */
    @Value("${elasticsearch.password}")
    private String password;

    /**
     * 客户端构造器
     *
     * @return {@link RestClientBuilder} 构造器
     */
    @Bean
    public RestClientBuilder restClientBuilder() {
        HttpHost[] hosts = Arrays.stream(ipAddress)
                .map(this::makeHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        log.debug("hosts:{}", Arrays.toString(hosts));
        // client builder
        RestClientBuilder clientBuilder = RestClient.builder(hosts);
        //配置权限验证
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            clientBuilder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        return clientBuilder;

    }

    /**
     * ES客户端
     *
     * @param restClientBuilder 客户端构造器
     * @return {@link RestHighLevelClient} 客户端实例
     */
    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }


    /**
     * 处理请求地址
     *
     * @param s IP:Port
     * @return {@link HttpHost}  主机端口
     */
    private HttpHost makeHttpHost(String s) {
        assert StringUtils.isNotEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_SPLIT_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port, scheme);
        } else {
            return null;
        }
    }

}
