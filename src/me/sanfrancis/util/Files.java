package me.sanfrancis.util;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 30.03.16.
 */
public class Files {

    public static String BWDATA_DIR;
    public static String GENERAL_DATA_FILE;
    public static String SPAWNS_FILE;
    public static String BRONZE_DIR;
    public static String IRON_DIR;
    public static String GOLD_DIR;

    public static void createBWDataDir( World world ) {

        BWDATA_DIR = world.getWorldFolder().getPath() + "/BWData";
        File dir = new File( BWDATA_DIR );

        if ( !dir.exists() ) {
            dir.mkdir();
        }
    }

    public static void createGeneralDataFile( World world ) {

        GENERAL_DATA_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "GeneralData" + ".yml";
        File data_file = new File( GENERAL_DATA_FILE );
        if ( !data_file.exists() ) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration( data_file );

            String prefix = Strings.DataFile_Prefix;

            cfg.set( prefix + ".TeamAmount" , 4 );
            cfg.set( prefix + ".MembersPerTeam" , 4 );
            cfg.set( prefix + ".Time" , "DAY" );

            try {
                cfg.save( data_file );
                data_file.createNewFile();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }

    }

    public static void createSpawnsFile( World world ) {

        SPAWNS_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "Spawns" + ".yml";
        File spawn_file = new File( SPAWNS_FILE );
        if ( !spawn_file.exists() ) {
            try {
                spawn_file.createNewFile();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public static void createBronzeDir( World world ) {
        BRONZE_DIR = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "Bronze";
        File bronze_dir = new File( BRONZE_DIR );

        if ( !bronze_dir.exists() ) {
            bronze_dir.mkdir();
        }
    }

    public static void createIronDir( World world ){
        IRON_DIR = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "Iron";
        File iron_dir = new File( IRON_DIR );

        if ( !iron_dir.exists() ) {
            iron_dir.mkdir();
        }
    }

    public static void createGoldDir( World world ) {
        GOLD_DIR = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "Gold";
        File gold_dir = new File( GOLD_DIR );

        if ( !gold_dir.exists() ) {
            gold_dir.mkdir();
        }
    }
}
