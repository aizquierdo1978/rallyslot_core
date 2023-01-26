CREATE TABLE IF NOT EXISTS PILOTO
(
   pilotoId IDENTITY  NOT NULL,
   pilotoName varchar(255) NOT NULL,
   creationDate timestamp NOT NULL,
   creationUser varchar(255) NOT NULL,
   modificationDate timestamp,
   modificationUser varchar(255),
   deleteDate timestamp,
   deleteUser varchar(255),
   primary key(pilotoId)
);