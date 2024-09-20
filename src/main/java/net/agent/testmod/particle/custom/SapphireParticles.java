package net.agent.testmod.particle.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SapphireParticles extends TextureSheetParticle {
    private static final RandomSource RANDOM = RandomSource.create();
    private final SpriteSet sprites;

    protected SapphireParticles(ClientLevel clientLevel, double xPos, double yPos, double zPos, double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(clientLevel, xPos, yPos, zPos, 0.5D - RANDOM.nextDouble(), ySpeed, 0.5D - RANDOM.nextDouble());
        this.friction = 0.96F;
        this.gravity = -0.1F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = spriteSet;
        this.yd *= 0.2F;
        if (xSpeed == 0.0D && zSpeed == 0.0D) {
            this.xd *= 0.1F;
            this.zd *= 0.1F;
        }

        this.quadSize *= 0.75F;
        this.lifetime = 40;
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
        this.setSpriteFromAge(this.sprites);
    }

    public void fadeOut(){
        this.alpha = (-(1/(float)lifetime)*age+1);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SapphireParticles(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }
}
