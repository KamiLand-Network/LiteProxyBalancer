package net.kamiland.liteproxybalancer_bungee.api.service;

import net.kamiland.liteproxybalancer_bungee.data.model.PlayerProfile;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Manages player connectivity and state within the proxy network.
 * <p>
 * Provides operations for server switching, player disconnection, and real-time status tracking.
 * All methods reflect immediate network operations except where explicitly noted.
 * </p>
 *
 * @author while1cry
 * @version 1.0
 */
public interface PlayerService {

    /**
     * Transfers the player to the target server.
     * <p>
     * Initiates a network-level connection process including handshake and state transfer.
     *
     * @param uuid Unique identifier of the target player
     * @param server Target server connection details
     */
    void connectToServer(UUID uuid, ServerInfo server);

    /**
     * Forcibly disconnects a player from the proxy network.
     *
     * @param uuid Unique identifier of the target player
     * @param reasons Optional disconnection message components
     * @throws IllegalStateException if player is not connected
     */
    void kickFromProxy(UUID uuid, BaseComponent... reasons);

    /**
     * Updates the player's server association in the data layer.
     * <p>
     * <b>Note:</b> This only modifies metadata and does <i>not</i> trigger actual server transfer.
     * For physical transfer use {@link #connectToServer(UUID, ServerInfo)}.
     *
     * @param uuid Player identifier
     * @param server New server metadata to register
     */
    void setPlayerServer(UUID uuid, ServerInfo server);

    /**
     * Retrieves the server where the player is currently connected.
     *
     * @param uuid Player identifier
     * @return Active server configuration, or {@code null} if player offline
     */
    @Nullable
    ServerInfo getPlayerServer(UUID uuid);

    /**
     * Gets all online players on a specific server.
     *
     * @param server Target server configuration
     * @return Non-null array of player UUIDs (empty if no players online)
     */
    @NotNull
    UUID[] getServerPlayers(ServerInfo server);

    /**
     * Retrieves the player's profile snapshot.
     * <p>
     * Profile data reflects last known state and may not match real-time status.
     *
     * @param uuid Player identifier
     * @return Profile instance, or {@code null} if profile not loaded
     */
    @Nullable
    PlayerProfile getPlayerProfile(UUID uuid);

    /**
     * Gets the live player session by UUID.
     *
     * @param uuid Player identifier
     * @return Active session instance, or {@code null} if offline
     */
    @Nullable
    ProxiedPlayer getProxiedPlayer(UUID uuid);

    /**
     * Resolves live session from profile data.
     *
     * @param profile Player profile reference
     * @return Corresponding active session, or {@code null} if offline
     */
    @Nullable
    ProxiedPlayer getProxiedPlayer(PlayerProfile profile);
}