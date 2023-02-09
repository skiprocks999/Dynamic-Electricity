package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricityContainers {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, References.ID);
	
	public static final RegistryObject<MenuType<ContainerMotorAC>> CONTAINER_MOTORAC = CONTAINERS.register("motorac", () -> new MenuType<>(ContainerMotorAC::new));
	public static final RegistryObject<MenuType<ContainerMotorDC>> CONTAINER_MOTORDC = CONTAINERS.register("motordc", () -> new MenuType<>(ContainerMotorDC::new));
	
}
