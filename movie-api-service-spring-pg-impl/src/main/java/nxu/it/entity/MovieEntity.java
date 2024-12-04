package nxu.it.entity;

import nxu.it.api.model.Cast;
import nxu.it.api.model.Genre;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "movie")
public class MovieEntity {

    protected Integer id;

    protected String originalTitle;

    protected String chineseTitle;

    protected String aka;

    protected Integer year;

    protected String region;

    protected Integer duration;

    protected String language;

    protected LocalDate pubDate;

    protected String summary;

    protected String posterUrl;

    protected Double rating;

    protected String poster;


    protected List<Genre> genres;

    protected List<Cast> directors;

    protected List<Cast> actors;


    public List<Cast> getDirectors() {
        return this.directors;
    }

    public void setDirectors(List<Cast> directors) {
        this.directors = directors;
    }

    public List<Cast> getActors() {
        return this.actors;
    }

    public void setActors(List<Cast> actors) {
        this.actors = actors;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public MovieEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return this.originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getChineseTitle() {
        return this.chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }

    public String getAka() {
        return this.aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MovieEntity movie = (MovieEntity)o;
            return this.id.equals(movie.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id});
    }

    public String toString() {
        return "MovieEntity{id=" + this.id + ", originalTitle='" + this.originalTitle + "'}";
    }

}
