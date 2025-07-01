package net.kamiland.liteproxybalancer_bungee.command.bungee;

import net.kamiland.liteproxybalancer_bungee.LiteProxyBalancer;
import net.kamiland.liteproxybalancer_bungee.api.event.PlayerBalanceEvent;
import net.kamiland.liteproxybalancer_bungee.api.service.SectionService;
import net.kamiland.liteproxybalancer_bungee.data.model.ProxySection;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SectionCommand extends Command {

    private final LiteProxyBalancer plugin;
    private final ProxySection section;
    private final String permission;

    public SectionCommand(LiteProxyBalancer plugin, SectionService sectionService,
                          String name, String section,
                          String... aliases) {
        super(name, null, aliases);
        this.plugin = plugin;
        this.section = sectionService.getSection(section);
        this.permission = String.format("lpb.section.%s", section);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (! (sender instanceof ProxiedPlayer)) {
            // TODO: Custom message
            return;
        }
        if (! sender.hasPermission(permission)) {
            // TODO: Custom message
            return;
        }

        plugin.getProxy().getPluginManager().callEvent(new PlayerBalanceEvent((ProxiedPlayer) sender, section));
    }

}
