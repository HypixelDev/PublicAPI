package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetSkyBlockAuctionsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockAuctions(0).whenComplete((page0, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                System.exit(0);
                return;
            }

            System.out.println(page0);
            if (page0.hasNextPage()) {
                ExampleUtil.API.getSkyBlockAuctions(page0.getPage() + 1).whenComplete(ExampleUtil.getTestConsumer());
            } else {
                System.exit(0);
            }
        });
        ExampleUtil.await();
    }
}
