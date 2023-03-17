package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.ASolitaireModel;

/**
 * Represents a game of English Marble Solitaire.
 */
public class EnglishSolitaireModel extends ASolitaireModel {

  /**
   * First Constructor: Takes in zero parameters, constructs the board with the default armThickness
   * 3, with the empty slot in the center at (3, 3).
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Second Constructor: Takes in the starting row and starting column, constructs the board with
   * the default armThickness 3.
   *
   * @param sRow Starting row for empty slot
   * @param sCol Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Third Constructor: Takes in armThickness, constructs the board with the empty slot in the
   * center.
   *
   * @param armThickness Arm thickness of the board
   * @throws IllegalArgumentException if armThickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) {
    this(armThickness, (3 * armThickness) / 2 - 1, (3 * armThickness) / 2 - 1);
  }

  /**
   * Fourth Constructor: Takes in armThickness, sRow, and sCol.
   *
   * @param armThickness Arm thickness of the board
   * @param sRow         Starting row for empty slot
   * @param sCol         Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position, or the
   *                                  armThickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    if ((armThickness < 1) || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
    if (sRow < 0 || sCol < 0 || sRow > 3 * armThickness - 2 || sCol > 3 * armThickness - 2) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    int dimensions = (3 * armThickness - 2);
    board = new SlotState[dimensions][dimensions];

    for (int row = 0; row < dimensions; row++) {
      for (int col = 0; col < dimensions; col++) {
        // for upper third of board
        if (row < armThickness - 1 && col > armThickness - 2 && col < 2 * armThickness - 1) {
          board[row][col] = SlotState.Marble;
        } else {
          // for the middle of the board with no invalid slots
          if (row > armThickness - 2 && row < 2 * armThickness - 1) {
            board[row][col] = SlotState.Marble;
          } else {
            // for the lower third of the board
            if (row > 2 * armThickness - 2 && col > armThickness - 2
                    && col < 2 * armThickness - 1) {
              board[row][col] = SlotState.Marble;
            } else {
              board[row][col] = SlotState.Invalid;
            }
          }
        }
      }
    }
    if (board[sRow][sCol] != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sRow][sCol] = SlotState.Empty;
    }
  }
}
