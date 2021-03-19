import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Embed extends ListenerAdapter {


    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy/ HH:mm:ss");
        SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(Main.prefix + "help")) {
            System.out.println("!help used in " + e.getGuild().getName() + ", " + e.getChannel().getName());

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Commands list");
            eb.setDescription("Current prefix is **" + Main.prefix + "**");
            eb.setColor(Color.pink);
            eb.addField("Ping", "Pong!", true);
            eb.addField("Random", "Sends random number", true);
            eb.addField("User", "Shows provided users information", true);
            eb.addField("ServerInfo", "Provides current server information", true);
            eb.addField("Speech", "Makes a randomized speech for you.", true);
            eb.addField("Setprefix", "Changes prefix to provided character", true);
            eb.addField("Coinflip", "flips a coin", true);
            eb.addField("Invite", "Sends a bot invite link", true);
            eb.addField("RandomEmoji", "Sends random emoji.", true);
            eb.addField("Doge", "Sends random doge image.", true);
            e.getChannel().sendMessage(eb.build()).queue();
        }

        try {
            if (args.length == 1 && args[0].equalsIgnoreCase(Main.prefix + "user")) {
                System.out.println("!user used in " + e.getGuild().getName() + ", " + e.getChannel().getName());
                e.getChannel().sendMessage("To get user's info type !user [name]").queue();
            } else if (args.length == 2 && args[0].equalsIgnoreCase(Main.prefix + "user")) {

                System.out.println("!user [username] used in " + e.getGuild().getName() + ", " + e.getChannel().getName());
                String userName = args[1];
                User user = e.getGuild().getMembersByName(userName, true).get(0).getUser();
                EmbedBuilder userEmbed = new EmbedBuilder();
                userEmbed.setTitle(userName + "'s Information");
                userEmbed.setColor(Color.CYAN);
                userEmbed.addField("Name", user.getName(), false);
                try {
                    userEmbed.addField("Nickname: ", e.getGuild().getMembersByName(userName, true).get(0).getNickname(), false);
                } catch (IllegalArgumentException exception) {
                    userEmbed.addField("Nickname: ", "None", false);
                }
                userEmbed.addField("Online status: ", e.getGuild().getMembersByName(userName, true).get(0).getOnlineStatus().toString(), false);
                userEmbed.addField("Time joined: ", user.getTimeCreated().format(formatter), false);
                userEmbed.setThumbnail(user.getAvatarUrl());
                userEmbed.setFooter(sformat.format(date));
                e.getChannel().sendMessage(userEmbed.build()).queue();
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("indexoutofbounds exception in " + e.getGuild().getName() + ", " + e.getChannel().getName());
            e.getChannel().sendMessage("Error, please try again later").queue();
        }

        if (args[0].equalsIgnoreCase(Main.prefix + "serverinfo")) {
            System.out.println("!server used in " + e.getGuild().getName() + ", " + e.getChannel().getName());
            EmbedBuilder servEmbed = new EmbedBuilder();
            servEmbed.setColor(Color.GREEN);
            servEmbed.setTitle("Current server information");
            servEmbed.setThumbnail(e.getGuild().getIconUrl());
            servEmbed.addField("Name", "**" + e.getGuild().getName() + "**", false);
//            servEmbed.addField("Online members", String.valueOf(e.getGuild().getMembers().stream().getOnlineStatus() == OnlineStatus.ONLINE), false);
            String ownerName = Objects.requireNonNull(Objects.requireNonNull(e.getGuild().getOwner()).getEffectiveName());
            servEmbed.addField("Owner", "**" + ownerName + "**", false);
            servEmbed.addField("Created on", "**" + e.getGuild().getTimeCreated().format(formatter) + "**", false);
            servEmbed.addField("Members count", "**" + e.getGuild().getMemberCount() + "**", true);
            servEmbed.addField("Booster count", "**" + e.getGuild().getBoostCount() + "**", true);
            servEmbed.addField("Boost Tier", "**" + e.getGuild().getBoostTier() + "**", true);
            servEmbed.addField("Explicit content level", "**" + e.getGuild().getExplicitContentLevel() + "**", false);
            servEmbed.addField("Verification Level", "**" + e.getGuild().getVerificationLevel() + "**", false);


            servEmbed.addField("Channels", "Text channels: " + "**" + e.getGuild().getTextChannels().size() + "**" +
                    "\nVoice channels: " + "**" + e.getGuild().getVoiceChannels().size() + "**" +
                    "\nCategories: " + "**" + e.getGuild().getCategories().size() + "**", false);

            try {
                servEmbed.addField("AFK Channel: ", e.getGuild().getAfkChannel().getName(), false);
            } catch (NullPointerException exception) {
                servEmbed.addField("AFK Channel: ", "**None**", false);
            }

            servEmbed.addField("Emotes", "**" + e.getGuild().getEmotes().size() + "/" + e.getGuild().getMaxEmotes() + "**", true);
            servEmbed.addField("Roles", "**" + e.getGuild().getRoles().size() + "**", true);

            servEmbed.setFooter(sformat.format(date));
            e.getChannel().sendMessage(servEmbed.build()).queue();
        }
        String[] dogeImg = {
                "https://i.pinimg.com/originals/f8/af/98/f8af98c78198528ebc79a414fae051a9.jpg",
                "https://getmemetemplates.com/wp-content/uploads/2020/06/Doggo-38.jpg",
                "https://i.kym-cdn.com/entries/icons/original/000/013/564/doge.jpg",
                "https://i.kym-cdn.com/photos/images/newsfeed/001/582/307/98c.jpg",
                "https://www.meme-arsenal.com/memes/b282bb90aa06c62a7450acaf4a182ad1.jpg",
                "https://www.meme-arsenal.com/memes/3bc173ad281f1baa9902f5c4b8de480d.jpg",
                "https://hg1.funnyjunk.com/pictures/My+additions+to+dogelore+big1improved+kid+doge+with+propeller+beaniebig1_71500b_7366988.jpg",
                "https://www.cryptonewsz.com/wp-content/uploads/2019/02/Dogecoin-DOGE-Price-Analysis-Feb.06.jpg"
        };
        Random dogeRand = new Random();
        Color dogeYellow = new Color(255, 204, 153);


        if (args[0].equalsIgnoreCase(Main.prefix + "doge")) {
            System.out.println("!doge used in " + e.getGuild().getName() + ", " + e.getChannel().getName());
            int dogeImgRand = dogeRand.nextInt(7);
            EmbedBuilder dogeEmbed = new EmbedBuilder();
            dogeEmbed.setColor(dogeYellow);
            dogeEmbed.setImage(dogeImg[dogeImgRand]);
            dogeEmbed.setFooter(sformat.format(date));
            e.getChannel().sendMessage(dogeEmbed.build()).queue();
        }
    }
}
