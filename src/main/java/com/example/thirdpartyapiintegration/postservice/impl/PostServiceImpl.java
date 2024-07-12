package com.example.thirdpartyapiintegration.postservice.impl;

import com.example.thirdpartyapiintegration.postservice.PostService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    String baseUrl = "https://jsonplaceholder.typicode.com/";

//    StringBuilder builder = new StringBuilder(baseUrl);
//    String POST = "/posts";
//    String POSTBYID = "/posts/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Map<String, Object>> getPosts() {

        HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
//        String url = builder.append(POST).toString();
        String url = baseUrl + "posts";
        // with restTemplate.exchange method we are going to call third party api
        // restTemplate(String url, HttpMethod method, HttpEntity requestEntity(header and payload),responsType)
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(int id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
//        String url = builder.append(POSTBYID).append(id).toString();
        String url = baseUrl + "posts/" + id;
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> insertPosts(Map<String, Object> payload) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, gethttpHeaders());
//        String url = builder.append(POST).toString();
        String url = baseUrl + "posts";
        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> updatePosts(Map<String, Object> payload, int id) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, gethttpHeaders());
//        String url = builder.append(POSTBYID).append(id).toString();
        String url = baseUrl + "posts/" + id;

        val response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> deletePost(int id) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(gethttpHeaders());
//        String url = builder.append(POSTBYID).append(id).toString();
        String url = baseUrl + "posts/" + id;
        val response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
        return response.getBody();
    }

    private HttpHeaders gethttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
