package me.Oracle.Listeners;


import me.Oracle.Manager.config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;


public class AddTutorial extends ListenerAdapter {

    private static FileWriter fileWriter;

    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (!event.getMember().isOwner()) { return; }

        if (!args[0].equalsIgnoreCase(config.PREFIX + "add")) { return; }

        org.json.simple.parser.JSONParser jsonParser = new JSONParser();


        JSONArray jsonArray = new JSONArray();



        String[] message = event.getMessage().getContentRaw().substring(5).split("\\|");
        String title = message[0];




        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/embed.json"));

            fileWriter = new FileWriter("src/main/resources/embed.json");

            for (String string : message[1].split("\n")) {
                jsonArray.add(string);
            }

            jsonObject.put(title, jsonArray);

            System.out.println(jsonObject.get(jsonObject.size()));

            fileWriter.write(jsonObject.toJSONString());


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {

                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


/*
        try {


            FileWriter fileWriter = new FileWriter("src/main/resources/embed.json");

            File file = new File("src/main/resources/embed.json");

            JSONObject jsonObject;

            if (file.length() != 0) {
                jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/embed.json"));
                for (Object key : jsonObject.keySet()) {

                    JSONObject object = new JSONObject();

                    object.put(key.toString(), jsonObject.get(key));

                    jsonArray.add(object);

                    System.out.println(object);

                }
                System.out.println("file not empty");
            }

            JSONArray Array = new JSONArray();
            Array.addAll(Arrays.asList(message[1].split("\n")));

            JSONObject object = new JSONObject();

            object.put(title, Array);

            jsonArray.add(object);
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
            fileWriter.close();


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


/*
        JSONObject jsonObject = new JSONObject();
        try {

            FileWriter file = new FileWriter("src/main/resources/embed.json");

            JSONObject object = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/embed.json"));

            for (Object per : object.keySet()) {

                jsonObject.put(per.toString(), object.get(per).);
            }

            jsonObject.put("test:", jsonArray);

            file.write(jsonObject.toJSONString());
            file.close();


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

 */




    }


}
