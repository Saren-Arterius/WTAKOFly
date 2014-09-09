package net.wtako.WTAKOFly;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.wtako.WTAKOFly.Commands.CommandWFly;
import net.wtako.WTAKOFly.EventHandlers.FactionListener;
import net.wtako.WTAKOFly.EventHandlers.PlayerProtectListener;
import net.wtako.WTAKOFly.Methods.FlyManager;
import net.wtako.WTAKOFly.Schedulers.ExpReducingTask;
import net.wtako.WTAKOFly.Utils.Config;
import net.wtako.WTAKOFly.Utils.Lang;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main             instance;
    public static String            artifactId;
    public static YamlConfiguration LANG;
    public static File              LANG_FILE;
    public static Logger            log = Logger.getLogger("WTAKOFly");

    @Override
    public void onEnable() {
        Main.instance = this;
        Main.artifactId = getProperty("artifactId");
        getCommand(getProperty("mainCommand")).setExecutor(new CommandWFly());
        Config.saveAll();
        loadLang();
        if (ExpReducingTask.getInstance() == null) {
            new ExpReducingTask();
        }
        getServer().getPluginManager().registerEvents(new FactionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerProtectListener(), this);
    }

    @Override
    public void onDisable() {
        for (final Player flyingPlayer: new ArrayList<Player>(FlyManager.getAllFlyingPlayers())) {
            FlyManager.setNotFly(flyingPlayer);
        }
    }

    @SuppressWarnings("deprecation")
    public void loadLang() {
        final File lang = new File(getDataFolder(), "messages.yml");
        if (!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                final InputStream defConfigStream = getResource("messages.yml");
                if (defConfigStream != null) {
                    final YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                    defConfig.save(lang);
                    Lang.setFile(defConfig);
                    return;
                }
            } catch (final IOException e) {
                e.printStackTrace(); // So they notice
                Main.log.severe("[" + Main.getInstance().getName() + "] Couldn't create language file.");
                Main.log.severe("[" + Main.getInstance().getName() + "] This is a fatal error. Now disabling");
                setEnabled(false); // Without it loaded, we can't send them
                // messages
            }
        }
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for (final Lang item: Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(conf);
        Main.LANG = conf;
        Main.LANG_FILE = lang;
        try {
            conf.save(getLangFile());
        } catch (final IOException e) {
            Main.log.log(Level.WARNING, "[" + Main.getInstance().getName() + "] Failed to save messages.yml.");
            Main.log.log(Level.WARNING, "[" + Main.getInstance().getName() + "] Report this stack trace to "
                    + getProperty("author") + ".");
            e.printStackTrace();
        }
    }

    /**
     * Gets the messages.yml config.
     * 
     * @return The messages.yml config.
     */
    public YamlConfiguration getLang() {
        return Main.LANG;
    }

    /**
     * Get the messages.yml file.
     * 
     * @return The messages.yml file.
     */
    public File getLangFile() {
        return Main.LANG_FILE;
    }

    @SuppressWarnings("deprecation")
    public String getProperty(String key) {
        final YamlConfiguration spawnConfig = YamlConfiguration.loadConfiguration(getResource("plugin.yml"));
        return spawnConfig.getString(key);
    }

    public static Main getInstance() {
        return Main.instance;
    }

}
