package nxu.it.movieapi.service;

import nxu.it.api.common.param.PageQueryParam;
import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Comment;
import nxu.it.api.service.CommentService;
import nxu.it.movieapi.service.doc.CommentDoc;
import nxu.it.movieapi.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CommentServiceMongoImpl implements CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Pageable<Comment> pageByUserId(Integer userId, PageQueryParam param) {
        Query query = new Query();
        Criteria criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
        int pageNumber = param.getPageNumber() > 0 ? param.getPageNumber() - 1 : 0;
        int pageSize = param.getPageSize();
        long total = mongoTemplate.count(query, CommentDoc.class);
        query.with(PageRequest.of(pageNumber, pageSize));
        List<CommentDoc> commentDocList = mongoTemplate.find(query, CommentDoc.class);
        int totalPages = (int) ((total + pageSize - 1) / pageSize);
        List<Comment> commentList = commentMapper.fromDocList(commentDocList);
        return  new BasePage<>(commentList, pageNumber, pageSize, totalPages, (int) total);
    }

    @Override
    public Pageable<Comment> pageByMovieId(Integer movieId, PageQueryParam param) {
        Query query = new Query();
        Criteria criteria = Criteria.where("movieId").is(movieId);
        query.addCriteria(criteria);
        int pageNumber = param.getPageNumber() > 0 ? param.getPageNumber() - 1 : 0;
        int pageSize = param.getPageSize();
        long total = mongoTemplate.count(query, CommentDoc.class);
        query.with(PageRequest.of(pageNumber, pageSize));
        List<CommentDoc> commentDocList = mongoTemplate.find(query, CommentDoc.class);
        int totalPages = (int) ((total + pageSize - 1) / pageSize);
        List<Comment> commentList = commentMapper.fromDocList(commentDocList);
        return  new BasePage<>(commentList, pageNumber, pageSize, totalPages, (int) total);
    }
}
