package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the text view for a game of triangle marble solitaire.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final Appendable appendable;

  /**
   * First Constructor: Defaults to System.out as default destination.
   *
   * @param model represents a model of Marble Solitaire
   * @throws IllegalArgumentException if the model is null
   */

  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Second Constructor: Defaults to an appendable as the default destination.
   *
   * @param model represents a model of Marble Solitaire
   * @param ap    represents an appendable
   * @throws IllegalArgumentException if the model or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Model or appendable cannot be null!");
    }
    this.model = model;
    this.appendable = ap;
  }

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
              // pads each row with spaces
              if (col == 0 && row != size - 1) {
                stringBuilder.append(String.format("%" + (size - 1 - row) + "s", ""));
              }
              // for last marble in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                stringBuilder.append("O");
              } else {
                stringBuilder.append("O ");
              }
              break;
            case Empty:
              // pads each row with spaces
              if (col == 0 && row != size - 1) {
                stringBuilder.append(String.format("%" + (size - 1 - row) + "s", ""));
              }
              // for last empty slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                stringBuilder.append("_");
              } else {
                stringBuilder.append("_ ");
              }
              break;
            case Invalid:
              // creates a new line and goes to next row
              stringBuilder.append("\n");
              row++;
              col = -1;
              break;

            default:
              System.out.println("Something is wrong.");
          }
        } catch (IOException e) {
          try {
            throw new IOException(e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
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
              // pads each row with spaces
              if (col == 0 && row != size - 1) {
                appendable.append(String.format("%" + (size - 1 - row) + "s", ""));
              }
              // for last marble in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                appendable.append("O");
              } else {
                appendable.append("O ");
              }
              break;
            case Empty:
              // pads each row with spaces
              if (col == 0 && row != size - 1) {
                appendable.append(String.format("%" + (size - 1 - row) + "s", ""));
              }
              // for last empty slot in a row
              if ((col + 1 < size && this.model.getSlotAt(row, col + 1) == invalid)
                      || (col + 1 > size - 1)) {
                appendable.append("_");
              } else {
                appendable.append("_ ");
              }
              break;
            case Invalid:
              // creates a new line and goes to next row
              appendable.append("\n");
              row++;
              col = -1;
              break;

            default:
              System.out.println("Something is wrong.");
          }
        } catch (IOException e) {
          try {
            throw new IOException(e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
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
