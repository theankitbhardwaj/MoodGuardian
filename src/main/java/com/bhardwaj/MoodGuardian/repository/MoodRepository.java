package com.bhardwaj.MoodGuardian.repository;

import com.bhardwaj.MoodGuardian.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    List<Mood> getMoodsByUserId(Long userId);

    Mood findByUserIdAndDate(Long userId, LocalDate date);

    Optional<Mood> findByIdAndUserId(Long moodId, Long userId);
}
