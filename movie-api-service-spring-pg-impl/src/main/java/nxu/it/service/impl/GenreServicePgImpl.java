package nxu.it.service.impl;

import nxu.it.api.model.Genre;
import nxu.it.api.service.GenreService;
import nxu.it.dao.GenreDao;
import nxu.it.entity.GenreEntity;
import nxu.it.entity.QGenreEntity;
import nxu.it.mapper.GenreMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreServicePgImpl implements GenreService {

    final GenreDao genreDao;
   // final QueryDsl queryDsl;
    final GenreMapper genreMapper;


    final QGenreEntity GENRE = new QGenreEntity();

    public GenreServicePgImpl(GenreDao genreDao, GenreMapper genreMapper) {
        this.genreDao = genreDao;
        this.genreMapper = genreMapper;
    }


    @Override
    public List<Genre> findAll() {
        List<GenreEntity> genreEntityList = genreDao.findAll();
        return genreMapper.fromEntityList(genreEntityList);
    }

    @Override
    public List<Genre> findByMovieId(Integer movieId) {
        List<GenreEntity> genreEntityList = genreDao.findByMovieId(movieId);
        return genreMapper.fromEntityList(genreEntityList);
    }

    @Override
    public List<Genre> findByName(String genreName) {
        List<GenreEntity> genreEntityList = genreDao.findByName(genreName);
        return genreMapper.fromEntityList(genreEntityList);
    }
}
