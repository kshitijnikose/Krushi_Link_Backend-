package com.tejas.F2B_Farmer.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postid;  // Unique identifier for the post

    private String name;
    private String description;
    private Double price;

    // Image logic (if necessary)
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "postimages",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "imageid"))
    private Set<ImageModel> postImages;  // Set of images associated with the post

    // Many-to-one relationship with Farmer
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmerid")
    @JsonBackReference(value = "farmerpost")  // Prevents infinite recursion during serialization
    private Farmer farmer;  // Each post is linked to a farmer
}














