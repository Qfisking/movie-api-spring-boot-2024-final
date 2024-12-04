package nxu.it.entity;

import org.seasar.doma.Entity;

@Entity
public class CastEntity {

    protected Integer id;

    protected String chineseName;

    protected String englishName;

    protected String actAs;

    protected String avatarUrl;

    protected String avatar;

    protected Integer roleTypeId;


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
