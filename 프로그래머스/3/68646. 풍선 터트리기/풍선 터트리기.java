class Solution {
    public int solution(int[] a) {
        int answer = a.length;
        int MAX_VAL = 1000000000;
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        leftMin[0] = MAX_VAL; 
        rightMin[a.length-1] = MAX_VAL;
        
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i-1]);
            leftMin[i] = min;
        }
        
        min = a[a.length-1];
        for (int i = a.length-2; i >= 0; i--) {
            min = Math.min(min, a[i+1]);
            rightMin[i] = min;
        }
        
        for (int i = 0; i < a.length; i++) {
            if (leftMin[i] < a[i] && rightMin[i] < a[i]) {
                answer--;
            }
        }
        return answer;
    }
}

/*
특정 인덱스에 좌, 우에 최솟값이 현재 인덱스값보다 둘다 작으면, 이곳은 못 남기는 곳임 
그럼 인덱스마다 좌우 최솟값을 다 저장한다
그리고 순회하면서 카운트한다. 끗
*/