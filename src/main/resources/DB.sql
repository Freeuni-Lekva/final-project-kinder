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
                      Job              VARCHAR(30) NULL    ,
                      School           VARCHAR(15) NULL    ,

                      Min_Age          INT     NULL  default 18,
                      Max_Age          INT     NULL   default 100,

                      Registration_Date DATETiME    NOT NULL  DEFAULT   CURRENT_TIMESTAMP,

                      Show_Active       INT   NOT NULL default 0,
                      Last_Session      DATE  NULL,

                      Hided             INT   NULL default 0,
                      Role              VARCHAR(20)   NOT NULL default 'USER' ,
                      Show_To_Liked     INT NULL default 0,
                      Balance           INT     NULL  DEFAULT 100 ,
                      Show_Recently_Active int NULL DEFAULT 0,
                      Premium          INT NULL DEFAULT 0,
                      Search_In_Range INT NULL DEFAULT 0,
                      Is_Banned INT NOT NULL DEFAULT 0,


                      PRIMARY KEY (User_id) );

CREATE TABLE Chat (
                      Chat_Id INT NOT NULL AUTO_INCREMENT,
                      User_id_1 INT     NOT NULL,
                      User_id_2 INT     NOT NULL,
                      PRIMARY KEY (Chat_Id) );
ALTER TABLE Chat  ADD CONSTRAINT FK_User_TO_Chat     FOREIGN KEY (User_id_1)     REFERENCES User (User_id) ON DELETE CASCADE;

ALTER TABLE Chat   ADD CONSTRAINT FK_User_TO_Chat1    FOREIGN KEY (User_id_2)     REFERENCES User (User_id) ON DELETE CASCADE;


CREATE TABLE Hobbies (
                         Hobby_id   INT     NOT NULL AUTO_INCREMENT,
                         User_id     INT     NOT NULL,
                         Hobby_Name VARCHAR(20) NOT NULL,
                         primary key (Hobby_Id) );

ALTER TABLE Hobbies   ADD CONSTRAINT FK_User_TO_Hobbies     FOREIGN KEY (User_id)     REFERENCES User (User_id) ON DELETE CASCADE;

CREATE TABLE Images (
                        Image_Id  INT     NOT NULL AUTO_INCREMENT,
                        User_id   INT     NOT NULL,
                        Image_Url VARCHAR(100) NOT NULL,
                        IsProfile INT  NULL,
                        Date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        primary key (Image_Id) );

ALTER TABLE Images   ADD CONSTRAINT FK_User_TO_Images     FOREIGN KEY (User_id)     REFERENCES User (User_id) ON DELETE CASCADE;

CREATE TABLE Likes (
                       Like_Id INT         NOT NULL AUTO_INCREMENT,
                       Status  VARCHAR(20) NOT NULL,
                       User_id_1 INT     NOT NULL,
                       User_id_2 INT     NOT NULL,
                       Date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        primary key (Like_Id));

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes     FOREIGN KEY (User_id_1)     REFERENCES User (User_id) ON DELETE CASCADE;

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes1     FOREIGN KEY (User_id_2)     REFERENCES User (User_id) ON DELETE CASCADE;

CREATE TABLE Matches (
                         User_id_1  INT  NOT NULL,
                         User_id_2  INT  NOT NULL,
                         Match_Id INT  NOT NULL auto_increment,
                         Unmatch   INT  NULL    ,
                         Date     DATETIME NULL  DEFAULT CURRENT_TIMESTAMP   ,
                         Chat_Id  INT  NOT NULL,   primary key(Match_Id) ) ;

ALTER TABLE Matches   ADD CONSTRAINT FK_User_TO_Matches     FOREIGN KEY (User_id_1)     REFERENCES User (User_id) ON DELETE CASCADE;

ALTER TABLE Matches   ADD CONSTRAINT FK_User_TO_Matches1     FOREIGN KEY (User_id_2)     REFERENCES User (User_id) ON DELETE CASCADE;

ALTER TABLE Matches   ADD CONSTRAINT FK_Chat_TO_Matches     FOREIGN KEY (Chat_Id)     REFERENCES Chat (Chat_Id) ON DELETE CASCADE;

CREATE TABLE Messages (
                          Chat_Id      INT     NOT NULL,
                          Message_Id   INT     NOT NULL auto_increment,
                          Message_Text VARCHAR(500) NOT NULL,
                          Date         DATETIME    NOT NULL DEFAULT current_timestamp,
                          User_id      INT     NOT NULL,
                          primary key (Message_Id) );

ALTER TABLE Messages   ADD CONSTRAINT FK_Chat_TO_Messages     FOREIGN KEY (Chat_Id)     REFERENCES Chat (Chat_Id) ON DELETE CASCADE;

ALTER TABLE Messages   ADD CONSTRAINT FK_User_TO_Messages     FOREIGN KEY (User_id)     REFERENCES User (User_id) ON DELETE CASCADE;

CREATE TABLE Premium_Users (
                               User_id    INT  NOT NULL,
                               Start_Date DATE NOT NULL,
                               End_Date   DATE NULL,
                               Show_To_Liked INT NULL);

ALTER TABLE Premium_Users   ADD CONSTRAINT FK_User_TO_Premium_Users     FOREIGN KEY (User_id)     REFERENCES User (User_id) ON DELETE CASCADE;




INSERT INTO Kinder_Base.User ( Mail,                            First_Name, Birth_Date,        City,    Gender, Show_Gender, Preference, Orientation,                                                Bio, Horoscope,             Company,            Job, School,                          Min_Age, Max_Age,  Show_Active, Last_Session, Hided, Role, Show_To_Liked, Balance,Show_Recently_Active, Premium, Search_In_Range,Is_Banned)
                            VALUES ( 'kakhikurasbediani@gmail.com', 'Kakhi',      '2000-06-20', 'Tbilisi', 'Man',         1,       'Woman', 'Straight',   'Hello, I am Kakhi! I am ESM student from Tbilisi.', null,      'Bank of Georgia',  'Marketing Guru', 'FreeUni',  '20',     '30',    0,           '2022-09-09 18:40:18',           0,  'USER',     0,          100,         0,               0,       0,1);
INSERT INTO Kinder_Base.User ( Mail,                                First_Name, Birth_Date,        City,    Gender, Show_Gender, Preference, Orientation,                                                Bio, Horoscope,             Company,            Job, School,                          Min_Age, Max_Age,  Show_Active, Last_Session, Hided, Role, Show_To_Liked, Balance,Show_Recently_Active, Premium, Search_In_Range)
                            VALUES ( 'megi.shashikadze.99@gmail.com', 'Megi',      '1999-10-04', 'Tbilisi', 'Woman',         1,       'Man', 'Straight',   'Hello, I am Megi! I am ESM student from Tbilisi.', null,      'EY',  'Assurance Staff', 'FreeUni',               null,    null,    0,           '2022-09-09 18:40:18',           0,  'USER',     0,          100,         0,               0,       0);
INSERT INTO Kinder_Base.User ( Mail,                                First_Name, Birth_Date,        City,    Gender, Show_Gender, Preference, Orientation,                    Bio,        Horoscope,    Company,            Job, School,               Min_Age, Max_Age,  Show_Active, Last_Session, Hided, Role, Show_To_Liked, Balance,Show_Recently_Active, Premium, Search_In_Range)
                            VALUES ( 'mshas18@freeuni.edu.ge',       'Mariami',      '1977-03-29', 'Tbilisi', 'Woman',         1,       'Man', 'Straight',   'Hello, I am Mariami!',       null,      'GAU',  'Invited Lecturer', null,               null,    null,    0,           '2022-09-09 18:40:18',           0,  'PREMIUM_USER',     1,          100,         1,               1,       0);
INSERT INTO Kinder_Base.User ( Mail,                            First_Name, Birth_Date,        City,    Gender, Show_Gender, Preference, Orientation,                                                Bio, Horoscope,             Company,            Job, School,Min_Age, Max_Age,  Show_Active, Last_Session, Hided, Role, Show_To_Liked, Balance,Show_Recently_Active, Premium, Search_In_Range)
                             VALUES ( 'kkura18@freeuni.edu.ge', 'Konstantine',      '2000-06-20', 'Tbilisi', 'Man',         1,       'Woman', 'Straight',   'Hello, I am Kote! I am GAU student from Tbilisi.', null,      'TBC',  'Financial analyst', 'GAU',  null,     null,    0,          '2022-09-09 18:40:18',           0,  'USER',     0,          100,         0,               0,       0);
INSERT INTO Kinder_Base.User ( Mail,                                First_Name, Birth_Date,        City,    Gender, Show_Gender, Preference, Orientation,                                                Bio, Horoscope,             Company,            Job, School,                          Min_Age, Max_Age,  Show_Active, Last_Session, Hided, Role, Show_To_Liked, Balance,Show_Recently_Active, Premium, Search_In_Range)
                             VALUES ( 'lgham18@freeuni.edu.ge',      'Lasha',      '2000-08-30', 'Tbilisi', 'Man',         1,       'Woman', 'Straight',   'Hello, I am Lasha! I am ESM student from Tbilisi.', null,      'BOG',  'Another Marketing Guru', 'FreeUni',       null,    null,    0,           '2022-09-09 18:40:18',           1,  'ADMIN',     0,          100,         0,               1,       0);

INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (1, 'kakhi_1.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (1, 'kakhi_2.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (2, 'megi_1.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (2, 'megi_2.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (3, 'mariami_1.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (3, 'mariami_2.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (4, 'kote_1.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (4, 'kote_2.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (5, 'lasha_1.jpg');
INSERT INTO Kinder_Base.Images ( User_id, Image_Url) VALUES (5, 'lasha_2.jpg');


INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (1,'CARS');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (1,'SPORT');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (2,'FOOTBALL');
INSERT INTO Kinder_Base.Hobbies (User_id, Hobby_Name) VALUES (2,'INSTAGRAM');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (3,'CARS');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (3,'INSTAGRAM');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (4,'CARS');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (4,'SPORT');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (4,'CARS');
INSERT INTO Kinder_Base.Hobbies ( User_id, Hobby_Name) VALUES (4,'FOOTBALL');

# insert into Kinder_Base.Likes( Status, User_id_1, User_id_2)  values('LIKE',2,1);
#
#  insert into Kinder_Base.Likes( Status, User_id_1, User_id_2)  values('LIKE',1,3);
# insert into Kinder_Base.Likes( Status, User_id_1, User_id_2)  values('LIKE',2,3);
# insert into Kinder_Base.Likes( Status, User_id_1, User_id_2)  values('LIKE',4,3);
