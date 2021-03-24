package dev.yhdiamond.wispopvillagers;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class RandomItemMaker {
    private static WispOPVillagers plugin = WispOPVillagers.plugin;
    public static ItemStack getRandomItem() {
        List<String> mats = plugin.getConfig().getStringList("tradeitems");
        List<String> counts = plugin.getConfig().getStringList("tradeitemscount");
        List<String> enchants = plugin.getConfig().getStringList("enchantslist");
        List<String> peffects = plugin.getConfig().getStringList("peffects");
        int min = 0;
        int max = mats.size() - 1;
        double random = Math.random() * (max - min + 1) + min;
        int stackcount = Integer.parseInt(counts.get((int) random));
        ItemStack ritem = new ItemStack(Material.valueOf(mats.get((int) random).toUpperCase()), stackcount);
        if (ritem.getType() == Material.ENCHANTED_BOOK) {
            ItemMeta meta = ritem.getItemMeta();
            EnchantmentStorageMeta emeta = (EnchantmentStorageMeta) meta;
            int mine = 0;
            int maxe = enchants.size() - 1;
            double rench = Math.random() * (maxe - mine + 1) + mine;
            emeta.addStoredEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchants.get((int) rench).toLowerCase())), 10, true);
            ritem.setItemMeta(emeta);
        }
        if (ritem.getType() == Material.POTION) {
            ItemMeta meta = ritem.getItemMeta();
            PotionMeta pmeta = (PotionMeta) meta;
            int mine = 0;
            int maxe = peffects.size() - 1;
            double rench = Math.random() * (maxe - mine + 1) + mine;
            pmeta.addCustomEffect(new PotionEffect((PotionEffectType.getByName(peffects.get((int) rench).toUpperCase())), 1200, 2), true);
            pmeta.setColor(Color.YELLOW);
            pmeta.setDisplayName("OP Potion");
            ritem.setItemMeta(pmeta);
        }
        if (ritem.getType() == Material.NETHERITE_HOE){
            ItemMeta meta = ritem.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY+"Knockback C");
            meta.setLore(lore);
            meta.addEnchant(Enchantment.KNOCKBACK, 100, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            ritem.setItemMeta(meta);
        }
        return ritem;
    }
}
