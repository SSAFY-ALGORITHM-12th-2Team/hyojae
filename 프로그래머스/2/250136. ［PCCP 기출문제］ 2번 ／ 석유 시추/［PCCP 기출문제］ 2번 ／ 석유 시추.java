import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] land) {
        int answer = 0;
        int N = land.length;
        int M = land[0].length;
        boolean[][] v = new boolean[N][M];
        int[] mounts = new int[M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && land[i][j] == 1) {
                    List<Integer> info = calculate(i, j, land, v);
                    int cnt = info.get(info.size()-1);
                    for (int k = 0; k < info.size() - 1; k++) {
                        mounts[info.get(k)] += cnt;
                    }
                }
            }
        }
        
        return Arrays.stream(mounts).max().getAsInt();
    }
    
    List<Integer> calculate(int r, int c, int[][] land, boolean[][] v) {
        Set<Integer> result = new HashSet<>();
        result.add(c);
        v[r][c] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        int cnt = 1;
        
        
        int nr, nc;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            for (int i = 0; i < dr.length; i++) {
                nr = curr[0] + dr[i];
                nc = curr[1] + dc[i];
                
                if (nr < 0 || nr >= land.length || nc < 0 || nc >= land[0].length 
                   || v[nr][nc] || land[nr][nc] == 0) {
                    continue;
                }
                
                v[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                result.add(nc);
                cnt++;
            }
        }
        
        List<Integer> list = new ArrayList(result);
        list.add(cnt);
        return list;
    }
}

/*
dfs를 하면서 열만 저장하면 된다
*/