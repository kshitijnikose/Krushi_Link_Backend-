package com.tejas.F2B_Farmer.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyerData")
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buyerid;

    private String firstName;

    private String lastName;    
    @Column(unique = true)
    private String email;
    
    private Long phone;

    private String address;
    
  
    private String password;
    
    
    @OneToMany(mappedBy="buyer",cascade = CascadeType.ALL)
    private List<ContactUs> queries;


    
}













