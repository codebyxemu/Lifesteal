/*
 * Copyright 2022, Palmen (palmen.gg) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential.
 *
 * Written by Anders F Landgraff, July 2022.
 */

package codes.xemu.lifestealcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

	private ItemStack item;

	public ItemBuilder(ItemStack item, boolean edit) {
		if (edit) this.item = item;
		else this.item = item.clone();
	}

	public ItemBuilder(Material mat, int amount) {
		item = new ItemStack(mat, amount);
	}

	public ItemBuilder(Material mat, int amount, short durability) {
		item = new ItemStack(mat, amount, durability);
	}

	public static ItemBuilder of(ItemStack item) {
		return of(item, false);
	}

	public static ItemBuilder of(ItemStack item, boolean edit) {
		return new ItemBuilder(item, edit);
	}

	public static ItemBuilder of(Material material) {
		return new ItemBuilder(material, 1);
	}

	public static ItemBuilder of(Material material, int amount) {
		return new ItemBuilder(material, amount);
	}

	public static ItemBuilder of(Material material, int amount, short durability) {
		return new ItemBuilder(material, amount, durability);
	}



	/*
	 * Material
	 */



	public void setMaterial(Material material) {
		this.item.setType(material);
	}



	/*
	 * Item Name
	 */



	/**
	 * Set the name of this item.
	 *
	 * @param   name
	 *          The new name of this item.
	 */
	public ItemBuilder setName(String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Reset the name of this item.
	 */
	public ItemBuilder resetName() {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(null);
		item.setItemMeta(meta);
		return this;
	}



	/*
	 * Item Lore
	 */



	/**
	 * Set the lore of this item.
	 *
	 * @param   lore
	 *          The lore to give this item.
	 */
	public ItemBuilder setLore(String... lore) {
		if (lore == null) {
			ItemMeta meta = item.getItemMeta();
			meta.setLore(null);
			item.setItemMeta(meta);
			return this;
		}
		for (int i = 0; i < lore.length; i++) lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
		return setLore(Arrays.asList(lore));
	}

	/**
	 * Set the lore of this item.
	 *
	 * @param   lore
	 *          The lore to give this item.
	 */
	public ItemBuilder setLore(List<String> lore) {
		if (lore == null) {
			ItemMeta meta = item.getItemMeta();
			meta.setLore(null);
			item.setItemMeta(meta);
			return this;
		}
		List<String> lines = new ArrayList<>();
		for (String s : lore) lines.add(ChatColor.translateAlternateColorCodes('&', s));

		ItemMeta meta = item.getItemMeta();
		meta.setLore(lines);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Add a lore line to this item.
	 *
	 * @param   line
	 *          The lore line to add.
	 */
	public ItemBuilder addLoreLine(String line) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (meta.hasLore()) lore = new ArrayList<>(meta.getLore());
		lore.add(line);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Add a lore line to this item.
	 *
	 * @param   line
	 *          The lore line to add.
	 * @param   pos
	 *          The index of where to put the lore line.
	 */
	public ItemBuilder addLoreLine(String line, int pos) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>(meta.getLore());
		lore.set(pos, line);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Remove a lore line from this item.
	 *
	 * @param   line
	 *          The lore line to remove.
	 */
	public ItemBuilder removeLoreLine(String line) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>(meta.getLore());
		if (!lore.contains(line)) return this;
		lore.remove(line);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Remove a lore line from this item.
	 *
	 * @param   index
	 *          The index of the lore line to remove.
	 */
	public ItemBuilder removeLoreLine(int index) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>(meta.getLore());
		if (index < 0 || index > lore.size()) return this;
		lore.remove(index);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}


	/*
	 * Enchantments
	 */



	/**
	 * Add an enchantment to the item.
	 *
	 * @param   enchantment
	 *          The enchantment to add.
	 * @param   level
	 *          The level of the enchantment.
	 */
	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchantment, level, true);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Add an unsafe enchantment to the item.
	 *
	 * @param   enchantment
	 *          The enchantment to add.
	 * @param   level
	 *          The level of the enchantment.
	 */
	public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
		item.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	/**
	 * Remove an enchantment from the item.
	 *
	 * @param   enchantment
	 *          The enchantment to remove.
	 */
	public ItemBuilder removeEnchantment(Enchantment enchantment) {
		item.removeEnchantment(enchantment);
		return this;
	}



	/*
	 * Durability
	 */



	/**
	 * Set the durability of this item.
	 *
	 * @param   dur
	 *          The durability to set.
	 */
	public ItemBuilder setDurability(short dur) {
		item.setDurability(dur);
		return this;
	}



	/*
	 * Item Flag
	 */



	/**
	 * Set the given ItemFlags of this item.
	 *
	 * @param   flags
	 *          The ItemFlags to set.
	 */
	public ItemBuilder setItemFlags(ItemFlag... flags) {
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(flags);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Remove the given ItemFlags from this item.
	 *
	 * @param   flags
	 *          The ItemFlags to remove.
	 */
	public ItemBuilder removeItemFlags(ItemFlag... flags) {
		ItemMeta meta = item.getItemMeta();
		for (ItemFlag flag : flags) {
			if (meta.hasItemFlag(flag)) meta.removeItemFlags(flag);
		}
		item.setItemMeta(meta);
		return this;
	}



	/*
	 * Other
	 */



	/**
	 * Sets the RGB color of the item if it is
	 * leather armor.
	 *
	 * @param   r
	 *          Red
	 * @param   g
	 *          Green
	 * @param   b
	 *          Blue
	 */
	public ItemBuilder setLeatherColor(int r, int g, int b) {
		if (!(item.getItemMeta() instanceof LeatherArmorMeta)) return this;
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(r, g, b));
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * Set the item amount of the item.
	 *
	 * @param   amount
	 *          The item amount.
	 */
	public ItemBuilder setAmount(int amount) {
		item.setAmount(amount);
		return this;
	}

	/**
	 * Set if this item should be unbreakable.
	 *
	 * @param   unbreakable
	 *          If this item should be unbreakable.
	 */
	public ItemBuilder setUnbreakable(boolean unbreakable) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(unbreakable);
		item.setItemMeta(meta);
		return this;
	}

	public ItemStack build() {
		return item;
	}
}
