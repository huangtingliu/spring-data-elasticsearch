package com.huangtl.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 *  '@Document' 注释代表加入索引,默认所有的属性都会被建立索引、并且分词。
 *  '@Field' 通过@Field注解来进行详细的指定，如果没有特殊需求，那么只需要添加@Document即可
 *  必须要有个@id注解字段
 */
@Document(indexName = "book_index",type = "book",shards = 2)
public class Book implements Serializable{

    @Id
    private long id;

    private String name;
    private double price;
    private String type;
    private String author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
