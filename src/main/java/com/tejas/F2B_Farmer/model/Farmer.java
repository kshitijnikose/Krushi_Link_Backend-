package com.tejas.F2B_Farmer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long farmerid;  // Unique identifier for the farmer

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private Long phone;

    private String address;
    private String password;

    // Bi-directional relationship with Post
    @OneToMany(mappedBy = "farmer", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "farmerpost")  // Ensures proper serialization
    private List<Post> posts;  // A farmer can have many posts

    // Bi-directional relationship with ContactUs
    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "farmercontact")  // Ensures proper serialization
    private List<ContactUs> queries;  // A farmer can have many queries
}














