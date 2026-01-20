package edu.esiea.tp_fil_rouge.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;


    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Lesson(){}

    public Lesson(String title, Theme theme) {
        this.title = title;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
