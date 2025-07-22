import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        int[][] parr = new int[2][sequence.length];
        for (int i = 0; i < 2; i++) {
            int m = (int) Math.pow(-1, i);
            for (int j = 0; j < sequence.length; j++, m *= -1) {
                parr[i][j] = sequence[j] * m;
            }
        }
        
        // 구간합 최대 구하기
        for (int i = 0; i < 2; i++) {
            long currSum = parr[i][0];
            long maxSum = currSum;
            for (int j = 1; j < sequence.length; j++) {
                currSum = Math.max(parr[i][j], currSum + parr[i][j]);
                maxSum = Math.max(maxSum, currSum);
            }
            answer = Math.max(answer, maxSum);
        }
        return answer;
    }
}

/*
원본배열을 복제해서 펄스연속수열 2개를 만들고
2개에서 가장 큰 부분합을 찾는다 
*/