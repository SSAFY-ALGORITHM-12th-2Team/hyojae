import java.util.*;
import java.io.*;

public class Main {
	static int answer;
	static int N, T, nums[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// 그래프를 만든다. 단방향 그래프
			Map<Integer, Integer> edges = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				edges.put(i+1, nums[i]);
			}
			
			// 사이클 검사한다. 방문배열과 DFS로 
		  boolean[] visit = new boolean[N+1];
		  int cnt = 0;
		  for (int i = 1; i <= N; i++) {
		  	if (visit[i]) continue;
		  	cnt++;
		  	dfs(i, edges, visit);
		  }
		  
		  sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int v, Map<Integer, Integer> edges, boolean[] visit) {
		visit[v] = true;
		
		int next = edges.get(v);
		if (!visit[next]) {
			dfs(next, edges, visit);
		}
	}
}

/*
 * 
 *    
 */