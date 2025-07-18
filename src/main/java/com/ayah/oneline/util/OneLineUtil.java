package com.ayah.oneline.util;

import com.ayah.oneline.model.Node;
import com.ayah.oneline.model.NodeLocation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * One line util.
 */
public final class OneLineUtil {

    private OneLineUtil() {

    }

    public static String[][] deepCopyBoard(final String[][] original) {
        if (original == null) return new String[0][0];

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    public static List<NodeLocation> getAdjacentWalkableNodes(final Node node) {
        List<NodeLocation> neighbors = new ArrayList<>();
        String[][] board = node.getBoard();
        int x = node.getNodeLocation().x();
        int y = node.getNodeLocation().y();

        int rows = board.length;
        int cols = board[0].length;

        // left
        if (x > 0 && board[y][x - 1].equalsIgnoreCase("Y")) {
            neighbors.add(new NodeLocation(x - 1, y));
        }
        // right
        if (x < cols - 1 && board[y][x + 1].equalsIgnoreCase("Y")) {
            neighbors.add(new NodeLocation(x + 1, y));
        }
        // up
        if (y > 0 && board[y - 1][x].equalsIgnoreCase("Y")) {
            neighbors.add(new NodeLocation(x, y - 1));
        }
        // down
        if (y < rows - 1 && board[y + 1][x].equalsIgnoreCase("Y")) {
            neighbors.add(new NodeLocation(x, y + 1));
        }

        return neighbors;
    }

}
