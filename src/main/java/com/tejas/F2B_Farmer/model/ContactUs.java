package com.tejas.F2B_Farmer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

//    private String name;
//    private String email;
    private String message;

    @ManyToOne
    @JoinColumn(name = "farmerid")
    @JsonBackReference(value = "farmercontact")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "buyerid")
    @JsonBackReference
    private Buyer buyer; 
}

