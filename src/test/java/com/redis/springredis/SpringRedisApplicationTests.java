package com.redis.springredis;

import com.redis.springredis.repository.CommentDao;
import com.redis.springredis.repository.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRedisApplicationTests  {
    private final ProductDao dao;
    private final CommentDao commentDao;


    @Autowired
    SpringRedisApplicationTests(ProductDao dao, CommentDao commentDao) {
        this.dao = dao;
        this.commentDao = commentDao;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void dataShouldBeSavedInRedis() {
        System.out.println(dao.findAll());
    }

}
