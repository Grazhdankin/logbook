CREATE TABLE users
(
    id         BIGSERIAL   NOT NULL,
    version    BIGINT      NOT NULL DEFAULT (1),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    created_by VARCHAR(32),
    updated_by VARCHAR(32),
    user_name  VARCHAR(32) NOT NULL,
    full_name  VARCHAR(64),
    password   VARCHAR(255),
    enabled    BOOLEAN     NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_users PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_users ON users (user_name);
ALTER TABLE users
    ADD CONSTRAINT uq_users UNIQUE USING INDEX idx_users;

CREATE TABLE roles
(
    id   SMALLSERIAL NOT NULL,
    name VARCHAR(64) NOT NULL,

    CONSTRAINT pk_roles PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_roles ON roles (name);
ALTER TABLE roles
    ADD CONSTRAINT uq_roles UNIQUE USING INDEX idx_roles;

CREATE TABLE users_roles
(
    user_id SMALLINT NOT NULL,
    role_id SMALLINT NOT NULL,

    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_logs_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_logs_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER');
INSERT INTO roles (name)
VALUES ('ROLE_CREATOR');
INSERT INTO roles (name)
VALUES ('ROLE_EDITOR');
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');

INSERT INTO users (user_name, password)
VALUES ('admin', '$2a$10$dWzR6FNae1QpiuDoexeNv.AqTtDd4183jLg77ugAS90GqRG/TVmN6');
INSERT INTO users (user_name, password)
VALUES ('user', '$2a$10$AdxzaR./gA9tlivH/vY4T.7j5B7wcAy1IV1ykRqYZR2qbhTKDiWPq');

INSERT INTO users_roles
VALUES (1, 1);
INSERT INTO users_roles
VALUES (1, 2);
INSERT INTO users_roles
VALUES (1, 3);
INSERT INTO users_roles
VALUES (1, 4);
INSERT INTO users_roles
VALUES (2, 1);

CREATE TABLE general_logs
(
    id                      BIGSERIAL NOT NULL,
    version                 BIGINT    NOT NULL DEFAULT (1),
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    created_by              VARCHAR(32),
    updated_by              VARCHAR(32),
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
    created_by                  VARCHAR(32),
    updated_by                  VARCHAR(32),
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
    created_by              VARCHAR(32),
    updated_by              VARCHAR(32),
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
    created_by  VARCHAR(32),
    updated_by  VARCHAR(32),
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
    created_by     VARCHAR(32),
    updated_by     VARCHAR(32),
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
    created_by     VARCHAR(32),
    updated_by     VARCHAR(32),
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