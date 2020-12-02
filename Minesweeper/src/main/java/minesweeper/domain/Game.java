package minesweeper.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
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
    final String saveGameMagic = "Minesweepersavegame";
    final int saveGameVersion = 1;

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
                    mine[y][x] = true;
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
    
    public boolean isFlag(int x, int y) {
        return flag[y][x];
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

        openNeighbouringSquares(x, y);

        return running;
    }
    
    private void openNeighbouringSquares(int x, int y) {
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
    
    public void setFlag(int x, int y, boolean placed) {
        //sets a flag in one square, placed=true when setting a flag
        //false when removing a flag
        //todo?check running? open?
        flag[y][x] = placed;      
    }

    private boolean isOnBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }
    
    private void arrayToStringBuilder(boolean[][] array, StringBuilder s) {
        //before each array as String is width and height
        int height = array.length;
        int width = array[0].length;
        s.append(height);
        s.append("\n");
        s.append(width);
        s.append("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                s.append(array[y][x] ? "1" : "0");            
            }
            s.append("\n");
        }
    }
    
    public void saveGameToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(writer);
        
        bw.write(toString());
        bw.close();
    }

    
    @Override
    public String toString() {
        //used for saving the game
        StringBuilder s = new StringBuilder();
        
        s.append(saveGameMagic);
        s.append("\n");
        s.append(saveGameVersion);
        s.append("\n");
        arrayToStringBuilder(mine, s);
        arrayToStringBuilder(open, s);
        arrayToStringBuilder(flag, s);
       
        return s.toString();
    }
}