CREATE TABLE person (
  USER_ID INT NOT NULL,
  USER_NAME VARCHAR(20) NOT NULL,
  USER_EMAIL VARCHAR(50) NOT NULL,
  USER_PASSWORD VARCHAR(20) NOT NULL,
  USER_BANK VARCHAR(50) NOT NULL,
  PRIMARY KEY( USER_ID )
);

CREATE TABLE party (
  PARTY_ID INT NOT NULL,
  PARTY_NAME VARCHAR(20) NOT NULL,
  PARTY_ORGANIZER INT NOT NULL,
  PARTY_ADDRESS TEXT NOT NULL,
  PARTY_DEFAULT_MONEY INT,
  PRIMARY KEY( PARTY_ID ),
  CONSTRAINT fk_party_organizer FOREIGN KEY (PARTY_ORGANIZER) REFERENCES person (USER_ID)
);

create TABLE present (
  PRESENT_ID INT NOT NULL,
  PRESENT_NAME VARCHAR(20) not NULL,
  PRESENT_COST INT NOT NULL,
  PRESENT_FOR_PARTY INT NOT NULL,
  PRESENT_PICTURE_URL VARCHAR(50),
  PRESENT_CURRENT_MONEY_STATE INT DEFAULT 0,
  PRIMARY KEY( PRESENT_ID ),
  CONSTRAINT fk_present_for_party FOREIGN KEY (PRESENT_FOR_PARTY) REFERENCES party (PARTY_ID)
);

CREATE TABLE comment (
  COMMENT_ID INT NOT NULL,
  COMMENT_AUTHOR INT NOT NULL,
  COMMENT_FOR_PRESENT INT NOT NULL,
  COMMENT_TEXT TEXT NOT NULL,
  PRIMARY KEY( COMMENT_ID ),
  CONSTRAINT fk_comment_author FOREIGN KEY (COMMENT_AUTHOR) REFERENCES person (USER_ID),
  CONSTRAINT fk_comment_for_present FOREIGN KEY (COMMENT_FOR_PRESENT) REFERENCES present (PRESENT_ID)
);