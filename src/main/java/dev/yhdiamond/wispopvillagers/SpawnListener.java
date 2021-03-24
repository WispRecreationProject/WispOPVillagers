package dev.yhdiamond.wispopvillagers;

import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class SpawnListener implements Listener {

    @EventHandler
    public void onTrade(VillagerAcquireTradeEvent e) {
        if (e.getRecipe().getIngredients().get(0).getType().equals(Material.EMERALD)) {
            Villager villager = (Villager) e.getEntity();
            e.setCancelled(true);
            List<MerchantRecipe> newRecipes = new ArrayList<>();
            if (villager.getRecipes() != null) {
                for (MerchantRecipe recipe : villager.getRecipes()) {
                    newRecipes.add(recipe);
                }
            }
            MerchantRecipe newRecipe = new MerchantRecipe(RandomItemMaker.getRandomItem(), 0, 100000, true, 5, 1);
            newRecipe.addIngredient(new ItemStack(Material.EMERALD, (int) (Math.random() * 7) + 1));
            newRecipes.add(newRecipe);
            villager.setRecipes(newRecipes);
        }
    }
}
