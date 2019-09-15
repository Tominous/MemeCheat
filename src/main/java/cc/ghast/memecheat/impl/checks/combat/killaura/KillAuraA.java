package cc.ghast.memecheat.impl.checks.combat.killaura;

import cc.ghast.memecheat.api.check.Check;
import cc.ghast.memecheat.api.check.CheckType;
import cc.ghast.memecheat.api.data.PlayerData;
import cc.ghast.memecheat.api.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class KillAuraA extends Check {
    public KillAuraA(){
        super("KillAuraA", "KillAura", "A", CheckType.COMBAT);
    }

    private double mo, f = Math.pow(2, 24);
    private float c;
    private List<Float> r = new ArrayList<>();

    @Override
    public void handle(PlayerData g, PlayerMoveEvent ev){
        Location to = ev.getTo();
        Location from = ev.getFrom();
        float d = Math.abs(to.getPitch() - from.getPitch());
        float e = Math.abs(to.getYaw() - from.getYaw());
        long a = (long) (d * f);
        long b = (long) (c * f);

        long h = MathUtil.gcd(0x4000, a, b);

        if (e > 0.9 && d < 15.0 && h < 0x20000) {
            r.add(e);
            if (++mo > 20){
                if (MathUtil.averageFloat(r) > 11) {
                    verbose(g, "Experimental");
                } else {
                    r.clear();
                    mo = 0;
                }
            }
        } else {
            if (mo > 0)
                --mo;
        }

        c = d;


    }
}
