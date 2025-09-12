### 1-1. Database란?
데이터베이스는 데이터의 집합이다.
여러 응용 시스템들의 통합된 정보들을 저장하여 운영할 수 있는 공용 데이터의 집합이다.
효율적으로 저장, 검색, 갱신할 수 있도록 데이터 집합들끼리 연관시키고 조직화 한다.

### 데이터 베이스 특성
1. 실시간접근성 : 사용자의 요구를 즉시 처리할 수 있다.
2. 계속적인 변화 : 삽입, 삭제, 수정으로 데이터를 지속적으로 갱신
3. 동시 공유성 : 여러 사용자가 동시에 같은 데이터에 접근가능
4. 내용 참조 : 데이터의 위치가 아닌 내용(값)에 따라 참조

### 데이터베이스의 기본 구성 요소
1. 테이블 : 데이터를 저장하는 기본 단위
2. 행 : 하나의 데이터 항목 (학생 한명의 정보)
3. 열 : 데이터의 속성 (이름, 학번, 학과)
4. 키 : 각 행을 고유하게 식별하는 값

### DBMS (Database Management System)
1. 정의 기능 : 데이터베이스의 논리적, 물리적 구조를 정의
2. 조작 기능 : 데이터를 검색, 삭제, 갱신, 삽입하는 기능
3. 제어 기능 : 데이터 베이스의 내용 정확성과 안정성을 유지

### 관계형 데이터베이스 (RDB) 특징
1. 데이터를 테이블 형태로 저장
2. 테이블 간의 관계를 통해 데이터 연결
3. SQL로 데이터 조작
4. 데이터 무결성과 일관성 보장

### Docker
Docker = 프로그램을 담는 상자

MySQL, Redis, MongoDB 같은 프로그램을 컨테이너라는 상자에 담아서 실행

복잡한 설치 과정 없이 명령어 하나로 실행 가능

삭제도 간단 (컨테이너만 지우면 끝)

### 꼭 알아야 할 Docker 용어 3가지
이미지 (Image)
프로그램이 들어있는 설치 파일
예: mysql:8.0 = MySQL 8.0 버전 설치 파일

컨테이너 (Container)
이미지를 실행한 실제 프로그램 하나의 이미지로 여러 개의 컨테이너 실행 가능

Docker Hub
이미지를 다운로드하는 앱스토어 hub.docker.com에서 다양한 프로그램 이미지 제공


### MYSQL 명령어

-- 버전 확인
SELECT VERSION(), CURRENT_DATE;

-- 데이터베이스 목록
SHOW DATABASES;

-- 현재 사용자 확인
SELECT USER();

-- 데이터베이스 선택
USE liondb;

-- 데이터 삽입
INSERT INTO test_table (name) VALUES ('테스트');

-- 데이터 조회
SELECT * FROM test_table;

-- 모든 호스트에서 접속 가능한 사용자
CREATE USER 'carami'@'%' IDENTIFIED BY 'kang1234';

-- 로컬에서만 접속 가능한 사용자
CREATE USER 'carami'@'localhost' IDENTIFIED BY 'kang1234';

-- 특정 데이터베이스에 대한 모든 권한
GRANT ALL PRIVILEGES ON liondb.* TO 'carami'@'%';
GRANT ALL PRIVILEGES ON liondb.* TO 'carami'@'localhost';

-- 권한 적용
FLUSH PRIVILEGES;

-- 사용자 확인
SELECT user, host FROM mysql.user WHERE user = 'carami';

-- 사용자 삭제
DROP USER 'carami'@'%';
DROP USER 'carami'@'localhost';

-- 모든 권한 취소
REVOKE ALL PRIVILEGES ON liondb.* FROM 'carami'@'%';

-- 특정 권한만 취소
REVOKE INSERT, UPDATE ON liondb.* FROM 'carami'@'%';

-- 권한 적용
FLUSH PRIVILEGES;

======================
---------------------------------------