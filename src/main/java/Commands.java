import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Main.prefix + "ping")) {
            System.out.println("!ping used in " + event.getGuild().getName());
            event.getChannel().sendTyping().queue();
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!")
                    .queue(response -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        }

        if (args.length == 1 && args[0].equalsIgnoreCase(Main.prefix + "random")) {
            System.out.println("!random used in " + event.getGuild().getName());
            event.getChannel().sendMessage("To get your random number type !random [max number]").queue();
        } else if (args.length == 2 && args[0].equalsIgnoreCase(Main.prefix + "random")) {
            System.out.println("!random [max number] used in " + event.getGuild().getName());
            String maxNumber = args[1];
            Random rand = new Random();
            int random = rand.nextInt(Integer.parseInt(maxNumber)) + 1;
            event.getChannel().sendMessage("Your random number is: " + random).queue();
            event.getChannel().sendTyping().queue();
        }

        //uniwersalny kod przemowien https://i.sadistic.pl/pics/9375f95d4fd1.jpg
        Random przemowienieRand = new Random();
        String[] tabela1 = {"Kolezanki i Koledzy ", "Z drugiej strony ", "podobnie ",
                " Nie zapomnijmy jednak, ze ", "W ten sposob ", "Praktyka dnia codziennego dowodzi, ze ",
                "Wagi i znaczenia trzeba szerzej uzasadniac, poniewaz ", "Roznorakie i bogate doswiadczenia ",
                "Troska organizacji, a szczegolnie ", "Wyzsze zalozenia ideowe, a takze "};
        String[] tabela2 = {"realizacja nakreslonych zadan programowych ", "zakres i miejsce szkolenia kadr ",
                "staly wzrost ilosci i zakres naszej aktywnosci ", "aktualna struktura organizacji ", "nowy model dzialalnosci organizacyjnej ",
                "dalszy rozwoj roznych form dzialalnosci ", "stale zabezpieczenie informacyjno - propagandowe naszej dzialalnosci ",
                "wzmocnienie i rozwijanie struktur ", "konsultacja z szerokim aktywem ", "rozpoczecie powszechnej akcji ksztaltowania postaw "};
        String[] tabela3 = {"zmusza nas do przeanalizowania ", "spelnia istotna role w ksztaltowaniu ", "wymaga sprecyzowania i okreslenia ",
                "pomaga w przygotowaniu i realizacji ", "zabezpiecza udzial szerokiej grupie w ksztaltowaniu ", "spelnia wazne zadania w wypracowaniu ",
                "umozliwia w wiekszym stopniu tworzenie ", "powoduje docenianie wagi ", "przedstawia interesujaca probe sprawdzenia ", "pociaga za soba proces wdrazania i unowoczesniania "};
        String[] tabela4 = {"istniejacych warunkow administracyjno-finansowych.", "dalszych kierunkow rozwoju.", "systemu powszechnego uczestnictwa.",
                "postaw uczestnikow wobec zadan stawianych przez organizacje.", "nowych propozycji.", "kierunkow postepowego wychowania.", "systemu szkolenia kadry odpowiadajacego potrzebom.",
                "odpowiednich warunkow aktywizacji.", "modelu rozwoju.", "form oddzialywania."};

        if (args[0].equalsIgnoreCase(Main.prefix + "speech")) {
            System.out.println("!speech used in " + event.getGuild().getName());
            int speechRand1 = przemowienieRand.nextInt(9);
            int speechRand2 = przemowienieRand.nextInt(9);
            int speechRand3 = przemowienieRand.nextInt(9);
            int speechRand4 = przemowienieRand.nextInt(9);
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Your speech is:\n" + tabela1[speechRand1] + tabela2[speechRand2] + tabela3[speechRand3] + tabela4[speechRand4]).queue();
        }

        if (args[0].equalsIgnoreCase(Main.prefix + "coinflip")) {
            System.out.println("!coinflip used in " + event.getGuild().getName());
            Random coinRand = new Random();
            int coinflip = coinRand.nextInt(9) + 1;
            if (coinflip >= 5) {
                event.getChannel().sendMessage("Heads").queue();
            } else {
                event.getChannel().sendMessage("Tails").queue();
            }
        }



        if (args.length == 1 && args[0].equalsIgnoreCase(Main.prefix + "setprefix")) {
            System.out.println("!setprefix used in " + event.getGuild().getName());
            event.getChannel().sendMessage("To change prefix type !setprefix [prefix]").queue();
        } else if (args.length == 2 && args[0].equalsIgnoreCase(Main.prefix + "setprefix")) {
            System.out.println("!setprefix [prefix] used in " + event.getGuild().getName());
            System.out.println("!setprefix used in " + event.getGuild().getName());
            Main.prefix = args[1].charAt(0);
            event.getChannel().sendMessage("prefix has been set to " + Main.prefix).queue();
        }

        Random randEmojiArray = new Random();
        String[] emojiArray = {":sunglasses:", ":flushed:", ":heart:", ":eyes:", ":slight_smile:", ":woozy_face:", ":cold_face:", ":ok_hand:", ":cowboy:", ":nerd:", ":blush:", ":disguised_face:", ":+1:", ":frog:", ":smiling_face_with_tear:"};

        if (args[0].equalsIgnoreCase(Main.prefix + "randomemoji")){
            int randEmoji = randEmojiArray.nextInt(14);
            System.out.println("!randomemoji used in " + event.getGuild().getName());
            event.getChannel().sendMessage(emojiArray[randEmoji]).queue();
        }

        if (args[0].equalsIgnoreCase(Main.prefix + "invite")){
            System.out.println("!invite used in " + event.getGuild().getName() + ", " + event.getChannel().getName());
            event.getChannel().sendMessage("https://discord.com/api/oauth2/authorize?client_id=821068960346669076&permissions=8&scope=bot").queue();
        }
    }
}
