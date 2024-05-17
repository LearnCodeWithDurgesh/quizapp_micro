package com.service.quiz.repositories;

import com.service.quiz.entities.Quiz;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

    @Query("SELECT q FROM Quiz q WHERE q.quizTitle LIKE %:titleKeyword%")
    List<Quiz> searchQuery(@Param("titleKeyword") String titleKeyword);

}
