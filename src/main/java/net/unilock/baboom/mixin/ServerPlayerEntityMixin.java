package net.unilock.baboom.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

import static net.unilock.baboom.Baboom.config;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
		super(world, pos, yaw, gameProfile, publicKey);
	}

	@Inject(at = @At("HEAD"), method = "onDeath(Lnet/minecraft/entity/damage/DamageSource;)V")
	private void explode(CallbackInfo info) {

		PlayerEntity player = this;
		String uuid = player.getUuidAsString();

		ArrayList<String> targetUuids = config.targetUuids;

		if (targetUuids.isEmpty() || targetUuids.contains(uuid)) {

			World world = player.world;
			Identifier worldValue = world.getRegistryKey().getValue();
			String dimension = worldValue.getNamespace() + ":" + worldValue.getPath();

			ArrayList<String> targetDimensions = config.targetDimensions;

			if (targetDimensions.isEmpty() || targetDimensions.contains(dimension)) {
				double x = player.getX();
				double y = player.getY();
				double z = player.getZ();
				Explosion.DestructionType destructionType = Explosion.DestructionType.BREAK;
				world.createExplosion(player, x, y, z, config.explosionPower, config.createFire, destructionType);
			}
		}
	}
}
