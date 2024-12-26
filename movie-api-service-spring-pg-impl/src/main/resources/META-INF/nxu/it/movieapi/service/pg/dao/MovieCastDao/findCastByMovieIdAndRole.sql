SELECT t0.id,
       t0.english_name,
       t0.chinese_name,
       t0.avatar,
        t0.avatar_url,
       t1.act_as,
       t1.role_id
FROM public.staff t0
    INNER JOIN public.movie_cast t1
        ON t0.id=t1.staff_id
WHERE t1.movie_id=/* movieId */1291543 AND t1.role_id=/* role.getId()*/1