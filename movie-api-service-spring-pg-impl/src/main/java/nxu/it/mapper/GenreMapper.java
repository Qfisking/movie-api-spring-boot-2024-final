package nxu.it.mapper;

import nxu.it.api.model.Genre;
import nxu.it.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    Genre fromEntity(GenreEntity entity);
    List<Genre> fromEntityList(List<GenreEntity> entityList);
}
