package pathfinder;

import graph.Edge;
import graph.Graph;
import pathfinder.datastructures.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Dijkstras class uses Dijkstras algorithm to finds the shortest path between two nodes.
 * where the shortest path represents the least cost path in a weighted directed edge graph
 * 
 * 
 * @author Omar Akaales
 * @version 05/24/2019
 */
 */


public class Dijkstras {



    public static <N> Path<Edge<Double, N>> findShortestPath(N start, N dest, Graph<N, Double> graph) {
            PriorityQueue<Path<Edge<Double, N>>> active;
            Set<N> known = new HashSet<>();
            active = new PriorityQueue<>(
                    (Path<Edge<Double, N>> path1, Path<Edge<Double,N>> path2) -> {
                        if (path1.getCost() > path2.getCost()) {
                            return 1;
                        } else if (path1.getCost() < path2.getCost()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    });

            Path<Edge<Double, N>> newPath = new Path<>(new Edge<>(0.0, start));
            active.add(newPath);
        Path<Edge<Double, N>> minPath;
        // keep looping until the heap is empty
            while (!active.isEmpty()) {
                minPath = active.remove();
                Edge<Double, N> minDest = minPath.getEnd();
                if (minDest.getDest().equals(dest)) {
                    return minPath;
                } else if (!known.contains(minDest)) {
                    for (Edge<Double, N> edge : graph.listChildren(minDest.getDest())) {
                            // if edge already known/visited, don't add to the heap
                            if (!known.contains(edge.getDest())) {
                                // keep adding path from start point to get the total path and add it
                                // to the heap
                                Path<Edge<Double, N>> newPath2 = minPath.extend(edge, edge.getLabel());
                                active.add(newPath2);
                            }
                    }
                    known.add(minDest.getDest());
                }
            }
            return null;
    }

/*
    // EdgeComparator class used to sort edges in alphabetical order, heroes (destination)
    // first then equal then compare book titles (edge labels)
    private static class EdgeComparator implements Comparator<Edge<Double,N>> {
        // this class doesn't represent an ADT

        public int compare(Edge<Double, N> e1, Edge<Double, N> e2) {
            if(!(e1.getDest().equals(e2.getDest()))) {
                return e1.getDest().compareTo(e2.getDest());
            } else if (!(e1.getLabel().equals(e2.getLabel()))) {
                return e1.getLabel().compareTo(e2.getLabel());
            } else {
                return 0;
            }
        }
    }
*/
}
