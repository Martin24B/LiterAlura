package com.literAlura.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	com.literAlura.Data.Author save(com.literAlura.Data.Author author);
    
}
