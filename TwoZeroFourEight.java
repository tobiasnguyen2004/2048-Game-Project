/* Name: Tung Nguyen
 * PennKey: ngtung
 * Execution: java TwoZeroFourEight
 * Description: simulates the game 2048. The user presses one of four keys - w,
 *              s, a, d - which represent the directions up, down, left, right to 
 *              move the tiles on the board and make them merge with each other to 
 *              make the number 2048.
 */

public class TwoZeroFourEight {
    private static Board board = new Board();
    // says whether the game is over
    private static boolean isOver = false;

    /* Description: resets the game board and draw the background
     * Input: n/a
     * Output: void
     */
    public static void drawGameBoard() {
        PennDraw.clear();
        PennDraw.setPenRadius(0.05);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(250, 250, 200);
        PennDraw.setPenColor(PennDraw.WHITE);
        for (int i = 0; i < Board.gridLength; i++) {
            for (int j = 0; j < Board.gridLength; j++) {
                PennDraw.filledSquare(Board.xPositions[i], Board.yPositions[j],
                                      Tile.TileDimension);
            }
        }
    }

    public static void main(String[] args) {
        PennDraw.setCanvasSize(700, 800);
        PennDraw.setXscale(-50, 550);
        PennDraw.setYscale(-50, 650); 

        while (true) {
            drawGameBoard();
            board.draw();
            board.drawScoreBoard();

            if (board.hasWon()) {
                board.winScreen();
                isOver = true;
            }

            if (board.hasLost()) {
                board.lossScreen();
                isOver = true;
            }

            // the player inputs their move 
            if (PennDraw.hasNextKeyTyped() && !isOver) {
                char t = PennDraw.nextKeyTyped();
                board.move(t);
            }
            PennDraw.advance();
        }
    }
}