package example.com.vtt;

/**
 * Created by GirishM on 07-11-2017.
 */

class MakeCode_ {

    static String getCode(String word) {
        word = word.toLowerCase();
        if (word.contains(" ")) {
            word = word.replaceAll(" ", "_");
        }
        return word;
    }
}
