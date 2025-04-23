package com.exodus.skills;

import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private final JavaPlugin plugin;
    private final Map<UUID, Map<SkillType, Skill>> playerSkills;

    public PlayerDataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerSkills = new HashMap<>();
    }

    public void loadPlayerData(Player player) {
        File file = new File(plugin.getDataFolder(), "playerdata/" + player.getUniqueId() + ".yml");
        if (!file.exists()) {
            createNewPlayerData(player);
            return;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Map<SkillType, Skill> skills = new HashMap<>();

        for (SkillType type : SkillType.values()) {
            if (config.contains(type.name())) {
                int level = config.getInt(type.name() + ".level");
                double experience = config.getDouble(type.name() + ".experience");
                Skill skill = new Skill(type);
                skill.addExperience(experience);
                for (int i = 1; i < level; i++) skill.addExperience(skill.getExperienceRequiredForNextLevel());
                skills.put(type, skill);
            }
        }
        playerSkills.put(player.getUniqueId(), skills);
    }

    public void savePlayerData(Player player) {
        File file = new File(plugin.getDataFolder(), "playerdata/" + player.getUniqueId() + ".yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().warning("Error al crear el archivo de datos del jugador.");
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Map<SkillType, Skill> skills = playerSkills.get(player.getUniqueId());

        for (Map.Entry<SkillType, Skill> entry : skills.entrySet()) {
            Skill skill = entry.getValue();
            config.set(entry.getKey().name() + ".level", skill.getLevel());
            config.set(entry.getKey().name() + ".experience", skill.getExperience());
        }

        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Error al guardar el archivo de datos del jugador.");
        }
    }

    public void createNewPlayerData(Player player) {
        Map<SkillType, Skill> skills = new HashMap<>();
        for (SkillType type : SkillType.values()) {
            skills.put(type, new Skill(type));
        }
        playerSkills.put(player.getUniqueId(), skills);
    }

    public void saveAll() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            savePlayerData(player);
        }
    }

    public Map<SkillType, Skill> getPlayerSkills(UUID playerId) {
        return playerSkills.get(playerId);
    }
}
