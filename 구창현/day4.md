### SQL
SQL(Structured Query Language)은 관계형 데이터베이스를 조작하기 위한 표준 언어

## SQL 특징 ⭐
대부분의 SQL 명령은 세미콜론(;)으로 끝납니다

키워드는 대소문자를 구분하지 않습니다

데이터는 대소문자를 구분합니다

## 데이터베이스 용어 ⭐
Table	데이터를 저장하는 2차원 구조

Row (행)	레코드, 튜플 - 하나의 데이터 항목

Column (열)	필드, 속성 - 데이터의 특정 속성

Primary Key	각 행을 고유하게 식별하는 키

## SELECT 기본 문법 ⭐
SELECT [DISTINCT] 컬럼명 [AS 별칭]
FROM 테이블명
WHERE 조건
ORDER BY 컬럼 [ASC|DESC];

### 데이터 조회
모든 컬럼 조회

SELECT * FROM employees;

### 특정 컬럼 조회
SELECT first_name, last_name, salary

FROM employees;

### 컬럼에 별칭 부여
SELECT first_name AS 이름,
hire_date AS 입사일,
salary AS 급여
FROM employees;

### 문자열 결합
SELECT CONCAT(first_name, ' ', last_name) AS full_name
FROM employees;

### 산술 연산
SELECT
first_name,
salary,
salary * 12 AS annual_salary
FROM employees;

### 중복된 값 제거
SELECT DISTINCT department_id
FROM employees;

### 여러 컬럼의 조합으로 중복 제거
SELECT DISTINCT department_id, job_id
FROM employees;

### 같음
SELECT * FROM employees WHERE department_id = 90;

## WHERE 절과 조건 검색 ⭐

### 크거나 같음
SELECT * FROM employees WHERE salary >= 10000;

### 날짜 비교
SELECT * FROM employees

WHERE hire_date > '2005-01-01';

### 문자열 비교
SELECT * FROM employees

WHERE last_name = 'King';

### AND 연산
SELECT * FROM employees

WHERE salary >= 10000 AND department_id = 90;

### OR 연산
SELECT * FROM employees

WHERE department_id = 90 OR department_id = 100;

### NOT 연산
SELECT * FROM employees

WHERE NOT department_id = 90;

### 복합 조건
SELECT * FROM employees

WHERE (department_id = 90 OR department_id = 100)

AND salary >= 10000;

### 범위 검색
SELECT * FROM employees

WHERE salary BETWEEN 5000 AND 10000;

### 날짜 범위
SELECT * FROM employees

WHERE hire_date BETWEEN '2005-01-01' AND '2005-12-31';

## LIKE 사용 예제 ⭐
% : 0개 이상의 문자

_ : 정확히 1개의 문자

### S로 시작하는 이름 :SELECT * FROM employees WHERE first_name LIKE 'S%';

### n으로 끝나는 이름
SELECT * FROM employees : 
WHERE first_name LIKE '%n';

### a를 포함하는 이름
SELECT * FROM employees :
WHERE first_name LIKE '%a%';

### 두 번째 글자가 o인 이름
SELECT * FROM employees : 
WHERE first_name LIKE '_o%';

### 정확히 5글자인 이름
SELECT * FROM employees : 
WHERE first_name LIKE '_____';

### a로 시작하고 5글자인 이름
SELECT * FROM employees
WHERE first_name LIKE 'a____';

## NULL 처리 ⭐

### NULL인 데이터 찾기
SELECT * FROM employees
WHERE commission_pct IS NULL;

### NULL이 아닌 데이터 찾기
SELECT * FROM employees
WHERE commission_pct IS NOT NULL;

### IFNULL: NULL을 다른 값으로 대체
SELECT
first_name,
IFNULL(commission_pct, 0) AS commission
FROM employees;

### COALESCE: 여러 값 중 첫 번째 NOT NULL 값 반환
SELECT
first_name,
COALESCE(commission_pct, manager_id, 0) AS value
FROM employees;

## 정렬 (ORDER BY) ⭐

### 오름차순 정렬 (기본값)
SELECT * FROM employees
ORDER BY salary;

### 내림차순 정렬
SELECT * FROM employees
ORDER BY salary DESC;

### 문자열 정렬
SELECT * FROM employees
ORDER BY last_name ASC;

### 부서별로 정렬 후, 같은 부서 내에서 급여순 정렬
SELECT * FROM employees
ORDER BY department_id, salary DESC;

### 별칭 사용
SELECT
first_name,
salary * 12 AS annual_salary
FROM employees
ORDER BY annual_salary DESC;

### 상위 5개 결과만 조회
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 5;

### 급여가 높은 상위 10명
SELECT first_name, last_name, salary
FROM employees
ORDER BY salary DESC
LIMIT 10;

## SQL 함수 ⭐
### 대소문자 변환
SELECT UPPER('hello'), LOWER('HELLO');

### 문자열 결합
SELECT CONCAT('Hello', ' ', 'World');

### 부분 문자열
SELECT SUBSTRING('Hello World', 1, 5);

### 문자열 길이
SELECT LENGTH('Hello World');

### 공백 제거
SELECT TRIM('  Hello  '), LTRIM('  Hello'), RTRIM('Hello  ');

### 문자열 치환
SELECT REPLACE('Hello World', 'World', 'MySQL');

### 패딩
SELECT LPAD('Hi', 5, '*'), RPAD('Hi', 5, '?');

-- 반올림, 올림, 내림
SELECT ROUND(3.7), CEIL(3.2), FLOOR(3.9);

## 숫자 함수 ⭐

### 절대값
SELECT ABS(-10);

### 나머지
SELECT MOD(10, 3);

### 제곱, 제곱근
SELECT POWER(2, 3), SQRT(16);

### 최대값, 최소값
SELECT GREATEST(1, 2, 3), LEAST(1, 2, 3);

## 날짜 함수 ⭐
### 현재 날짜와 시간
SELECT CURDATE(), CURTIME(), NOW();

### 날짜 포맷팅
SELECT DATE_FORMAT(NOW(), '%Y년 %m월 %d일');

### 날짜 연산
SELECT
DATE_ADD(NOW(), INTERVAL 1 DAY),
DATE_SUB(NOW(), INTERVAL 1 MONTH);

### 날짜 차이
SELECT DATEDIFF('2024-12-31', NOW());

### 날짜 부분 추출
SELECT
YEAR(NOW()),
MONTH(NOW()),
DAY(NOW()),
DAYOFWEEK(NOW());

## 그룹 함수 ⭐
### COUNT: 행 개수
SELECT COUNT(*) FROM employees;
SELECT COUNT(commission_pct) FROM employees; -- NULL 제외

### SUM: 합계
SELECT SUM(salary) FROM employees;

### AVG: 평균
SELECT AVG(salary) FROM employees;

### MAX, MIN: 최대값, 최소값
SELECT MAX(salary), MIN(salary) FROM employees;

### 여러 집계 함수 함께 사용
SELECT
COUNT(*) AS 직원수,
AVG(salary) AS 평균급여,
MAX(salary) AS 최고급여,
MIN(salary) AS 최저급여
FROM employees;

## GROUP BY ⭐

### 부서별 평균 급여
SELECT
department_id,
AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id;

### 여러 컬럼으로 그룹화
SELECT
department_id,
job_id,
COUNT(*) AS count,
AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id, job_id;

### 그룹별 정렬
SELECT
department_id,
COUNT(*) AS emp_count
FROM employees
GROUP BY department_id
ORDER BY emp_count DESC;

## HAVING ⭐
### 그룹 조건 (평균 급여가 10000 이상인 부서)
SELECT
department_id,
AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id
HAVING AVG(salary) >= 10000;

### WHERE와 HAVING 함께 사용
SELECT
department_id,
AVG(salary) AS avg_salary
FROM employees
WHERE hire_date >= '2005-01-01'
GROUP BY department_id
HAVING COUNT(*) >= 5;

## 테이블 관계와 JOIN 개념 ⭐⭐
JOIN : JOIN은 하나 이상의 테이블로부터 연관된 데이터를 검색해 오는 방법입니다.

## JOIN 종류 ⭐
Cross Join : 모든 가능한 쌍이 나타남 (Cartesian Product)

Inner Join : 조인 조건을 만족하는 튜플만 나타남

Outer Join : 조인 조건을 만족하지 않는 튜플도 null과 함께 나타남

Self Join : 자기 자신과 조인

## ANSI JOIN 문법 ⭐⭐⭐
### JOIN ~ ON
SELECT
e.first_name,
e.last_name,
d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id;

### JOIN ~ USING (동일한 컬럼명일 때)
SELECT
e.first_name,
e.last_name,
d.department_name
FROM employees e
JOIN departments d USING(department_id);

### NATURAL JOIN (자동으로 같은 이름 컬럼 조인)
SELECT * FROM employees NATURAL JOIN departments;