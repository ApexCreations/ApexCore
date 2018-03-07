package io.apexcreations.core.api.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

  private ItemStack itemStack;
  private ItemMeta meta;
  private List<String> lore;

  public ItemBuilder(ItemStack itemStack, boolean skullMeta) {
    this.itemStack = itemStack.clone();
    this.meta =
        (skullMeta) ? (SkullMeta) this.itemStack.getItemMeta() : this.itemStack.getItemMeta();
    this.lore = (this.meta.hasLore() ? this.meta.getLore() : new ArrayList<>());
  }

  public ItemBuilder(ItemStack itemStack) {
    this(itemStack, false);
  }

  public ItemBuilder(Material mat) {
    this(new ItemStack(mat));
  }

  public static ItemBuilder create(ItemStack item, boolean skullMeta) {
    return new ItemBuilder(item, skullMeta);
  }

  public static ItemBuilder create(Material mat) {
    return create(new ItemStack(mat));
  }

  public static ItemBuilder create(ItemStack item) {
    return create(item, false);
  }

  public ItemBuilder amount(int amount) {
    this.itemStack.setAmount(amount);
    return this;
  }

  public ItemBuilder type(Material newType) {
    this.itemStack.setType(newType);
    return this;
  }

  public ItemBuilder durability(byte durability) {
    this.itemStack.setDurability(durability);
    return this;
  }

  public ItemBuilder name(String newName) {
    this.meta.setDisplayName(this.translate(newName));
    return this;
  }

  public ItemBuilder clearLore() {
    this.lore.clear();
    return this;
  }

  public ItemBuilder lore(String s) {
    this.lore.add(this.translate(s));
    return this;
  }

  public ItemBuilder lore(String... strings) {
    Arrays.stream(strings).forEach(this::lore);
    return this;
  }

  public ItemBuilder setSkullOwner(String owningPlayer) {
    ((SkullMeta) this.meta).setOwner(owningPlayer);
    return this;
  }

  public ItemStack build() {
    this.meta.setLore(this.lore);
    this.itemStack.setItemMeta(this.meta);
    return this.itemStack;
  }

  private String translate(String s) {
    return ChatColor.translateAlternateColorCodes('&', s);
  }
}