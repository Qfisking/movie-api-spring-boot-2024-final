
package nxu.it.movieapi.service.pg;

import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Filmography;
import nxu.it.api.model.SimpleStaff;
import nxu.it.api.model.Staff;
import nxu.it.api.service.StaffService;
import nxu.it.api.service.param.StaffPageQueryParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffServiceTestCase {
    @Autowired
    StaffService staffService;

    @Test
    @DisplayName("测试查询指定编号的影人信息")
    void test_find_staff_by_id() {
        Optional<Staff> staffOptional = staffService.findById(66665555);
        assertThat(staffOptional)
                .as("编号为%d的影人不存在", 1000525)
                .isEmpty();

        staffOptional = staffService.findById(1000525);
        assertThat(staffOptional)
                .as("编号为%d的影人存在", 1000525)
                .isPresent();
        Staff staff = staffOptional.get();
        assertThat(staff)
                .as("编号为%d的影人中文名为:吴京", 1000525)
                .matches(s -> s.getChineseName().equals("吴京"))
                .as("编号为%d的影人英文名为:Jing Wu", 1000525)
                .matches(s -> s.getEnglishName().equals("Jing Wu"))
                .as("编号为%d的影人国家地区为:中国", 1000525)
                .matches(s -> s.getRegion().equals("中国"));

        List<Filmography> actedMovies = staff.getActed();
        assertThat(actedMovies)
                .as("吴京主演的电影有:\"殺破狼\", \"黑拳\", \"男儿本色\", \"战狼\", \"殺破狼2\", \"流浪地球\", \"战狼2\"")
                .extracting(Filmography::getOriginalTitle)
                .containsExactlyInAnyOrder("殺破狼", "黑拳", "男儿本色", "战狼", "殺破狼2", "流浪地球", "战狼2");

        List<Filmography> directed = staff.getDirected();
        assertThat(directed)
                .as("吴京导演的电影有: \"战狼\", \"战狼2\"")
                .extracting(Filmography::getOriginalTitle)
                .containsExactlyInAnyOrder("战狼", "战狼2");


        staffOptional = staffService.findById(1021999);
        assertThat(staffOptional)
                .as("编号为%d的影人存在", 1021999)
                .isPresent();
        staff = staffOptional.get();
        assertThat(staff)
                .as("编号为%d的影人中文名为:姜文", 1021999)
                .matches(s -> s.getChineseName().equals("姜文"))
                .as("编号为%d的影人英文名为:Wen Jiang", 1021999)
                .matches(s -> s.getEnglishName().equals("Wen Jiang"))
                .as("编号为%d的影人国家地区为:中国", 1021999)
                .matches(s -> s.getRegion().equals("中国"));

        directed = staff.getDirected();
        assertThat(directed)
                .as("编号为%d的影人(姜文)导演的电影应包括:\"鬼子来了\",\"阳光灿烂的日子\",\"太阳照常升起\",\"New York, I Love You\",\"让子弹飞\",\"一步之遥\",\"邪不压正\"", 1021999)
                .extracting(Filmography::getOriginalTitle)
                .containsExactlyInAnyOrder("鬼子来了", "阳光灿烂的日子", "太阳照常升起",
                        "New York, I Love You", "让子弹飞", "一步之遥", "邪不压正");


        actedMovies = staff.getActed();
        assertThat(actedMovies)
                .as("编号为%d的影人(姜文)主演的电影应包括:\"鬼子来了\",\"阳光灿烂的日子\",\"有话好好说\",\"芙蓉镇\",\"宝莲灯\",\"我和爸爸\",\"寻枪\",\"茉莉花开\",\"红高粱\",\"一个陌生女人的来信\",\"太阳照常升起\",\"让子弹飞\",\"Océans\",\"一步之遥\",\"Rogue One: A Star Wars Story\",\"邪不压正\"", 1021999)
                .extracting(Filmography::getOriginalTitle)
                .containsExactlyInAnyOrder("鬼子来了", "阳光灿烂的日子", "有话好好说", "芙蓉镇",
                        "宝莲灯", "我和爸爸", "寻枪", "茉莉花开", "红高粱", "一个陌生女人的来信", "太阳照常升起", "让子弹飞", "Océans",
                        "一步之遥", "Rogue One: A Star Wars Story", "邪不压正");


    }

    @Test
    @DisplayName("测试查询分页查询影人信息")
    void test_staff_page_query() {
        StaffPageQueryParam pageQueryParam = new StaffPageQueryParam();
        pageQueryParam.setEnglishName("lee");
        pageQueryParam.setChineseName("斯");
        pageQueryParam.setRegion("美国");
        pageQueryParam.setGender("女");
        pageQueryParam.setPageSize(3);
        pageQueryParam.setPageNumber(2);
        Pageable<SimpleStaff> staffPage = staffService.page(pageQueryParam);

        assertThat(staffPage)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第2页,共有2页")
                .matches(p -> p.getTotalPage() == 2)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第2页,共有5条记录")
                .matches(p -> p.getTotalRow() == 5);
        List<SimpleStaff> staffList = staffPage.getList();
        assertThat(staffList)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第2页,共有2条记录")
                .hasSize(2);
        SimpleStaff staff = staffList.get(0);
        String chineseName = staff.getChineseName();
        assertThat(chineseName)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第2页第1条记录影人中文名包含斯")
                .contains("斯");
    }


}

