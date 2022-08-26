package tech.erudo.mc.test.inventoryframework.testinventoryframework;

import dev.jorel.commandapi.CommandAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestInventoryFramework extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandAPI.registerCommand(CommandManager.class);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
