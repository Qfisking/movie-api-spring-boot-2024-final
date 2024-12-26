package nxu.it.movieapi.service.pg;

import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Cast;
import nxu.it.api.model.Genre;
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


public class MovieServiceTestCase  extends BaseTestCase {
    @Autowired
    MovieService movieService;

    @Test
    @DisplayName("测试查询指定编号的电影信息")
    void test_find_by_movie_id() {
        Integer movieId = 99998888;
        Optional<Movie> movieOptional = movieService.findById(movieId);
        assertThat(movieOptional)
                .as("编号为%d的电影不存在", movieId)
                .isEmpty();


        movieId = 1291543;
        movieOptional = movieService.findById(movieId);
        assertThat(movieOptional)
                .as("编号为%d的电影存在", movieId)
                .isPresent();

        Movie movie = movieOptional.get();
        assertThat(movie)
                .as("编号为%d的电影的片名为:功夫", movieId)
                .matches(m -> m.getOriginalTitle().equals("功夫"))
                .as("编号为%d的电影的语言为:粤语", movieId)
                .matches(m -> m.getLanguage().equals("粤语"))
                .as("编号为%d的电影的上映年份为:2004", movieId)
                .matches(m -> m.getYear() == 2004);
        List<Genre> genreList = movie.getGenres();
        assertThat(genreList)
                .as("编号为%d的电影,分类包括:\"喜剧\",\"动作\",\"犯罪\"", movieId)
                .extracting(Genre::getName)
                .containsExactlyInAnyOrder("喜剧", "动作", "犯罪");

        List<Cast> actors = movie.getActors();
        assertThat(actors)
                .as("编号为%d的电影演员有9名", movieId)
                .hasSize(9)
                .as("编号为%d的电影演员应包含:\"林雪\", \"林子聪\", \"陈国坤\", \"梁小龙\", \"黄圣依\", \"元华\", \"元秋\", \"田启文\", \"周星驰\"", movieId)
                .extracting(Cast::getChineseName)
                .containsExactlyInAnyOrder("林雪", "林子聪", "陈国坤", "梁小龙", "黄圣依", "元华", "元秋", "田启文", "周星驰");

        List<Cast> directors = movie.getDirectors();
        assertThat(directors)
                .as("编号为%d的电影导演有1名", movieId)
                .hasSize(1)
                .as("编号为%d的电影演员导演应包含:\"周星驰\"", movieId)
                .extracting(Cast::getChineseName)
                .containsExactlyInAnyOrder("周星驰");


        movieId = 1291843;
        movieOptional = movieService.findById(movieId);
        assertThat(movieOptional)
                .as("编号为%d的电影存在", movieId)
                .isPresent();

        movie = movieOptional.get();
        assertThat(movie)
                .as("编号为%d的电影的片名为:The Matrix", movieId)
                .matches(m -> m.getOriginalTitle().equals("The Matrix"))
                .as("编号为%d的电影的语言为:英语", movieId)
                .matches(m -> m.getLanguage().equals("英语"))
                .as("编号为%d的电影的上映年份为:1999", movieId)
                .matches(m -> m.getYear() == 1999);
        genreList = movie.getGenres();
        assertThat(genreList)
                .as("编号为%d的电影,分类包括:\"动作\",\"科幻\"", movieId)
                .extracting(Genre::getName)
                .containsExactlyInAnyOrder("动作", "科幻");
        actors = movie.getActors();
        assertThat(actors)
                .as("编号为%d的电影演员有8名", movieId)
                .hasSize(8)
                .as("编号为%d的电影演员应包含:\"雨果·维文\",\"朱利安·阿拉汗加\",\"马库斯·钟\",\"乔·潘托里亚诺\",\"格洛丽亚·福斯特\",\"劳伦斯·菲什伯恩\",\"凯瑞-安·莫斯\",\"基努·里维斯\"", movieId)
                .extracting(Cast::getChineseName)
                .containsExactlyInAnyOrder("雨果·维文", "朱利安·阿拉汗加", "马库斯·钟", "乔·潘托里亚诺", "格洛丽亚·福斯特", "劳伦斯·菲什伯恩", "凯瑞-安·莫斯", "基努·里维斯");

        directors = movie.getDirectors();
        assertThat(directors)
                .as("编号为%d的电影导演有2名", movieId)
                .hasSize(2)
                .as("编号为%d的电影演员导演应包含:\"莉莉·沃卓斯基\",\"拉娜·沃卓斯基\"", movieId)
                .extracting(Cast::getChineseName)
                .containsExactlyInAnyOrder("莉莉·沃卓斯基", "拉娜·沃卓斯基");


    }

    @Test
    @DisplayName("测试分页查询电影信息")
    void test_movie_page_query() {
        MoviePageQueryParam pageQueryParamOne = new MoviePageQueryParam();
        pageQueryParamOne.setOriginalTitle("harry");
        pageQueryParamOne.setPageSize(5);
        Pageable<SimpleMovie> moviePageOne = movieService.page(pageQueryParamOne);
        assertThat(moviePageOne)
                .as("片名中包含harry的电影分页查询,每页5条记录,共有2页")
                .matches(page -> page.getTotalPage() == 2)
                .as("片名中包含harry的电影分页查询,每页5条记录,共有9条记录")
                .matches(page -> page.getTotalRow() == 9);

        List<SimpleMovie> movieListOne = moviePageOne.getList();
        assertThat(movieListOne)
                .as("片名中包含harry的电影分页查询,每页有5条记录")
                .hasSize(5);
        SimpleMovie movieOne = movieListOne.get(0);
        String originalTitle = movieOne.getOriginalTitle();
        assertThat(originalTitle)
                .as("片名中包含harry的电影分页查询,第1条记录中电影片名应包含harry")
                .containsIgnoringCase("harry");

        MoviePageQueryParam pageQueryParamTwo = new MoviePageQueryParam();
        pageQueryParamTwo.setChineseTitle("天");
        pageQueryParamTwo.setPageSize(5);
        pageQueryParamTwo.setLanguage("普通话");
        pageQueryParamTwo.setRegion("大陆");
        pageQueryParamTwo.setPageNumber(2);
        pageQueryParamTwo.setStartYear(2010);
        pageQueryParamTwo.setOrderField("year");
        pageQueryParamTwo.setGenreIds(List.of(1, 4));
        pageQueryParamTwo.setOrderDirection("ASC");
        Pageable<SimpleMovie> moviePageTwo = movieService.page(pageQueryParamTwo);

        assertThat(moviePageTwo)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第2页,共有2页")
                .matches(page -> page.getTotalPage() == 2)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第2页,共有8条记录")
                .matches(page -> page.getTotalRow() == 8);

        List<SimpleMovie> movieListTwo = moviePageTwo.getList();
        assertThat(movieListTwo)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第2页中有3条记录")
                .hasSize(3);
        List<String> chineseTitleList = movieListTwo.stream().map(SimpleMovie::getChineseTitle)
                .collect(Collectors.toList());
        assertThat(chineseTitleList)
                .as("中文片名中包含天、普通话、2010及之后大陆上映电影分页查询,电影分类编号包括:1,4,按年份升序,每页5条记录,第2页中电影包括：过春天,地久天长,空天猎")
                .containsAll(Arrays.asList("过春天", "地久天长", "空天猎"));

    }

    //  @Test
    @DisplayName("测试评分最高的10条电影信息")
    void test_top_rated_movies() {
        List<SimpleMovie> topRatedMovies = movieService.topRatedMovies();
        assertThat(topRatedMovies)
                .as("评分最高的电影应有10条记录")
                .hasSize(10);

    }

    //@Test
    @DisplayName("测试指定编号的电影的相关电影信息")
    void test_related_movies_by_movie_id() {
        List<SimpleMovie> relatedMovies = movieService.relatedMovies(1291543);
        assertThat(relatedMovies)
                .as("与编号为%d的电影(功夫)相关的电影应有10条记录", 1291543)
                .hasSize(10);
    }
}
