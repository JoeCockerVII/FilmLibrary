package ru.otus.hw.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.models.dto.AuthorDto;

import java.util.List;

@FeignClient(name = "film-server", contextId = "films-authors")
public interface AuthorServiceProxy {

    @CircuitBreaker(name = "film-server", fallbackMethod = "getDefaultAuthors")
    @GetMapping("/authors")
    List<AuthorDto> findAll();


    default List<AuthorDto> getDefaultAuthors(Throwable throwable) {
        return List.of(
                new AuthorDto(101L,"Author_101"),
                new AuthorDto(102L,"Author_102"),
                new AuthorDto(103L,"Author_103")
        );
    }
}