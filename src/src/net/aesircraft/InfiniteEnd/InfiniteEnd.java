/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aesircraft.InfiniteEnd;


import org.bukkit.event.Event;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InfiniteEnd extends JavaPlugin {
	private final playerListener playerListener = new playerListener(this);
	private final creatureListener creatureListener = new creatureListener(this);

	@Override
    public void onDisable() {
    }
    
	@Override
    public void onEnable() {
        PluginDescriptionFile desc = this.getDescription();

        System.out.println( desc.getName() + " version " + desc.getVersion() + " is enabled!" );
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.CREATURE_SPAWN, creatureListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, creatureListener, Event.Priority.Lowest, this);

    }
    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new InfiniteEndGenerator();
    }
	
}