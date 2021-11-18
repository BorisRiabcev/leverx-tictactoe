package com.riabcev.tasks;

import java.util.Scanner;

public class GameAgainstComputer extends Game implements Gameplay {
    private final char[] field = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    @Override
    public void playGame(Scanner scanner) {
        System.out.println("Starting of the game with computer:");
        if (this.determinateWhoPlaysCrosses()) {
            toPlayAgainstZeroes(scanner);
        } else {
            toPlayAgainstCrosses(scanner);
        }
    }

    private void toPlayAgainstZeroes(Scanner scanner) {
        printGameRules();
        System.out.println("You play with Crosses, computer play with Zeros");
        boolean isOver;
        char currentTurn;
        do {
            if (isFieldNotEmpty(field)) {
                drawField(field);
            }
            //turn of Player (X)
            currentTurn = 'X';
            int cellNumber = doHumanTurn(currentTurn, scanner, field);
            isOver = isEndGame(cellNumber, field);
            if (isOver) {
                break;
            }
            //Turn of Computer (O)
            currentTurn = 'O';
            cellNumber = doComputerTurn(currentTurn);
            isOver = isEndGame(cellNumber, field);
        } while (!isOver);
        drawField(field);
        if (!isFieldOccupied(field)) {
            System.out.println("The winner is " + currentTurn);
            if (currentTurn == 'X') {
                System.out.println("Congrats!!!");
            } else {
                System.out.println("Don't worry, you can win next time!");
            }
        } else {
            System.out.println("It's a draw!");
        }
    }

    private void toPlayAgainstCrosses(Scanner scanner) {
        printGameRules();
        System.out.println("You play with Zeros, computer play with Crosses");
        boolean isOver;
        char currentTurn;
        do {
            //Turn of Computer (X)
            currentTurn = 'X';
            int cellNumber = doComputerTurn(currentTurn);
            isOver = isEndGame(cellNumber, field);
            if (isOver) {
                break;
            }
            drawField(field);
            //turn of Player (O)
            currentTurn = 'O';
            cellNumber = doHumanTurn(currentTurn, scanner, field);

            isOver = isEndGame(cellNumber, field);

        } while (!isOver);
        drawField(field);
        if (!isFieldOccupied(field)) {
            System.out.println("The winner is " + currentTurn);
            if (currentTurn == 'O') {
                System.out.println("Congrats!!!");
            } else {
                System.out.println("Don't worry, you can win next time!");
            }
        } else {
            System.out.println("It's a draw!");
        }
    }

    private boolean determinateWhoPlaysCrosses() {
        double randomForPerson = (int) (Math.random() * 100);
        double randomForComputer = (int) (Math.random() * 100);
        return randomForPerson > randomForComputer;
    }

    private int doComputerTurn(char turn) {
        System.out.println("Turn of " + turn);
        int cellNumber = getCellNumberFromComputer();
        field[cellNumber-1] = turn;
        return cellNumber;
    }

    private int getCellNumberFromComputer() {
        int cellNumber;
        do {
            cellNumber = 1 + (int) (Math.random() * 9);
        } while (!isCellValid(cellNumber, field));
        return cellNumber;
    }

    private boolean isCellValid(int cellNumber, char[] field) {
        if (cellNumber <= 0 || cellNumber > field.length) {
            return false;
        }
        return field[cellNumber-1] == ' ';
    }
}
