
package net.aesircraft.InfiniteEnd;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class creatureListener extends EntityListener
{
	public static InfiniteEnd plugin;

	public creatureListener(InfiniteEnd instance)
	{
		plugin = instance;
	}
	@Override
	public void onEntityDeath(EntityDeathEvent event)
	{
		Random random=new Random((event.getEntity().getTicksLived() + event.getDroppedExp()));
		if (event.getEntity() instanceof EnderDragon){
			ItemStack[] items = new ItemStack[3];			
			items[0]=new ItemStack(122,1);
			int count=random.nextInt(5);
			if (count>0){
			items[1]=new ItemStack(51,count);
			event.getDrops().add(items[1]);
			}
			int count2=random.nextInt(3);
			if (count2>0){
			items[2]=new ItemStack(264,count2);
			event.getDrops().add(items[2]);
			}
			event.getDrops().add(items[0]);
				
				
		}
		if (event.getEntity() instanceof MagmaCube){
			ItemStack item=new ItemStack(378,1);			
			if (random.nextInt(200)<=1){
			event.getDrops().add(item);
		}
		}
	}
	
	@Override
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		World world=event.getLocation().getWorld();
		CreatureType type=event.getCreatureType();
		CreatureType slime=CreatureType.SLIME;
		CreatureType magma=CreatureType.MAGMA_CUBE;
		CreatureType ender=CreatureType.ENDERMAN;
		if (!world.getName().toLowerCase().equals("world_the_end")){
			return;
		}
		if (type!=ender){
			return;
		}
		Random random=new Random();
		if (random.nextInt(100)<35){
			event.setCancelled(true);
			return;
		}
		List players=world.getPlayers();
		Player player=(Player) players.get(random.nextInt(players.size()));
		int chancex=1;
		int chancez=1;
		int chancey=1;
		if (random.nextInt(1)==1){
			chancex=-1;
		}
		if (random.nextInt(1)==1){
			chancez=-1;
		}
		int x=player.getLocation().getBlockX()+((random.nextInt(15)+5)*chancex);
		int z=player.getLocation().getBlockZ()+((random.nextInt(15)+5)*chancez);
				
		Location loc=new Location(world,x,player.getLocation().getY(),z);
		int y=loc.getBlockY();
		if (world.getBlockAt(new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY()-1,loc.getBlockZ())).isEmpty()){
			boolean bool=false;
			int hc=0;
			while (bool==false){
			hc++;
			loc=new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY()-hc,loc.getBlockZ());
			if (!world.getBlockAt(loc).isEmpty()){
				y=loc.getBlockY();
				bool=true;
			}
			if (hc<-63){
				if (!world.getHighestBlockAt(loc).isEmpty()){
				y=world.getHighestBlockAt(loc).getY();
				}
				else{
					return;
				}
				bool=true;				
			}
		}			
		}
		loc=new Location(loc.getWorld(),loc.getBlockX(),y+4,loc.getBlockZ());
		if (loc.getBlock().getLightLevel()>=5){
			event.setCancelled(true);
			return;
		}
		if (random.nextInt(150)<=1){
			event.setCancelled(true);
			if (random.nextInt(3)==1){
				world.spawnCreature(loc, magma);
			}
			else{
			world.spawnCreature(loc, slime);
			}
			return;
		}
		event.getLocation().setX(loc.getBlockX());
		event.getLocation().setX(loc.getBlockY());
		event.getLocation().setX(loc.getBlockZ());
		return;
	
	}
}
