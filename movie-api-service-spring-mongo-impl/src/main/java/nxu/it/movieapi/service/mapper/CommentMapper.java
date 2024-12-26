package nxu.it.movieapi.service.mapper;

import nxu.it.api.model.Comment;
import nxu.it.movieapi.service.doc.CommentDoc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment fromDoc(CommentDoc commentDoc);
    List<Comment> fromDocList(List<CommentDoc> commentDocList);
}
