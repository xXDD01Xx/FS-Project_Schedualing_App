BEGIN;

CREATE SEQUENCE  IF NOT EXISTS primary_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE phase_items (
    id BIGINT NOT NULL,
    phase VARCHAR(20),
    item_description VARCHAR(50),
    CONSTRAINT phase_items_pkey PRIMARY KEY (id)
);

CREATE TABLE project (
    id BIGINT NOT NULL,
    project_name VARCHAR(50),
    contract_id INTEGER,
    date_received date,
    budget BIGINT,
    last_modified TIMESTAMP WITHOUT TIME ZONE,
    tasks_substantial INTEGER,
    tasks_construction INTEGER,
    contract_project_id BIGINT,
    CONSTRAINT project_pkey PRIMARY KEY (id)
);

CREATE TABLE contract (
    id BIGINT NOT NULL,
    contract_name VARCHAR(50),
    CONSTRAINT contract_pkey PRIMARY KEY (id)
);

CREATE TABLE mods_changes (
    id BIGINT NOT NULL,
    mod_co_name VARCHAR(20),
    project_id INTEGER,
    type VARCHAR(20),
    court_date date,
    schedule_impacted BOOLEAN,
    budget_impacted BOOLEAN,
    why VARCHAR(255),
    approved BOOLEAN,
    approv_datetm TIMESTAMP WITHOUT TIME ZONE,
    project_mods_id BIGINT,
    CONSTRAINT mods_changes_pkey PRIMARY KEY (id)
);

CREATE TABLE baseline_sched_items (
    id BIGINT NOT NULL,
    project_id INTEGER,
    phase_item INTEGER,
    item_date date,
    project_baseline_id BIGINT,
    baseline_items_id BIGINT,
    CONSTRAINT baseline_sched_items_pkey PRIMARY KEY (id)
);

CREATE TABLE monthly_schedule (
    id BIGINT NOT NULL,
    project_id INTEGER,
    month_year date,
    schedule_notes VARCHAR(255),
    pct_complete INTEGER,
    same_prev_month BOOLEAN,
    why_one VARCHAR(255),
    why_two VARCHAR(255),
    why_three VARCHAR(255),
    why_four VARCHAR(255),
    why_five VARCHAR(255),
    tasks_substantial INTEGER,
    tasks_construction INTEGER,
    project_monthly_id BIGINT,
    CONSTRAINT monthly_schedule_pkey PRIMARY KEY (id)
);

CREATE TABLE monthly_sched_items (
    id BIGINT NOT NULL,
    monthly_sched_id INTEGER,
    phase_item INTEGER,
    item_date date,
    monthly_items_id BIGINT,
    monthly_sched_items_id BIGINT,
    CONSTRAINT monthly_sched_items_pkey PRIMARY KEY (id)
);

ALTER TABLE project ADD CONSTRAINT fk_project_contract_project_id FOREIGN KEY (contract_project_id) REFERENCES contract (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE mods_changes ADD CONSTRAINT fk_mods_changes_project_mods_id FOREIGN KEY (project_mods_id) REFERENCES project (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE baseline_sched_items ADD CONSTRAINT fk_baseline_sched_items_project_baseline_id FOREIGN KEY (project_baseline_id) REFERENCES project (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE baseline_sched_items ADD CONSTRAINT fk_baseline_sched_items_baseline_items_id FOREIGN KEY (baseline_items_id) REFERENCES phase_items (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE monthly_schedule ADD CONSTRAINT fk_monthly_schedule_project_monthly_id FOREIGN KEY (project_monthly_id) REFERENCES project (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE monthly_sched_items ADD CONSTRAINT fk_monthly_sched_items_monthly_items_id FOREIGN KEY (monthly_items_id) REFERENCES phase_items (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE monthly_sched_items ADD CONSTRAINT fk_monthly_sched_items_monthly_sched_items_id FOREIGN KEY (monthly_sched_items_id) REFERENCES monthly_schedule (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

COMMIT;