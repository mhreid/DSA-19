import java.util.Arrays;
import java.util.HashMap;

public class FrequencyPrint {
    //Runtime is O(N)
    static String frequencyPrint(String s) {
        String[] words = s.split(" ");
        HashMap<String, Integer> hist = new HashMap<>();
        //This loop is O(N)
        for(int i = 0; i < words.length; i++){
            int temp = hist.getOrDefault(words[i], 0) + 1;
            hist.put(words[i], temp);
        }
        String[] out = new String[words.length];
        //This fill is O(N)
        Arrays.fill(out, "");
        //This amortizes to O(N)
        for (HashMap.Entry<String, Integer> entry : hist.entrySet()) {
            String temp = "";
            for(int i = 0; i < entry.getValue(); i++){
                temp += entry.getKey() + " ";
            }
            out[entry.getValue()] = out[entry.getValue()] + temp;
        }

        String ret = "";
        //O(N)
        for(int i = 0; i < out.length; i++){
                ret += out[i];
        }
        return ret;
    }


}
