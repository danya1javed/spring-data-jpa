-- select memid, surname, firstname, joindate
-- from cd.members
-- where joindate >= '2012-09-01'::date

-- select DISTINCT surname from cd.members
-- order by surname limit 10;

-- UNION, UNION ALL, INTERSECT, EXCEPT
-- select surname from cd.members union
-- select name as surname from cd.facilities

-- select joindate as latest from cd.members
-- order by joindate desc limit 1;
-- select max(joindate) as latest from cd.members;

-- select firstname, surname, joindate from cd.members
-- where joindate = (select max(joindate) from cd.members);

-- select b.starttime from cd.bookings as b
-- join cd.members as m on b.memid = m.memid
-- where m.firstname = 'David' and m.surname = 'Farrell';

-- select b.starttime as start, f.name from cd.bookings b
-- join cd.facilities f on b.facid = f.facid
-- where b.starttime >= '2012-09-21'::date and b.starttime < '2012-09-22'::date and f.name like '%Tennis Court%'
-- order by b.starttime;

-- select DISTINCT r.firstname, r.surname from cd.members m
-- join cd.members r on m.memid = r.recommendedby
-- order by surname,firstname;

-- select distinct recs.firstname as firstname, recs.surname as surname
-- 	from
-- 		cd.members mems
-- 		inner join cd.members recs
-- 			on recs.memid = mems.recommendedby
-- order by surname, firstname;

-- select mem.firstname as memfname, mem.surname as memsname, rec.firstname as recfname, rec.surname as recsname
-- from cd.members mem
-- left outer join cd.members rec on mem.recommendedby = rec.memid
-- order by mem.surname, mem.firstname;

-- select distinct mem.firstname || ' ' || mem.surname as member, fac.name as facility
-- from cd.members mem
-- join cd.bookings b on b.memid = mem.memid
-- join cd.facilities fac on fac.facid = b.facid
-- where fac.name in ('Tennis Court 1', 'Tennis Court 2')
-- order by member, facility;

-- select fac.facid, fac.name, round(sum(b.slots) / 2.0, 2) as booked from cd.facilities fac
-- inner join cd.bookings b on b.facid = fac.facid
-- group by fac.facid
-- order by fac.facid;

-- select fac.facid, fac.name from cd.facilities fac
-- inner join cd.bookings b on b.facid = fac.facid

-- order by fac.facid;

-- select generate_series('2020-01-01'::Date, '2021-01-01'::Date, interval '1 Month');



-- With member_join_after_2012_march (mid, firstname, joiningdate) as (
-- 	select memid, firstname, joindate from cd.members
-- 	where joindate >= '2012-03-01'::date
-- )
-- select mid, firstname, joiningdate from member_join_after_2012_march;

-- RECURSIVE CTE
-- WITH RECURSIVE  cte
-- AS     (SELECT 1 AS n
--         UNION ALL
--         SELECT n + 1
--         FROM   ctehttp://127.0.0.1:50902/datagrid/panel/1524090?is_query_tool=true&sgid=1&sid=3&server_type=pg&did=25235#
-- --         WHERE  n < 50
--        )
-- SELECT n
-- FROM   cte;






-- select * from cd.members
-- where memid = 5

-- My solution
-- WITH RECURSIVE getRecommender AS (
--  	select recommendedby, memid, firstname, surname from cd.members
--  	where memid = 27
--  	UNION
--  	select m.recommendedby, m.memid, m.firstname, m.surname from cd.members m
--  	inner join getRecommender gr on m.memid = gr.recommendedby
-- ) select memid as recommender, firstname, surname from getRecommender offset 1;

-- There solution
-- with recursive recommenders(recommender) as (
-- 	select recommendedby from cd.members where memid = 27
-- 	union all
-- 	select mems.recommendedby
-- 		from recommenders recs
-- 		inner join cd.members mems
-- 			on mems.memid = recs.recommender
-- )
-- select recs.recommender, mems.firstname, mems.surname
-- 	from recommenders recs
-- 	inner join cd.members mems
-- 		on recs.recommender = mems.memid
-- order by memid desc







