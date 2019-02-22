import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        HashMap<Double, Integer> dists = new HashMap<>();
        int count = 0;

        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                double d = Math.sqrt(Math.pow((points[i][0] - points[j][0]),2) + Math.pow(points[i][1] - points[j][1], 2));
                int h = dists.getOrDefault(d, 0) + 1;
                dists.put(d, h);
            }
            for (HashMap.Entry<Double, Integer> entry : dists.entrySet()) {
                count += entry.getValue() * (entry.getValue() - 1);
            }
            dists.clear();
        }

        return count;
    }
}

