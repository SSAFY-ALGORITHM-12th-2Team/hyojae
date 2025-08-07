import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);
        
        boolean[] v = new boolean[targets.length];
        for (int i = 0; i < targets.length; i++) {
            if (v[i]) continue;
            v[i] = true;
            answer++;
            for (int j = i+1; j < targets.length; j++) {
                if (targets[j][0] < targets[i][1]) {
                    v[j] = true;
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}