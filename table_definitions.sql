CREATE TABLE IF NOT EXISTS person (
	id char(10) NOT NULL,
	name varchar(255) NOT NULL,
	birth_year int,
	death_year int,
	professions varchar(128),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS title (
	id char(10) NOT NULL,
	type varchar(32) NOT NULL,
	primary_title varchar(512) NOT NULL,
	original_title varchar(512) NOT NULL,
	is_adult boolean NOT NULL,
	start_year int,
	end_year int,
	runtime int,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person$titles (
	person_id char(10) NOT NULL,
	title_id char(10) NOT NULL,
	PRIMARY KEY (person_id, title_id),
	FOREIGN KEY (person_id)
		REFERENCES person(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS episode (
	id char(10) NOT NULL,
	parent_id char(10) NOT NULL,
	season_number int,
	episode_number int,
	PRIMARY KEY (id),
	FOREIGN KEY (id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (parent_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS title$rating (
	title_id char(10) NOT NULL,
	average_rating real NOT NULL,
	votes int NOT NULL,
	PRIMARY KEY (title_id)
);

CREATE TABLE IF NOT EXISTS title$akas (
	title_id char(10) NOT NULL,
	ordering int NOT NULL,
	title varchar(1024) NOT NULL,
	region varchar(16),
	language varchar(16),
	types varchar(32),
	attributes varchar(128),
	is_original boolean,
	PRIMARY KEY (title_id, ordering),
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS title$writers (
	title_id char(10) NOT NULL,
	person_id char(10) NOT NULL,
	PRIMARY KEY (title_id, person_id),
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (person_id)
		REFERENCES person(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS title$principals (
	title_id char(10) NOT NULL,
	ordering int NOT NULL,
	person_id char(10) NOT NULL,
	category varchar(32) NOT NULL,
	job varchar(512),
	characters varchar(512),
	PRIMARY KEY (title_id, person_id),
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (person_id)
		REFERENCES person(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS title$directors (
	title_id char(10) NOT NULL,
	person_id char(10) NOT NULL,
	PRIMARY KEY (title_id, person_id),
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (person_id)
		REFERENCES person(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS title$genres (
	title_id char(10) NOT NULL,
	genre varchar(32) NOT NULL,
	PRIMARY KEY (title_id, genre),
	FOREIGN KEY (title_id)
		REFERENCES title(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
