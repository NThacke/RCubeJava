import model.Model;
import model.State;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Model m = new Model();
        Thread t = new Thread(m);
        t.start();

        // Schedule a TimerTask to run every 30 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("30 seconds have passed");
                System.out.println(m.count() + " states visited");
            }
        }, 0, 30 * 1000); // 30 seconds in milliseconds

        try {
            t.join();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(m.time() + " ms");
        System.out.println(m.start());
        System.out.println(m.finish());

    }
}