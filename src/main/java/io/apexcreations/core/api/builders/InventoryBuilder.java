package io.apexcreations.core.api.builders;

import io.apexcreations.core.ApexCore;
import java.util.Arrays;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder implements Listener {

  private String inventoryName;
  private int inventorySlots;
  private Inventory inventory;
  private Consumer<InventoryClickEvent> eventConsumer;

  private InventoryBuilder(String inventoryName, int slots) {
    this.inventoryName = inventoryName;
    this.inventorySlots = slots;
    this.inventory = Bukkit.createInventory(null, slots, this.translate(inventoryName));
  }

  private InventoryBuilder(String inventoryName) {
    this(inventoryName, 9);
  }

  private InventoryBuilder(int slots) {
    this("", slots);
  }

  public static InventoryBuilder create(String inventoryName, int slots) {
    return new InventoryBuilder(inventoryName, slots);
  }

  public static InventoryBuilder create(String inventoryName) {
    return new InventoryBuilder(inventoryName);
  }

  public static InventoryBuilder create(int slots) {
    return new InventoryBuilder(slots);
  }

  public InventoryBuilder clearItem(int slot) {
    this.inventory.setItem(slot, null);
    return this;
  }

  public InventoryBuilder setItem(ItemStack item, int slot) {
    this.inventory.setItem(slot, item);
    return this;
  }

  public InventoryBuilder setItem(ItemBuilder item, int slot) {
    this.inventory.setItem(slot, item.build());
    return this;
  }

  public InventoryBuilder addItem(ItemBuilder item) {
    this.inventory.addItem(item.build());
    return this;
  }

  public InventoryBuilder addItem(ItemStack item) {
    this.inventory.addItem(item);
    return this;
  }

  public InventoryBuilder open(Player player) {
    player.openInventory(this.getInventory());
    return this;
  }

  public InventoryBuilder open(Player... players) {
    Arrays.stream(players).forEach(player -> player.openInventory(this.getInventory()));
    return this;
  }

  private String translate(String s) {
    return ChatColor.translateAlternateColorCodes('&', s);
  }

  public String getInventoryName() {
    return this.inventoryName;
  }

  public int getInventorySlots() {
    return this.inventorySlots;
  }

  public Inventory getInventory() {
    return this.inventory;
  }

  public InventoryBuilder registerAsListener() {
    Bukkit.getServer().getPluginManager().registerEvents(this, ApexCore.getInstance());
    return this;
  }

  public InventoryBuilder onClick(Consumer<InventoryClickEvent> eventConsumer) {
    this.eventConsumer = eventConsumer;
    return this;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!event.getInventory().getTitle().equals(this.inventory.getTitle())) {
      return;
    }
    this.eventConsumer.accept(event);
  }

  public void clearItems() {
    this.inventory.clear();
  }
}
