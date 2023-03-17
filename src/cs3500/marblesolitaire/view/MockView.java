package cs3500.marblesolitaire.view;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a mock text view to keep track of controller transmissions to the view.
 */
public class MockView implements MarbleSolitaireView {
  private final StringBuilder log;

  /**
   * Keeps track of transmissions between the view and controller.
   *
   * @param log keeps track of controller inputs
   */
  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void renderBoard() throws IOException {
    // void, no need to have body in mock
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(String.format("Message/Newline Message: %s\n", message));
  }
}
