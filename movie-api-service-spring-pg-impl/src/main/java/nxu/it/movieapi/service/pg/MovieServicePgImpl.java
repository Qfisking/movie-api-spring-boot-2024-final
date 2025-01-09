package nxu.it.movieapi.service.pg;

import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.*;
import nxu.it.api.service.GenreService;
import nxu.it.api.service.MovieCastService;
import nxu.it.api.service.MovieService;
import nxu.it.api.service.param.MoviePageQueryParam;
import nxu.it.movieapi.service.pg.mapper.MovieMapper;
import org.jetbrains.annotations.NotNull;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nxu.it.movieapi.service.pg.dao.MovieDao;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class MovieServicePgImpl implements MovieService {
    @Autowired
    MovieDao movieDao;

    @Autowired
    GenreService genreService;

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieCastService movieCastService;

    public Optional<Movie> findById(@NotNull Integer movieId) {
        Optional<MovieEntity> movieEntityopt = movieDao.findById(movieId);
        return movieEntityopt.map(movieEntity -> {
            Movie movie = movieMapper.fromEntity(movieEntity);
            List<Genre> genreList = genreService.findByMovieId(movieId);
            movie.setGenres(genreList);
            List<Cast> castList = movieCastService.findCastByMovieId(movieId);
            List<Cast> actorList = castList.stream()
                    .filter(cast -> cast.getRoleTypeId() == CastRole.ACTOR.getId())
                    .collect(Collectors.toList());
            List<Cast> directorList = castList.stream()
                    .filter(cast -> cast.getRoleTypeId() == CastRole.DIRECTOR.getId())
                    .collect(Collectors.toList());
            movie.setActors(actorList);
            movie.setDirectors(directorList);
            return movie;
        });
    }

    @Override
    public Pageable<SimpleMovie> page(@NotNull MoviePageQueryParam param) {
        SelectOptions options = SelectOptions.get().count();
        movieDao.page(param, options);
        int totalCount = (int) options.getCount();
        int pageNumber = param.getPageNumber();
        int pageSize = param.getPageSize();
        int totalPage = PageUtil.getTotalPage(totalCount, pageSize);
        int offset = PageUtil.getOffset(pageNumber, pageSize);
        options.offset(offset).limit(pageSize);
        List<MovieEntity> movieEntityList = movieDao.page(param, options);
        List<SimpleMovie> simpleMovieList = movieMapper.fromEntityListToSimpleMovieList(movieEntityList);
        Pageable<SimpleMovie> moviePage = new BasePage<SimpleMovie>(simpleMovieList,pageNumber,pageSize,totalPage,totalCount);
        return moviePage;
    }
}
