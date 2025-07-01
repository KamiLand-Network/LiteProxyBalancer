package net.kamiland.liteproxybalancer_bungee.data.manager;

import net.kamiland.liteproxybalancer_bungee.api.data.PlayerDataManager;
import net.kamiland.liteproxybalancer_bungee.data.model.PlayerProfile;
import net.md_5.bungee.api.config.ServerInfo;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class PlayerDataManagerImpl implements PlayerDataManager {

    public final Map<ServerInfo, List<PlayerProfile>> serverPlayersMap = new HashMap<>();
    public final Map<PlayerProfile, ServerInfo> playerServerMap = new HashMap<>();
    public final Map<UUID, PlayerProfile> uuidProfileMap = new HashMap<>();

    public PlayerProfile[] getPlayerProfiles(ServerInfo server) {
        return serverPlayersMap.getOrDefault(server, Collections.emptyList()).toArray(new PlayerProfile[0]);
    }

    @Override
    @Nullable
    public PlayerProfile getPlayerProfile(UUID uuid) {
        return uuidProfileMap.get(uuid);
    }

    @Override
    @Nullable
    public ServerInfo getPlayerServer(UUID uuid) {
        PlayerProfile profile = getPlayerProfile(uuid);
        if (profile == null) return null;
        else return playerServerMap.get(profile);
    }

    @Override
    public void setPlayerProfile(UUID uuid, PlayerProfile profile) {
        uuidProfileMap.put(uuid, profile);
    }

    @Override
    public void setPlayerServer(PlayerProfile player, ServerInfo server) {
        ServerInfo before = playerServerMap.get(player);
        List<PlayerProfile> players;

        if (before != null) {
            players = serverPlayersMap.getOrDefault(before, new ArrayList<>());
            players.remove(player);
            if (!players.isEmpty()) serverPlayersMap.put(before, players);
            else serverPlayersMap.remove(before);

        }

        players = serverPlayersMap.getOrDefault(server, new ArrayList<>());
        players.add(player);
        serverPlayersMap.put(server, players);
        playerServerMap.put(player, server);
    }

    @Override
    public void removePlayer(PlayerProfile player) {
        ServerInfo server = playerServerMap.remove(player);
        List<PlayerProfile> players = serverPlayersMap.getOrDefault(server, new ArrayList<>());

        if (! players.isEmpty()) serverPlayersMap.put(server, players);
        else serverPlayersMap.remove(server);

        uuidProfileMap.remove(player.getUuid());

        players.remove(player);
    }

}
