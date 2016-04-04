package me.sanfrancis.commands;

import me.sanfrancis.inventories.BWDataStartInventory;
import me.sanfrancis.util.Files;
import me.sanfrancis.util.Strings;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Max on 28.03.16.
 */
public class BWDataCommand implements CommandExecutor {

    @Override
    public boolean onCommand ( CommandSender sender, Command command, String s, String[] args ) {

        if ( command.getName().equalsIgnoreCase( "bwdata" ) ) {
            Player player = (Player) sender;
            World currentWorld = player.getWorld();
            Files.createBWDataDir( currentWorld );
            Files.createGeneralDataFile( currentWorld );
            Files.createSpawnsFile( currentWorld );
            Files.createBronzeDir( currentWorld );
            Files.createIronDir( currentWorld );
            Files.createGoldDir( currentWorld );

            if ( sender instanceof Player ) {
                if ( sender.isOp() ) {
                    BWDataStartInventory.openInventory( player );
                } else {
                    sender.sendMessage( Strings.ERROR + "You have to be OP to use this command! " );
                }
            } else {
                System.out.println( "[ ERROR ] Only players can use this command!" );
            }
        }
        return true;
    }
}

