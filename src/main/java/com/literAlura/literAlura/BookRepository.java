package com.literAlura.literAlura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookData, Long> {
    long countByLanguage(String language);
}
