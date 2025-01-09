package nxu.it.movieapi.service.pg.mapper;

import nxu.it.api.model.Cast;
import nxu.it.api.model.Filmography;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieCastMapper{
    List<Cast> fromEntityList(List<CastEntity> castEntityList);
    List<Filmography> frommovieEntityList(List<FilmographyEntity> movieEntityList);
}
