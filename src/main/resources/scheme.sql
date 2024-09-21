CREATE TABLE IF NOT EXISTS capabilities ( 
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL
);

CREATE TABLE  IF NOT EXISTS capability_technology (
    id SERIAL PRIMARY KEY,
    capability_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL
);