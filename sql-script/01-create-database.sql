DROP SCHEMA IF EXISTS poc CASCADE;

CREATE SCHEMA poc;

CREATE TABLE poc.rssegp (
  id              	BIGSERIAL PRIMARY KEY,
  publish_date      DATE NOT NULL,
  title  			TEXT NOT NULL,
  egp_url			TEXT NOT NULL
);

CREATE SEQUENCE poc.rssegp_seq
  start 1 increment 1;

CREATE TABLE poc.pdf_map (
	id BIGSERIAL PRIMARY KEY,
	file_name text NULL,
	file_path text NULL,
	file_link text NULL,
	record_by text NULL,
	file_comment text NULL,
	date_upload date NULL,
	date_update date NULL
)
WITH (
	OIDS=FALSE
) ;