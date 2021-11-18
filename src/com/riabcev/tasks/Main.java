package com.riabcev.tasks;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        if (game.chooseGameMod(scanner)) {
            game.playGameWithFriend(scanner);
        } else {
            game.playGameWithComputer(scanner);
        }
        scanner.close();
    }
}
