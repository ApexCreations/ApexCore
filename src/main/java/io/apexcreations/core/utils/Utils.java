package io.apexcreations.core.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat();

    static {
        DECIMAL_FORMAT.setMaximumFractionDigits(2);
        DECIMAL_FORMAT.setMinimumFractionDigits(0);
        DECIMAL_FORMAT.setGroupingUsed(false);
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    public static String toString(Location location) {
        return (location
                .getWorld().getName() + ":" +
                location.getX()
                + ":" + location.getY() + ":" + location.getZ() + ":"
                + location.getYaw() + ":" + location.getPitch());
    }

    public static String formatCurrency(BigDecimal bigDecimal) {
        return DECIMAL_FORMAT.format(bigDecimal);
    }

    public static Location fromString(String string) {
        String[] split = string.split(":");
        return new Location(
                Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }

    /*
    Taken from:
    https://github.com/Exorath/ExoCommons/blob/master/src/main/java/com/exorath/commons/ItemStackSerialize.java
     */
    public static JsonObject fromItemStack(ItemStack is) {
        JsonObject itemObject = new JsonObject();
        itemObject.addProperty("material", is.getType().name());
        itemObject.addProperty("amount", is.getAmount());
        itemObject.addProperty("durability", is.getDurability());
        if (is.getItemMeta().hasDisplayName()) {
            itemObject.addProperty("displayName", is.getItemMeta().getDisplayName());
        }
        if (is.getItemMeta().hasLore()) {
            JsonArray lore = new JsonArray();
            is.getItemMeta().getLore().forEach(string -> {
                lore.add(new JsonPrimitive(string));
            });
            itemObject.add("lore", lore);
        }
        if (is.getItemMeta().hasEnchants()) {
            JsonArray enchantments = new JsonArray();
            is.getItemMeta().getEnchants().keySet().forEach(enchantment -> {
                JsonObject ench = new JsonObject();
                ench.addProperty("enchantment", enchantment.getName());
                ench.addProperty("level", is.getItemMeta().getEnchants().get(enchantment));
                enchantments.add(ench);
            });
            itemObject.add("enchantments", enchantments);
        }
        return itemObject;
    }

    public static ItemStack toItemStack(JsonObject object) {
        Material mat = Material.getMaterial(object.get("material").getAsString());

        int amount = object.has("amount") ? object.get("amount").getAsInt() : 1;
        short durability = object.has("durability") ? object.get("durability").getAsShort() : 0;
        ItemStack item = new ItemStack(mat, amount, durability);
        ItemMeta meta = item.getItemMeta();
        if (object.has("displayName")) {
            meta.setDisplayName(object.get("displayName").getAsString());
        }
        if (object.has("lore")) {
            List<String> lore = new ArrayList<>();
            object.get("lore").getAsJsonArray().forEach(element -> lore.add(element.getAsString()));
            meta.setLore(lore);
        }

        if (object.get("enchantments") != null) {
            object.get("enchantments").getAsJsonArray().forEach(element -> {
                JsonObject enchantment = element.getAsJsonObject();
                meta.addEnchant(Enchantment.getByName(enchantment.get("enchantment").getAsString()),
                        enchantment.get("level").getAsInt(), true);
            });
        }
        item.setItemMeta(meta);
        return item;
    }
}
