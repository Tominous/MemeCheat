package cc.ghast.memecheat.impl.commands;

import cc.ghast.memecheat.MemeCheat;
import cc.ghast.memecheat.api.algorithm.Evaluator;
import cc.ghast.memecheat.api.algorithm.PossibilityEnum;
import cc.ghast.memecheat.api.data.PlayerData;
import cc.ghast.memecheat.api.manager.ConfigManager;
import cc.ghast.memecheat.api.utils.chat.Chat;
import cc.ghast.memecheat.api.utils.chat.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MemeCommand implements CommandExecutor {
    public MemeCommand(MemeCheat memeCheat) {
        memeCheat.getCommand("meme").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!(sender instanceof Player)) return false;
        Player player = (Player)sender;
        PlayerData data = MemeCheat.getInstance().getApi().getPlayerDataManager().getData(player);
        if (!player.hasPermission(StringUtil.ADMIN_PERM)) return returnNoPerm(player);
        switch (args.length){
            case 1: {
                switch (args[0].toLowerCase()){
                    case "alerts": {
                        data.staff.setVLAlertable(!data.staff.isVLAlertable());
                        player.sendMessage(Chat.translate(data.staff.isVLAlertable() ? "&aToggled alerts on" : "&aToggled alerts off"));
                        return true;
                    }
                    case "verbose": {
                        data.staff.setVerboseAlertable(!data.staff.isVerboseAlertable());
                        player.sendMessage(Chat.translate(data.staff.isVerboseAlertable() ? "&aToggled verboses on" : "&aToggled verboses off"));
                        return true;
                    }
                    case "checks": {
                        player.sendMessage(Chat.translate("&a The following checks are currently active for the user " + player.getName()));
                        data.getCheckManager().getChecks().forEach(check->{
                            player.sendMessage(Chat.translate("&7&l-> &a" + check.getName() + " &7(&a" + check.getVar() + "&7) [&a" + check.getType().name() + "&7]"));
                        });
                        return true;
                    }
                    default: {
                        return returnInfo(player);
                    }
                }
            }
            case 2: {
                switch (args[0].toLowerCase()){
                    case "forcecheck": {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) return returnInvalidPlayer(player);
                        PlayerData targerData = MemeCheat.getInstance().getApi().getPlayerDataManager().getData(target);
                        PossibilityEnum result = Evaluator.isHacking(targerData);
                        String[] message = new String[]{
                                "&7&m-----------------------------------",
                                "&7Scanning User&a " + target.getName(),
                                "&7Output: &a" + result,
                                "&7&m-----------------------------------",
                        };
                        for (String s : message){
                            player.sendMessage(Chat.translate(s));
                        }
                        return true;
                    }
                    default: {
                        return returnInfo(player);
                    }
                }
            }
            default: {
                return returnInfo(player);
            }
        }
    }

    private boolean returnInvalidPlayer(Player player){
        player.sendMessage(Chat.translate(ConfigManager.getSettings().getString("message.invalid-player")));
        return false;
    }

    private boolean returnNoPerm(Player player){
        player.sendMessage(Chat.translate(ConfigManager.getSettings().getString("message.no-permission")));
        return false;
    }

    private boolean returnInfo(Player player){
        String[] message = new String[]{
                "&7&m-----------------------------------",
                "&a&lMeme AC &7 made by &aGhast",
                "&7Version: &a" + MemeCheat.getInstance().getDescription().getVersion(),
                "&7&m-----------------------------------",
                "&7/&ameme &7-> &aMain meme command",
                "&7/&ameme verbose &7-> &aToggle verboses",
                "&7/&ameme alerts &7-> &aToggle alerts",
                "&7/&ameme checks &7-> &aSee your current checks",
                "&7/&ameme forcecheck <player> &7-> &aForcefully start the algorithm",
                "&7&m-----------------------------------"
        };
        for (String s : message){
            player.sendMessage(Chat.translate(s));
        }
        return true;
    }


}
