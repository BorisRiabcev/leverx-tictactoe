package com.riabcev.tasks;

import java.util.Scanner;

public class Game {

    private final char[] field = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    //main methods

    //press f for play with friend)
    protected boolean chooseGameMod(Scanner scanner) {
        System.out.println("Hello player, enter f if you want to play TicTacToe with other human\n" +
                "or enter any other symbol if you want to play TicTacToe with computer");
        if ("f".equals(scanner.next())) {
            System.out.println("You chose a game with friend:");
            return true;
        } else {
            System.out.println("You chose a game with computer:");
            return false;
        }
    }

    protected void playGameWithFriend(Scanner scanner) {
        System.out.println("Starting the game with friend:");
        printGameRules();
        boolean isXTurn = false;
        boolean isOver;
        do {
            isXTurn = !isXTurn;
            if (isFieldNotEmpty()) {
                drawField();
            }
            int cellNumber = doHumanTurn(isXTurn, scanner);
            isOver = isEndGame(cellNumber);
        } while (!isOver);
        drawField();
        if (!isFieldOccupied()) {
            System.out.println("The winner is " + findOutWhoseTurn(isXTurn));
            System.out.println("Congrats!!!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    protected void playGameWithComputer(Scanner scanner) {
        System.out.println("Starting of the game with computer:");
        if (this.determinateWhoPlaysCrosses()) {
            toPlayAgainstZeroes(scanner);
        } else {
            toPlayAgainstCrosses(scanner);
        }
    }

    //common methods
    private void printGameRules() {
        System.out.println("Rules of the game:\n" +
                "Tic-tac-toe is played on a three-by-three grid by two players,\n" +
                "who alternately place the marks X and O in one of the nine spaces in the field.\n" +
                "The player who succeeds in placing three of their marks in a horizontal,\n" +
                "vertical, or diagonal row is the winner.\n" +
                "To make a move, you must enter the number of the cell\n" +
                "where you want to place your piece.\n" +
                "You can see numbers of cells below:");
        System.out.println("|  1  |  2  |  3  |");
        System.out.println("|  4  |  5  |  6  |");
        System.out.println("|  7  |  8  |  9  |");
    }

    private void drawField() {
        System.out.println("| " + field[0] + " | " + field[1] + " | " + field[2] + " |");
        System.out.println("| " + field[3] + " | " + field[4] + " | " + field[5] + " |");
        System.out.println("| " + field[6] + " | " + field[7] + " | " + field[8] + " |");
    }

    private boolean isFieldOccupied() {
        for (char cell : field) {
            if (' ' == cell) {
                return false;
            }
        }
        return true;
    }

    private boolean isFieldNotEmpty() {
        for (char cell : field) {
            if (' ' != cell) {
                return true;
            }
        }
        return false;
    }

    private boolean isEndGame(int cellNumber) {
        return isFieldOccupied() || isAnyPlayerWin(cellNumber);
    }

    private boolean isAnyPlayerWin(int cellNumber) {
        if (cellNumber % 2 == 0) {
            switch (cellNumber) {
                case 2:
                    return checkTheFirstRow() || checkTheSecondColumn();
                case 4:
                    return checkTheSecondRow() || checkTheFirstColumn();
                case 6:
                    return checkTheSecondRow() || checkTheThirdColumn();
                default: //case 8:
                    return checkTheThirdRow() || checkTheSecondColumn();
            }
        } else if (cellNumber == 5) {
            return checkTheSecondRow() || checkTheSecondColumn() || checkBackslash() || checkSlash();
        } else {
            switch (cellNumber) {
                case 1:
                    return checkTheFirstRow() || checkTheFirstColumn() || checkBackslash();
                case 3:
                    return checkTheFirstRow() || checkTheThirdColumn() || checkSlash();
                case 7:
                    return checkTheThirdRow() || checkTheFirstColumn() || checkSlash();
                default:  //case 9:
                    return checkTheThirdRow() || checkTheThirdColumn() || checkBackslash();
            }
        }
    }

    private boolean checkTheFirstRow() {
        return (field[1] == field[0] && field[1] == field[2]);
    }

    private boolean checkTheSecondRow() {
        return (field[4] == field[5] && field[4] == field[3]);
    }

    private boolean checkTheThirdRow() {
        return (field[7] == field[6] && field[7] == field[8]);
    }

    private boolean checkTheFirstColumn() {
        return (field[3] == field[0] && field[3] == field[6]);
    }

    private boolean checkTheSecondColumn() {
        return (field[4] == field[1] && field[4] == field[7]);
    }

    private boolean checkTheThirdColumn() {
        return (field[5] == field[2] && field[5] == field[8]);
    }

    private boolean checkBackslash() {
        return (field[4] == field[0] && field[4] == field[8]);
    }

    private boolean checkSlash() {
        return (field[4] == field[2] && field[4] == field[6]);
    }

    private int enterNumber(Scanner scanner) {
        while (true) {
            try {
                int newNumberForTurn = Integer.parseInt(scanner.next());
                if (newNumberForTurn > 0 && newNumberForTurn <= field.length && field[newNumberForTurn-1] == ' ') {
                    return newNumberForTurn;
                }
                System.out.println("Please choose free cell of field and enter it number (from 1 to 9)");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter the number (from 1 to 9):");
            }
        }
    }

    //methods for Game with Human
    private char findOutWhoseTurn(boolean mark) {
        return mark ? 'X' : 'O';
    }

    private int doHumanTurn(boolean isXTurn, Scanner scanner) {
        System.out.println("Turn of " + findOutWhoseTurn(isXTurn) + "\n" +
                "Enter number:");
        int cellNumber = enterNumber(scanner);
        field[cellNumber-1] = findOutWhoseTurn(isXTurn);
        return cellNumber;
    }

    //methods for Game with Computer
    private int doHumanTurn(char turn, Scanner scanner) {
        System.out.println("Turn of " + turn + "\n" +
                "Enter number:");
        int cellNumber = enterNumber(scanner);
        field[cellNumber-1] = turn;
        return cellNumber;
    }


    private boolean determinateWhoPlaysCrosses() {
        double randomForPerson = (int) (Math.random() * 100);
        double randomForComputer = (int) (Math.random() * 100);
        return randomForPerson > randomForComputer;
    }

    private void toPlayAgainstZeroes(Scanner scanner) {
        printGameRules();
        System.out.println("You play with Crosses, computer play with Zeros");
        boolean isOver;
        char currentTurn;
        do {
            if (isFieldNotEmpty()) {
                drawField();
            }
            //turn of Player (X)
            currentTurn = 'X';
            int cellNumber = doHumanTurn(currentTurn, scanner);
            isOver = isEndGame(cellNumber);
            if (isOver) {
                break;
            }
            //Turn of Computer (O)
            currentTurn = 'O';
            cellNumber = doComputerTurn(currentTurn);
            isOver = isEndGame(cellNumber);
        } while (!isOver);
        drawField();
        if (!isFieldOccupied()) {
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
            isOver = isEndGame(cellNumber);
            if (isOver) {
                break;
            }
            drawField();
            //turn of Player (O)
            currentTurn = 'O';
            cellNumber = doHumanTurn(currentTurn, scanner);

            isOver = isEndGame(cellNumber);

        } while (!isOver);
        drawField();
        if (!isFieldOccupied()) {
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
        } while (!isCellValid(cellNumber));
        return cellNumber;
    }

    private boolean isCellValid(int cellNumber) {
        if (cellNumber <= 0 || cellNumber > field.length) {
            return false;
        }
        return field[cellNumber-1] == ' ';
    }
}
