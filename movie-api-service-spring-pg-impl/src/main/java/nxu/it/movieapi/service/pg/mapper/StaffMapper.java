package nxu.it.movieapi.service.pg.mapper;

import nxu.it.api.model.SimpleStaff;
import nxu.it.api.model.Staff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper{
    Staff fromEntity(StaffEntity staffEntity);
    SimpleStaff fromEntityToSimpleStaff(StaffEntity staffEntity);
    List<SimpleStaff> fromEntityListToSimpleStaffList(List<StaffEntity> staffEntityList);
}
