package dev.enderdevelopment.asyncmapper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class AsyncMapperClient implements ClientModInitializer {
    static MapperConfig config;
    private static KeyBinding toggleKey;
    private static KeyBinding configKey;
    private static int returnSlot = -1;

    @Override
    public void onInitializeClient() {
        config = MapperConfig.load();
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.asyncmapper.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, KeyBinding.Category.MISC));
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.asyncmapper.config", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KeyBinding.Category.MISC));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(new MapperScreen(null));
            }
            while (toggleKey.wasPressed()) toggle(client);
        });
    }

    private static void toggle(MinecraftClient client) {
        if (!config.enabled || client.player == null || client.currentScreen != null) return;
        int current = client.player.getInventory().getSelectedSlot();
        int destination;
        if (returnSlot >= 0 && current == config.targetSlot) {
            destination = returnSlot;
            returnSlot = -1;
        } else {
            if (current == config.targetSlot) return;
            returnSlot = current;
            destination = config.targetSlot;
        }
        client.player.getInventory().setSelectedSlot(destination);
        if (config.notifications) {
            client.player.sendMessage(Text.translatable("text.asyncmapper.slot", destination + 1), true);
        }
    }

    static void save() { config.save(); }
    static KeyBinding activationKey() { return toggleKey; }
}
