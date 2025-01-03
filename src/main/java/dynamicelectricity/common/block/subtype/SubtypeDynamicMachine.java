package dynamicelectricity.common.block.subtype;

import dynamicelectricity.common.block.DynamicElectricityVoxelShapes;
import dynamicelectricity.common.tile.*;
import electrodynamics.api.ISubtype;
import electrodynamics.api.multiblock.subnodebased.Subnode;
import electrodynamics.api.tile.IMachine;
import electrodynamics.api.tile.MachineProperties;
import electrodynamics.common.block.voxelshapes.VoxelShapeProvider;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public enum SubtypeDynamicMachine implements ISubtype, IMachine {

    motoraclv(true, TileMotorAcLv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.LV_ACMOTOR)),
    motordchv(true, TileMotorDcHv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.LV_ACMOTOR)),
    motordcmv(true, TileMotorDcMv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.MV_ACMOTOR)),
    motordclv(true, TileMotorDcLv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.MV_DCMOTOR)),
    motorachv(true, TileMotorAcHv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.HV_ACMOTOR)),
    motoracmv(true, TileMotorAcMv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.HV_DCMOTOR)),
    ;

    private final BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier;
    private final boolean showInItemGroup;
    private final MachineProperties properties;

    private SubtypeDynamicMachine(boolean showInItemGroup, BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier) {
        this(showInItemGroup, blockEntitySupplier, MachineProperties.DEFAULT);
    }

    private SubtypeDynamicMachine(boolean showInItemGroup, BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier, MachineProperties properties) {
        this.showInItemGroup = showInItemGroup;
        this.blockEntitySupplier = blockEntitySupplier;
        this.properties = properties;
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<BlockEntity> getBlockEntitySupplier() {
        return blockEntitySupplier;
    }

    @Override
    public int getLitBrightness() {
        return properties.litBrightness;
    }

    @Override
    public RenderShape getRenderShape() {
        return properties.renderShape;
    }

    @Override
    public boolean isMultiblock() {
        return properties.isMultiblock;
    }

    @Override
    public boolean propegatesLightDown() {
        return properties.propegatesLightDown;
    }

    @Override
    public String tag() {
        return name();
    }

    @Override
    public String forgeTag() {
        return tag();
    }

    @Override
    public boolean isItem() {
        return false;
    }

    public boolean isPlayerStorable() {
        return false;
    }

    @Override
    public Subnode[] getSubnodes() {
        return properties.subnodes;
    }

    @Override
    public VoxelShapeProvider getVoxelShapeProvider() {
        return properties.provider;
    }

    @Override
    public boolean usesLit() {
        return properties.usesLit;
    }

    public boolean showInItemGroup() {
        return showInItemGroup;
    }
}
