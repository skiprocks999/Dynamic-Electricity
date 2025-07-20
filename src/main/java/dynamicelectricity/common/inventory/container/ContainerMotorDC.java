package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import voltaic.prefab.inventory.container.slot.item.type.SlotFluid;
import voltaic.prefab.inventory.container.slot.item.type.SlotRestricted;
import voltaic.prefab.inventory.container.types.GenericContainerBlockEntity;
import voltaic.prefab.utilities.math.Color;

public class ContainerMotorDC extends GenericContainerBlockEntity<TileMotorDC> {

	public ContainerMotorDC(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(2), new SimpleContainerData(5));
    }
	
	public ContainerMotorDC(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(Container inv, Inventory playerInv) {
		addSlot(new SlotRestricted(inv, nextIndex(), 101, 33).setRestriction(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get()).setIOColor(new Color(0, 240, 255, 255)));
		addSlot(new SlotFluid(inv, nextIndex(), 129, 33));
	}

}
