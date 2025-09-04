# Object Stream

---

자바 객체를 바이트 스트림으로 변환(직렬화)하여 파일에 저장하거나 네트워크를 통해 전송하고, 다시 바이트 스트림을 객체로 복원(역직렬화) 하는 데 사용되는 기술

---

`java.io.ObjectInputStream`과 `java.io.lObjectOutputStream` 클래스

---
<br>

- **직렬화(Serialization)** : 자바 객체의 상태(필드 값) → 바이트 형태로 변환하는 과정
    - `ObjectOutputStream`을 사용해 writeObject() 메서드를 호출하면 직렬화되어 스트림으로 출력
        - 기본적으로 파일을 덮어씀 → 기존데이터 사라짐

- **역직렬화(Deserialization)** : 바이트 스트림으로 변환된 데이터를 다시 원래의 자바 객체로 복원하는 과정.
    - `ObjectInputStream`을  사용해 readObject() 메서드를 호출하면 바이트 데이터가 객체로 복원

---
<br>
- java.io.Serializable 인터페이스
    
    이 인터페이스를 구현하는 클래스만이 직렬화 될 수 있다.
    
    특별한 메서드 없이 마커(Marker) 인터페이스 역할을 한다. → JVM에게 이 클래스는 직렬화 될 수 있습니다 라고 알려주는 용도
    

```java
import java.io.*;

// 직렬화를 위해 Serializable 인터페이스 구현
class User implements Serializable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}

public class ObjectStreamExample {
    public static void main(String[] args) {
        // 객체 직렬화
        try (FileOutputStream fos = new FileOutputStream("user.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            User user = new User("홍길동", 30);
            **oos.writeObject(user); // 객체 쓰기**
            System.out.println("객체 직렬화 완료: " + user);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 객체 역직렬화
        try (FileInputStream fis = new FileInputStream("user.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            **User deserializedUser = (User) ois.readObject(); // 객체 읽기**
            System.out.println("객체 역직렬화 완료: " + deserializedUser);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```
<br>

**여러 User 객체 ArrayList에 담아 파일에 저장**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class User implements Serializable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}

public class MultipleObjectStreamExample {
    public static void main(String[] args) {
        // 여러 User 객체를 담을 리스트 생성
        List<User> userList = new ArrayList<>();
        userList.add(new User("김철수", 25));
        userList.add(new User("박영희", 28));
        userList.add(new User("이민준", 32));

        // 객체 리스트 직렬화
        try (FileOutputStream fos = new FileOutputStream("users.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userList); // 리스트 객체 하나를 통째로 씀
            System.out.println("여러 객체 직렬화 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 객체 리스트 역직렬화
        try (FileInputStream fis = new FileInputStream("users.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<User> deserializedUserList = (List<User>) ois.readObject(); // 리스트 객체 하나를 통째로 읽음
            System.out.println("여러 객체 역직렬화 완료:");
            for (User user : deserializedUserList) {
                System.out.println(user);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

<aside>
💡

        try (FileOutputStream fos = new FileOutputStream("users.ser");

`user.ser` → `.ser`는 파일이 일반 텍스트가 아니라 직렬화된 자바 객체 데이터를 담고 있음을 명시적으로 알림 . 일종의 약속(`.ser` `.dat`)

</aside>
<br>

---

- **사용처**
    
    개인 프로젝트보다는 데이터를 파일에 저장하거나 네트워크로 전송해야 할 때 사용
    
    1. **서버간 통신** : 분산 시스템에서 한 서버에 있는 객체를 다른 서버로 보내야 할 때(원격 메서드 호출(RMI)
    2. **캐시** : 데이터베이스 부하를 줄이기 위해 자주 사용하는 객체를 캐시에 저장할 때
    3. **데이터 저장** : 프로그램이 종료된 후에도 객체 상태를 유지해야 할 때(객체를 파일에 영구적으로 보관)
    4. **세션관리** : 웹 애플리케이션 서버에서 사용자 세션 정보를 서버 메모리가 아닌 외부 파일이나 데이터베이스에 저장할 때

<aside>
💡

**보안 취약점, JVM 종속성, 성능 등 때문에 JSON, XML , Protocol Buffers와 같은** 

**데이터 포맷을 사용해 객체 데이터를 주고 받는 경우가 많아졌다**

</aside>