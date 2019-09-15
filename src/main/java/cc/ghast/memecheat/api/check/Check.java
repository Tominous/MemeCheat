package cc.ghast.memecheat.api.check;

import cc.ghast.memecheat.MemeCheat;
import cc.ghast.memecheat.algorithm.Evaluator;
import cc.ghast.memecheat.api.data.PlayerData;
import cc.ghast.memecheat.algorithm.PossibilityEnum;
import cc.ghast.memecheat.api.packet.events.PacketPositionLook;
import cc.ghast.memecheat.api.packet.events.PacketUseEntity;
import cc.ghast.memecheat.utils.chat.Chat;
import cc.ghast.memecheat.managers.ConfigManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public abstract class Check {

    @Getter
    private final MemeCheat memeCheat = MemeCheat.getInstance();
    @Getter
    private final String identifier;
    @Getter
    private final String name;
    @Getter
    private final String var;
    @Getter
    private final CheckType type;
    @Getter
    private final int maxVls = 5;
    @Getter
    private final int maxVbs = 15;
    @Getter
    @Setter
    private boolean bannable;
    @Getter
    @Setter
    private boolean active;
    @Getter
    @Setter
    private double falsePosibility;

    public void handle(PlayerData data, PlayerMoveEvent playerMoveEvent) {
    }

    public void handle(PlayerData data, PacketUseEntity packetUseEntity) {
    }

    public void handle(PlayerData data, PacketPositionLook packetPositionLook) {
    }

    public void log(PlayerData data, String... args){
        this.runTask(() -> verbose(data, args));
    }

    public void verbose(PlayerData data, String... args) {
        data.addVerbose(this);
        Bukkit.getOnlinePlayers().parallelStream().forEach(player -> {
            PlayerData targetData = memeCheat.getApi().getPlayerDataManager().getData(player);
            if (player.hasPermission("meme.alert") && targetData.staff.isVerboseAlertable()) {
                String msg = Chat.translate(ConfigManager.getSettings().getString("general.prefix") + " " + ConfigManager.getSettings().getString("message.verbose")
                        .replace("%player%", data.getPlayer().getName())
                        .replace("%check%", name)
                        .replace("%violation%", Integer.toString(data.getVerboses(this)))
                        .replace("%type%", var)
                        .replace("%identifiers%", args[0]));
                player.sendMessage(msg);
            }
        });
        if (data.getVerboses(this) >= maxVbs) {
            violate(data, args);
            data.getVerboses().clear();
        }
    }

    public void violate(PlayerData data, String... args) {
        data.addViolation(this);
        Bukkit.getOnlinePlayers().parallelStream().forEach(player -> {
            PlayerData targetData = memeCheat.getApi().getPlayerDataManager().getData(player);
            if (player.hasPermission("meme.alert") && targetData.staff.isVLAlertable()) {
                String msg = Chat.translate(ConfigManager.getSettings().getString("general.prefix") + " " + ConfigManager.getSettings().getString("message.violation")
                        .replace("%player%", data.getPlayer().getName())
                        .replace("%check%", name)
                        .replace("%violation%", Integer.toString(data.getViolations(this)))
                        .replace("%type%", var)
                        .replace("%identifiers%", args[0]));
                player.sendMessage(msg);
            }
        });
        if (data.getViolations(this) >= maxVls) {
            PossibilityEnum result = Evaluator.isHacking(data);
            if (result.equals(PossibilityEnum.BLATANT) || result.equals(PossibilityEnum.HIGH)) {
                data.getPlayer().setBanned(true);
                data.getPlayer().kickPlayer(Chat.translate(ConfigManager.getSettings().getString("message.ban")));
            }
            Bukkit.getOnlinePlayers().parallelStream().forEach(player -> {
                PlayerData targetData = memeCheat.getApi().getPlayerDataManager().getData(player);
                if (player.hasPermission("meme.alert") && targetData.staff.isVLAlertable()) {
                    String msg = Chat.translate(ConfigManager.getSettings().getString("general.prefix") + " " + ConfigManager.getSettings().getString("message.evaluator")
                            .replace("%player%", data.getPlayer().getName())
                            .replace("%evaluator%", result.name()));
                    player.sendMessage(msg);
                }
            });
            data.getViolations().clear();
        }
    }

    protected void runTask(Runnable runnable) {

        Bukkit.getScheduler().runTask(memeCheat, runnable);
    }
}