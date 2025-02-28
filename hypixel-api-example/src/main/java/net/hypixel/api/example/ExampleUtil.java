package net.hypixel.api.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.config.ConfigManager;
import net.hypixel.api.reply.AbstractReply;

import java.util.Properties;
import java.util.UUID;
import java.util.function.BiConsumer;

public class ExampleUtil {

    private static final Properties config = ConfigManager.getInstance().getConfig();

    private static String getApiKey() {
        return config.getProperty("HYPIXEL_API_KEY", "64bd424e-ccb0-42ed-8b66-6e42a135afb4");
    }

    public static final HypixelAPI API;

    static {
        API = new HypixelAPI(new ApacheHttpClient(UUID.fromString(getApiKey())));
    }

    public static final UUID HYPIXEL = UUID.fromString(config.getProperty("HYPIXEL", "f7c77d99-9f15-4a66-a87d-c4a51ef30d19"));
    public static final String GUILD_ID = config.getProperty("GUILD_ID", "53bd67d7ed503e868873eceb");

    /**
     * Keep the program alive till we explicitly exit.
     */
    public static void await() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T extends AbstractReply> BiConsumer<T, Throwable> getTestConsumer() {
        return (result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                System.exit(0);
                return;
            }

            System.out.println(result);

            System.exit(0);
        };
    }
}
