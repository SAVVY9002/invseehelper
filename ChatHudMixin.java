package de.jannes.invseehelper.mixin;

import de.jannes.invseehelper.InvSeeHelperClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void invseehelper$clickName(double mouseX, double mouseY, CallbackInfoReturnable<Boolean> cir) {
        ChatHud hud = (ChatHud) (Object) this;
        @Nullable Style style = hud.getTextStyleAt(mouseX, mouseY);

        if (style == null) return;

        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent instanceof ClickEvent.RunCommand run) {
            String command = run.command();
            if (command.startsWith("/msg ") || command.startsWith("/tell ") || command.startsWith("/w ")) {
                return;
            }
        }

        String insertion = style.getInsertion();
        if (insertion != null && insertion.matches("[A-Za-z0-9_]{1,16}")) {
            InvSeeHelperClient.executeInvSee(insertion);
            cir.setReturnValue(true);
        }
    }
}