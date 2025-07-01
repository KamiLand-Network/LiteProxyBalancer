package net.kamiland.liteproxybalancer_bungee.service;

import net.kamiland.liteproxybalancer_bungee.LiteProxyBalancer;
import net.kamiland.liteproxybalancer_bungee.api.data.PlayerDataManager;
import net.kamiland.liteproxybalancer_bungee.api.service.PlayerService;
import net.kamiland.liteproxybalancer_bungee.data.model.PlayerProfile;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class PlayerServiceImpl implements PlayerService {

    private final LiteProxyBalancer plugin;
    private final PlayerDataManager pdm;

    public PlayerServiceImpl(LiteProxyBalancer plugin, PlayerDataManager playerDataManager) {
        this.plugin = plugin;
        this.pdm = playerDataManager;
    }

    @Override
    public void connectToServer(UUID uuid, ServerInfo server) {
        plugin.getProxy().getPlayer(uuid).connect(server);
    }

    @Override
    public void kickFromProxy(UUID uuid, BaseComponent... reasons) {
        plugin.getProxy().getPlayer(uuid).disconnect(reasons);
    }

    @Override
    public void setPlayerServer(UUID uuid, ServerInfo server) {
        pdm.setPlayerServer(pdm.getPlayerProfile(uuid), server);
    }

    @Override
    @Nullable
    public ServerInfo getPlayerServer(UUID uuid) {
        return pdm.getPlayerServer(uuid);
    }

    @Override
    @NotNull
    public UUID[] getServerPlayers(ServerInfo server) {
        List<PlayerProfile> profiles = List.of(pdm.getPlayerProfiles(server));
        return profiles.stream().map(PlayerProfile::getUuid).toArray(UUID[]::new);
    }

    @Override
    @Nullable
    public PlayerProfile getPlayerProfile(UUID uuid) {
        return pdm.getPlayerProfile(uuid);
    }

    @Override
    @Nullable
    public ProxiedPlayer getProxiedPlayer(UUID uuid) {
        PlayerProfile profile = pdm.getPlayerProfile(uuid);
        if (profile == null) return null;
        return plugin.getProxy().getPlayer(profile.getUuid());
    }

    @Override
    @Nullable
    public ProxiedPlayer getProxiedPlayer(PlayerProfile profile) {
        return plugin.getProxy().getPlayer(profile.getUuid());
    }

}
