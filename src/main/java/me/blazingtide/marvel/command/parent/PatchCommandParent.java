package me.blazingtide.marvel.command.parent;

import me.blazingtide.library.command.parent.CommandParent;

/**
 * The command handle will automatically create an instance of this
 * command parent and store it for future use
 *
 * @see me.blazingtide.library.command.base.CloverCommandHandler
 */
public class PatchCommandParent implements CommandParent {
    @Override
    public String[] getLabels() {
        return new String[]{"patch", "mpatch"};
    }
}
