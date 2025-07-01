package net.kamiland.liteproxybalancer_bungee.listener;

import net.kamiland.liteproxybalancer_bungee.LiteProxyBalancer;
import net.kamiland.liteproxybalancer_bungee.api.service.PlayerService;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerListener implements Listener {

    private final LiteProxyBalancer plugin;
    private final PlayerService playerService;

    public PlayerListener(LiteProxyBalancer plugin, PlayerService playerService) {
        this.plugin = plugin;
        this.playerService = playerService;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerConnected(ServerConnectEvent event) {
    }

}
