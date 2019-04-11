package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skylinetwo {

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
        B = recurse(B);

        return convert(B);
    }

    public static Building[] recurse(Building[] B){
        int l = (int)Math.ceil((double)B.length / 2);
        int r = (int)Math.floor((double)B.length / 2);
        //split sizes

        if(B.length <= 1){
            return B;
        }

        Building[] left = new Building[l];
        Building[] right = new Building[r];
        System.arraycopy(B, 0, left, 0, l);
        System.arraycopy(B, l, right, 0,r);

        return merge2(recurse(left), recurse(right));
    }

    public static Building[] merge2(Building[] l, Building[] r){
        int lp = 0;
        int rp = 0;
        int count = 0;
        Building[] out = new Building[(r.length + l.length) * 2];
        while(lp < l.length && rp < r.length){
            if(l[lp].l > r[rp].l){
                //switch if right is actually left
                Building[] temp = l;
                l = r;
                r = temp;
                int tp = lp;
                lp = rp;
                rp = tp;
            }
            if(l[lp].r < r[rp].l){
                //no overlap
                out[count] = l[lp];
                count++;
                lp++;
            } else if (l[lp].r >= r[rp].r) {
                //complete x overlap
                if(l[lp].h >= r[rp].r){
                    //if swallowed
                    out[count] = l[lp];
                    lp++;
                    count++;
                } else {
                    //right peeks out
                    out[count] = new Building(l[lp].l, r[rp].l, l[lp].h);
                    count++;
                    out[count] = r[rp];
                    count++;
                    out[count] = new Building(r[rp].r, l[lp].r, l[lp].h);
                    count++;
                    lp++;
                    rp++;
                }
            } else if (l[lp].h > r[rp].h) {
                //normal left taller merge
                out[count] = l[lp];
                count++;
                out[count] = new Building(l[lp].r, r[rp].r, r[rp].h);
                count++;
                lp++;
                rp++;
            } else if (l[lp].h < r[rp].h){
                //normal right taller merge
                out[count] = new Building(l[lp].l, r[rp].l, l[lp].h);
                count++;
                out[count] = r[rp];
                count++;
                lp++;
                rp++;
            } else {
                //flat merge
                out[count] = new Building(l[lp].l, r[rp].r, l[lp].h);
                count++;
                lp++;
                rp++;
            }
        }
        if(lp < l.length){
            System.arraycopy(l,lp,out,count, l.length - lp);
            count += l.length - lp;
        }
        if(rp < r.length){
            System.arraycopy(r,rp,out,count, r.length - rp);
            count += r.length - rp;
        }
        Building[] out2 = new Building[count];
        System.arraycopy(out,0,out2, 0,count);
        return out2;
    }

    public static Building[] merge(Building[] l,  Building[] r){
        if(r.length == 0) return l;
        if(l.length == 0) return r;
        Building L = l[l.length - 1];
        Building R = r[0];
        //Switch if in wrong order
        if(L.l > R.l){
            Building temp = L;
            L = R;
            R = L;
            l[l.length - 1] = L;
            r[0] = R;
            r = recurse(r);
            l = recurse(l);
        }

        System.out.print(L.l);
        System.out.println(L.r);
        System.out.print(R.l);

        System.out.println(R.r);

        //Left swallows right
        if(L.r >= R.r && L.h >= R.h){

            System.out.println("removed");
            if(r.length ==1){
                return l;
            }
            Building[] out = new Building[r.length - 1];
            System.arraycopy(r, 1, out, 0, r.length - 1);
            merge(l, out);
        }
        //Right pops out of left
        if(L.r >= R.r && L.l <= R.l && L.h <= R.h){

            System.out.println("added");
            if(r.length <= 1000) {
                Building[] out = new Building[r.length + l.length + 1];
                System.arraycopy(l, 0, out, 0, l.length - 1);
                out[l.length - 1] = new Building(L.l, R.l, L.h);
                out[l.length] = R;
                out[l.length + 1] = new Building(R.r, L.r, L.h);
                System.arraycopy(r, 1, out, l.length + 2, r.length - 1);

                return out;
            }

            Building[] ol = new Building[l.length + 2];
            Building[] or = new  Building[r.length - 1];
            System.arraycopy(l,0,ol,0,l.length - 1);
            ol[l.length - 1] =new Building(L.l, R.l, L.h);
            ol[l.length] = R;
            ol[l.length + 1] = new Building(R.r, L.r, L.h);
            System.arraycopy(r,1,or,0,r.length-1);
            merge(ol,or);
        }

        //Right swallows left
        if(R.r >= L.r && R.l <= L.l && R.h >= L.h){
            if(l.length ==1){
                return r;
            };
            System.out.println("removed2");
            Building[] out = new Building[l.length - 1];
            System.arraycopy(l, 0, out, 0, l.length - 1);
            merge(out, r);
        }
        Building[] out = new Building[l.length + r.length];

        //Any overlap
        if(L.r > R.l){
            if(L.h == R.h){
                System.out.println("merged");
                r[0] = new Building(L.l, R.r, R.h);

                Building[] out2 = new Building[r.length + l.length - 1];
                System.arraycopy(r, 0, out2, l.length - 1, r.length);
                System.arraycopy(l, 0, out2, 0, l.length - 1);


                return out2;
            }
            if(L.h > R.h){
                System.out.println("normal");
                r[0] = new Building(L.r, R.r, R.h);
                return merge(l,r);
            } else if(L.h < R.h){
                System.out.println("normal");

                l[l.length - 1] = new Building(L.l, R.l, L.h);
                return merge(l,r);
            }
        }
        //Combines for no overlap or above statements
        System.arraycopy(l, 0, out, 0, l.length);
        System.arraycopy(r, 0, out, l.length, r.length);
        return out;
    }

    public static List<Point> convert(Building[] B){
        if (B.length < 1) return null;
        List<Point> out = new ArrayList<Point>();
        Building last = B[0];
        for(Building b: B){
            System.out.println(b);
            if(b.l > last.r){
                out.add(new Point(last.r, 0));
                System.out.println(last.r);
            }
            if(b.l < b.r) {
                out.add(new Point(b.l, b.h));
                System.out.println(b.l);
                System.out.println(b.h);
            }
            last = b;
        }
        System.out.println(last.r);
        System.out.println(0);

        out.add(new Point(last.r, 0));

        return out;
    }
}
