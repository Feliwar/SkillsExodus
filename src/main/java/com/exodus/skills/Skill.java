package com.exodus.skills;

import org.bukkit.entity.Player;

public class Skill {
    private final SkillType skillType;
    private int level;
    private double experience;

    public Skill(SkillType skillType) {
        this.skillType = skillType;
        this.level = 1;
        this.experience = 0;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public int getLevel() {
        return level;
    }

    public double getExperience() {
        return experience;
    }

    public void addExperience(double amount) {
        this.experience += amount;
        while (this.experience >= getExperienceRequiredForNextLevel()) {
            this.experience -= getExperienceRequiredForNextLevel();
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        // Llamar a efectos visuales y sonidos al subir de nivel
        LevelUpEffects.playLevelUpEffects();
    }

    public double getExperienceRequiredForNextLevel() {
        // XP exponencial, pero alcanzable
        return Math.pow(level, 2) * 10;
    }

    public void reset() {
        this.level = 1;
        this.experience = 0;
    }

    public void giveLevelReward(Player player) {
        // Aquí puedes agregar efectos adicionales o recompensas al subir de nivel.
    }
}
