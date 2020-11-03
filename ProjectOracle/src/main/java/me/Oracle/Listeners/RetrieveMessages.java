package me.Oracle.Listeners;

import me.Oracle.Manager.config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RetrieveMessages extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        TextChannel channel = event.getTextChannel();

        String[] arg = event.getMessage().getContentRaw().split(" ");

        if (!event.getMember().isOwner()) { return; }

        if (!arg[0].equalsIgnoreCase(config.PREFIX + "calculate")) { return; }

        event.getTextChannel().sendMessage("Calculating...").queue();

        double count = 0;

        for (Message message : channel.getHistory().retrievePast(100).complete()) {

            if (!message.getContentRaw().contains("€")) {
                continue;
            }

            String string = message.getContentRaw();

            string = string.substring(string.indexOf("€") + 1);
            string = string.substring(0, string.lastIndexOf("."));

            count = count + Double.parseDouble(string);

        }

        event.getTextChannel().sendMessage("Total amount is..." + count + " euros!").queue();




    }



}
