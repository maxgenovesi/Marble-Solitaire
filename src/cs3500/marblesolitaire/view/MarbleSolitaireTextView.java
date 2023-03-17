package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the text view for a game of Marble Solitaire.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final Appendable appendable;

  /**
   * First Constructor: Defaults to System.out as default destination.
   *
   * @param model represents a model of Marble Solitaire
   * @throws IllegalArgumentException if the model is null
   */

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Second Constructor: Defaults to an appendable as the default destination.
   *
   * @param model represents a model of Marble Solitaire
   * @param ap    represents an appendable
   * @throws IllegalArgumentException if the model or appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model or appendable cannot be null!");
    }
    this.model = model;
    this.appendable = ap;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   * @throws IllegalArgumentException if the board cannot be printed
   */
  @Override
  public String toString() throws IllegalArgumentException {
    Appendable stringBuilder = new StringBuilder();
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;
    int size = this.model.getBoardSize();

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        try {
          switch (this.model.getSlotAt(row, col)) {
            case Marble:
              // for last marble slot in last row
              if ((row + 1 == size)
                      && ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1))) {
                stringBuilder.append("O");
                // ends the loop
                col = size - 1;
                break;
              }
              // for the last marble slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                stringBuilder.append("O" + "\n");
                // goes to beginning of next row
                row++;
                col = -1;
                break;
              }
              // marble with a slot to it's right
              stringBuilder.append("O ");
              break;
            case Empty:
              // for last empty slot in last row
              if ((row + 1 == size)
                      && ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1))) {
                stringBuilder.append("_");
                // ends the loop
                col = size - 1;
                break;
              }
              // for last empty slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                stringBuilder.append("_" + "\n");
                // goes to beginning of next row
                row++;
                col = -1;
                break;
              }
              // empty slot with slot to it's right
              stringBuilder.append("_ ");
              break;
            case Invalid:
              // any invalid slot with a valid slot to it's right
              stringBuilder.append("  ");
              break;
            default:
              System.out.println("Something is wrong.");
          }
        } catch (IOException e) {
          throw new IllegalArgumentException("Cannot print board");
        }
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;
    int size = this.model.getBoardSize();

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        try {
          switch (this.model.getSlotAt(row, col)) {
            case Marble:
              // for last marble slot in last row
              if ((row + 1 == size)
                      && ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1))) {
                appendable.append("O");
                // ends the loop
                col = size - 1;
                break;
              }
              // for the last marble slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                appendable.append("O" + "\n");
                // goes to beginning of next row
                row++;
                col = -1;
                break;
              }
              // marble with a slot to it's right
              appendable.append("O ");
              break;
            case Empty:
              // for last empty slot in last row
              if ((row + 1 == size)
                      && ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1))) {
                appendable.append("_");
                // ends the loop
                col = size - 1;
                break;
              }
              // for last empty slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                appendable.append("_" + "\n");
                // goes to beginning of next row
                row++;
                col = -1;
                break;
              }
              // empty slot with slot to it's right
              appendable.append("_ ");
              break;
            case Invalid:
              // any invalid slot with a valid slot to it's right
              appendable.append("  ");
              break;
            default:
              System.out.println("Something is wrong.");
          }
        } catch (IOException e) {
          throw new IOException(e);
        }
      }
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IOException(e);
    }
  }
}
