class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int maxSum = 0;
        for (int num : cookie) {
            maxSum += num;
        }
        maxSum /= 2;
        int l, r, lsum, rsum;
        for (int i = 0; i < cookie.length; i++) {
            l = r = i;
            lsum = 0;
            rsum = cookie[i];
            
            while (l >= 0 && r < cookie.length) {
                if (lsum < rsum) {
                    if (--l < 0) break;
                    lsum += cookie[l];
                } else if (lsum > rsum) {
                    if (++r >= cookie.length) break;
                    rsum += cookie[r];
                } else {
                    answer = Math.max(answer, lsum);
                    if (answer == maxSum) {
                        return answer;
                    }
                    if (l > 0) {
                        lsum += cookie[--l]; 
                    } else if (r < cookie.length-1) {
                        rsum += cookie[++r];
                    }
                }
            }
        }
        return answer;
    }
}

/*
l <= m, m+1 <= r -> 총 r-l+1개의 바구니 
구간합? 최대값 
l, m, r -> l, r의 2차원 DP? 
*/