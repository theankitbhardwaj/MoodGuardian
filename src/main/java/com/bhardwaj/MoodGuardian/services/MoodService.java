package com.bhardwaj.MoodGuardian.services;

import com.bhardwaj.MoodGuardian.model.Mood;
import com.bhardwaj.MoodGuardian.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MoodService {
    @Autowired
    MoodRepository moodRepository;

    public void saveMood(Mood mood){
        moodRepository.save(mood);
    }

    public List<Mood> getMoodsByUserId(Long userId){
        return moodRepository.getMoodsByUserId(userId);
    }

    public boolean isAlreadySubmitted(Long userId, LocalDate date){
        Mood mood = moodRepository.findByUserIdAndDate(userId, date);
        return mood!=null;
    }

    public Optional<Mood> getMoodByIdAndUserId(Long moodId, Long userId){
        return moodRepository.findByIdAndUserId(moodId,userId);
    }

    public boolean belongsToUserId(Long moodId,Long userId){
        Optional<Mood> mood = moodRepository.findById(moodId);
        return mood.isPresent() && mood.get().getUserId().equals(userId);
    }
}
