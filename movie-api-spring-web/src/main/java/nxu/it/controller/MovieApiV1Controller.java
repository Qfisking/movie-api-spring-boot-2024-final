package nxu.it.controller;


import nxu.it.api.common.result.ApiResult;
import nxu.it.api.model.Author;
import nxu.it.api.model.Genre;
import nxu.it.api.service.GenreService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie/api/v1")
@CrossOrigin("*")
public class MovieApiV1Controller {
    private final GenreService genreService;

    public MovieApiV1Controller(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("/authors")
    public ApiResult<List<Author>> author(){
        var authors = List.of(new Author("001","Jack",2022,"CS"),
                new Author("002","Jane",2022,"CS"),
                new Author("003","Jack",2022,"CS"));
        return ApiResult.success(authors);
    }

    @GetMapping("/")
    public ApiResult<List<Genre>> index(){
        List<Genre> genres = genreService.findAll();
        return ApiResult.success(genres);
    }
}
