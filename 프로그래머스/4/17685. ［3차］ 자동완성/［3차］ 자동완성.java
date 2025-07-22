import java.util.*;

class Node {
    Map<Character, Node> children = new HashMap<>();
    boolean isLeaf;
    int passCnt;
    
    Node addChild(char c, Node n) {
        Node node = children.computeIfAbsent(c, k -> n);
        node.passCnt += 1;
        return node;
    }
}

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node child = new Node();
                curr = curr.addChild(c, child);
            }    
            curr.isLeaf = true;
        }
        
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            Node curr = q.poll();
            // 자식들 순회
            for (Node child : curr.children.values()) {
                // passCnt만큼 더해줌
                answer += child.passCnt;
                // passCnt가 1이면 여기가 끝임 
                if (child.passCnt > 1) {
                    q.offer(child);
                }
            }
        }
        return answer;
    }
}

/*
trie인거같은데? 종료는 단어가 끝나던가, 그 이후에 그 단어밖에 없던가 
Node에 어떤 정보를 저장할 것인가
- 하위 자식들 (a-z)
- 리프 노드인지 아닌지
- 이 노드를 지나온 개수? 
*/