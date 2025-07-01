package net.kamiland.liteproxybalancer_bungee.api.event;

import lombok.Getter;
import lombok.Setter;
import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class PlayerBalanceEvent extends Event implements Cancellable {

    @Getter
    private final ProxiedPlayer player;
    @Getter
    private final ProxySection section;
    private boolean cancelled;
    @Getter @Setter
    private ServerInfo server;

    public PlayerBalanceEvent(ProxiedPlayer player, ProxySection section) {
        this.player = player;
        this.section = section;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

}
