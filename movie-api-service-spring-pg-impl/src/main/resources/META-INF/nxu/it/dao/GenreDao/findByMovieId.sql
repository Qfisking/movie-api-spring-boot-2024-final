SELECT id, name
FROM public.genre
WHERE id IN
(SELECT t2.id FROM public.movie_genre t1
       INNER JOIN public.genre t2 ON t1.genre_id = t2.id
       WHERE t1.movie_id = /* movieId */1291543
) ORDER BY id