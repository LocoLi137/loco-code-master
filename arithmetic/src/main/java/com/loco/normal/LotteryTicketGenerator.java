package com.loco.normal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description:
 * author: Loco.Li
 */

public class LotteryTicketGenerator {
    private static final int TICKETS_COUNT = 100000;
    private static final int GROUPS_PER_TICKET = 10;
    private static final int NUMBERS_PER_GROUP = 2;
    private static final String FILE_NAME = "lottery_tickets.txt";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < TICKETS_COUNT; i++) {
            tasks.add(new LotteryTicketTask(i));
        }

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt status
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        System.out.println("Lottery tickets generation completed.");
    }

    static class LotteryTicketTask implements Callable<Void> {
        private final int ticketNumber;

        LotteryTicketTask(int ticketNumber) {
            this.ticketNumber = ticketNumber;
        }

        @Override
        public Void call() throws Exception {
            StringBuilder ticket = new StringBuilder("Ticket " + ticketNumber + ": ");
            Random random = new Random();
            for (int i = 0; i < GROUPS_PER_TICKET; i++) {
                List<Integer> group = new ArrayList<>();
                HashSet<Integer> usedNumbers = new HashSet<>();
                int numberRepeats = random.nextInt(3); // 0, 1, or 2 repeats

                for (int j = 0; j < NUMBERS_PER_GROUP; j++) {
                    int number;
                    // Ensure we have at most 'numberRepeats' repeated numbers
                    if (j < numberRepeats && usedNumbers.size() > 0) {
                        number = group.get(0); // Reusing the first number
                    } else {
                        number = 10 + random.nextInt(90); // Generate a number between 10 and 99
                        usedNumbers.add(number);
                    }
                    group.add(number);
                }
                ticket.append(String.format("%02d%02d ", group.get(0), group.get(1)));
            }
            writeToFile(ticket.toString().trim());
            return null;
        }

        private synchronized void writeToFile(String ticket) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(ticket);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}