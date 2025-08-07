import java.util.*;
import java.io.*;

public class Main {
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  int N = Integer.parseInt(br.readLine());
	  nums = new int[N];
	  for (int i = 0; i < N; i++) {
	  	nums[i] = Integer.parseInt(br.readLine());
	  }
	  Arrays.sort(nums);
	  
	  // a + b + c = d -> a + b = d - c
	  List<Integer> plus = new ArrayList<>();
	  for (int i = 0; i < N; i++) {
	  	for (int j = i; j < N; j++) {
	  		plus.add(nums[i] + nums[j]);
	  	}
	  }
	  
	  Collections.sort(plus);
	  for (int i = N-1; i >= 0; i--) {
	  	for (int j = i-1; j >= 0; j--) {
	  		if (Collections.binarySearch(plus, nums[i] - nums[j]) >= 0) {
	  			System.out.println(nums[i]);
	  			return;
	  		}
	  	}
	  }
	}
}