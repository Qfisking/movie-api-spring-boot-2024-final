package nxu.it.movieapi.service.pg;

import nxu.it.api.common.param.PageQueryParam;
import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Comment;
import nxu.it.api.service.CommentService;
import nxu.it.movieapi.service.pg.dao.CommentDao;
import nxu.it.movieapi.service.pg.entity.CommentEntity;
import nxu.it.movieapi.service.pg.mapper.CommentMapper;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentServicePgImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Pageable<Comment> pageByUserId(Integer userId, PageQueryParam param) {
        SelectOptions options = SelectOptions.get().count();
        commentDao.pageByUserId(userId,options);
        int totalCount = (int) options.getCount();
        int pageNumber = param.getPageNumber();
        int pageSize = param.getPageSize();
        int totalPage = PageUtil.getTotalPage(totalCount, pageSize);
        int offset = PageUtil.getOffset(pageNumber, pageSize);
        options.offset(offset).limit(pageSize);
        List<CommentEntity> commentEntityList = commentDao.pageByUserId(userId,options);
        List<Comment> CommentList = commentMapper.fromEntityToList(commentEntityList);
        Pageable<Comment> moviePage = new BasePage<Comment>(CommentList,pageNumber,pageSize,totalPage,totalCount);
        return moviePage;
    }
    @Override
    public Pageable<Comment> pageByMovieId(Integer movieId, PageQueryParam param) {
        SelectOptions options = SelectOptions.get().count();
        commentDao.pageByMovieId(movieId,options);
        int totalCount = (int) options.getCount();
        int pageNumber = param.getPageNumber();
        int pageSize = param.getPageSize();
        int totalPage = PageUtil.getTotalPage(totalCount, pageSize);
        int offset = PageUtil.getOffset(pageNumber, pageSize);
        options.offset(offset).limit(pageSize);
        List<CommentEntity> commentEntityList = commentDao.pageByMovieId(movieId,options);
        List<Comment> CommentList = commentMapper.fromEntityToList(commentEntityList);
        Pageable<Comment> moviePage = new BasePage<Comment>(CommentList,pageNumber,pageSize,totalPage,totalCount);
        return moviePage;
    }
}
