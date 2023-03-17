package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Creates the objects and allows the program to be played.
 */
public final class MarbleSolitaire {
  /**
   * Constructor for main that allows the game to be played.
   *
   * @param args the strings received from the console
   */
  public static void main(String[] args) {
    String gameMode = "";
    int size = 0;
    int row = 0;
    int col = 0;

    // default game
    if (args[0].equals("english") || args[0].equals("european")) {
      gameMode = args[0];
      size = 3;
      row = 3;
      col = 3;
    } else if (args[0].equals("triangle")) {
      gameMode = args[0];
      size = 5;
      row = 0;
      col = 0;
    }

    // one custom setting, english and european
    if (args.length > 1 && args.length < 5
            && (gameMode.equals("english") || gameMode.equals("european"))) {
      if (args[1].equals("-size")) {
        size = Integer.parseInt(args[2]);
        row = (3 * Integer.parseInt(args[2])) / 2 - 1;
        col = (3 * Integer.parseInt(args[2])) / 2 - 1;
      } else if (args[1].equals("-hole")) {
        row = Integer.parseInt(args[2]);
        col = Integer.parseInt(args[3]);
      }
    }

    // one custom setting, triangle
    if (args.length > 1 && args.length < 5 && gameMode.equals("triangle")) {
      if (args[1].equals("-size")) {
        size = Integer.parseInt(args[2]);
      } else if (args[1].equals("-hole")) {
        row = Integer.parseInt(args[2]);
        col = Integer.parseInt(args[3]);
      }
    }

    // all custom settings
    if (args.length > 5) {
      if (args[1].equals("-size") && args[3].equals("-hole")) {
        size = Integer.parseInt(args[2]);
        row = Integer.parseInt(args[4]);
        col = Integer.parseInt(args[5]);
      }
    }

    //constructs the model, view, controller, and starts the game
    switch (gameMode) {
      case "english":
        MarbleSolitaireModel englishModel = new EnglishSolitaireModel(size, row, col);
        MarbleSolitaireView englishView = new MarbleSolitaireTextView(englishModel);
        MarbleSolitaireControllerImpl englishController =
                new MarbleSolitaireControllerImpl(englishModel,
                        englishView, new InputStreamReader(System.in));
        englishController.playGame();
        break;
      case "european":
        MarbleSolitaireModel europeanModel = new EuropeanSolitaireModel(size, row, col);
        MarbleSolitaireView europeanView = new MarbleSolitaireTextView(europeanModel);
        MarbleSolitaireControllerImpl europeanController =
                new MarbleSolitaireControllerImpl(europeanModel,
                        europeanView, new InputStreamReader(System.in));
        europeanController.playGame();
        break;
      case "triangle":
        MarbleSolitaireModel triangleModel = new TriangleSolitaireModel(size, row, col);
        MarbleSolitaireView triangleView = new TriangleSolitaireTextView(triangleModel);
        MarbleSolitaireControllerImpl triangleController =
                new MarbleSolitaireControllerImpl(triangleModel,
                        triangleView, new InputStreamReader(System.in));
        triangleController.playGame();
        break;
      default:
        break;
    }
  }
}
