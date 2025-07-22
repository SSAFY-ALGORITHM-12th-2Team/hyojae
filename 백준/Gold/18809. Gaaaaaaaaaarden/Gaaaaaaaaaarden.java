import java.util.*;
import java.io.*;

public class Main {
    static int N, M, G, R;
    static int[][] map;
    static List<int[]> candidates = new ArrayList<>();
    static int maxFlower = 0;

    static final int BLANK = 0;
    static final int LAKE = 0;
    static final int GROUND = 1;
    static final int GARDEN = 2;

    static final int GREEN = 1;
    static final int RED = 2;
    static final int FLOWER = 3;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Cell {
        int r, c, color, time;

        public Cell(int r, int c, int color, int time) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == GARDEN) {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        combGreen(0, 0, new ArrayList<>());
        System.out.println(maxFlower);
    }

    // 1) Green 조합
    static void combGreen(int idx, int g, List<Integer> greens) {
        if (g == G) {
            combRed(0, 0, greens, new ArrayList<>());
            return;
        }
        if (idx == candidates.size()) return;

        greens.add(idx);
        combGreen(idx + 1, g + 1, greens);
        greens.remove(greens.size() - 1);
        combGreen(idx + 1, g, greens);
    }

    // 2) Red 조합 (Green 중복 방지)
    static void combRed(int idx, int r, List<Integer> greens, List<Integer> reds) {
        if (r == R) {
            simulate(greens, reds);
            return;
        }
        if (idx == candidates.size()) return;

        if (!greens.contains(idx)) {
            reds.add(idx);
            combRed(idx + 1, r + 1, greens, reds);
            reds.remove(reds.size() - 1);
        }
        combRed(idx + 1, r, greens, reds);
    }

    // 3) BFS 퍼짐 시뮬레이션
    static void simulate(List<Integer> greens, List<Integer> reds) {
        int[][] visited = new int[N][M]; // 0: 미방문, 1: Green, 2: Red, 3: Flower
        int[][] time = new int[N][M];
        Queue<Cell> q = new ArrayDeque<>();

        for (int g : greens) {
            int[] p = candidates.get(g);
            visited[p[0]][p[1]] = GREEN;
            q.offer(new Cell(p[0], p[1], GREEN, 0));
        }
        for (int r : reds) {
            int[] p = candidates.get(r);
            visited[p[0]][p[1]] = RED;
            q.offer(new Cell(p[0], p[1], RED, 0));
        }

        int flowerCount = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Cell curr = q.poll();
                if (visited[curr.r][curr.c] == FLOWER) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dr[d];
                    int nc = curr.c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] == LAKE) continue;
                    if (visited[nr][nc] == FLOWER) continue;

                    if (visited[nr][nc] == 0) {
                        visited[nr][nc] = curr.color;
                        time[nr][nc] = curr.time + 1;
                        q.offer(new Cell(nr, nc, curr.color, curr.time + 1));
                    } else if (visited[nr][nc] != curr.color && time[nr][nc] == curr.time + 1) {
                        // 다른 색이 같은 시간에 퍼지면 꽃
                        visited[nr][nc] = FLOWER;
                        flowerCount++;
                    }
                }
            }
        }

        maxFlower = Math.max(maxFlower, flowerCount);
    }
}
