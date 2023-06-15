package net.hypixel.api.example;

import net.hypixel.api.reply.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestAuthenticatedEndpoints {

    @Test
    void boosters() throws ExecutionException, InterruptedException, TimeoutException {
        BoostersReply response = ExampleUtil.API.getBoosters().get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void leaderboards() throws ExecutionException, InterruptedException, TimeoutException {
        LeaderboardsReply response = ExampleUtil.API.getLeaderboards().get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void punishmentStats() throws ExecutionException, InterruptedException, TimeoutException {
        PunishmentStatsReply response = ExampleUtil.API.getPunishmentStats().get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    void player() throws ExecutionException, InterruptedException, TimeoutException {
        PlayerReply response = ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL).get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getPlayer());
        Assertions.assertNotNull(response.getPlayer().getName());
        Assertions.assertNotNull(response.getPlayer().getUuid());
    }

    @Test
    void guild() throws ExecutionException, InterruptedException, TimeoutException {
        GuildReply response = ExampleUtil.API.getGuildByPlayer(ExampleUtil.HYPIXEL).get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getGuild());
        Assertions.assertNotNull(response.getGuild().getName());
        Assertions.assertNotNull(response.getGuild().getId());
    }

    @Test
    void counts() throws ExecutionException, InterruptedException, TimeoutException {
        CountsReply response = ExampleUtil.API.getCounts().get(5, TimeUnit.SECONDS);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertTrue(response.getPlayerCount() >= 0);
        Assertions.assertFalse(response.getGames().isEmpty());
    }

}
