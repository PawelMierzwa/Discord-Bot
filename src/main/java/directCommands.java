import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class directCommands extends ListenerAdapter {
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (event.getAuthor().isBot()){
            return;
        }
        if (args[0].equalsIgnoreCase(Main.prefix + "help")){
            event.getAuthor().openPrivateChannel().queue();
            EmbedBuilder pmEmbed = new EmbedBuilder();
            pmEmbed.setTitle("Commands list");
            pmEmbed.setDescription("Current prefix is **" + Main.prefix + "**");
            pmEmbed.setColor(Color.pink);
            pmEmbed.addField("Ping", "Pong!", true);
            pmEmbed.addField("Random", "Sends random number", true);
            pmEmbed.addField("User", "Shows provided users information", true);
            pmEmbed.addField("ServerInfo", "Provides current server information", true);
            pmEmbed.addField("Speech", "Makes a randomized speech for you.", true);
            pmEmbed.addField("Setprefix", "Changes prefix to provided character", true);
            pmEmbed.addField("Coinflip", "flips a coin", true);
            pmEmbed.addField("Invite", "Sends a bot invite link", true);
            pmEmbed.addField("RandomEmoji", "Sends random emoji.", true);
            pmEmbed.addField("Doge", "Sends random doge image.", true);
            event.getChannel().sendMessage(pmEmbed.build()).queue();
        }
    }
}
