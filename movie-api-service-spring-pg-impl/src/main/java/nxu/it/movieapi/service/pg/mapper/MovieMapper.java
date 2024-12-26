package nxu.it.movieapi.service.pg.mapper;

import nxu.it.api.model.Movie;
import nxu.it.api.model.SimpleMovie;
import nxu.it.movieapi.service.pg.entity.MovieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper{
    Movie fromEntity(MovieEntity movieEntity);
    SimpleMovie fromEntityToSimpleMovie(MovieEntity movieEntity);
    List<SimpleMovie> fromEntityListToSimpleMovieList(List<MovieEntity>movieEntityList);
}
