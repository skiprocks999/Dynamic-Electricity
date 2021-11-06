package dynamicelectricity.common.inventory.container;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import electrodynamics.prefab.inventory.container.GenericContainer;
import electrodynamics.prefab.inventory.container.slot.SlotRestricted;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;

public class ContainerMotorAC extends GenericContainer<TileMotorAC>{
	
	public ContainerMotorAC(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(5), new SimpleContainerData(3));
    }
	
	public ContainerMotorAC(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(DeferredRegisters.CONTAINER_MOTORAC.get(), id, playerinv, inventory, inventorydata);
	}
	
	public ContainerMotorAC(MenuType<?> type, int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(type, id, playerinv, inventory, inventorydata);
    }

	@Override
	public void addInventorySlots(Container inv, Inventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 109, 33, DeferredRegisters.ITEM_GELLUBRICANT.get()));
	}

}
