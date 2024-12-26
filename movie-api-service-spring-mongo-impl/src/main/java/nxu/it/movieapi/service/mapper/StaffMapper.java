package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.SimpleStaff;
import nxu.it.api.model.Staff;
import nxu.it.movieapi.service.doc.StaffDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {FilmographyMapper.class})
public interface StaffMapper {
    Staff fromDoc(StaffDoc staffDoc);
    List<SimpleStaff> fromDocList(List<StaffDoc> staffDocList);
}
