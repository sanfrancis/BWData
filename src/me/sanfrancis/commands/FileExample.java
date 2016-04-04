package me.sanfrancis.commands;

/**
 * Created by Max on 30.03.16.
 */
public class FileExample {

    /*

    package me.sanfrancis.commands;

import me.sanfrancis.util.Strings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Max on 27.10.15.
 *
 *
 */

    /*

    public class HomeCommand implements CommandExecutor {

        public static String HOME_DIR = new String( "plugins//CowboysAndIndians" + "/homes" );

        @Override
        public boolean onCommand ( CommandSender sender, Command command, String s, String[] arguments ) {

            if( sender instanceof ConsoleCommandSender ) {
                System.out.println( "Only Players can use this command!" );
                return true;
            }

            Player player = (Player) sender;
            int homes = getHomes( player.getName() ).size();

            if ( command.getName( ).equalsIgnoreCase( "home" ) ) {
                if ( arguments.length == 0 ) {
                    if ( homes == 0 ) {
                        player.sendMessage( Strings.HOMES + "You dont have any homes." );
                    } else {
                        player.sendMessage( Strings.HOMES + "You have the following homes: " + ChatColor.GOLD + getHomes( player.getName() ) );
                    }
                } else if ( arguments.length == 1 ) {
                    String home_name = arguments[0];
                    teleportToHome( player, home_name );
                    //message in function, otherwise buggy
                } else {
                    player.sendMessage( ChatColor.RED + "/home <Name>" );
                }

            } else if ( command.getName( ).equalsIgnoreCase( "sethome" ) ) {

                if( homes > 2 && !player.hasPermission( "CaI.homes.multiple" )) {
                    player.sendMessage( Strings.ERROR + "You have too much homes. Delete one to set a new one!" );
                } else if ( arguments.length == 1 ) {
                    String home_name = arguments[ 0 ];
                    sethome( player , home_name );
                } else {
                    player.sendMessage( ChatColor.RED + "/sethome <Name>" );
                }

            } else if ( command.getName( ).equalsIgnoreCase( "delhome" ) ) {
                if ( arguments.length == 1 ) {
                    String home_name = arguments[0];
                    delhome( player , home_name );
                } else {
                    player.sendMessage( ChatColor.RED + "/delhome <Name>" );
                }
            }

            return true;
        }

        public static void sethome( Player player , String home_name) {
            if ( player == null ) {
                return;
            } else {
                File home_file = new File( HOME_DIR + "/" + player.getName() + ".yml" );
                if( home_file.exists () ) {
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration( home_file );

                    Location location = player.getLocation( );

                    cfg.set( home_name + ".X", location.getBlockX( ) );
                    cfg.set( home_name + ".Y", location.getBlockY( ) );
                    cfg.set( home_name + ".Z", location.getBlockZ( ) );
                    cfg.set( home_name + ".Yaw", location.getYaw( ) );
                    cfg.set( home_name + ".Pitch", location.getPitch( ) );
                    cfg.set( home_name + ".World", location.getWorld( ).getName().toString() );

                    try {
                        cfg.save( home_file );
                        player.sendMessage( Strings.HOMES + "Your home " + ChatColor.GOLD + home_name + ChatColor.RESET + " was set!" );
                    } catch ( IOException e ) {
                        e.printStackTrace( );

                    }
                } else {

                    try {
                        home_file.createNewFile( );
                        YamlConfiguration cfg = YamlConfiguration.loadConfiguration( home_file );

                        Location location = player.getLocation();

                        cfg.set( home_name + ".X" , location.getBlockX( ) );
                        cfg.set( home_name + ".Y" , location.getBlockY( ) );
                        cfg.set( home_name + ".Z" , location.getBlockZ( ) );
                        cfg.set( home_name + ".Yaw", location.getYaw( ) );
                        cfg.set( home_name + ".Pitch", location.getPitch( ) );
                        cfg.set( home_name + ".World", location.getWorld( ) );

                        cfg.save( home_file );
                        player.sendMessage( Strings.HOMES + "Your home " + ChatColor.GOLD + home_name + ChatColor.RESET + " was set!" );
                    } catch ( IOException exception ) {
                        exception.printStackTrace( );
                        player.sendMessage( Strings.ERROR + "Your Home could not be set!" );
                    }
                }
            }

        }

        public static void delhome ( Player player , String home_name ) {
            File file = new File( HOME_DIR + "/" + player.getName() + ".yml" );
            if(file.exists()) {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration( file );
                Set<String> set = cfg.getKeys( false );

                for ( String name : set  ) {
                    if ( name.equalsIgnoreCase( home_name ) ) {
                        cfg.set( name, null );
                        player.sendMessage( Strings.HOMES + "Your home " + ChatColor.GOLD + home_name + ChatColor.RESET + " was deleted!" );
                        break;
                    }
                }

                try {
                    cfg.save( file );
                } catch ( IOException exception ) {
                    exception.printStackTrace();
                    player.sendMessage( Strings.ERROR + "Your Home could not be deleted!" );
                }

            } else {
                player.sendMessage( Strings.ERROR + "You dont have any Homes" );

            }
        }

        public static void teleportToHome ( Player player , String home_name ) {
            File file = new File( HOME_DIR + "/" + player.getName() + ".yml" );
            if ( file.exists() ) {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration( file );
                for ( String name : cfg.getKeys( false ) ) {
                    if ( name.equalsIgnoreCase( home_name ) ) {

                        double x = cfg.getDouble( name + ".X" );
                        double y = cfg.getDouble( name + ".Y" );
                        double z = cfg.getDouble( name + ".Z" );
                        float yaw = ( float ) cfg.getDouble( name + ".Yaw" );
                        float pitch = ( float ) cfg.getDouble( name + ".Pitch" );
                        String world = cfg.getString( name + ".World" );
                        Location new_location = new Location( Bukkit.getWorld( world ), x, y, z, yaw, pitch );

                        player.teleport( new_location );
                        player.sendMessage( Strings.HOMES + " You were teleported to your home" + ChatColor.GOLD + home_name );
                    }
                }
            } else {
                player.sendMessage( Strings.ERROR + "You dont have a home named " + ChatColor.GOLD + home_name );
            }

        }

        public static Set<String> getHomes ( String player ) {
            File file = new File( HOME_DIR + "/" + player + ".yml" );
            if(file.exists()) {
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration( file );
                return cfg.getKeys( false );
            } else {
                return new HashSet<String>(  );
            }

        }

        public static void checkHomeFolder() {
            File dir = new File( HOME_DIR );
            if ( !dir.exists() ) {
                dir.mkdir();
            }
        }
    }

     */
































    /*

    public void ChickenSpawnStick() {

        ItemStack result = new ItemStack( Material.STICK );
        result.setAmount( 1 );
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName( "SpawnChicken" );
        result.setItemMeta( meta );

        ShapedRecipe r = new ShapedRecipe( result );

        r.shape( "AAA", "ASA", "AFA" );
        r.setIngredient( 'S', Material.STICK );
        r.setIngredient( 'F', Material.FEATHER );

        this.getServer().addRecipe( r );

        //Methode abrufen
    }

    */

}
