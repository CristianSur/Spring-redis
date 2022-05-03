package com.redis.springredis.repository;

import com.redis.springredis.entity.Comment;
import com.redis.springredis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {

    public static final String HASH_KEY = "Comment";

    private RedisTemplate template;

    @Autowired
    public CommentDao(RedisTemplate template) {
        this.template = template;
    }

    public Comment save(Comment comment) {
        template.opsForHash().put(HASH_KEY, comment.getId(), comment);
        return comment;
    }

    public List<Comment> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Comment findCommentById(int id) {
        return (Comment) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteComment(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed!";
    }
}
