import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int solution(String[] board) {
        int answer = -1;
        int N = board.length;
        int M = board[0].length();
        
        char[][] map = new char[N][M];
        int sr = -1, sc = -1, er = -1, ec = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    sr = i;
                    sc = j;
                } else if (map[i][j] == 'G') {
                    er = i;
                    ec = j;
                }
            }
        }
        
        boolean[][] v = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        v[sr][sc] = true;
        
        int nr, nc;
        int loop = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int[] curr = q.poll();
                
                // 정답 도착
                if (curr[0] == er && curr[1] == ec) {
                    answer = loop;
                    return answer;
                }
                
                for (int j = 0; j < dr.length; j++) {
                    nr = curr[0] + dr[j];
                    nc = curr[1] + dc[j];
                    
                    while (nr >= 0 && nr < N && nc >= 0 && nc < M 
                           && map[nr][nc] != 'D') {
                        nr += dr[j];
                        nc += dc[j];
                    }
                    
                    // 이미 도착한적 있는지 확인
                    nr -= dr[j];
                    nc -= dc[j];
                    
                    if (v[nr][nc]) continue;
                    
                    v[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            loop++;
        }
        
        // for (boolean[] row : v) {
        //     System.out.println(Arrays.toString(row));
        // }
        
        return answer;
    }
}