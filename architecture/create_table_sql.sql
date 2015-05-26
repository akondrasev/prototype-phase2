CREATE SEQUENCE person_user_id_seq;
CREATE SEQUENCE present_present_id_seq;
CREATE SEQUENCE party_party_id_seq;
CREATE SEQUENCE comment_comment_id_seq;
CREATE SEQUENCE news_id_seq;


CREATE TABLE person (
  USER_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('person_user_id_seq'),
  USER_NAME VARCHAR(20) NOT NULL,
  USER_EMAIL VARCHAR(50) NOT NULL,
  USER_PASSWORD VARCHAR(20) NOT NULL,
  USER_BANK VARCHAR(50) NOT NULL,
  PRIMARY KEY( USER_ID )
);

CREATE TABLE party (
  PARTY_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('party_party_id_seq'),
  PARTY_NAME VARCHAR(20) NOT NULL,
  PARTY_ORGANIZER INT NOT NULL,
  PARTY_ADDRESS TEXT NOT NULL,
  PARTY_DEFAULT_MONEY INT,
  PARTY_IS_OPEN BOOL,
  PARTY_DATE VARCHAR(30),
  PRIMARY KEY( PARTY_ID ),
  CONSTRAINT fk_party_organizer FOREIGN KEY (PARTY_ORGANIZER) REFERENCES person (USER_ID)
);

create TABLE present (
  PRESENT_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('present_present_id_seq'),
  PRESENT_NAME VARCHAR(20) not NULL,
  PRESENT_COST INT NOT NULL,
  PRESENT_FOR_PARTY INT NOT NULL,
  PRESENT_PICTURE_URL VARCHAR(500),
  PRIMARY KEY( PRESENT_ID ),
  CONSTRAINT fk_present_for_party FOREIGN KEY (PRESENT_FOR_PARTY) REFERENCES party (PARTY_ID)
);

CREATE TABLE comment (
  COMMENT_ID NUMERIC(10,0) NOT NULL DEFAULT nextval('comment_comment_id_seq'),
  COMMENT_AUTHOR INT NOT NULL,
  COMMENT_FOR_PRESENT INT NOT NULL,
  COMMENT_TEXT TEXT NOT NULL,
  PRIMARY KEY( COMMENT_ID ),
  CONSTRAINT fk_comment_author FOREIGN KEY (COMMENT_AUTHOR) REFERENCES person (USER_ID),
  CONSTRAINT fk_comment_for_present FOREIGN KEY (COMMENT_FOR_PRESENT) REFERENCES present (PRESENT_ID)
);

CREATE TABLE vote (
  present_id INT NOT NULL,
  user_id int not null
);
CREATE TABLE guest (
  party_id INT NOT NULL,
  user_id int not null,
  state_id int not null,
  CONSTRAINT fk_party_invite FOREIGN KEY (party_id) REFERENCES party (PARTY_ID),
  CONSTRAINT fk_guset FOREIGN KEY (user_id) REFERENCES person (USER_ID),
  CONSTRAINT fk_guest_state FOREIGN KEY (state_id) REFERENCES guestState (state_id)
);
CREATE TABLE guestState (
  state_id INT NOT NULL,
  state_name VARCHAR(50),
  PRIMARY KEY( state_id )
);

CREATE TABLE news (
  news_id NUMERIC(10,0) NOT NULL DEFAULT nextval('news_id_seq'),
  user_email VARCHAR(50) NOT NULL,
  party_id INT NOT NULL
);