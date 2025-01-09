package nxu.it.movieapi.service.pg.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;

@Dao
@ConfigAutowireable
public interface CommentDao{
    @Select
    List<CommentEntity> pageByUserId(Integer userId,SelectOptions options);
    @Select
    List<CommentEntity> pageByMovieId(Integer movieId,SelectOptions options);
}
