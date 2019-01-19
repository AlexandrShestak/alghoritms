import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        CellState[][] cells = new CellState[n][m];

        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                int cellValue = scanner.nextInt();
                CellState cellState = new CellState(cellValue);
                cells[i][j] = cellState;
            }
        }

        for (int l = 0 ; l < k ; l++) {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0; j < m; j++) {
                    int stableNighbourCount = 0;
                    int activeNeighbourCount = 0;

                    if (j-1>=0) { // left cell
                        if (cells[i][j-1].currentValue == 2) {
                            stableNighbourCount++;
                        }
                        if (cells[i][j-1].currentValue == 3 || cells[i][j-1].currentValue == 2) {
                            activeNeighbourCount++;
                        }
                    }
                    if (j+1<m) { //right cell
                        if (cells[i][j+1].currentValue == 2) {
                            stableNighbourCount++;
                        }
                        if (cells[i][j+1].currentValue == 3 || cells[i][j+1].currentValue == 2) {
                            activeNeighbourCount++;
                        }
                    }
                    if (i-1>=0) { //top cell
                        if (cells[i-1][j].currentValue == 2) {
                            stableNighbourCount++;
                        }
                        if (cells[i-1][j].currentValue == 3 || cells[i-1][j].currentValue == 2) {
                            activeNeighbourCount++;
                        }
                    }
                    if (i+1<n) { //bottom cell
                        if (cells[i+1][j].currentValue == 2) {
                            stableNighbourCount++;
                        }
                        if (cells[i+1][j].currentValue == 3 || cells[i+1][j].currentValue == 2) {
                            activeNeighbourCount++;
                        }
                    }
                    if (stableNighbourCount >= 2) {
                        cells[i][j].updatedValue = 2;
                    } else if (activeNeighbourCount >= 1) {
                        cells[i][j].updatedValue = 3;
                    } else {
                        cells[i][j].updatedValue = 1;
                    }
                }
            }

            for (int i = 0 ; i < n ; i++) {
                for (int j = 0; j < m; j++) {
                    if (cells[i][j].currentValue != cells[i][j].updatedValue) {
                        cells[i][j].changesCount++;
                    }
                    cells[i][j].currentValue = cells[i][j].updatedValue;
                }
            }
        }
        printResult(cells, n, m);
    }

    static class CellState {
        int currentValue;
        int updatedValue;
        int changesCount;

        CellState(int currentValue) {
            this.currentValue = currentValue;
            this.changesCount = 0;
        }
    }

    private static void printResult(CellState[][] cellStates, int n, int m) {
        for (int i = 0 ; i < n ; i++) {
            StringBuilder resultRow = new StringBuilder();
            for (int j = 0; j < m; j++) {
                resultRow.append(cellStates[i][j].changesCount).append(" ");
            }
            System.out.println(resultRow);
        }
    }
}






