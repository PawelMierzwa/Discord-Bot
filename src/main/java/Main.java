import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static char prefix = '!';

    public static void main(String[] args) throws LoginException, IllegalArgumentException {
        JDA jda = JDABuilder.createDefault(Config.get("TOKEN"),
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES)
                .disableCache(CacheFlag.EMOTE,
                        CacheFlag.VOICE_STATE)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda.getPresence().setActivity(Activity.watching("java tutorials xd"));
        jda.addEventListener(new ready());
        jda.addEventListener(new Commands());
        jda.addEventListener(new Embed());
    }
}

