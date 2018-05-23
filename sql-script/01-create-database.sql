DROP SCHEMA IF EXISTS poc CASCADE;

CREATE SCHEMA poc;

CREATE TABLE poc.rssegp (
	id bigserial NOT NULL,
	publish_date date NOT NULL,
	title text NOT NULL,
	egp_url text NOT NULL,
	deptid text NOT NULL,
	deptsubid text NOT NULL,
	anouncetype text NOT NULL,
	methodid text NOT NULL,
	CONSTRAINT rssegp_pkey PRIMARY KEY (id),
	CONSTRAINT rssegp_unique1 UNIQUE (title, publish_date)
)
WITH (
	OIDS=FALSE
) ;

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

CREATE TABLE poc.announce_type (
  param  			TEXT PRIMARY KEY,
  description		TEXT NOT NULL
)
WITH (
	OIDS=FALSE
);

CREATE TABLE poc.method_id (
  param  			TEXT PRIMARY KEY,
  description		TEXT NOT NULL
)
WITH (
	OIDS=FALSE
);

INSERT INTO poc.announce_type values ('P0', 'แผนการจัดซื้อจัดจ้าง');
INSERT INTO poc.announce_type values ('15', 'ประกาศราคากลาง');
INSERT INTO poc.announce_type values ('B0', 'ร่างเอกสารประกวดราคา (e-Bidding) และร่างเอกสารซื้อหรือจ้างด้วยวิธีสอบราคา');
INSERT INTO poc.announce_type values ('D0', 'ประกาศเชิญชวน');
INSERT INTO poc.announce_type values ('W0', 'ประกาศรายชื่อผู้ชนะการเสนอราคา / ประกาศผู้ได้รับการคัดเลือก');
INSERT INTO poc.announce_type values ('D1', 'ยกเลิกประกาศเชิญชวน');
INSERT INTO poc.announce_type values ('W1', 'ยกเลิกประกาศรายชื่อผู้ชนะการเสนอราคา / ประกาศผู้ได้รับการคัดเลือก');
INSERT INTO poc.announce_type values ('D2', 'เปลี่ยนแปลงประกาศเชิญชวน');
INSERT INTO poc.announce_type values ('W2', 'เปลี่ยนแปลงประกาศรายชื่อผู้ชนะการเสนอราคา');

INSERT INTO poc.method_id values ('02', 'สอบราคา');
INSERT INTO poc.method_id values ('15', 'e-market');
INSERT INTO poc.method_id values ('16', 'e-bidding');
INSERT INTO poc.method_id values ('18', 'คัดเลือก');
INSERT INTO poc.method_id values ('19', 'เฉพาะเจาะจง');
INSERT INTO poc.method_id values ('20', 'จ้างที่ปรึกษาโดยวิธีประกาศเชิญชวนทั่วไป');
INSERT INTO poc.method_id values ('21', 'จ้างที่ปรึกษาโดยวิธีคัดเลือก');
INSERT INTO poc.method_id values ('22', 'จ้างที่ปรึกษาโดยวิธีเฉพาะเจาะจง');
INSERT INTO poc.method_id values ('23', 'จ้างออกแบบหรือควบคุมงานก่อสร้างโดยวิธีประกาศเชิญชวนทั่วไป');
INSERT INTO poc.method_id values ('24', 'จ้างออกแบบหรือควบคุมงานก่อสร้างโดยวิธีคัดเลือก');
INSERT INTO poc.method_id values ('25', 'จ้างออกแบบหรือควบคุมงานก่อสร้างโดยวิธีเฉพาะเจาะจง');
INSERT INTO poc.method_id values ('26', 'จ้างออกแบบหรือควบคุมงานก่อสร้างโดยวิธีประกวดแบบ');

commit;