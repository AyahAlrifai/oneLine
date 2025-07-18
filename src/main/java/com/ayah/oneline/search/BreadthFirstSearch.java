package com.ayah.oneline.search;

import com.ayah.oneline.exception.NoSolutionException;
import com.ayah.oneline.model.Node;
import com.ayah.oneline.model.NodeLocation;
import com.ayah.oneline.util.OneLineUtil;
import java.util.Queue;

/**
 * Breadth First Search.
 */
public final class BreadthFirstSearch {

    private static int allVisitedNodes = 0;

    private BreadthFirstSearch() {
    }

    public static void setAllVisitedNodes(int allVisitedNodes) {
        BreadthFirstSearch.allVisitedNodes = allVisitedNodes;
    }

    public static String[][] start(final Queue<Node> fridge) {
        while (!fridge.isEmpty()) {
            Node currentNode = fridge.poll();
            String[][] board = currentNode.getBoard();
            NodeLocation location = currentNode.getNodeLocation();

            currentNode.incrementVisited();
            board[location.y()][location.x()] = String.valueOf(currentNode.getVisitedNodes());

            if (currentNode.getVisitedNodes() == allVisitedNodes) {
                return board;
            }

            addChildren(fridge, currentNode);
        }

        throw new NoSolutionException("No solution found for BFS");
    }

    private static void addChildren(final Queue<Node> fridge, final Node currentNode) {
        for (NodeLocation next : OneLineUtil.getAdjacentWalkableNodes(currentNode)) {
            fridge.add(new Node(
                    OneLineUtil.deepCopyBoard(currentNode.getBoard()),
                    next,
                    currentNode.getVisitedNodes()
            ));
        }
    }
}
