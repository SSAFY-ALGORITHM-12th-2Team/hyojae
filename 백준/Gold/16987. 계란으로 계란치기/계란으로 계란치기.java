import java.util.*;
import java.io.*;

public class Main {
	static int answer;
	static int N, weights[], durablity[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		weights = new int[N];
		durablity = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			durablity[i] = Integer.parseInt(st.nextToken());
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		// 집어든 계란 위치, 깬 계란 수 
		backtrack(0, 0);
		System.out.println(answer);
	}

	private static void backtrack(int curr, int cnt) {
		if (curr == N) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		// 내 손에 든 계란이 깨졌을수도 
	  if (durablity[curr] <= 0) {
	  	backtrack(curr+1, cnt);
	  	return;
	  }
		
		// 모든 계란을 살피며 깨는 시뮬레이션
	  boolean flag = false;
		for (int i = 0; i < N; i++) {
			if (i == curr || durablity[i] <= 0) 
				continue;
			
			// 계란 치는 과정
			int brokenCnt = hitegg(curr, i);
			backtrack(curr+1, cnt + brokenCnt);
			flag = true;
			// 복원
			durablity[curr] += weights[i];
			durablity[i] += weights[curr];
		}
		
		if (!flag) {
			backtrack(curr+1, cnt);
		}
	}

	private static int hitegg(int curr, int ops) {
		int result = 0;
		durablity[curr] -= weights[ops];
		durablity[ops] -= weights[curr];
		
		if (durablity[curr] <= 0)
			result++;
		if (durablity[ops] <= 0)
			result++;
		
		return result;
	}


}

/*
 * 완탐을 해야함 그리고 각 콜스택에서는 환경이 독립적으로 유지해야함
 * 무게 배열은 변하지않음, 반면에 내구도는 각 재귀마다 달라짐
 * 즉 내구도를 빼주고, 다시 복원해주는 과정이 필요함
 * 종료조건은 마지막 계란을 들었을때고 
 * 매 재귀마다 내가 지금 몇번 계란을 들었는지 알아야하고
 * for문을 돌며 내가 든 계란 제외 모두 확인해야함  
 * 
 *  
 */