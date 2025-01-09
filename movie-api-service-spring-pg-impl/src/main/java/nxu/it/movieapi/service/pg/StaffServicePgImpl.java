package nxu.it.movieapi.service.pg;

import nxu.it.api.common.result.BasePage;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.model.*;
import nxu.it.api.service.GenreService;
import nxu.it.api.service.MovieCastService;
import nxu.it.api.service.StaffService;
import nxu.it.api.service.param.StaffPageQueryParam;
import nxu.it.movieapi.service.pg.dao.ProductDao;
import nxu.it.movieapi.service.pg.mapper.StaffMapper;
import org.jetbrains.annotations.NotNull;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StaffServicePgImpl implements StaffService {
    @Autowired
    ProductDao staffDao;

    @Autowired
    GenreService genreService;

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    MovieCastService movieCastService;

    public Optional<Staff> findById(@org.jetbrains.annotations.NotNull Integer staffId){
            Optional<StaffEntity> staffEntityOpt = staffDao.findById(staffId);
            return staffEntityOpt.map(staffEntity -> {
                Staff staff = staffMapper.fromEntity(staffEntity);
                List<Filmography> movieList= movieCastService.findMoviesByStaffId(staffId);
                List<Filmography> actedList = movieList.stream()
                        .filter(movie -> movie.getRoleTypeId() == CastRole.ACTOR.getId())
                        .collect(Collectors.toList());
                List<Filmography> directedList = movieList.stream()
                        .filter(movie -> movie.getRoleTypeId() == CastRole.DIRECTOR.getId())
                        .collect(Collectors.toList());
                staff.setActed(actedList);
                staff.setDirected(directedList);
                return staff;
            });
        }

    @Override
    public Pageable<SimpleStaff> page(@NotNull StaffPageQueryParam param) {
        SelectOptions options = SelectOptions.get().count();
        staffDao.page(param, options);
        int totalCount = (int) options.getCount();
        int pageNumber = param.getPageNumber();
        int pageSize = param.getPageSize();
        int totalPage = PageUtil.getTotalPage(totalCount, pageSize);
        int offset = PageUtil.getOffset(pageNumber, pageSize);
        options.offset(offset).limit(pageSize);
        List<StaffEntity> staffEntityList = staffDao.page(param, options);
        List<SimpleStaff> simpleStaffList = staffMapper.fromEntityListToSimpleStaffList(staffEntityList);
        Pageable<SimpleStaff> staffPage = new BasePage<SimpleStaff>(simpleStaffList,pageNumber,pageSize,totalPage,totalCount);
        return staffPage;
    }
}
