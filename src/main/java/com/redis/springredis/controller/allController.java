package com.redis.springredis.controller;

import com.redis.springredis.SpringRedisApplication;
import com.redis.springredis.entity.Comment;
import com.redis.springredis.entity.Product;
import com.redis.springredis.repository.CommentDao;
import com.redis.springredis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class allController {

        private final ProductDao dao;
        private final CommentDao commentDao;

        @Autowired
        public allController(ProductDao dao, CommentDao commentDao) {
            this.dao = dao;
            this.commentDao = commentDao;
        }

        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public String save(@ModelAttribute Product product, Model model) {
            System.out.println("Product from UI = " + product);
            dao.save(product);
            model.addAttribute("product", product);
            return "product_info";
        }

        @GetMapping("/all")
        public String getAllProducts(Model model) {
            model.addAttribute("products", dao.findAll());
            return "all_products";
        }

        @GetMapping("/all/{id}")
        public String findProduct(@PathVariable int id, Model model) {
            model.addAttribute("product", dao.findProductById(id));
            return "product";
        }

        @DeleteMapping("/delete/{id}")
        public String remove(@PathVariable int id) {
            dao.deleteProduct(id);
            return "redirect:/all";
        }

        @GetMapping()
        public String home() {
            return "index";
        }

        @PostMapping("/blog/save")
        public String saveComment(@ModelAttribute Comment comment, Model model) {
            commentDao.save(comment);
            model.addAttribute("comment", comment);
            return "redirect:/blog";
        }

        @GetMapping("/blog")
        public String blog(Model model) {
            model.addAttribute("comments", commentDao.findAll());
            return "blog";
        }

        @DeleteMapping("/delete/comment/{id}")
        public String removeComment(@PathVariable int id) {
            commentDao.deleteComment(id);
            return "redirect:/blog";
        }

        @GetMapping("/comment/{id}")
        public String findComment(@PathVariable int id, Model model) {
            model.addAttribute("comment", commentDao.findCommentById(id));
            return "comment_info";
        }
}

