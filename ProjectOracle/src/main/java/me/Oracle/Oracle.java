package me.Oracle;


import me.Oracle.Listeners.*;
import me.Oracle.Manager.config;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.concurrent.TimeUnit;

public class Oracle {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {



        long enable = System.currentTimeMillis();
        restartBot();


        System.out.println("Oracle Bot enabled in " + TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - enable)) + "s!");
    }

    public static void restartBot() throws LoginException {

        jda = JDABuilder.createDefault(config.TOKEN)
                .setActivity(Activity.watching("Minehut Tutorials"))
                .addEventListeners(new shutdown())
                .addEventListeners(new vote())
                .addEventListeners(new skript())
                .addEventListeners(new GuildJoin())
                .addEventListeners(new AddTutorial())
                .addEventListeners(new RetrieveMessages())
                .build();

    }



}
