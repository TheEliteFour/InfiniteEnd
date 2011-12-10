
package net.aesircraft.InfiniteEnd;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class playerListener extends PlayerListener
{
	public static InfiniteEnd plugin;

	public playerListener(InfiniteEnd instance)
	{
		plugin = instance;
	}
	@Override
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player=event.getPlayer();
		World world=player.getWorld();
		if (event.getClickedBlock() == null)
			return;
		Block block=event.getClickedBlock();
		if (event.getClickedBlock().isEmpty())
			return;
		if (block.getTypeId() == 122){
			event.setCancelled(true);
			BlockBreakEvent bevent= new BlockBreakEvent(block,player);
			Bukkit.getServer().getPluginManager().callEvent(bevent);
			if (!bevent.isCancelled()){
			world.playEffect(block.getLocation(), Effect.SMOKE, 20);			
			ItemStack items=new ItemStack(122,1);
			block.setTypeId(0);
			world.dropItem(block.getLocation(), items);
			}
		}
		Location point=block.getLocation();
		ItemStack iteminhand=player.getItemInHand();
		if (!world.getName().toLowerCase().equals("world_the_end"))
			return;
		if (block.getTypeId()!=41){
			return;
		}
		if (iteminhand.getTypeId()!=369)
			return;
		block=world.getBlockAt(block.getX(), block.getY()-1, block.getZ());
		if (block.getTypeId()!=41 && block.getTypeId()!=52)
			return;
		Block[] blocks=new Block[5];
		if (block.getTypeId()==41){
			blocks[0]=world.getBlockAt(block.getX(), block.getY()-1, block.getZ());
			blocks[1]=world.getBlockAt(block.getX()+1, block.getY()-1, block.getZ());
			blocks[2]=world.getBlockAt(block.getX()-1, block.getY()-1, block.getZ());
			blocks[3]=world.getBlockAt(block.getX(), block.getY()-1, block.getZ()+1);
			blocks[4]=world.getBlockAt(block.getX(), block.getY()-1, block.getZ()-1);
		}
		if (block.getTypeId()==52){
			blocks[0]=world.getBlockAt(block.getX(), block.getY(), block.getZ());
			blocks[1]=world.getBlockAt(block.getX()+1, block.getY(), block.getZ());
			blocks[2]=world.getBlockAt(block.getX()-1, block.getY(), block.getZ());
			blocks[3]=world.getBlockAt(block.getX(), block.getY(), block.getZ()+1);
			blocks[4]=world.getBlockAt(block.getX(), block.getY(), block.getZ()-1);
		}
		if (blocks[0].getTypeId()!=52){
			return;
		}
		if (blocks[1].getTypeId()!=49){
			return;
		}
		if (blocks[2].getTypeId()!=49){
			return;
		}
		if (blocks[3].getTypeId()!=49){
			return;
		}
		if (blocks[4].getTypeId()!=49){
			return;
		}
		List<Entity> entities = world.getEntities();
		for (int ctr=0;ctr<entities.size();ctr++){
			if (entities.get(ctr) instanceof org.bukkit.entity.EnderDragon){
				return;
			}
		}
			
		ItemStack sac=new ItemStack(369,1);
		InventoryWorkaround.removeItem(player.getInventory(), false, sac);
		player.damage(1);
		Location loc=new Location(player.getWorld(),point.getBlockX(),point.getBlockY()+50,point.getBlockZ());
		world.strikeLightningEffect(point);
		world.playEffect(point, Effect.GHAST_SHRIEK, 10);
		world.playEffect(point, Effect.SMOKE, 50);
		player.sendMessage("§2Your sacrifice has give rise to a Dragon of Ender!");
		world.spawnCreature(loc, CreatureType.ENDER_DRAGON);
		
	
	}
}
