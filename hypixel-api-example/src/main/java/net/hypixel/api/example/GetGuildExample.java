package net.hypixel.api.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.data.type.GuildAchievement;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.reply.GuildReply.Guild;
import net.hypixel.api.reply.GuildReply.Guild.Member;
import net.hypixel.api.reply.GuildReply.Guild.Rank;
import net.hypixel.api.util.IGuildLeveling;

/**
 * A sample app for demonstrating how guilds can be fetched & used from the Hypixel API.
 */
public class GetGuildExample {

    public static void main(String[] args) {
        /*
         * Make sure you have a HypixelAPI object set up. You can see how this is done by going to
         * the ExampleUtil class.
         *
         * See the finally{} block below for how to shutdown this API once you're all done.
         */
        HypixelAPI api = ExampleUtil.API;

        /*
         * Skip below the try/catch/finally block to see how this is used.
         */
        GuildReply apiReply;

        try {
            /*
             * We'll be fetching the guild's stats using its ID for this example, but guilds can
             * also be looked up using their name, or one of their members' Minecraft UUIDs.
             *  - HypixelAPI.getGuildByName(String)
             *  - HypixelAPI.getGuildByPlayer(UUID)
             */
            String guildId = ExampleUtil.GUILD_ID;

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
            apiReply = api.getGuildById(guildId).get();

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
            System.err.println("Oh no, the guild fetch thread was interrupted!");
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
         * Now that we have the guild, we can start to read its information and stats!
         */
        Guild guild = apiReply.getGuild();

        // ===================================================
        // Check out the methods referenced below to see how
        // each type of stat is retrieved! (their code can be
        // found further down in this file)
        // ===================================================

        /*
         * First we'll display some basic information about the guild, like its name, tag,
         * description, and level.
         */
        printGuildSummary(guild);
        System.out.println();

        /*
         * After that, we'll print the guild's high-score for each tiered guild achievement.
         */
        printAchievementScores(guild);
        System.out.println();

        /*
         * Then we'll display how much experience the guild has earned from each game on Hypixel,
         * as well as an overall total.
         */
        printGuildXpPerGame(guild);
        System.out.println();

        /*
         * Next, we'll print some info about each of the guild's ranks, if it has any.
         */
        List<Rank> ranks = guild.getRanks();
        if (!ranks.isEmpty()) {
            printGuildRanks(ranks);
            System.out.println();
        }

        /*
         * Finally, we'll print some information about each member in the guild.
         *
         * This might print out A LOT, so you may want to comment the following line out if you're
         * focusing on some of the guild's other info.
         */
        printGuildMembers(guild.getMembers());
    }

    private static void printGuildSummary(Guild guild) {
        /*
         * First, we'll print the guild's name. If it also has a tag, we'll print that on the same
         * line.
         */
        System.out.print(guild.getName());
        if (guild.getTag() != null) {
            System.out.print(" [" + guild.getTag() + "]");
        }
        System.out.println();

        int guildLevel = (int) IGuildLeveling.getLevel(guild.getExperience());

        System.out.println("\tID:          " + guild.getId());
        System.out.println("\tLevel:       " + guildLevel);
        System.out.println("\tCreated At:  " + guild.getCreationDate());
        System.out.println("\tDescription: \"" + guild.getDescription() + '"');
        System.out.println("\tGames:       " + guild.getPreferredGames());
        System.out.println("\tBanner:      " + guild.getBanner());
    }

    private static void printAchievementScores(Guild guild) {
        System.out.println("Guild Achievement High-Scores");

        /*
         * Displays the guild's high-score for each tiered achievement. The meaning of "score"
         * varies between achievements, but an explanation for each can be found in the
         * `GuildAchievement` class.
         */
        for (GuildAchievement achievement : GuildAchievement.values()) {
            int highScore = guild.getAchievementHighScore(achievement);
            System.out.println("\t" + achievement + ": " + highScore);
        }
    }

    private static void printGuildXpPerGame(Guild guild) {
        System.out.println("Guild XP Breakdown");

        /*
         * This line prints the guild's total experience from all games.
         */
        System.out.println("\tOVERALL: " + guild.getExperience());

        /*
         * Then we loop through each game and see how much experience the guild's earned from it.
         */
        for (GameType game : GameType.values()) {
            long experienceForGame = guild.getExperienceForGame(game);
            System.out.println("\t" + game.getName() + ": " + experienceForGame);
        }
    }

    private static void printGuildRanks(List<Rank> ranks) {
        System.out.println("Ranks (" + ranks.size() + " total)");

        /*
         * This just sorts the list in reverse order by priority, so that higher-level ranks (like
         * officer) are printed before lower-level ones (like member).
         *
         * The first line copies the list beforehand, because `Guild.getRanks()` returns an
         * immutable (unmodifiable) list that can't be altered (including sorting).
         */
        ranks = new ArrayList<>(ranks);
        ranks.sort(Comparator.comparingInt(Rank::getPriority).reversed());

        for (Rank rank : ranks) {
            /*
             * Here we print the rank's name. If it also has a chat tag, we print that on the
             * same line.
             */
            System.out.print("\t" + rank.getName());
            if (rank.getChatTag() != null) {
                System.out.print(" [" + rank.getChatTag() + "]");
            }
            System.out.println();

            System.out.println("\t\tPriority:  " + rank.getPriority());
            System.out.println("\t\tCreated at " + rank.getCreationDate());
        }
    }

    private static void printGuildMembers(List<Member> members) {
        System.out.println("Members (" + members.size() + " total)");

        for (Member member : members) {
            /*
             * First we'll print some basic information about each member.
             *
             * Notice how we only have the member's UUID. If you want to get their name, you'll have
             * to make a separate request to the API using this UUID. See the GetPlayerExample class
             * for an example of that.
             */
            System.out.println("\tUUID: " + member.getUuid());
            System.out.println("\tRank: " + member.getRank());
            System.out.println("\tJoin date: " + member.getJoinDate());

            /*
             * Then, we'll print how much guild experience they earned for the last 7 days. We'll
             * sum up these daily totals to print an overall (weekly) total at the end.
             *
             * The code for the `getWeekDates()` function can be found at the bottom of the class.
             */
            int weeklyExp = 0;
            System.out.println("\tWeekly Exp: ");
            for (LocalDate date : getWeekDates()) {
                int dailyExp = member.getExperienceEarned(date);
                weeklyExp += dailyExp;

                // "<date>: <xp amount>"
                System.out.println("\t\t" + date + ": " + dailyExp);
            }
            System.out.println("\t\tWeekly Total: " + weeklyExp);
            System.out.println();
        }
    }

    /*
     * Returns an array containing the date for each of the past 7 days (including today).
     * We use this to get each member's weekly experience by day (see `main` method above).
     */
    private static LocalDate[] getWeekDates() {
        LocalDate[] week = new LocalDate[7];
        for (int i = 0; i < 7; i++) {
            week[i] = LocalDate.now().minusDays(i);
        }
        return week;
    }
}
