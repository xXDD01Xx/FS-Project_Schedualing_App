BEGIN;
DROP VIEW IF EXISTS current_items_vw, baseline_items_vw, monthly_items_vw, all_items_vw, schedule_vw;

CREATE OR REPLACE VIEW baseline_items_vw AS 
(SELECT p.project_name, p.id, pi.phase, pi.item_description, bi.item_date, bi.item_tasks, bi.phase_item
FROM project p
JOIN baseline_sched_items bi ON bi.project_id = p.id
JOIN phase_items pi ON pi.id = bi.phase_item
ORDER BY p.project_name, bi.item_date);

CREATE OR REPLACE VIEW monthly_items_vw AS 
(SELECT p.project_name, p.id, m.month_year, pi.phase, pi.item_description, mi.item_date, mi.phase_item
FROM project p
JOIN monthly_schedule m ON m.project_id = p.id
JOIN monthly_sched_items mi ON mi.monthly_sched_id = m.id
JOIN phase_items pi ON pi.id = mi.phase_item
ORDER BY p.project_name, m.month_year, mi.item_date);

CREATE OR REPLACE VIEW all_items_vw AS
select c.contract_name, p.contract_id, p.project_name, p.id as project_id, phase_item, item_date, item_tasks, '2000-01-01' as month_year, pi.phase, pi.item_description
from baseline_sched_items bi
join project p ON p.id = bi.project_id
join contract c ON c.id = p.contract_id
join phase_items pi ON pi.id = bi.phase_item
union all
select c.contract_name, p.contract_id, p.project_name, m.project_id, phase_item, item_date, item_tasks, month_year, pi.phase, pi.item_description
from project p
join contract c ON c.id = p.contract_id
join monthly_schedule m on p.id=m.project_id
join monthly_sched_items mi ON mi.monthly_sched_id = m.id
join phase_items pi ON pi.id = mi.phase_item;

CREATE OR REPLACE VIEW schedule_vw AS
select contract_name, contract_id, project_name, project_id, phase, min(item_date) as start_dt, max(item_date) as end_dt, max(item_date)-min(item_date)+1 as duration_days
from all_items_vw a
where month_year = (select max(month_year) from all_items_vw sq where sq.project_id=a.project_id and sq.phase_item=a.phase_item)
and phase != 'TNR Hold'
group by contract_name, contract_id, project_name, project_id, phase
order by contract_name, contract_id, project_name, project_id, (case phase when 'Design' then 1 when 'Pre-Construction' then 2 when 'Construction' then 3 else 4 end);

CREATE OR REPLACE VIEW current_items_vw AS
WITH current_items AS (SELECT * 
FROM all_items_vw a
WHERE month_year = (SELECT MAX(month_year) FROM all_items_vw sq WHERE sq.project_id=a.project_id and sq.phase_item=a.phase_item)
AND item_date IS NOT NULL
ORDER BY project_id, phase_item)
SELECT *
FROM current_items c
WHERE item_date=(SELECT max(item_date) FROM current_items sq WHERE sq.project_id=c.project_id AND item_date<=current_date);

COMMIT;