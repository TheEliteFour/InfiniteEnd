package net.aesircraft.InfiniteEnd;

import java.util.Random;
import net.minecraft.server.Block;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public class EndOrePopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk){
		int radius=5;
		int amount=random.nextInt(25);
		int amountLow=random.nextInt(10);
		int chance=random.nextInt(100);
		int spawn=random.nextInt(100);
		if (chance>40){
			return;
		}		
		Material gold=Material.GOLD_ORE;
		Material netherrack=Material.NETHERRACK;
		Material netherbrick=Material.NETHER_BRICK;
		Material obsidian=Material.OBSIDIAN;
		Material diamond=Material.DIAMOND_ORE;
		Material redstone=Material.REDSTONE_ORE;
		Material lava=Material.STATIONARY_LAVA;
		Material glow=Material.GLOWSTONE;
		Material sponge=Material.SPONGE;
		Material material=sponge;
		int amn=amount;
		if (spawn<=50){
			material=obsidian;
			amn=amountLow;
		}
		if (spawn<=35){
			material=netherrack;
		}
		if (spawn<=28){
			material=netherbrick;
		}
		if (spawn<=25){
			material=lava;
		}
		if (spawn<=20){
			material=glow;
			amn=amountLow;
		}
		if (spawn<=15){
			material=redstone;
			amn=amountLow;
		}
		if (spawn<=10){
			material=gold;
			amn=amountLow;
		}
		if (spawn<=5){
			material=diamond;
			amn=amountLow;
		}
		
		
		
		int centerX = (chunk.getX() << 4) + random.nextInt(16);
        int centerZ = (chunk.getZ() << 4) + random.nextInt(16);
        int centerY = random.nextInt(50);
		if (centerY<44 && random.nextInt(1)==1){
			centerY=-centerY;
		}
		
        Vector center = new BlockVector(centerX, centerY, centerZ);
		
		
		
		
		
		for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Vector position = center.clone().add(new Vector(x, y, z));

                        if (center.distance(position) <= radius + 0.5) {						
						   if (world.getBlockAt(position.toLocation(world)).getTypeId()==0 ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.GLOWSTONE.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.OBSIDIAN.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.DIAMOND_ORE.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.SPONGE.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.NETHER_BRICK.id ||								   
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.NETHERRACK.id ||								   
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.STATIONARY_LAVA.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.REDSTONE_ORE.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.LAVA.id ||
								   world.getBlockAt(position.toLocation(world)).getTypeId()==Block.GOLD_ORE.id)
							   return;
						
						}
						
                    }
					
                }				
            }
		
		
		
		
		
		
		
		
		
		
		
		for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Vector position = center.clone().add(new Vector(x, y, z));

                        if (center.distance(position) <= radius + 0.5) {
						if (material==lava){
							 world.getBlockAt(position.toLocation(world)).setType(material);
						}
						else{
						if (random.nextInt(100)<=amn){
						   world.getBlockAt(position.toLocation(world)).setType(material);
						}
						}
						
						}
						
                    }
					
                }				
            }
		
		
		
		
		
		
		
		
		
				
	}

}
