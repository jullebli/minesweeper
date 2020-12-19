
package minesweeper.ui;


public class SetupCompleteEvent {
    
    private final int height;
    private final int width;
    
    public SetupCompleteEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    
}
