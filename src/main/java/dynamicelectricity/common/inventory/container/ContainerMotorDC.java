package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import voltaic.prefab.inventory.container.slot.item.type.SlotFluid;
import voltaic.prefab.inventory.container.slot.item.type.SlotRestricted;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;
import voltaic.prefab.utilities.math.Color;

public class ContainerMotorDC extends GenericContainerBlockEntity<TileMotorDC> {

	public ContainerMotorDC(int id, PlayerInventory playerinv) {
		this(id, playerinv, new Inventory(2), new IntArray(3));
    }
	
	public ContainerMotorDC(int id, PlayerInventory playerinv, IInventory inventory, IIntArray inventorydata) {
		super(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(IInventory inv, PlayerInventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 101, 33).setRestriction(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get()).setIOColor(new Color(0, 240, 255, 255)));
		addSlot(new SlotFluid(inv, nextIndex(), 129, 33));
	}

}
