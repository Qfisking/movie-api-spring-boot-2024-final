SELECT id, name
FROM genre
WHERE id IN (SELECT genre_id FROM movie_genre WHERE movie_id =/* id */1291543)
ORDER BY id;