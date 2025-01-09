package nxu.it.movieapi.service.pg;

import nxu.it.movieapi.service.pg.dao.GenreDao;
import nxu.it.api.model.Genre;
import nxu.it.api.service.GenreService;
import nxu.it.movieapi.service.pg.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreServicePgImpl implements GenreService {
    @Autowired
    GenreDao genreDao;

    @Autowired
    GenreMapper genreMapper;

    @Override
    public List<Genre> findAll() {
        List<GenreEntity> genreEntities = genreDao.findAll();
        List<Genre> genres =genreEntities.stream().map(genreEntity ->
                new Genre(genreEntity.getId(),genreEntity.getName())).collect(Collectors.toList());
        return genres;
    }

    @Override
    public List<Genre> findByMovieId(Integer id){
        List<GenreEntity> genreEntities = genreDao.findByMovieId(id);
        List<Genre> genres =genreEntities.stream().map(genreEntity ->
                new Genre(genreEntity.getId(),genreEntity.getName())).collect(Collectors.toList());
        return genres;
    }

    @Override
    public List<Genre> findByName(String name){
        List<GenreEntity> genreEntities = genreDao.findByName(name);
        List<Genre> genres =genreEntities.stream().map(genreEntity ->
                new Genre(genreEntity.getId(),genreEntity.getName())).collect(Collectors.toList());
        return genres;
    }
}
