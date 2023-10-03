/* Name: Tung Nguyen
 * PennKey: ngtung
 * Execution: n/a
 * Description: Tile class, corresponding to a Tile object, that implements
 *              the TileInterface to simulate a tile in 2048
 */

public class Tile implements TileInterface {
    // the x coordinates of the Tile
    private int x;
    // the y coordinates of the Tile
    private int y;
    // the value of the Tile
    private int value;
    // the dimension of a Tile 
    public static final int TileDimension = 50;

    /* Description: a constructor that has a 25% chance of creating
     *              a tile with a 2 value and 75% chance of creating
     *              a tile with a 4 value.
     * Input: n/a
     * Output: n/a 
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        if (Math.random() < 0.75) {
            value = 2;
        } else {
            value = 4;
        }
    }

    /* Description: set the color to draw Tiles depending on the value of the Tile
    * Input: n/a
    * Output: void
    */
    private void setTileColor() {
        if (value == 2) {
            PennDraw.setPenColor(238, 228, 218);
        } else if (value == 4) {
            PennDraw.setPenColor(237, 224, 200);
        } else if (value == 8) {
            PennDraw.setPenColor(242, 177, 121);
        } else if (value == 16) {
            PennDraw.setPenColor(245, 149, 99);
        } else if (value == 32) {
            PennDraw.setPenColor(246, 124, 95);
        } else if (value == 64) {
            PennDraw.setPenColor(246, 94, 59);
        } else if (value == 128) {
            PennDraw.setPenColor(237, 207, 114);
        } else if (value == 256) {
            PennDraw.setPenColor(237, 204, 97);
        } else if (value == 512) {
            PennDraw.setPenColor(237, 200, 80);
        } else if (value == 1024) {
            PennDraw.setPenColor(237, 197, 63);
        } else if (value == 2048) {
            PennDraw.setPenColor(237, 194, 46);
        }
    }

    /* Description: draw a Tile at its location 
    * Input: n/a
    * Output: void
    */
    public void draw() {
        PennDraw.setPenRadius(0.02);
        setTileColor();
        PennDraw.filledSquare(x, y, TileDimension);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(x, y, TileDimension);
        PennDraw.text(x, y, "" + value);
    }

    /* Description: animate a tile appearing at a location on the Grid
     * Input: n/a
     * Output: void
     */
    public void appear() {
        PennDraw.enableAnimation(100);
            if (PennDraw.hasNextKeyTyped()) {
                return;
            }
            setTileColor();
            PennDraw.filledSquare(x, y, TileDimension);
            PennDraw.advance();
        }

    /* Description: a getter function for a value of a Tile
     * Input: n/a 
     * Output: the value of a given Tile
     */
    public int getValue() {
        return value;
    }

    /* Description: returns true if the original tile and the input Tile i have
     *              the same value
     * Input: a Tile i 
     * Output: true if has the same value as Tile i, false otherwise
     */
    public boolean shouldMergeWith(Tile i) {
        return this.value == i.value;
    }

    /* Description: add the value of the two merging tiles
     * Input: a Tile i 
     * Output: void
     */
    public void mergeWith(Tile i) {
        this.value = this.value + i.value;
    }

    /* Description: a getter function for the x coordinate of a given Tile
     * Input: n/a 
     * Output: the x coordinate of a given Tile 
     */
    public int getX() {
        return x;
    }

    /* Description: a getter function for the x coordinate of a given Tile
     * Input: n/a 
     * Output: the y coordinate of a given Tile 
     */
    public int getY() {
        return y;
    }

    /* Description: sets this Tile's x to newX
     * Input: int newX
     * Output: void
     */
    public void setX(int newX) {
        x = newX;
    }

    /* Description: sets this Tile's y to newY
     * Input: int newY
     * Output: void
     */
    public void setY(int newY) {
        y = newY;
    }
}


    