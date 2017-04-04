  prompt PL/SQL Developer import file
prompt Created on 2017年2月16日 by ttc
set feedback off
set define off
prompt Creating TEACHERS...
create table TEACHERS
(
  teachernumber VARCHAR2(50) not null,
  teachername   VARCHAR2(50) not null,
  gender        VARCHAR2(50) not null,
  birthday      DATE not null,
  phonenumber   VARCHAR2(50) not null,
  address       VARCHAR2(200) not null,
  email         VARCHAR2(50) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TEACHERS
  add constraint PK_TEACHERS primary key (TEACHERNUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TEACHERS
  add constraint UQ_TEACHERS_TELE unique (PHONENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating CLAZZES...
create table CLAZZES
(
  clazznumber   VARCHAR2(50) not null,
  clazzname     VARCHAR2(50) not null,
  begindate     DATE not null,
  enddate       DATE not null,
  teachernumber VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CLAZZES
  add constraint PK_CLAZZES primary key (CLAZZNUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CLAZZES
  add constraint UQ_CLAZZESNAME unique (CLAZZNAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CLAZZES
  add constraint UQ_TEACHERNUMBER unique (TEACHERNUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table CLAZZES
  add constraint FK_TEACHERS_CLAZZES foreign key (TEACHERNUMBER)
  references TEACHERS (TEACHERNUMBER);

prompt Creating COURSES...
create table COURSES
(
  coursenumber VARCHAR2(50) not null,
  coursename   VARCHAR2(50) not null,
  credit       FLOAT not null,
  hours        FLOAT not null,
  description  VARCHAR2(50) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table COURSES
  add constraint PK_COURSES primary key (COURSENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating STUDENTS...
create table STUDENTS
(
  studentnumber VARCHAR2(50) not null,
  studentname   VARCHAR2(50) not null,
  gender        VARCHAR2(50) not null,
  birthday      DATE not null,
  phonenumber   VARCHAR2(50) not null,
  address       VARCHAR2(200) not null,
  clazznumber   VARCHAR2(50) not null,
  email         VARCHAR2(50) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDENTS
  add constraint PK_STUDENTS primary key (STUDENTNUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDENTS
  add constraint UQ_STUDENTS_TELE unique (PHONENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDENTS
  add constraint FK_CLAZZES_STUDENTS foreign key (CLAZZNUMBER)
  references CLAZZES (CLAZZNUMBER);

prompt Creating STUDYCOURSES...
create table STUDYCOURSES
(
  studycoursenumber VARCHAR2(50) not null,
  studentnumber     VARCHAR2(50) not null,
  coursenumber      VARCHAR2(50) not null,
  score             FLOAT not null,
  examdate          DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDYCOURSES
  add constraint PK_STUDYCOURSES primary key (STUDYCOURSENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDYCOURSES
  add constraint UQ_STU_COUR_NUMBER unique (STUDENTNUMBER, COURSENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table STUDYCOURSES
  add constraint FQ_COURSES_STUDYCOURSES foreign key (COURSENUMBER)
  references COURSES (COURSENUMBER);
alter table STUDYCOURSES
  add constraint FQ_STUDENTS_STUDYCOURSES foreign key (STUDENTNUMBER)
  references STUDENTS (STUDENTNUMBER);

prompt Creating TEACHCOURSES...
create table TEACHCOURSES
(
  teachcoursenumber VARCHAR2(50) not null,
  teachernumber     VARCHAR2(50) not null,
  coursenumber      VARCHAR2(50) not null,
  begindate         DATE not null,
  enddate           DATE not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TEACHCOURSES
  add constraint PK_TEACHCOURSES primary key (TEACHCOURSENUMBER)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TEACHCOURSES
  add constraint FK_COURSES_TEACHCOURSES foreign key (COURSENUMBER)
  references COURSES (COURSENUMBER);
alter table TEACHCOURSES
  add constraint FK_TEACHER_TEACHCOURSES foreign key (TEACHERNUMBER)
  references TEACHERS (TEACHERNUMBER);

prompt Disabling triggers for TEACHERS...
alter table TEACHERS disable all triggers;
prompt Disabling triggers for CLAZZES...
alter table CLAZZES disable all triggers;
prompt Disabling triggers for COURSES...
alter table COURSES disable all triggers;
prompt Disabling triggers for STUDENTS...
alter table STUDENTS disable all triggers;
prompt Disabling triggers for STUDYCOURSES...
alter table STUDYCOURSES disable all triggers;
prompt Disabling triggers for TEACHCOURSES...
alter table TEACHCOURSES disable all triggers;
prompt Disabling foreign key constraints for CLAZZES...
alter table CLAZZES disable constraint FK_TEACHERS_CLAZZES;
prompt Disabling foreign key constraints for STUDENTS...
alter table STUDENTS disable constraint FK_CLAZZES_STUDENTS;
prompt Disabling foreign key constraints for STUDYCOURSES...
alter table STUDYCOURSES disable constraint FQ_COURSES_STUDYCOURSES;
alter table STUDYCOURSES disable constraint FQ_STUDENTS_STUDYCOURSES;
prompt Disabling foreign key constraints for TEACHCOURSES...
alter table TEACHCOURSES disable constraint FK_COURSES_TEACHCOURSES;
alter table TEACHCOURSES disable constraint FK_TEACHER_TEACHCOURSES;
prompt Loading TEACHERS...
insert into TEACHERS (teachernumber, teachername, gender, birthday, phonenumber, address, email)
values ('1', '李老师', '男', to_date('16-02-2017', 'dd-mm-yyyy'), '123456', '通辽', '45687949@qq.com');
commit;
prompt 1 records loaded
prompt Loading CLAZZES...
insert into CLAZZES (clazznumber, clazzname, begindate, enddate, teachernumber)
values ('c1', '班级一', to_date('01-02-2017', 'dd-mm-yyyy'), to_date('03-02-2017', 'dd-mm-yyyy'), null);
insert into CLAZZES (clazznumber, clazzname, begindate, enddate, teachernumber)
values ('c2', '班级二', to_date('03-02-2017', 'dd-mm-yyyy'), to_date('09-02-2017', 'dd-mm-yyyy'), null);
commit;
prompt 2 records loaded
prompt Loading COURSES...
insert into COURSES (coursenumber, coursename, credit, hours, description)
values ('c3', '大学语文', 2, 2, 'a');
insert into COURSES (coursenumber, coursename, credit, hours, description)
values ('c1', 'fd ', 1, 1, 'dfds');
insert into COURSES (coursenumber, coursename, credit, hours, description)
values ('888', '纳尼', 1, 1, '发生大');
insert into COURSES (coursenumber, coursename, credit, hours, description)
values ('c2', '大学数学', 1, 1, '太diffcult!');
insert into COURSES (coursenumber, coursename, credit, hours, description)
values ('006', 'jgh', 5, 5, 'jg');
commit;
prompt 5 records loaded
prompt Loading STUDENTS...
insert into STUDENTS (studentnumber, studentname, gender, birthday, phonenumber, address, clazznumber, email)
values ('1', '张三', '男', to_date('12-02-2017', 'dd-mm-yyyy'), '110', '哈尔滨', 'c1', '1512@qq.com');
insert into STUDENTS (studentnumber, studentname, gender, birthday, phonenumber, address, clazznumber, email)
values ('2', '李四', '女', to_date('14-02-2017', 'dd-mm-yyyy'), '120', '沈阳', 'c2', '12@qq.com');
insert into STUDENTS (studentnumber, studentname, gender, birthday, phonenumber, address, clazznumber, email)
values ('3', '王二麻子', '男', to_date('06-02-2017', 'dd-mm-yyyy'), '119', '长春', 'c1', '4512@qq.com');
insert into STUDENTS (studentnumber, studentname, gender, birthday, phonenumber, address, clazznumber, email)
values ('4', 'Golden', '男', to_date('02-02-1232', 'dd-mm-yyyy'), '12', '2', 'c1', '3562@qq.com');
commit;
prompt 4 records loaded
prompt Loading STUDYCOURSES...
insert into STUDYCOURSES (studycoursenumber, studentnumber, coursenumber, score, examdate)
values ('1', '1', 'c1', 12, to_date('05-06-1154', 'dd-mm-yyyy'));
commit;
prompt 1 records loaded
prompt Loading TEACHCOURSES...
insert into TEACHCOURSES (teachcoursenumber, teachernumber, coursenumber, begindate, enddate)
values ('7', '1', 'c2', to_date('06-09-1549', 'dd-mm-yyyy'), to_date('05-09-1876', 'dd-mm-yyyy'));
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for CLAZZES...
alter table CLAZZES enable constraint FK_TEACHERS_CLAZZES;
prompt Enabling foreign key constraints for STUDENTS...
alter table STUDENTS enable constraint FK_CLAZZES_STUDENTS;
prompt Enabling foreign key constraints for STUDYCOURSES...
alter table STUDYCOURSES enable constraint FQ_COURSES_STUDYCOURSES;
alter table STUDYCOURSES enable constraint FQ_STUDENTS_STUDYCOURSES;
prompt Enabling foreign key constraints for TEACHCOURSES...
alter table TEACHCOURSES enable constraint FK_COURSES_TEACHCOURSES;
alter table TEACHCOURSES enable constraint FK_TEACHER_TEACHCOURSES;
prompt Enabling triggers for TEACHERS...
alter table TEACHERS enable all triggers;
prompt Enabling triggers for CLAZZES...
alter table CLAZZES enable all triggers;
prompt Enabling triggers for COURSES...
alter table COURSES enable all triggers;
prompt Enabling triggers for STUDENTS...
alter table STUDENTS enable all triggers;
prompt Enabling triggers for STUDYCOURSES...
alter table STUDYCOURSES enable all triggers;
prompt Enabling triggers for TEACHCOURSES...
alter table TEACHCOURSES enable all triggers;
set feedback on
set define on
prompt Done.
