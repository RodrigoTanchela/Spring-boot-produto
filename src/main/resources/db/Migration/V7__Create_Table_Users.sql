CREATE TABLE IF NOT EXISTS tbusuarios (
  id serial PRIMARY KEY,
  username varchar(255) UNIQUE,
  full_name varchar(255),
  password varchar(255),
  account_non_expired boolean,
  account_non_locked boolean,
  credentials_non_expired boolean,
  enabled boolean
);
