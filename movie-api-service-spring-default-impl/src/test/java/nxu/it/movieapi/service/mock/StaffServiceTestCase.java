/*
package nxu.it.movieapi.service.mock;


import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.SimpleStaff;
import nxu.it.api.model.Staff;
import nxu.it.api.service.StaffService;
import nxu.it.api.service.param.StaffPageQueryParam;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffServiceTestCase extends BaseTestCase{
    @Autowired
    StaffService staffService;

    @Test
    @DisplayName("测试查询指定编号的影人信息")
    void test_find_staff_by_id() {
        Optional<Staff> staffOptional = staffService.findById(1000525);
        assertThat(staffOptional)
                .as("编号为%d的影人存在", 1000525)
                .isPresent();
        Staff staff = staffOptional.get();
        assertThat(staff)
                .as("编号为%d的影人中文名为:马老实", 1000525)
                .matches(s -> s.getChineseName().equals("马老实"))
                .as("编号为%d的影人英文名为:Rajab Ma", 1000525)
                .matches(s -> s.getEnglishName().equals("Rajab Ma"))
                .as("编号为%d的影人国家地区为:中国大陆", 1000525)
                .matches(s -> s.getRegion().equals("中国大陆"));
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
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第1页,共有1页")
                .matches(p -> p.getTotalPage() == 1)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第1页,共有1条记录")
                .matches(p -> p.getTotalRow() == 2);
        List<SimpleStaff> staffList = staffPage.getList();
        assertThat(staffList)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第1页,共有2条记录")
                .hasSize(2);
        SimpleStaff staff = staffList.get(0);
        String chineseName = staff.getChineseName();
        assertThat(chineseName)
                .as("英文名中包含lee,中文名包含斯的美国女影人分页查询,每页3条记录,第2页第1条记录影人中文名包含斯")
                .contains("老");
    }


}
*/
