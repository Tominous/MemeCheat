package cc.ghast.memecheat.api.command;

import cc.ghast.memecheat.MemeCheat;
import lombok.Getter;
import org.bukkit.command.CommandSender;


public abstract class Command {
    public MemeCheat memeCheat = MemeCheat.getInstance();


    // Couldn't be bothered to use this in the end
    @Getter private final String command;
    public Command(String command){
        this.command = command;
    }

    public void execute(CommandSender sender, String[] args) {}
}
