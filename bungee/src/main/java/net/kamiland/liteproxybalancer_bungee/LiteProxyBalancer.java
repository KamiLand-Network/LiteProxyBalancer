package net.kamiland.liteproxybalancer_bungee;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bungee.LiteBungeeFactory;
import net.kamiland.liteproxybalancer_bungee.api.data.PlayerDataManager;
import net.kamiland.liteproxybalancer_bungee.api.service.PlayerService;
import net.kamiland.liteproxybalancer_bungee.api.service.SectionService;
import net.kamiland.liteproxybalancer_bungee.command.FallbackCommand;
import net.kamiland.liteproxybalancer_bungee.command.bungee.SectionCommand;
import net.kamiland.liteproxybalancer_bungee.data.manager.PlayerDataManagerImpl;
import net.kamiland.liteproxybalancer_bungee.listener.PlayerListener;
import net.kamiland.liteproxybalancer_bungee.monitor.LiteProxyBalancerMonitor;
import net.kamiland.liteproxybalancer_bungee.service.PlayerServiceImpl;
import net.kamiland.liteproxybalancer_bungee.service.SectionServiceImpl;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.Arrays;

/**
 * LiteProxyBalancer Main Class (BungeeCord)
 *
 * @author while1cry
 * @version 1.0
 */
public class LiteProxyBalancer extends Plugin {

    private PluginManager pluginManager;
    private LiteCommands<CommandSender> liteCommands;

    private PlayerDataManager playerDataManager;
    private PlayerService playerService;
    private SectionService sectionService;

    @Override
    public void onEnable() {
        pluginManager = getProxy().getPluginManager();

        // Initialize data managers
        playerDataManager = new PlayerDataManagerImpl();

        // Initialize services
        playerService = new PlayerServiceImpl(this, playerDataManager);
        // sectionService = new SectionServiceImpl();

        registerCommands();
    }

    private void registerCommands() {
        // Register LiteCommands
        this.liteCommands = LiteBungeeFactory.builder(this)
                .commands(
                        new FallbackCommand()
                )
                .build();

        // Register BungeeCord native commands
        Arrays.stream(sectionService.getSections())
                .map(section -> new SectionCommand(
                        this, sectionService,
                        section.getCommands().removeFirst(),
                        section.getName(),
                        section.getCommands().toArray(new String[0])))
                .forEach(command -> pluginManager.registerCommand(this, command));
    }

    private void registerListeners() {
        // Register Listeners
        pluginManager.registerListener(this, new PlayerListener(this, playerService));

        // Register Monitor
        pluginManager.registerListener(this, new LiteProxyBalancerMonitor());
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.unregister();
        }
    }

}
