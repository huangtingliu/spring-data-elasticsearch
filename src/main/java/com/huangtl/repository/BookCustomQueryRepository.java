package com.huangtl.repository;

import com.huangtl.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/*
* 添加@Query注释自定义ES查询语句，需继承ElasticsearchRepository
*/

public interface BookCustomQueryRepository extends ElasticsearchRepository<Book, Long> {

    @Query("{'bool' : {'must' : {'field' : {'name' : '?0'}}}}")
    Page<Book> findByName(String name,Pageable pageable);
}
