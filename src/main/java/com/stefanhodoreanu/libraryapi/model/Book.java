package com.stefanhodoreanu.libraryapi.model;

import com.stefanhodoreanu.libraryapi.model.Member;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "borrowed_by")
    private Member borrowedBy;
}