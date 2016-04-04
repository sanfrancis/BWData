package me.sanfrancis.listener;

import me.sanfrancis.inventories.BWDataStartInventory;
import me.sanfrancis.inventories.TeamAmountInventory;
import me.sanfrancis.inventories.TeamOptionInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Max on 30.03.16.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick( InventoryClickEvent event ) {

        if ( event.getClickedInventory().getTitle().equalsIgnoreCase( "Bedwars Data" ) ) {
            //BedwarsDataStartInventory
            event.setCancelled( true );
            if ( event.isLeftClick() || event.isRightClick() || event.isShiftClick() ) {
                Player player = ( Player ) event.getWhoClicked();
                if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.BLUE + "Team Options" ) ) {
                    //TeamOptionInventory
                    TeamOptionInventory.openInventory( player );
                }
            }


        } else if ( event.getClickedInventory().getTitle().equalsIgnoreCase( "Team Options" ) ) {
            //TeamOptionInventory
            event.setCancelled( true );
            if ( event.isLeftClick() || event.isRightClick() || event.isShiftClick() ) {
                Player player = ( Player ) event.getWhoClicked();
                if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.RED + "Back" ) ) {
                    BWDataStartInventory.openInventory( player );
                } else if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.BLUE + "Teams" ) ) {
                    TeamAmountInventory.openInventory( player );
                } else if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.BLUE + "Players" ) ) {
                    //TODO open memberperteaminv
                }
            }


        } else if ( event.getClickedInventory().getTitle().equalsIgnoreCase( "Team Amount" ) ) {
            //TeamAmountInventory
            event.setCancelled( true );
            if ( event.isLeftClick() || event.isRightClick() || event.isShiftClick() ) {
                Player player = ( Player ) event.getWhoClicked();
                if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.RED + "Back" ) ) {
                    TeamOptionInventory.openInventory( player );
                } else if ( event.getCurrentItem().getAmount() == 2 ) {
                    TeamAmountInventory.clickTwoTeams( event.getClickedInventory() , player.getWorld() );
                } else if ( event.getCurrentItem().getAmount() == 4 ) {
                    TeamAmountInventory.clickFourTeams( event.getClickedInventory() , player.getWorld() );
                } else if ( event.getCurrentItem().getAmount() == 8 ) {
                    TeamAmountInventory.clickEightTeams( event.getClickedInventory() , player.getWorld() );
                }
            }


        }
    }

}
