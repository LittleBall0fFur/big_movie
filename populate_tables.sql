copy person     from 'C:/csv/person.csv'    (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (birth_year, death_year, professions));
copy title      from 'C:/csv/title.csv'     (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (start_year, end_year, runtime));
copy episode    from 'C:/csv/episode.csv'   (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (season_number, episode_number));

copy person$titles from 'C:/csv/person$titles.csv' (FORMAT csv, HEADER, NULL '\N');

copy title$rating       from 'C:/csv/title$rating.csv'          (FORMAT csv, HEADER, NULL '\N');
copy title$akas         from 'C:/csv/title$akas.csv'            (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (region, language, types, attributes, is_original));
copy title$writers      from 'C:/csv/title$writers.csv'         (FORMAT csv, HEADER, NULL '\N');
copy title$principals   from 'C:/csv/title$principals_0.csv'    (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (job, characters));
copy title$principals   from 'C:/csv/title$principals_1.csv'    (FORMAT csv, HEADER, NULL '\N', FORCE_NULL (job, characters));
copy title$directors    from 'C:/csv/title$directors.csv'       (FORMAT csv, HEADER, NULL '\N');
copy title$genres       from 'C:/csv/title$genres.csv'          (FORMAT csv, HEADER, NULL '\N');
