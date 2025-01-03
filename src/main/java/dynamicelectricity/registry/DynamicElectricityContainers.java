package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityContainers {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, References.ID);
	
	public static final DeferredHolder<MenuType<?>, MenuType<ContainerMotorAC>> CONTAINER_MOTORAC = register("motorac", ContainerMotorAC::new);
	public static final DeferredHolder<MenuType<?>, MenuType<ContainerMotorDC>> CONTAINER_MOTORDC = register("motordc", ContainerMotorDC::new);
	
	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> register(String id, MenuSupplier<T> supplier) {
		return CONTAINERS.register(id, () -> new MenuType<T>(supplier, FeatureFlags.VANILLA_SET));
	}
	
}
