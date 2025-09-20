package net.stln.magitech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.stln.magitech.particle.option.UnstableSquareParticleEffect;

import org.joml.Vector3f;

public class MistaliaPetalsBlock extends PinkPetalsBlock {

    public MistaliaPetalsBlock(Properties p_273335_) {
        super(p_273335_);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if ((long) level.random.nextInt(200) <= level.getGameTime() % 200L) {
            Vec3 vec3 = Vec3.atCenterOf(pos);
            double d0 = vec3.x + Mth.nextDouble(level.random, -1.0, 1.0) * (0.4);
            double d1 = vec3.y + Mth.nextDouble(level.random, -1.0, -0.8) * (0.5);
            double d2 = vec3.z + Mth.nextDouble(level.random, -1.0, 1.0) * (0.4);
            double d3 = Mth.nextDouble(level.random, -0.05, 0.05);
            double d4 = Mth.nextDouble(level.random, 0, 0.05);
            double d5 = Mth.nextDouble(level.random, -0.05, 0.05);
            level.addParticle(
                    new UnstableSquareParticleEffect(
                            new Vector3f(0.0F, 1.0F, 1.0F),
                            new Vector3f(1.0F, 1.0F, 1.0F),
                            0.5F,
                            1,
                            0),
                    d0,
                    d1,
                    d2,
                    d3,
                    d4,
                    d5);
        }
    }
}
