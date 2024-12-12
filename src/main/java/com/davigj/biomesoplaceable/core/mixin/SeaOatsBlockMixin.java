package com.davigj.biomesoplaceable.core.mixin;

import biomesoplenty.block.DoublePlantBlockBOP;
import biomesoplenty.block.SeaOatsBlock;
import com.davigj.biomesoplaceable.core.other.BOPlaceableBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SeaOatsBlock.class)
public class SeaOatsBlockMixin extends DoublePlantBlockBOP {
    public SeaOatsBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void placeable(BlockState state, LevelReader worldReader, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        SeaOatsBlock oats = (SeaOatsBlock) (Object) this;
        if (state.getBlock() != oats) {
            cir.setReturnValue(super.canSurvive(state, worldReader, pos));
        } else {
            BlockState below;
            if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
                below = worldReader.getBlockState(pos.below());
                cir.setReturnValue(below.is(BOPlaceableBlockTags.SEA_OATS_MAY_PLACE_ON));
            } else {
                below = worldReader.getBlockState(pos.below());
                cir.setReturnValue(below.getBlock() == this && below.getValue(HALF) == DoubleBlockHalf.LOWER);
            }
        }
    }
}
