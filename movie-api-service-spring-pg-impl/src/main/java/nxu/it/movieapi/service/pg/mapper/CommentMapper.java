package nxu.it.movieapi.service.pg.mapper;

import nxu.it.api.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment fromEntity(CommentEntity commentEntity);
    List<Comment> fromEntityToList(List<CommentEntity> commentEntityList);
}
