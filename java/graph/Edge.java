package graph;

/**
 * Edge represents a mutable outgoing edge with its label and
 * the destination node of the edge. The edge doesn't track it's
 * source node.
 *
 * @spec.specfield label : L
 * // label of the edge
 * @spec.specfield destination : N
 * destination node of the edge
 *
 * @author Omar Akaales
 * @version 04/27/2019
 */

/*
use both standard Javadoc tags (param, returns, throws)
 and ones introduced for this course (spec.modifies, spec.requires and spec.effects).
 */

public class  Edge  <L, N> {
/*
     Rep inv:
     dest != null
     label != null

     Abstract function:
         AF(this) = a labeled edge without source node where
                   destination is the destination of the edge
                   and label is the label of the node
*/

    // label and destination of the edge
    private L label;
    private N destination;


    // to enable or disable checkRep()
    private final boolean CHECK_REP_ENABLE = false;



    /**
     * Creates a labeled edge.
     *
     * @param label label of the edge
     * @param dest destination of the edge
     * @spec.requires label != null, dest != null
     * @spec.effects constructs a labeled edge with label label
     * and destination dest
     */
    public Edge(L label, N dest) {
        this.label = label;
        this.destination = dest;
    }

    /**
     * Returns a Node, which is the destination of this edge.
     *
     * @return this.destination
     */

    public N getDest() {
        return this.destination;
    }

    /**
     * Returns the label of this edge.
     *
     * @return this.label
     */
    public L getLabel() {
        return this.label;
    }

    /**
     * Sets the label of this edge to new value
     * @param newLabel the new label for this edge
     * spec.requires newLabel !=null
     * spec.modifies edge
     *
     */
    public void setLabel(L newLabel) {
        this.label = newLabel;
    }

    /**
     * Sets the dest of this edge to new value
     * @param dest  for this edge
     * spec.requires dest !=null
     *
     */
    public void setDestination(N dest) {
        this.destination = dest;
    }

    /**
     * @return string representing this edge in form "dest(label)"
     */
    @Override
    public String toString() {
        return this.destination + "("+this.label + ")";
    }

    /**
     * Standard equality operation.
     *
     * @param o to be compared for equality
     * @spec.requires o != null
     * @return true if and only if the passed edge is same as this edge
     * (same label and destination)
     */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        } else {
            Edge edge =(Edge) o;
            return this.label.equals(edge.getLabel())
                    && this.destination.equals(edge.getDest());
        }
    }

    /**
     * Return hash code of this edge.
     *
     * @return hash code of this edge
     */
    @Override
    public int hashCode() {
        return this.label.hashCode() *31 + this.destination.hashCode();
    }

    /**
     * Checks if rep inv holds.
     */
    private void checkRep() throws RuntimeException {
        if (CHECK_REP_ENABLE) {
            if (this.destination == null) {
                throw new RuntimeException("Destination cannot be null.");
            } else if (this.label == null) {
                throw new RuntimeException("Label cannot be null.");
            }
        }
    }

}
