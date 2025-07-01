package net.kamiland.liteproxybalancer_bungee.api.data;

import net.kamiland.liteproxybalancer_bungee.data.model.PlayerProfile;
import net.md_5.bungee.api.config.ServerInfo;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Manages player-server associations and profile data storage.
 * <p>
 * This service maintains bidirectional mappings between players and their connected servers,
 * enabling efficient lookup operations for player locations and profile data. All write operations
 * automatically synchronize related mappings to ensure data consistency.
 * </p>
 *
 * <p>See official implementation: {@link net.kamiland.liteproxybalancer_bungee.data.manager.PlayerDataManagerImpl}</p>
 *
 * @author while1cry
 * @version 1.0
 */
public interface PlayerDataManager {

    /**
     * Registers or updates a player's profile in the data store.
     * <p>
     * Note: This establishes the core player identity record but does not assign server connections.
     * Use {@link #setPlayerServer(PlayerProfile, ServerInfo)} for server assignment.
     *
     * @param uuid Player identifier
     * @param profile Player profile container
     */
    void setPlayerProfile(UUID uuid, PlayerProfile profile);

    /**
     * Updates a player's connected server association.
     * <p>
     * Synchronizes all related mappings:
     * <ul>
     * <li>Removes player from previous server's player list</li>
     * <li>Adds player to new server's player list</li>
     * <li>Updates player-server direct mapping</li>
     * </ul>
     *
     * @param player Player profile reference
     * @param server New server configuration
     */
    void setPlayerServer(PlayerProfile player, ServerInfo server);

    /**
     * Removes all traces of a player from the data store.
     * <p>
     * Performs cascading cleanup:
     * <ul>
     * <li>Removes from server-player mappings</li>
     * <li>Clears player-server direct association</li>
     * <li>Deletes profile record</li>
     * </ul>
     *
     * @param player Player profile to remove
     */
    void removePlayer(PlayerProfile player);

    /**
     * Retrieves all player profiles connected to a specific server.
     *
     * @param server Target server configuration
     * @return Array of player profiles (empty array if no players connected)
     */
    PlayerProfile[] getPlayerProfiles(ServerInfo server);

    /**
     * Retrieves a player's profile by unique identifier.
     *
     * @param uuid Player identifier
     * @return Associated player profile, or {@code null} if not found
     */
    @Nullable
    PlayerProfile getPlayerProfile(UUID uuid);

    /**
     * Retrieves the server to which a player is currently connected.
     *
     * @param uuid Player identifier
     * @return Server configuration, or {@code null} if not connected
     */
    @Nullable
    ServerInfo getPlayerServer(UUID uuid);

}