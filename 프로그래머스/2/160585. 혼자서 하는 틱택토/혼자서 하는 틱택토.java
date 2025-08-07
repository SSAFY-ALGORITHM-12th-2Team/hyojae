class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int ocnt = 0, xcnt = 0;
        char[][] map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                map[i][j] = c;
                if (c == 'O') {
                    ocnt++;
                } else if (c == 'X') {
                    xcnt++;
                }
            }
        }
        
        // 가로, 세로, 대각선으로 게임 종료요건 성립했는지 확인 
        boolean ostat = false, xstat = false;
        for (int i = 0; i < 3; i++) {
            // 가로
            if (map[i][0] != '.' && (map[i][0] == map[i][1] &&
                                    map[i][1] == map[i][2])) {
                if (map[i][0] == 'O') {
                    ostat = true;
                } else {
                    xstat = true;
                }
            }
            
            // 세로
            if (map[0][i] != '.' && (map[0][i] == map[1][i] &&
                                    map[1][i] == map[2][i])) {
                if (map[0][i] == 'O') {
                    ostat = true;
                } else {
                    xstat = true;
                }
            }
            
            if (i == 1 && map[1][1] != '.') {
                if (map[i-1][i-1] == map[i][i] && map[i][i] == map[i+1][i+1]) {
                     if (map[i][i] == 'O') {
                        ostat = true;
                    } else {
                        xstat = true;
                    }   
                }
                if (map[i-1][i+1] == map[i][i] && map[i][i] == map[i+1][i-1]) {
                     if (map[i][i] == 'O') {
                        ostat = true;
                    } else {
                        xstat = true;
                    }   
                }
            }
        }
        
        if ((xcnt == 1 && ocnt == 0)
           || (Math.max(xcnt, ocnt) - Math.min(xcnt, ocnt) >= 2)
           || (ostat && xstat)
           || (ocnt == 4 && xcnt == 5)
           || (xstat && (ocnt != xcnt))
           || (ostat && (ocnt - xcnt != 1))) {
            answer = 0;
        }
        
        return answer;
    }
}

/*
# 이상한 상황 케이스
- O나 X가 2개 이상 많다
- X : 1, O : 0
- X : 3, O : 3
- O > 3 || X > 3
*/