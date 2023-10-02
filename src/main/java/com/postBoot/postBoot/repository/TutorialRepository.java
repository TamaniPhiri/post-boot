package com.postBoot.postBoot.repository;

import com.postBoot.postBoot.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    List<Tutorial> findByPublished(boolean published);
}
