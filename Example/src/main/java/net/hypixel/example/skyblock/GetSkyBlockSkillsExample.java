package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetSkyBlockSkillsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockSkills().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
