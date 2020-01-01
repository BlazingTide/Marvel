package me.blazingtide.marvel;

import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.target.impl.SpigotTarget;
import me.blazingtide.marvel.thread.ThreadAction;
import net.minecraft.server.v1_7_R4.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JuiceWorldPatch implements Patch<SpigotTarget> {

    @Override
    public SpigotTarget getTarget() {
        return SpigotTarget.of();
    }

    @Override
    public void onStartup() {
        createThread();
    }

    @Override
    public void onDisable() {}

    protected void createThread() {
        MarvelPlugin.get().getThreadFactory().create(() -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.GREEN + "She make me leave the thrills at home!");

                CraftPlayer craftPlayer = (CraftPlayer) player;
                PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 1);

                craftPlayer.getHandle().playerConnection.sendPacket(packet);
            }
        }, ThreadAction.RUN_TASK_TIMER.of(5L));
    }

}
