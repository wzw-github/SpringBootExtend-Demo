package com.wzw.elastic.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *  indexName:名称，中文控制台可能输出乱码，但是取值的时候没问题
 *  type:类型
 */
@Document(indexName = "index测试" ,type = "Article")
public class Article {
    //标注它的id
    @Id
    private Integer id;
    private String author;
    private String title;
    private String content;

    public Article() {
    }

    @Override
    public String toString() {
        return id+" "+author+" "+title+" "+content;
    }

    public Article(Integer id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
