package com.example.saas.Model;

import jakarta.persistence.*;

@Entity
@Table( name = "entity")
public class YourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String column1;
    private String column2;

    public YourEntity() {

    }
    // Add other columns as needed

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public YourEntity(Long id, String column1, String column2) {
        this.id = id;
        this.column1 = column1;
        this.column2 = column2;
    }
}
