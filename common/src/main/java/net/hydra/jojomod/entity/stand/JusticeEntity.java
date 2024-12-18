package net.hydra.jojomod.entity.stand;

import net.hydra.jojomod.event.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class JusticeEntity extends StandEntity {
    public JusticeEntity(EntityType<? extends Mob> entityType, Level world) {
        super(entityType, world);
    }
    public static final byte
            PART_3_SKIN = 1,
            MANGA_SKIN = 2,
            SKELETON_SKIN = 3,
            OVA_SKIN = 4,
            BOGGED = 5,
            STRAY_SKIN = 6,
            TAROT = 8,
            FLAMED = 7,
            WITHER = 9,
            TWILIGHT = 10,
            PIRATE = 11,
            BLUE_FLAMED = 12,
            DARK_MIRAGE = 13;

    @Override
    public Component getSkinName(byte skinId) {
        return getSkinNameT(skinId);
    }
    public static Component getSkinNameT(byte skinId){
        if (skinId == PART_3_SKIN){
            return Component.translatable(  "skins.roundabout.justice.base");
        } else if (skinId == MANGA_SKIN){
            return Component.translatable(  "skins.roundabout.justice.manga");
        } else if (skinId == SKELETON_SKIN){
            return Component.translatable(  "skins.roundabout.justice.skeleton");
        } else if (skinId == OVA_SKIN){
            return Component.translatable(  "skins.roundabout.justice.ova");
        } else if (skinId == STRAY_SKIN){
            return Component.translatable(  "skins.roundabout.justice.stray");
        } else if (skinId == BOGGED){
            return Component.translatable(  "skins.roundabout.justice.bogged");
        } else if (skinId == TAROT){
            return Component.translatable(  "skins.roundabout.justice.tarot");
        } else if (skinId == WITHER){
            return Component.translatable(  "skins.roundabout.justice.wither");
        } else if (skinId == FLAMED){
            return Component.translatable(  "skins.roundabout.justice.flamed");
        } else if (skinId == BLUE_FLAMED){
            return Component.translatable(  "skins.roundabout.justice.flamed_blue");
        } else if (skinId == TWILIGHT){
            return Component.translatable(  "skins.roundabout.justice.twilight");
        } else if (skinId == PIRATE){
            return Component.translatable(  "skins.roundabout.justice.pirate");
        } else if (skinId == DARK_MIRAGE){
            return Component.translatable(  "skins.roundabout.justice.dark_mirage");
        }
        return Component.translatable(  "skins.roundabout.the_world.base");
    }
    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState idleAnimation2 = new AnimationState();
    @Override
    public void setupAnimationStates() {
        if (this.getUser() != null) {
            if (this.getAnimation() == 0 && this.getIdleAnimation() == 1) {
                this.idleAnimation2.startIfStopped(this.tickCount);
            } else {
                this.idleAnimation2.stop();
            }
            if (this.getAnimation() == 0 && this.getIdleAnimation() == 0) {
                this.idleAnimation.startIfStopped(this.tickCount);
            } else {
                this.idleAnimation.stop();
            }
        }
    }

    @Override
    public Component getPosName(byte posID){
        if (posID == 1){
            return Component.translatable(  "idle.roundabout.battle_justice");
        } else {
            return Component.translatable(  "idle.roundabout.passive_justice");
        }
    }

    public int tsReleaseTime = 0;
    @Override
    public void tick(){
        if (!this.level().isClientSide){
            if (this.getAnimation() == 31) {
                tsReleaseTime++;
                if (tsReleaseTime > 24){
                    this.setAnimation((byte) 0);
                    tsReleaseTime = 0;
                }
            }
        } else {

            if (this.getSkin() != DARK_MIRAGE) {
                if (this.getSkin() == FLAMED) {
                    for (int i = 0; i < 3; i++) {
                        this.level()
                                .addParticle(
                                        ParticleTypes.FLAME,
                                        this.getRandomX(1.1),
                                        this.getRandomY(),
                                        this.getRandomZ(1.1),
                                        0,
                                        0.1,
                                        0
                                );
                    }
                }

                if (this.getSkin() == BLUE_FLAMED) {
                    for (int i = 0; i < 3; i++) {
                        this.level()
                                .addParticle(
                                        ParticleTypes.SOUL_FIRE_FLAME,
                                        this.getRandomX(1.1),
                                        this.getRandomY(),
                                        this.getRandomZ(1.1),
                                        0,
                                        0.1,
                                        0
                                );
                    }
                }

                for (int i = 0; i < 5; i++) {
                    this.level()
                            .addParticle(
                                    ModParticles.FOG_CHAIN,
                                    this.getRandomX(1.1),
                                    this.getRandomY(),
                                    this.getRandomZ(1.1),
                                    0,
                                    0.1,
                                    0
                            );
                }
            }
        }
        super.tick();
    }

    @Override
    public Vec3 getIdleOffset(LivingEntity standUser) {
        if (this.getSkin() != DARK_MIRAGE) {
            int vis = this.getFadeOut();
            double r = (((double) vis / MaxFade) * ((standUser.getBbWidth() / 2) + this.getDistanceOut()));
            if (r < 0.5) {
                r = 0.5;
            }
            double yawfix = standUser.getYRot();
            yawfix += this.getAnchorPlace() + 125;
            if (yawfix > 360) {
                yawfix -= 360;
            } else if (yawfix < 0) {
                yawfix += 360;
            }
            double ang = (yawfix - 180) * Math.PI;

            double mcap = 0.3;
            Vec3 xyz = standUser.getDeltaMovement();
            double yy = xyz.y() * 0.3;
            if (yy > mcap) {
                yy = mcap;
            } else if (yy < -mcap) {
                yy = -mcap;
            }
            if (isSwimming() || isVisuallyCrawling() || isFallFlying()) {
                yy += 1;
            }

            double x1 = standUser.getX() - -1 * (r * (Math.sin(ang / 180)));
            double y1 = standUser.getY() + 0.1 - yy;
            double z1 = standUser.getZ() - (r * (Math.cos(ang / 180)));

            return new Vec3(x1, y1, z1);
        } else {
            return super.getIdleOffset(standUser);
        }
    }

}

