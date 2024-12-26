package nxu.it.movieapi.service.doc;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "movie")
public class MovieDoc {
    private Long id;
    private String originalTitle;
    private String chineseTitle;
    private String aka;
    private String year;
    private String region;
    private Integer duration;
    private String language;
    private LocalDate pubDate;
    private String summary;
    private String posterUrl;
    private Double rating;
    private String poster;
    private List<GenreDoc> genres = new ArrayList<>();
    private List<CastDoc> actors = new ArrayList<>();
    private List<CastDoc> directors = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public List<CastDoc> getDirectors() {
        return directors;
    }

    public void setDirectors(List<CastDoc> directors) {
        this.directors = directors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getChineseTitle() {
        return chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<CastDoc> getActors() {
        return actors;
    }

    public void setActors(List<CastDoc> actors) {
        this.actors = actors;
    }

    public List<GenreDoc> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDoc> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieDoc{" +
                "id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", chineseTitle='" + chineseTitle + '\'' +
                ", aka='" + aka + '\'' +
                ", year='" + year + '\'' +
                ", region='" + region + '\'' +
                ", duration=" + duration +
                ", language='" + language + '\'' +
                ", pubDate=" + pubDate +
                ", summary='" + summary + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", poster='" + poster + '\'' +
                ", genres=" + genres +
                ", actors=" + actors +
                ", directors=" + directors +
                '}';
    }
}
