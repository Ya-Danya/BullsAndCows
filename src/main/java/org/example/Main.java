package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int[] generateSecret() {
        int[] secret = {0, 0, 0, 0};
        // (secret[i] >= 0) && (secret[i] <= 9)
        for (int i = 0; i < 4; i++) {
            boolean k = false;
            int tempo;
            do {
                tempo = (int) (Math.random() * 9);
                k = false;
                for (int j = 0; j < i; j++) {
                    if (tempo == secret[j]) {
                        k = true;
                    }
                }
            } while (k);
            secret[i] = tempo;
        }
        return secret;
    }

    public static void printArr(int[] arr) {
        for (int item: arr) {
            System.out.print(item);
        }
        System.out.println();
    }

    public static int[] compareArrs(int[] secret, int[] input) {
        if (secret.length != input.length) {
            System.out.println("Введена комбинация неправильной длины.");
            throw new ArrayIndexOutOfBoundsException();
        }

        int secret_length = secret.length;
        int[] ans = {0, 0, 0, 0};
        for (int i = 0; i < secret_length; i++) {
            for (int j = 0; j < secret_length; j++) {
                if (input[i] == secret[j]) {
                    if (i == j) {
                        ans[i] = 2;
                    } else {
                        ans[i] = 1;
                    }
                    break;
                }
            }
        }
        return ans;
    }

    public static void bullsNCows (int[] secret) {
        List<int[]> tries = new ArrayList<int[]>();
        ArrayList<int[]> compares = new ArrayList<int[]>();
        int[] bulls = {-1, -1, -1, -1};
        int bulls_amount = 0;
        ArrayList<Integer>[] cows = new ArrayList[4];

        int cows_iterator = 0;

        int cows_amount = 0;

        int[] ans = {-1, -1, -1, -1};
        // Первая попытка угадывания.
        tries.add(new int[4]);
        tries.set(0, new int[]{0, 1, 2, 3});
        compares.add(new int[4]);
        compares.set(0, compareArrs(secret, tries.get(0)));
        for (int i = 0; i < 4; i++) {
            if (compares.get(0)[i] == 2) {
                ans[i] = tries.get(0)[i];
                bulls_amount++;
            } else if(compares.get(0)[i] == 1) {
                cows[cows_amount] = new ArrayList<>();
                cows[cows_amount].set(0, tries.get(0)[i]);
                cows[cows_amount].add(i);
                cows_amount++;
            }
        }
        if (bulls_amount == 4) {
            printArr(ans);
            return;
        }
        System.out.println("First combination is 0123");
        // Вторая попытка угадывания.
        tries.add(new int[4]);
        tries.set(1, new int[]{4, 5, 6, 7});
        compares.add(new int[4]);
        compares.set(1, compareArrs(secret, tries.get(1)));
        for (int i = 0; i < 4; i++) {
            if (compares.get(1)[i] == 2) {
                ans[i] = tries.get(1)[i];
                bulls_amount++;
            } else if(compares.get(1)[i] == 1) {
                cows[cows_amount] = new ArrayList<>();
                cows[cows_amount].add(tries.get(1)[i]);
                cows[cows_amount].add(i);
                cows_amount++;
            }
        }

        if (cows_amount == 2) {
            cows[cows_amount] = new ArrayList<>();
            cows[cows_amount].add(8);
            cows_amount++;
            cows[cows_amount] = new ArrayList<>();
            cows[cows_amount].add(9);
            cows_amount++;
        } else if (cows_amount == 3) {

        }


        System.out.println("Second combination is 4567");
        if (bulls_amount == 4) {
            printArr(ans);
            return;
        }
        // Третяя попытка угадывания.
        tries.add(new int[4]);

        // Формируем комбинацию.

        for (int i = 0; i < 4; i++) {
            if (i != cows[0].get(1)) {
                tries.get(2)[i] = cows[0].get(0);
                cows[0].add(i);
                cows_iterator++;
            } else {
                tries.get(2)[i] = cows[1].get(0);
                cows[1].add(i);
                cows_iterator++;
            }
        }

        compares.add(new int[4]);
        compares.set(2, compareArrs(secret, tries.get(2)));

        // Проверка на быков и коров.
        for (int i = 0; i < 4; i++) {
            if (compares.get(2)[i] == 2) {
                ans[i] = tries.get(2)[i];
                bulls_amount++;
            } else if(tries.get(2)[i] == 1) {
                cows[cows_amount] = new ArrayList<>();
                cows[cows_amount].add(tries.get(2)[i]);
                cows[cows_amount].add(i);
                cows_amount++;
            }
        }

        if (bulls_amount == 4) {
            printArr(ans);
            return;
        }

        // Четвертая попытка.
        tries.add(new int[4]);

        for (int i = 0; i < 4; i++) {
            if ((i == cows[1].get(1)) || (i == cows[1].get(2))) {
                tries.get(3)[i] = cows[2].get(0);
                cows[2].add(i);
                cows_iterator++;
            } else {
                tries.get(3)[i] = cows[1].get(0);
                cows[1].add(i);
                cows_iterator++;
            }
        }

        compares.add(new int[4]);
        compares.set(3, compareArrs(secret, tries.get(3)));

        for (int i = 0; i < 4; i++) {
            if (compares.get(3)[i] == 2) {
                ans[i] = tries.get(3)[i];
                bulls_amount++;
            } else if(tries.get(3)[i] == 1) {
                cows[cows_amount] = new ArrayList<>();
                cows[cows_amount].add(tries.get(3)[i]);
                cows[cows_amount].add(i);
                cows_amount++;
            }
        }

        if (bulls_amount == 4) {
            printArr(ans);
            return;
        }
    }

    public static int[] inputSecret(Scanner in) {
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] secret = generateSecret();
        printArr(secret);

        int[] input = inputSecret(scanner);

        bullsNCows(input);

    }
}