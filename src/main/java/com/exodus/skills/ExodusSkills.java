package com.exodus.skills;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ExodusSkills extends JavaPlugin {

    private static ExodusSkills instance;
    private PlayerDataManager playerDataManager;
    private LevelManager levelManager;
    private GUIManager guiManager;

    @Override
    public void onEnable() {
        instance = this;

        // Cargar configuración
        saveDefaultConfig();  // Si no existe el archivo config.yml, se crea con valores predeterminados
        FileConfiguration config = getConfig();  // Obtiene la configuración cargada

        // Usar configuraciones
        boolean enableSounds = config.getBoolean("general.enable-sounds", true);
        boolean enableParticles = config.getBoolean("general.enable-particles", true);
        String rewardItem = config.getString("general.reward-item", "DIAMOND_SWORD");
        int maxLevel = config.getInt("experience.max-level", 50);

        // Inicializar los sistemas
        this.playerDataManager = new PlayerDataManager(this);
        this.levelManager = new LevelManager(this, enableSounds, enableParticles, rewardItem, maxLevel);
        this.guiManager = new GUIManager(this);

        // Registrar eventos
        getServer().getPluginManager().registerEvents(playerDataManager, this);
        getServer().getPluginManager().registerEvents(guiManager, this);

        // Registrar comando
        getCommand("skills").setExecutor(guiManager);

        getLogger().info("ExodusSkills ha sido activado.");
    }

    @Override
    public void onDisable() {
        playerDataManager.saveAll();
        getLogger().info("ExodusSkills ha sido desactivado.");
    }

    public static ExodusSkills getInstance() {
        return instance;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }
}
