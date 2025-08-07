import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {0, gems.length-1};
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(gems).distinct()
            .forEach(gem -> map.put(gem, 0));
        System.out.println(map.size());
        int target = map.size();
        int cnt = 0;
        int minLen = gems.length + 1;
        
        int left = 0, right = 0;
        while (left <= right) {
            if (cnt < target) {
                if (right == gems.length) break;
                int newVal = map.compute(gems[right++], (k, v) -> v + 1);
                if (newVal == 1) {
                    cnt++;
                }
            } else if (cnt == target) {
                if (right - left < minLen) {
                    answer[0] = left + 1;
                    answer[1] = right;
                    minLen = right - left;
                }
                // 왼쪽 꺼 빼줌
                int newVal = map.compute(gems[left++], (k, v) -> v - 1);
                if (newVal == 0) {
                    cnt--;
                }
            }
        }
        
        if (cnt == target && right - left < minLen) {
            answer[0] = left + 1;
            answer[1] = right;
        }
        
        return answer;
    }
}

/*
map으로 저장해야하고, 투포인터를 써야한다?
*/