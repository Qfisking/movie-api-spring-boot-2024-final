package nxu.it.movieapi.service;

import nxu.it.api.model.Genre;
import nxu.it.api.service.GenreService;
import nxu.it.movieapi.service.doc.GenreDoc;
import nxu.it.movieapi.service.doc.MovieDoc;
import nxu.it.movieapi.service.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreServiceMongoImpl implements GenreService {

    @Autowired
    MongoTemplate template;
    @Autowired
    GenreMapper genreMapper;
    @Override
    public List<Genre> findByMovieId(Integer movieId) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        Criteria c = Criteria.where("id").is(movieId);
        query.addCriteria(c);
        MovieDoc movieDoc = template.findOne(query,MovieDoc.class);
        List<Genre> emptyList = new ArrayList<>();
        if(movieDoc == null) {
            return emptyList;
        }
        List<GenreDoc> genreDocList = movieDoc.getGenres();
        return genreMapper.fromDocList(genreDocList);

    }

    @Override
    public List<Genre> findAll() {
        List<GenreDoc> allGenreDocList = template.query(GenreDoc.class).all();
        return genreMapper.fromDocList(allGenreDocList);
    }

    @Override
    public List<Genre> findByName(String genreName) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        Criteria c = Criteria.where("name").regex(genreName,"i");
        query.addCriteria(c);
        List<GenreDoc> genreDocList = template.find(query,GenreDoc.class);
        return genreMapper.fromDocList(genreDocList);
    }
}
