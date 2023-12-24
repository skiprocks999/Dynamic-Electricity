package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.prefab.inventory.container.GenericContainerBlockEntity;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotRestricted;
import electrodynamics.prefab.utilities.math.Color;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class ContainerMotorDC extends GenericContainerBlockEntity<TileMotorDC>{

	public ContainerMotorDC(int id, PlayerInventory playerinv) {
		this(id, playerinv, new Inventory(5), new IntArray(3));
    }
	
	public ContainerMotorDC(int id, PlayerInventory playerinv, IInventory inventory, IIntArray inventorydata) {
		super(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(IInventory inv, PlayerInventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 100, 33).setRestriction(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get()).setIOColor(new Color(0, 240, 255, 255)));
		addSlot(new SlotRestricted(inv, nextIndex(), 118, 33).setRestriction(DynamicElectricityItems.ITEM_LUBRICANTGEL.get()).setIOColor(new Color(0, 240, 255, 255)));
		
	}

}
