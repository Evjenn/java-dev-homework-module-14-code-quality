package org.example;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameApp {

    static final Logger LOGGER = Logger.getLogger(GameApp.class.getName());
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';

    private GameApp() {
        //no - op
    }

    public static char[] initializeBoard() {
        return new char[]{ '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    }

    public static void printBoard(char[] box) {
        LOGGER.log(Level.INFO, () ->
                "\n\n " + box[0] + " | " + box[1] + " | " + box[2] +
                        "\n-----------\n " + box[3] + " | " + box[4] + " | " + box[5] +
                        "\n-----------\n " + box[6] + " | " + box[7] + " | " + box[8] + "\n");
    }

    private static boolean isValidInput(int input) {
        return input >= 1 && input <= 9;
    }

    public static void userMove(Scanner scanner, char[] box) {
        boolean boxAvailable = boardStatus(box);
        while (boxAvailable) {

            int input = scanner.nextInt();
            int index = input - 1;

            if (!isValidInput(input)) {
                LOGGER.warning("Invalid input. Enter again.");
            } else {
                if (box[index] == USER_MARK || box[index] == COMPUTER_MARK) {
                    LOGGER.warning("That one is already in use. Enter another.");
                } else {
                    box[index] = USER_MARK;
                    break;
                }
            }
        }
    }

    public static void computerMove(char[] box) {
        byte rand;
        boolean boxAvailable = boardStatus(box);
        while (boxAvailable) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != USER_MARK && box[rand - 1] != COMPUTER_MARK) {
                box[rand - 1] = COMPUTER_MARK;
                break;
            }
        }
    }

    private static boolean playerWin(char[] box){
        return (box[0] == 'X' && box[1] == 'X' && box[2] == 'X') || (box[3] == 'X' && box[4] == 'X' && box[5] == 'X') || (box[6] == 'X' && box[7] == 'X' && box[8] == 'X') ||
                (box[0] == 'X' && box[3] == 'X' && box[6] == 'X') || (box[1] == 'X' && box[4] == 'X' && box[7] == 'X') || (box[2] == 'X' && box[5] == 'X' && box[8] == 'X') ||
                (box[0] == 'X' && box[4] == 'X' && box[8] == 'X') || (box[2] == 'X' && box[4] == 'X' && box[6] == 'X');
    }

    private static boolean aiWin(char[] box){
        return (box[0]=='O' && box[1]=='O' && box[2]=='O') || (box[3]=='O' && box[4]=='O' && box[5]=='O') || (box[6]=='O' && box[7]=='O' && box[8]=='O') ||
                (box[0]=='O' && box[3]=='O' && box[6]=='O') || (box[1]=='O' && box[4]=='O' && box[7]=='O') || (box[2]=='O' && box[5]=='O' && box[8]=='O') ||
                (box[0]=='O' && box[4]=='O' && box[8]=='O') || (box[2]=='O' && box[4]=='O' && box[6]=='O');
    }

    private static boolean boardStatus(char[] box) {
        boolean boxAvailable = false;
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                boxAvailable = true;
                break;
            }
        }
        return boxAvailable;
    }

    public static byte checkWinner(char[] box){
        byte winner = 0;
        if (playerWin(box)) {
            winner = 1;
        } else if (aiWin(box)) {
            winner = 2;
        } else if (!boardStatus(box)) {
            winner = 3;
        }
        return winner;
    }

    public static void printResult(byte winner) {
        switch (winner) {
            case 1:
                LOGGER.info("You won the game!");
                break;
            case 2:
                LOGGER.info("You lost the game!");
                break;
            case 3:
                LOGGER.info("It's a draw!");
                break;
            default:
                break;
        }

        LOGGER.info("Created by Shreyas Saha. Thanks for playing!");
    }

}
