package simplexity.simplepronouns.commands.subcommands;

import org.bukkit.command.TabExecutor;

public abstract class SubCommand implements TabExecutor {
    
    private final String permission;
    private final String label;
    private final String helpMessage;
    
    public SubCommand(String permission, String label, String helpMessage) {
        this.permission = permission;
        this.label = label;
        this.helpMessage = helpMessage;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getHelpMessage() {
        return helpMessage;
    }
    
}
