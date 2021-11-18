package com.riabcev.tasks;

import java.util.Scanner;

public class Game {
    protected static boolean chooseGameMod(Scanner scanner) {
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

    protected void printGameRules() {
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

    protected int enterNumber(Scanner scanner, char[] field) {
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

    protected int doHumanTurn(char turn, Scanner scanner, char[] field) {
        System.out.println("Turn of " + turn + "\n" +
                "Enter number:");
        int cellNumber = enterNumber(scanner, field);
        field[cellNumber-1] = turn;
        return cellNumber;
    }

    protected void drawField(char[] field) {
        System.out.println("| " + field[0] + " | " + field[1] + " | " + field[2] + " |");
        System.out.println("| " + field[3] + " | " + field[4] + " | " + field[5] + " |");
        System.out.println("| " + field[6] + " | " + field[7] + " | " + field[8] + " |");
    }

    protected boolean isFieldNotEmpty(char[] field) {
        for (char cell : field) {
            if (' ' != cell) {
                return true;
            }
        }
        return false;
    }

    protected boolean isEndGame(int cellNumber, char[] field) {
        return isFieldOccupied(field) || isAnyPlayerWin(cellNumber, field);
    }

    protected boolean isAnyPlayerWin(int cellNumber, char[] field) {
        if (cellNumber % 2 == 0) {
            switch (cellNumber) {
                case 2:
                    return checkTheFirstRow(field) || checkTheSecondColumn(field);
                case 4:
                    return checkTheSecondRow(field) || checkTheFirstColumn(field);
                case 6:
                    return checkTheSecondRow(field) || checkTheThirdColumn(field);
                default: //case 8:
                    return checkTheThirdRow(field) || checkTheSecondColumn(field);
            }
        } else if (cellNumber == 5) {
            return checkTheSecondRow(field) || checkTheSecondColumn(field) || checkBackslash(field) || checkSlash(field);
        } else {
            switch (cellNumber) {
                case 1:
                    return checkTheFirstRow(field) || checkTheFirstColumn(field) || checkBackslash(field);
                case 3:
                    return checkTheFirstRow(field) || checkTheThirdColumn(field) || checkSlash(field);
                case 7:
                    return checkTheThirdRow(field) || checkTheFirstColumn(field) || checkSlash(field);
                default:  //case 9:
                    return checkTheThirdRow(field) || checkTheThirdColumn(field) || checkBackslash(field);
            }
        }
    }

    protected boolean isFieldOccupied(char[] field) {
        for (char cell : field) {
            if (' ' == cell) {
                return false;
            }
        }
        return true;
    }

    private boolean checkTheFirstRow(char[] field) {
        return (field[1] == field[0] && field[1] == field[2]);
    }

    private boolean checkTheSecondRow(char[] field) {
        return (field[4] == field[5] && field[4] == field[3]);
    }

    private boolean checkTheThirdRow(char[] field) {
        return (field[7] == field[6] && field[7] == field[8]);
    }

    private boolean checkTheFirstColumn(char[] field) {
        return (field[3] == field[0] && field[3] == field[6]);
    }

    private boolean checkTheSecondColumn(char[] field) {
        return (field[4] == field[1] && field[4] == field[7]);
    }

    private boolean checkTheThirdColumn(char[] field) {
        return (field[5] == field[2] && field[5] == field[8]);
    }

    private boolean checkBackslash(char[] field) {
        return (field[4] == field[0] && field[4] == field[8]);
    }

    private boolean checkSlash(char[] field) {
        return (field[4] == field[2] && field[4] == field[6]);
    }
}
