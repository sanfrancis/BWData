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

        inventory.setItem( 31 , choosen );  // 28 , 31 , 34

        inventory.setItem( 44 , back );

        player.openInventory( inventory );


    }

    private static int getCurrent( World world , Inventory inventory ) {

        String GENERAL_DATA_FILE;

        GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";
        File data_file = new File( GENERAL_DATA_FILE );
        if ( data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            String prefix = Strings.DataFile_Prefix;

            int current = cfg.getInt( prefix + ".TeamAmount" );

            if ( current == 2 ) {
                if( inventory.getItem( 28 ).getAmount() == 2 ) {
                    return current;
                } else {
                    return 0;
                }
            } else if ( current == 4 ) {
                if ( inventory.getItem( 31 ).getAmount() ==4 ) {
                    return current;
                } else {
                    return 0;
                }
            } else if ( current == 8 ) {
                if ( inventory.getItem( 34 ).getAmount() == 8 ) {
                    return current;
                } else {
                    return 0;
                }
            }
        } else {
            Files.createGeneralDataFile( world );
            return 0;
        }
        return 0;
    }







    // 28 , 31 , 34

    public static void clickTwoTeams( Inventory inventory, World world ) {
        int current = getCurrent( world , inventory );
        if ( current == 2 ) {
            System.out.println( "Idiot" );
        } else if ( current == 4 ) {
            ItemStack choiceItem =inventory.getItem( 31 );
            inventory.remove( choiceItem );
            inventory.setItem( 28 , choiceItem );
            setFileToTwo( world );
        } else if ( current == 8 ) {
            ItemStack choiceItem =inventory.getItem( 34 );
            inventory.remove( choiceItem );
            inventory.setItem( 28 , choiceItem );
            setFileToTwo( world );
        }
    }

    public static void clickFourTeams( Inventory inventory, World world ) {
        int current = getCurrent( world , inventory );
        if ( current == 4 ) {
            System.out.println( "Idiot" );
        } else if ( current== 2 ) {
            ItemStack choiceItem =inventory.getItem( 31 );
            inventory.remove( choiceItem );
            inventory.setItem( 28 , choiceItem );
            setFileToFour( world );
        } else if ( current == 8 ) {
            ItemStack choiceItem =inventory.getItem( 34 );
            inventory.remove( choiceItem );
            inventory.setItem( 31 , choiceItem );
            setFileToFour( world );
        }
    }

    public static void clickEightTeams( Inventory inventory, World world ) {
        int current = getCurrent( world , inventory );
        if ( current == 8 ) {
            System.out.println( "Idiot" );
        } else if ( current== 2 ) {
            ItemStack choiceItem =inventory.getItem( 28 );
            inventory.remove( choiceItem );
            inventory.setItem( 34 , choiceItem );
            setFileToEight( world );
        } else if ( current == 5 ) {
            ItemStack choiceItem =inventory.getItem( 31 );
            inventory.remove( choiceItem );
            inventory.setItem( 34 , choiceItem );
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
