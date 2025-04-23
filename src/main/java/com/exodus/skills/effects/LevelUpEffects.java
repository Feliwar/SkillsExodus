package com.exodus.skills.effects;

import org.bukkit.entity.Player;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class LevelUpEffects {

    // Método para mostrar efectos cuando se sube de nivel
    public static void playLevelUpEffects(Player player) {
        // Efecto visual de partículas alrededor del jugador
        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 100);

        // Sonido de subida de nivel
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

        // Mensaje al jugador
        player.sendMessage("¡Has subido de nivel! ¡Felicidades!");
    }
}
