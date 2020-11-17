CREATE TABLE users
(
    id         BIGSERIAL    NOT NULL,
    version    BIGINT       NOT NULL DEFAULT (1),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),

    CONSTRAINT pk_users PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_users ON users (email);
ALTER TABLE users
    ADD CONSTRAINT uq_users UNIQUE USING INDEX idx_users;

CREATE TABLE general_logs
(
    id                      BIGSERIAL NOT NULL,
    version                 BIGINT    NOT NULL DEFAULT (1),
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    created_by              BIGINT,
    updated_by              BIGINT,
    date                    DATE      NOT NULL,
    lights_on_time          TIME,
    lights_off_time         TIME,
    diesel_fuel_consumption NUMERIC,
    diesel_fuel_retention   NUMERIC,
    heavy_fuel_consumption  NUMERIC,
    heavy_fuel_retention    NUMERIC,
    fresh_water_consumption NUMERIC,
    fresh_water_retention   NUMERIC,
    note                    VARCHAR(128),

    CONSTRAINT pk_general_logs PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_general_logs ON general_logs (date);
ALTER TABLE general_logs
    ADD CONSTRAINT uq_general_logs UNIQUE USING INDEX idx_general_logs;

CREATE TABLE weather_logs
(
    id                          BIGSERIAL NOT NULL,
    version                     BIGINT    NOT NULL DEFAULT (1),
    created_at                  TIMESTAMP,
    updated_at                  TIMESTAMP,
    created_by                  BIGINT,
    updated_by                  BIGINT,
    general_log_id              BIGINT    NOT NULL,
    time                        TIME      NOT NULL,
    wind_direction_and_velocity VARCHAR(16),
    sea                         VARCHAR(16),
    weather_and_visibility      VARCHAR(16),
    air_pressure                SMALLINT,
    air_temperature             NUMERIC,
    water_temperature           NUMERIC,
    cargo_holds                 SMALLINT,
    bildge_water_level          SMALLINT,

    CONSTRAINT pk_weather_logs PRIMARY KEY (id),
    CONSTRAINT fk_weather_logs_general_log_id FOREIGN KEY (general_log_id) REFERENCES general_logs (id)
);

CREATE UNIQUE INDEX idx_weather_logs ON weather_logs (general_log_id, time);
ALTER TABLE weather_logs
    ADD CONSTRAINT uq_weather_logs UNIQUE USING INDEX idx_weather_logs;

CREATE TABLE passage_logs
(
    id                      BIGSERIAL NOT NULL,
    version                 BIGINT    NOT NULL DEFAULT (1),
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    created_by              BIGINT,
    updated_by              BIGINT,
    general_log_id          BIGINT    NOT NULL,
    time                    TIME      NOT NULL,
    passage                 NUMERIC   NOT NULL,
    officer_of_the_watch_id BIGINT    NOT NULL,
    seamen_of_the_watch_id  BIGINT    NOT NULL,

    CONSTRAINT pk_passage_logs PRIMARY KEY (id),
    CONSTRAINT fk_passage_logs_general_log_id FOREIGN KEY (general_log_id) REFERENCES general_logs (id),
    CONSTRAINT fk_passage_logs_officer_of_the_watch_id FOREIGN KEY (officer_of_the_watch_id) REFERENCES users (id),
    CONSTRAINT fk_passage_logs_seamen_of_the_watch_id FOREIGN KEY (seamen_of_the_watch_id) REFERENCES users (id)
);

CREATE UNIQUE INDEX idx_passage_logs ON passage_logs (general_log_id, time);
ALTER TABLE passage_logs
    ADD CONSTRAINT uq_passage_logs UNIQUE USING INDEX idx_passage_logs;

CREATE TABLE tanks
(
    id          BIGSERIAL   NOT NULL,
    version     BIGINT      NOT NULL DEFAULT (1),
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    created_by  BIGINT,
    updated_by  BIGINT,
    name        VARCHAR(32) NOT NULL,
    description VARCHAR(128),

    CONSTRAINT pk_tanks PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_tanks ON tanks (name);
ALTER TABLE tanks
    ADD CONSTRAINT uq_tanks UNIQUE USING INDEX idx_tanks;

CREATE TABLE tanks_logs
(
    id             BIGSERIAL NOT NULL,
    version        BIGINT    NOT NULL DEFAULT (1),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    created_by     BIGINT,
    updated_by     BIGINT,
    general_log_id BIGINT    NOT NULL,
    time           TIME      NOT NULL,
    tank_id        BIGINT    NOT NULL,
    ballast_water  NUMERIC,
    fuel           NUMERIC,

    CONSTRAINT pk_tanks_logs PRIMARY KEY (id),
    CONSTRAINT fk_tanks_logs_general_log_id FOREIGN KEY (general_log_id) REFERENCES general_logs (id),
    CONSTRAINT fk_tanks_logs_tank_id FOREIGN KEY (tank_id) REFERENCES tanks (id)
);

CREATE UNIQUE INDEX idx_tanks_logs ON tanks_logs (general_log_id, time, tank_id);
ALTER TABLE tanks_logs
    ADD CONSTRAINT uq_tanks_logs UNIQUE USING INDEX idx_tanks_logs;

CREATE TABLE common_logs
(
    id             BIGSERIAL    NOT NULL,
    version        BIGINT       NOT NULL DEFAULT (1),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    created_by     BIGINT,
    updated_by     BIGINT,
    general_log_id BIGINT       NOT NULL,
    time           TIME         NOT NULL,
    location       VARCHAR(64),
    log            VARCHAR(256) NOT NULL,

    CONSTRAINT pk_common_logs PRIMARY KEY (id),
    CONSTRAINT fk_common_logs_general_log_id FOREIGN KEY (general_log_id) REFERENCES general_logs (id)
);

CREATE UNIQUE INDEX idx_common_logs ON common_logs (general_log_id, time);
ALTER TABLE common_logs
    ADD CONSTRAINT uq_common_logs UNIQUE USING INDEX idx_common_logs;