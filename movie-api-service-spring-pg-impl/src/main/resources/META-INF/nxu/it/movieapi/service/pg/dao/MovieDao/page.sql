SELECT *
FROM movie
WHERE
    /*%if param!=null && @isNotBlank(param.getOriginalTitle())*/
        original_title ILIKE '%' || /* param.getOriginalTitle() */'' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getChineseTitle())*/
    AND chinese_title ILIKE '%' || /* param.getChineseTitle() */'天' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getRegion())*/
    AND region ILIKE'%' || /*param.getRegion()*/'大陆' || '%'
    /*%end*/
    /*%if param!=null && @isNotBlank(param.getLanguage())*/
    AND Language ILIKE '%' || /*param.getLanguage()*/'普通话' || '%'
    /*%end*/
    /*%if param!=null && param.getStartYear()!=null */
    AND year >= /*param.getStartYear()*/2010
    /*%end*/
    /*%if param!=null && param.getEndYear()!=null */
    AND year <= /*param.getEndYear()*/2099
    /*%end*/
    /*%if param!=null && param.getGenreIds()!=null && param.getGenreIds().size() != 0*/
    AND id IN(SELECT movie_id FROM public.movie_genre WHERE genre_id IN /*param.getGenreIds()*/(1,4))
    /*%end*/