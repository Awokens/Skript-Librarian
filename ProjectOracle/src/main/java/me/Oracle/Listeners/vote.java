package me.Oracle.Listeners;

import me.Oracle.Manager.config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class vote extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        String[] poll = event.getMessage().getContentRaw().replace(config.PREFIX + "vote ", "").split("\\|");

        if (!args[0].equalsIgnoreCase(config.PREFIX + "vote")) { return; }

        if (poll.length != 3) {
            event.getTextChannel().sendMessage("Usage: !vote <vote 1>|<vote 2>|<title> ").queue();
            return;
        }


        event.getTextChannel().sendMessage(createPoll(event.getAuthor().getName(), poll[0], poll[1], poll[2])).queue(message -> {


            message.addReaction("\u0031\u20E3").queueAfter(1, TimeUnit.SECONDS);
            message.addReaction("\u0032\u20E3").queueAfter(1, TimeUnit.SECONDS);


        });

    }


    public MessageEmbed createPoll(String member, String vote1, String vote2, String title) {

        EmbedBuilder poll = new EmbedBuilder();

        poll.setTitle(title)
                .setAuthor(member)
                .setColor(Color.CYAN)
                .setDescription("A vote of your choice!")
                .addField("Vote 1", vote1, true)
                .addField("Vote 2", vote2, true);

        return poll.build();

    }
}
