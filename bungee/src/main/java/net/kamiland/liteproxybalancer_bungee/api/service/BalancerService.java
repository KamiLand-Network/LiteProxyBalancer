package net.kamiland.liteproxybalancer_bungee.api.service;

import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.md_5.bungee.api.config.ServerInfo;
import org.jetbrains.annotations.NotNull;

public interface BalancerService {

    @NotNull
    ServerInfo getBalancedServer(ProxySection section);

}
