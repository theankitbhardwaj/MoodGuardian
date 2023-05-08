package com.bhardwaj.MoodGuardian.controller;

import com.bhardwaj.MoodGuardian.model.EMood;
import com.bhardwaj.MoodGuardian.model.Mood;
import com.bhardwaj.MoodGuardian.payload.response.MessageResponse;
import com.bhardwaj.MoodGuardian.security.services.UserDetailsImpl;
import com.bhardwaj.MoodGuardian.services.MoodService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MoodController {
    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping("/mood")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addMood(@RequestBody Mood moodRequest, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Long userId = userDetails.getId();

        LocalDate date = LocalDate.now();

        if(moodService.isAlreadySubmitted(userId,date)){
            return ResponseEntity.badRequest().body(new MessageResponse("You have already submitted today's mood."));
        }

        EMood moodValue = moodRequest.getFeeling();

        String notes = moodRequest.getNotes();

        Mood mood = new Mood(moodValue, date, notes, userId);

        try {
            Mood savedMood = moodService.saveMood(mood);
            return ResponseEntity.ok(savedMood);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Internal Server Error.");
        }
    }

    @GetMapping("/mood")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllMoods(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        try {
            List<Mood> moodList = moodService.findMoodsByUserId(userId);
            return ResponseEntity.ok(moodList);
        } catch (Exception ex) {
            LoggerFactory.getLogger("MoodController").error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Internal Server Error.");
        }
    }

    @GetMapping("/mood/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getMoodByID(@PathVariable long id,Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        try {
            Optional<Mood> mood = moodService.findMoodByIdAndUserId(id,userId);
            if(mood.isPresent()){
                return ResponseEntity.ok(mood.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex){
            LoggerFactory.getLogger("MoodController").error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Internal Server Error.");
        }
    }

    @PutMapping("/mood/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateMoodById(@PathVariable(name = "id") long moodId, @RequestBody Mood moodRequest, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        try {
            Optional<Mood> currentMood = moodService.findMoodByIdAndUserId(moodId,userId);

            if(currentMood.isPresent()){
                Mood updatedMood = currentMood.get();

                if(moodRequest.getFeeling() != null){
                    updatedMood.setFeeling(moodRequest.getFeeling());
                }

                if(moodRequest.getNotes() != null){
                    updatedMood.setNotes(moodRequest.getNotes());
                }

                try{
                    updatedMood = moodService.saveMood(updatedMood);
                    return ResponseEntity.ok(updatedMood);
                } catch (Exception ex){
                    LoggerFactory.getLogger("MoodController").error(ex.getMessage());
                    return ResponseEntity.internalServerError().body("Internal Server Error.");
                }

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception ex){
            LoggerFactory.getLogger("MoodController").error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Internal Server Error.");
        }
    }

    @DeleteMapping("/mood/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteMoodById(@PathVariable long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        try {
            Optional<Mood> moodToBeDeleted = moodService.findMoodByIdAndUserId(id,userId);

            if(moodToBeDeleted.isPresent()){
                moodService.deleteMood(moodToBeDeleted.get());
                return ResponseEntity.ok(new MessageResponse(String.format("Mood with id: %d deleted successfully.",id)));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex){
            LoggerFactory.getLogger("MoodController").error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Internal Server Error.");
        }
    }
}
