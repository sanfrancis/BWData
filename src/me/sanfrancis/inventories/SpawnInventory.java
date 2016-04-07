package me.sanfrancis.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Max on 02.04.16.
 */
public class SpawnInventory {

    public static void openTwoTeamInventory( Player player ) {

        Inventory inventory = player.getServer().createInventory( null , 4 * 9 , "Spawns" );

        File spawns_file = new File( player.getWorld().getWorldFolder().getPath() + "/BWData" + "/" + "spawns" + ".yml" );
        if ( spawns_file.exists() ) {

            ItemStack teamRed = new ItemStack( Material.WOOL , 1, (byte) 14 );
            teamRed.getItemMeta().setDisplayName( ChatColor.RED + "RED" );
            teamRed.setItemMeta( teamRed.getItemMeta() );

            ItemStack teamBlue = new ItemStack( Material.WOOL , 1 , (byte) 11 );
            ItemMeta teamBlueItemMeta = teamBlue.getItemMeta();
            teamBlueItemMeta.setDisplayName( ChatColor.BLUE + "BLUE" );
            teamBlue.setItemMeta( teamBlueItemMeta );

            ItemStack delete = new ItemStack( Material.BARRIER );
            ItemMeta deleteItemMeta  = delete.getItemMeta();
            deleteItemMeta.setDisplayName( ChatColor.BOLD + "" +  ChatColor.RED + "Delete" );
            delete.setItemMeta( deleteItemMeta );

            ItemStack set = createWool( Material.WOOL , ChatColor.GREEN + "Already set" , 1 , (byte) 5 , null );

            ItemStack notset = createWool( Material.WOOL , ChatColor.RED + "Not yet et" , 1 , (byte) 14 , null );

            ItemStack setnow = createItem( Material.GOLD_AXE , ChatColor.GOLD + "Set now" , 1 , null );

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
            //Files.createSpawnsFile( player.getWorld() , 2 );
        }
    }

    private static ItemStack createItem ( Material material , String displayName , int amount , List<String> list ) {
        ItemStack itemStack = new ItemStack( material );
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName( displayName );
        itemMeta.setLore( list );
        itemStack.setItemMeta( itemMeta );
        if ( amount > 0 ) {
            itemStack.setAmount( amount );
        } else {
            itemStack.setAmount( 1 );
        }
        return itemStack;
    }

    private static ItemStack createWool ( Material material , String displayName , int amount , byte data , List<String> list ) {
        ItemStack itemStack = new ItemStack( material , amount , data );
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName( displayName );
        itemMeta.setLore( list );
        itemStack.setItemMeta( itemMeta );
        if ( amount > 0 ) {
            itemStack.setAmount( amount );
        } else {
            itemStack.setAmount( 1 );
        }
        return itemStack;
    }
}
