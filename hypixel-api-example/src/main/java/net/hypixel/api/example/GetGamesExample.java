package net.hypixel.api.example;

public class GetGamesExample {
    public static void main(String[] args) {
        ExampleUtil.API.getGames().whenComplete((games, throwable) -> System.out.println(games));
        ExampleUtil.await();
    }
}