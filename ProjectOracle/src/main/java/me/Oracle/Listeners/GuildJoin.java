package me.Oracle.Listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoin extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        TextChannel channel = event.getJDA().getTextChannelById("766731653824380939");

        TextChannel log = event.getJDA().getTextChannelById("768152935292338226");


        Guild guild = event.getGuild();
        Role role = guild.getRoleById("766819159299981382");
        Member member = guild.getMemberById(event.getUser().getId());

        if (member == null || role == null) {

            guild.addRoleToMember(member, role).queue();

        } else {


            log.sendMessage("Invalid default member role ID! (From GuildMemberJoinEvent)" ).queue();

        }
        channel.sendMessage("Please welcome, " + event.getUser().getName()).queue();
    }



}
