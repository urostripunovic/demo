package com.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        new App().run();
    }

    void run() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "Choose a what number to validate along with the number string separated by a semicolon: "
                            + "\n" + "1: For person number"
                            + "\n" + "2: For coordination number"
                            + "\n" + "3: For organization number"
                            + "\n" + "4 or ctrl+c: For exiting the program");

            String[] value = scan.nextLine().split(";");

            if (value[0].equals("4")) {
                System.out.println("Thank you, come again!");
                break;
            }

            var input = sanitizeInput(value[1]);

            switch (Integer.parseInt(value[0])) {
                case 1:
                    new ValidationLogger(new PersonNumber(input)).validate();
                    break;
                case 2:
                    new ValidationLogger(new CoordinationNumber(input)).validate();
                    break;
                case 3:
                    new ValidationLogger(new OrganizationNumber(input)).validate();
                    break;
            }
        }
        scan.close();
    }

    private String sanitizeInput(String s) {
        return s.trim();
    }
}
