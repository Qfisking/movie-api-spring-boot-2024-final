package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.Filmography;
import nxu.it.movieapi.service.doc.FilmographyDoc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmographyMapper {
    Filmography fromDoc(FilmographyDoc filmographyDoc);
    List<Filmography> fromDocList(List<FilmographyDoc> filmographyDocList);
}
