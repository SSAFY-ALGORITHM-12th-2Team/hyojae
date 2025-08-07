class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int cnt = 0, currTime = 1, life = health;
        for (int[] attack : attacks) {
            while (currTime++ < attack[0]) {
                life = Math.min(health, life + bandage[1]);
                if (++cnt == bandage[0]) {
                    life = Math.min(health, life + bandage[2]);
                    cnt = 0;
                }
            }
            life -= attack[1];
            cnt = 0;
            if (life <= 0) {
                return -1;
            }
        }
        
        return life;
    }
}