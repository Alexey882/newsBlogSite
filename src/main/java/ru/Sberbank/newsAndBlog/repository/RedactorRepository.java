package ru.Sberbank.newsAndBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.Sberbank.newsAndBlog.models.Redactor;

public interface RedactorRepository extends CrudRepository<Redactor , Long> {

@Query(value = "select id from redactor where mail = :mail limit 1" ,
nativeQuery = true)
long findIdByMail(@Param("mail")String mail);
    @Query(value = "select count(*) from redactor",
            nativeQuery = true
    )
    long count();
}
