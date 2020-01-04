package me.blazingtide.marvel.command.params;

import me.blazingtide.library.command.base.param.CommandParameter;
import me.blazingtide.marvel.MarvelPlugin;
import me.blazingtide.marvel.save.PatchSave;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PatchSaveParameter implements CommandParameter<PatchSave<?, ?>> {
    @Override
    public PatchSave<?, ?> cast(CommandSender commandSender, String s) {
        return MarvelPlugin.get().getPatchSaveByName(s).get();
    }

    @Override
    public void handleException(CommandSender sender, String s, Exception e) {
        sender.sendMessage(ChatColor.RED + "Patch with the name " + s + " does not exist.");
    }
}
