-- create database companydb;
-- use companydb;
/*create table departments(
  dept_id int primary key,
  dept_name varchar(50),
  location varchar(50)
  );*/

/*create table employees(
  emp_id int primary key,
  emp_name varchar(50),
  salary decimal(10,2),
  dept_id int,
  foreign key (dept_id) references departments(dept_id)
  );*/
  
 /* create table projects (
    project_id int primary key,
    project_name varchar(50),
    emp_id int,
    foreign key (emp_id) references employees(emp_id)
);*/
/*INSERT INTO departments VALUES
(1, 'Engineering', 'Kochi'),
(2, 'HR', 'Bengaluru'),
(3, 'Finance', 'Mumbai');*/

/*(101, 'Kaif', 75000, 1),
(102, 'Aisha', 60000, 2),
(103, 'Rahul', 65000, 3),
(104, 'Krish', 70000, 1);*/

/*INSERT INTO projects VALUES
(201, 'Payroll System', 103),
(202, 'Recruitment Portal', 102),
(203, 'Website Redesign', 101),
(204, 'AI Chatbot', 104);*/

-- inner join
/*SELECT e.emp_name, d.dept_name
FROM employees e
INNER JOIN departments d ON e.dept_id = d.dept_id;*/
-- insert into departments values(4,"marketing","delhi");
-- left join
/*SELECT d.dept_name, e.emp_name
FROM departments d
LEFT JOIN employees e ON d.dept_id = e.dept_id;*/

-- INSERT INTO employees VALUES (105, 'Mary', 55000, NULL);
 -- SELECT COUNT(*) AS total_employees FROM employees;
/*SELECT dept_id, COUNT(*) AS total_in_dept
FROM employees
GROUP BY dept_id;*/

/*SELECT d.dept_name, COUNT(e.emp_id) AS total_employees
FROM departments d
LEFT JOIN employees e
ON d.dept_id = e.dept_id
GROUP BY d.dept_name;*/
