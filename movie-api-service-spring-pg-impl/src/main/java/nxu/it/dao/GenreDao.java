package nxu.it.dao;

import nxu.it.entity.GenreEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;
import java.util.Map;

@Dao
@ConfigAutowireable
public interface GenreDao {
    @Select
    List<GenreEntity> findByMovieId(int movieId);

    @Select
    List<GenreEntity> findAll();

    @Select
    List<GenreEntity> findByName(String name);

    @Select
    @Sql("SELECT id, name FROM public.genre WHERE id = /* id*/1")
    Map<String, Object> findGenreById(int id);
}
