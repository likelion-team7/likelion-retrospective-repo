# Java 예외 처리 & IO 정리

## 📌 목차
1. 예외 처리의 필요성과 기본 개념
2. Error vs Exception
3. throw 와 throws 차이
4. Checked Exception vs Unchecked Exception
5. IllegalArgumentException 과 사용자 정의 예외
6. finally 블록의 역할
7. 스코프(scope) 개념
8. static 키워드
9. Java IO의 필요성과 설계 철학
10. Decorator 패턴과 주인공 vs 장식
11. 스트림(Stream)의 개념과 분류
12. 바이트 스트림 (InputStream / OutputStream)
13. ByteArrayInputStream
14. FileInputStream vs Reader/Writer
15. BufferedInputStream & DataInputStream (왜 필요한가?)

---

## 1. 예외 처리의 필요성과 기본 개념
- 실행 중 발생할 수 있는 비정상 상황에 대비해 프로그램을 안전하게 유지하는 기법
- 필요 이유  
  1. 안정성 & 신뢰성 보장  
  2. 오류 원인 조기 발견  
  3. 사용자 경험 개선  
  4. 시스템 자원 안전 관리 (`finally`, `try-with-resources`)

---

## 2. Error vs Exception
- **Error**: 시스템 레벨 치명적 오류 (예: OutOfMemoryError) → 복구 불가
- **Exception**: 프로그램에서 처리 가능한 오류 → `try-catch`, `throws` 로 대응

---

## 3. throw 와 throws 차이
- **throw**: 메소드 내부에서 예외를 실제 발생  
  ```java
  throw new IllegalArgumentException("잘못된 값");
  ```
- **throws**: 메소드 선언부에서 예외 발생 가능성을 알림  
  ```java
  public void readFile() throws IOException { ... }
  ```

👉 **throw = 실제 던짐, throws = 던질 수 있음 선언**  

---

## 4. Checked Exception vs Unchecked Exception
- **Checked Exception**: 컴파일러 강제, 외부 환경 문제 (IOException, SQLException)
- **Unchecked Exception**: RuntimeException 계열, 개발자 실수 (NullPointerException, IllegalArgumentException)

---

## 5. IllegalArgumentException 과 사용자 정의 예외
- **IllegalArgumentException**: 잘못된 매개변수 전달 시 던지는 표준 런타임 예외
- **사용자 정의 예외**
  ```java
  class MyException extends RuntimeException {
      public MyException(String msg) {
          super(msg);
      }
  }
  ```  

---

## 6. finally 블록의 역할
- 예외 발생 여부와 관계없이 무조건 실행됨
- 주로 파일, DB, 네트워크 연결 같은 **자원 해제** 용도

---

## 7. 스코프(scope) 개념
- 변수/메소드의 **유효 범위**
- 종류: 지역 스코프, 클래스 스코프, 전역 스코프
- 이유: 메모리 효율성, 가독성, 버그 예방

---

## 8. static 키워드
- 클래스 차원의 멤버 (객체 없이 사용 가능)
- 모든 인스턴스가 공유
- 장점: 공통 데이터 관리, 유틸 메서드 제공
- 단점: 남발 시 객체지향성 훼손

---

## 9. Java IO의 필요성과 설계 철학
- 필요성: 프로그램 ↔ 외부 세계(파일, 네트워크, DB) 상호작용
- 철학: **유연성, 확장성, 재사용성**
- 구조: **Decorator 패턴** 기반

---

## 10. Decorator 패턴과 주인공 vs 장식
- **주인공**: 실제 데이터 소스/목적지 (예: FileInputStream)
- **장식**: 성능·기능 확장 (BufferedInputStream, DataInputStream 등)

👉 비유:  
- 아메리카노 = FileInputStream  
- 휘핑 추가 = BufferedInputStream  
- 카라멜 추가 = DataInputStream  

---

## 11. 스트림(Stream)의 개념과 분류
- 데이터가 흐르는 **통로**
- 분류:  
  - **바이트 단위 vs 문자 단위**
  - **입력 스트림 vs 출력 스트림**
- 추상 클래스: InputStream, OutputStream, Reader, Writer

---

## 12. 바이트 스트림 (InputStream / OutputStream)
- 모든 데이터는 결국 바이트로 표현
- **InputStream 계열**: FileInputStream, ByteArrayInputStream, BufferedInputStream, DataInputStream
- **OutputStream 계열**: FileOutputStream, ByteArrayOutputStream, BufferedOutputStream, DataOutputStream

---

## 13. ByteArrayInputStream
- 메모리의 byte[] 배열을 스트림처럼 다룸
- 파일 없이도 테스트 가능
- `ByteArrayOutputStream` 과 함께 많이 사용

---

## 14. FileInputStream vs Reader/Writer
- **FileInputStream**: 바이트 단위 → 텍스트도 읽을 수 있지만 인코딩 직접 처리 필요
- **FileReader**: 문자 단위 → 인코딩 자동 처리, 텍스트 전용

---

## 15. BufferedInputStream & DataInputStream (왜 필요한가?)
- **BufferedInputStream**: 버퍼 사용 → 성능 향상 (디스크 접근 최소화)
- **DataInputStream**: 자료형 단위(`int`, `double`, `UTF 문자열`)로 읽기 가능 → 편리성 제공

👉 결국 `FileInputStream`만 쓰면 느리고 불편 → 보조 스트림으로 “휘핑+카라멜” 얹듯이 기능을 확장하는 것  

---

## ✅ 전체 정리
이번에 다룬 내용은 **예외 처리 (throw/throws, Checked vs Unchecked, finally, 사용자 정의 예외)** 와  
**Java IO (스트림 개념, 설계 철학, Decorator 패턴, 바이트/문자 스트림, 보조 스트림)** 이었다.  

👉 핵심:  
- **예외 처리**는 프로그램을 안정적으로 유지하기 위한 필수 장치  
- **Java IO**는 Decorator 패턴을 이용해 기능을 유연하게 조합하는 구조  
