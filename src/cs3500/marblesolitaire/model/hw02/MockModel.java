package cs3500.marblesolitaire.model.hw02;

import java.util.Objects;

/**
 * Represents a mock model to keep track of controller transmissions to the model.
 */
public class MockModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  /**
   * Keeps track of transmissions between the view and controller.
   *
   * @param log keeps track of controller inputs
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format(
            "fromRow:% d, fromCol:% d, toRow:% d, toCol:% d", fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
