package xyz.lightning.hungergames.hungergames;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.lightning.hungergames.hungergames.party.*;

public final class HungerGames extends JavaPlugin {

    private static Party party = new NoParty();

    @Override
    public void onEnable() {
        // Plugin startup logic

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


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
