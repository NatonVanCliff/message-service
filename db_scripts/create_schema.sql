CREATE TABLE ms_users
(
  id serial NOT NULL,
  username character varying(45) NOT NULL,
  password character varying(45) NOT NULL,
  roleid integer NOT NULL,
  firstname character varying(255) NOT NULL,
  lastname character varying(255) NOT NULL,
  email character varying(255),
  CONSTRAINT pk_ms_users PRIMARY KEY (id),
  CONSTRAINT uk_username UNIQUE (username)
);

CREATE TABLE ms_contacts
(
  contactid integer NOT NULL,
  userid integer NOT NULL,
  CONSTRAINT pk_ms_contacts PRIMARY KEY (contactid, userid),
  CONSTRAINT fk_userId FOREIGN KEY (userid)
      REFERENCES ms_users (id) ON DELETE CASCADE,
  CONSTRAINT fk_contactId FOREIGN KEY (contactid)
      REFERENCES ms_users (id) ON DELETE CASCADE
);

CREATE TABLE ms_messages
(
  id serial NOT NULL, 
  recipientId integer NOT NULL,
  senderId integer NOT NULL,
  subject character varying(255) NOT NULL,
  date timestamp without time zone,
  text character varying(255),
  CONSTRAINT pk_ms_messages PRIMARY KEY (id),
  CONSTRAINT fk_senderId FOREIGN KEY (senderId)
      REFERENCES ms_users (id) ON DELETE CASCADE,
  CONSTRAINT fk_recipientId FOREIGN KEY (recipientId)
      REFERENCES ms_users (id)ON DELETE CASCADE
);

INSERT INTO ms_users (id, username, password, roleid, firstname, lastname)
  VALUES (0, 'admin', 'admin', 1, 'Admin', 'Admin');


ALTER TABLE ms_users OWNER TO ms_user;
ALTER TABLE ms_contacts OWNER TO ms_user;
ALTER TABLE ms_messages OWNER TO ms_user;
