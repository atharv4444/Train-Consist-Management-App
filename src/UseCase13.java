import java.util.*;
import java.util.stream.*;

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}

public class UseCase13 {
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("UC13 - Performance Comparison (Loop vs Stream)");
        System.out.println("====================================\n");

        // Step 1: Create dataset (large for realistic benchmarking)
        List<Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            bogies.add(new Bogie("Bogie" + i, i % 100));
        }

        // =========================
        // LOOP-BASED FILTERING
        // =========================
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // =========================
        // STREAM-BASED FILTERING
        // =========================
        long startStream = System.nanoTime();

        List<Bogie> streamResult = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // =========================
        // RESULTS
        // =========================
        System.out.println("Loop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());

        System.out.println("\nExecution Time:");
        System.out.println("Loop Time   : " + loopTime + " ns");
        System.out.println("Stream Time : " + streamTime + " ns");

        // Comparison
        if (loopTime < streamTime) {
            System.out.println("\nLoop is faster.");
        } else if (streamTime < loopTime) {
            System.out.println("\nStream is faster.");
        } else {
            System.out.println("\nBoth have similar performance.");
        }

        System.out.println("\nUC13 benchmarking completed...");
    }
}