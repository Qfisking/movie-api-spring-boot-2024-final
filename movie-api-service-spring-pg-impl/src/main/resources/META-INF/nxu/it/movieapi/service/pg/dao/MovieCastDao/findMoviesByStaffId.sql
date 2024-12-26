SELECT t0.id,
       t0.original_title,
       t0.chinese_title,
       t0.year,
       t0.region,
       t0.poster_url,
       t0.rating,
       t0.poster,
       t1.role_id
FROM movie t0
         INNER JOIN movie_cast t1
                    ON t0.id=t1.movie_id
WHERE t1.staff_id=/* staffId */1048026