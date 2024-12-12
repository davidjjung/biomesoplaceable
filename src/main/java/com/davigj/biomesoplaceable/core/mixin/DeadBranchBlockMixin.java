package com.davigj.biomesoplaceable.core.mixin;

import biomesoplenty.block.DeadBranchBlock;
import com.davigj.biomesoplaceable.core.other.BOPlaceableBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DeadBranchBlock.class)
public class DeadBranchBlockMixin {

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void placeable(BlockState state, LevelReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Direction direction = (Direction)state.getValue(HorizontalDirectionalBlock.FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = worldReader.getBlockState(blockpos);
        cir.setReturnValue(blockstate.is(BOPlaceableBlockTags.DEAD_BRANCH_MAY_PLACE_ON));
    }
}
