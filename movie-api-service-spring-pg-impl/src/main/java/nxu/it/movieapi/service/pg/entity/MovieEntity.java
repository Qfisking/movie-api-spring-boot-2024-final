package nxu.it.movieapi.service.pg.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "movie")
public class MovieEntity{

    private Long id;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "chinese_title")
    private String chineseTitle;

    private String aka;

    private Integer year;

    private String region;

    private Integer duration;

    private String language;

    @Column(name = "pub_date")
    private LocalDate pubDate;

    private String summary;

    @Column(name = "poster_url")
    private String posterUrl;

    private double rating;

    private String poster;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getOriginalTitle(){
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }
    public String getAka(){
        return aka;
    }
    public void setAka(String aka){
        this.aka = aka;
    }
    public Integer getYear(){
        return year;
    }
    public void setYear(Integer year){
        this.year = year;
    }
    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public Integer getDuration(){
        return duration;
    }
    public void setDuration(Integer duration){
        this.duration = duration;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public LocalDate getPubDate(){
        return pubDate;
    }
    public void setPubDate(LocalDate pubDate){
        this.pubDate = pubDate;
    }
    public String getSummary(){
        return summary;
    }
    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getPosterUrl(){
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl){
        this.posterUrl = posterUrl;
    }
    public double getRating(){
        return rating;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public String getPoster(){
        return poster;
    }
    public void setPoster(String poster){
        this.poster = poster;
    }

    public String getChineseTitle() {
        return chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }
}
