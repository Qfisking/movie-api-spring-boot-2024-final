package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.Cast;
import nxu.it.movieapi.service.doc.CastDoc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CastMapper {
    Cast fromDoc(CastDoc castDoc);
    List<Cast> fromDocList(List<CastDoc> castDocList);
}
