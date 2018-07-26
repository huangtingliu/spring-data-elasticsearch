package com.huangtl.repository;

import com.huangtl.bean.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
* 接口方法命名有关键字可以被spring 识别，spring会自动生成es的语法
* 需继承org.springframework.data.repository.Repository,（还有一种可在方法上添加@Query注释自定义ES查询语句，需继承ElasticsearchRepository）
* 关键字查看https://docs.spring.io/spring-data/elasticsearch/docs/3.0.8.RELEASE/reference/html/#elasticsearch.query-methods.criterions
* 如：
* 方法名            |   关键字    |   对应es查询语句
* findByNameAndPrice    And         {"bool" : {"must" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}
* findByNameOrPrice     Or          {"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}
* findByName            Is          {"bool" : {"must" : {"field" : {"name" : "?"}}}}
* findByNameNot         Not         {"bool" : {"must_not" : {"field" : {"name" : "?"}}}}
* findByPriceBetween    Between     {"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
* findByPriceLessThan   LessThanEqual   {"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
* findByPriceGreaterThan    GreaterThanEqual    {"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}
* findByPriceBefore     Before      {"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}
* findByPriceAfter      After       {"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}
* findByNameLike        Like        {"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}
* findByNameStartingWith    StartingWith    {"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}
* findByNameEndingWith  EndingWith  {"bool" : {"must" : {"field" : {"name" : {"query" : "*?","analyze_wildcard" : true}}}}}
* findByNameContaining Contains/Containing  {"bool" : {"must" : {"field" : {"name" : {"query" : "?","analyze_wildcard" : true}}}}}
* findByNameIn(Collection<String>names) In  {"bool" : {"must" : {"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"name" : "?"}} ]}}}}
* findByNameNotIn(Collection<String>names) NotIn    {"bool" : {"must_not" : {"bool" : {"should" : {"field" : {"name" : "?"}}}}}}
* findByStoreNear       Near        Not Supported Yet !
* findByAvailableTrue   True        {"bool" : {"must" : {"field" : {"available" : true}}}}
* findByAvailableFalse  False       {"bool" : {"must" : {"field" : {"available" : false}}}}
* findByAvailableTrueOrderByNameDesc    OrderBy {"sort" : [{ "name" : {"order" : "desc"} }],"bool" : {"must" : {"field" : {"available" : true}}}}
*
* 这些关键字应该可以组合使用类似 findByAvailableTrueOrderByNameDesc查询available为true的并且根据name降序
*/

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findById(String id);

    List<Book> findByName(String name);

    List<Book> findByNameAndPrice(String name,double price);

    List<Book> findByPriceBetween(double priceMin,double priceMax);
}
