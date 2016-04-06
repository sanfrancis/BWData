package me.sanfrancis.inventories;

import me.sanfrancis.util.Files;
import me.sanfrancis.util.Strings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Max on 28.03.16.
 */
public class TeamAmountInventory {

    public static void openInventory( Player player ) {
        Inventory inventory = player.getServer().createInventory( null , 5 * 9 , "Team Amount" );

        //TEAMS: Black   , green, orange , blue , aqua   , red , pink , yellow
        //Teams: Schwarz , Grün , orange , blau , türkis , rot , pink , gelb

        ItemStack two = new ItemStack( Material.BED );
        two.setAmount( 2 );
        ItemMeta twoItemMeta = two.getItemMeta();
        twoItemMeta.setDisplayName( "2 Teams" );
        two.setItemMeta( twoItemMeta );

        ItemStack four = new ItemStack( Material.BED );
        four.setAmount( 4 );
        ItemMeta fourItemMeta = four.getItemMeta();
        fourItemMeta.setDisplayName( "4 Teams" );
        four.setItemMeta( fourItemMeta );

        ItemStack eight = new ItemStack( Material.BED );
        eight.setAmount( 8 );
        ItemMeta eightItemMeta = eight.getItemMeta();
        eightItemMeta.setDisplayName( "8 Teams" );
        eight.setItemMeta( eightItemMeta );

        ItemStack choosen = new ItemStack( Material.EMERALD_BLOCK );
        ItemMeta choosenItemMeta = choosen.getItemMeta();
        choosenItemMeta.setDisplayName( ChatColor.GREEN + "Choice" );
        choosen.setItemMeta( choosenItemMeta );

        ItemStack back = new ItemStack( Material.BARRIER );
        ItemMeta backItemMeta = back.getItemMeta();
        backItemMeta.setDisplayName( ChatColor.RED + "Back" );
        backItemMeta.setLore( Arrays.asList( "Click to get back" ) );
        back.setItemMeta( backItemMeta );

        inventory.setItem( 10 , two );      // 10 , 13 , 16
        inventory.setItem( 13 , four );
        inventory.setItem( 16 , eight );

        // 28 , 31 , 34

        if ( getCurrent( player.getWorld() ) == 2 ) {
            inventory.setItem( 28 , choosen );
        } else if ( getCurrent( player.getWorld() ) == 2 ) {
            inventory.setItem( 31 , choosen );
        } else if ( getCurrent( player.getWorld() ) == 8 ) {
            inventory.setItem( 34 , choosen );
        }

        inventory.setItem( 44 , back );

        player.openInventory( inventory );


    }

    private static int getCurrent( World world ) {

        String GENERAL_DATA_FILE;

        GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";
        File data_file = new File( GENERAL_DATA_FILE );
        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            String prefix = Strings.DataFile_Prefix;

            int current = cfg.getInt( prefix + ".TeamAmount" );

            if ( current == 2 ) {
                return current;
            } else if ( current == 4 ) {
            return current;
            } else if ( current == 8 ) {
                return current;
            }
        } else {
            Files.createGeneralDataFile( world );
            return 0;
        }
        return 0;
    }

    // 28 , 31 , 34

    public static void clickTwoTeams( Inventory inventory , Player player ) {
        World world = player.getWorld();

        int current = getCurrent( world );

        if ( current != 2 ) {
            inventory.remove( Material.EMERALD_BLOCK );

            ItemStack choosen = new ItemStack( Material.EMERALD_BLOCK );
            ItemMeta choosenItemMeta = choosen.getItemMeta();
            choosenItemMeta.setDisplayName( ChatColor.GREEN + "Choice" );
            choosen.setItemMeta( choosenItemMeta );

            inventory.setItem( 28 , choosen );

            player.updateInventory();

            setFileToTwo( world );
        }

    }

    public static void clickFourTeams( Inventory inventory, Player player ) {
        World world = player.getWorld();
        int current = getCurrent( world );

        if ( current != 4 ) {
            inventory.remove( Material.EMERALD_BLOCK );

            ItemStack choosen = new ItemStack( Material.EMERALD_BLOCK );
            ItemMeta choosenItemMeta = choosen.getItemMeta();
            choosenItemMeta.setDisplayName( ChatColor.GREEN + "Choice" );
            choosen.setItemMeta( choosenItemMeta );

            inventory.setItem( 31 , choosen );

            player.updateInventory();

            setFileToFour( world );
        }
    }

    public static void clickEightTeams( Inventory inventory, Player player ) {
        World world = player.getWorld();
        int current = getCurrent( world );

        if ( current != 8 ) {
            inventory.remove( Material.EMERALD_BLOCK );

            ItemStack choosen = new ItemStack( Material.EMERALD_BLOCK );
            ItemMeta choosenItemMeta = choosen.getItemMeta();
            choosenItemMeta.setDisplayName( ChatColor.GREEN + "Choice" );
            choosen.setItemMeta( choosenItemMeta );

            inventory.setItem( 34 , choosen );

            player.updateInventory();

            setFileToEight( world );

        }
    }

    private static void setFileToTwo( World world ) {

        String GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";

        File data_file = new File( GENERAL_DATA_FILE );

        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            cfg.set( Strings.DataFile_Prefix + ".TeamAmount" , 2 );
            try {
                cfg.save( data_file );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println( " [ ERROR ] The GeneralData File does not exist." );
        }
    }

    private static void setFileToFour( World world ) {

        String GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";

        File data_file = new File( GENERAL_DATA_FILE );

        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            cfg.set( Strings.DataFile_Prefix + ".TeamAmount" , 4 );
            try {
                cfg.save( data_file );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println( " [ ERROR ] The GeneralData File does not exist." );
        }
    }

    private static void setFileToEight( World world ) {

        String GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";

        File data_file = new File( GENERAL_DATA_FILE );

        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            cfg.set( Strings.DataFile_Prefix + ".TeamAmount" , 8 );
            try {
                cfg.save( data_file );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println( " [ ERROR ] The GeneralData File does not exist." );
        }
    }
}
