package nxu.it.movieapi.service.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CastDoc {
    @Id
    private Integer id;
    private String chineseName;
    private String englishName;
    private String avatarUrl;
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "CastDoc{" +
                "id=" + id +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
