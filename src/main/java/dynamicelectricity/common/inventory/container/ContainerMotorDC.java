package dynamicelectricity.common.inventory.container;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.prefab.inventory.container.GenericContainer;
import electrodynamics.prefab.inventory.container.slot.SlotRestricted;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class ContainerMotorDC extends GenericContainer<TileMotorDC>{

	public ContainerMotorDC(int id, PlayerInventory playerinv) {
		this(id, playerinv, new Inventory(5), new IntArray(3));
    }
	
	public ContainerMotorDC(int id, PlayerInventory playerinv, IInventory inventory, IIntArray inventorydata) {
		super(DeferredRegisters.CONTAINER_MOTORDC.get(), id, playerinv, inventory, inventorydata);
	}
	
	public ContainerMotorDC(ContainerType<?> type, int id, PlayerInventory playerinv, IInventory inventory, IIntArray inventorydata) {
		super(type, id, playerinv, inventory, inventorydata);
    }

	@Override
	public void addInventorySlots(IInventory inv, PlayerInventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 100, 33, DeferredRegisters.ITEM_CONDUCTORBRUSH.get()));
		addSlot(new SlotRestricted(inv, nextIndex(), 118, 33, DeferredRegisters.ITEM_GELLUBRICANT.get()));
		
	}

}
