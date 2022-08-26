package tech.erudo.mc.test.inventoryframework.testinventoryframework;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("tif")
public class CommandManager {

    private static final String[] helpMsgs = {
            "===================",
            "HELP",
            "==================="
    };


    @Default
    public static void tif(CommandSender sender) {
        sender.sendMessage(helpMsgs);
    }

    @Subcommand("gui1")
    public static void gui1(Player player) {
        SampleChestGUI gui1 = new SampleChestGUI(player);
    }


}
