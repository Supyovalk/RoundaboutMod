package net.hydra.jojomod.event.index;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
public class PoseAnimations {

        public static final AnimationDefinition Giorno = AnimationDefinition.Builder.withLength(0.5417F)
                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(25.0F, 100.8945F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-31.2549F, 0.0F, 108.862F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.posVec(1.4F, -1.9F, 4.9F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(-9.4957F, -14.1195F, 17.0008F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.posVec(-4.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 68.7549F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 68.7549F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.posVec(1.3F, 0.0F, 1.8F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 68.7549F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.posVec(-1.25F, 0.0F, -1.85F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition OH_NO = AnimationDefinition.Builder.withLength(0.2917F)
        .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-128.6496F, -32.8019F, 17.6718F), AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
        new Keyframe(0.2917F, KeyframeAnimations.posVec(0.9F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-128.6496F, 32.8019F, -17.6718F), AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
        new Keyframe(0.2917F, KeyframeAnimations.posVec(-0.9F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
        ))
        .build();

        public static final AnimationDefinition JOTARO = AnimationDefinition.Builder.withLength(0.4583F)
                .addAnimation("head_part", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-2.8648F, 11.4592F, -4.5837F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("upper_body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-93.0405F, 65.0508F, 4.5344F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.posVec(-0.16F, -1.29F, -2.5F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("body_part", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, -51.5662F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("full_body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition WRRRYYY = AnimationDefinition.Builder.withLength(1.6865F).looping()
                .addAnimation("head_part", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2976F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.3968F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.496F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5952F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6944F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.7937F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8929F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.9921F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0913F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.1905F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.2897F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.3889F, KeyframeAnimations.degreeVec(-45.8366F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.4881F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(-11.4592F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("head_part", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.posVec(0.0F, -4.0F, 9.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.posVec(0.0F, -4.0F, 9.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("upper_body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2976F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.3968F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.496F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5952F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6944F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.7937F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8929F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.9921F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0913F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.1905F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.2897F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.3889F, KeyframeAnimations.degreeVec(-51.5662F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.4881F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(-54.431F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2976F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.3968F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.496F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5952F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6944F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.7937F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8929F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.9921F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0913F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.1905F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.2897F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.3889F, KeyframeAnimations.degreeVec(42.4576F, 39.6774F, 98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.4881F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(0.0F, 57.3F, 45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.2976F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.3968F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.496F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5952F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.6944F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.7937F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.8929F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.9921F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.0913F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.1905F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.2897F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.3889F, KeyframeAnimations.degreeVec(42.4576F, -39.6774F, -98.62F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.4881F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(0.0F, -57.3F, -45.8366F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.1984F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.5873F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.6865F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition Jonathan = AnimationDefinition.Builder.withLength(2.0417F)
                .addAnimation("head_part", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("upper_body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.7296F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.7296F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 76.7763F, 34.3775F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 76.7763F, 34.3775F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-82.5059F, 45.8366F, 80.2141F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(-82.5059F, 45.8366F, 80.2141F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("right_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 11.4592F), AnimationChannel.Interpolations.LINEAR)
                ))
                .addAnimation("left_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -11.4592F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(1.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -11.4592F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();


        public static final AnimationDefinition Koichi = AnimationDefinition.Builder.withLength(0.4167F)
                .addAnimation("head_part", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
                ))
                .build();
}
