import model.Model;
import model.State;
import model.Util;

import java.util.*;

public class Main {

    private static String formatElapsedTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = milliseconds / (1000 * 60 * 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {

        Model m = new Model();
        Thread t = new Thread(m);
        t.start();

        // Schedule a TimerTask to run every 30 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int time = 0;
            public void run() {
                System.out.println("Total time elapsed: " + formatElapsedTime(time));
                time += 1000; // Increment elapsed time by 1 second
                System.out.println(m.count() + " states visited");
            }
        }, 0, 30 * 1000); // 30 seconds in milliseconds

        try {
            t.join();
            timer.cancel();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(m.time() + " ms");
        System.out.println(m.start());
        System.out.println(m.finish());

        for(Util.Move move : m.moves()) {
            System.out.println(move);
        }

    }
}