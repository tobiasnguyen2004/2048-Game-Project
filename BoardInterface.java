/* Name: Tung Nguyen
 * PennKey: ngtung
 * Execution: n/a
 * Description: the interface for the board class
 */

public interface BoardInterface {

    /* Description: draws all the Tiles of the current board
     * Input: n/a
     * Output: void
     */
    void draw();

    /* Description: updates the grid according to the move 
     *              specified by t
     * Input: the char t that defines direction
     * Output: void
     */
    void move(char t);

    /* Description: returns true if the user has won, false otherwise
     * Input: n/a
     * Output: true if the user has won, false otherwise
     */
    boolean hasWon();

    /* Description: Return true if the grid is full of number tiles and 
     *              there is no move possible for the user, false otherwise
     * Input: n/a
     * Output: true if the user ran out of possible moves and lost
     */
    boolean hasLost();

    /* Description: draw the win screen when the player wins
     * Input: n/a
     * Output: void
     */
    void winScreen();

    /* Description: does the lose screen when the player loses
     * Input: n/a
     * Output: void
     */
    void lossScreen();
}