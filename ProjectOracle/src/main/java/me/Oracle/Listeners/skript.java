package me.Oracle.Listeners;

import me.Oracle.Manager.config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class skript extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");


        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }
        if (!args[0].equalsIgnoreCase(config.PREFIX + "skript")) {
            return;
        }
        if (args.length != 2) {
            event.getTextChannel().sendMessage("usage: !skript <search>").queue();
            return;
        }

        JSONParser jsonParser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/embed.json"));
            StringBuilder message = new StringBuilder();
            JSONArray jsonArray = (JSONArray) jsonObject.get(args[1].toLowerCase());

            if (jsonArray != null) {

                for (Object o : jsonArray) {
                    message.append(o).append("\n");
                }
                event.getTextChannel().sendMessage(createEmbed(event.getAuthor().getName(), message.toString())).queue();
                return;
            }

            AtomicReference<String> similar = new AtomicReference<>("");
            for (Object key : jsonObject.keySet()) {

                if (!key.toString().contains(args[1].toLowerCase())) {
                    continue;
                }
                similar.updateAndGet(v -> v + key);
            }

            JSONArray jsonArray1 = (JSONArray) jsonObject.get(similar.toString());
            if (jsonArray1 == null) {
                event.getTextChannel().sendMessage("Sorry, but the skript you're looking for doesn't exist in my library.").queue();
                return;
            }
            for (Object o : jsonArray1) {
                message.append(o).append("\n");

            }

            event.getTextChannel().sendMessage(createEmbed(event.getAuthor().getName(), message.toString())).queue();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }


    public MessageEmbed createEmbed(String member, String search) {

        EmbedBuilder poll = new EmbedBuilder();

        poll
            .setAuthor(member)
            .setColor(Color.CYAN)
            .setDescription(search);
        return poll.build();

    }

}
