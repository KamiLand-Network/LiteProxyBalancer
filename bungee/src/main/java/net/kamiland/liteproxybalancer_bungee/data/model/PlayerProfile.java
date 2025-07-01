package net.kamiland.liteproxybalancer_bungee.data.model;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerProfile {

    private UUID uuid;
    private String name;

}
