package ru.otus.hw.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;

    private long filmYear;

    private Double rating;

}