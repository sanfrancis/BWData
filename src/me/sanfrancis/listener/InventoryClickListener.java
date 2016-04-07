package me.sanfrancis.listener;

import me.sanfrancis.inventories.BWDataStartInventory;
import me.sanfrancis.inventories.PlayerPerTeamInventory;
import me.sanfrancis.inventories.TeamAmountInventory;
import me.sanfrancis.inventories.TeamOptionInventory;
import me.sanfrancis.util.Files;
import me.sanfrancis.util.Strings;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;

/**
 * Created by Max on 30.03.16.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick( InventoryClickEvent event ) throws NullPointerException {

        if ( event.getClickedInventory( ).getTitle( ).equalsIgnoreCase( "Bedwars Data" ) ) {
            //BedwarsDataStartInventory

            event.setCancelled( true );
            if ( event.isLeftClick( ) || event.isRightClick( ) || event.isShiftClick( ) ) {
                Player player = ( Player ) event.getWhoClicked( );
                if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.BLUE + "Team Options" ) ) {
                    TeamOptionInventory.openInventory( player );
                } else if ( event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase( ChatColor.BLUE + "Spawns" ) ) {
                    File GENERAL_DATA_FILE = new File ( event.getWhoClicked().getWorld().getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml" );
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration( GENERAL_DATA_FILE );
                    int amount = cfg.getInt( Strings.DataFile_Prefix + ".TeamAmount" );
                    Files.createSpawnsFile( event.getWhoClicked().getWorld() , amount );
                }
            }

        } else if ( event.getClickedInventory( ).getTitle( ).equalsIgnoreCase( "Team Options" ) ) {
            //TeamOptionInventory

            event.setCancelled( true );
            if ( event.isLeftClick( ) || event.isRightClick( ) || event.isShiftClick( ) ) {
                Player player = ( Player ) event.getWhoClicked( );
                if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.RED + "Back" ) ) {
                    BWDataStartInventory.openInventory( player );
                } else if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.BLUE + "Teams" ) ) {
                    TeamAmountInventory.openInventory( player );
                } else if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.BLUE + "Player per Team" ) ) {
                    PlayerPerTeamInventory.openInventory( player );
                }
            }

        } else if ( event.getClickedInventory( ).getTitle( ).equalsIgnoreCase( "Team Amount" ) ) {
            //TeamAmountInventory

            event.setCancelled( true );
            if ( event.isLeftClick( ) || event.isRightClick( ) || event.isShiftClick( ) ) {
                Player player = ( Player ) event.getWhoClicked( );
                if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.RED + "Back" ) ) {
                    TeamOptionInventory.openInventory( player );
                } else if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( "2 Teams" ) ) {
                    TeamAmountInventory.clickTwoTeams( event.getClickedInventory( ), player );
                } else if ( event.getCurrentItem( ).getAmount( ) == 4 ) {
                    TeamAmountInventory.clickFourTeams( event.getClickedInventory( ), player );
                } else if ( event.getCurrentItem( ).getAmount( ) == 8 ) {
                    TeamAmountInventory.clickEightTeams( event.getClickedInventory( ), player );
                }
            }

        } else if ( event.getClickedInventory( ).getTitle( ).equalsIgnoreCase( "Player per Team" ) ) {
            //PlayerPerTeamInventory

            event.setCancelled( true );
            if ( event.isLeftClick( ) || event.isRightClick( ) || event.isShiftClick( ) ) {
                Player player = ( Player ) event.getWhoClicked( );
                if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.GREEN + "Add one" ) ) {
                    PlayerPerTeamInventory.clickAdd( player, event.getClickedInventory( ) );
                } else if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.RED + "Remove one" ) ) {
                    PlayerPerTeamInventory.ClickRemove( player, event.getClickedInventory( ) );
                } else if ( event.getCurrentItem( ).getItemMeta( ).getDisplayName( ).equalsIgnoreCase( ChatColor.RED + "Back" ) ) {
                    TeamOptionInventory.openInventory( player );
                }
            }
        }
    }
}
