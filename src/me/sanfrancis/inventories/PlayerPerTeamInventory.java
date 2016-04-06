package me.sanfrancis.inventories;

import me.sanfrancis.util.Strings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Max on 06.04.16.
 */
public class PlayerPerTeamInventory {

    public static void openInventory( Player player ) {

        Inventory inventory = player.getServer().createInventory( null , 3 * 9 , "Player per Team" );

        int amount = getAmount( player );

        ItemStack players = new ItemStack( Material.LEATHER_HELMET );
        ItemMeta playerItemMeta = players.getItemMeta();
        playerItemMeta.setDisplayName( "Player per Team" );
        players.setItemMeta( playerItemMeta );
        players.setAmount( amount );

        ItemStack add = new ItemStack( Material.WOOL , 1 , (byte) 5  );
        ItemMeta addItemMeta = add.getItemMeta();
        addItemMeta.setDisplayName( ChatColor.GREEN + "Add one" );
        add.setItemMeta( addItemMeta );

        ItemStack remove = new ItemStack( Material.WOOL , 1 , (byte) 14 );
        ItemMeta removeItemMeta = remove.getItemMeta();
        removeItemMeta.setDisplayName( ChatColor.RED + "Remove one" );
        remove.setItemMeta( removeItemMeta );

        ItemStack back = new ItemStack( Material.BARRIER );
        ItemMeta backItemMeta = back.getItemMeta();
        backItemMeta.setDisplayName( ChatColor.RED + "Back" );
        backItemMeta.setLore( Arrays.asList( "Click to get back" ) );
        back.setItemMeta( backItemMeta );

        inventory.setItem( 13 , players );
        inventory.setItem( 11 , remove );
        inventory.setItem( 15 , add );
        inventory.setItem( 26 , back );

        player.openInventory( inventory );

    }

    public static void clickAdd( Player player , Inventory inventory ) {

        int amount = inventory.getItem( 13 ).getAmount();

        if ( amount < 16 ) {
            inventory.getItem( 13 ).setAmount( amount + 1 );
            setFile( player , amount + 1 );
        }
    }

    public static void ClickRemove( Player player , Inventory inventory ) {

        int amount = inventory.getItem( 13 ).getAmount();

        if ( amount > 1 ) {
            inventory.getItem( 13 ).setAmount( amount - 1 );
            setFile( player , amount - 1 );
        }
    }

    public static void setFile( Player player , int amount ) {
        String GENERAL_DATA_FILE;

        GENERAL_DATA_FILE = player.getWorld().getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";
        File data_file = new File( GENERAL_DATA_FILE );
        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            String prefix = Strings.DataFile_Prefix;

            cfg.set( prefix + ".MembersPerTeam" , amount );

            try {
                cfg.save( GENERAL_DATA_FILE );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }

    public static int getAmount( Player player ) {
        String GENERAL_DATA_FILE;

        GENERAL_DATA_FILE = player.getWorld().getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";
        File data_file = new File( GENERAL_DATA_FILE );
        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            String prefix = Strings.DataFile_Prefix;

            int current = cfg.getInt( prefix + ".MembersPerTeam" );

            return current;
        } else {
            return 0;
        }
    }
}
