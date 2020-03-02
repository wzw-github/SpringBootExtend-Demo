package com.wzw.elastic;

import com.wzw.elastic.bean.Article;
import com.wzw.elastic.dao.ArticleDao;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


@SpringBootTest
class Springboot03ElasticApplicationTests {

	@Autowired
	ArticleDao articleDao;


	@Test
	void contextLoads() throws IOException {
		//1、给es中索引保存一个文档
		Article article = new Article(1,"好消息","张三","Hello World");
		Article article1=new Article(2,"搜索","lisi","哈哈哈");
		//2、构建一个索引功能
		articleDao.save(article);
		articleDao.save(article1);
	}

	@Test
	void testFindAll(){
		Iterable<Article> all = articleDao.findAll();

		all.forEach(new Consumer<Article>() {
			@Override
			public void accept(Article article) {
				System.out.println(article.toString());
			}
		});
	}

	@Test
	void testSearch(){
		//查询title为lisi，content为哈哈哈，模糊查询
		QueryBuilder builder = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("title", "lisi"))
				.must(QueryBuilders.matchQuery("content", "哈哈"));
		Iterable<Article> search = articleDao.search(builder);
		search.forEach(new Consumer<Article>() {
			@Override
			public void accept(Article article) {
				System.out.println(article.toString());
			}
		});
	}

	@Test
	void testFindName(){
		for (Article article:articleDao.findByContent("哈")) {
			System.out.println(article.toString());
		}
	}



}
