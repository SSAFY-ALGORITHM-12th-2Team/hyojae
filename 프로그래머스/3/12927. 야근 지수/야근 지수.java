import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works)
            pq.offer(work);
        
        while (n > 0 && !pq.isEmpty()) {
            int num = pq.poll();
            if (num > 1) {
                pq.offer(num-1);
            }
            n--;
        }
        
        for (Integer num : pq) {
            answer += num * num;
        }
        
        return answer;
    }
}

/*
제곱은 수가 클수록 커지니까 제일 큰수를 깍아내주면 되는거 아님?
그리디 같은데
*/