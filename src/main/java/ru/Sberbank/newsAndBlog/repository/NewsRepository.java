package ru.Sberbank.newsAndBlog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.Sberbank.newsAndBlog.models.News;

import java.util.Collection;

public interface NewsRepository extends CrudRepository<News,Long> {
    @Query(value = "select * from news order by rating desc limit 5",
            nativeQuery = true
    )
    Collection<News> findBestNews();

    @Query(value = "select * from news where id = :id  ",
            nativeQuery = true
    )
    News findByID(@Param("id") long id);

    @Query(value = "select * from news where redactor_id = :redactor_id",
            nativeQuery = true
    )
    Collection<News> findNewsByIdRedactor(@Param("redactor_id") long redactor_id);

    @Query(value = "select * from news where category = :category  ",
            nativeQuery = true
    )
    Collection<News> findAllByCategory(@Param("category") String category);


    @Query(value = "select category from news",
            nativeQuery = true
    )
    Collection<String> findAllCategory();

}
