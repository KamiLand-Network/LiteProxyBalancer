package net.kamiland.liteproxybalancer_bungee.service;

import lombok.AllArgsConstructor;
import net.kamiland.liteproxybalancer_bungee.api.expansion.Balancer;
import net.kamiland.liteproxybalancer_bungee.api.service.SectionService;
import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.kamiland.liteproxybalancer_bungee.exception.BalancerPriorityConflictException;
import net.kamiland.liteproxybalancer_bungee.exception.SectionAlreadyRegisteredException;
import net.kamiland.liteproxybalancer_bungee.exception.SectionNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final Map<String, ProxySection> sectionsMap = new HashMap<>();
    private final Map<ProxySection, LinkedHashMap<Byte, Balancer>> externalBalancerMap = new HashMap<>();
    private final ProxySection primeSection;

    @Override
    public void registerSection(ProxySection section) throws SectionAlreadyRegisteredException {
        if (sectionsMap.containsKey(section.getName())) throw new SectionAlreadyRegisteredException(section.getName());
        sectionsMap.put(section.getName(), section);
    }

    @Override
    public void unregisterSection(ProxySection section) throws SectionNotFoundException {
        if (!sectionsMap.containsKey(section.getName())) throw new SectionNotFoundException(section.getName());
        sectionsMap.remove(section.getName());
    }

    @Override
    public void registerExternalBalancer(ProxySection section, Balancer balancer) {
        TreeMap<Byte, Balancer> balancerMap = new TreeMap<>(externalBalancerMap.getOrDefault(section, new LinkedHashMap<>()));
        if (balancerMap.containsKey(balancer.getPriority())) throw new BalancerPriorityConflictException(balancer.getPriority(), balancer);
        balancerMap.put(balancer.getPriority(), balancer);
        externalBalancerMap.put(section, new LinkedHashMap<>(balancerMap));
    }

    @Override
    public void unregisterExternalBalancer(ProxySection section, Balancer balancer) {
        TreeMap<Byte, Balancer> balancerMap = new TreeMap<>(externalBalancerMap.getOrDefault(section, new LinkedHashMap<>()));
        if (balancerMap.containsKey(balancer.getPriority())) throw new BalancerPriorityConflictException(balancer.getPriority(), balancer);
        balancerMap.remove(balancer.getPriority(), balancer);
        externalBalancerMap.put(section, new LinkedHashMap<>(balancerMap));
    }

    @Override
    public void unregisterAll() {
        sectionsMap.clear();
        externalBalancerMap.clear();
    }

    @Override
    @Nullable
    public ProxySection getSection(String name) {
        return sectionsMap.get(name);
    }

    @Override
    @NotNull
    public ProxySection getPrimeSection() {
        return primeSection;
    }

    @Override
    @Nullable
    public Balancer getExternalBalancer(ProxySection section) {
        Balancer[] balancers = externalBalancerMap.get(section).values().toArray(new Balancer[0]);
        return balancers.length == 0 ? null : balancers[0];
    }

    @Override
    @NotNull
    public ProxySection[] getSections() {
        if (!sectionsMap.isEmpty()) {
            return sectionsMap.values().toArray(new ProxySection[0]);
        }
        return new ProxySection[0];
    }

}
