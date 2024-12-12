package com.davigj.biomesoplaceable.core.mixin;

import biomesoplenty.block.SpanishMossBottomBlock;
import com.davigj.biomesoplaceable.core.other.BOPlaceableBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static biomesoplenty.block.SpanishMossBlock.SHAPE;

@Mixin(SpanishMossBottomBlock.class)
public abstract class SpanishMossBottomBlockMixin extends GrowingPlantHeadBlock {

    public SpanishMossBottomBlockMixin(Properties p_i241194_1_) {
        super(p_i241194_1_, Direction.DOWN, SHAPE, false, 0.01D);
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
