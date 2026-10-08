package de.jannes.invseehelper.mixin;

import de.jannes.invseehelper.InvSeeHelperClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatHudMixin {
    @Inject(method = "handleClickEvent(Lnet/minecraft/text/Style;Z)Z", at = @At("HEAD"), cancellable = true)
    private void invseehelper$clickName(Style style, boolean insert, CallbackInfoReturnable<Boolean> cir) {
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
