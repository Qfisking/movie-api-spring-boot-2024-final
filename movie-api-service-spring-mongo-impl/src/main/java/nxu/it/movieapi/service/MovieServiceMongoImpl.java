package nxu.it.movieapi.service;

import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Movie;
import nxu.it.api.model.SimpleMovie;
import nxu.it.api.service.MovieService;
import nxu.it.api.service.param.MoviePageQueryParam;
import nxu.it.movieapi.service.doc.MovieDoc;
import nxu.it.movieapi.service.mapper.MovieMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieServiceMongoImpl implements MovieService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Optional<Movie> findById(@NotNull Integer movieId) {
//        Query query = new Query();
//        Criteria c = Criteria.where("id").is(movieId);
//        query.addCriteria(c);
        MovieDoc movieDoc = mongoTemplate.findById(movieId, MovieDoc.class);
        Movie movie = movieMapper.fromDoc(movieDoc);
        return Optional.ofNullable(movie);

    }
    @Override
    public Pageable<SimpleMovie> page(@NotNull MoviePageQueryParam param) {
          List<Criteria> criteriaList = new ArrayList<>();
        String chineseTitle = param.getChineseTitle();
        if (chineseTitle != null && !chineseTitle.isBlank()) {
            criteriaList.add(Criteria.where("chineseTitle").regex(chineseTitle, "i"));
        }
        String originalTitle = param.getOriginalTitle();
        if (originalTitle != null && !originalTitle.isBlank()) {
            criteriaList.add(Criteria.where("originalTitle").regex(originalTitle, "i"));
        }
        String language = param.getLanguage();
        if (language != null && !language.isBlank()) {
            criteriaList.add(Criteria.where("language").regex(language, "i"));
        }
        String region = param.getRegion();
        if (region != null && !region.isBlank()) {
            criteriaList.add(Criteria.where("region").regex(region, "i"));
        }
        Integer startYear = param.getStartYear();
        if (startYear != null) {
            criteriaList.add(Criteria.where("year").gte(startYear));
        }
        Integer endYear = param.getEndYear();
        if (endYear != null) {
            criteriaList.add(Criteria.where("year").lte(endYear));
        }
        List<Integer> genreIds = param.getGenreIds();
        if (genreIds != null && !genreIds.isEmpty()) {
            // 确保字段存在
            criteriaList.add(Criteria.where("genres._id").exists(true).in(genreIds));
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
        query.with(PageRequest.of(pageNumber, pageSize)); // 添加分页条件

        // 查询
        List<MovieDoc> movieDocs = mongoTemplate.find(query, MovieDoc.class);

        // 总记录数
        long total = mongoTemplate.count(query.skip(0).limit(0), MovieDoc.class);

        // 总页数
        int totalPages = (int) ((total + pageSize - 1) / pageSize);

        List<SimpleMovie> movieList = movieMapper.fromDocList(movieDocs);

        // 返回分页对象
        return new BasePage<>(movieList, pageNumber, pageSize, totalPages, (int) total);

    }
}


