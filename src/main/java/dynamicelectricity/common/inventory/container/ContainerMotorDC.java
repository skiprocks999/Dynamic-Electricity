package dynamicelectricity.common.inventory.container;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.prefab.inventory.container.GenericContainerBlockEntity;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotRestricted;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;

public class ContainerMotorDC extends GenericContainerBlockEntity<TileMotorDC>{

	public ContainerMotorDC(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(5), new SimpleContainerData(3));
    }
	
	public ContainerMotorDC(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(DeferredRegisters.CONTAINER_MOTORDC.get(), id, playerinv, inventory, inventorydata);
	}
	
	public ContainerMotorDC(MenuType<?> type, int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(type, id, playerinv, inventory, inventorydata);
    }

	@Override
	public void addInventorySlots(Container inv, Inventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 100, 33, DeferredRegisters.ITEM_CONDUCTORBRUSH.get()));
		addSlot(new SlotRestricted(inv, nextIndex(), 118, 33, DeferredRegisters.ITEM_GELLUBRICANT.get()));
		
	}

}
