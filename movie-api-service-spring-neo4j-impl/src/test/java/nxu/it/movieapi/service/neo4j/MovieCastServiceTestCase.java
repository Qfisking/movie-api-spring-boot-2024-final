
package nxu.it.movieapi.service.neo4j;

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

public class MovieCastServiceTestCase extends BaseTestCase {
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
                .as("编号为%d的电影(勇闯夺命岛)的导演英文名为:Michael Bay", 1292728)
                .matches(d -> d.getEnglishName().equals("Michael Bay"))
                .as("编号为%d的电影(勇闯夺命岛)的导演编号为%d", 1292728, 1027776)
                .matches(d -> d.getId() == 1027776);
    }

    @Test
    @DisplayName("测试查询指定编号的电影的演员信息")
    void test_find_actors_by_movie_id() {
        Integer movieId = 26985127;
        List<Cast> actors = movieCastService.findActorsByMovieId(movieId);
        assertThat(actors)
                .as("编号为%d的电影(一出好戏)的演员有9个", movieId)
                .hasSize(9);
        List<String> actorNameList = actors.stream().map(Cast::getChineseName).collect(Collectors.toList());
        assertThat(actorNameList).as("编号%d的电影(一出好戏)的演员包括:张艺兴,黄渤,王宝强,于和伟,舒淇", movieId)
                .containsAll(Arrays.asList("张艺兴", "黄渤", "王宝强", "于和伟", "舒淇"));

    }

    @Test
    @DisplayName("测试查询指定编号的电影的影人信息")
    public void test_find_cast_by_movie_id() {
        Integer movieId = 1297747;
        List<Cast> castList = movieCastService.findCastByMovieId(movieId);
        assertThat(castList)
                .as("编号为%d的电影(少林足球)的影人有9个", movieId)
                .hasSize(10)
                .as("编号为%d的电影(少林足球)的影人包括:\"赵薇\",\"莫文蔚\",\"林子聪\",\"吴孟达\",\"周星驰\",\"谢贤\",\"田启文\",\"黄一飞\",\"张柏芝\",\"周星驰\"", movieId)
                .extracting(Cast::getChineseName)
                .containsExactlyInAnyOrder("赵薇", "莫文蔚", "林子聪", "吴孟达", "周星驰", "谢贤", "田启文", "黄一飞", "张柏芝", "周星驰");

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
                .as("编号%d的电影(英雄)的影人包括:张艺谋", movieId1)
                .contains("张艺谋");

        Integer movieId2 = 3541415;
        List<Cast> castList2 = movieCastService.findCastByMovieIdAndRole(movieId2, CastRole.ACTOR);
        assertThat(castList2)
                .as("编号%d的电影(盗梦空间)的演员有9个", movieId2)
                .hasSize(9);

        List<String> castNameList2 = castList2.stream().map(Cast::getEnglishName).collect(Collectors.toList());
        assertThat(castNameList2)
                .as("编号%d的电影(盗梦空间)的演员有:Ellen Page,Leonardo DiCaprio,Tom Berenger,Ken Watanabe,Tom Hardy,Marion Cotillard,Cillian Murphy", movieId2)
                .containsAll(Arrays.asList("Ellen Page", "Leonardo DiCaprio", "Tom Berenger", "Ken Watanabe", "Tom Hardy", "Marion Cotillard", "Cillian Murphy"));


    }

    @Test
    @DisplayName("测试查询编号的影人的电影作品信息")
    void test_find_movies_by_staff_id() {
        Integer staffId = 1021999;
        List<Filmography> filmographyList = movieCastService.findMoviesByStaffId(staffId);
        assertThat(filmographyList)
                .as("编号为%d的影人(姜文)有23部作品", staffId)
                .hasSize(23)
                .as("编号为%d的影人(姜文)作品应包括:\"鬼子来了\",\"鬼子来了\",\"阳光灿烂的日子\",\"阳光灿烂的日子\",\"有话好好说\",\"芙蓉镇\",\"宝莲灯\",\"我和爸爸\",\"寻枪\",\"茉莉花开\",\"红高粱\",\"一个陌生女人的来信\",\"太阳照常升起\",\"太阳照常升起\",\"New York, I Love You\",\"让子弹飞\",\"让子弹飞\",\"Océans\",\"一步之遥\",\"一步之遥\",\"Rogue One: A Star Wars Story\",\"邪不压正\",\"邪不压正\"", staffId)
                .extracting(Filmography::getOriginalTitle)
                .containsExactlyInAnyOrder("鬼子来了", "鬼子来了", "阳光灿烂的日子", "阳光灿烂的日子", "有话好好说",
                        "芙蓉镇", "宝莲灯", "我和爸爸", "寻枪", "茉莉花开", "红高粱", "一个陌生女人的来信", "太阳照常升起",
                        "太阳照常升起", "New York, I Love You", "让子弹飞", "让子弹飞",
                        "Océans", "一步之遥", "一步之遥", "Rogue One: A Star Wars Story", "邪不压正", "邪不压正");

    }

    @Test
    @DisplayName("测试查询编号的影人的参演电影作品信息")
    void test_find_acted_movies_by_staff_id() {
        Integer staffId = 1274388;
        List<Filmography> filmographyList = movieCastService.findActedMoviesByStaffId(1274388);
        assertThat(filmographyList)
                .as("编号为%d的影人(王宝强)参演了13部作品", staffId)
                .hasSize(13);

        List<String> originalTitleList = filmographyList.stream().map(Filmography::getOriginalTitle).collect(Collectors.toList());
        assertThat(originalTitleList)
                .as("编号为%d的影人(王宝强)参演作品包括：Hello！树先生,人在囧途,唐人街探案,唐人街探案2,天下无贼", staffId)
                .containsAll(Arrays.asList("Hello！树先生", "人在囧途", "唐人街探案", "唐人街探案2", "天下无贼"));
    }

    @Test
    @DisplayName("测试查询编号的影人的执导电影作品信息")
    void test_find_directed_movies_by_staff_id() {
        Integer staffId = 1054440;
        List<Filmography> filmographyList = movieCastService.findDirectedMoviesByStaffId(staffId);
        assertThat(filmographyList)
                .as("编号为%d的影人(史蒂文·斯皮尔伯格)导演了14部作品", staffId)
                .hasSize(14);

        List<String> originalTitleList = filmographyList.stream().map(Filmography::getOriginalTitle).collect(Collectors.toList());
        assertThat(originalTitleList)
                .as("编号为%d的影人(史蒂文·斯皮尔伯格)导演作品包括:Catch Me If You Can,Ready Player One,The Terminal,Jurassic Park,Saving Private Ryana", staffId)
                .containsAll(Arrays.asList("Catch Me If You Can", "Ready Player One", "The Terminal", "Jurassic Park", "Saving Private Ryan"));
    }

}

