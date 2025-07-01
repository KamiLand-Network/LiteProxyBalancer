package net.kamiland.liteproxybalancer_bungee.api.expansion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public abstract class Balancer {

    private final ProxySection section;
    public List<ServerInfo> serverList;

    @Getter
    private final String name;
    @Getter
    private final String version;
    @Getter
    private final String author;
    @Getter @Setter
    private byte priority;

    @NotNull
    public abstract ServerInfo getBalancedServer(ProxiedPlayer player);

}
