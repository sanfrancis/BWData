package me.sanfrancis.main;

import me.sanfrancis.commands.BWDataCommand;
import me.sanfrancis.listener.InventoryClickListener;
import me.sanfrancis.listener.PlayerJoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Max on 25.03.16.
 */
public class BWData extends JavaPlugin {

    private static String BWDATA_DIR;

    public void onEnable() {
        sendEnableMessage();
        registerCommands();
        registerListeners();

    }

    public void onDisable() {
        sendDisableMessage();
    }

    private void registerCommands() {
        this.getCommand( "bwdata" ).setExecutor( new BWDataCommand() );
    }

    private void registerListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents( new PlayerJoinListener() , this );
        pluginManager.registerEvents( new InventoryClickListener(), this );
    }

    private void sendEnableMessage(){
        System.out.println( "Enabled the Bedwars Data Help Plugin by sanfrancis" + "" );
        System.out.println( "This isn´t the real Bedwars Plugin. Don´t forget to install that as well." );

    }

    private void sendDisableMessage() {
        System.out.println( "Disabled the Bedwars Data Help Plugin by sanfrancis" + " " );

    }

    private void createFiles() {
    }
}