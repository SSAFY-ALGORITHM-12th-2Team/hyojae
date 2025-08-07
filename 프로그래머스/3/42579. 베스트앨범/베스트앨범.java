import java.util.*;

class Stat {
    String genre;
    PriorityQueue<Music> musics = new PriorityQueue<>((m1, m2) -> m2.playtime - m1.playtime);
    int totalPlayTime;
    
    Stat (String genre) {
        this.genre = genre;
    }
    
    void addMusic(Music music) {
        this.musics.add(music);
        this.totalPlayTime += music.playtime;
    }
}

class Music {
    int index;
    int playtime;
    
    Music (int index, int playtime) {
        this.index = index;
        this.playtime = playtime;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Stat> statMap = new HashMap<>();
        for (int i = 0; i < plays.length; i++) {
            Music music = new Music(i, plays[i]);
            String genre = genres[i];
            statMap.putIfAbsent(genre, new Stat(genre));
            Stat stat = statMap.get(genre);
            stat.addMusic(music);
        }
        
        List<Stat> statList = new ArrayList(statMap.values());
        Collections.sort(statList, (s1, s2) -> s2.totalPlayTime - s1.totalPlayTime);
        
        for (Stat stat : statList) {
            for (int i = 0; i < 2 && !stat.musics.isEmpty(); i++) {
                Music music = stat.musics.poll();
                answer.add(music.index);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

/*
Music 클래스를 만들어서 관리, TreeSet으로 
장르는 Map으로 관리
한 장르안의 음악들도 관리해야함
장르별로 합 계산하고 정렬해서 저장 
*/