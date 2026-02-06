/*create table students(
  student_id int primary key,
  name varchar(50),
  age int,
  grade varchar(10)
);*/

-- insert into students values (1,"Kaif",23,"A"),(2,"Krish",24,"B");
-- use schooldb;
-- select * from students;
-- use schooldb;

/*create table courses(
  course_id int primary key,
  course_name varchar(50) not null unique,
  duration int check (duration>0),
  fee decimal(10,2) default 1000.00
  );*/
  
  -- add a column
  -- alter table students add column city varchar(30);
  -- select * from students;
  
 -- update students set city="Kochi" where student_id=1;
  -- update students set city="Bengaluru" where student_id=2; 
 -- use schooldb;
 
 /*create table enrollments(
   student_id int,
   course_id int,
   foreign key (student_id) references students(student_id),
   foreign key (course_id) references courses(course_id)
   );*/
   -- insert into courses values(101,"Java",3,5000.00),(102,"Python",4,6000.00);
    -- insert into enrollments values(1,101),(1,102),(2,102);
  -- select s.name,c.course_name from students s join enrollments e on s.student_id=e.student_id join courses c on e.course_id=c.course_id;
   
  -- use schooldb;
  select * from courses;