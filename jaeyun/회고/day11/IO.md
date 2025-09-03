# I/O

---

# I/O?

- **Input** : 외부에서 프로그램으로 들어오는 데이터
- **Output**: 프로그램에서 외부로 내보내는 데이터

- 외부 장치 간 데이터 흐름을 다루는 시스템

---

## 스트림(Stream)

한 방향으로 흐르는 데이터 파이프

`[데이터 소스] → [입력 스트림] → [프로그램] → [출력 스트림] → [데이터 대상]`

## 바이트 스트림

- **InputStream, OutputStream** 계열 클래스
- 모든 종류 데이터 처리가능 (예: 이미지, 동영상, 이진 파일 등)
- 예: `FileInputStream`, `FileOutputStream`, `BufferedInputStream`, `BufferedOutputStream`
    - 구현
        - 파일: `FileInputStream`, `FileOutputStream`
        - 버퍼: `BufferedInputStream`, `BufferedOutputStream`
        - 데이터 형식: `DataInputStream`, `DataOutputStream`
        - 출력 : `PrintStream` (ex. `System.out`)

## 문자 스트림

- **Reader, Writer** 계열 클래스
- 문자 데이터 처리
- 예:  `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`
    - 구현
        - 파일: `FileReader`, `FileWriter`
        - 버퍼: `BufferedReader`, `BufferedWriter`
        - 변환: `InputStreamReader`(바이트→문자), `OutputStreamWriter`(문자→바이트)

---

### InputStream 예제 (파일 읽기)

```java
import java.io.*;

public class InputStreamExample {
    public static void main(String[] args) {
        String sourceFile = "data.txt";

        try (InputStream in = new FileInputStream(sourceFile)) {
            int b;
            while ((b = in.read()) != -1) { 
                System.out.print((char) b);   
            }

            System.out.println("\n파일 읽기 완료!");

        } catch (IOException e) { // 파일이 없거나 읽기 중 오류 발생 시 출력
            e.printStackTrace();
        }
    }
}
```

- `“data.txt.”` 파일 대상으로 `FileInputStream`으로 파일 읽기 스트림 생성
- `in.read()` → 1바이트 읽기 (읽을 데이터가 없으면 `read()`에서 -1 반환 → 파일 끝을 의미)
- 읽은 바이트를 (char)로 변환 → 화면에 출력

### OutputStream 예제 (파일 쓰기)

```java
import java.io.*;

public class OutputStreamExample {
    public static void main(String[] args) {
        String destFile = "data.txt"; //생성/쓰기할 파일 이름
        String content = "Hello, Java I/O!"; // 파일에 쓸 문자열

        try (OutputStream out = new FileOutputStream(destFile)) {
            byte[] bytes = content.getBytes(); 
            out.write(bytes);                  

            System.out.println("파일 쓰기 완료!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

- 문자열을 **바이트 배열**로 변환 → `write()`로 파일에 기록
- 실행하면 `“data.txt”` 파일에 `“Hello, Java I/O!”`로 작성됨