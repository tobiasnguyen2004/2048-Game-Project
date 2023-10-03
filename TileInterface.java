/* Name: Tung Nguyen
 * PennKey: ngtung
 * Execution: n/a
 * Description: the API for the Tile class
 */

public interface TileInterface {

    /* Description: draw a Tile at its location 
    * Input: n/a
    * Output: void
    */
    void draw();

    /* Description: animate a tile appearing at a location on the Grid
     * Input: n/a
     * Output: void
     */
    void appear();

    /* Description: a getter function for a value of a Tile
     * Input: n/a 
     * Output: the value of a given Tile
     */
    int getValue();

    /* Description: returns true if the original tile and the input Tile i have
     *              the same value
     * Input: a Tile i 
     * Output: true if has the same value as Tile i, false otherwise
     */
    boolean shouldMergeWith(Tile t);

    /* Description: add the value of the two merging tiles
     * Input: a Tile i 
     * Output: void
     */
    void mergeWith(Tile t);

    /* Description: a getter function for the x coordinate of a given Tile
     * Input: n/a 
     * Output: the x coordinate of a given Tile 
     */
    int getX();

    /* Description: a getter function for the x coordinate of a given Tile
     * Input: n/a 
     * Output: the y coordinate of a given Tile 
     */
     int getY();

    /* Description: sets this Tile's x to newX
     * Input: int newX
     * Output: void
     */
    void setX(int newX);

    /* Description: sets this Tile's y to newY
     * Input: int newY
     * Output: void
     */
    void setY(int newY);
}