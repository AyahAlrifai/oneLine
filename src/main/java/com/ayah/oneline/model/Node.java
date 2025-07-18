package com.ayah.oneline.model;

/**
 * Noe class.
 */
public class Node {

    private final String[][] board;
    private final NodeLocation nodeLocation;
    private Integer visitedNodes;

    public Node(final String[][] board,
                final NodeLocation nodeLocation,
                final Integer visitedNodes) {
        this.board = board;
        this.nodeLocation = nodeLocation;
        this.visitedNodes = visitedNodes;
    }

    public String[][] getBoard() {
        return board;
    }

    public NodeLocation getNodeLocation() {
        return nodeLocation;
    }

    public Integer getVisitedNodes() {
        return visitedNodes;
    }

    public void incrementVisited() {
        this.visitedNodes++;
    }
}
