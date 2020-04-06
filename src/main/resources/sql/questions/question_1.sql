-- Question #2

SELECT
    le.person_id,
    re.person_id
FROM
    title$principals AS le INNER JOIN title$principals AS re ON le.title_id = re.title_id
WHERE
    le.category LIKE 'act%' AND re.category LIKE 'act%' AND le.person_id <> re.person_id;
