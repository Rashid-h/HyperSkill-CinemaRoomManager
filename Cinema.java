package cinema;

import java.util.Scanner;

public class Cinema {
    static int rows;
    static int seats;
    static char[][] cinema;
    static boolean exit = false;
    static int buyATicket = 0;
    static int totalIncome = 0;
    static int currentIncome = 0;
    static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BuildCinema();
        while (!exit) {
            System.out.println("1. Show the seats\n" +
                               "2. Buy a ticket\n" +
                               "3. Statistics\n" +
                               "0. Exit");
            int num = sc.nextInt();
            switch (num) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    ShowCinema();
                    break;
                case 2:
                    TicketPrice();
                    break;
                case 3:
                    Statistics();
                    break;

            }
        }
        }

    static void BuildCinema() {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            int front = (rows / 2) * seats * 10;
            int back = (rows / 2 + 1) * seats * 8;
            totalIncome = front + back;
        }

    }

    static void TicketPrice () {
    int price;
    System.out.println("Enter a row number:");
    int bRow = sc.nextInt();
    System.out.println("Enter a seat number in that row:");
    int bSeat = sc.nextInt();
    if (bRow - 1 >= rows || bSeat - 1 >= seats){
        System.out.println("Wrong input!");
        TicketPrice();
    } else if (cinema[bRow - 1][bSeat - 1] == 'B') {
        System.out.println("That ticket has already been purchased!");
        TicketPrice();
    } else {
        if (rows * seats <= 60 || bRow <= rows / 2) {
            price = 10;
        } else {
            price = 8;
        }
        System.out.println("Ticket price: $" + price);
        cinema[bRow - 1][bSeat - 1] = 'B';
        buyATicket++;
        currentIncome += price;
    }
}

    static void ShowCinema() {
        int number = 1;
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < seats; i++) {
            System.out.print(number + " ");
            number++;
        }
        System.out.println();
        number = 1;
        for (int i = 0; i < rows; i++) {
            System.out.print(number + " ");
            number++;
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void Statistics() {
        double a = buyATicket;
        double b = rows * seats;
        double percentage = a / b * 100;
        System.out.printf("Number of purchased tickets: %d\n" +
                "Percentage: %.2f" + "%c\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n", buyATicket, percentage, '%', currentIncome, totalIncome);
    }
}
