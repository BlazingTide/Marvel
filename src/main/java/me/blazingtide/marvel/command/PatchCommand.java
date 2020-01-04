package me.blazingtide.marvel.command;

import me.blazingtide.library.command.base.CommandBase;
import me.blazingtide.library.command.base.param.Param;
import me.blazingtide.marvel.MarvelPlugin;
import me.blazingtide.marvel.loader.reason.LoadReason;
import me.blazingtide.marvel.patch.Patch;
import me.blazingtide.marvel.save.PatchSave;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.stream.Collectors;

public class PatchCommand {

    @CommandBase(label = {"patch", "patches", "patch.list"}, permission = "marvel.admin")
    public void execute(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Patches: " + ChatColor.GRAY + MarvelPlugin.get().getPatchSaves().stream()
                .map(save -> save.getPatch().getName())
                .collect(Collectors.joining(", ")));
    }

    @CommandBase(label = {"patch.load", "patch.add", "patch.enable"}, permission = "marvel.admin")
    public void executeLoad(CommandSender sender, @Param(val = "File") String fileName) {
        if (!fileName.endsWith(".jar")) {
            fileName = fileName.concat(".jar");
        }
        File file = new File(MarvelPlugin.get().getDataFolder().getPath() + "/" + fileName);

        if (!file.exists()) {
            sender.sendMessage(ChatColor.RED + "Failed to find file with the name " + fileName);
            return;
        }

        sender.sendMessage(ChatColor.GRAY + "Attempting to load " + fileName + ".");
        try {
            Patch<?> patch = MarvelPlugin.get().getLoader().load(file, LoadReason.COMMAND);
            sender.sendMessage(ChatColor.GREEN + "Succeeded in loading " + patch.getName() + ".");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Failed to load " + fileName + ".");
            e.printStackTrace();
        }
    }

    @CommandBase(label = {"patch.unload", "patch.disable"}, permission = "marvel.admin")
    public void executeUNload(CommandSender sender, @Param(val = "Patch") PatchSave save) {
        Patch patch = save.getPatch();

        sender.sendMessage(ChatColor.GRAY + "Attempting to unload " + patch.getName() + ".");
        try {
            MarvelPlugin.get().getLoader().unload(save);
            sender.sendMessage(ChatColor.GREEN + "Succeeded in unloading " + patch.getName() + ".");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Failed to unload " + patch.getName() + ".");
            e.printStackTrace();
        }
    }

    @CommandBase(label = {"patch.reload"}, permission = "marvel.admin")
    public void executeReload(CommandSender sender, @Param(val = "Patch") PatchSave save) {
        Patch patch = save.getPatch();

        sender.sendMessage(ChatColor.GRAY + "Attempting to reload " + patch.getName() + ".");
        try {
            MarvelPlugin.get().getLoader().unload(save);
            MarvelPlugin.get().getLoader().load(save.getFile(), LoadReason.COMMAND);
            sender.sendMessage(ChatColor.GREEN + "Succeeded in reloading " + patch.getName() + ".");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Failed to reload " + patch.getName() + ".");
            e.printStackTrace();
        }
    }

}
