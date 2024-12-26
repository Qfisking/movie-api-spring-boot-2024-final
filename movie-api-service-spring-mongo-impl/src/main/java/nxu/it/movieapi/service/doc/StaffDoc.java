package nxu.it.movieapi.service.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "staff")
public class StaffDoc {
    @Id
    private Integer id;
    private String chineseName;
    private String englishName;
    private String gender;
    private String region;
    private String summary;
    private LocalDate birthday;
    private String avatarUrl;
    private List<FilmographyDoc> acted =new ArrayList<>();
    private List<FilmographyDoc> directed =new ArrayList<>();

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<FilmographyDoc> getActed() {
        return acted;
    }

    public void setActed(List<FilmographyDoc> acted) {
        this.acted = acted;
    }

    public List<FilmographyDoc> getDirected() {
        return directed;
    }

    public void setDirected(List<FilmographyDoc> directed) {
        this.directed = directed;
    }

    @Override
    public String toString() {
        return "StaffDoc{" +
                "id=" + id +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                ", summary='" + summary + '\'' +
                ", birthday=" + birthday +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", acted=" + acted +
                ", directed=" + directed +
                '}';
    }
}
