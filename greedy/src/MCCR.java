import java.util.Arrays;
import java.util.PriorityQueue;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO
            //start with the first node, look for cheapest route to something new
            //create an array with all of the nodes to check if included
            boolean[] included = new boolean[G.numberOfV()];
            int total = 0;
            included[0] = true;
            int numIncluded = 1;
            PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.weight() - y.weight());
            for(Edge e: G.edges(0)){
                pq.add(e);
            }
            while(numIncluded < G.numberOfV()){
                Edge e = pq.poll();
                int v1 = e.either();
                int v2 = e.other(v1);
                if(!included[v1] || !included[v2]){
                    numIncluded++;
                    included[v1] = true;
                    included[v2] = true;
                    total += e.weight();
                    for(Edge edge: G.edges(v1)){
                        pq.add(edge);
                    }
                    for(Edge edge: G.edges(v2)){
                        pq.add(edge);
                    }
                }
            }

            return total;
        }

    }

