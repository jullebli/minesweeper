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
                if (mineMap[y].charAt(x) == 'F') {
                    flag[y][x] = true;
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

    /**
     * Returns if the game is still running.
     * 
     * @return true if game is running, false otherwise
     */
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

    /**
     * Places mines randomly on the minefield.
     * 
     * @param totalMines number of mines to place
     */
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

    /**
     * Opens a square on the minefield
     * 
     * @param x x coordinate of the square
     * @param y y coordinate of the square
     * 
     * @return true if game is running, false otherwise
     */
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

    /**
     * Counts the mines in the neighbouring squares of a square.
     * 
     * @param x x coordinate of a square
     * @param y y coordinate of a square
     * 
     * @return number of mines in the neighbouring squares
     */
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
    
    /**
     * Sets a flag in one square.
     * 
     * @param x x coordinate of a square
     * @param y y coordinate of a square
     * @param placed true when setting a flag, false when removing a flag
     */
    public void setFlag(int x, int y, boolean placed) {
        //sets a flag in one square, placed=true when setting a flag
        //false when removing a flag
        //todo? check running? open?
        flag[y][x] = placed;      
    }

    /**
     * Checks if the square is on the game board, within height and width of
     * the board
     * 
     * @param x x coordinate of a square
     * @param y y coordinate of a square
     * 
     * @return true if square is on board, false if square is not on board
     */
    private boolean isOnBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }
    
    /**
     * Forms a StringBuilder type variable from a 2-dimensional boolean
     * array.
     * 
     * @param array 2-dimensional boolean array
     * @param s StringBuilder type variable that will be appended
     */
    private void arrayToStringBuilder(boolean[][] array, StringBuilder s) {
        //before each array as String is width and height
        int h = array.length;
        int w = array[0].length;
        s.append(h);
        s.append("\n");
        s.append(w);
        s.append("\n");
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                s.append(array[y][x] ? "1" : "0");            
            }
            s.append("\n");
        }
    }
    
    /**
     * Saves the game by writing it to a file where it can be read from later.
     * 
     * @param filename name of the file where the saved game will be written
     * 
     * @throws IOException throws exception if writing to the file fails
     */
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