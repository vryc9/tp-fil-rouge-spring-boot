package edu.esiea.tp_fil_rouge.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float coef;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Lesson> lessons;


    private String name;


    public Theme() {
    }

    public Theme(float coef, String name) {
        this.coef = coef;
        this.name = name;
    }

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

