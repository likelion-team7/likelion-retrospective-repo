package study;

import java.util.*;

public class CollectionsExam {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7));

        // 1. 정렬
        Collections.sort(numbers);
        System.out.println("정렬된 리스트: " + numbers);

        // 2. 섞기
        Collections.shuffle(numbers);
        System.out.println("셔플된 리스트: " + numbers);

        // 3. 최대값 / 최소값
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        System.out.println("최댓값: " + max + ", 최솟값: " + min);

        Collections.sort(numbers); // 순서 재 정렬(이진 탐색 전 필수)
        // 4. 이진 탐색
        int index = Collections.binarySearch(numbers, 7);
        System.out.println("값 7의 인덱스: " + index);

        // 5. 채우기
        Collections.fill(numbers, 0);
        System.out.println("채운 후 리스트: " + numbers);
    }
}
