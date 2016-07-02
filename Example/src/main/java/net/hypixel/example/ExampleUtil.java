package net.hypixel.example;

import java.util.UUID;

public class ExampleUtil {

    public static final UUID API_KEY = UUID.fromString("64bd424e-ccb0-42ed-8b66-6e42a135afb4"); // arbitrary key, replace with your own to test

    /**
     * Keep the program alive till we explicitly exit.
     */
    static void await() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
