# AsyncMapper Fabric Client Mod

AsyncMapper is an open source Fabric client mod for Minecraft Java Edition 1.21.11 that provides a configurable hotbar slot toggle utility. Press the activation key to switch to a configured target slot, then press it again to return to the slot selected before the first press. The mod changes only the selected hotbar index and never moves, drops, or edits inventory items.

## Install

Requirements:

- Minecraft Java Edition 1.21.11
- Java 21
- Fabric Loader for Minecraft 1.21.11
- Fabric API for Minecraft 1.21.11

To install the compiled mod:

1. Install Fabric Loader for Minecraft 1.21.11.
2. Start Minecraft once with the Fabric profile to create the `mods` directory.
3. Place `asyncmapper-1.0.0.jar` in the `mods` directory.
4. Place a Fabric API jar matching Minecraft 1.21.11 in the same directory.
5. Start Minecraft using the Fabric profile.

To build the mod from source:

1. Install Java 21 and Gradle.
2. Run `gradle build` from the project directory.
3. Find the compiled jar at `build/libs/asyncmapper-1.0.0.jar`.
4. Install the jar using the steps above.

## Use

1. Start Minecraft with Fabric and AsyncMapper installed.
2. Open Options > Controls.
3. Find the AsyncMapper keybindings.
4. Set the activation key. It defaults to `R`.
5. Press the activation key during gameplay to switch to the configured target hotbar slot.
6. Press it again to return to the previously selected slot.
7. To open settings, assign the `Open AsyncMapper Settings` keybinding. It is unbound by default.
8. Use AsyncMapper Settings to enable or disable the mod, cycle the target slot from 1 through 9, and enable or disable action-bar notifications.
9. Settings are saved to `config/asyncmapper.json`.

The toggle does not operate while another screen is open, while the player is unavailable, or when AsyncMapper is disabled.

---

More open source project projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/f0a6aec5-81c1-4399-ba1d-86083738c44c?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=f0a6aec5-81c1-4399-ba1d-86083738c44c
