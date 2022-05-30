package xyz.lightning.hungergames.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.lightning.hungergames.hungergames.party.*;
import xyz.lightning.hungergames.hungergames.support.PlaceholderAPIHook;

public final class HungerGames extends JavaPlugin {

    private static Party party = new NoParty();

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Java Version check
        String Version = System.getProperty("java.version");
        if (Version.contains("1.8")) {
            Bukkit.getLogger().info("Your Java Version is " + Version + "     This plugin requires Java 11 or higher");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Version = Version.substring(0, 2);
        double version = Double.parseDouble(Version);

        if (version < 17) {
            Bukkit.getLogger().info("Your Java Version is " + System.getProperty("java.version") + " This plugin requires Java 11 or higher");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else if (version >= 17) {
            Bukkit.getLogger().info("Your Java Version is " + System.getProperty("java.version") + " This plugin is compatible with your version");
        }





        //Party Support by JT122406
        if (getServer().getPluginManager().isPluginEnabled("Spigot-Party-API-PAF")){
            getLogger().info("Hook into Spigot Party API for Party and Friends Extended (by Simonsator) support!");
            party = new PAFBungee();
        } else if (getServer().getPluginManager().isPluginEnabled("PartyAndFriends")) {
            getLogger().info("Hook into Party and Friends for Spigot (by Simonsator) support!");
            party = new PAFSpigot();
        } else if (getServer().getPluginManager().isPluginEnabled("Parties")) {
            getLogger().info("Hook into Parties (by AlessioDP) support!");
            party = new Parties();
        }

        //PlaceHolderAPI
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().info("Hook into PlaceholderAPI (by PlaceholderAPI) support!");
            new PlaceholderAPIHook();
        }else {
           getLogger().info("PlaceholderAPI not found! PlaceholderAPI support disabled!");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Party getParty(){return party;}
}
