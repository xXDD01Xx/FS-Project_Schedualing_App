begin;
insert into monthly_schedule
(id, project_id, month_year, schedule_notes, pct_complete)
values
(1,1,'2022-09-01','Test notes', 51);

insert into monthly_sched_items
(monthly_sched_id, phase_item, item_date, item_tasks)
values
(1,1,'2022-01-01',1),
(1,6,'2022-01-31',2),
(1,7,'2022-02-01',3),
(1,12,'2022-02-28',4),
(1,13,'2022-03-01',5),
(1,17,'2022-03-31',6);

insert into baseline_sched_items
(project_id, phase_item, item_date, item_tasks)
values
(2,1,'2022-01-01',1),
(2,6,'2022-01-31',2),
(2,7,'2022-02-01',3),
(2,12,'2022-02-28',4),
(2,13,'2022-03-01',5),
(2,17,'2022-03-31',6);
insert into baseline_sched_items
(project_id, phase_item)
values
(2,2),
(2,3),
(2,4),
(2,5),
(2,8),
(2,9),
(2,10),
(2,11),
(2,14),
(2,15),
(2,16),
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15),
(1,16),
(1,17);
commit;