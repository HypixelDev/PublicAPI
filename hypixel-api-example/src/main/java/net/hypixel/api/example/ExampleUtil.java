package net.hypixel.api.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.config.ConfigManager;
import net.hypixel.api.reply.AbstractReply;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ExampleUtil {

    public static final HypixelAPI API;
    private static final ConfigManager config = ConfigManager.getInstance();
    public static final UUID HYPIXEL = UUID.fromString(config.get("HYPIXEL"));
    public static final String GUILD_ID = config.get("GUILD_ID");

    static {
        API = new HypixelAPI(new ApacheHttpClient(UUID.fromString(getApiKey())));
    }

    private static String getApiKey() {
        return config.get("HYPIXEL_API_KEY");
    }

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
