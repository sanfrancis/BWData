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

    //Unicode Häkchen: \u2714 , Kreuz: \u00D7

    @Override
    public boolean onCommand ( CommandSender sender, Command command, String s, String[] args ) {


        char haken = '\u2714' ;
        char kreuz = '\u00D7' ;

        if ( command.getName().equalsIgnoreCase( "bwdata" ) ) {
            if ( sender instanceof Player ) {
                Player player = (Player) sender;
                if ( player.isOp() )
                    BWDataStartInventory.openInventory( player );
                    World currentWorld = player.getWorld();
                    Files.createBWDataDir( currentWorld );
                    Files.createGeneralDataFile( currentWorld );
                    Files.createBronzeDir( currentWorld );
                    Files.createIronDir( currentWorld );
                    Files.createGoldDir( currentWorld );
                } else {
                sender.sendMessage( Strings.ERROR + "You have to be OP to use this command! " );
                }
            } else {
            System.out.println( "[ ERROR ] Only players can use this command!" );
        }
        return true;
    }
}

