package nxu.it.movieapi.service.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FilmographyDoc {
    @Id
    private Integer id;
    private String chineseTitle;
    private String originalTitle;
    private String year;
    private String region;
    private String posterUrl;
    private String rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseTitle() {
        return chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "FilmographyDoc{" +
                "id=" + id +
                ", chineseTitle='" + chineseTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", year='" + year + '\'' +
                ", region='" + region + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
