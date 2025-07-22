import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> (a[1] - b[1]));
        boolean[] visit = new boolean[routes.length];
        for (int i = 0; i < routes.length; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            answer++;
            for (int j = i+1; j < routes.length; j++) {
                if (routes[j][0] > routes[i][1]) 
                    break;
                
                if (routes[j][0] <= routes[i][1])
                    visit[j] = true;
            }
        }
        
        return answer;
    }
}