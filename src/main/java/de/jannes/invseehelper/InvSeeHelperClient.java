package de.jannes.invseehelper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class InvSeeHelperClient implements ClientModInitializer {
    public static final String MOD_ID = "invseehelper";

    @Override
    public void onInitializeClient() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.isSneaking() && entity instanceof PlayerEntity target && target != player) {
                executeInvSee(target.getName().getString());
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
    }

    public static void executeInvSee(String playerName) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        String cleanName = playerName.replaceAll("[^A-Za-z0-9_]", "");
        if (cleanName.isEmpty()) return;

        client.player.networkHandler.sendChatCommand("invsee " + cleanName);
    }
}
