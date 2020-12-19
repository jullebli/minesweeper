package minesweeper.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Game {

    private int width;
    private int height;
    private int totalMines;
    private boolean running;
    private boolean victory;
    private boolean[][] open;
    private boolean[][] mine;
    private boolean[][] flag;
    private int openedSquares;
    final String saveGameMagic = "Minesweepersavegame";
    final int saveGameVersion = 1;

    private Game(int width, int height, String[] mineMap, String filename) throws
            IOException {

        this.running = true;
        this.victory = false;
        this.openedSquares = 0;
        
        if (width > 0) {
            this.width = width;
            this.height = height;
            this.open = new boolean[height][width];
            this.mine = new boolean[height][width];
            this.flag = new boolean[height][width];
            
            placeRandomMines(10);

        } else if (filename != null) {
            loadGame(filename);

        } else if (mineMap != null) {
            loadTestData(mineMap);
        }

        this.totalMines = countTrueValues(this.mine);
        this.openedSquares = countTrueValues(this.open);
    }

    public Game(int width, int height) throws IOException {
        //create Game with randomized mine locations
        this(width, height, null, null);
    }

    public Game(String[] mineMap) throws IOException {
        //create Game with String array given mine locations
        //for test purposes only!
        this(0, 0, mineMap, null);

    }

    public Game(String filename) throws IOException {
        //create Game loading from a file
        this(0, 0, null, filename);
    }

    /**
     * Returns the number of columns (x) in the minefield
     * 
     * @return number of columns in the minefield
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the number of rows (y) in the minefield
     * 
     * @return number of rows in the minefield
     */
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

    /**
     * Returns the value of victory variable. Victory is set at false in the
     * beginning and true if there is as many squares unopened as there is mines
     * in total.
     * 
     * @return if the game has been won
     */
    public boolean isVictory() {
        return victory;
    }

    
    /**
     * Returns if the square in the coordinates has been opened or not.
     *
     * @return true if square has been opened, false otherwise
     */
    public boolean isOpen(int x, int y) {
        return open[y][x];
    }
    
    /**
     * Returns if the square in the coordinates has a flag or not.
     *
     * @return true if square has been flagged, false otherwise
     */
    public boolean isFlag(int x, int y) {
        return flag[y][x];
    }
    
    /**
     * Returns if the square in the coordinates has a mine or not.
     *
     * @return true if square has a mine, false otherwise
     */
    public boolean isMine(int x, int y) {
        return mine[x][y];
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
     * Checks if the square is on the game board, within height and width of the
     * board
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
     * Forms a StringBuilder type variable from a 2-dimensional boolean array.
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

    /**
     * Initiates height and width, and 2-dimensional arrays mine, flag,open.
     * Sets mine and flag values according to the input String array.
     *
     * @param array a String array. Contains mines as M and flags as F
     */
    private void loadTestData(String[] array) {
        int h = array.length;
        int w = array[0].length();

        this.height = h;
        this.width = w;
        this.mine = new boolean[h][w];
        this.flag = new boolean[h][w];
        this.open = new boolean[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (array[y].charAt(x) == 'M') {
                    mine[y][x] = true;
                }
                if (array[y].charAt(x) == 'F') {
                    flag[y][x] = true;
                }
            }
        }
    }

    private boolean[][] loadArray(BufferedReader br) throws IOException {
        int h = Integer.valueOf(br.readLine());
        int w = Integer.valueOf(br.readLine());

        boolean[][] array = new boolean[h][w];

        for (int y = 0; y < h; y++) {
            String line = br.readLine();
            for (int x = 0; x < w; x++) {
                array[y][x] = line.charAt(x) == '1';
            }
        }

        return array;
    }

    private void loadGame(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);

        String magic = br.readLine();
        if (!magic.equals(saveGameMagic)) {
            throw new IOException("Not a minesweeper file");
        }
        int version = Integer.valueOf(br.readLine());
        if (version != saveGameVersion) {
            throw new IOException("Unsupported minesweeper game version");
        }
        this.mine = loadArray(br);
        this.open = loadArray(br);
        this.flag = loadArray(br);

        this.height = mine.length;
        this.width = mine[0].length;

        br.close();
    }

    private int countTrueValues(boolean[][] array) {
        //counts true values in an array
        int count = 0;
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                if (array[y][x]) {
                    count++;
                }
            }
        }
        return count;
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
