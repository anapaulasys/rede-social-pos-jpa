package com.posjava.redesocial.repository;

import com.posjava.redesocial.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryJpaRepository extends JpaRepository<Story, Long> {
}
