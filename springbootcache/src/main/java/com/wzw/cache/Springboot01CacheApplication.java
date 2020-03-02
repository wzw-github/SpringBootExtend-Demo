package com.wzw.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 	1、导入数据库文件 创建department和employee表
 * 	2、创建javabean封装数据
 * 	3、整合Mybatis操作数据库
 * 		1）配置数据源信息
 * 		2)使用注解版的Mybatis
 * 			(3)@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 * 	1、开启基于注解的缓存@EnableCaching
 * 	2、标注缓存注解即可
 * 		@Cacheable
 * 		@CacheEvict
 * 		@CachePut
 * 三、整合Redis作为缓存
 * 	1、安装Redis，这里使用Docker
 * 	2、引入redis的starter
 * 	3、配置redis
 * 	4、测试缓存
 * 		原理：CacheManager创建Cache缓存组件来给缓存中的存储数据
 * 		1)引入redis的starter,容器中保存的是RedisCacheManager
 * 		2)RedisCacheManager帮我们创建RedisCache来作为缓存组件，RedisCache通过操作Redis缓存数据
 * 		3）默认保存数据k-v都是Object，利用序列化保存
 * 			1、引入redis的starter，CacheManager变为RedisCacheManager
 * 			2、默认创建的RedisCacheManager操作Redis的时候使用的是RedisTemplate<Object,Objet>
 * 			3、RedisTemplate<Object,Objet>是默认使用Jdk的序列化机制
 * 		4）自定义CacheManager
 */
@MapperScan("com.wzw.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}

}
