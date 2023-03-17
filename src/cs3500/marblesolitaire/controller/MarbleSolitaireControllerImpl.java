package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents a controller object, allows the game to be played by the user.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructs a controller for a game of MarbleSolitaire.
   *
   * @param model represents a model of the game
   * @param view  represents a view of the game
   * @param input represents input from the user
   * @throws IllegalArgumentException if input or output are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view
          , Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Model, View, or Input cannot be null!");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    boolean beenQuit = false; // helps determine if game has been quit
    int posnCounter = 0; // helps determine how which posn we are on in movePosn
    int[] movePosn = new int[4]; // stores the values for move

    // renders the beginning
    try {
      view.renderBoard();
      view.renderMessage("\n");
      view.renderMessage("Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error transmitting data.");
    }
    Scanner scanner = new Scanner(this.input);
    while (scanner.hasNext() && !beenQuit) {
      String element = scanner.next();
      // for when game is quit
      if (element.equalsIgnoreCase("q")) {
        try {
          view.renderMessage("Game quit!\n");
          view.renderMessage("State of game when quit:\n");
          view.renderBoard();
          view.renderMessage("\n");
          view.renderMessage("Score: " + this.model.getScore());
          beenQuit = true;
        } catch (IOException e) {
          throw new IllegalStateException("Error transmitting data.");
        }
        // when current move does not have four values
      } else if (posnCounter < 4) {
        try {
          int asInt = Integer.parseInt(element);
          if (asInt >= 1) {
            movePosn[posnCounter] = asInt;
            posnCounter++;
          }
        } catch (NumberFormatException e) {
          try {
            view.renderMessage("Invalid input: " + element + "\n");
          } catch (IOException ex) {
            throw new IllegalStateException("Error transmitting data.");
          }
        }
      }
      // when current move has four values, makes the move
      if (posnCounter == 4) {
        int fromRow = movePosn[0] - 1;
        int fromCol = movePosn[1] - 1;
        int toRow = movePosn[2] - 1;
        int toCol = movePosn[3] - 1;
        try {
          model.move(fromRow, fromCol, toRow, toCol);
          posnCounter = 0;
          view.renderBoard();
          view.renderMessage("\n");
          view.renderMessage("Score: " + this.model.getScore() + "\n");
        } catch (IllegalArgumentException e) {
          try {
            // if move was invalid, signaled from model
            view.renderMessage("Invalid move. Play again.\n");
            posnCounter = 0;
            movePosn = new int[4];
          } catch (IOException ex) {
            throw new IllegalStateException("Error transmitting data.");
          }
        } catch (IOException e) {
          throw new IllegalStateException("Error transmitting data.");
        }
      }
    }
    // outside of while loop, checks if game is over
    if (model.isGameOver()) {
      try {
        view.renderMessage("Game over!\n");
        view.renderBoard();
        view.renderMessage("\n");
        view.renderMessage("Score: " + this.model.getScore());
      } catch (IOException e) {
        throw new IllegalStateException("Error transmitting data.");
      }
    }
    // checks if there are no more inputs and the method is still running
    if (!model.isGameOver() && !beenQuit) {
      throw new IllegalStateException("No more inputs.");
    }
  }
}