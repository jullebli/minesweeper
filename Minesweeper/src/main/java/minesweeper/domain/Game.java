package minesweeper.domain;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

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
    /**
     * Standard file extension for saved game files.
     */
    public static final String SAVEGAME_EXTENSION = ".minesave";

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

    /**
     * Creates a new Game instance with randomized mine locations.
     *
     * @param width width of the minefield
     * @param height height of the minefield
     * @throws IOException if file operations fail
     */
    public Game(int width, int height) throws IOException {
        this(width, height, null, null);
    }

    /**
     * Creates a new Game instance using given mine locations. This constructor
     * is only used for testing.
     *
     * @param mineMap array of strings where each row describes a row on the
     * minefield. Each character describes one square on the minefield.
     * Character M is a mine and F is a flag. Any other character can be used
     * for an empty square. It is not possible to specify a flag on top of a
     * mine.
     * @throws IOException if file operations fail
     */
    public Game(String[] mineMap) throws IOException {
        //create Game with String array given mine locations
        //for test purposes only!
        this(0, 0, mineMap, null);

    }

    /**
     * Creates a new Game instance based on a saved game file.
     *
     * @param filename name of the file to be loaded
     * @throws IOException If file operations fail
     */
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
     * Returns the value of victory variable. Victory is set as false in the
     * beginning and as true if there is as many squares unopened as there is
     * mines in total.
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
        return mine[y][x];
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

    private ArrayList<Pair<Integer, Integer>> neigbourCoordinates(int x, int y) {
        ArrayList<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        int[][] neighbours = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0},
        {1, -1}, {0, -1}};

        for (int i = 0; i < 8; i++) {
            int nY = y + neighbours[i][0];
            int nX = x + neighbours[i][1];
            if (isOnBoard(nX, nY)) {
                coordinates.add(new Pair(nX, nY));
            }
        }
        return coordinates;
    }

    private void openNeighbouringSquares(int x, int y) {
        if (countMines(x, y) == 0) {
            for (Pair<Integer, Integer> pair : neigbourCoordinates(x, y)) {
                open(pair.getKey(), pair.getValue());
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
        int mineCount = 0;
        for (Pair<Integer, Integer> pair : neigbourCoordinates(x, y)) {
            if (mine[pair.getValue()][pair.getKey()]) {
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
        flag[y][x] = placed;
    }

    private boolean isOnBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }

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

    /**
     * Returns state of the game as a String. Is used mainly for saving the
     * game.
     *
     * @return game state as a String
     */
    @Override
    public String toString() {
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
