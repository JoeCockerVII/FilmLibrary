package ru.otus.hw.controllers;

import org.mapstruct.factory.Mappers;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.FilmDto;
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.models.mappers.AuthorMapper;
import ru.otus.hw.models.mappers.GenreMapper;

import java.util.List;
import java.util.stream.IntStream;

public class TestHelper {

    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    public List<AuthorDto> getDbAuthorsDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id.longValue(), "Author_" + id))
                .toList();
    }

    public List<GenreDto> getDbGenresDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id.longValue(), "Genre_" + id))
                .toList();
    }

    public List<FilmDto> getDbFilms(List<AuthorDto> dbAuthors, List<GenreDto> dbGenres) {
        var initYear = 2019L;
        var initRating = 8.0;
        return IntStream.range(1, 4).boxed()
                .map(id -> new FilmDto(id.longValue(), "FilmTitle_" + id,
                        dbAuthors.get(id - 1), dbGenres.get(id - 1),
                        initYear + (id - 1),
                        initRating + (id - 1)
                        )
                )
                .toList();
    }

    public List<FilmDto> getDbFilms() {
        return getDbFilms(getDbAuthorsDto(), getDbGenresDto());
    }
}
