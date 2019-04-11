import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<char[]> result = new ArrayList<>();
        char[] hours = new char[hoursInDay];
        Arrays.fill(hours, '.');
        helper(0, pennies, nickels, dimes, hours, result);
        return result;
    }

    public static void helper(int i, int p, int n, int d, char[] hours, List<char[]> result){
        if(p == 0 && n == 0 && d == 0){
            boolean a = true;
            for(char c: hours){
                //if(c == '.') a = false;
            }
            if(a) result.add(hours.clone());
        }
        if(p != 0 && (hours[(i + 1) % hours.length] == '.' || (n == 0 && d == 0 && p == 1))){
            hours[i % hours.length] = 'p';
            helper(i + 1, p -1, n, d, hours, result);
            hours[i % hours.length] = '.';
        }
        if(n != 0 && (hours[(i + 5) % hours.length] == '.' ||(p == 0 && d == 0 && n == 1))){
            hours[i % hours.length] = 'n';
            helper(i + 5, p, n - 1, d, hours, result);
            hours[i % hours.length] = '.';
        }
        if(d != 0 && (hours[(i + 10) % hours.length] == '.' || (n == 0 && p == 0 && d == 1))){
            hours[i % hours.length] = 'd';
            helper(i + 10, p, n, d - 1, hours, result);
            hours[i % hours.length] = '.';
        }
    }
}
