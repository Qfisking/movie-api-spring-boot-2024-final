package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.Genre;
import nxu.it.movieapi.service.doc.GenreDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreMapper {
    Genre fromDoc(GenreDoc genreDoc);
    List<Genre> fromDocList(List<GenreDoc> genreDocList);


}
