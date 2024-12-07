package nxu.it.movieapi.service.mock;


import nxu.it.api.model.Genre;
import nxu.it.api.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreServiceTestCase extends BaseTestCase{
    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("测试查询全部电影分类")
    void test_find_all_genres() {
        List<Genre> allGenreList = genreService.findAll();
        assertThat(allGenreList)
                .as("全部电影分类共有4个")
                .hasSize(4);
    }

    @Test
    @DisplayName("测试查询指定名称的电影分类")
    void test_find_genres_by_name() {
        List<Genre> genreList = genreService.findByName("测试");
        assertThat(genreList)
                .as("包含测试的电影分类共有2个")
                .hasSize(2);
    }

    @Test
    @DisplayName("测试根据查询指定编号的电影的分类信息")
    void test_find_genres_by_movie_id() {
        Integer movieId = 1291543;
        List<Genre> genreList = genreService.findByMovieId(movieId);
        assertThat(genreList)
                .as("编号为%d的电影(1291543)的分类有2个", movieId)
                .hasSize(2);
        List<Integer> genreIdList = genreList.stream().map(Genre::getId).collect(Collectors.toList());
        assertThat(genreIdList)
                .as("编号为%d的电影(1291543)的分类编号包括:1,3", movieId)
                .containsAll(Arrays.asList(1, 3));

    }
}

