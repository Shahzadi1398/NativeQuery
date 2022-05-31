package com.example.nativequery.repo;

import com.example.nativequery.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Query(value = "SELECT * FROM tutorials", nativeQuery = true)
    List<Tutorial> findAll();
    @Query(value = "select * from tutorials t where t.published=?1",nativeQuery = true)
    List<Tutorial> findByPublished(boolean isPublished);
    @Query(value = "SELECT * FROM tutorials t WHERE t.title LIKE %?1%", nativeQuery = true)
    List<Tutorial> findByTitleLike(String title);
    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%',?1,'%')) ORDER BY t.level ASC ",nativeQuery = true)
    List<Tutorial> findByTitleLikeCaseInsensitive(String title);
    @Query(value = "SELECT * FROM tutorials t WHERE t.level >= ?1",nativeQuery = true)
    List<Tutorial> findByLevelGreaterThanEqual (int level);
    @Query(value ="SELECT * FROM tutorials t WHERE t.createdat >=?1",nativeQuery = true)
    List<Tutorial> findByDateGreaterThanEqual(Date date);
    @Query(value = "SELECT * FROM tutorials t ORDER BY t.level DESC", nativeQuery = true)
    List<Tutorial> findAllOrderByLevelDesc();
    @Query(value = "SELECT * FROM tutorials t WHERE t.published=true ORDER BY t.createdat DESC", nativeQuery = true)
    List<Tutorial> findAllPublishedOrderByCreatedDesc();
    @Query(value = "SELECT * FROM tutorials t WHERE t.level BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Tutorial> findByLevelBetween(int start, int end);
    @Query(value = "SELECT * FROM tutorials t WHERE t.createdat BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Tutorial> findByDateBetween(Date start, Date end);
    @Query(value = "SELECT * FROM tutorials t WHERE t.published=:isPublished AND t.level BETWEEN :start AND :end", nativeQuery = true)
    List<Tutorial> findByLevelBetween(@Param("start") int start, @Param("end") int end, @Param("isPublished") boolean isPublished);

}
