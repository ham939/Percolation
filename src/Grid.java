import java.util.Scanner;

public class Grid {

    public static final Scanner in = new Scanner(System.in);

    private static Integer[][] grid;

    public Grid(int n) {

        grid = new Integer[n][n];
        for (int y = 0; y < grid.length; ++y)
            for (int x = 0; x < grid.length; ++x)
                grid[y][x] = 0;
    }

    public void showGrid() {

        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid.length; ++x)
                System.out.print(grid[y][x]);
            System.out.println();
        }
        System.out.println();

    }

    public void openCell(int row, int col) {

        grid[row - 1][col - 1] = 1;

    }


    public static void main(String[] args) {

        Grid grid = new Grid(2);
        grid.showGrid();
        grid.openCell(1, 1);
        grid.showGrid();
    }
}
