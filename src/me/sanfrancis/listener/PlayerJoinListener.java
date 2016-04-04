package me.sanfrancis.listener;

import me.sanfrancis.util.Strings;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

/**
 * Created by Max on 25.03.16.
 */
public class PlayerJoinListener implements Listener {

    private static World world;

    @EventHandler
    public void onJoin( PlayerJoinEvent event ) {

        Player player = event.getPlayer();
        world = event.getPlayer().getWorld();


        if ( player.isOp() ) {
            if ( !worldHadDir( world ) ) {
                player.sendMessage( Strings.PREFIX + "Looks like the first time. Use /bwdata to set the data for this map" );
            }
        }
    }

    private boolean worldHadDir( World world ) {
        File dir = new File( world.getWorldFolder().getPath() + "/BWData" );
        if ( !dir.exists() ){
            return false;
        } else {
            return true;
        }
    }

}

