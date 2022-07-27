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
     First_Name       VARCHAR(20) NOT NULL,
     Last_Name        VARCHAR(20) NOT NULL,
     User_id          INT NOT NULL AUTO_INCREMENT,
     Bio              VARCHAR(200) NULL    ,
     Mail             VARCHAR(50) NOT NULL,
     Country          VARCHAR(20) NOT NULL    ,
     City             VARCHAR(20) NOT NULL    ,
     Min_Age          INT     NULL    ,
     Max_Age          INT     NULL    ,
     Gender           VARCHAR(20) NOT NULL   ,
     Orientation      VARCHAR(30) NULL    ,
     Birth_Date       DATE    NOT NULL,

     Registration_Date DATE    NOT NULL,
     Horoscope         VARCHAR(15) NULL    ,
     Hided             INT     NOT NULL,
     Admin             INT   NOT NULL ,
     Balance           INT     NULL  DEFAULT 100 ,

     PRIMARY KEY (User_id) );

CREATE TABLE Chat (
    Chat_Id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (Chat_Id) );

CREATE TABLE Hobbies (
    Hobbie_id   INT     NOT NULL AUTO_INCREMENT,
    User_id     INT     NOT NULL,
    Hobbie_Name VARCHAR(20) NOT NULL,
    primary key (Hobbie_Id) );

ALTER TABLE Hobbies   ADD CONSTRAINT FK_User_TO_Hobbies     FOREIGN KEY (User_id)     REFERENCES User (User_id);

CREATE TABLE Images (
    Image_Id  INT     NOT NULL AUTO_INCREMENT,
    User_id   INT     NOT NULL,
    Image_Url VARCHAR(100) NOT NULL,
    primary key (Image_Id) );

ALTER TABLE Images   ADD CONSTRAINT FK_User_TO_Images     FOREIGN KEY (User_id)     REFERENCES User (User_id);

CREATE TABLE Likes (
    Status  VARCHAR(20) NOT NULL,
    User_id_1 INT     NOT NULL,
    User_id_2 INT     NOT NULL,
    Date    DATE    NOT NULL );

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes     FOREIGN KEY (User_id_1)     REFERENCES User (User_id);

ALTER TABLE Likes   ADD CONSTRAINT FK_User_TO_Likes1     FOREIGN KEY (User_id_2)     REFERENCES User (User_id);

CREATE TABLE Matches (
    User_id_1  INT  NOT NULL,
    User_id_2  INT  NOT NULL,
    Match_Id INT  NOT NULL auto_increment,
    Unmatch   INT  NULL    ,   Date     DATE NULL    ,
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
    End_Date   DATE NULL     );

ALTER TABLE Premium_Users   ADD CONSTRAINT FK_User_TO_Premium_Users     FOREIGN KEY (User_id)     REFERENCES User (User_id);
