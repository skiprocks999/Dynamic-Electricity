package dynamicelectricity.common.inventory.container;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.prefab.inventory.container.GenericContainerBlockEntity;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotFluid;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotRestricted;
import electrodynamics.prefab.utilities.math.Color;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class ContainerMotorDC extends GenericContainerBlockEntity<TileMotorDC>{

	public ContainerMotorDC(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(2), new SimpleContainerData(3));
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
