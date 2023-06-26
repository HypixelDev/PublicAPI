package net.hypixel.api.reply;

public class FindGuildReply extends RateLimitedReply {
    private String guild;

    /**
     * @return The ID of the guild that was found, or null if there was no guild by that name
     */
    public String getGuild() {
        return guild;
    }

    @Override
    public String toString() {
        return "FindGuildReply{" +
                "guild='" + guild + '\'' +
                "} " + super.toString();
    }
}
