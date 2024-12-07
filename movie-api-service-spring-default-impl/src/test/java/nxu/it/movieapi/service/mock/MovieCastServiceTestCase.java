package nxu.it.movieapi.service.mock;


import nxu.it.api.model.Cast;
import nxu.it.api.model.CastRole;
import nxu.it.api.model.Filmography;
import nxu.it.api.service.MovieCastService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieCastServiceTestCase extends BaseTestCase{
    @Autowired
    MovieCastService movieCastService;


    @Test
    @DisplayName("测试查询指定编号的电影的导演信息")
    void test_find_directors_by_movie_id() {
        List<Cast> directors = movieCastService.findDirectorsByMovieId(1292728);
        assertThat(directors)
                .as("编号为%d的电影(勇闯夺命岛)的导演有1个", 1292728)
                .hasSize(1);
        Cast director = directors.get(0);
        assertThat(director)
                .as("编号为%d的电影(勇闯夺命岛)的导演英文名为:Lin Tao", 1292728)
                .matches(d -> d.getEnglishName().equals("Lin Tao"))
                .as("编号为%d的电影(勇闯夺命岛)的导演编号为%d", 1292728, 1)
                .matches(d -> d.getId() == 1);
    }

    @Test
    @DisplayName("测试查询指定编号的电影的演员信息")
    void test_find_actors_by_movie_id() {
        Integer movieId = 26985127;
        List<Cast> actors = movieCastService.findActorsByMovieId(movieId);
        assertThat(actors)
                .as("编号为%d的电影(一出好戏)的演员有2个", movieId)
                .hasSize(2);
        List<String> actorNameList = actors.stream().map(Cast::getChineseName).collect(Collectors.toList());
        assertThat(actorNameList).as("编号%d的电影(一出好戏)的演员包括:李雷,韩梅梅", movieId)
                .containsAll(Arrays.asList("李雷", "韩梅梅"));

    }

    @Test
    @DisplayName("测试查询指定编号的电影的影人信息")
    public void test_find_cast_by_movie_id() {
        Integer movieId = 1297747;
        List<Cast> castList = movieCastService.findCastByMovieId(movieId);
        assertThat(castList)
                .as("编号为%d的电影(一出好戏)的演员有3个", movieId)
                .hasSize(3);
        List<String> castNameList = castList.stream().map(Cast::getChineseName).collect(Collectors.toList());
        assertThat(castNameList).as("编号%d的电影(少林足球)的影人包括:李雷,韩梅梅,林涛", movieId)
                .containsAll(Arrays.asList("李雷", "韩梅梅", "林涛"));

    }

    @Test
    @DisplayName("测试根据电影编号电影和演职员类型查询影人信息")
    public void test_find_cast_by_movie_id_and_role() {
        Integer movieId1 = 1306123;
        List<Cast> castList1 = movieCastService.findCastByMovieIdAndRole(movieId1, CastRole.DIRECTOR);
        assertThat(castList1)
                .as("编号%d的电影(英雄)的导演有1个", movieId1)
                .hasSize(1);
        List<String> castNameList1 = castList1.stream().map(Cast::getChineseName).collect(Collectors.toList());
        assertThat(castNameList1)
                .as("编号%d的电影(英雄)的影人包括:林涛", movieId1)
                .contains("林涛");

        Integer movieId2 = 3541415;
        List<Cast> castList2 = movieCastService.findCastByMovieIdAndRole(movieId2, CastRole.ACTOR);
        assertThat(castList2)
                .as("编号%d的电影(盗梦空间)的演员有2个", movieId2)
                .hasSize(2);

        List<String> castNameList2 = castList2.stream().map(Cast::getEnglishName).collect(Collectors.toList());
        assertThat(castNameList2)
                .as("编号%d的电影(盗梦空间)的演员有:Li Lei,Han Meimei", movieId2)
                .containsAll(Arrays.asList("Li Lei", "Han Meimei"));


    }

    @Test
    @DisplayName("测试查询编号的影人的电影作品信息")
    void test_find_movies_by_staff_id() {
        Integer staffId = 1021999;
        List<Filmography> filmographyList = movieCastService.findMoviesByStaffId(staffId);
        assertThat(filmographyList)
                .as("编号为%d的影人(姜文)有3部作品", staffId)
                .hasSize(3);
        List<String> originalTitleList = filmographyList.stream().map(Filmography::getOriginalTitle).collect(Collectors.toList());
        assertThat(originalTitleList)
                .containsAll(Arrays.asList("从前有座山", "Demo Movie B", "馬老師の冒険"));
    }

    @Test
    @DisplayName("测试查询编号的影人的参演电影作品信息")
    void test_find_acted_movies_by_staff_id() {
        Integer staffId = 1274388;
        List<Filmography> filmographyList = movieCastService.findActedMoviesByStaffId(1274388);
        assertThat(filmographyList)
                .as("编号为%d的影人(王宝强)参演了2部作品", staffId)
                .hasSize(2);

        List<String> originalTitleList = filmographyList.stream().map(Filmography::getOriginalTitle).collect(Collectors.toList());
        assertThat(originalTitleList)
                .as("编号为%d的影人(王宝强)参演作品包括：从前有座山,馬老師の冒険", staffId)
                .containsAll(Arrays.asList("从前有座山", "馬老師の冒険"));
    }

    @Test
    @DisplayName("测试查询编号的影人的执导电影作品信息")
    void test_find_directed_movies_by_staff_id() {
        Integer staffId = 1054440;
        List<Filmography> filmographyList = movieCastService.findDirectedMoviesByStaffId(staffId);
        assertThat(filmographyList)
                .as("编号为%d的影人(史蒂文·斯皮尔伯格)导演了2部作品", staffId)
                .hasSize(2);

        List<String> originalTitleList = filmographyList.stream().map(Filmography::getOriginalTitle).collect(Collectors.toList());
        assertThat(originalTitleList)
                .as("编号为%d的影人(史蒂文·斯皮尔伯格)导演作品包括:从前有座山,Demo Movie B", staffId)
                .containsAll(Arrays.asList("从前有座山", "Demo Movie B"));
    }

}

