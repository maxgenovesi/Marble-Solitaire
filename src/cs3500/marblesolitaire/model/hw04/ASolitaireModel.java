package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents an abstract marble solitaire model.
 */
public abstract class ASolitaireModel implements MarbleSolitaireModel {
  protected SlotState[][] board;

  protected ASolitaireModel() {
    // empty because construction happens in child classes
  }

  /**
   * Checks whether the supplied move is orthogonally valid. Overridden in triangle model.
   *
   * @param fromRow represents the starting row location
   * @param fromCol represents the starting column location
   * @param toRow   represents the final row location
   * @param toCol   represents the final column location
   * @return true if the move is valid, false otherwise
   */
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
            && ((Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
            || (Math.abs(fromCol - toCol) == 2 && fromRow == toRow));
  }

  /**
   * Checks whether a marble has a valid move orthogonally. Overridden in triangle model.
   *
   * @param row represents the marble's row
   * @param col represents the marble's column
   * @return true if the marble can make a move, false otherwise
   */
  protected boolean canMarbleMove(int row, int col) {
    return this.isMove(row, col, row + 2, col)
            || this.isMove(row, col, row - 2, col)
            || this.isMove(row, col, row, col + 2)
            || this.isMove(row, col, row, col - 2);
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int jumpedRow = (fromRow + toRow) / 2;
    int jumpedCol = (fromCol + toCol) / 2;

    if (!isMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid Move");
    } else {
      board[fromRow][fromCol] = SlotState.Empty;
      board[jumpedRow][jumpedCol] = SlotState.Empty;
      board[toRow][toCol] = SlotState.Marble;
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    // i represents every row of the board
    for (int i = 0; i < board.length; i++) {
      // j represents every column of the board
      for (int j = 0; j < board.length; j++) {
        SlotState slotState = board[i][j];
        if ((slotState == SlotState.Marble) && (this.canMarbleMove(i, j))) {
          return false;
        }
      }
    }
    return true;
  }

  public int getBoardSize() {
    return board.length;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < board.length && col < board.length && row >= 0 && col >= 0) {
      return board[row][col];
    } else {
      throw new IllegalArgumentException("Beyond dimensions of the board");
    }
  }

  /**
   * Checks how many marbles on remaining on the board, the score.
   *
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    int score = 0;

    // i represents the rows
    for (int i = 0; i < board.length; i++) {
      // j represents the columns
      for (int j = 0; j < board.length; j++) {
        if (getSlotAt(i, j) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
