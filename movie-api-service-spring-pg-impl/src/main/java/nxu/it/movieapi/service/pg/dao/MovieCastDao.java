package nxu.it.movieapi.service.pg.dao;

import nxu.it.api.model.CastRole;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Dao
@ConfigAutowireable
public interface MovieCastDao{
    @Select
    List<CastEntity> findCastByMovieId(@NotNull Integer movieId);
    @Select
    List<CastEntity> findCastByMovieIdAndRole(@NotNull Integer movieId, @NotNull CastRole role);
    @Select
    List<FilmographyEntity> findMoviesByStaffId(@NotNull Integer staffId);
/*    @Select
    List<FilmographyEntity> findMoviesByStaffIdAndRole(@NotNull Integer staffId, @NotNull CastRole role);*/
}
