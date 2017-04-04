/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/2/16 ÐÇÆÚËÄ 19:59:25                       */
/*==============================================================*/


drop table if exists CLAZZES;

drop table if exists COURSES;

drop table if exists STUDENTS;

drop table if exists STUDYCOURSES;

drop table if exists TEACHCOURSES;

drop table if exists TEACHERS;

/*==============================================================*/
/* Table: CLAZZES                                               */
/*==============================================================*/
create table CLAZZES
(
   CLAZZNUMBER          varchar(50) not null,
   CLAZZNAME            varchar(50) not null,
   BEGINDATE            datetime not null,
   ENDDATE              datetime not null,
   TEACHERNUMBER        varchar(50),
   primary key (CLAZZNUMBER),
   key UQ_CLAZZESNAME (CLAZZNAME),
   key UQ_TEACHERNUMBER (TEACHERNUMBER)
);

/*==============================================================*/
/* Table: COURSES                                               */
/*==============================================================*/
create table COURSES
(
   COURSENUMBER         varchar(50) not null,
   COURSENAME           varchar(50) not null,
   CREDIT               float not null,
   HOURS                float not null,
   DESCRIPTION          varchar(50) not null,
   primary key (COURSENUMBER)
);

/*==============================================================*/
/* Table: STUDENTS                                              */
/*==============================================================*/
create table STUDENTS
(
   STUDENTNUMBER        varchar(50) not null,
   STUDENTNAME          varchar(50) not null,
   GENDER               varchar(50) not null,
   BIRTHDAY             datetime not null,
   PHONENUMBER          varchar(50) not null,
   ADDRESS              varchar(200) not null,
   CLAZZNUMBER          varchar(50) not null,
   EMAIL                varchar(50) not null,
   primary key (STUDENTNUMBER),
   key UQ_STUDENTS_TELE (PHONENUMBER)
);

/*==============================================================*/
/* Table: STUDYCOURSES                                          */
/*==============================================================*/
create table STUDYCOURSES
(
   STUDYCOURSENUMBER    varchar(50) not null,
   STUDENTNUMBER        varchar(50) not null,
   COURSENUMBER         varchar(50) not null,
   SCORE                float not null,
   EXAMDATE             datetime not null,
   primary key (STUDYCOURSENUMBER),
   key UQ_STU_COUR_NUMBER (STUDENTNUMBER, COURSENUMBER)
);

/*==============================================================*/
/* Table: TEACHCOURSES                                          */
/*==============================================================*/
create table TEACHCOURSES
(
   TEACHCOURSENUMBER    varchar(50) not null,
   TEACHERNUMBER        varchar(50) not null,
   COURSENUMBER         varchar(50) not null,
   BEGINDATE            datetime not null,
   ENDDATE              datetime not null,
   primary key (TEACHCOURSENUMBER)
);

/*==============================================================*/
/* Table: TEACHERS                                              */
/*==============================================================*/
create table TEACHERS
(
   TEACHERNUMBER        varchar(50) not null,
   TEACHERNAME          varchar(50) not null,
   GENDER               varchar(50) not null,
   BIRTHDAY             datetime not null,
   PHONENUMBER          varchar(50) not null,
   ADDRESS              varchar(200) not null,
   EMAIL                varchar(50) not null,
   primary key (TEACHERNUMBER),
   key UQ_TEACHERS_TELE (PHONENUMBER)
);

alter table CLAZZES add constraint FK_TEACHERS_CLAZZES foreign key (TEACHERNUMBER)
      references TEACHERS (TEACHERNUMBER) on delete restrict on update restrict;

alter table STUDENTS add constraint FK_CLAZZES_STUDENTS foreign key (CLAZZNUMBER)
      references CLAZZES (CLAZZNUMBER) on delete restrict on update restrict;

alter table STUDYCOURSES add constraint FQ_COURSES_STUDYCOURSES foreign key (COURSENUMBER)
      references COURSES (COURSENUMBER) on delete restrict on update restrict;

alter table STUDYCOURSES add constraint FQ_STUDENTS_STUDYCOURSES foreign key (STUDENTNUMBER)
      references STUDENTS (STUDENTNUMBER) on delete restrict on update restrict;

alter table TEACHCOURSES add constraint FK_COURSES_TEACHCOURSES foreign key (COURSENUMBER)
      references COURSES (COURSENUMBER) on delete restrict on update restrict;

alter table TEACHCOURSES add constraint FK_TEACHER_TEACHCOURSES foreign key (TEACHERNUMBER)
      references TEACHERS (TEACHERNUMBER) on delete restrict on update restrict;

