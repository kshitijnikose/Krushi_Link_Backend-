package com.tejas.F2B_Farmer.controller;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tejas.F2B_Farmer.model.ImageModel;
import com.tejas.F2B_Farmer.model.Post;
import com.tejas.F2B_Farmer.services.PostServices;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostServices postService;
    

    @GetMapping()
    public List<Post> getAllPosts() {
        return postService.getAllPosts() ; 
    }    
    
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
    	
        return postService.getPostById(id).get();
    }   


    
   


    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        postService.deletePost(id);
        return true;
    }
    
    
    
    
    

    
    
    @PutMapping("/{id}")
    public boolean updateproduct(@PathVariable Long id, @RequestBody Post updatedProduct) {
    	
    			postService.updatePost(id, updatedProduct);
      			
    return true;
       
    
}
    
    @GetMapping("farmer/{farmerid}")
    public List<Post> findByFarmer(@PathVariable Long farmerid) {
    	
    	
    	
    	return postService.findByFarmer(farmerid);
    	
    	
    	
    }
    
    
    
    
    
    
    
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public boolean addPost(
            @RequestPart("post") String postJson,
            @RequestPart("imageFile") MultipartFile[] files) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Post post = objectMapper.readValue(postJson, Post.class);

            Set<ImageModel> images = uploadImage(files);
            post.setPostImages(images);

            postService.addPost(post);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
    

    
    
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }



    
}













