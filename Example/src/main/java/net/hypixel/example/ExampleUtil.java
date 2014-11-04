package net.hypixel.example;

public class ExampleUtil {
    /**
     * Keep the program alive till we explicitly exit.
     */
    static void await() {
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
