package nxu.it.entity;


import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "movie_genre", schema = "public")
public class MovieGenreEntity {
    @Id
    private Integer id;

    private Integer movieId;

    private Integer genreId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return this.movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getGenreId() {
        return this.genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}
