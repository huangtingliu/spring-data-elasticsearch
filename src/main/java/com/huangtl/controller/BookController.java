package com.huangtl.controller;

import com.huangtl.bean.Book;
import com.huangtl.repository.BookRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/book")
@Controller
public class BookController {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private BookRepository bookRepository;


    @RequestMapping("/add")
    @ResponseBody
    public Object add(Book book){

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败："+e.getMessage();
        }

        return book;
    }

    @RequestMapping("/queryByName")
    @ResponseBody
    public Object queryByName(Book book){

        try {

            List<Book> list = bookRepository.findByName(book.getName());
            System.out.println(list);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public Object queryAll(){

        try {
            Iterable<Book> list = bookRepository.findAll();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    /*
     *  ES复合查询
     *
     * */
    @RequestMapping("/queryByDocId")
    @ResponseBody
    public Object queryByDocId(String id){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("id", id)))
                .build();
        Page<Book> sampleEntities =
                template.queryForPage(searchQuery,Book.class);

        System.out.println(template.putMapping(Book.class));

        return sampleEntities;
    }

    /*
     *  自动分词查询所有字段
     *
     * */
    @RequestMapping("/queryString")
    @ResponseBody
    public Object queryString(String queryString){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(queryString))
                .build();

        List<Book> list =
                template.queryForList(searchQuery,Book.class);

        return list;
    }

    /**
     * 自动分词查询某些字段
     * @param queryString
     * @return
     */
    @RequestMapping("/queryStringByName")
    @ResponseBody
    public Object queryStringByName(String queryString){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(queryString).field("name"))
                .build();

        List<Book> list =
                template.queryForList(searchQuery,Book.class);

        return list;
    }

}
