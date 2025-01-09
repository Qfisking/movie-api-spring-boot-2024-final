package nxu.it.movieapi.service.pg.dao;

import nxu.it.api.service.param.StaffPageQueryParam;
import nxu.it.movieapi.service.pg.entity.ProductEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface ProductDao {

    @Select
    Optional<ProductEntity> findById(Integer staffId);

    @Select
    List<ProductEntity> page(StaffPageQueryParam param,SelectOptions options);
}
