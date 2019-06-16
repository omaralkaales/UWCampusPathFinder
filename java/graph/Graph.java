package graph;

import java.util.*;

/**
 * Graph represents a mutable directed graph with nodes and edges, each node has a
 * label and each edge represents an outgoing edge
 *
 * @spec.specfield nodes : N
 * // a list of nodes in graph
 * @spec.specfield outgoing_edges : Edge
 * // The edges with label and destination of the graph.
 *
 * @author Omar Akaales
 * @version 04/27/2019
 */



/*
use both standard Javadoc tags (param, returns, throws)
 and ones introduced for this course (spec.modifies, spec.requires and spec.effects).
 */


public class Graph<N, L> {

    /*
     Rep invariant:
         graph != null
         All nodes and edges in graph are not null.
         graph must have node n before any edge with dest
         n created

     Abstract function:
         AF(this) = directed graph g such that
             {} if g is an empty graph
             if a is a node in g.nodes, then
             {a=[], ...} if a has no children
             {a=[label= l, destination= b} where graph has node a and
             where b is destination of a's outgoing edges,
             and l is label of the edge
     */

    // directed graph map, maps each node in graph with corresponding list of edges.
    private final Map<N, HashSet<Edge<L, N>>> graph;

    // to enable or disable checkRep()
    private final boolean CHECK_REP_ENABLE = false;




    /**
     * Creates an empty directed graph.
     *
     * @spec.effects constructs an empty directed graph with name set to graphName
     */

    public Graph() {
        this.graph = new HashMap<N, HashSet<Edge<L, N>>>();
        checkRep();
    }

    /**
     * Adds node to the graph if it is not already present.
     *
     * @param node a node to be added
     * spec.requires node != null
     * @return true if this graph did not already contain node
     * @spec.modifies graph
     * @spec.effects adds node node to this.adjList with empty edge set if
     * it is not already present
     */
    public boolean addNode(N node) {
        checkRep();
        if (graph.containsKey(node) || node == null) {
            return false;
        } else {
            graph.put(node, new HashSet<Edge<L, N>>());
            checkRep();
            return true;
        }
    }

    /** Adds edge from src to dst with label to the graph if both nodes exists
     * in graph, and edge with same label and same src and dest does not already
	 * exist in the graph.
     * @param src start of the edge
	 * @param dest end of the edge
     * @param label label of the edge
	 * @spec.requires src, dest, label != null. src, dest exists in graph
     * @spec.modifies graph
	 * @spec.effects Adds edge from src to dest with label
	 * to the graph if the same edge is not already
	 * present in the graph
	 * @return true if edge added successfully
     */
    public boolean addEdge(N src, N dest, L label) {
        checkRep();
        if (!graph.containsKey(src) || !graph.containsKey(dest)) {
            return false;
        } else {
            graph.get(src).add(new Edge<L, N> (label, dest));
            checkRep();
            return true;
        }
    }


    /** Adds edge from src to dst with label to the graph if both nodes exists
     * in graph, and edge with same label and same src and dest does not already
     * exist in the graph.
     * @param src start of the edge
     * @param edge end of the edge
     * @spec.requires src, edge != null. src, dest exists in graph
     * @spec.modifies graph
     * @spec.effects Adds edge from src to dest with label
     * to the graph if the same edge is not already
     * present in the graph
     * @return true if edge added successfully
     */
    public boolean addEdge(N src, Edge<L,N> edge) {
        checkRep();
        if (!graph.containsKey(src)) {
            return false;
        } else {
            graph.get(src).add(edge);
            checkRep();
            return true;
        }
    }




    /**
     * Return a set of nodes.
     *
     * @return a set of nodes
     */
    public Set<N> getNodes() {
        checkRep();
        Set<N> nodes = new HashSet<>(graph.keySet());
        return nodes;
    }



    /**
     * Returns a set of outgoing edges of node <var>n</var>.
     *
     * @param node a node
     * @spec.requires node != null, node in graph
     * @return a set of children nodes
     */
    public Set<Edge<L,N>> listChildren(N node) {
        checkRep();
        Set<Edge<L,N>> edges = new HashSet<Edge<L, N>>();
        edges = graph.get(node);
        return edges;
    }

    /**
     * Returns number of edges from one node to another node.
     *
     * @param src origin of the edge
     * @param dest destination of the edge
     * @spec.requires src, dest != null
     * @throws IllegalArgumentException if either src or dest is not in this.adjList.keys
     * @return number of edges from src to dest
     */
    public int numberOfEdges(N src, N dest) {
        checkRep();
        int edgesCount = 0;
        for (Edge<L, N> e : graph.get(src)){
            if (e.getDest().equals(dest)) {
                edgesCount++;
            }
        }
        return edgesCount;
    }

    /**
     * Returns number of nodes in the graph.
     *
     * @return number of nodes in the graph
     */
    public int size() {
        checkRep();
        return graph.size();
    }

     /**
     * Removes an edge from src to dest with label
     * from the graph and returns the edge.
     * Returns null if the specified edge doesn't exist.
     *
     * @param src origin of the edge
     * @param dest destination of the edge
     * @param label label of the edge
     * @spec.requires src, dest label != null, src, dest in graph
     * @spec.modifies graph
     * @spec.effects removes specified edge from this.outgoing_edges
     * @return true if edge removed, or false
     * if the specified edge doesn't exist
     */
    public boolean removeEdge(N src, N dest, L label) {
        checkRep();
        boolean removeResult = graph.get(src).remove(new Edge<L, N>(label, dest));
        checkRep();
        return removeResult;
    }
    /**
     * Removes an edge from src to dest with label
     * from the graph and returns the edge.
     * Returns null if the specified edge doesn't exist.
     *
     * @param src origin of the edge
     * @param edge destination of the edge
     * @spec.requires src, edge != null, src, dest in graph
     * @spec.modifies graph
     * @spec.effects removes specified edge from this.outgoing_edges
     * @return true if edge removed, or false
     * if the specified edge doesn't exist
     */
    public boolean removeEdge(N src, Edge<L, N> edge) {
        checkRep();
        boolean removeResult = graph.get(src).remove(edge);
        checkRep();
        return removeResult;
    }

    /**
     * Removes an edge from src to dest with label
     * from the graph and returns the edge.
     * Returns null if the specified edge doesn't exist.
     *
     * @param node node to remove
     * @spec.requires node!= null, node in graph, node.edges == empty, node.parent == empty
     * @spec.modifies graph
     * @spec.effects removes specified node from graph
     * @return the node removed from graph, null if node doesn't exist
     */
    public boolean removeNode(N node) {
        checkRep();
        if (!graph.get(node).isEmpty()) {
            return false;
        } else {
            for (N n : graph.keySet()){
                for (Edge edge : graph.get(n)){
                    if (edge.getDest().equals(node)){
                        return false;
                    }
                }
            }
         graph.remove(node);
         checkRep();
         return true;
        }
    }

    /**
     * Return true if node  is in the graph.
     * @param node a node
     * @spec.requires node != null
     * @return true if node exists in graph
     */

    public boolean containsNode(N node) {
        checkRep();
        return graph.containsKey(node);
    }

    /**
     * Return true if an edge exists between src and
     * dest with any label.
     *
     * @param src a source node
     * @param dest a destination node
     * @param label label of edge
     * @spec.requires src, dest, label != null, src, dest in graph
     * @return true if an edge with any label exists from
     * src to dest with specified label
     */

    public boolean containsEdge(N src, N dest, L label) {
        checkRep();
        for (Edge<L, N> e : graph.get(src)) {
            if (e.getDest().equals(dest) && e.getLabel().equals(label)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns string representation of the graph
     * @return string representation of the graph
     */

    public String toString() {
        checkRep();
        return graph.toString();
    }

    /**
     * Checks if rep inv holds.
     */
    private void checkRep() throws RuntimeException {
        if (CHECK_REP_ENABLE) {
            if (graph == null) {
                throw new RuntimeException("Graph cannot be null");
            } else {
                // check nodes are not null
                for (N node : graph.keySet()) {
                    if (node == null) {
                        throw new RuntimeException("Node cannot be null");
                    }
                }
                // check edges are not null, and no edge has label
                // or dest equals to null
                for (N node : graph.keySet()) {
                    for (Edge<L, N> edge : graph.get(node)) {
                        if (edge == null || edge.getLabel() == null
                                || edge.getDest() == null) {
                            throw new RuntimeException("edge or it's label" +
                                    "or destination cannot be null");
                        }
                        if (!graph.containsKey(edge.getDest())) {
                            throw new RuntimeException("graph must have node n before any edge with dest" +
                                    "         n created");
                        }
                    }
                }
            }
        }
    }
}
