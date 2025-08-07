import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int nA = Integer.parseInt(st.nextToken());
		int nB = Integer.parseInt(st.nextToken());
		Arrays.asList(new int[3]);
		TreeSet<Integer> aSet = new TreeSet<>(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
		TreeSet<Integer> bSet = new TreeSet<>(Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
		aSet.removeAll(bSet);
		System.out.println(aSet.size());
		StringBuilder sb = new StringBuilder();
		for (Integer num : aSet) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}