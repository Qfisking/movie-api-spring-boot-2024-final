package nxu.it.movieapi.service.mock;

import nxu.it.api.common.param.PageQueryParam;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.Comment;
import nxu.it.api.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CommentServiceTestCase extends BaseTestCase {
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("测试查询根据用户编号分页查询评论")
    public void testPageByUserId() {
        PageQueryParam pageQueryParam = new PageQueryParam();
        pageQueryParam.setPageNumber(1);
        pageQueryParam.setPageSize(10);
        Pageable<Comment> commentPage = commentService.pageByUserId(1, pageQueryParam);
        assertThat(commentPage.getTotalPage()).isEqualTo(1);
        assertThat(commentPage.getTotalRow()).isEqualTo(2);
        List<Comment> commentList = commentPage.getList();
        Integer userId = commentList.get(0).getUserId();
        assertThat(userId).isEqualTo(1);
    }

    @Test
    @DisplayName("测试查询根据电影编号分页查询评论")
    public void testPageByMovieId() {
        PageQueryParam pageQueryParam = new PageQueryParam();
        pageQueryParam.setPageNumber(1);
        pageQueryParam.setPageSize(10);
        Pageable<Comment> commentPage = commentService.pageByMovieId(3742360, pageQueryParam);
        assertThat(commentPage.getTotalPage()).isEqualTo(1);
        assertThat(commentPage.getTotalRow()).isEqualTo(2);
    }
}
