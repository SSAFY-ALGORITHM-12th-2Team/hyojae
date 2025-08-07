import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] lens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(lens);
		
		int right = lens[N-1];
		int left = 1;
		int mid;
		int answer = 0;
		
		while (left <= right) {
			mid = (left + right) / 2;
			// mid의 막대로 모두에게 나눠줄 수 있는지 확인
			int cnt = 0;
			for (int i = N-1; i >= 0 && cnt < M; i--) {
				cnt += lens[i] / mid;
			}
			if (cnt >= M) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}
}

/*
 * 
 *    
 */