package net.hypixel.api.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.reply.PlayerReply.Player;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * A sample app for demonstrating how players can be fetched & used from the Hypixel API.
 */
public class GetPlayerExample {

    public static void main(String[] args) {
        /*
         * Make sure you have a HypixelAPI object set up. You can see how this is done by going to
         * the ExampleUtil class.
         *
         * See the finally{} block below for how to shutdown this API once you're all done.
         */
        HypixelAPI api = ExampleUtil.API;

        /*
         * Skip to below the try/catch/finally block to see how this is used.
         */
        PlayerReply apiReply;

        try {
            UUID playerUuid = ExampleUtil.HYPIXEL;
            /*
             * Here, we store the response from the API in our variable.
             *
             * We call `.get()` at the end so that we can use the reply in the same thread.
             * The downside is that the current thread freezes (or "blocks") until the API responds.
             * If this is a problem for you, instead use:
             *
             *     .whenComplete((apiReply, error) -> {
             *          // Do something with apiReply (in a different thread)...
             *     });
             *
             * But for a simple command-line app like this one, `.get()` will do the job.
             */
            apiReply = api.getPlayerByUuid(playerUuid).get();

        } catch (ExecutionException e) {
            System.err.println("Oh no, our API request failed!");

            /*
             * If an ExecutionException is thrown, it's typically because of an API error.
             * Use `getCause()` to determine what the actual problem is.
             */
            e.getCause().printStackTrace();
            return;

        } catch (InterruptedException e) {
            // Shouldn't happen under normal circumstances.
            System.err.println("Oh no, the player fetch thread was interrupted!");
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return;

        } finally {
            /*
             * Once you're finished with all your requests, you can shutdown your HypixelAPI object.
             *
             * If your app is meant to run continuously, you probably don't want to do this until
             * the app is stopped/closed. For this example though, we only need the one request.
             */
            api.shutdown();
        }

        /*
         * Now that we have the player, we can start to read their stats! (if they actually exist)
         */
        Player player = apiReply.getPlayer();
        if (!player.exists()) {
            System.err.println("Player not found!");

            api.shutdown();
            return;
        }

        /*
         * The player class has some built-in getters, like for the player's name, rank, and UUID.
         */
        System.out.println("Here are some of \"" + player.getName() + "\"'s stats!");
        System.out.println();
        System.out.println("UUID ----------> " + player.getUuid());
        System.out.println("Rank ----------> " + player.getHighestRank());
        System.out.println("On Build Team? > " + player.isOnBuildTeam());
        System.out.println("Exact Level ---> " + player.getNetworkLevel());
        System.out.println("Experience ----> " + player.getNetworkExp());
        System.out.println("Karma ---------> " + player.getKarma());
        System.out.println("Last Game Type > " + player.getMostRecentGameType());

        /*
         * If you want to find a stat that doesn't have a built-in method, you can use the
         * `getProperty(<propertyName>)` method.
         *
         * If you also know what type of property it is (like a string or number), you can use more
         * specific methods, like `getStringProperty(...)`, `getIntProperty(...)`, and so on.
         */
        System.out.println("Language ------> " + player.getStringProperty("userLanguage", null));

        /*
         * Some of the property methods also accept a default value, which gets returned if the
         * field does not exist for the player. In this case, we return `0` as the default number of
         * deaths.
         *
         * If a stat is a bit deeper in the player object, you can separate each layer of the path
         * using dots, like below.
         *   - In our case, it's the equivalent of getting the player's "stats" object, then the
         *     "SkyWars" object inside that, and finally the "deaths" stat inside that.
         */
        System.out.println("SkyWars Deaths > " + player.getIntProperty("stats.SkyWars.deaths", 0));

        /*
         * If you need the entire player JSON returned by the API, you can get it using the player's
         * `.getRaw()` method.
         */
        System.out.println("Raw JSON ------> " + player.getRaw());

        /*
         * RateLimit object is available to any reply from a request to an authenticated endpoint and returns context
         * to the rate limit of the used API key.
         */
        System.out.println("Rate Limit ----> " + apiReply.getRateLimit());
    }
}
