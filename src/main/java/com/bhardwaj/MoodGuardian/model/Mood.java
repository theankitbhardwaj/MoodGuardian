package com.bhardwaj.MoodGuardian.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "moods")
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private EMood feeling;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "notes")
    private String notes;

    public Mood() {
    }

    public Mood(EMood feeling, String notes) {
        this.feeling = feeling;
        this.notes = notes;
    }

    public Mood(EMood feeling, LocalDate date, String notes, Long userId) {
        this.userId = userId;
        this.feeling = feeling;
        this.date = date;
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EMood getFeeling() {
        return feeling;
    }

    public void setFeeling(EMood feeling) {
        this.feeling = feeling;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
}
