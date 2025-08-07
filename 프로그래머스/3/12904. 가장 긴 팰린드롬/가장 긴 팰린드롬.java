class Solution
{
    Boolean[][] memo;
    public int solution(String s)
    {
        int answer = 1;
        int len = s.length();
        memo = new Boolean[len][len];
        for (int i = 0; i < len; i++) {
            memo[i][i] = true;
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (isPalindrom(i, j, s)) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }
        return answer;
    }
    
    boolean isPalindrom(int i, int j, String str) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        
        if (str.charAt(i) == str.charAt(j)) {
            if (i + 1 == j) {
                return memo[i][j] = true;
            }   
            return memo[i][j] = isPalindrom(i+1, j-1, str);
        }
        
        return memo[i][j] = false;
    }
}

/*
0 ~ N-1까지가 펠린드롬인지 확인하려면
0과 n-1이 같아야죠 일단 ? 그리고 그 안에도 같아야함 
A == B이면 무조건 1이죠 
 
*/