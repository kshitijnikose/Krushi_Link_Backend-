package com.tejas.F2B_Farmer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.tejas.F2B_Farmer.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByFarmer_Farmerid(Long farmerid);
	

    
}