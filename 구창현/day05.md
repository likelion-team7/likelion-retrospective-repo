-- 3. 부서명이 it로 시작하는 //'모든' 부서이름과// 사원명을 출력하세요.
show tables;
desc departments;
desc employees;
select * from departments;
select * from employees;

select e.first_name, d.department_name
from employees e
right join departments d on e.department_id = d.department_id
where d.department_name like 'IT%';



desc departments;
desc employees;
select * from departments;
select * from employees;
show tables;
select * from countries;
select * from locations;

-- 2. 각 부서에서 /최고 급여를 받는 직원/의 /직원번호, 이름, 부서명, 급여, 국가를 //급여가 높은 순//으로 출력하세요
select e.employee_id, e.first_name, d.department_name, e.salary, c.country_name
from employees e
join departments d on e.department_id = d.department_id
join locations l on d.location_id = l.location_id
join countries c on l.country_id = c.country_id

where (e.department_id, e.salary) IN (
SELECT department_id, MAX(salary)
FROM employees
GROUP BY department_id
)ORDER BY salary DESC;
