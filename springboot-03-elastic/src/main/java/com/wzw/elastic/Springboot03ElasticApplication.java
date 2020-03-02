package com.wzw.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springBoot默认支持两种技术来和ES交互
 * 1、Jest(默认不生效)
 * 		需要导入jest的工具包（io.searchbox.client.JestClient;）
 * 2、SpringData ElasticSearch
 * 		1）Client	需要配节点信息clusterNodes,clusterName
 * 		2）ElasticsearchTemplate	操作es
 * 		3）编写ElasticsearchRepository的子接口来操作ES
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot03ElasticApplication.class, args);
	}

}
