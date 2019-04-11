package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    public static class Point {
        public int x;
        public int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;
        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        if(B.length == 0){
            return null;
        }
        if(B.length <= 1){
            List<Point> out = new ArrayList<>();
            out.add(new Point(B[0].l, B[0].h));
            out.add(new Point(B[0].r, 0));
            return out;
        }
        int mid = Math.floorDiv(B.length, 2);
        Building[] a = Arrays.copyOfRange(B,0, mid);
        Building[] b = Arrays.copyOfRange(B, mid, B.length);
        return merge(skyline(a), skyline(b));
    }

    public static List<Point> merge(List<Point> a, List<Point> b){
        int i = 0;
        int pa = 0;
        int pb = 0;
        int ha = 0;
        int hb = 0;
        List<Point> out = new ArrayList<>();
        while(i < a.size() + b.size()){
            if(pa >= a.size()){
                //if all of a is traversed
                out.add(b.get(pb));
                pb++;
            } else if(pb >= b.size()){
                //if all of b is traversed
                out.add(a.get(pa));
                pa++;
            } else if(a.get(pa).x < b.get(pb).x){
                //if a is on left
                //stores last height for next comparison
                ha = a.get(pa).y;
                //whichever incomplete building is taller gets dominance
                int h = Math.max(ha, hb);
                out.add(new Point(a.get(pa).x, h));
                pa++;
            } else if(b.get(pb).x < a.get(pa).x){
                //if b on left
                hb = b.get(pb).y;
                int h = Math.max(ha, hb);
                out.add(new Point(b.get(pb).x, h));
                pb++;
            } else {
                //if x is equal choose taller
                ha = a.get(pa).y;
                hb = b.get(pb).y;
                int h = Math.max(ha, hb);
                out.add(new Point(b.get(pb).x, h));
                pa++;
                pb++;
                //extra increment because both lists traversed
                i++;
            }
            if(out.size() >= 2 && out.get(out.size() - 1).y == out.get(out.size() - 2).y){
                //past two heights are the same
                out.remove(out.size() - 1);
                System.out.println(out.get(out.size() - 1).x);
                System.out.println("oops");
            }
            i++;
        }
        for(Point o: out){
            System.out.print(o.x);
            System.out.print("x");
            System.out.print(o.y);
            System.out.print("y");
        }
        System.out.println();
        return out;

    }

}
