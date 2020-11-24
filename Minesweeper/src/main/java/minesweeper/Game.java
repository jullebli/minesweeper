package minesweeper;

import java.util.Random;

public class Game {

    private final int width;
    private final int height;
    private int totalMines;
    private boolean running;
    private boolean victory;
    private boolean[][] open;
    private boolean[][] mine;
    private boolean[][] flag;
    private int openedSquares;

    private Game(int width, int height, boolean randomMines) {
        this.width = width;
        this.height = height;
        this.running = true;
        this.victory = false;
        this.open = new boolean[height][width];
        this.mine = new boolean[height][width];
        this.flag = new boolean[height][width];

        this.openedSquares = 0;

        if (randomMines) {
            this.totalMines = 10;
            placeRandomMines(totalMines);

        }
    }

    public Game(int width, int height) {
        //create Game with randomized mine locations
        this(width, height, true);
    }

    public Game(String[] mineMap) {
        //create Game with String array given mine locations
        //for test purposes only!
        this(mineMap.length, mineMap[0].length(), false);

        totalMines = 0;

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (mineMap[y].charAt(x) == 'M') {
                    totalMines++;
                }
            }
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isVictory() {
        return victory;
    }

    public boolean isOpen(int x, int y) {
        return open[y][x];
    }

    private void placeRandomMines(int totalMines) {
        Random r = new Random();

        for (int i = 0; i < totalMines; i++) {
            int x, y;
            do {
                x = r.nextInt(this.width);
                y = r.nextInt(this.height);

            } while (mine[y][x]);
            mine[y][x] = true;

        }
    }

    public boolean open(int x, int y) {
        if (open[y][x]) {
            return running;
        }
        open[y][x] = true;
        openedSquares++;

        if (mine[y][x]) {
            victory = false;
            running = false;
            return running;
        }

        if (openedSquares == width * height - totalMines) {
            victory = true;
            running = false;
            return running;
        }

        if (countMines(x, y) == 0) {
            // if 0 mines, go through neigbouring squares
            int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}};

            for (int i = 0; i < 8; i++) {
                int nY = y + neighbours[i][0];
                int nX = x + neighbours[i][1];
                if (isOnBoard(nX, nY)) {
                    open(nX, nY);
                }
            }
        }

        return running;
    }

    public int countMines(int x, int y) {
        //count mines in neighbouring squares
        int mineCount = 0;
        int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
        {1, 0}, {1, -1}, {0, -1}};

        for (int i = 0; i < 8; i++) {
            int nY = y + neighbours[i][0];
            int nX = x + neighbours[i][1];
            if (isOnBoard(nX, nY) && mine[nY][nX]) {
                mineCount++;
            }
        }
        return mineCount;
    }

    private boolean isOnBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }
}
