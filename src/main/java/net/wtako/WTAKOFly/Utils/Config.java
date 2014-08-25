package net.wtako.WTAKOFly.Utils;

import java.util.Arrays;
import java.util.List;

import net.wtako.WTAKOFly.Main;

import org.bukkit.configuration.file.FileConfiguration;

public enum Config {

    ALLOWED_WORLDS("flying.worlds.allowed", Arrays.asList("world", "world_nether", "world_the_end")),
    BUILD_FACTION_COST_FACTOR("faction.build-faction-flying-cost-factor", 0),
    REVERSED_ALLOWED_WORLDS("flying.worlds.reverse", false),
    EXP_COST_PER_SECOND("flying.exp-cost-per-second", 0.5),
    TICK_INTERVAL("flying.tick-interval", 1),
    FLY_START_MIN_EXP("flying.start.min-exp", 60),
    FLY_START_COST("flying.start.exp-cost", 10);

    private String path;
    private Object value;

    Config(String path, Object var) {
        this.path = path;
        final FileConfiguration config = Main.getInstance().getConfig();
        if (config.contains(path)) {
            value = config.get(path);
        } else {
            value = var;
        }
    }

    public Object getValue() {
        return value;
    }

    public boolean getBoolean() {
        return (boolean) value;
    }

    public String getString() {
        return (String) value;
    }

    public int getInt() {
        if (value instanceof Double) {
            return ((Double) value).intValue();
        }
        return (int) value;
    }

    public long getLong() {
        return Integer.valueOf(getInt()).longValue();
    }

    public double getDouble() {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        return (double) value;
    }

    public String getPath() {
        return path;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getValues() {
        return (List<Object>) value;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStrings() {
        return (List<String>) value;
    }

    public static void saveAll() {
        final FileConfiguration config = Main.getInstance().getConfig();
        for (final Config setting: Config.values()) {
            if (!config.contains(setting.getPath())) {
                config.set(setting.getPath(), setting.getValue());
            }
        }
        Main.getInstance().saveConfig();
    }

}