package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityContainers {

	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DynamicElectricity.ID);
	
	public static final RegistryObject<ContainerType<ContainerMotorAC>> CONTAINER_MOTORAC = register("motorac", ContainerMotorAC::new);
	public static final RegistryObject<ContainerType<ContainerMotorDC>> CONTAINER_MOTORDC = register("motordc", ContainerMotorDC::new);
	
	private static <T extends Container> RegistryObject<ContainerType<T>> register(String id, ContainerType.IFactory<T> supplier) {
		return CONTAINERS.register(id, () -> new ContainerType<T>(supplier));
	}
	
}
