/**
 * Driver program for the Train Consist Management App.
 *
 * UC1: Initialize an empty consist and display summary.
 * Later use cases extend this to a menu-driven console application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        TrainConsist consist = new TrainConsist();
        System.out.println("UC1 - Initialize Consist");
        System.out.println(consist.summary());
    }
}

