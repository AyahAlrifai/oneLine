package com.ayah.oneline;

import com.ayah.oneline.exception.InvalidBoardException;
import com.ayah.oneline.model.Node;
import com.ayah.oneline.model.NodeLocation;
import com.ayah.oneline.search.BreadthFirstSearch;
import com.ayah.oneline.search.DepthFirstSearch;
import com.ayah.oneline.util.OneLineUtil;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startTheGame();
    }

    private static void startTheGame() {
        String[][] board = getBoardFromInput();

        int totalVisitableNodes = countVisitableNodes(board);
        DepthFirstSearch.setAllVisitedNodes(totalVisitableNodes);
        BreadthFirstSearch.setAllVisitedNodes(totalVisitableNodes);

        NodeLocation startLocation = findStartNode(board);

        // Prepare search structures
        Stack<Node> dfsFridge = new Stack<>();
        dfsFridge.push(new Node(OneLineUtil.deepCopyBoard(board), startLocation, 0));

        Queue<Node> bfsFridge = new LinkedList<>();
        bfsFridge.add(new Node(OneLineUtil.deepCopyBoard(board), startLocation, 0));

        // DFS
        String[][] dfsResult = measurePerformance("DFS", () -> DepthFirstSearch.start(dfsFridge));

        // BFS
        String[][] bfsResult = measurePerformance("BFS", () -> BreadthFirstSearch.start(bfsFridge));

        // Show results
        System.out.println("\n--- Final Results ---");
        System.out.println("DFS Result:");
        printBoard(dfsResult);

        System.out.println("BFS Result:");
        printBoard(bfsResult);
    }

    private static String[][] getBoardFromInput() {
        System.out.print("Enter number of rows and columns (e.g., 5 5): ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[][] board = new String[rows][cols];
        System.out.println("Enter the board rows (e.g., Y N S Y N):");

        for (int i = 0; i < rows; i++) {
            String[] row = scanner.nextLine().trim().split("\\s+");
            if (row.length != cols) {
                throw new InvalidBoardException("Row " + (i + 1) + " must have " + cols + " elements.");
            }
            board[i] = row;
        }

        return board;
    }

    private static int countVisitableNodes(final String[][] board) {
        int count = 0;
        for (String[] row : board) {
            for (String cell : row) {
                if (cell.equalsIgnoreCase("Y") || cell.equalsIgnoreCase("S")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static NodeLocation findStartNode(final String[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x].equalsIgnoreCase("S")) {
                    return new NodeLocation(x, y);
                }
            }
        }
        throw new InvalidBoardException("Start node 'S' not found in the board.");
    }

    private static void printBoard(final String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    @FunctionalInterface
    interface SearchFunction {
        String[][] run();
    }

    private static String[][] measurePerformance(final String label,
                                                 final SearchFunction search) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long beforeMem = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        String[][] result = search.run();

        long endTime = System.nanoTime();
        long afterMem = runtime.totalMemory() - runtime.freeMemory();

        long usedBytes = afterMem - beforeMem;
        double usedKB = usedBytes / 1024.0;
        double usedMB = usedKB / 1024.0;
        double durationNs = endTime - startTime;
        double durationUs = durationNs / 1_000.0;
        double durationMs = durationNs / 1_000_000.0;

        System.out.println("\n===== " + label + " Performance =====");
        System.out.printf("Time: %.0f ns | %.3f µs | %.3f ms%n", durationNs, durationUs, durationMs);
        System.out.printf("Memory: %d bytes | %.2f KB | %.4f MB%n", usedBytes, usedKB, usedMB);

        return result;
    }
}
