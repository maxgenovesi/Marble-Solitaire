package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a game of european marble solitaire.
 */
public class EuropeanSolitaireModel extends ASolitaireModel {

  /**
   * First Constructor: Takes in zero parameters, constructs the board with the default sideLength
   * 3, with the empty slot in the center at (3, 3).
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Second Constructor: Takes in the starting row and starting column, constructs the board with
   * the default sideLength 3.
   *
   * @param row Starting row for empty slot
   * @param col Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position
   */
  public EuropeanSolitaireModel(int row, int col) {
    this(3, row, col);
  }

  /**
   * Third Constructor: Takes in sideLength, constructs the board with the empty slot in the
   * center.
   *
   * @param sideLength Side length of the board
   * @throws IllegalArgumentException if sideLength is not a positive odd number
   */
  public EuropeanSolitaireModel(int sideLength) {
    this(sideLength, (3 * sideLength) / 2 - 1, (3 * sideLength) / 2 - 1);
  }

  /**
   * Fourth Constructor: Takes in armThickness, sRow, and sCol.
   *
   * @param sideLength Side length of the board
   * @param row        Starting row for empty slot
   * @param col        Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position, or the
   *                                  armThickness is not a positive odd number
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    if ((sideLength < 1) || (sideLength % 2 == 0)) {
      throw new IllegalArgumentException("Side length must be a positive odd number");
    }
    if (row < 0 || col < 0 || row > 3 * sideLength - 2 || col > 3 * sideLength - 2) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    }
    int dimensions = (3 * sideLength - 2);
    board = new SlotState[dimensions][dimensions];

    for (int tempRow = 0; tempRow < dimensions; tempRow++) {
      for (int tempCol = 0; tempCol < dimensions; tempCol++) {
        // for upper third of board
        if (tempRow < sideLength - 1 && tempCol > sideLength - 2 - tempRow
                && tempCol < 2 * sideLength - 1 + tempRow) {
          board[tempRow][tempCol] = SlotState.Marble;
        } else {
          // for the middle of the board with no invalid slots
          if (tempRow > sideLength - 2 && tempRow < 2 * sideLength - 1) {
            board[tempRow][tempCol] = SlotState.Marble;
          } else {
            // for the lower third of the board
            if (tempRow > 2 * sideLength - 2 && tempCol > tempRow - 2 * sideLength + 1
                    && tempCol < (dimensions - 1) - ((tempRow + 1) % sideLength)) {
              board[tempRow][tempCol] = SlotState.Marble;
            } else {
              board[tempRow][tempCol] = SlotState.Invalid;
            }
          }
        }
      }
    }
    if (board[row][col] != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    } else {
      board[row][col] = SlotState.Empty;
    }
  }
}
