package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityContainers {

	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, References.ID);
	
	public static final RegistryObject<ContainerType<ContainerMotorAC>> CONTAINER_MOTORAC = CONTAINERS.register("motorac", () -> new ContainerType<>(ContainerMotorAC::new));
	public static final RegistryObject<ContainerType<ContainerMotorDC>> CONTAINER_MOTORDC = CONTAINERS.register("motordc", () -> new ContainerType<>(ContainerMotorDC::new));
	
}
