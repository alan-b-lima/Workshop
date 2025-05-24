package visual.tui;

import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
        HashMap map = new HashMap<String, Integer>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);

        System.out.println(map.get("one"));
    }
}
