package net.hydra.jojomod.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.access.IPlayerEntity;
import net.hydra.jojomod.entity.stand.StarPlatinumEntity;
import net.hydra.jojomod.event.index.ShapeShifts;
import net.hydra.jojomod.event.powers.StandUser;
import net.hydra.jojomod.item.GlaiveItem;
import net.hydra.jojomod.item.ModItems;
import net.hydra.jojomod.item.StandArrowItem;
import net.hydra.jojomod.util.MainUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ZItemInHandRenderer {

    @Shadow
    private void applyItemArmTransform(PoseStack poseStack, HumanoidArm humanoidArm, float f) {
    }
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    public void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
    }

    @Inject(method = "renderHandsWithItems", at = @At(value = "HEAD"), cancellable = true)
    public void roundabout$renderHandsWithItems(float $$0, PoseStack $$1, MultiBufferSource.BufferSource $$2, LocalPlayer $$3, int $$4, CallbackInfo ci) {
        if ($$3 != null && ((StandUser)$$3).roundabout$getStand() instanceof StarPlatinumEntity SE){
            if (SE.getScoping() && Minecraft.getInstance().options.getCameraType().isFirstPerson()){
                ci.cancel();
            }
        }
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "HEAD"), cancellable = true)
    public void roundabout$renderArmWithItemAbstractClientPlayer(AbstractClientPlayer abstractClientPlayer, float ff, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, MultiBufferSource multiBufferSource, int j, CallbackInfo ci) {
        if (abstractClientPlayer.isScoping()) {
            return;
        }
        if (!itemStack.isEmpty()) {
            if (abstractClientPlayer.isUsingItem() && abstractClientPlayer.getUseItemRemainingTicks() > 0 && abstractClientPlayer.getUsedItemHand() == interactionHand) {

                boolean bl = interactionHand == InteractionHand.MAIN_HAND;
                HumanoidArm humanoidArm = bl ? abstractClientPlayer.getMainArm() : abstractClientPlayer.getMainArm().getOpposite();
                boolean bl2;
                boolean bl3 = bl2 = humanoidArm == HumanoidArm.RIGHT;
                int q = bl2 ? 1 : -1;
                if ((itemStack.getUseAnimation() == UseAnim.SPEAR &&
                        (itemStack.is(ModItems.KNIFE) || itemStack.is(ModItems.KNIFE_BUNDLE)
                                || itemStack.is(ModItems.MATCH) || itemStack.is(ModItems.MATCH_BUNDLE))) || itemStack.getItem() instanceof GlaiveItem) {

                    float knifeTime = 10f;
                    if (itemStack.is(ModItems.KNIFE) || itemStack.is(ModItems.MATCH)) {
                        knifeTime = 5F;
                    } else if (itemStack.getItem() instanceof GlaiveItem) {
                        knifeTime = 14F;
                    }
                    float kT2 = (float) (knifeTime * 0.1);
                    float kT3 = (float) (knifeTime * 0.01);

                    ci.cancel();
                    poseStack.pushPose();

                    this.applyItemArmTransform(poseStack, humanoidArm, i);
                    poseStack.translate((float) q * -0.3f, 0.25, 0.15731531f);
                    if (itemStack.is(ModItems.KNIFE) || itemStack.is(ModItems.KNIFE_BUNDLE)) {
                        poseStack.mulPose(Axis.XP.rotationDegrees(-55.0f));
                    } else if (itemStack.getItem() instanceof GlaiveItem) {
                        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0f));
                    } else {
                        poseStack.mulPose(Axis.XP.rotationDegrees(-10.0f));
                    }
                    poseStack.mulPose(Axis.YP.rotationDegrees((float) q * 35.3f));
                    poseStack.mulPose(Axis.ZP.rotationDegrees((float) q * -9.785f));
                    float r = (float) itemStack.getUseDuration() - ((float) this.minecraft.player.getUseItemRemainingTicks() - ff + kT2);
                    float l = r / knifeTime;
                    if (l > kT2) {
                        l = kT2;
                    }
                    if (l > kT3) {
                        float m = Mth.sin((r - kT3) * 1.3f);
                        float n = l - kT3;
                        float o = m * n;
                        poseStack.translate(o * 0.0f, o * 0.004f, o * 0.0f);
                    }
                    if (itemStack.is(ModItems.KNIFE_BUNDLE) || itemStack.is(ModItems.MATCH_BUNDLE) || itemStack.getItem() instanceof GlaiveItem) {
                        l /= 2;
                    }
                    if (itemStack.getItem() instanceof GlaiveItem) {
                        poseStack.translate(0.0f, l * -0.4, l * 0.1f);
                    } else {
                        poseStack.translate(0.0f, 0.0f, l * 0.2f);
                    }
                    poseStack.scale(1.0f, 1.0f, 1.0f + l * 0.2f);
                    poseStack.mulPose(Axis.YN.rotationDegrees((float) q * 45.0f));
                    this.renderItem(abstractClientPlayer, itemStack, bl2 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND :
                            ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !bl2, poseStack, multiBufferSource, j);
                    poseStack.popPose();
                } else if (itemStack.getUseAnimation() == UseAnim.BLOCK && itemStack.getItem() instanceof StandArrowItem) {
                    ci.cancel();
                    poseStack.pushPose();
                    this.applyItemArmTransform(poseStack, humanoidArm, i);
                    poseStack.translate((float) q * -0.3f, 0.25, 0.15731531f);
                    float knifeTime = 5f;
                    float kT2 = (float) (knifeTime * 0.1);
                    float kT3 = (float) (knifeTime * 0.01);
                    poseStack.mulPose(Axis.XP.rotationDegrees(30.0f));
                    poseStack.mulPose(Axis.YP.rotationDegrees((float) q * -35.3f));
                    poseStack.mulPose(Axis.ZP.rotationDegrees((float) q * -9.785f));
                    float r = (float) itemStack.getUseDuration() - ((float) this.minecraft.player.getUseItemRemainingTicks() - ff + kT2);
                    float l = r / knifeTime;
                    if (l > kT2) {
                        l = kT2;
                    }
                    if (l > kT3) {
                        float m = Mth.sin((r - kT3) * 1.3f);
                        float n = l - kT3;
                        float o = m * n;
                        poseStack.translate(o * 0.0f, o * 0.004f, o * 0.0f);
                    }
                    poseStack.translate(0.0f, l * -0.6, l * -0.1f);
                    poseStack.scale(1.0f, 1.0f, 1.0f);
                    poseStack.mulPose(Axis.YN.rotationDegrees((float) q * 45.0f));
                    this.renderItem(abstractClientPlayer, itemStack, bl2 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND :
                            ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !bl2, poseStack, multiBufferSource, j);
                    poseStack.popPose();
                }
            } else {
                if (itemStack.getItem() instanceof StandArrowItem){
                    Mob ME =  MainUtil.homeOnWorthy(abstractClientPlayer.level(),abstractClientPlayer.position(),5);
                    if (ME != null) {
                        float homingMod = (5-Math.min(5,ME.distanceTo(abstractClientPlayer)))/5;
                        boolean bl = interactionHand == InteractionHand.MAIN_HAND;
                        HumanoidArm humanoidArm = bl ? abstractClientPlayer.getMainArm() : abstractClientPlayer.getMainArm().getOpposite();
                        boolean bl2;
                        boolean bl3 = bl2 = humanoidArm == HumanoidArm.RIGHT;
                        int q = bl2 ? 1 : -1;
                        ci.cancel();
                        poseStack.pushPose();

                        this.applyItemArmTransform(poseStack, humanoidArm, i);
                        poseStack.translate((float) q * -0.28f, 0.15, 0.1);
                        float knifeTime = 5f;
                        float kT2 = (float) (knifeTime * 0.1);
                        float r = (-ff + kT2);
                        poseStack.scale(1.0f, 1.0f, 1.0f);
                        poseStack.mulPose(Axis.XP.rotationDegrees(-30.0f-Math.abs(20F*(r*homingMod))-(14F*homingMod)));
                        this.renderItem(abstractClientPlayer, itemStack, bl2 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND :
                                ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !bl2, poseStack, multiBufferSource, j);
                        poseStack.popPose();
                    }
                }
            }
        }
    }
}