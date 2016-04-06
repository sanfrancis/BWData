package me.sanfrancis.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Max on 01.04.16.
 */
public class TeamOptionInventory {

    public static void openInventory( Player player ) {
        Inventory inventory = player.getServer().createInventory( null , 3 * 9 , "Team Options" );

        ItemStack teams = new ItemStack( Material.DIAMOND_BLOCK );
        //TODO Find item for this possibilty
        teams.setAmount( 1 );
        ItemMeta teamsItemMeta = teams.getItemMeta();
        teamsItemMeta.setDisplayName( ChatColor.BLUE + "Teams" );
        teamsItemMeta.setLore( Arrays.asList( "Click to get to " , "team amount options" ) );
        teams.setItemMeta( teamsItemMeta );

        ItemStack members = new ItemStack( Material.LEATHER_HELMET );
        //TODO Find better item for this possibilty, set normal to 4 per team
        members.setAmount( 1 );
        ItemMeta membersItemMeta = members.getItemMeta();
        membersItemMeta.setDisplayName( ChatColor.BLUE + "Player per Team" );
        membersItemMeta.setLore( Arrays.asList( "Click to get to", "options for the", "players per team" ) );
        members.setItemMeta( membersItemMeta );

        ItemStack back = new ItemStack( Material.BARRIER );
        ItemMeta backItemMeta = back.getItemMeta();
        backItemMeta.setDisplayName( ChatColor.RED + "Back" );
        backItemMeta.setLore( Arrays.asList( "Click to get back" ) );
        back.setItemMeta( backItemMeta );

        inventory.setItem( 11 , teams );
        inventory.setItem( 15 , members );
        inventory.setItem( 26 , back );

        player.openInventory( inventory );

    }

}

