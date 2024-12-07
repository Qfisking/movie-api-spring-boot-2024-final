package nxu.it.movieapi.service.mock;

import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Movie;
import nxu.it.api.model.SimpleMovie;
import nxu.it.api.service.MovieService;
import nxu.it.api.service.param.MoviePageQueryParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class MovieServiceTestCase extends BaseTestCase{
    @Autowired
    MovieService movieService;

    @Test
    @DisplayName("测试查询指定编号的电影信息")
    void test_find_by_movie_id() {
        Integer movieId = 1291543;
        Optional<Movie> movieOptional = movieService.findById(movieId);
        assertThat(movieOptional)
                .as("编号为%d的电影存在", movieId)
                .isPresent();

        Movie movie = movieOptional.get();
        assertThat(movie)
                .as("编号为%d的电影的片名为:怀远爱情故事", movieId)
                .matches(m -> m.getOriginalTitle().equals("怀远爱情故事"))
                .as("编号为%d的电影的语言为:汉语普通话", movieId)
                .matches(m -> m.getLanguage().equals("汉语普通话"))
                .as("编号为%d的电影的上映年份为:2022", movieId)
                .matches(m -> m.getYear() == 2022);
    }

    @Test
    @DisplayName("测试分页查询电影信息")
    void test_movie_page_query() {
        MoviePageQueryParam pageQueryParamOne = new MoviePageQueryParam();
        pageQueryParamOne.setOriginalTitle("harry");
        pageQueryParamOne.setPageSize(5);
        Pageable<SimpleMovie> moviePageOne = movieService.page(pageQueryParamOne);
        assertThat(moviePageOne)
                .as("片名中包含harry的电影分页查询,每页1条记录,共有1页")
                .matches(page -> page.getTotalPage() == 1)
                .as("片名中包含harry的电影分页查询,每页5条记录,共有2条记录")
                .matches(page -> page.getTotalRow() == 2);

        List<SimpleMovie> movieListOne = moviePageOne.getList();
        assertThat(movieListOne)
                .as("片名中包含harry的电影分页查询,每页有5条记录")
                .hasSize(2);
        SimpleMovie movieOne = movieListOne.get(0);
        String originalTitle = movieOne.getOriginalTitle();
        assertThat(originalTitle)
                .as("片名中包含harry的电影分页查询,第1条记录中电影片名应包含:怀远爱情故事")
                .containsIgnoringCase("怀远爱情故事");

        MoviePageQueryParam pageQueryParamTwo = new MoviePageQueryParam();
        pageQueryParamTwo.setChineseTitle("天");
        pageQueryParamTwo.setPageSize(5);
        pageQueryParamTwo.setLanguage("普通话");
        pageQueryParamTwo.setRegion("大陆");
        pageQueryParamTwo.setPageNumber(1);
        pageQueryParamTwo.setStartYear(2010);
        pageQueryParamTwo.setOrderField("year");
        pageQueryParamTwo.setGenreIds(List.of(1, 4));
        pageQueryParamTwo.setOrderDirection("ASC");
        Pageable<SimpleMovie> moviePageTwo = movieService.page(pageQueryParamTwo);

        assertThat(moviePageTwo)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第1页,共有1页")
                .matches(page -> page.getTotalPage() == 1)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第1页,共有2条记录")
                .matches(page -> page.getTotalRow() == 2);

        List<SimpleMovie> movieListTwo = moviePageTwo.getList();
        assertThat(movieListTwo)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第1页中有2条记录")
                .hasSize(2);
        List<String> chineseTitleList = movieListTwo.stream().map(SimpleMovie::getChineseTitle)
                .collect(Collectors.toList());
        assertThat(chineseTitleList)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第2页中电影包括：怀远爱情故事,文萃娇佳人")
                .containsAll(Arrays.asList("怀远爱情故事", "文萃娇佳人"));

    }

    @Test
    @DisplayName("测试评分最高的电影信息")
    void test_top_rated_movies() {
        List<SimpleMovie> topRatedMovies = movieService.topRatedMovies();
        assertThat(topRatedMovies)
                .as("评分最高的电影应有2条记录")
                .hasSize(2);

    }

    @Test
    @DisplayName("测试指定编号的电影的相关电影信息")
    void test_related_movies_by_movie_id() {
        List<SimpleMovie> relatedMovies = movieService.relatedMovies(1291543);
        assertThat(relatedMovies)
                .as("与编号为%d的电影(功夫)相关的电影应有2条记录", 1291543)
                .hasSize(2);
    }
}

