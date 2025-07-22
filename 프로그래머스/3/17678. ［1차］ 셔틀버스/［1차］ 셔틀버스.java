import java.util.*;
import java.util.Map.*;

class Time implements Comparable<Time> {
    int hour, min;
    
    Time (String timetable) {
        String[] split = timetable.split(":");
        hour = Integer.parseInt(split[0]);
        min = Integer.parseInt(split[1]);
    }
    
    Time (Time time, int min) {
        int minSum = time.min + min;
        if (minSum >= 60) {
           int addedHour = minSum / 60;
           hour = time.hour + addedHour;
           this.min = minSum % 60;
        } else {
            hour = time.hour;
            this.min = minSum;
        }
    }
    
    Time subtract(int min) {
        if (this.min == 0) {
            return new Time(hour-1+":"+59);
        }
        return new Time(hour+":"+(this.min-1));
    }
    
    @Override
    public int compareTo(Time t) {
        int hourComp = Integer.compare(hour, t.hour);
        if (hourComp != 0) return hourComp;
        return Integer.compare(min, t.min);
    }
    
    String print() {
        return hour + ":" + min;
    }
}

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        TreeMap<Time, Integer> timeMap = new TreeMap<>();
        List<Time> timeList = new ArrayList<>();
        for (String time : timetable) {
            Time curr = new Time(time);
            timeMap.compute(curr, (k, v) -> v == null ? 1 : v + 1);
        }
        // for (Entry<Time, Integer> entry : timeMap.entrySet()) {
        //     System.out.println(entry.getKey().print() + " : " + entry.getValue());
        // }
        
        TreeMap<Time, List<Time>> busMap = new TreeMap<>();
        Time startTime = new Time("09:00");
        for (int i = 0; i < n; i++) {
            Time currBus = new Time(startTime, t*i);
            List<Time> list = busMap.getOrDefault(currBus, new ArrayList<>());
            NavigableMap<Time, Integer> headMap = timeMap.headMap(currBus, true);
            
            int cnt = m;
            while (cnt > 0 && !headMap.isEmpty()) {
                Entry<Time, Integer> entry = headMap.firstEntry();
                Time key = entry.getKey();
                while (timeMap.get(key) > 0 && cnt > 0) {
                    list.add(key);
                    int val = timeMap.get(key);
                    timeMap.put(key, val-1);
                    cnt--;
                }
                
                if (entry.getValue() == 0) {
                    timeMap.pollFirstEntry();
                }
            }
            busMap.put(currBus, list);
        }
        
        // System.out.println(timeMap.size());
        Time lastKey = busMap.lastKey();
        List<Time> times = busMap.get(lastKey);
        if (times.size() < m) {
            // 수용 가능하다면
            return String.format("%02d:%02d", lastKey.hour, lastKey.min);
        }
        
        Time lastTime = times.get(times.size()-1);
        Time finalTime = lastTime.subtract(1);
        return String.format("%02d:%02d", finalTime.hour, finalTime.min);
    }
}

/*
버스 도착시간은 정해져있고, 해당 시간까지 도착한 크루원들을 태우고감 
아무리 늦게타도 18:00 
버스 시간마다 몇명타는지를 저장한다 

*/