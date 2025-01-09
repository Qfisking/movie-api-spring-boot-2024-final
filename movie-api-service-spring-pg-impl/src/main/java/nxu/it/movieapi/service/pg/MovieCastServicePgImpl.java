package nxu.it.movieapi.service.pg;

import nxu.it.api.model.Cast;
import nxu.it.api.model.CastRole;
import nxu.it.api.model.Filmography;
import nxu.it.api.service.MockResult;
import nxu.it.api.service.MovieCastService;
import nxu.it.movieapi.service.pg.dao.MovieCastDao;
import nxu.it.movieapi.service.pg.mapper.MovieCastMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;


@Component
public class MovieCastServicePgImpl implements MovieCastService {

    @Autowired
    private MovieCastDao movieCastDao;

    @Autowired
    private MovieCastMapper movieCastMapper;

    @Override
    public List<Cast> findCastByMovieId(@NotNull Integer movieId) {
        List<CastEntity> castEntityList = movieCastDao.findCastByMovieId(movieId);
        return movieCastMapper.fromEntityList(castEntityList);
    }

    @Override
    public List<Cast> findCastByMovieIdAndRole(@NotNull Integer movieId, @NotNull CastRole role) {
        List<CastEntity> castEntityList = movieCastDao.findCastByMovieIdAndRole(movieId,role);
        return movieCastMapper.fromEntityList(castEntityList);
    }

    @Override
    public List<Filmography> findMoviesByStaffId(@NotNull Integer staffId) {
        List<FilmographyEntity> MovieEntityList = movieCastDao.findMoviesByStaffId(staffId);
        return movieCastMapper.frommovieEntityList(MovieEntityList);
    }

    @Override
    public List<Filmography> findMoviesByStaffIdAndRole(@NotNull Integer staffId, @NotNull CastRole role) {
        return role == CastRole.ACTOR ? MockResult.ACTED : MockResult.DIRECTED;
    }
}
