package com.literAlura.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	void save(com.literAlura.Data.Book book);
   
}
