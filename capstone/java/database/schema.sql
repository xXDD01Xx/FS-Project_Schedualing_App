BEGIN;

DROP TABLE IF EXISTS monthly_sched_items, monthly_schedule, baseline_sched_items, mods_changes, contract, project, phase_items, users, user_status;

DROP SEQUENCE IF EXISTS primary_sequence;

CREATE SEQUENCE  IF NOT EXISTS primary_sequence START WITH 10000 INCREMENT BY 1;

CREATE TABLE user_status(
	user_status_id SERIAL PRIMARY KEY,
	user_status_desc varchar(20)
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	user_status_id INTEGER REFERENCES user_status(user_status_id)
);

CREATE TABLE phase_items (
    id SERIAL NOT NULL,
    phase VARCHAR(20) NOT NULL,
    item_description VARCHAR(50) NOT NULL,
    CONSTRAINT phase_items_pkey PRIMARY KEY (id)
);

CREATE TABLE contract (
    id SERIAL NOT NULL,
    contract_name VARCHAR(50),
    CONSTRAINT contract_pkey PRIMARY KEY (id)
);

CREATE TABLE project (
    id SERIAL NOT NULL,
    project_name VARCHAR(100) NOT NULL,
    contract_id INTEGER REFERENCES contract (id) ON DELETE CASCADE,
    date_received date NOT NULL,
    budget BIGINT NOT NULL,
    last_modified TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    tasks_substantial INTEGER,
    tasks_construction INTEGER,
    CONSTRAINT project_pkey PRIMARY KEY (id)
);

CREATE TABLE mods_changes (
    id SERIAL NOT NULL,
    mod_co_name VARCHAR(20) NOT NULL,
    project_id INTEGER REFERENCES project (id) ON DELETE CASCADE,
    type VARCHAR(20),
    court_date date NOT NULL,
    schedule_impacted BOOLEAN NOT NULL,
    why_sched_impact TEXT,
	how_sched_impact TEXT,
	budget_impacted BOOLEAN NOT NULL,
	why_budgt_impact TEXT,
	how_budgt_impact TEXT,
	why_submit TEXT,
	why_two TEXT,
	why_three TEXT,
	why_four TEXT,
	why_five TEXT,
    approved BOOLEAN,
    approv_datetm TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT mods_changes_pkey PRIMARY KEY (id)
);

CREATE TABLE baseline_sched_items (
    id SERIAL NOT NULL,
    project_id INTEGER REFERENCES project (id) ON DELETE CASCADE,
    phase_item INTEGER REFERENCES phase_items (id),
    item_date date,
	item_tasks INTEGER,
    CONSTRAINT baseline_sched_items_pkey PRIMARY KEY (id)
);

CREATE TABLE monthly_schedule (
    id SERIAL NOT NULL,
    project_id INTEGER REFERENCES project (id) ON DELETE CASCADE,
    month_year date NOT NULL,
    schedule_notes TEXT,
    pct_complete INTEGER,
	sched_prod_idx DECIMAL,
    same_prev_month BOOLEAN NOT NULL,
    why_one TEXT,
    why_two TEXT,
    why_three TEXT,
    why_four TEXT,
    why_five TEXT,
    tasks_substantial INTEGER,
    tasks_construction INTEGER,
    CONSTRAINT monthly_schedule_pkey PRIMARY KEY (id),
	UNIQUE (project_id, month_year)
);

CREATE TABLE monthly_sched_items (
    id SERIAL NOT NULL,
    monthly_sched_id INTEGER REFERENCES monthly_schedule (id) ON DELETE CASCADE,
    phase_item INTEGER REFERENCES phase_items (id),
    item_date date,
	item_tasks INTEGER,
    CONSTRAINT monthly_sched_items_pkey PRIMARY KEY (id)
);

COMMIT;