package net.kamiland.liteproxybalancer_bungee.api.service;

import net.kamiland.liteproxybalancer_bungee.api.expansion.Balancer;
import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.kamiland.liteproxybalancer_bungee.exception.BalancerPriorityConflictException;
import net.kamiland.liteproxybalancer_bungee.exception.SectionAlreadyRegisteredException;
import net.kamiland.liteproxybalancer_bungee.exception.SectionNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Manages proxy sections and their associated load balancers.
 * <p>
 * Provides registration, lookup, and removal operations for logical groupings
 * of backend servers with configurable traffic distribution strategies.
 * </p>
 *
 * @author while1cry
 * @version 1.2
 */
public interface SectionService {

    /**
     * Registers a new proxy section.
     *
     * @param section Proxy section configuration
     * @throws SectionAlreadyRegisteredException if the section name is duplicated
     */
    void registerSection(ProxySection section) throws SectionAlreadyRegisteredException;

    /**
     * Unregisters an existing proxy section.
     *
     * @param section Target section configuration
     * @throws SectionNotFoundException if the section is not registered
     */
    void unregisterSection(ProxySection section) throws SectionNotFoundException;

    /**
     * Attaches an external balancer to a proxy section.
     * <p>
     * Overrides the default balancing strategy for the section.
     *
     * @param section Target proxy section
     * @param balancer Custom load balancing implementation
     * @throws BalancerPriorityConflictException if the priority conflicts with an existing balancer
     */
    void registerExternalBalancer(ProxySection section, Balancer balancer) throws BalancerPriorityConflictException;

    /**
     * Removes the external balancer from a section.
     * <p>
     * Restores the default balancing strategy.
     *
     * @param section Target proxy section
     * @param balancer Custom load balancing implementation
     */
    void unregisterExternalBalancer(ProxySection section, Balancer balancer);

    /**
     * Unregisters all sections and their associated balancers.
     */
    void unregisterAll();

    /**
     * Retrieves a section by name.
     *
     * @param name Unique section identifier
     * @return Configured section, or {@code null} if not found
     */
    @Nullable
    ProxySection getSection(String name);

    /**
     * Gets the default proxy section.
     *
     * @return Non-null primary section configuration
     */
    @NotNull
    ProxySection getPrimeSection();

    /**
     * Retrieves the custom balancer attached to a section.
     *
     * @param section Target proxy section
     * @return External balancer instance, or {@code null} if not configured
     */
    @Nullable
    Balancer getExternalBalancer(ProxySection section);

    /**
     * Gets all registered proxy sections.
     *
     * @return Non-null array of sections (empty if none registered)
     */
    @NotNull
    ProxySection[] getSections();
}
