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
select p.id as project_id, phase_item, item_date, item_tasks, '2000-01-01' as month_year, pi.phase, pi.item_description
from baseline_sched_items b
join project p ON p.id = b.project_id
join phase_items pi ON pi.id = b.phase_item
union all
select p.id, phase_item, item_date, item_tasks, month_year, pi.phase, pi.item_description
from project p
join monthly_schedule m on p.id=m.project_id
join monthly_sched_items mi ON mi.monthly_sched_id = m.id
join phase_items pi ON pi.id = mi.phase_item;