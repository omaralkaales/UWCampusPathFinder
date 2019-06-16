package pathfinder;

import graph.Edge;
import graph.Graph;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CampusModelGraph represents a model that represent data, as well as the
 * classes that load, store, look up, or operate on data
 *
 * @spec.specfield campusGraph : Graph
 * @spec.specfield buildings : List of campus building
 * @spec.specfield paths : List of campus paths
 *
 *
 * @author Omar Akaales
 * @version 05/24/2019
 */





public class CampusMapModel {

    /*
     Rep invariant:
         CampusGraph, buildings, paths != null
         All paths and buildings in graph are not null.

     Abstract function:
         AF(this) = is a model that contains campus graph
         data, in addition to alist of buildings (nodes in graph)
         and paths (edges and it's weight).

     */



    private Graph<Point, Double> campusGraph;
    private List<CampusBuilding> buildings;
    private List<CampusPath> paths;
    private Map<String, String> buildingsShortNameToLong;
    private Map<String, Point> buildingsShortNameToCoord;
    private final boolean CHECK_REP_ENABLE = false;


    /**
     * Creates a model with parsed data from data files.
     *
     * @spec.effects constructs a model with data about campus map
     */

    public CampusMapModel(){
        buildings = CampusPathsParser.parseCampusBuildings();
        paths = CampusPathsParser.parseCampusPaths();
        campusGraph = new Graph<>();
        buildingsShortNameToLong = new HashMap<>();
        buildingsShortNameToCoord = new HashMap<>();
        buildLists();
        addCoordToCampusGraph();
        checkRep();
    }

    // initialize building lists
    private void buildLists() {
        for (CampusBuilding building : buildings) {
            this.buildingsShortNameToLong.put(building.getShortName(),building.getLongName());
            this.buildingsShortNameToCoord.put(building.getShortName(),
                    new Point(building.getX(),building.getY()));
        }
    }

    // add coordinates to campus graph map
    private void addCoordToCampusGraph() {
        for (CampusPath path : paths) {
            Point start = new Point(path.getX1(), path.getY1());
            Point end = new Point(path.getX2(), path.getY2());
            if (!campusGraph.containsNode(start)) {
                campusGraph.addNode(start);
            }
            if (!campusGraph.containsNode(end)) {
                campusGraph.addNode(end);
            }
            campusGraph.addEdge(start, end, path.getDistance());
        }
    }

    /**
     * Return a graph of nodes are points and
     * weighted edge with double.
     * @return a Graph
     */
    public Graph<Point, Double> getCampusGraph() {
        checkRep();
        return campusGraph;
    }


    public void setCampusGraph(Graph<Point, Double> campusGraph) {
        checkRep();
        this.campusGraph = campusGraph;
    }

    /**
     * Return a list of campusBuilding objects
     *
     * @return a list of campus building
     *
     */

    public List<CampusBuilding> getBuildings() {
        checkRep();
        return buildings;
    }
    /**
     * set a List of campusBuilding objects which is list of buildings  in campus map
     * @param buildings map of string to points
     */
    public void setBuildings(List<CampusBuilding> buildings) {
        checkRep();
        this.buildings = buildings;
        checkRep();
    }
    /**
     * Return a list of CampusPath objects
     *
     * @return a list of campus paths
     */
    public List<CampusPath> getPaths() {
        checkRep();
        return paths;
    }

    /**
     * set a List of Paths in campus map
     * @param paths map of string to points
     */

    public void setPaths(List<CampusPath> paths) {
        checkRep();
        this.paths = paths;
        checkRep();
    }
    /**
     * Return a map of strings from buildings short name
     * to buildings long abbreviated name
     *
     * @return a Map long name of building from short
     */
    public Map<String, String> getBuildingsShortNameToLong() {
        checkRep();
        return buildingsShortNameToLong;
    }

    /**
     * set a map of string to string to get buildings short name
     * to their long abbreviated name
     * @param buildingsShortNameToLong map of string to points
     */

    public void setBuildingsShortNameToLong(Map<String, String> buildingsShortNameToLong) {
        checkRep();
        this.buildingsShortNameToLong = buildingsShortNameToLong;
        checkRep();
    }
    /**
     * Return a map of string to points to  get buildings short name
     * to their related coordinate
     *
     * @return a Map between short name building to coordinates
     */

    public Map<String, Point> getBuildingsShortNameToCoord() {
        checkRep();
        return buildingsShortNameToCoord;
    }
    /**
     * set a map of string to points to  get buildings short name
     * to their related coordinate
     * @param buildingsShortNameToCoord map of string to points
     */
    public void setBuildingsShortNameToCoord(Map<String, Point> buildingsShortNameToCoord) {
        checkRep();
        this.buildingsShortNameToCoord = buildingsShortNameToCoord;
        checkRep();
    }



    /**
     * Checks if rep inv holds.
     */
    private void checkRep() throws RuntimeException {
        if (CHECK_REP_ENABLE) {
            if (this.buildings == null) {
                throw new RuntimeException("buildings cannot be null");
            } else if (this.buildingsShortNameToCoord == null) {
                throw new RuntimeException("building coordinates cannot be null");
            } else if (this.buildingsShortNameToLong == null) {
                throw new RuntimeException("short name to long cannot be null");
            } else if (this.paths == null) {
                throw new RuntimeException("Graph cannot be null");
            } else if (this.campusGraph == null) {
                throw new RuntimeException("Graph cannot be null");
            }
        }
    }
}
