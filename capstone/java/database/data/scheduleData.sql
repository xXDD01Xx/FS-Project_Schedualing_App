begin;
truncate baseline_sched_items, monthly_sched_items, monthly_schedule;
insert into monthly_schedule
(id, project_id, month_year, schedule_notes, pct_complete, same_prev_month)
values
(1,1,'2022-09-01','Test notes', 51, TRUE);

insert into monthly_sched_items
(monthly_sched_id, phase_item, item_date, item_tasks)
values
(1,1,'2022-09-01',1),
(1,6,'2022-09-30',2),
(1,7,'2022-10-01',3),
(1,12,'2022-10-28',4),
(1,13,'2022-10-29',5),
(1,17,'2022-12-31',6);

insert into baseline_sched_items
(project_id, phase_item, item_date, item_tasks)
values
(1,1,'2022-09-01',1),
(1,6,'2022-09-30',2),
(1,7,'2022-10-01',3),
(1,12,'2022-10-28',4),
(1,13,'2022-10-29',5),
(1,17,'2023-01-31',6),
(2,1,'2022-09-01',1),
(2,6,'2022-09-30',2),
(2,7,'2022-10-01',3),
(2,12,'2022-10-31',4),
(2,13,'2022-11-01',5),
(2,17,'2022-12-31',6);

insert into baseline_sched_items
(project_id, phase_item)
select p.id, pi.id
from project p, phase_items pi
group by p.id, pi.id
having (select count(*) from baseline_sched_items b where b.project_id=p.id and b.phase_item=pi.id)<1
order by p.id, pi.id;
commit;