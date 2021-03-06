package com.untamedears.humbug;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import com.google.common.base.Splitter;
import com.untamedears.humbug.Humbug;

public class Config {
  private static Config global_instance_ = null;

  // ================================================
  // Configuration defaults
  private static final boolean debug_log_ = false;
  private static final boolean anvil_enabled_ = false;
  private static final boolean ender_chest_enabled_ = false;
  private static final boolean ender_chests_placeable_ = true;
  private static final boolean villager_trades_enabled_ = false;
  private static final boolean unlimited_cauldron_enabled_ = false;
  private static final int quartz_gravel_percentage_ = 0;
  private static final boolean portalcreate_enabled_ = true;
  private static final boolean enderdragon_enabled_ = true;
  private static final boolean joinquitkick_enabled_ = true;
  private static final boolean deathpersonal_enabled_ = false;
  private static final boolean deathannounce_enabled_ = true;
  private static final boolean deathred_enabled_ = false;
  private static final boolean deathlog_enabled_ = false;
  private static final boolean endergrief_enabled_ = true;
  private static final boolean wither_enabled_ = true;
  private static final boolean wither_explosions_enabled_ = false;
  private static final boolean wither_insta_break_enabled_ = false;
  private static final boolean cobble_from_lava_enabled_ = false;
  private static final int cobble_from_lava_scan_radius_ = 0;
  private static final boolean ench_book_craftable_ = false;
  private static final boolean scale_protection_enchant_ = true;
  private static final boolean fix_rail_dup_bug_ = true;
  private static final boolean fix_vehicle_logout_bug_ = true;
  private static final int wither_skull_drop_rate_ = -1;
  private static final int player_max_health_ = 20;
  private static final boolean ender_pearl_teleportation_enabled_ = true;
  private static final boolean ender_pearl_teleportation_throttled_ = true;
  private static final double ender_pearl_launch_velocity_ = 1.0F;
  private static final boolean ench_gold_app_edible_ = false;
  private static final boolean ench_gold_app_craftable_ = false;
  // For fixing the teleport glitch
  private static final boolean fix_teleport_glitch_ = true;
  private static final boolean disallow_record_playing_ = true;
  private static final boolean allow_dye_sheep_ = true;
  private static final boolean allow_water_in_nether_ = false;
  private static final int projectile_slow_chance_ = 30;
  private static final int projectile_slow_ticks_ = 100;
  private static final int loot_multiplier_ = 1;
  private static final boolean disable_experience=true;
  private static final int xp_per_bottle=10;
  private static final String book_name_ = "A Guide to Civcraft";
  private static final String book_author_ = "dydomite";
  private static final String book_text_ =
      "    {|oWhat is CivCraft?\n"
      + "\"Civcraft is an experiment for communities & political ideologies [...] to work together to create and shape civilization or to watch it fall\"{|r\n"
      + "-Ttk2, server owner\n}|"
      + "    {|oWhat is CivCraft?{|r\n"
      + "{|lNOT just survival{|r Admins are hands-off and only handle glitches and hackers\n"
      + "{|lNOT just chaos{|r Mods allow players and towns to enforce their own rules\n"
      + "{|lNOT just roleplay{|r Nobody pretends -- conflict is genuine and heated\n}|"
      + "    {|oBasic Mechanics{|r\n"
      + "-The world is a circle\n"
      + "-Stretches 15k blocks\n"
      + "-500 block chat range\n"
      + "-Respawn in random area unless you sleep\n"
      + "-Food grows slowly\n"
      + "-Mobs spawn sparsely\n"
      + "-No Nether portals\n"
      + "-Nether biomes instead\n"
      + "-No XP from killing\n}|"
      + "   {|oCivCraft Mods{|r\n"
      + "{|lCitadel{|r: Reinforces things so it takes numerous breaks to destroy them. Locks some things too.\n"
      + "{|lMore Info:{|r {|oVisual Guide:{|r imgur.com/BnlL2 {|oWiki Page:{|r tinyurl.com/citadelmod\n"
      + "{|oType \"/help citadel\" in chat to get commands\n}|"
      + "   {|lPrison Pearl{|r: Ender Pearls trap players in the end. Others can steal back your pearl and free you -- you always know where it is so they cannot hide it\n"
      + "{|lMore Info:{|r {|oVisual Guide:{|r imgur.com/XbhkK {|oWiki Page:{|r tinyurl.com/prisperl\n}|"
      + "   {|lJuke Alert{|r: Creates 'Juke' blocks that record player activity in radius. If you steal, grief, or trespass -- people will know about it & put a bounty for you to be pearled.\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/snitchblock\n}|"
      + "   {|lMusterCull{|r: Kills some of your farm animals when there's too many to decrease lag. Mob spawners stop spawning if there are too many mobs around -- grinders must be cleared a lot.\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/mustercull\n}|"
      + "   {|lPhysical Shop{|r: Allows automated stores. Hit a sign with it's designated 'currency' to buy. Currencies are commodities and free markets dictate their worth -- no fixed prices\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/storemod\n}|"
      + "   {|lHumbug:{|r Disables some features of minecraft -- see wiki for short list. Please read it to ensure you don't waste resources on a useless block.\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/humbugwiki\n}|"
      + "   {|lFactory Mod{|r: Factories are hard to create but can mass produce goods for cheaper. Gives groups gear advantages over lone wolves. Trading may be cheaper than crafting due to this.\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/factorymod\n}|"
      + "   {|lRealistic Biomes{|r: Biomes are huge, crops grow different in different biomes. Hit ground with seed to see growth rate. Farms need sunlight. Crops grow with nobody around.\n"
      + "{|lMore Info:{|r {|oWiki Page:{|r tinyurl.com/realbiome\n}|"
      + "   {|oFurther Info{|r\n"
      + "Visit our subreddit at: {|oreddit.com/r/civcraft{|r\n"
      + "Visit the unofficial wiki: {|oCivCraft.org{|r\n"
      + "Both have player made guides on other mods, towns, and general tips";
  private static final Iterable<String> compiled_book_text_ =
      Splitter.on("}|").split(book_text_.replaceAll("\\{\\|", "\u00A7"));


  public static Config initialize(Plugin plugin) {
    if (global_instance_ == null) {
      global_instance_ = new Config(plugin);
      global_instance_.load();
    }
    return global_instance_;
  }

  private FileConfiguration config_ = null;
  private Plugin plugin_ = null;
  private Set<Integer> remove_item_drops_ = null;

  public Config(Plugin plugin) {
    plugin_ = plugin;
  }

  public void load() {
    plugin_.reloadConfig();
    FileConfiguration config = plugin_.getConfig();
    config.options().copyDefaults(true);
    config_ = config;
    // Setting specific initialization
    loadRemoveItemDrops();
  }

  public void reload() {
    plugin_.reloadConfig();
  }

  public void save() {
    plugin_.saveConfig();
  }

  public boolean getDebug() {
    return config_.getBoolean("debug", debug_log_);
  }

  public void setDebug(boolean value) {
    config_.set("debug", value);
  }

  public String getTitle(){
    return config_.getString("noobbook.name", book_name_);
  }

  public String getAuthor(){
    return config_.getString("noobbook.author", book_author_);
  }

  public List<String> getPages(){
    List<String> book_pages = new LinkedList<String>();
    for(final String text: compiled_book_text_){
      book_pages.add(text);
    }
    return book_pages;
  }

  public int getLootMultiplier(String entity_type){
    return config_.getInt("loot_multiplier." + entity_type.toLowerCase(), loot_multiplier_);
  }

  public void setLootMultiplier(String entity_type, int value){
    config_.set("loot_multiplier." + entity_type.toLowerCase(), value);
  }

  public boolean getAnvilEnabled() {
    return config_.getBoolean("anvil", anvil_enabled_);
  }

  public void setAnvilEnabled(boolean value) {
    config_.set("anvil", value);
  }

  public boolean getEnderChestEnabled() {
    return config_.getBoolean("ender_chest", ender_chest_enabled_);
  }

  public void setEnderChestEnabled(boolean value) {
    config_.set("ender_chest", value);
  }

  public boolean getEnderChestsPlaceable() {
    return config_.getBoolean("ender_chests_placeable", ender_chests_placeable_);
  }

  public void setEnderChestsPlaceable(boolean value) {
    config_.set("ender_chests_placeable", value);
  }

  public boolean getVillagerTradesEnabled() {
    return config_.getBoolean("villager_trades", villager_trades_enabled_);
  }

  public void setVillagerTradesEnabled(boolean value) {
    config_.set("villager_trades", value);
  }

  public boolean getPortalCreateEnabled() {
    return config_.getBoolean("portalcreate", portalcreate_enabled_);
  }

  public void setPortalCreateEnabled(boolean value) {
    config_.set("portalcreate", value);
  }

  public boolean getEnderDragonEnabled() {
    return config_.getBoolean("enderdragon", enderdragon_enabled_);
  }

  public void setEnderDragonEnabled(boolean value) {
    config_.set("enderdragon", value);
  }

  public boolean getUnlimitedCauldronEnabled() {
    return config_.getBoolean("unlimitedcauldron", unlimited_cauldron_enabled_);
  }

  public void setUnlimitedCauldronEnabled(boolean value) {
    config_.set("unlimitedcauldron", value);
  }

  public int getQuartzGravelPercentage() {
    return config_.getInt("quartz_gravel_percentage", quartz_gravel_percentage_);
  }

  public void setQuartzGravelPercentage(int value) {
    if (value < 0) {
      value = 0;
      Humbug.warning("quartz_gravel_percentage adjusted to 0");
    } else if (value > 100) {
      value = 100;
      Humbug.warning("quartz_gravel_percentage adjusted to 100");
    }
    config_.set("quartz_gravel_percentage", value);
  }

  public boolean getJoinQuitKickEnabled() {
    return config_.getBoolean("joinquitkick", joinquitkick_enabled_);
  }

  public void setJoinQuitKickEnabled(boolean value) {
    config_.set("joinquitkick", value);
  }

  public boolean getDeathMessagePersonalEnabled() {
    return config_.getBoolean("deathpersonal", deathpersonal_enabled_);
  }

  public void setDeathMessagePersonalEnabled(boolean value) {
    config_.set("deathpersonal", value);
  }

  public boolean getDeathAnnounceEnabled() {
    return config_.getBoolean("deathannounce", deathannounce_enabled_);
  }

  public void setDeathAnnounceEnabled(boolean value) {
    config_.set("deathannounce", value);
  }

  public boolean getDeathMessageRedEnabled() {
    return config_.getBoolean("deathred", deathred_enabled_);
  }

  public void setDeathMessageRedEnabled(boolean value) {
    config_.set("deathred", value);
  }

  public boolean getDeathLoggingEnabled() {
    return config_.getBoolean("deathlog", deathlog_enabled_);
  }

  public void setDeathLoggingEnabled(boolean value) {
    config_.set("deathlog", value);
  }

  public boolean getEndermenGriefEnabled() {
    return config_.getBoolean("endergrief", endergrief_enabled_);
  }

  public void setEndermenGriefEnabled(boolean value) {
    config_.set("endergrief", value);
  }

  public boolean getWitherEnabled() {
    return config_.getBoolean("wither", wither_enabled_);
  }

  public void setWitherEnabled(boolean value) {
    config_.set("wither", value);
  }

  public boolean getWitherExplosionsEnabled() {
    return config_.getBoolean("wither_explosions", wither_explosions_enabled_);
  }

  public void setWitherExplosionsEnabled(boolean value) {
    config_.set("wither_explosions", value);
  }

  public boolean getWitherInstaBreakEnabled() {
    return config_.getBoolean("wither_insta_break", wither_insta_break_enabled_);
  }

  public void setWitherInstaBreakEnabled(boolean value) {
    config_.set("wither_insta_break", value);
  }

  public boolean getEnchGoldAppleEdible() {
    return config_.getBoolean("ench_gold_app_edible", ench_gold_app_edible_);
  }

  public void setEnchGoldAppleEdible(boolean value) {
    config_.set("ench_gold_app_edible", value);
  }

  public boolean getEnchGoldAppleCraftable() {
    return config_.getBoolean("ench_gold_app_craftable", ench_gold_app_craftable_);
  }

  public void setEnchGoldAppleCraftable(boolean value) {
    config_.set("ench_gold_app_craftable", value);
  }

  public boolean getCobbleFromLavaEnabled() {
    return config_.getBoolean("cobble_from_lava", cobble_from_lava_enabled_);
  }

  public void setCobbleFromLavaEnabled(boolean value) {
    config_.set("cobble_from_lava", value);
  }

  public int getCobbleFromLavaScanRadius() {
    return config_.getInt("cobble_from_lava_scan_radius", cobble_from_lava_scan_radius_);
  }

  public void setCobbleFromLavaScanRadius(int value) {
    if (value < 0) {
      value = 0;
      Humbug.warning("cobble_from_lava_scan_radius adjusted to 0");
    } else if (value > 20) {  // 8000 blocks to scan at 20
      value = 20;
      Humbug.warning("cobble_from_lava_scan_radius adjusted to 20");
    }
    config_.set("cobble_from_lava_scan_radius", value);
  }

  public boolean getEnchBookCraftable() {
    return config_.getBoolean("ench_book_craftable", ench_book_craftable_);
  }

  public void setEnchBookCraftable(boolean value) {
    config_.set("ench_book_craftable", value);
  }

  public boolean getScaleProtectionEnchant() {
    return config_.getBoolean("scale_protection_enchant", scale_protection_enchant_);
  }

  public void setScaleProtectionEnchant(boolean value) {
    config_.set("scale_protection_enchant", value);
  }

  public boolean getFixRailDupBug() {
    return config_.getBoolean("fix_rail_dup_bug", fix_rail_dup_bug_);
  }

  public void setFixRailDupBug(boolean value) {
    config_.set("fix_rail_dup_bug", value);
  }

  public boolean getFixVehicleLogoutBug() {
    return config_.getBoolean("fix_vehicle_logout_bug", fix_vehicle_logout_bug_);
  }

  public void setFixVehicleLogoutBug(boolean value) {
    config_.set("fix_vehicle_logout_bug", value);
  }

  public int getWitherSkullDropRate() {
    return config_.getInt("wither_skull_drop_rate", wither_skull_drop_rate_);
  }

  public void setWitherSkullDropRate(int value) {
    config_.set("wither_skull_drop_rate", value);
  }

  public int getMaxHealth() {
    return config_.getInt("player_max_health", player_max_health_);
  }

  public void setMaxHealth(int value) {
    config_.set("player_max_health", value);
  }

  public boolean getTeleportFixEnabled() {
    return config_.getBoolean("fix_teleport_glitch", fix_teleport_glitch_);
  }

  public void setTeleportFixEnabled(boolean value) {
    config_.set("fix_teleport_glitch", value);
  }

  public boolean getEnderPearlTeleportationEnabled() {
    return config_.getBoolean("ender_pearl_teleportation", ender_pearl_teleportation_enabled_);
  }

  public void setEnderPearlTeleportationEnabled(boolean value) {
    config_.set("ender_pearl_teleportation", value);
  }

  public boolean getThrottlePearlTeleport() {
    return config_.getBoolean("ender_pearl_teleportation_throttled", ender_pearl_teleportation_throttled_);
  }

  public void setThrottlePearlTeleport(boolean value) {
    config_.set("ender_pearl_teleportation_throttled", value);
  }

  public double getEnderPearlLaunchVelocity() {
    return config_.getDouble("ender_pearl_launch_velocity", ender_pearl_launch_velocity_);
  }
  
  public void setEnderPearlLaunchVelocity(double value) {
    config_.set("ender_pearl_launch_velocity", value);
  }

  public boolean getDisallowRecordPlaying() {
    return config_.getBoolean("disallow_record_playing", disallow_record_playing_);
  }

  public void setDisallowRecordPlaying(boolean value) {
    config_.set("disallow_record_playing", value);
  }

  public boolean getAllowDyeSheep() {
    return config_.getBoolean("allow_dye_sheep", allow_dye_sheep_);
  }

  public void setAllowDyeSheep(boolean value) {
    config_.set("allow_dye_sheep", value);
  }

  public boolean getAllowWaterInNether() {
    return config_.getBoolean("allow_water_in_nether", allow_water_in_nether_);
  }

  public void setAllowWaterInNether(boolean value) {
    config_.set("allow_water_in_nether", value);
  }

  public int getProjectileSlowChance() {
    return config_.getInt("projectile_slow_chance", projectile_slow_chance_);
  }

  public void setProjectileSlowChance(int value) {
    config_.set("projectile_slow_chance", value);
  }

  public int getProjectileSlowTicks() {
    int ticks = config_.getInt("projectile_slow_ticks", projectile_slow_ticks_);
    if (ticks <= 0 || ticks > 600) {
      ticks = 100;
    }
    return ticks;
  }
  
  public boolean getDisableExperience(){
    return config_.getBoolean("disable_experience", disable_experience);
  }
  
  public int getXPPerBottle(){
    return config_.getInt("xp_per_bottle", xp_per_bottle);
  }

  public void setProjectileSlowTicks(int value) {
    config_.set("projectile_slow_ticks", value);
  }

  private void loadRemoveItemDrops() {
    if (!config_.isSet("remove_mob_drops")) {
      remove_item_drops_ = new HashSet<Integer>(4);
      return;
    }
    remove_item_drops_ = new HashSet<Integer>();
    if (!config_.isList("remove_mob_drops")) {
      Integer val = config_.getInt("remove_mob_drops");
      if (val == null) {
        config_.set("remove_mob_drops", new LinkedList<Integer>());
        Humbug.info("remove_mob_drops was invalid, reset");
        return;
      }
      remove_item_drops_.add(val);
      List<Integer> list = new LinkedList<Integer>();
      list.add(val);
      config_.set("remove_mob_drops", val);
      Humbug.info("remove_mob_drops was not an Integer list, converted");
      return;
    }
    remove_item_drops_.addAll(config_.getIntegerList("remove_mob_drops"));
  }

  public boolean doRemoveItemDrops() {
    return !remove_item_drops_.isEmpty();
  }

  public Set<Integer> getRemoveItemDrops() {
    return Collections.unmodifiableSet(remove_item_drops_);
  }

  public void addRemoveItemDrop(int item_id) {
    if (item_id < 0) {
      return;
    }
    remove_item_drops_.add(item_id);
    List<Integer> list;
    if (!config_.isSet("remove_mob_drops")) {
      list = new LinkedList<Integer>();
    } else {
      list = config_.getIntegerList("remove_mob_drops");
    }
    list.add(item_id);
    config_.set("remove_mob_drops", list);
  }

  public void removeRemoveItemDrop(int item_id) {
    if (item_id < 0) {
      return;
    }
    if (!remove_item_drops_.remove(item_id)) {
      return;
    }
    List<Integer> list = config_.getIntegerList("remove_mob_drops");
    list.remove((Object)item_id);
    config_.set("remove_mob_drops", list);
  }

  public void setRemoveItemDrops(Set<Integer> item_ids) {
    remove_item_drops_ = new HashSet<Integer>();
    remove_item_drops_.addAll(item_ids);
    List<Integer> list = new LinkedList<Integer>();
    list.addAll(item_ids);
    config_.set("remove_mob_drops", list);
  }

  public String toDisplayRemoveItemDrops() {
    StringBuilder sb = new StringBuilder();
    for (Integer item_id : remove_item_drops_) {
      Material mat = Material.getMaterial(item_id);
      if (mat == null) {
        sb.append(item_id);
      } else {
        sb.append(mat.toString());
      }
      sb.append(",");
    }
    return sb.toString();
  }
}
