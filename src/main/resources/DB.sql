use Kinder_Base;

drop table if exists Matches;
drop table if exists Messages;
drop table if exists Chat;
drop table if exists Hobbies;
drop table if exists Images;
drop table if exists Likes;
drop table if exists Premium_Users;
drop table if exists User;

CREATE TABLE User (
                      User_id          INT NOT NULL AUTO_INCREMENT,
                      Mail             VARCHAR(50) NOT NULL,
                      First_Name       VARCHAR(20) NOT NULL,
                      Birth_Date       DATE    NOT NULL,
                      City             VARCHAR(20) NOT NULL    ,
                      Gender           VARCHAR(20) NOT NULL   ,
                      Show_Gender       INT   NOT NULL ,
                      Preference       VARCHAR(30) NOT NULL,

                      Orientation      VARCHAR(30) NULL    ,

                      Bio              VARCHAR(200) NULL    ,
                      Horoscope        VARCHAR(15) NULL    ,
                      Company          VARCHAR(15) NULL    ,
                      Job              VARCHAR(15) NULL    ,
                      School           VARCHAR(15) NULL    ,

                      Min_Age          INT     NULL    ,
                      Max_Age          INT     NULL    ,

                      Registration_Date DATETiME    NOT NULL  DEFAULT   CURRENT_TIMESTAMP,

                      Show_Active       INT   NULL ,
                      Last_Session      DATE  NULL,

                      Hided             INT   NULL default 0,
                      Role              VARCHAR(20)   NOT NULL ,
                      Show_To_Liked     INT NULL default 0,
                      Balance           INT     NULL  DEFAULT 100 ,


                      PRIMARY KEY (User_id) );

CREATE TABLE Chat (
                      Chat_Id INT NOT NULL AUTO_INCREMENT,
                      PRIMARY KEY (Chat_Id) );

CREATE TABLE Hobbies (
                         Hobby_id   INT     NOT NULL AUTO_INCREMENT,
                         User_id     INT     NOT NULL,
                         Hobby_Name VARCHAR(20) NOT NULL,
                         primary key (Hobby_Id) );

ALTER TABLE Hobbies   ADD CONSTRAINT FK_User_TO_Hobbies     FOREIGN KEY (User_id)     REFERENCES User (User_id);

CREATE TABLE Images (
                        Image_Id  INT     NOT NULL AUTO_INCREMENT,
                        User_id   INT     NOT NULL,
                        Image_Url VARCHAR(100) NOT NULL,
                        IsProfile INT  NULL,
                        Date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        primary key (Image_Id) );

ALTER TABLE Images   ADD CONSTRAINT FK_User_TO_Images     FOREIGN KEY (User_id)     REFERENCES User (User_id);

CREATE TABLE Likes (
                       Like_Id INT         NOT NULL AUTO_INCREMENT,
                       Status  VARCHAR(20) NOT NULL,
                       User_id_1 INT     NOT NULL,
                       User_id_2 INT     NOT NULL,
                       Date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        primary key (Like_Id));

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes     FOREIGN KEY (User_id_1)     REFERENCES User (User_id);

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes1     FOREIGN KEY (User_id_2)     REFERENCES User (User_id);

CREATE TABLE Matches (
                         User_id_1  INT  NOT NULL,
                         User_id_2  INT  NOT NULL,
                         Match_Id INT  NOT NULL auto_increment,
                         Unmatch   INT  NULL    ,
                         Date     DATETIME NULL  DEFAULT CURRENT_TIMESTAMP   ,
                         Chat_Id  INT  NOT NULL,   primary key(Match_Id) );

ALTER TABLE Matches   ADD CONSTRAINT FK_User_TO_Matches     FOREIGN KEY (User_id_1)     REFERENCES User (User_id);

ALTER TABLE Matches   ADD CONSTRAINT FK_User_TO_Matches1     FOREIGN KEY (User_id_2)     REFERENCES User (User_id);

ALTER TABLE Matches   ADD CONSTRAINT FK_Chat_TO_Matches     FOREIGN KEY (Chat_Id)     REFERENCES Chat (Chat_Id);

CREATE TABLE Messages (
                          Chat_Id      INT     NOT NULL,
                          Message_Id   INT     NOT NULL,
                          Message_Text VARCHAR(500) NOT NULL,
                          Date         DATE    NOT NULL,
                          User_id      INT     NOT NULL,
                          primary key (Message_Id) );

ALTER TABLE Messages   ADD CONSTRAINT FK_Chat_TO_Messages     FOREIGN KEY (Chat_Id)     REFERENCES Chat (Chat_Id);

ALTER TABLE Messages   ADD CONSTRAINT FK_User_TO_Messages     FOREIGN KEY (User_id)     REFERENCES User (User_id);

CREATE TABLE Premium_Users (
                               User_id    INT  NOT NULL,
                               Start_Date DATE NOT NULL,
                               End_Date   DATE NULL,
                               Show_To_Liked INT NULL);

ALTER TABLE Premium_Users   ADD CONSTRAINT FK_User_TO_Premium_Users     FOREIGN KEY (User_id)     REFERENCES User (User_id);


