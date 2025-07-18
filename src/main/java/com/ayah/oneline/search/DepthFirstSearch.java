package com.ayah.oneline.search;

import com.ayah.oneline.exception.NoSolutionException;
import com.ayah.oneline.model.Node;
import com.ayah.oneline.model.NodeLocation;
import com.ayah.oneline.util.OneLineUtil;
import java.util.Stack;

/**
 * Depth First Search.
 */
public final class DepthFirstSearch {

    private static int allVisitedNodes = 0;

    private DepthFirstSearch() {
    }

    public static void setAllVisitedNodes(int allVisitedNodes) {
        DepthFirstSearch.allVisitedNodes = allVisitedNodes;
    }

    public static String[][] start(final Stack<Node> fridge) {
        while (!fridge.isEmpty()) {
            Node currentNode = fridge.pop();
            String[][] board = currentNode.getBoard();
            NodeLocation location = currentNode.getNodeLocation();

            currentNode.incrementVisited();
            board[location.y()][location.x()] = String.valueOf(currentNode.getVisitedNodes());

            if (currentNode.getVisitedNodes() == allVisitedNodes) {
                return board;
            }

            addChildren(fridge, currentNode);
        }

        throw new NoSolutionException("No solution found for DFS");
    }

    private static void addChildren(final Stack<Node> fridge, final Node currentNode) {
        for (NodeLocation next : OneLineUtil.getAdjacentWalkableNodes(currentNode)) {
            fridge.push(new Node(
                    OneLineUtil.deepCopyBoard(currentNode.getBoard()),
                    next,
                    currentNode.getVisitedNodes()
            ));
        }
    }
}
