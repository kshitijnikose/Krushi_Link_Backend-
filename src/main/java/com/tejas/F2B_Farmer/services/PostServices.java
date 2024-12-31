package com.tejas.F2B_Farmer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.F2B_Farmer.model.Post;
import com.tejas.F2B_Farmer.repositories.PostRepository;

@Service
public class PostServices {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    
    
    

    public Post addPost(Post product) {
        return postRepository.save(product);
    }
    
    
    

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    
    
    

    public String updatePost(Long post_id, Post newproductData) {
        if (postRepository.existsById(post_id)) {
        	
            newproductData.setPostid(post_id);  // Make sure the ID is set
            
            postRepository.save(newproductData); // Save the updated data
            
            return "product updated successfully";
            
        } else {
        	
            return "product not found";
            
        }
    }
    
    
    
    
    public List<Post> findByFarmer(Long farmerid) {
    	
    	
     return 	postRepository.findByFarmer_Farmerid(farmerid);
    	
    	
    }

}























