package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a game of triangle marble solitaire.
 */
public class TriangleSolitaireModel extends ASolitaireModel {
  /**
   * First Constructor: Takes in zero parameters, constructs the board with the default dimensions
   * 5, with the empty slot at (0, 0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Second Constructor: Takes in the starting row and starting column, constructs the board with
   * the default dimensions 5.
   *
   * @param sRow Starting row for empty slot
   * @param sCol Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Third Constructor: Takes in dimensions, constructs the board with the empty slot at (0, 0).
   *
   * @param dimensions Dimensions of the board
   * @throws IllegalArgumentException if dimensions is not a positive number
   */
  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  /**
   * Fourth Constructor: Takes in dimensions, sRow, and sCol.
   *
   * @param dimensions Dimensions of the board
   * @param sRow       Starting row for empty slot
   * @param sCol       Starting column for empty slot
   * @throws IllegalArgumentException if sRow and sCol create an invalid starting position, or the
   *                                  dimensions is not a positive number
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    if (dimensions < 1) {
      throw new IllegalArgumentException("Dimension must be a positive number");
    }
    if (sRow < 0 || sCol < 0 || sRow > dimensions || sCol > dimensions) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    board = new SlotState[dimensions][dimensions];

    for (int row = 0; row < dimensions; row++) {
      for (int col = 0; col < dimensions; col++) {
        if (col <= row) {
          board[row][col] = SlotState.Marble;
        } else {
          board[row][col] = SlotState.Invalid;
        }
      }
    }
    if (board[sRow][sCol] != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sRow][sCol] = SlotState.Empty;
    }
  }

  /**
   * Checks whether the supplied move is horizontally/diagonally valid.
   *
   * @param fromRow represents the starting row location
   * @param fromCol represents the starting column location
   * @param toRow   represents the final row location
   * @param toCol   represents the final column location
   * @return true if the move is valid, false otherwise
   */
  @Override
  protected boolean isMove(int fromRow, int fromCol, int toRow, int toCol) {
    int jumpedRow = (fromRow + toRow) / 2;
    int jumpedCol = (fromCol + toCol) / 2;

    return (fromRow >= 0)
            && (fromRow < board.length)
            && (fromCol >= 0)
            && (fromCol < board.length)
            && (toRow >= 0)
            && (toRow < board.length)
            && (toCol >= 0)
            && (toCol < board.length)
            && (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
            && (this.getSlotAt(jumpedRow, jumpedCol) == SlotState.Marble)
            && ((Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2)
            || (Math.abs(fromCol - toCol) == 2 && fromRow == toRow)
            || (Math.abs(fromRow - toRow) == 2 && fromCol == toCol));
  }

  /**
   * Checks whether a marble has a valid move horizontally/diagonally.
   *
   * @param row represents the marble's row
   * @param col represents the marble's column
   * @return true if the marble can make a move, false otherwise
   */
  @Override
  protected boolean canMarbleMove(int row, int col) {
    return this.isMove(row, col, row + 2, col)
            || this.isMove(row, col, row - 2, col)
            || this.isMove(row, col, row, col + 2)
            || this.isMove(row, col, row, col - 2)
            || this.isMove(row, col, row - 2, col - 2)
            || this.isMove(row, col, row + 2, col + 2);
  }
}
