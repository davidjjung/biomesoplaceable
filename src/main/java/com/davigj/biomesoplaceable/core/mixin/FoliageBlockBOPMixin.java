package com.davigj.biomesoplaceable.core.mixin;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.FoliageBlockBOP;
import com.davigj.biomesoplaceable.core.other.BOPlaceableBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoliageBlockBOP.class)
public class FoliageBlockBOPMixin extends BushBlock {
    public FoliageBlockBOPMixin(Properties p_51021_) {
        super(p_51021_);
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void placeable(BlockState state, LevelReader worldIn, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        FoliageBlockBOP foliage = (FoliageBlockBOP) (Object) this;
        BlockState groundState = worldIn.getBlockState(pos.below());
        if (foliage == BOPBlocks.SPROUT) {
            cir.setReturnValue(groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP) || super.canSurvive(state, worldIn, pos));
        } else if (foliage == BOPBlocks.DUNE_GRASS) {
            cir.setReturnValue(groundState.is(BOPlaceableBlockTags.DUNE_GRASS_MAY_PLACE_ON));
        } else if (foliage != BOPBlocks.DESERT_GRASS && foliage != BOPBlocks.DEAD_GRASS) {
            if (foliage != BOPBlocks.ENDERPHYTE) {
                cir.setReturnValue(super.canSurvive(state, worldIn, pos));
            } else {
                cir.setReturnValue(groundState.is(BOPlaceableBlockTags.ENDERPHYTE_MAY_PLACE_ON));
            }
        } else {
            cir.setReturnValue(groundState.is(BOPlaceableBlockTags.DEAD_OR_DESERT_GRASS_MAY_PLACE_ON) || super.canSurvive(state, worldIn, pos));
        }
    }
}
