package example.com.vtt;

import java.util.HashMap;

/**
 * Created by GirishM on 07-11-2017.
 */

public class Dictionary_ {

    private static HashMap<String, Integer> dictionary() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("light_on", 1);
        map.put("lights_on", 2);
        map.put("light_off", 3);
        map.put("lights_off", 4);
        map.put("on", 5);
        map.put("off", 6);
        map.put("", 7);
        map.put("", 8);
        map.put("", 9);
        map.put("", 10);

        map.put("", 11);
        map.put("", 12);
        map.put("", 13);
        map.put("", 14);
        map.put("", 15);
        map.put("", 16);
        map.put("", 17);
        map.put("", 18);
        map.put("", 19);
        map.put("", 20);

        return map;
    }

    static int getCode(String code) {
        if (dictionary().containsKey(code)) {
            return dictionary().get(code);
        }
        return 0;
    }
}
