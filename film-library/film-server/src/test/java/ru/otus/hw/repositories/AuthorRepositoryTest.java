package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw.models.Author;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Author repository test (JPA)")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager entityManager;

    private List<Author> dbAuthors;

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
    }

    @DisplayName("Get all authors")
    @Test
    void shouldGetAllAuthors() {
        var actualAuthors = authorRepository.findAll();
        var expectedAuthors = dbAuthors;
        assertThat(actualAuthors)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthors);
    }

    @DisplayName("Authors by id")
    @ParameterizedTest
    @MethodSource("getDbAuthors")
    void shouldGetAuthorById(Author expectedAuthor) {
        var actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

    private static List<Author> getDbAuthors() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Author(id.longValue(), "Author_" + id))
                .toList();
    }
}