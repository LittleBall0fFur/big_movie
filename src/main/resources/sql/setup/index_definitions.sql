
-- person indices
CREATE INDEX name_index ON public.person USING hash
    (name);

-- title indices
CREATE INDEX start_year_index ON public.title USING btree
    (start_year);
