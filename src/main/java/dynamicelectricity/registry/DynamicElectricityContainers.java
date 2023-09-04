package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricityContainers {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, References.ID);
	
	public static final RegistryObject<MenuType<ContainerMotorAC>> CONTAINER_MOTORAC = register("motorac", ContainerMotorAC::new);
	public static final RegistryObject<MenuType<ContainerMotorDC>> CONTAINER_MOTORDC = register("motordc", ContainerMotorDC::new);
	
	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String id, MenuSupplier<T> supplier) {
		return CONTAINERS.register(id, () -> new MenuType<T>(supplier, FeatureFlags.VANILLA_SET));
	}
	
}
