package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import voltaic.prefab.inventory.container.slot.item.type.SlotFluid;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;

public class ContainerMotorAC extends GenericContainerBlockEntity<TileMotorAC> {
	
	public ContainerMotorAC(int id, PlayerInventory playerinv) {
		this(id, playerinv, new Inventory(1), new IntArray(5));
    }
	
	public ContainerMotorAC(int id, PlayerInventory playerinv, IInventory inventory, IIntArray inventorydata) {
		super(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(IInventory inv, PlayerInventory playerInv) {
		addSlot(new SlotFluid(inv, nextIndex(), 101, 33));
	}

}
