package com.wzw.elastic.dao;

import com.wzw.elastic.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    List<Article> findByContent(String name);
}
