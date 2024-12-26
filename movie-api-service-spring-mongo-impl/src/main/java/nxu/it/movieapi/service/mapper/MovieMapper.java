package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.Movie;
import nxu.it.api.model.SimpleMovie;
import nxu.it.movieapi.service.doc.MovieDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {CastMapper.class,GenreMapper.class})
public interface MovieMapper {
    Movie fromDoc(MovieDoc movieDoc);
    List<SimpleMovie> fromDocList(List <MovieDoc> movieDocList);

}
