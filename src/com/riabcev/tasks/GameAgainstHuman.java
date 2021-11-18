package com.riabcev.tasks;

import java.util.Scanner;

public class GameAgainstHuman extends Game implements Gameplay {
    private final char[] field = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    @Override
    public void playGame(Scanner scanner) {
        System.out.println("Starting the game with friend:");
        printGameRules();
        boolean isXTurn = false;
        boolean isOver;
        do {
            isXTurn = !isXTurn;
            if (isFieldNotEmpty(field)) {
                drawField(field);
            }
            int cellNumber = doHumanTurn(isXTurn, scanner);
            isOver = isEndGame(cellNumber, field);
        } while (!isOver);
        drawField(field);
        if (!isFieldOccupied(field)) {
            System.out.println("The winner is " + findOutWhoseTurn(isXTurn));
            System.out.println("Congrats!!!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private int doHumanTurn(boolean isXTurn, Scanner scanner) {
        System.out.println("Turn of " + findOutWhoseTurn(isXTurn) + "\n" +
                "Enter number:");
        int cellNumber = enterNumber(scanner, field);
        field[cellNumber-1] = findOutWhoseTurn(isXTurn);
        return cellNumber;
    }

    private char findOutWhoseTurn(boolean mark) {
        return mark ? 'X' : 'O';
    }
}
