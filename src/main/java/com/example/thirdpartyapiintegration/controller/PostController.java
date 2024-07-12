package com.example.thirdpartyapiintegration.controller;

import com.example.thirdpartyapiintegration.postservice.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/getPost")
    public List<Map<String, Object>> getAllPosts() {
        return postService.getPosts();
    }

    @GetMapping("/getPostById/{id}")
    public Map<String, Object> getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PostMapping("/insertPosts")
    public Map<String, Object> insertPosts(@RequestBody Map<String, Object> payload) {
        return postService.insertPosts(payload);
    }

    @PutMapping("/updatePosts/{id}")
    public Map<String, Object> updatePosts(@RequestBody Map<String, Object> payload, @PathVariable int id) {
        return postService.updatePosts(payload, id);
    }

    @DeleteMapping("/deletePost/{id}")
    public Map<String, Object> deletePost(@PathVariable int id) {
        return postService.deletePost(id);
    }
}
