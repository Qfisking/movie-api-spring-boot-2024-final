package nxu.it.movieapi.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import nxu.it.api.common.result.ApiResult;
import nxu.it.api.model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie/api/v1")
public class MovieApiV1Controller {
    @ApiResponse(responseCode = "200", description = "成功获取作者列表",
            content = @Content(schema = @Schema(implementation = AuthorListApiResult.class)))
    @GetMapping("/authors")

    public ApiResult<List<Author>> authors() {
        var authors = List.of(
                new Author("001", "Jack"),
                new Author("002", "John")
        );
        return ApiResult.success(authors);
    }
}
