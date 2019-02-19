package oa.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class RobotRemoveObstacle {
    public int removeObstacle(int numRows, int numColumns, int[][] lot) {
        if (lot[0][0] == 0) return -1;

        boolean[][] visited = new boolean[lot.length][lot[0].length];

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, 0));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            if (lot[pos.row][pos.col] == 9) return pos.stepsFromStart;

            if (pos.row - 1 >= 0 && !visited[pos.row - 1][pos.col] && lot[pos.row - 1][pos.col] != 0) {
                queue.offer(new Position(pos.row - 1, pos.col, pos.stepsFromStart + 1));
            }
            if (pos.row + 1 < lot.length && !visited[pos.row + 1][pos.col] && lot[pos.row + 1][pos.col] != 0) {
                queue.offer(new Position(pos.row + 1, pos.col, pos.stepsFromStart + 1));
            }
            if (pos.col - 1 >= 0 && !visited[pos.row][pos.col - 1] && lot[pos.row][pos.col - 1] != 0) {
                queue.offer(new Position(pos.row, pos.col - 1, pos.stepsFromStart + 1));
            }
            if (pos.col + 1 < lot[0].length && !visited[pos.row][pos.col + 1] && lot[pos.row][pos.col + 1] != 0) {
                queue.offer(new Position(pos.row, pos.col + 1, pos.stepsFromStart + 1));
            }

            visited[pos.row][pos.col] = true;
        }

        return -1;
    }

    class Position {
        int row;
        int col;
        int stepsFromStart;

        Position(int i, int j, int steps) {
            row = i;
            col = j;
            stepsFromStart = steps;
        }
    }
}
