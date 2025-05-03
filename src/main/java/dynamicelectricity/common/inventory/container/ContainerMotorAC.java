package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import voltaic.prefab.inventory.container.slot.item.type.SlotFluid;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;

public class ContainerMotorAC extends GenericContainerBlockEntity<TileMotorAC> {
	
	public ContainerMotorAC(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(1), new SimpleContainerData(3));
    }
	
	public ContainerMotorAC(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(Container inv, Inventory playerInv) {
		addSlot(new SlotFluid(inv, nextIndex(), 101, 33));
	}

}
