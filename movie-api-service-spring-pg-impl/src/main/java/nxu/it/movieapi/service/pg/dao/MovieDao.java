package nxu.it.movieapi.service.pg.dao;

import nxu.it.api.service.param.MoviePageQueryParam;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface MovieDao {
    @Select
    Optional<MovieEntity> findById(Integer movieId);

    @Select
    List<MovieEntity> page(MoviePageQueryParam param, SelectOptions options);
}
