package com.kotakotik.creategears.blocks;

import com.kotakotik.creategears.regitration.GearsTiles;
import com.kotakotik.creategears.util.GenericUtils;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.relays.encased.GearshiftBlock;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SimpleGearshiftBlock extends GearshiftBlock implements GenericUtils {
    public SimpleGearshiftBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, true));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).setValue(POWERED, true);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        // ignore redstone stuff
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GearsTiles.SIMPLE_GEARSHIFT.create();
    }

    public ShapedRecipeBuilder recipe(RegistrateRecipeProvider prov) {
        return ShapedRecipeBuilder.shaped(this)
                .define('c', AllBlocks.ANDESITE_CASING.get())
                .define('w', AllBlocks.COGWHEEL.get())
                .unlockedBy("has_cogwheel", prov.hasItem(AllBlocks.COGWHEEL.get()));
    }

    public void recipe(ShapedRecipeBuilder builder, RegistrateRecipeProvider prov, String type) {
        builder.save(prov, modLoc(getRegistryName().getPath() + "_" + type));
    }
}
