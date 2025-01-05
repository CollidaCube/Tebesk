package com.collidacube.tebesk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import io.tebex.sdk.SDK;
import io.tebex.sdk.Tebex;
import io.tebex.sdk.platform.Platform;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Tebesk extends JavaPlugin {

    public static Platform getTebexPlatform() {
        return Tebex.get();
    }

    public static SDK getTebexSdk() {
        return getTebexPlatform().getSDK();
    }

    private SkriptAddon addon;

    @Override
    public void onEnable() {
        try {
            addon = Skript.registerAddon(this);
            addon.loadClasses("com.collidacube.tebesk.skript");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {

    }
}
