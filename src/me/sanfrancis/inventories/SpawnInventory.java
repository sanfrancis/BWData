package me.sanfrancis.inventories;

import me.sanfrancis.util.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Max on 02.04.16.
 */
public class SpawnInventory {

    public static void openTwoTeamInventory( Player player ) {

        Inventory inventory = player.getServer().createInventory( null , 3 * 9 , "Spawns" );

        File spawns_file = new File( player.getWorld().getWorldFolder().getPath() + "/BWData" + "/" + "spawns" + ".yml" );
        if ( spawns_file.exists() ) {

            ItemStack teamRed = new ItemStack( Material.WOOL , 14 );
            teamRed.getItemMeta().setDisplayName( ChatColor.RED + "RED" );
            teamRed.setItemMeta( teamRed.getItemMeta() );

            ItemStack teamBlue = new ItemStack( Material.WOOL , 11 );
            ItemMeta teamBlueItemMeta = teamBlue.getItemMeta();
            teamBlueItemMeta.setDisplayName( ChatColor.BLUE + "BLUE" );
            teamBlue.setItemMeta( teamBlueItemMeta );

            ItemStack delete = new ItemStack( Material.BARRIER );
            ItemMeta deleteItemMeta  = delete.getItemMeta();
            deleteItemMeta.setDisplayName( ChatColor.BOLD + "" +  ChatColor.RED + "Delete" );
            delete.setItemMeta( deleteItemMeta );

            ItemStack set = new ItemStack( Material.EMERALD_BLOCK );

            ItemStack notset = new ItemStack( Material.REDSTONE_BLOCK );

            ItemStack setNow = new ItemStack( Material.GOLD_AXE );

            ItemStack back = new ItemStack( Material.ARROW );
            ItemMeta backItemMeta = back.getItemMeta();
            backItemMeta.setDisplayName( ChatColor.RED + "Back" );
            backItemMeta.setLore( Arrays.asList( "Click to get back" ) );
            back.setItemMeta( backItemMeta );

            // 1 , TEAM , 3 , 4 , 5 , SET? , 7 , SET NOW , DELETE

            inventory.setItem( 1 , teamRed );
            inventory.setItem( 10 , teamBlue );
            inventory.setItem( 26 , back );

            //TODO Add: Set? ( Red / Green Wool, Bad because of team colour? )  , Delete ( Barrier )  , Set Now

            // Icons for Team RED
            if ( spawns_file.exists() ) {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawns_file );
                String name = new String( "RED" );
            }
        } else {
            Files.createSpawnsFile( player.getWorld() );
        }
    }
}
