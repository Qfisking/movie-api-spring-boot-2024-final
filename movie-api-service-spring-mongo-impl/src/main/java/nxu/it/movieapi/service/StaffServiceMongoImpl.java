package nxu.it.movieapi.service;

import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.SimpleStaff;
import nxu.it.api.model.Staff;
import nxu.it.api.service.StaffService;
import nxu.it.api.service.param.StaffPageQueryParam;
import nxu.it.movieapi.service.doc.StaffDoc;
import nxu.it.movieapi.service.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StaffServiceMongoImpl implements StaffService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Optional<Staff> findById(@NotNull Integer staffId) {
        StaffDoc staffDoc = mongoTemplate.findById(staffId,StaffDoc.class);
        return Optional.ofNullable(staffMapper.fromDoc(staffDoc));
    }

    @Override
    public Pageable<SimpleStaff> page(@NotNull StaffPageQueryParam param) {
        List<Criteria> criteriaList = new ArrayList<>();
        String chineseName = param.getChineseName();
        if (chineseName != null && !chineseName.isBlank()) {
            criteriaList.add(Criteria.where("chineseName").regex(chineseName, "i"));
        }
        String englishName = param.getEnglishName();
        if (englishName != null && !englishName.isBlank()) {
            criteriaList.add(Criteria.where("englishName").regex(englishName, "i"));
        }
        String region = param.getRegion();
        if (region != null && !region.isBlank()) {
            criteriaList.add(Criteria.where("region").regex(region, "i"));
        }
        String gender = param.getGender();
        if (gender != null && !gender.isBlank()) {
            criteriaList.add(Criteria.where("gender").regex(gender, "i"));
        }
        // 排序逻辑
        String orderField = param.getOrderField(); // 排序字段
        String orderDirection = param.getOrderDirection(); // 排序方向 ("asc" 或 "desc")
        Sort sort = null;
        if (orderField != null && !orderField.isBlank()) {
            if ("asc".equalsIgnoreCase(orderDirection)) {
                sort = Sort.by(Sort.Direction.ASC, orderField); // 升序
            } else if ("desc".equalsIgnoreCase(orderDirection)) {
                sort = Sort.by(Sort.Direction.DESC, orderField); // 降序
            } else {
                throw new IllegalArgumentException("Invalid order direction: " + orderDirection);
            }
        }
        int pageNumber = param.getPageNumber() > 0 ? param.getPageNumber() - 1 : 0;
        int pageSize = param.getPageSize();
        // 构建查询
        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }
        if (sort != null) {
            query.with(sort); // 添加排序条件
        }

        // 总记录数
        long total = mongoTemplate.count(query, StaffDoc.class);
        query.with(PageRequest.of(pageNumber, pageSize)); // 添加分页条件

        List<StaffDoc> staffDocList = mongoTemplate.find(query,StaffDoc.class);


        // 总页数
        int totalPages = (int) ((total + pageSize - 1) / pageSize);

        List<SimpleStaff> staffList = staffMapper.fromDocList(staffDocList);

        return new BasePage<>(staffList, pageNumber, pageSize, totalPages, (int) total);
    }
}
