import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++)
            adjList[i] = new ArrayList<>();
        
        for (int[] road : roads) {
            adjList[road[0]].add(road[1]);
            adjList[road[1]].add(road[0]);
        }
        
        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);
        distance[destination] = 0;
        boolean[] v = new boolean[n+1];
        v[destination] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        
        int dis = 1;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int curr = q.poll();
            
                for (int adj : adjList[curr]) {
                    if (v[adj]) continue;
                    v[adj] = true;
                    distance[adj] = dis;
                    q.offer(adj);
                }    
            }
            dis++;
        }
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]];
        }
        
        return answer;
    }
}