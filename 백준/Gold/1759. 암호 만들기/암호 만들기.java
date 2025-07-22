import java.util.*;
import java.io.*;

public class Main {
	static final String VOWEL = "aeiou";
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String[] alpha = br.readLine().split(" "); 
		
		// cnt = 자음 개수 + 모음 개수 
		// index, 자음 개수, 모음 개수, new String array, alpha 
		Arrays.sort(alpha);
		recursive(0, 0, 0, new String[L], alpha);
	}

	private static void recursive(int index, int consCnt, int vowelCnt, String[] selected, String[] alpha) {
		int cnt = consCnt + vowelCnt;
		
		// END
		if (cnt == L) {
			// constraint
			if (consCnt >= 2 && vowelCnt >= 1) {
				System.out.println(String.join("", selected));
			}
			return;
		}
		
		for (int i = index; i < alpha.length; i++) {
			selected[cnt] = alpha[i];
			if (isVowel(alpha[i])) {
				recursive(i+1, consCnt, vowelCnt+1, selected, alpha);
			} else {
				recursive(i+1, consCnt+1, vowelCnt, selected, alpha);
			}
		}
	}

	private static boolean isVowel(String target) {
		int index = VOWEL.indexOf(target);
		return index >= 0 ? true : false;
	}
}

/*
 * L = 암호를 이루는 서로 다른 알파벳의 개수
 * C = 문자의 종류 개수
 * 3 <= L <= C <= 15 
 * 모음(a, e, i, o, u) 최소 1개, 자음 최소 2개 -> 이게 제약 조건? 인자로 넘긴다?
 * 자음,모음을 직접 구분해줘야하나? w y이런애는?;; 깍두기인가 아니다 자음으로 치는거같다 
 * 
 * 
 * 
 */