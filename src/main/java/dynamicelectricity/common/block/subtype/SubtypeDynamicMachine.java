package dynamicelectricity.common.block.subtype;

import dynamicelectricity.common.block.DynamicElectricityVoxelShapes;
import dynamicelectricity.common.tile.*;
import net.minecraft.block.BlockRenderType;
import net.minecraft.tileentity.TileEntity;
import voltaic.api.ISubtype;
import voltaic.api.multiblock.subnodebased.parent.IMultiblockParentBlock;
import voltaic.api.tile.IMachine;
import voltaic.api.tile.MachineProperties;
import voltaic.api.tile.TileEntitySupplier;
import voltaic.common.block.voxelshapes.VoxelShapeProvider;

public enum SubtypeDynamicMachine implements ISubtype, IMachine {

    motoraclv(true, TileMotorAcLv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.LV_ACMOTOR)),
    motoracmv(true, TileMotorAcMv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.MV_ACMOTOR)),
    motorachv(true, TileMotorAcHv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.HV_ACMOTOR)),
    motordclv(true, TileMotorDcLv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.LV_DCMOTOR)),
    motordcmv(true, TileMotorDcMv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.MV_DCMOTOR)),
    motordchv(true, TileMotorDcHv::new, MachineProperties.builder().setShapeProvider(DynamicElectricityVoxelShapes.HV_DCMOTOR)),
    ;

    private final TileEntitySupplier<TileEntity> blockEntitySupplier;
    private final boolean showInItemGroup;
    private final MachineProperties properties;

    private SubtypeDynamicMachine(boolean showInItemGroup, TileEntitySupplier<TileEntity> blockEntitySupplier) {
        this(showInItemGroup, blockEntitySupplier, MachineProperties.DEFAULT);
    }

    private SubtypeDynamicMachine(boolean showInItemGroup, TileEntitySupplier<TileEntity> blockEntitySupplier, MachineProperties properties) {
        this.showInItemGroup = showInItemGroup;
        this.blockEntitySupplier = blockEntitySupplier;
        this.properties = properties;
    }

    @Override
    public TileEntitySupplier<TileEntity> getBlockEntitySupplier() {
        return blockEntitySupplier;
    }

    @Override
    public int getLitBrightness() {
        return properties.litBrightness;
    }

    @Override
    public BlockRenderType getRenderShape() {
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
    public IMultiblockParentBlock.SubnodeWrapper getSubnodes() {
        return properties.wrapper;
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
