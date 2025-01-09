package nxu.it.movieapi.service.pg.entity;


import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.seasar.doma.Column;

@Entity
@Table(name = "t_category")
public class Category {
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "level")
    private Integer level;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
