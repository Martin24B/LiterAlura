package com.literAlura.literAlura;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.literAlura.model.Author;

import jakarta.persistence.Column;

@Entity  
public class AuthorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
 
    private int birthYear;
    private Integer deathYear;
    
    public AuthorData() {}

    public AuthorData(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }
    
    public static Author fromAuthorData(AuthorData authorData) {
        return new Author(authorData.getName(), authorData.getBirthYear(), authorData.getDeathYear());
    } 
}
