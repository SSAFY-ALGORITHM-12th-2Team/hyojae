import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	static int N, M, K, grid[][], result = Integer.MAX_VALUE;
	static int[][] orderArr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		orderArr = new int[K][3];
		grid = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) grid[i][j] = sc.nextInt();
		}
		
		// 순서는 임의로 정해도된다. 돌리는 순서를 수열로 빼야하네
		// 값을 복사해서 넘겨야하네
		
		result = Integer.MAX_VALUE;
		
		// command 배열 저장
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 3; j++) {
				orderArr[i][j] = sc.nextInt();
			}
		}
		
		permutation(0, new int[K], new boolean[K]);
		
		System.out.println(result);
		
		sc.close();
	}
	
	private static int[][] copyArr() {
		int [][] tmpArr = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			tmpArr[i] = grid[i].clone();
		}
		return tmpArr;
	}

	private static void permutation(int k, int[] sel, boolean[] visit) {
		if (k == sel.length) {
			// 여기서 정해진 순서대로 배열 돌림
			int [][] copyArr = copyArr();
			
			for (int i = 0; i < sel.length; i++) {
				int r = orderArr[sel[i]][0];
				int c = orderArr[sel[i]][1];
				int s = orderArr[sel[i]][2];
				
				rotate(r, c, s, copyArr);
				// 최소값 구하기
			}
			updateMinRowSum(copyArr);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			sel[k] = i;
			permutation(k+1, sel, visit);
			visit[i] = false;
		}
	}

	private static void updateMinRowSum(int [][] grid) {
		for (int i = 1; i <= N; i++) {
			result = Math.min(result, IntStream.of(grid[i]).sum());
		}
	}

	// 방향은 아래 오른쪽 위 왼쪽
	static int [] dr = {1, 0, -1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	private static void rotate(int r, int c, int s, int [][] grid) {
		int len = s*2 + 1; // 항상 정사각형
		
		int sr = r-s, sc = c-s;
		// 방향은 아래, 오른쪽, 위, 왼쪽
		for (int rotate = 0; rotate < s; rotate++) {
			// 전체 돌리는 횟수
			int temp = grid[sr][sc];
			
			for (int i = 0; i < dr.length; i++) {
				for (int j = 1; j < len - 2 * rotate; j++) {
					grid[sr][sc] = grid[sr + dr[i]][sc + dc[i]];
					sr += dr[i];
					sc += dc[i];
				}
			}
			
			// 임시값 옮겨줌
			grid[sr][sc+1] = temp;
			
			// sr, sc 1씩 증가, 즉 안쪽 껍데기 돌림
			sr++; sc++;
		}
	}
}

