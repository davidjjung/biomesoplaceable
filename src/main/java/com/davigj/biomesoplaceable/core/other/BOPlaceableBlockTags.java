package com.davigj.biomesoplaceable.core.other;

import com.davigj.biomesoplaceable.core.BOPlaceable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BOPlaceableBlockTags {
    public static final TagKey<Block> SEA_OATS_MAY_PLACE_ON = blockTag("sea_oats_may_place_on");
    public static final TagKey<Block> DEAD_BRANCH_MAY_PLACE_ON = blockTag("dead_branch_may_place_on");
    public static final TagKey<Block> SPANISH_MOSS_MAY_PLACE_ON = blockTag("spanish_moss_may_place_on");
    public static final TagKey<Block> DUNE_GRASS_MAY_PLACE_ON = blockTag("dune_grass_may_place_on");
    public static final TagKey<Block> ENDERPHYTE_MAY_PLACE_ON = blockTag("enderphyte_may_place_on");
    public static final TagKey<Block> DEAD_OR_DESERT_GRASS_MAY_PLACE_ON = blockTag("dead_or_desert_grass_may_place_on");

    private static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(BOPlaceable.MOD_ID, name));
    }
}
