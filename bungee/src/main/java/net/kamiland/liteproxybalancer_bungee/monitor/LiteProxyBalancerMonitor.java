package net.kamiland.liteproxybalancer_bungee.monitor;

import net.kamiland.liteproxybalancer_bungee.api.event.PlayerBalanceEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LiteProxyBalancerMonitor implements Listener {

    @EventHandler(priority = Byte.MAX_VALUE)
    public void onPlayerBalance(PlayerBalanceEvent event) {

    }

}
