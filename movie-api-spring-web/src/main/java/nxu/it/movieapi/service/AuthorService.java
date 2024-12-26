package nxu.it.movieapi.service;
import nxu.it.api.model.Author;
import java.util.List;
public interface AuthorService {
    List<Author> findAll();
}