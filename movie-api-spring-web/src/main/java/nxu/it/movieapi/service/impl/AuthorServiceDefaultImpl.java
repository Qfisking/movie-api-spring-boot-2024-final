package nxu.it.movieapi.service.impl;

import nxu.it.api.model.Author;
import nxu.it.movieapi.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorServiceDefaultImpl implements AuthorService {
    @Override
    public List<Author> findAll() {
        List<Author> authorList=List.of(
                new Author("001","赵大",2022,"1班"),
                new Author("002","王二",2022,"2班"),
                new Author("003","张三",2022, "3班"),
                new Author("004","李四",2022,"4班"));
        return authorList;
    }
}
