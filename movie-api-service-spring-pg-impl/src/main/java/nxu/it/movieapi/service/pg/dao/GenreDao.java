package nxu.it.movieapi.service.pg.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface GenreDao {
    @Select
    List<GenreEntity> findAll();
    @Select
    List<GenreEntity> findByMovieId(Integer id);
    @Select
    List<GenreEntity> findByName(String name);
}