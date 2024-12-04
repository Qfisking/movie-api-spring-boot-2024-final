package nxu.it.dao;

import nxu.it.entity.GenreEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface GenreDao {
    @Select
    List<GenreEntity> findByMovieId(int movieId);

    @Select
    List<GenreEntity> findAll();

    @Select
    List<GenreEntity> findByName(String name);
}
