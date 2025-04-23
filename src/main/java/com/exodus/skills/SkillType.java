package com.exodus.skills;

public enum SkillType {
    STRENGTH("Fuerza", "Aumenta el daño cuerpo a cuerpo", null),
    AGILITY("Agilidad", "Aumenta la velocidad de movimiento", null),
    MINING("Minería", "Mejora la eficiencia al picar", null),

    FISHING("Pesca", "Aumenta la probabilidad de encontrar tesoros", "rpgskills.skill.fishing"),
    MAGIC("Magia", "Te da poderes especiales al subir de nivel", "rpgskills.skill.magic"),
    ARCHERY("Arquería", "Aumenta el daño con arco", "rpgskills.skill.archery"),
    ALCHEMY("Alquimia", "Mejora el efecto y duración de pociones", "rpgskills.skill.alchemy"),
    EXCAVATION("Excavación", "Mejora habilidades de excavación", "rpgskills.skill.excavation"),
    COMBAT("Combate", "Aumenta el daño crítico", "rpgskills.skill.combat"),
    DEFENSE("Defensa", "Reduce el daño recibido", "rpgskills.skill.defense");

    private final String displayName;
    private final String description;
    private final String permission;

    SkillType(String displayName, String description, String permission) {
        this.displayName = displayName;
        this.description = description;
        this.permission = permission;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isUnlockedByDefault() {
        return permission == null;
    }
}
