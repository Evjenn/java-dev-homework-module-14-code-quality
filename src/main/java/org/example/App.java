package org.example;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        char[] box = GameApp.initializeBoard();
        byte winner = 0;
        Scanner scanner = new Scanner(System.in);
        GameApp.LOGGER.info("Enter box number to select. Enjoy!");

        while (winner == 0) {
            GameApp.printBoard(box);
            GameApp.userMove(scanner, box);
            GameApp.computerMove(box);
            winner = GameApp.checkWinner(box);
        }

        GameApp.printBoard(box);
        GameApp.printResult(winner);
        scanner.close();
    }
}