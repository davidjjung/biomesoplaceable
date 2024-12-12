package com.davigj.biomesoplaceable.core.mixin;

import biomesoplenty.block.SpanishMossBlock;
import com.davigj.biomesoplaceable.core.other.BOPlaceableBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpanishMossBlock.class)
public abstract class SpanishMossBlockMixin extends GrowingPlantBodyBlock {

    protected SpanishMossBlockMixin(Properties p_53886_, Direction p_53887_, VoxelShape p_53888_, boolean p_53889_) {
        super(p_53886_, p_53887_, p_53888_, p_53889_);
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void placeable(BlockState state, LevelReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = worldReader.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate)) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.is(BOPlaceableBlockTags.SPANISH_MOSS_MAY_PLACE_ON));
        }
    }
}
