package net.hydra.jojomod.event.powers.stand;

import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.access.ILivingEntityAccess;
import net.hydra.jojomod.access.IPlayerEntity;
import net.hydra.jojomod.client.KeyInputs;
import net.hydra.jojomod.client.StandIcons;
import net.hydra.jojomod.event.index.OffsetIndex;
import net.hydra.jojomod.event.index.PlayerPosIndex;
import net.hydra.jojomod.event.index.PowerIndex;
import net.hydra.jojomod.event.index.SoundIndex;
import net.hydra.jojomod.event.powers.*;
import net.hydra.jojomod.networking.ModPacketHandler;
import net.hydra.jojomod.sound.ModSounds;
import net.hydra.jojomod.util.MainUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Main;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class PowersTheWorld extends StandPowers {

    public PowersTheWorld(LivingEntity self) {
        super(self);
    }

    @Override
    protected SoundEvent getSummonSound() {
        return ModSounds.WORLD_SUMMON_SOUND_EVENT;
    }


    /**Dodge ability*/
    @Override
    public void buttonInput3(boolean keyIsDown, Options options) {
        if (this.getSelf().level().isClientSide) {
            if (keyIsDown) {
                    if (!options.keyShift.isDown()){
                        if (!this.onCooldown(PowerIndex.SKILL_3)) {
                            if (this.getSelf().onGround()) {
                                byte forward = 0;
                                byte strafe = 0;
                                if (options.keyUp.isDown()) forward++;
                                if (options.keyDown.isDown()) forward--;
                                if (options.keyLeft.isDown()) strafe++;
                                if (options.keyRight.isDown()) strafe--;
                                int degrees = (int) (this.getSelf().getYRot() % 360);
                                int backwards = 0;

                                if (strafe > 0 && forward == 0) {
                                    degrees -= 90;
                                    degrees = degrees % 360;
                                } else if (strafe > 0 && forward > 0) {
                                    degrees -= 45;
                                    degrees = degrees % 360;
                                } else if (strafe > 0) {
                                    degrees -= 135;
                                    degrees = degrees % 360;
                                    backwards = 1;
                                } else if (strafe < 0 && forward == 0) {
                                    degrees += 90;
                                    degrees = degrees % 360;
                                } else if (strafe < 0 && forward > 0) {
                                    degrees += 45;
                                    degrees = degrees % 360;
                                } else if (strafe < 0) {
                                    degrees += 135;
                                    degrees = degrees % 360;
                                    backwards = 1;
                                } else if (forward < 0) {
                                    degrees += 180;
                                    degrees = degrees % 360;
                                    backwards = 1;
                                }


                                int cdTime = 50;
                                if (this.getSelf() instanceof Player) {
                                    if (((Player) this.getSelf()).isCreative()) {
                                        cdTime = 7;
                                    }
                                    ((IPlayerEntity) this.getSelf()).roundabout$setClientDodgeTime(0);
                                /*
                                if (backwards > 0){
                                    ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.DODGE_BACKWARD);
                                } else {
                                    ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.DODGE_FORWARD);
                                }
                                */
                                }
                                this.setCooldown(PowerIndex.SKILL_3, cdTime);
                                MainUtil.takeUnresistableKnockbackWithY(this.getSelf(), 1F,
                                        Mth.sin(degrees * ((float) Math.PI / 180)),
                                        Mth.sin(-20 * ((float) Math.PI / 180)),
                                        -Mth.cos(degrees * ((float) Math.PI / 180)));

                                ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.MOVEMENT, backwards);
                            }
                        }
                    } else {
                        if (!this.onCooldown(PowerIndex.SKILL_3_SNEAK)) {
                            if (this.getSelf().onGround()) {
                                this.setCooldown(PowerIndex.SKILL_3_SNEAK, 300);
                                bigLeap(this.getSelf(),20);
                                ((StandUser) this.getSelf()).roundabout$setLeapTicks(((StandUser) this.getSelf()).roundabout$getMaxLeapTicks());
                                ModPacketHandler.PACKET_ACCESS.StandPowerPacket(PowerIndex.SNEAK_MOVEMENT);
                            }
                        }
                    }
            }
        }
    }

    public void bigLeap(LivingEntity entity,float range){
        Vec3 vec3d = entity.getEyePosition(0);
        Vec3 vec3d2 = entity.getViewVector(0);
        Vec3 vec3d3 = vec3d.add(vec3d2.x * range, vec3d2.y * range, vec3d2.z * range);
        BlockHitResult blockHit = entity.level().clip(new ClipContext(vec3d, vec3d3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

        double mag = this.getSelf().getPosition(0).distanceTo(
                new Vec3(blockHit.getLocation().x, blockHit.getLocation().y,blockHit.getLocation().z))*0.7+1;

        MainUtil.takeUnresistableKnockbackWithY2(this.getSelf(),
                (blockHit.getLocation().x - this.getSelf().getX())/mag,
                0.6+Math.max((blockHit.getLocation().y - this.getSelf().getY())/mag,0),
                (blockHit.getLocation().z - this.getSelf().getZ())/mag
        );

    }

    /**Begin Charging Time Stop, also detects activation via release**/
    @Override
    public void buttonInput4(boolean keyIsDown, Options options) {
        if (this.getSelf().level().isClientSide) {
            if (!this.onCooldown(PowerIndex.SKILL_4) || ((Player)this.getSelf()).isCreative()) {
                if ((((TimeStop)this.getSelf().level()).CanTimeStopEntity(this.getSelf()) || !this.isAttackInept(this.getActivePower()))) {
                    boolean sendPacket = false;
                    if (KeyInputs.roundaboutClickCount == 0) {
                        if (keyIsDown) {
                            if (this.isStoppingTime()) {
                                KeyInputs.roundaboutClickCount = 2;
                                this.playSoundsIfNearby(TIME_RESUME_NOISE, 100, true);
                                ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.SPECIAL_FINISH, this.getChargedTSTicks());
                                ((StandUser) this.getSelf()).tryPower(PowerIndex.SPECIAL_FINISH, true);
                            } else if (this.getActivePower() == PowerIndex.SPECIAL || (this.getSelf() instanceof Player && ((Player) this.getSelf()).isCreative())) {
                                sendPacket = true;
                            } else {
                                KeyInputs.roundaboutClickCount = 2;
                                if (options.keyShift.isDown()) {
                                    this.setChargedTSTicks(20);
                                    this.setMaxChargeTSTime(20);
                                    sendPacket = true;
                                } else {
                                    if (this.getAttackTimeDuring() < 0) {
                                        this.setMaxChargeTSTime(this.getMaxTSTime());
                                        ModPacketHandler.PACKET_ACCESS.StandPowerPacket(PowerIndex.SPECIAL);
                                        ((StandUser) this.getSelf()).tryPower(PowerIndex.SPECIAL, true);
                                        this.updateUniqueMoves();
                                    }
                                }
                            }
                        }

                    } else {
                        if (keyIsDown) {
                            KeyInputs.roundaboutClickCount = 2;
                        }
                    }

                    if (sendPacket) {
                        KeyInputs.roundaboutClickCount = 2;
                        ((StandUser) this.getSelf()).tryPower(PowerIndex.SPECIAL_CHARGED, true);
                        ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.SPECIAL_CHARGED, this.getChargedTSTicks());
                    }
                }
            }
        }
    }


    @Override
    public void tryPower(int move, boolean forced) {
        if (!this.getSelf().level().isClientSide && this.getActivePower() == PowerIndex.SPECIAL) {
            this.stopSoundsIfNearby(SoundIndex.TIME_CHARGE_SOUND_GROUP, 100);
        }
        super.tryPower(move,forced);
    }

    public void setMaxChargeTSTime(int chargedTSTicks){
        this.maxChargeTSTime = chargedTSTicks;
    }
    /**The version of the above function to call at the end of a timestop. Used to calculate additional TS seconds*/
    public void setCurrentMaxTSTime(float chargedTSSeconds){
        if (chargedTSSeconds >= 100){
            this.maxChargeTSTime = 180;
            this.setChargedTSTicks(180);
        } else if (chargedTSSeconds == 20) {
            this.maxChargeTSTime = 20;
        } else {
            this.maxChargeTSTime = 100;
        }
    }

    /*Activate Time Stop**/

    public void stopTime() {
                /*Time Stop*/
          if (!this.getSelf().level().isClientSide()) {
              if (!((TimeStop) this.getSelf().level()).isTimeStoppingEntity(this.getSelf())) {
                  boolean animate = false;
                  hasActedInTS = false;
                  this.setCurrentMaxTSTime(this.getChargedTSTicks());
                  if (!(((TimeStop)this.getSelf().level()).CanTimeStopEntity(this.getSelf()))) {
                      if (this.getChargedTSTicks() > 20 || (this.getSelf() instanceof Player && ((Player) this.getSelf()).isCreative())) {
                          /*Charged Sound*/
                          animate = true;
                          playSoundsIfNearby(TIME_STOP_NOISE, 100, true);
                      } else {
                          /*No Charged Sound*/
                          playSoundsIfNearby(TIME_STOP_NOISE_2, 100, true);
                      }
                  }
                  ((TimeStop) this.getSelf().level()).addTimeStoppingEntity(this.getSelf());
                  if (this.getSelf() instanceof Player) {
                      ModPacketHandler.PACKET_ACCESS.sendIntPowerPacket(((ServerPlayer) this.getSelf()), PowerIndex.SPECIAL, maxChargeTSTime);
                  }
                  ((StandUser) this.getSelf()).tryPower(PowerIndex.NONE, true);
                  /**
                  if (animate){
                      animateStand((byte) 31);
                  }
                   */
              }
          } else {
              ((StandUser) this.getSelf()).tryPower(PowerIndex.LEAD_IN, true);
          }
    }

    @Override
    public boolean canInterruptPower(){
        if (this.getActivePower() == PowerIndex.SPECIAL){
            return true;
        } else {
            return super.canInterruptPower();
        }
    }

    /**Stand related things that slow you down or speed you up*/
    public int inputSpeedModifiers(int sprintTrigger){
        if (this.getSelf().level().isClientSide) {
            LocalPlayer local = ((LocalPlayer) this.getSelf());
            StandUser standUser = ((StandUser) this.getSelf());
            if (standUser.roundaboutGetTSJump()) {
                if (local.isCrouching()) {
                    local.input.leftImpulse *= 1.0f;
                    local.input.forwardImpulse *= 1.1f;
                    sprintTrigger = 0;
                } else {
                    local.input.leftImpulse *= 0.85f;
                    local.input.forwardImpulse *= 0.85f;
                    sprintTrigger = 0;
                }
            } else if (this.getActivePower() == PowerIndex.SPECIAL) {
                local.input.leftImpulse *= 0.48f;
                local.input.forwardImpulse *= 0.48f;
                sprintTrigger = 0;
            }
        }
        return super.inputSpeedModifiers(sprintTrigger);
    }
    @Override
    public boolean getIsTsCharging(){
        if (this.getActivePower() == PowerIndex.SPECIAL) {
            return true;
        }
        return false;
    }

    /**Tick through dash*/
    @Override
    public void tickDash(){
        if (this.getSelf() instanceof Player) {

            if (((IPlayerEntity)this.getSelf()).roundabout$getClientDodgeTime() >= 10){
                ((IPlayerEntity)this.getSelf()).roundabout$setClientDodgeTime(-1);
                if (!this.getSelf().level().isClientSide){
                    ((IPlayerEntity)this.getSelf()).roundabout$setDodgeTime(-1);
                    byte pos = ((IPlayerEntity)this.getSelf()).roundabout$GetPos();
                    if (pos == PlayerPosIndex.DODGE_FORWARD || pos == PlayerPosIndex.DODGE_BACKWARD) {
                        ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.NONE);
                    }
                }
            } else if (((IPlayerEntity)this.getSelf()).roundabout$getClientDodgeTime() >= 0){
                ((IPlayerEntity) this.getSelf()).roundabout$setClientDodgeTime(((IPlayerEntity) this.getSelf()).roundabout$getClientDodgeTime()+1);
            }

            if (((IPlayerEntity)this.getSelf()).roundabout$getDodgeTime() >= 10){

                ((IPlayerEntity)this.getSelf()).roundabout$setDodgeTime(-1);
                byte pos = ((IPlayerEntity)this.getSelf()).roundabout$GetPos();
                if (pos == PlayerPosIndex.DODGE_FORWARD || pos == PlayerPosIndex.DODGE_BACKWARD) {
                    ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.NONE);
                }
            } else if (((IPlayerEntity)this.getSelf()).roundabout$getDodgeTime() >= 0){
                if (this.getSelf().level().isClientSide){
                    ((IPlayerEntity) this.getSelf()).roundabout$setDodgeTime(((IPlayerEntity) this.getSelf()).roundabout$getDodgeTime()+1);
                    if (((IPlayerEntity)this.getSelf()).roundabout$getDodgeTime() > 2){
                        this.getSelf().setDeltaMovement(this.getSelf().getDeltaMovement().x*0.82,
                                this.getSelf().getDeltaMovement().y,
                                this.getSelf().getDeltaMovement().z*0.82);
                    }
                }
            }
        }
    }

    public void resumeTime() {
        /*Time Resume*/
        if (!this.getSelf().level().isClientSide()) {
            if (((TimeStop) this.getSelf().level()).isTimeStoppingEntity(this.getSelf())) {
                float tsTimeRemaining = (200+((this.getMaxChargeTSTime()-this.getChargedTSTicks())*5));
                if ((this.getActivePower() == PowerIndex.ATTACK || this.getActivePower() == PowerIndex.SNEAK_ATTACK) && this.getAttackTimeDuring() > -1){
                    this.hasActedInTS = true;
                }
                if (this.hasActedInTS){
                    tsTimeRemaining+=300;
                    this.hasActedInTS = false;
                }

                int sendTSCooldown = Math.round(tsTimeRemaining);
                if (!(this.getSelf() instanceof Player && ((Player)this.getSelf()).isCreative())) {
                    if (this.getSelf() instanceof Player) {
                        ModPacketHandler.PACKET_ACCESS.syncSkillCooldownPacket(((ServerPlayer) this.getSelf()), PowerIndex.SKILL_4, sendTSCooldown);
                    }
                    this.setCooldown(PowerIndex.SKILL_4, sendTSCooldown);
                }

                ((TimeStop) this.getSelf().level()).removeTimeStoppingEntity(this.getSelf());
                stopSoundsIfNearby(SoundIndex.TIME_SOUND_GROUP, 200);
                if (this.getSelf() instanceof Player) {
                    ModPacketHandler.PACKET_ACCESS.sendIntPowerPacket(((ServerPlayer) this.getSelf()), PowerIndex.SPECIAL_FINISH, 0);
                }

                if (!(((TimeStop)this.getSelf().level()).CanTimeStopEntity(this.getSelf()))) {
                    if (this.getMaxChargeTSTime() > 20) {
                        this.playSoundsIfNearby(TIME_RESUME_NOISE, 100, true);
                    }
                }
            }
        }
        this.setChargedTSTicks(0);
        if (this.isBarraging()) {
            ((StandUser) this.getSelf()).tryPower(PowerIndex.NONE, true);
        }
    }
    @Override
    public void setPowerSpecial(int lastMove) {

        this.setMaxChargeTSTime(this.getMaxTSTime());
        this.setAttackTimeDuring(0);
        this.setChargedTSTicks(20);

        this.setActivePower(PowerIndex.SPECIAL);
        poseStand(OffsetIndex.GUARD);
        animateStand((byte) 30);
        if (!(((TimeStop)this.getSelf().level()).CanTimeStopEntity(this.getSelf()))) {
            playSoundsIfNearby(getTSVoice(), 100, false);
        }
        playSoundsIfNearby(TIME_STOP_CHARGE, 100, true);
    }
    @Override
    public void setPowerMovement(int lastMove) {
            if (this.getSelf() instanceof Player) {
                this.setPowerNone();
                ((IPlayerEntity)this.getSelf()).roundabout$setClientDodgeTime(0);
                ((IPlayerEntity) this.getSelf()).roundabout$setDodgeTime(0);
                if (storedInt > 0){
                    ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.DODGE_BACKWARD);
                } else {
                    ((IPlayerEntity) this.getSelf()).roundabout$SetPos(PlayerPosIndex.DODGE_FORWARD);
                }
            }
            if (!this.getSelf().level().isClientSide()) {
                this.getSelf().level().playSound(null, this.getSelf().blockPosition(), ModSounds.DODGE_EVENT, SoundSource.PLAYERS, 1.0F, (float) (0.98 + (Math.random() * 0.04)));
            }
    }

    @Override
    public void setPowerSneakMovement(int lastMove) {
        if (this.getSelf() instanceof Player) {
            this.setPowerNone();
            this.setPowerNone();
        }
        ((StandUser) this.getSelf()).roundabout$setLeapTicks(((StandUser) this.getSelf()).roundabout$getMaxLeapTicks());
        if (!this.getSelf().level().isClientSide()) {

            this.getSelf().level().playSound(null, this.getSelf().blockPosition(), ModSounds.STAND_LEAP_EVENT, SoundSource.PLAYERS, 20.0F, (float) (0.98 + (Math.random() * 0.04)));
        }
    }
    public byte getTSVoice(){
        double rand = Math.random();
        if (rand > 0.6){
            return TIME_STOP_VOICE;
        } else {
            return TIME_STOP_VOICE_2;
        }
    }

    @Override
    public boolean isAttackInept(byte activeP){
        if (activeP == PowerIndex.SPECIAL){
            return false;
        }
        return super.isAttackInept(activeP);
    }


    @Override
    public void setPowerOther(int move, int lastMove) {
        if (move == PowerIndex.SPECIAL_FINISH) {
            this.resumeTime();
        } else if (move == PowerIndex.SPECIAL_CHARGED){
            this.stopTime();
        }
    }

    @Override
    public void updateUniqueMoves() {
        /*Tick through Time Stop Charge*/
        if (this.getActivePower() == PowerIndex.SPECIAL) {
            int TSChargeSeconds = this.getChargedTSTicks();
            TSChargeSeconds += ((this.getMaxChargeTSTime()-20) / 40);
            if (TSChargeSeconds >= this.getMaxChargeTSTime()) {
                TSChargeSeconds = this.getMaxChargeTSTime();
                this.setChargedTSTicks(TSChargeSeconds);
                if (this.getSelf().level().isClientSide) {
                    ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.SPECIAL_CHARGED, TSChargeSeconds);
                } else {
                    if (this.getSelf() instanceof ServerPlayer) {
                        ModPacketHandler.PACKET_ACCESS.sendIntPowerPacket(((ServerPlayer)this.getSelf()),PowerIndex.SPECIAL_CHARGED, TSChargeSeconds);
                    }
                }
                ((StandUser) this.getSelf()).tryPower(PowerIndex.SPECIAL_CHARGED, true);
            } else {
                this.setChargedTSTicks(TSChargeSeconds);
            }
        }
    }

    @Override
    public void timeTickStopPower(){
        if (!(this.getSelf() instanceof Player && ((Player)this.getSelf()).isCreative())) {
            int TSChargeTicks = this.getChargedTSTicks();
            TSChargeTicks -= 1;

            if (!Roundabout.canBreathInTS){
                this.getSelf().setAirSupply(((ILivingEntityAccess) this.getSelf()).roundaboutDecreaseAirSupply(this.getSelf().getAirSupply()));
            }

            if (TSChargeTicks < 0 || (!Roundabout.canBreathInTS && this.getSelf().getAirSupply() == -20)) {
                if (this.getSelf().getAirSupply() == -20) {
                    this.getSelf().setAirSupply(0);
                }
                TSChargeTicks = 0;
                this.setChargedTSTicks(TSChargeTicks);
                if (this.getSelf().level().isClientSide) {
                    ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.SPECIAL_FINISH,TSChargeTicks);
                }
                ((StandUser) this.getSelf()).tryPower(PowerIndex.SPECIAL_FINISH, true);
            } else {
                if (this.getSelf().level().isClientSide) {
                    /*If the server is behind on the client TS time, update it to lower*/
                    ModPacketHandler.PACKET_ACCESS.StandChargedPowerPacket(PowerIndex.SPECIAL_TRACKER, TSChargeTicks);
                } else {
                    /** This code was for the time resume sfx creeping in, but it sounds very chaotic
                     * with all of the other TS sounds so I am opting out
                    if (this.getMaxChargeTSTime() >= 20 && !playedResumeSound) {
                        if (TSChargeTicks <= 25 && this.getMaxChargeTSTime() >= 65) {
                            playSoundsIfNearby(TIME_STOP_ENDING_NOISE,100);
                            playedResumeSound = true;
                        } else if (TSChargeTicks <= 20 && this.getMaxChargeTSTime() > 20){
                            playSoundsIfNearby(TIME_STOP_ENDING_NOISE_2,100);
                            playedResumeSound = true;
                        }
                    }
                     */
                }
                this.setChargedTSTicks(TSChargeTicks);
            }
        }
    }

    /**If a client is behind a server on TS charging somehow, and the server finishes charging, this packet rounds
     * things out*/
    @Override
    public void updatePowerInt(byte activePower, int data){
        if (activePower == PowerIndex.SPECIAL) {
            if (this.getMaxChargeTSTime() < data) {
                this.setMaxChargeTSTime(data);
                this.setChargedTSTicks(data);
            }
        } else if (activePower == PowerIndex.SPECIAL_CHARGED){
            this.setChargedTSTicks(data);
        } else if (activePower == PowerIndex.SPECIAL_FINISH){
            if (this.isBarraging()) {
                ((StandUser) this.getSelf()).tryPower(PowerIndex.NONE, true);
            }
        }
    }

    /**Charge up Time Stop*/
    @Override
    public boolean tryChargedPower(int move, boolean forced, int chargeTime){
        if (move == PowerIndex.SPECIAL_CHARGED){
            if (this.getSelf().level().isClientSide() ||
                    !((TimeStop) this.getSelf().level()).isTimeStoppingEntity(this.getSelf())) {
                this.setChargedTSTicks(chargeTime);
            }
            super.tryChargedPower(move, forced, chargeTime);
        } else if (move == PowerIndex.SPECIAL_TRACKER){
            /*If the server is behind on the client TS time, update it to lower*/
            if (this.getChargedTSTicks() > chargeTime){
                this.setChargedTSTicks(chargeTime);
            }
            return false;
        } else if (move == PowerIndex.SPECIAL_FINISH){
            /*If the server is behind on the client TS time, update it to lower*/
            if (this.getChargedTSTicks() > chargeTime){
                this.setChargedTSTicks(chargeTime);
            }
        } else if (move == PowerIndex.MOVEMENT) {
            this.storedInt = chargeTime;
        }
        super.tryChargedPower(move, forced, chargeTime);
        return true;
    }

    /**Indicates the standard max TS Time, for setting up bar length*/
    @Override
    public int getMaxTSTime (){
        return 100;
    }

    /*Change this value actively to manipulate how long a ts charge can be in ticks*/
    private int maxChargeTSTime = 100;
    @Override
    public int getMaxChargeTSTime(){
        return maxChargeTSTime;
    }

    @Override
    public float getSoundVolumeFromByte(byte soundChoice){
        if (soundChoice == TIME_STOP_NOISE) {
            return 0.7f;
        } else if (soundChoice == TIME_RESUME_NOISE) {
            return 0.8f;
        } else if (soundChoice == TIME_STOP_NOISE_3) {
            return 0.6f;
        }
        return 1F;
    }

    @Override
    public float getSoundPitchFromByte(byte soundChoice){
        if (soundChoice == TIME_STOP_NOISE_3){
            return 1F;
        } else {
            return super.getSoundPitchFromByte(soundChoice);
        }
    }

    @Override
    public byte chooseBarrageSound(){
        double rand = Math.random();
        if (rand > 0.5) {
            return BARRAGE_NOISE;
        } else {
            return BARRAGE_NOISE_2;
        }
    }

    @Override
    public SoundEvent getSoundFromByte(byte soundChoice){
        if (soundChoice == BARRAGE_NOISE) {
            return ModSounds.STAND_THEWORLD_MUDA5_SOUND_EVENT;
        } else if (soundChoice == BARRAGE_NOISE_2){
            return ModSounds.STAND_THEWORLD_MUDA1_SOUND_EVENT;
        } else if (soundChoice == TIME_STOP_NOISE) {
            return ModSounds.TIME_STOP_THE_WORLD_EVENT;
        } else if (soundChoice == TIME_STOP_NOISE_2) {
            return ModSounds.TIME_STOP_THE_WORLD2_EVENT;
        } else if (soundChoice == TIME_STOP_NOISE_3) {
            return ModSounds.TIME_STOP_THE_WORLD3_EVENT;
        } else if (soundChoice == SoundIndex.SPECIAL_MOVE_SOUND_2) {
            return ModSounds.TIME_RESUME_EVENT;
        } else if (soundChoice == TIME_STOP_CHARGE){
            return ModSounds.TIME_STOP_CHARGE_THE_WORLD_EVENT;
        } else if (soundChoice == TIME_STOP_VOICE){
            return ModSounds.TIME_STOP_VOICE_THE_WORLD_EVENT;
        } else if (soundChoice == TIME_STOP_VOICE_2){
            return ModSounds.TIME_STOP_VOICE_THE_WORLD2_EVENT;
        } else if (soundChoice == TIME_STOP_VOICE_3){
            return ModSounds.TIME_STOP_VOICE_THE_WORLD3_EVENT;
        } else if (soundChoice == TIME_STOP_ENDING_NOISE){
            return ModSounds.TIME_STOP_RESUME_THE_WORLD_EVENT;
        } else if (soundChoice == TIME_STOP_ENDING_NOISE_2){
            return ModSounds.TIME_STOP_RESUME_THE_WORLD2_EVENT;
        } else if (soundChoice == TIME_RESUME_NOISE){
            return ModSounds.TIME_RESUME_EVENT;
        } else if (soundChoice == TIME_STOP_TICKING){
            return ModSounds.TIME_STOP_TICKING_EVENT;
        }
        return super.getSoundFromByte(soundChoice);
    }
    @Override
    public void runExtraSoundCode(byte soundChoice) {
        if (soundChoice >= TIME_STOP_NOISE && soundChoice <= TIME_STOP_NOISE_5) {
            if (this.getSelf().level().isClientSide) {
                Minecraft mc = Minecraft.getInstance();
                mc.getSoundManager().stop();
                if (!((TimeStop)this.getSelf().level()).isTimeStoppingEntity(this.getSelf())){
                    ((StandUserClient)this.getSelf()).clientPlaySoundIfNoneActive(TIME_STOP_TICKING);
                }
            }
        }
    }

    @Override
    public void timeTick(){
        if (this.getActivePower() == PowerIndex.SPECIAL){
            this.updateUniqueMoves();
        }
        super.timeTick();
    }


    @Override
    public int getBarrageWindup(){
        if (timeStopStartedBarrage) {
            return 13;
        } else {
            return 29;
        }
    }
    //public void setSkillIcon(GuiGraphics context, int x, int y, ResourceLocation rl, boolean dull, @Nullable CooldownInstance cooldownInstance){
    @Override
    public boolean isAttackIneptVisually(byte activeP){
        return this.isDazed(this.getSelf()) || (activeP != PowerIndex.SKILL_4 && (((TimeStop)this.getSelf().level()).CanTimeStopEntity(this.getSelf())));
    }

    @Override
    public void renderIcons(GuiGraphics context, int x, int y){
        if (this.getSelf().isCrouching()){
            setSkillIcon(context, x, y, 3, StandIcons.STAND_LEAP_WORLD, PowerIndex.SKILL_3_SNEAK);
        } else {
            setSkillIcon(context, x, y, 3, StandIcons.DODGE, PowerIndex.SKILL_3);
        }

        if (((TimeStop)this.getSelf().level()).isTimeStoppingEntity(this.getSelf())) {
            setSkillIcon(context, x, y, 4, StandIcons.THE_WORLD_TIME_STOP_RESUME, PowerIndex.NO_CD);
        } else if (this.getSelf().isCrouching()){
            setSkillIcon(context, x, y, 4, StandIcons.THE_WORLD_TIME_STOP_IMPULSE, PowerIndex.SKILL_4);
        } else {
            setSkillIcon(context, x, y, 4, StandIcons.THE_WORLD_TIME_STOP, PowerIndex.SKILL_4);
        }
    }

    public boolean timeStopStartedBarrage = false;
    @Override
    public boolean bonusBarrageConditions(){
        if (this.getSelf() != null){
            boolean TSEntity = ((TimeStop)this.getSelf().level()).isTimeStoppingEntity(this.getSelf());
            if (TSEntity && !this.timeStopStartedBarrage){
                this.timeStopStartedBarrage = true;
                return true;
            } else if (!TSEntity && this.timeStopStartedBarrage){
                this.timeStopStartedBarrage = false;
                return false;
            }
        }
        return true;
    }

    /**Barrage During a time stop, and it will cancel when time resumes, but it will also skip the charge*/
    @Override
    public void setPowerBarrageCharge(){
        if (this.getSelf() != null && ((TimeStop)this.getSelf().level()).isTimeStoppingEntity(this.getSelf())){
            timeStopStartedBarrage = true;
        } else {
            timeStopStartedBarrage = false;
        }
        super.setPowerBarrageCharge();
    }

    @Override
    public void playBarrageChargeSound(){
        if (!timeStopStartedBarrage){
            super.playBarrageChargeSound();
        }
    }

    @Override
    public void playBarrageNoise(int hitNumber, Entity entity){
        if (!this.getSelf().level().isClientSide()) {
            if (!this.getSelf().level().isClientSide() && (((TimeStop)this.getSelf().level()).CanTimeStopEntity(entity))) {
                playBarrageBlockNoise();
            } else {
                if (hitNumber % 2 == 0) {
                    this.getSelf().level().playSound(null, this.getSelf().blockPosition(), ModSounds.STAND_BARRAGE_HIT_EVENT, SoundSource.PLAYERS, 0.9F, (float) (0.9 + (Math.random() * 0.25)));
                }
            }
        }
    }

    @Override
    public void playBarrageNoise2(int hitNumber, Entity entity){
        if (!this.getSelf().level().isClientSide()) {
            if (!this.getSelf().level().isClientSide() && (((TimeStop)this.getSelf().level()).CanTimeStopEntity(entity))) {
                playBarrageBlockNoise();
            } else {
                if (hitNumber%2==0) {
                    this.getSelf().level().playSound(null, this.getSelf().blockPosition(), ModSounds.STAND_BARRAGE_HIT2_EVENT, SoundSource.PLAYERS, 0.95F, (float) (0.9 + (Math.random() * 0.25)));
                }
            }
        }
    }
    @Override
    public void playBarrageEndNoise(float mod, Entity entity){
        if (!this.getSelf().level().isClientSide()) {
            if (!this.getSelf().level().isClientSide() && (((TimeStop)this.getSelf().level()).CanTimeStopEntity(entity))) {
                if (entity instanceof  LivingEntity){
                    ((StandUser)entity).roundaboutSetTSHurtSound(3);
                }
                playBarrageBlockEndNoise(0,entity);
            } else {
                this.getSelf().level().playSound(null, this.getSelf().blockPosition(), ModSounds.STAND_BARRAGE_END_EVENT, SoundSource.PLAYERS, 0.95F+mod, 1f);
            }
        }
    }

    /**20 ticks in a second*/
    @Override
    public boolean isStoppingTime(){
       return (((TimeStop)this.getSelf().level()).isTimeStoppingEntity(this.getSelf()));
    }



    @Override
    public byte getSoundCancelingGroupByte(byte soundChoice) {
        if (soundChoice >= TIME_STOP_CHARGE && soundChoice <= TIME_STOP_VOICE_3) {
            return SoundIndex.TIME_CHARGE_SOUND_GROUP;
        } else if (soundChoice >= TIME_STOP_NOISE && soundChoice <= TIME_STOP_ENDING_NOISE_2) {
            return SoundIndex.TIME_SOUND_GROUP;
        } else if (soundChoice >= BARRAGE_NOISE && soundChoice <= BARRAGE_NOISE_2){
                return SoundIndex.BARRAGE_SOUND_GROUP;
        } else {
            return super.getSoundCancelingGroupByte(soundChoice);
        }
    }


    public static final byte DODGE_NOISE = 19;

    public static final byte BARRAGE_NOISE = 20;
    public static final byte BARRAGE_NOISE_2 = BARRAGE_NOISE+1;
    public static final byte TIME_STOP_CHARGE = 30;
    public static final byte TIME_STOP_VOICE = TIME_STOP_CHARGE+1;
    public static final byte TIME_STOP_VOICE_2 = TIME_STOP_CHARGE+2;
    public static final byte TIME_STOP_VOICE_3 = TIME_STOP_CHARGE+3;
    public static final byte TIME_STOP_NOISE = 40;
    public static final byte TIME_STOP_NOISE_2 = TIME_STOP_NOISE+1;
    public static final byte TIME_STOP_NOISE_3 = TIME_STOP_NOISE+2;
    public static final byte TIME_STOP_NOISE_4 = TIME_STOP_NOISE+3;
    public static final byte TIME_STOP_NOISE_5 = TIME_STOP_NOISE+4;
    public static final byte TIME_STOP_TICKING = TIME_STOP_NOISE+9;
    public static final byte TIME_STOP_ENDING_NOISE_2 = TIME_STOP_NOISE+10;
    public static final byte TIME_STOP_ENDING_NOISE = TIME_STOP_NOISE+11;
    public static final byte TIME_RESUME_NOISE = 60;
}
