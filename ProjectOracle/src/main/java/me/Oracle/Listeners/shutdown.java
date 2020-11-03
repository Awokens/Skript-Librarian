package me.Oracle.Listeners;

import me.Oracle.Manager.config;
import me.Oracle.Oracle;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class shutdown extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (!event.getMember().isOwner()) { return; }

        if (args[0].equalsIgnoreCase(config.PREFIX + "restart")) {

            Oracle.jda.shutdown();

            try {
                Oracle.restartBot();
            } catch (LoginException e) {
                e.printStackTrace();
            }

        }
    }


}
