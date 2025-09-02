# Collections

---

**java.util.Collections** 

→  컬렉션(리스트, 세트, 맵 등)을 편리하게 다루도록 도와주는 유틸리티 클래스

---

## Collections의 대표 기능

---

1. **정렬 (sort)**
    
    ```java
    List<String> list = new ArrayList<>();
    list.add("C");
    list.add("A");
    list.add("B");
    
    Collections.sort(list); //[A, B, C]
    ```
    
2. **섞기 (shuffle)**
    
    ```java
    Collections.shuffle(list); // 랜덤하게 섞음
    ```
    
3. **최댓값/최솟값 (max/min)**
    
    ```java
    int max = Collections.max(listOfNumbers);
    int min = Collections.min(listOfNumbers);
    ```
    
4. **이진 탐색 (binarySearch) -** 단 정렬된 상태여야 함
    
    ```java
    int idx = Collections.binarySearch(list, key);
    ```
    
5. **채우기(fill)**
    
    ```java
    Collections.fill(list, "Hello"); // 모든 값을 "Hello"로 바꿈
    ```