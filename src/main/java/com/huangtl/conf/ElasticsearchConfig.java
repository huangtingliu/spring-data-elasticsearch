package com.huangtl.conf;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.huangtl.repository")
public class ElasticsearchConfig {

    @Value("elasticsearch.host")
    private String EsHost;
    @Value("elasticsearch.port")
    private String EsPort;
    @Value("elasticsearch.cluster-name")
    private String EsClusterName;


    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        System.out.println("初始化elasticsearchTemplate");
        //return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
        Client client = null;
        return new ElasticsearchTemplate(client);
    }
}
