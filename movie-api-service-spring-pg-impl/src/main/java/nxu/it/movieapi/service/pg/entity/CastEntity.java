package nxu.it.movieapi.service.pg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class CastEntity {
    protected Integer id;

    protected String chineseName;

    protected String englishName;

    protected String actAs;

    protected String avatarUrl;

    protected String avatar;

    @Column(name="role_id")
    protected transient Integer roleTypeId;

    public CastEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Integer getRoleTypeId() {
        return this.roleTypeId;
    }

    public void setRoleTypeId(Integer roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getActAs() {
        return this.actAs;
    }

    public void setActAs(String actAs) {
        this.actAs = actAs;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
