import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  int N = Integer.parseInt(br.readLine());
	  nums = new int[N];
	  int left = 1, right = 0;
	  StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
	  for (int i = 0; i < N; i++) {
	  	int val = Integer.parseInt(st.nextToken());
	  	right = Math.max(right, val);
	  	nums[i] = val;
	  }
	  int M = Integer.parseInt(br.readLine());
	  Arrays.sort(nums);
	  int max = right;
	  int answer = 0;
	  while (left <= right) {
	  	int mid = (left + right) / 2;
	  	// 상한을 mid로 정함
	  	long sum = 0;
	  	boolean flag = false;
	  	for (int i = 0; i < N; i++) {
	  		if (nums[i] < mid) {
	  			sum += nums[i];
	  		} else {
	  			flag = true;
	  			sum += mid;
	  		}
	  	}
	  	
	  	if (sum <= M) {
	  		answer = Math.max(answer, mid);
	  		left = mid + 1;
	  	} else {
	  		right = mid - 1;
	  	}
	  }
	  
	  System.out.println(answer);
	}
}