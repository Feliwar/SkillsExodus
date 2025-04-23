package com.exodus.skills;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class LevelManager {

    private final ExodusSkills plugin;
    private final boolean enableSounds;
    private final boolean enableParticles;
    private final String rewardItem;
    private final int maxLevel;

    public LevelManager(ExodusSkills plugin, boolean enableSounds, boolean enableParticles, String rewardItem, int maxLevel) {
        this.plugin = plugin;
        this.enableSounds = enableSounds;
        this.enableParticles = enableParticles;
        this.rewardItem = rewardItem;
        this.maxLevel = maxLevel;
    }

    // Llamado cuando un jugador sube de nivel en alguna habilidad
    public void playLevelUpEffects(Player player) {
        // Efecto visual de partículas
        if (enableParticles) {
            player.getWorld().spawnParticle(org.bukkit.Particle.valueOf(plugin.getConfig().getString("visuals.level-up-particle", "FIREWORKS_SPARK")), player.getLocation(), plugin.getConfig().getInt("visuals.particle-count", 100));
        }

        // Sonido de subida de nivel
        if (enableSounds) {
            player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("sounds.level-up", "ENTITY_PLAYER_LEVELUP")), 1.0f, 1.0f);
        }

        // Recompensa: Darle un ítem
        Material material = Material.valueOf(rewardItem);
        player.getInventory().addItem(new ItemStack(material));
        player.sendMessage("¡Has subido de nivel!");
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
