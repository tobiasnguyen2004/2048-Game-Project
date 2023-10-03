/* Name: Tung Nguyen
 * PennKey: ngtung
 * Execution: n/a
 * Description:  Board class, corresponding to a Board object, that implements
 *               the BoardInterface to simulate the board in 2048
 */

public class Board implements BoardInterface {
    // array of Tiles representing the current grid
    private Tile[][] currentGrid;
    // number of Tiles currently on the board
    private int numberOfTiles;
    // the number of moves made by the player
    private int numOfMoves;
    // the x-position of each corresponding Tile in the Grid
    public static final int[] xPositions = {100, 200, 300, 400};
    // the y-position of each corresponding Tile in the Grid
    public static final int[] yPositions = {100, 200, 300, 400};
    // number of Tiles per column or row
    public static final int gridLength = 4;

    /* Description: constructor - build a board with 2 tiles on it
     * Input: n/a
     * Output: n/a
     */
    public Board() {
        currentGrid = new Tile[gridLength][gridLength];
        numOfMoves = 0;
        addTile();
        addTile();
    }

    /* Description: add a Tile with 2 or 4 to a random empty tile on the current grid
     * Input: n/a
     * Output: void
     */
    private void addTile() {
        while (true) {
            int i = (int) (Math.random() * gridLength);
            int j = (int) (Math.random() * gridLength);
            if (currentGrid[i][j] == null) {
                currentGrid[i][j] = new Tile(xPositions[j], yPositions[i]);
                numberOfTiles++;
                currentGrid[i][j].appear();
                return;
            }
        }
    }

    /* Description: sets the x and y coordinates of each Tile in the list for
     *              their positions on the board to match their positions on the
     *              grid
     * Input: n/a
     * Output: void
     */
    private void updatePositions() {
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                if (currentGrid[i][j] != null) {
                    currentGrid[i][j].setX(xPositions[j]);
                    currentGrid[i][j].setY(yPositions[i]);
                }
            }
        }
    }

    /* Description: draws all the Tiles of the current board
     * Input: n/a
     * Output: void
     */
    public void draw() {
        PennDraw.setFontSize(30);
        // iterate over the grid
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                if (currentGrid[i][j] != null) {
                    currentGrid[i][j].draw();
                }
            }
        }
    }

    /* Description: returns true if the key pressed is a valid move for
     *              the user (one of the four directions corresponding to
     *              the four keys w, a, s, d)
     * Input: a char t that has been inputed by the player
     * Output: true if the key typed is valid, false otherwise
     */
    private boolean keyIsValid(char t) {
        if (t == 'w' || t == 's' || t == 'a' || t == 'd') {
            // iterate over the grid
            for (int i = 0; i < gridLength; i++) {
                for (int j = 0; j < gridLength; j++) {
                    if (canMove(i, j, t)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Description: returns true if a tile at int i, int j in currentGrid
     *              is blocked by another Tile (with different value) in the
     *              direction described by char t
     * Input: int i and j are the coordinates of the original tile, char t
     *        determines the direction in which the tiles moves
     * Output: true if the original tile in currentGrid is blocked by
     *         another Tile (with different value) in the direction described
     *         by char t, false otherwise
     */
    private boolean isBlockedByTile(int i, int j, char t) {
        if (nextTileinLine(i, j, t) == null) {
            return false;
        }
        if (nextTileinLine(i, j, t).shouldMergeWith(currentGrid[i][j])) {
            return false;
        }
        // else, reaches a tile with different value, so the tile is blocked
        return true;
    }

    /* Description: returns true if Tile t at int i, int j in currentGrid
     *              is blocked by an edge in the direction descirbed by char t
     * Input: int i, int j that gives the position in currentGrid of the
     *        Tile we want to determine whether can move, char t that determines
     *        the move we want to make on this Tile
     * Output: true if Tile t at int i, int j in currentGrid is blocked by
     *         an edge in the direction descirbed by char t, false otherwise
     */
    private boolean reachesEdge(int i, int j, char t) {
        if (t == 'w') {
            // if ton top of grid, cannot go higher (blocked by edge)
            if (i >= gridLength - 1) {
                return true;
            }
        }
        if (t == 's') {
            // if t is on bottom of grid, cannot go down (blocked by edge)
            if (i <= 0) {
                return true;
            }
        }
        if (t == 'a') {
            // if t is on left end of grid, cannot go to left (blocked by edge)
            if (j <= 0) {
                return true;
            }
        }
        if (t == 'd') {
            // if t is on right end of grid, cannot go to right (edge blocks)
            if (j >= gridLength - 1) {
                return true;
            }
        }
        return false;
    }

    /* Description: returns true if Tile t at int i, int j in currentGrid
     *              can move (is not blocked by another Tile or by an edge) in
     *              the direction described by char t, in the currentGrid
     *              array, false otherwise
     * Input: int i, int j of Tile in currentGrid and char t that determines
     *        the move we want to make on this Tile
     * Output: true if can move the Tile t at int i, int j in currentGrid,
     *         false otherwise
     */
    private boolean canMove(int i, int j, char t) {
        if (currentGrid[i][j] == null) {
            return false;
        }
        if (reachesEdge(i, j, t)) {
            return false;
        }
        if (isBlockedByTile(i, j, t)) {
            return false;
        }
        // else, it is possible to move the Tile in t direction
        return true;
    }

    /* Description: returns the Tile right after the Tile at int i, int j
     *              in the direction described by char t
     * Input: int i, int j of Tile in currentGrid and char t that determines
     *        the move we want to make on this Tile
     * Output: the Tile right after the Tile at int i, int j in the direction 
     *         described by char t
     */
    private Tile nextTileinLine(int i, int j, char t) {
        if (t == 'w') {
            if (i + 1 < gridLength) {
                return currentGrid[i + 1][j];
            }
        } else if (t == 's') {
            if (i - 1 >= 0) {
                return currentGrid[i - 1][j];
            }
        } else if (t == 'a') {
            if (j - 1 >= 0) {
                return currentGrid[i][j - 1];
            }
        } else if (t == 'd') {
            if (j + 1 < gridLength) {
                return currentGrid[i][j + 1];
            }
        }
        // else, key is not valid, or there is no next block, so null
        return null;
    }

    /* Description: returns true if Tile t at int i, int j in currentGrid
     *              cannot move in the direction described by char t
     * Input: int i, int j that gives the position in currentGrid of the
     *        Tile to determine whether it can move in the 
     *         direction of char t
     * Output: true if Tile t at int i, int j in currentGrid cannot move
     *         in the direction described by char t
     */
    private boolean cannotMove(int i, int j, char t) {
        if (reachesEdge(i, j, t)) {
            return true;
        }
        else if (nextTileinLine(i, j, t) != null) {
            return true;
        }
        return false;
    }


    /* Description: when the player presses char t, all blocks move in 
     *              the direction described by char t
     * Input: the char t that defines direction
     * Output: void
     */
    private void mergeBoard(char t) {
        if (t == 'w') {
            for (int i = gridLength - 1; i >= 0; i--) {
                for (int j = 0; j < gridLength; j++) {
                    int y = i;
                    while (!cannotMove(y, j, t)) {
                        // change position to the next spot in line
                        currentGrid[y + 1][j] = currentGrid[y][j];
                        currentGrid[y][j] = null;
                        y++;
                    }
                }
            }
        } else if (t == 's') {
            for (int i = 0; i < gridLength; i++) {
                for (int j = 0; j < gridLength; j++) {
                    int y = i;
                    while (!cannotMove(y, j, t)) {
                        currentGrid[y - 1][j] = currentGrid[y][j];
                        currentGrid[y][j] = null;
                        y--;
                    }
                }
            }
        } else if (t == 'a') {
            for (int j = 0; j < gridLength; j++) {
                for (int i = 0; i < gridLength; i++) {
                    int y = j;
                    while (!cannotMove(i, y, t)) {
                        currentGrid[i][y - 1] = currentGrid[i][y];
                        currentGrid[i][y] = null;
                        y--;
                    }
                }
            }
        } else if (t == 'd') {
            for (int j = gridLength - 1; j >= 0; j--) {
                for (int i = 0; i < gridLength; i++) {
                    int y = j;
                    while (!cannotMove(i, y, t)) {
                        currentGrid[i][y + 1] = currentGrid[i][y];
                        currentGrid[i][y] = null;
                        y++;
                    }
                }
            }
        }
    }

    /* Description: returns true if Tile t at int i, int j in currentGrid
     *              is adjacent to a Tile with same value in the direction
     *              described by char t
     * Input: int i, int j of the Tile in currentGrid of to determine whether 
     *        can merge in the direction of char t
     * Output: true if can merge the Tile t at int i, int j in currentGrid,
     *         with the next tile in line, false otherwise
     */
    private boolean canMerge(int i, int j, char t) {
        // cannot merge if there is no tile
        if (currentGrid[i][j] == null) {
            return false;
        }
        // cannot merge with tile if there is no next tile
        if (nextTileinLine(i, j, t) == null) {
            return false;
        }
        return nextTileinLine(i, j, t).shouldMergeWith(currentGrid[i][j]);
    }

    /* Description: merge all the Tiles possible in the direction of the
                    direction described by char t. Recount the number of 
                    Tiles
     * Input: char t to describe the direction of the move 
     * Output: void
     */
    private void newBoard(char t) {
        if (t == 'w') {
            for (int i = gridLength - 1; i >= 0; i--) {
                for (int j = 0; j < gridLength; j++) {
                    if (canMerge(i, j, t)) {
                        nextTileinLine(i, j, t).mergeWith(currentGrid[i][j]);
                        currentGrid[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (t == 's') {
            for (int i = 0; i < gridLength; i++) {
                for (int j = 0; j < gridLength; j++) {
                    if (canMerge(i, j, t)) {
                        nextTileinLine(i, j, t).mergeWith(currentGrid[i][j]);
                        currentGrid[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (t == 'a') {
            for (int j = 0; j < gridLength; j++) {
                for (int i = 0; i < gridLength; i++) {
                    if (canMerge(i, j, t)) {
                        nextTileinLine(i, j, t).mergeWith(currentGrid[i][j]);
                        currentGrid[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        } else if (t == 'd') {
            for (int j = gridLength - 1; j >= 0; j--) {
                for (int i = 0; i < gridLength; i++) {
                    if (canMerge(i, j, t)) {
                        nextTileinLine(i, j, t).mergeWith(currentGrid[i][j]);
                        currentGrid[i][j] = null;
                        numberOfTiles--;
                    }
                }
            }
        }
    }

    /* Description: updates the currentGrid for the Tiles inside to match
     *              its new situation after a valid move described by char t.
     *              Creates one new Tile in a random Tile on the grid
     *              Displays nice animations for the Tiles to move smoothly
     *              on the board.
     * Input: the char t that defines direction
     * Output: void
     */
    public void move(char t) {
        if (keyIsValid(t)) {
            mergeBoard(t);
            newBoard(t);
            mergeBoard(t);
            updatePositions();
            addTile();
            numOfMoves++;
        }
    }

    /* Description: The player loses if the grid is full of Tiles and there 
     *              are no possible moves left
     * Input: n/a
     * Output: true if all there are no possible moves left for the player
     */
    public boolean hasLost() {
        // if the grid is not full of Tiles, the player hasn't lost
        if (numberOfTiles <= 15) {
            return false;
        }

        // if any two adjacent Tiles have the same value, it is possible to move
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                // check if Tile on the top has same value
                if (j + 1 < gridLength && currentGrid[i][j].shouldMergeWith(
                    currentGrid[i][j + 1])) {
                    return false;
                }
                // check if Tile on the right has same value
                if (i + 1 < gridLength && currentGrid[i][j].shouldMergeWith(
                    currentGrid[i + 1][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Description: If the player manage to merge two 1024 tiles to make 2048,
     *              the player wins
     * Input: n/a
     * Output: true if the user has won, false otherwise
     */
    public boolean hasWon() {
        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid[i].length; j++) {
                if (currentGrid[i][j] != null &&
                    currentGrid[i][j].getValue() >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Description: draw the scoreboard so the user can keep track of the 
     *              number of moves they made
     * Input: n/a
     * Output: void
     */
    public void drawScoreBoard() {
        PennDraw.setFontSize(20);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(50, 550, "Moves:" + numOfMoves);
    }

    /* Description: does the drawing in case we win
     * Input: n/a
     * Output: void
     */
    public void winScreen() {
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledSquare(300, 300, 600);
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "Congratulaions! You won!");
        PennDraw.text(250, 275, "Number of Moves:" + numOfMoves);
    }

    /* Description: does the drawing in case we lose
     * Input: n/a
     * Output: void
     */
    public void lossScreen() {
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledSquare(300, 300, 600);
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(250, 350, "You lost :(");
        PennDraw.text(250, 275, "Number of Moves:" + numOfMoves);
    }
}