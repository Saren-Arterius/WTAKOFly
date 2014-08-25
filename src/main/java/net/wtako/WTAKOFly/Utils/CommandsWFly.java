package net.wtako.WTAKOFly.Utils;

import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Commands.WFly.ArgHelp;
import net.wtako.WTAKOFly.Commands.WFly.ArgReload;
import net.wtako.WTAKOFly.Commands.WFly.WFly;

public enum CommandsWFly implements BaseCommands {

    MAIN_COMMAND(Lang.HELP_WFLY.toString(), WFly.class, Main.artifactId + ".use"),
    WFLY(Lang.HELP_WFLY.toString(), WFly.class, Main.artifactId + ".use"),
    H(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    HELP(Lang.HELP_HELP.toString(), ArgHelp.class, Main.artifactId + ".use"),
    RELOAD(Lang.HELP_RELOAD.toString(), ArgReload.class, Main.artifactId + ".reload");

    private String   helpMessage;
    private Class<?> targetClass;
    private String   permission;

    private CommandsWFly(String helpMessage, Class<?> targetClass, String permission) {
        this.helpMessage = helpMessage;
        this.targetClass = targetClass;
        this.permission = permission;
    }

    @Override
    public String getHelpMessage() {
        return helpMessage;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public String getRequiredPermission() {
        return permission;
    }
}
