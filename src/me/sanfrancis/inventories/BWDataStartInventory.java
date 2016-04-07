package me.sanfrancis.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Max on 28.03.16.
 */
public class BWDataStartInventory {

    public static void openInventory( Player player ) {
        Inventory inventory = player.getServer().createInventory( null , 5 * 9 , "Bedwars Data" );
        Inventory inventory1 = player.getServer().createInventory( null , InventoryType.CHEST , "BedwarsData" );
        inventory1.getSize();

        //Item-Creation

        ItemStack teams = new ItemStack( Material.DIAMOND_BLOCK );
        //TODO Find better item
        teams.setAmount( 1 );
        ItemMeta teamsItemMeta = teams.getItemMeta();
        teamsItemMeta.setDisplayName( ChatColor.BLUE + "Team Options" );
        teamsItemMeta.setLore( Arrays.asList( "Click to get to " , "team options" ) );
        teams.setItemMeta( teamsItemMeta );

        ItemStack spawns = new ItemStack( Material.BED );
        spawns.setAmount( 1 );
        ItemMeta spawnsItemMeta = spawns.getItemMeta();
        spawnsItemMeta.setDisplayName( ChatColor.BLUE + "Spawns" );
        spawnsItemMeta.setLore( Arrays.asList( "Click to get to " , "spawn options" ) );
        spawns.setItemMeta( spawnsItemMeta );

        ItemStack bronze = new ItemStack( Material.CLAY_BRICK );
        bronze.setAmount( 1 );
        ItemMeta bronzeItemMeta = bronze.getItemMeta();
        bronzeItemMeta.setDisplayName( ChatColor.RED + "Bronze" );
        bronzeItemMeta.setLore( Arrays.asList( "Click to get to" , "bronze spawn options" ) );
        bronze.setItemMeta( bronzeItemMeta );

        ItemStack iron = new ItemStack( Material.IRON_INGOT );
        iron.setAmount( 1 );
        ItemMeta ironItemMeta = iron.getItemMeta();
        ironItemMeta.setDisplayName( ChatColor.GRAY + "Iron" );
        ironItemMeta.setLore( Arrays.asList( "Click to get to" , "iron spawn options" ) );
        iron.setItemMeta( ironItemMeta );

        ItemStack gold = new ItemStack( Material.GOLD_INGOT );
        iron.setAmount( 1 );
        ItemMeta goldItemMeta = gold.getItemMeta();
        goldItemMeta.setDisplayName( ChatColor.GOLD + "Gold" );
        goldItemMeta.setLore( Arrays.asList( "Click to get to" , "gold spawn options" ) );
        gold.setItemMeta( goldItemMeta );

        ItemStack back = new ItemStack( Material.BARRIER );
        ItemMeta backItemMeta = back.getItemMeta();
        backItemMeta.setDisplayName( ChatColor.RED + "Back" );
        backItemMeta.setLore( Arrays.asList( "Click to get back" ) );
        back.setItemMeta( backItemMeta );




        //Inventory Add
        inventory.setItem( 10 , teams );                // File 1, Name: GeneralData.yml
        //inventory.setItem( 13 , free space );
        inventory.setItem( 16 , spawns );               // File 2, Name: Spawns.yml
        inventory.setItem( 28 , bronze );               // File 3, new Dir, everyone own File
        inventory.setItem( 31 , iron );                 // File 4, new Dir, everyone own File
        inventory.setItem( 34 , gold );                 // File 5, new Dir, everyone own File

        //TODO Add Time Set Inv
        //TODO Add Spawns Inv
        //TODO Add Bronze, Iron, Gold Inv

        player.openInventory( inventory );
    }
}
