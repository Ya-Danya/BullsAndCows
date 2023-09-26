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
        List<int[]> compares = new ArrayList<int[]>();
        int[] bulls = {-1, -1, -1, -1};
        int bulls_amount = 0;
        int[] cows = {-1, -1, -1, -1};
        int cows_amount = 0;

        int[] ans = {-1, -1, -1, -1};
        // Первая попытка угадывания.
        tries.add(new int[4]);
        tries.set(0, new int[]{0, 1, 2, 3});
        compares.add(new int[4]);
        compares.set(0, compareArrs(secret, tries.get(0)));
        for (int i = 0; i < 4; i++) {
            if (tries.get(0)[i] == 2) {
                ans[i] = compares.get(0)[i];
            }
        }
        // Вторая попытка угадывания.
        tries.add(new int[4]);
        tries.set(1, new int[]{4, 5, 6, 7});
        for (int i = 0; i < 4; i++) {
            if (tries.get(0)[i] == 2) {
                ans[i] = compares.get(0)[i];
            }
        }
        compareArrs(secret, tries.get(1));
        compares.add(new int[4]);
        compares.set(0, compareArrs(secret, tries.get(1)));
        // Третяя попытка угадывания.
        if (cows_amount == 4) {

        } else if (cows_amount == 3) {

        } else {

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

        int[] ans = compareArrs(secret, input);

        printArr(ans);

        

    }
}