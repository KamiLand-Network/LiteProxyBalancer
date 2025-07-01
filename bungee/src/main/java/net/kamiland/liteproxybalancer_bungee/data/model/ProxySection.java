package net.kamiland.liteproxybalancer_bungee.data.model;

import lombok.Data;
import net.md_5.bungee.api.config.ServerInfo;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

@Data
public class ProxySection {

    private final String name;
    private String description;
    private String serverRegex;
    private LinkedList<ServerInfo> servers;
    private LinkedList<String> commands;
    private BalancerType balancerType;
    @Nullable private ProxySection parent;

}

enum BalancerType {
    RANDOM,
    RANDOM_LOWEST,
    RANDOM_FILLER,
    PROGRESSIVE,
    PROGRESSIVE_LOWEST,
    PROGRESSIVE_FILLER,
    EXTERNAL
}