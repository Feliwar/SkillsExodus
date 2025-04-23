package com.exodus.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager implements Listener {

    private final ExodusSkills plugin;

    public GUIManager(ExodusSkills plugin) {
        this.plugin = plugin;
    }

    public void openSkillsMenu(Player player) {
        Inventory menu = plugin.getServer().createInventory(null, 27, "Habilidades");

        for (SkillType skillType : SkillType.values()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(skillType.getDisplayName());
            meta.setLore(java.util.Arrays.asList(
                    skillType.getDescription(),
                    "Nivel: " + plugin.getPlayerDataManager().getPlayerSkills(player.getUniqueId()).get(skillType).getLevel(),
                    "XP: " + plugin.getPlayerDataManager().getPlayerSkills(player.getUniqueId()).get(skillType).getExperience()
            ));

            item.setItemMeta(meta);
            menu.addItem(item);
        }

        player.openInventory(menu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Habilidades")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

            Player player = (Player) event.getWhoClicked();
            SkillType clickedSkill = SkillType.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().toUpperCase());

            if (clickedSkill != null) {
                player.sendMessage("Haz clic en " + clickedSkill.getDisplayName());
            }
        }
    }
}
