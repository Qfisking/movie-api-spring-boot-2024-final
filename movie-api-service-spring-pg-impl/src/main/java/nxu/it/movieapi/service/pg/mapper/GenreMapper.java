package nxu.it.movieapi.service.pg.mapper;

import nxu.it.api.model.Genre;
import nxu.it.movieapi.service.pg.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    Genre fromEntity(GenreEntity genreEntity);
    List<Genre> fromEntityList(List<GenreEntity> genreEntity);
}
