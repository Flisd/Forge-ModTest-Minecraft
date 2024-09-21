package net.agent.testmod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class SapphireParticles extends TextureSheetParticle {
    public SapphireParticles(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.98F;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize= 1;
        this.lifetime = 100;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1 / (float) lifetime) * age + 1);
    }

    @NotNull
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType particleType, @NotNull ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            SapphireParticles sapphireParticles = new SapphireParticles(level, x, y, z, this.spriteSet, dx, dy, dz);
            sapphireParticles.setColor(1F, 0.80F, 0.25F);
            return sapphireParticles;
        }
    }
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
            SapphireParticles particle = new SapphireParticles(level, x, y, z, sprite, xd, yd, zd);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
