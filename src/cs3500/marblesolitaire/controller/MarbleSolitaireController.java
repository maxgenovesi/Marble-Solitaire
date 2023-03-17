package cs3500.marblesolitaire.controller;

/**
 * Represents a controller for Marble Solitaire, its method allows the game to be played.
 */
public interface MarbleSolitaireController {
  /**
   * Allows the user to play the game.
   *
   * @throws IllegalStateException if the readable or appendable cannot be successfully transmitted
   */
  void playGame() throws IllegalArgumentException;
}
