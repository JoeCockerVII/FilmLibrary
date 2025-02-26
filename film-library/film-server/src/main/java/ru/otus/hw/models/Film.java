package ru.otus.hw.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedAttributeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films")
@NamedEntityGraph(name = "author-genre-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "filmYear", nullable = false)
    private long filmYear;

    @Column(name = "rating", nullable = false)
    private Double rating;


}
