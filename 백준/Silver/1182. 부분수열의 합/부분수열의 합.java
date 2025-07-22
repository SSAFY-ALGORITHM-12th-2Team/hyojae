import java.util.*;
import java.io.*;

public class Main {
	static int answer;
	static int S, nums[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		// 선택/미선택 모든 경우의 수 구하기
		recursive(0, new boolean[N]);
		System.out.println(answer);
	}

	private static void recursive(int idx, boolean[] sel) {
		if (idx == sel.length) {
			// sum 구하고 target과 일치 확인
			int sum = 0, cnt = 0;
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					sum += nums[i];
					cnt++;
				}
			}
			if (sum == S && cnt > 0) {
				answer++;
			}
			return;
		}
		
		sel[idx] = true;
		recursive(idx+1, sel);
		sel[idx] = false;
		recursive(idx+1, sel);
	}

}

/*
 * 
 * 
 *  
 */