package net.aesircraft.InfiniteEnd;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;


public class SpirePopulator extends BlockPopulator
{
	private static final int FLAG_CHANCE = 1; // Out of 200
	private static final int FLAG_HEIGHT = 20;

	@Override
	public void populate(World world, Random random, Chunk source)
	{
		if (random.nextInt(20) <= FLAG_CHANCE)
		{
			int centerX = (source.getX() << 4) + random.nextInt(16);
			int centerZ = (source.getZ() << 4) + random.nextInt(16);
			int centerY = world.getHighestBlockYAt(centerX, centerZ);
			Block top = null;
			Block[] surround = new Block[32];
			int ctr4 = 0;
			boolean safe = true;
			int height=(FLAG_HEIGHT + random.nextInt(10));
			for (int y = centerY; y < centerY + height; y++)
			{
				if (height>63)
					return;
				top = world.getBlockAt(centerX, y, centerZ);
				for (int ycheck = top.getY(); ycheck > -64; ycheck--)
				{
					if (!world.getBlockAt(centerX, ycheck, centerZ).isEmpty())
					{
						safe = true;
						break;
					}
					safe = false;
				}
				if (!safe)
				{
					return;
				}				
				surround[0] = world.getBlockAt(centerX - 1, y, centerZ);
				surround[1] = world.getBlockAt(centerX + 1, y, centerZ);
				surround[2] = world.getBlockAt(centerX + 2, y, centerZ);
				surround[3] = world.getBlockAt(centerX, y, centerZ + 1);
				surround[4] = world.getBlockAt(centerX, y, centerZ + 2);
				surround[5] = world.getBlockAt(centerX, y, centerZ - 1);
				surround[6] = world.getBlockAt(centerX + 1, y, centerZ - 1);
				surround[7] = world.getBlockAt(centerX +1, y, centerZ + 1);
				surround[8] = world.getBlockAt(centerX +2, y, centerZ + 1);
				surround[9] = world.getBlockAt(centerX +1, y, centerZ +2);
				surround[10] = world.getBlockAt(centerX -1, y, centerZ +1);
				
				top.setType(Material.OBSIDIAN);
				if (!(ctr4 > 0))
				{
					for (int ctr2 = y; ctr2 > -64; ctr2--)
					{
						if (world.getBlockAt(centerX, ctr2, centerZ).isEmpty())
						{
							world.getBlockAt(centerX, ctr2, centerZ).setType(Material.OBSIDIAN);
						}
						else
						{
							break;
						}
					}
				}
				
				
				
				
				
				
				
				
				
				for (int ctr = 0; ctr < 11; ctr++)
				{            



					for (int ycheck = surround[ctr].getY(); ycheck > -65; ycheck--)
					{
						if (!world.getBlockAt(surround[ctr].getX(), ycheck, surround[ctr].getZ()).isEmpty())
						{
							safe = true;
							break;
						}
						safe = false;
					}
					if (!safe)
					{
						return;
					}




					






					
					
						Block checker = null;
						for (int ctr6 = surround[ctr].getY(); ctr6 > -64; ctr6--)
						{
							checker = world.getBlockAt(surround[ctr].getX(), ctr6, surround[ctr].getZ());
							if (checker.getTypeId()==0)
							{
								checker.setType(Material.OBSIDIAN);
							}
							else
							{
								break;
							}
						}
						surround[ctr].setType(Material.OBSIDIAN);
					

				}

                



				ctr4++;
			}
			
			if (random.nextInt(30) == 1)
			{
				top.setTypeId(87);
				world.getBlockAt(top.getX(), top.getY()+1, top.getZ()).setTypeId(51);
				Location crystloc=top.getWorld().getBlockAt(top.getX()+1, top.getY()+1, top.getZ()+1).getLocation();
				top.getWorld().spawn(crystloc, org.bukkit.entity.EnderCrystal.class);							
				surround[0] = world.getBlockAt(top.getX() - 1, top.getY(), top.getZ());
				surround[1] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ());
				surround[2] = world.getBlockAt(top.getX() + 2, top.getY(), top.getZ());
				surround[3] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 1);
				surround[4] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 2);
				surround[5] = world.getBlockAt(top.getX(), top.getY(), top.getZ() - 1);
				surround[6] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ() - 1);
				surround[7] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() + 1);
				surround[8] = world.getBlockAt(top.getX() +2, top.getY(), top.getZ() + 1);
				surround[9] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() +2);
				surround[10] = world.getBlockAt(top.getX() -1, top.getY(), top.getZ() +1);
				surround[11] = world.getBlockAt(top.getX() - 1, top.getY(), top.getZ());
				surround[12] = world.getBlockAt(top.getX() + 2, top.getY(), top.getZ());
				surround[13] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 2);
				surround[14] = world.getBlockAt(top.getX(), top.getY(), top.getZ() - 1);
				surround[15] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ() - 1);
				surround[16] = world.getBlockAt(top.getX() +2, top.getY(), top.getZ() + 1);
				surround[17] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() +2);
				surround[18] = world.getBlockAt(top.getX() -1, top.getY(), top.getZ() +1);
				surround[19] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ());
				surround[20] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 1);
				surround[21] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() + 1);
				
				
				for (int nullout=0;nullout<19;nullout++){
					surround[nullout].setTypeId(0);
				}
				for (int nullout=19;nullout<22;nullout++){
					surround[nullout].setTypeId(87);
					world.getBlockAt(surround[nullout].getX(), surround[nullout].getY()+1, surround[nullout].getZ()).setTypeId(51);
				}
			}
			else
			{
				top.setTypeId(87);
				world.getBlockAt(top.getX(), top.getY()+1, top.getZ()).setTypeId(51);
				surround[0] = world.getBlockAt(top.getX() - 1, top.getY(), top.getZ());
				surround[1] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ());
				surround[2] = world.getBlockAt(top.getX() + 2, top.getY(), top.getZ());
				surround[3] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 1);
				surround[4] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 2);
				surround[5] = world.getBlockAt(top.getX(), top.getY(), top.getZ() - 1);
				surround[6] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ() - 1);
				surround[7] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() + 1);
				surround[8] = world.getBlockAt(top.getX() +2, top.getY(), top.getZ() + 1);
				surround[9] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() +2);
				surround[10] = world.getBlockAt(top.getX() -1, top.getY(), top.getZ() +1);
				surround[11] = world.getBlockAt(top.getX() - 1, top.getY(), top.getZ());
				surround[12] = world.getBlockAt(top.getX() + 2, top.getY(), top.getZ());
				surround[13] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 2);
				surround[14] = world.getBlockAt(top.getX(), top.getY(), top.getZ() - 1);
				surround[15] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ() - 1);
				surround[16] = world.getBlockAt(top.getX() +2, top.getY(), top.getZ() + 1);
				surround[17] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() +2);
				surround[18] = world.getBlockAt(top.getX() -1, top.getY(), top.getZ() +1);
				surround[19] = world.getBlockAt(top.getX() + 1, top.getY(), top.getZ());
				surround[20] = world.getBlockAt(top.getX(), top.getY(), top.getZ() + 1);
				surround[21] = world.getBlockAt(top.getX() +1, top.getY(), top.getZ() + 1);
				
				
				for (int nullout=0;nullout<19;nullout++){
					surround[nullout].setTypeId(0);
				}
				for (int nullout=19;nullout<22;nullout++){
					surround[nullout].setTypeId(87);
					world.getBlockAt(surround[nullout].getX(), surround[nullout].getY()+1, surround[nullout].getZ()).setTypeId(51);
				}
			}			

		}
	}
}
