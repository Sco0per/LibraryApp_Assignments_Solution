select * from users;

-- US01-1
   -- OPT 1
    select count(id) from users;           -- 138  -- ACTUAL
    select  count(distinct id) from users; -- 138  -- EXPECTED

   -- OPT 2
    select id from users;
    -- getAllColumnAsList --> List --> size  --> EXPECTED
    -- getAllColumnAsList --> Set  --> size  --> EXPECTED

    select count(*) from book_borrow
    where is_returned = 0;


select name from book_categories;

select books.name,isbn,year,author,books.description from books
where books.name like 'Clean Code';


select book_id,count(*) as popular from book_borrow
group by book_id
HAVING popular = (SELECT MAX(book_count) FROM (SELECT COUNT(*) AS book_count FROM book_borrow GROUP BY book_id) as borrow_count);


select * from book_categories;
select * from book_borrow;
select * from books;



SELECT bc.name
FROM book_borrow bb
         LEFT JOIN books b ON bb.book_id = b.id
         JOIN book_categories bc ON b.book_category_id = bc.id
GROUP BY bb.book_id
order by count(*) desc ;


HAVING COUNT(bc.name) = (SELECT MAX(book_count) FROM (SELECT COUNT(*) AS book_count FROM book_borrow GROUP BY book_id) AS borrow_counts);

select bc.name,count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;

select b.name as name,isbn,year,author as category
from books b
where b.name like 'The Scrum Field Guide';


select * from users
where email like 'student5@library';

select * from book_borrow;

select name from books
where name like 'Self Confidence';

SELECT b.name
FROM book_borrow bb
         JOIN books b ON bb.book_id = b.id
        JOIN users u ON bb.user_id = u.id
WHERE u.email like 'student5@library';