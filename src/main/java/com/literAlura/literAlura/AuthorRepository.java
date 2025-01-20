package com.literAlura.literAlura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorData, Long> {

    List<AuthorData> findByBirthYearBeforeAndDeathYearAfterOrDeathYearNull(int year, int year2);
}
