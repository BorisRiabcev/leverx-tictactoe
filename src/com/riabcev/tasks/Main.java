package com.riabcev.tasks;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (Game.chooseGameMod(scanner)) {
                GameAgainstHuman gameAgainstHuman = new GameAgainstHuman();
                gameAgainstHuman.playGame(scanner);
            } else {
                GameAgainstComputer gameAgainstComputer = new GameAgainstComputer();
                gameAgainstComputer.playGame(scanner);
            }
        }
    }
}
