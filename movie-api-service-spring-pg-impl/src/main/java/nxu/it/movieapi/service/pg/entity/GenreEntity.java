package nxu.it.movieapi.service.pg.entity;


import org.seasar.doma.Entity;
import org.seasar.doma.Table;

@Entity
@Table(name="genre")
public class GenreEntity{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreEntity [id=" + id + ", name=" + name + "]";
    }
}
