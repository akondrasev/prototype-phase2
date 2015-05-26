CREATE SEQUENCE person_user_id_seq;
CREATE SEQUENCE present_present_id_seq;
CREATE SEQUENCE party_party_id_seq;
CREATE SEQUENCE comment_comment_id_seq;
CREATE SEQUENCE news_id_seq;


CREATE TABLE person (
  USER_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('person_user_id_seq'),
  USER_NAME VARCHAR(20),
  USER_EMAIL VARCHAR(50),
  USER_PASSWORD VARCHAR(20),
  USER_BANK VARCHAR(50),
  PRIMARY KEY( USER_ID )
);

CREATE TABLE party (
  PARTY_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('party_party_id_seq'),
  PARTY_NAME VARCHAR(20),
  PARTY_ORGANIZER INT,
  PARTY_ADDRESS TEXT,
  PARTY_DEFAULT_MONEY INT,
  PARTY_IS_OPEN BOOL,
  PARTY_DATE VARCHAR(50),
  PRIMARY KEY( PARTY_ID ),
  CONSTRAINT fk_party_organizer FOREIGN KEY (PARTY_ORGANIZER) REFERENCES person (USER_ID)
);

create TABLE present (
  PRESENT_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('present_present_id_seq'),
  PRESENT_NAME VARCHAR(20),
  PRESENT_COST INT,
  PRESENT_FOR_PARTY INT,
  PRESENT_PICTURE_URL VARCHAR(500),
  PRIMARY KEY( PRESENT_ID ),
  CONSTRAINT fk_present_for_party FOREIGN KEY (PRESENT_FOR_PARTY) REFERENCES party (PARTY_ID)
);

CREATE TABLE comment (
  COMMENT_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('comment_comment_id_seq'),
  COMMENT_AUTHOR INT,
  COMMENT_FOR_PRESENT INT,
  COMMENT_TEXT TEXT,
  PRIMARY KEY( COMMENT_ID ),
  CONSTRAINT fk_comment_author FOREIGN KEY (COMMENT_AUTHOR) REFERENCES person (USER_ID),
  CONSTRAINT fk_comment_for_present FOREIGN KEY (COMMENT_FOR_PRESENT) REFERENCES present (PRESENT_ID)
);

CREATE TABLE vote (
  present_id INT,
  user_id int
);
CREATE TABLE guest (
  party_id INT,
  user_id int,
  state_id int,
  CONSTRAINT fk_party_invite FOREIGN KEY (party_id) REFERENCES party (PARTY_ID),
  CONSTRAINT fk_guset FOREIGN KEY (user_id) REFERENCES person (USER_ID),
  CONSTRAINT fk_guest_state FOREIGN KEY (state_id) REFERENCES guestState (state_id)
);
-- CREATE TABLE guestState ( DEPRECATED('not discussed')
--   state_id INT NOT NULL,
--   state_name VARCHAR(50),
--   PRIMARY KEY( state_id )
-- );

CREATE TABLE news (
  news_id NUMERIC(10,0) NOT NULL DEFAULT nextval('news_id_seq'),
  user_email VARCHAR(50),
  party_id INT
);