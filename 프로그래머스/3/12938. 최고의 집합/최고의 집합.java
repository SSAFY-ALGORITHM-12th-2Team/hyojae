import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }
        
        int remain = s % n; // n보단 작음, 즉 다 분배가능
        int numerator = s / n;
        
        int[] answer = new int[n];
        for (int i = n-1; i >= 0; i--) {
            answer[i] = --remain >= 0 ? numerator + 1 : numerator;
        }
        
        return answer;
    }
}

/*
S < N -> 못만들지 
N <= S 만들순 있다..
최대 곱을 만드려면 최대한 원소끼리의 차이가 없어야해 
S를 N으로 나눠 그럼 나머지가 생기겠죠?
걔를 하나씪 나눠줘 원소마다 

*/