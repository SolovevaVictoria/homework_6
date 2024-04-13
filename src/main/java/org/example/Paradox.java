package org.example;

import java.util.Random;

public class Paradox {
        private static final int COUNT_DOORS = 3;
        private static final int SUM_DOORS_NUMBERS = 6;
        private final Random random;
        private static int carDoor;


        public Paradox() {
            this.random = new Random();
            carDoor = random.nextInt(1, COUNT_DOORS + 1);
        }

        public int chooseDoor() {
            return random.nextInt(1, COUNT_DOORS + 1);
        }

        public int openDoorLead(int selectedDoor) {
            int openedDoor;
            do {
                openedDoor = chooseDoor();
            } while (openedDoor == selectedDoor || openedDoor == carDoor);
            return openedDoor;
        }

        public int changeDoor(int selectedDoor, int openedDoor) {
            return SUM_DOORS_NUMBERS - selectedDoor - openedDoor;
        }

        public void startGame(int countGames) {
            int constantWins = 0;
            int changeWins = 0;
            int selectedDoor;
            int openedDoor;
            int changedDoor;

            for (int i = 0; i < countGames; i++) {
                selectedDoor = chooseDoor();
                openedDoor = openDoorLead(selectedDoor);
                changedDoor = changeDoor(selectedDoor, openedDoor);

                if (selectedDoor == carDoor) {
                    constantWins++;
                } else if (changedDoor == carDoor) {
                    changeWins++;
                }
            }

            double constantWinsPercent = constantWins * 100. / countGames;
            double changeWinsPercent = changeWins * 100. / countGames;

            System.out.printf("""
                Game's number: %d
                Person did not change his choice: number of wins = %d; percent of game's number = (%.2f)
                Person changed his choice: number of wins = %d; percent of game's number = (%.2f)
                """, countGames, constantWins, constantWinsPercent, changeWins, changeWinsPercent);

            Main.latch.countDown();
        }
    }


