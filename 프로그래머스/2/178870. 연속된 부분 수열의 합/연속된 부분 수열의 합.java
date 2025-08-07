class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int left = 0, right = 0;
        int sum = sequence[left];
        int minLen = sequence.length+1;
        
        while (right < sequence.length) {
            if (sum < k) {
                if (right == sequence.length - 1) 
                    break;
                sum += sequence[++right];
            } else if (sum > k) {
                sum -= sequence[left++];
            } else {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    answer[0] = left;
                    answer[1] = right;
                } 
                sum -= sequence[left++];
            }
        }
        return answer;
    }
}