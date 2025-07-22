import java.util.*;

class Node {
    int num;
    Set<Node> win = new HashSet<>();
    Set<Node> lose = new HashSet<>();
    boolean winFlag;
    boolean loseFlag;
    
    Node (int num) { 
        this.num = num;
    }
    
    Set<Node> initWin() {
        if (winFlag) {
            return win;
        }
        for (Node n : Set.copyOf(win)) {
            win.addAll(n.initWin());
        }
        winFlag = true;
        return win;
    }
    
    Set<Node> initLose() {
        if (loseFlag) {
            return lose;
        }
        for (Node n : Set.copyOf(lose)) {
            lose.addAll(n.initLose());
        }
        loseFlag = true;
        return lose;
    }
}

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new Node(i));
        }
        for (int[] result : results) {
            nodeList.get(result[0]).win.add(nodeList.get(result[1]));
            nodeList.get(result[1]).lose.add(nodeList.get(result[0]));
        }
        
        for (int i = 1; i <= n; i++) {
            Node node = nodeList.get(i);
            node.initWin();
            node.initLose();
            // System.out.printf("Node %d WIN : %d, LOSE : %d\n", i, node.win.size(), node.lose.size());
            if (node.win.size() + node.lose.size() == n-1)
                answer++;
        }
        
        return answer;
    }
}

/*
내 순위를 알려면 내가 몇명한테 지고, 몇명한테 이겨야하는지를 알아야하는거 아님?
그리고 그게 전체 인원 - 1 이어야하고, n이 100이니까 전수조사는 가능함 
*/