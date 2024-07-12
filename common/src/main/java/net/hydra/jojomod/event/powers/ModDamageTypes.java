package net.hydra.jojomod.event.powers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
        /**
         * Store the RegistryKey of our DamageType into a new constant called CUSTOM_DAMAGE_TYPE
         * The Identifier in use here points to our JSON file we created earlier.
         */
        public static final ResourceKey<DamageType> STAND = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("roundabout", "stand"));
        public static final ResourceKey<DamageType> TIME = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("roundabout", "time"));
        public static final ResourceKey<DamageType> KNIFE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("roundabout", "knife"));
        public static final ResourceKey<DamageType> MATCH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("roundabout", "match"));
        public static final ResourceKey<DamageType> BARBED_WIRE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("roundabout", "barbed_wire"));

        public static DamageSource of(Level world, ResourceKey<DamageType> key, Entity attacker) {
            return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key), attacker, attacker);
        }
        public static DamageSource of(Level world, ResourceKey<DamageType> key) {
                return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
        }
}
