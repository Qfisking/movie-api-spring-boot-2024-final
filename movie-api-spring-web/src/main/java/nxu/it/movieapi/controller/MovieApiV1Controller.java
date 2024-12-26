package nxu.it.movieapi.controller;


import nxu.it.api.common.param.PageQueryParam;
import nxu.it.api.common.result.ApiResult;
import nxu.it.api.common.result.Pageable;
import nxu.it.api.common.result.ResponseType;
import nxu.it.api.model.*;
import nxu.it.api.service.CommentService;
import nxu.it.api.service.GenreService;
import nxu.it.api.service.MovieService;
import nxu.it.api.service.StaffService;
import nxu.it.api.service.param.MoviePageQueryParam;
import nxu.it.api.service.param.StaffPageQueryParam;
import nxu.it.movieapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movie/api/v1")
public class MovieApiV1Controller {
    @Autowired
    AuthorService authorServiceImpl;
    @Autowired
    GenreService genreServiceImpl;
    @Autowired
    MovieService movieServiceImpl;
    @Autowired
    CommentService commentServiceImpl;
    @Autowired
    StaffService staffServiceImpl;



    @GetMapping("/author")
    public ApiResult<List<Author>> getAuthorList() {
        return ApiResult.success(authorServiceImpl.findAll());
    }

    @GetMapping("/genres")
    public ApiResult<List<Genre>> getGenreList() {
        return ApiResult.success(genreServiceImpl.findAll());
    }

    @PostMapping("/movies")
    public ApiResult<Pageable<SimpleMovie>> getMoviePageData(@RequestBody MoviePageQueryParam param) {
        return ApiResult.success(movieServiceImpl.page(param));
    }

    @GetMapping("/movie/{movieId}")
    public ApiResult<Movie> getMovieDetails(@PathVariable Integer movieId) {
        Optional<Movie> movieOptional = movieServiceImpl.findById(movieId);
        if (movieOptional.isPresent()){
            return ApiResult.success(movieOptional.get());
        }
        return ApiResult.fail(ResponseType.NOT_FOUND);
    }

    @PostMapping("/movie/{movieId}/comments")
    public ApiResult<List<Comment>> getMovieComments(@PathVariable Integer movieId,
                                                     @RequestBody MoviePageQueryParam param) {
        Pageable<Comment> commentPage = commentServiceImpl.pageByMovieId(movieId, param);
        List<Comment> commentList = commentPage.getList();
        if (!commentList.isEmpty()){
            return ApiResult.success(commentList);
        }
        return ApiResult.fail(ResponseType.NOT_FOUND);
    }

    @PostMapping("/staffs")
    public ApiResult<Pageable<SimpleStaff>> getStaffPageData(@RequestBody StaffPageQueryParam param) {
        return ApiResult.success(staffServiceImpl.page(param));
    }

    @GetMapping("staff/{staffId}")
    public ApiResult<Staff> getStaffDetails(@PathVariable Integer staffId) {
        Optional<Staff> staffOptional = staffServiceImpl.findById(staffId);
        if (staffOptional.isPresent()) {
            return ApiResult.success(staffOptional.get());
        }
        return ApiResult.fail(ResponseType.NOT_FOUND);
    }
}
