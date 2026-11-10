package dev.enderdevelopment.asyncmapper;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class MapperScreen extends Screen {
    private final Screen parent;
    private ButtonWidget enabledButton;
    private ButtonWidget slotButton;
    private ButtonWidget notificationButton;

    MapperScreen(Screen parent) {
        super(Text.translatable("screen.asyncmapper.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int center = width / 2;
        enabledButton = ButtonWidget.builder(enabledText(), button -> {
            AsyncMapperClient.config.enabled = !AsyncMapperClient.config.enabled;
            AsyncMapperClient.save();
            button.setMessage(enabledText());
        }).dimensions(center - 100, height / 2 - 54, 200, 20).build();
        addDrawableChild(enabledButton);
        slotButton = ButtonWidget.builder(slotText(), button -> {
            AsyncMapperClient.config.targetSlot = (AsyncMapperClient.config.targetSlot + 1) % 9;
            AsyncMapperClient.save();
            button.setMessage(slotText());
        }).dimensions(center - 100, height / 2 - 26, 200, 20).build();
        addDrawableChild(slotButton);
        notificationButton = ButtonWidget.builder(notificationText(), button -> {
            AsyncMapperClient.config.notifications = !AsyncMapperClient.config.notifications;
            AsyncMapperClient.save();
            button.setMessage(notificationText());
        }).dimensions(center - 100, height / 2 + 2, 200, 20).build();
        addDrawableChild(notificationButton);
        addDrawableChild(ButtonWidget.builder(Text.translatable("gui.done"), button -> close())
                .dimensions(center - 100, height / 2 + 32, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.asyncmapper.key", AsyncMapperClient.activationKey().getBoundKeyLocalizedText()), width / 2, height / 2 - 78, 0xFFFFFF);
    }

    private Text enabledText() { return Text.translatable("screen.asyncmapper.enabled", Text.translatable(AsyncMapperClient.config.enabled ? "options.on" : "options.off")); }
    private Text slotText() { return Text.translatable("screen.asyncmapper.target", AsyncMapperClient.config.targetSlot + 1); }
    private Text notificationText() { return Text.translatable("screen.asyncmapper.notifications", Text.translatable(AsyncMapperClient.config.notifications ? "options.on" : "options.off")); }

    @Override
    public void close() {
        if (client != null) client.setScreen(parent);
    }
}
