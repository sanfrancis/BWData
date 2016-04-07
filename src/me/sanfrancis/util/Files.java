package me.sanfrancis.util;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    public static void createSpawnsFile( World world , int teamAmount ) {

        //TEAMS: Black   , green, orange , blue , aqua   , red , pink , yellow
        // Teams 2: RED , BLUE
        // Teams 4: RED , BLUE , GREEN , YELLOW
        // Teams 8: RED , BLUE , GREEN , YELLOW , BLACK , PINK , ORANGE , AQUA


        SPAWNS_FILE = world.getWorldFolder().getAbsolutePath() + "/BWData" + "/" + "Spawns" + ".yml";
        File spawn_file = new File( SPAWNS_FILE );
        if ( spawn_file.exists() ) {
            spawn_file.delete();
            switch ( teamAmount ) {
                case 2:
                    List< String > twoTeamsList = Arrays.asList( "RED", "BLUE" );
                    for ( String prefix : twoTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                case 4:
                    List< String > fourTeamsList = Arrays.asList( "RED", "BLUE" , "GREEN" , "YELLOW" );
                    for ( String prefix : fourTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                case 8:
                    List< String > eightTeamsList = Arrays.asList( "RED", "BLUE" , "GREEN" , "YELLOW" , "BLACK" , "PINK" , "ORANGE" , "AQUA" );
                    for ( String prefix : eightTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                default:
                    System.out.println( "Impossible Team amount. Using 4 teams now." );
                    List< String > defualtList = Arrays.asList( "RED", "BLUE" , "GREEN" , "YELLOW" );
                    for ( String prefix : defualtList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
            }

            // Spawn file does not exist

        } else if ( !spawn_file.exists() ) {
            switch ( teamAmount ) {
                case 2:
                    List< String > twoTeamsList = Arrays.asList( "RED", "BLUE" );
                    for ( String prefix : twoTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                case 4:
                    List< String > fourTeamsList = Arrays.asList( "RED", "BLUE", "GREEN", "YELLOW" );
                    for ( String prefix : fourTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                case 8:
                    List< String > eightTeamsList = Arrays.asList( "RED", "BLUE", "GREEN", "YELLOW", "BLACK", "PINK", "ORANGE", "AQUA" );
                    for ( String prefix : eightTeamsList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;
                default:
                    System.out.println( "Impossible Team amount. Using 4 teams now." );
                    List< String > defualtList = Arrays.asList( "RED", "BLUE", "GREEN", "YELLOW" );
                    for ( String prefix : defualtList ) {
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( spawn_file );
                        cfg.set( prefix + ".X", 0 );
                        cfg.set( prefix + ".Y", 0 );
                        cfg.set( prefix + ".Z", 0 );
                        cfg.set( prefix + ".Yaw", 0 );
                        cfg.set( prefix + ".Pitch", 0 );
                        cfg.set( prefix + ".World", world.getName( ) );

                        try {
                            cfg.save( spawn_file );
                        } catch ( IOException e ) {
                            e.printStackTrace( );
                        }
                    }
                    break;

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
