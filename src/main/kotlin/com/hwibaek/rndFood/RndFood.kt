package com.hwibaek.rndFood

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RndFood : JavaPlugin(), Listener {

    private val foodDic = mutableMapOf<Material, List<PotionEffect>>()
    
    private val effectsOrigin = mutableListOf<PotionEffect>()
    private var effectsList = mutableListOf<PotionEffect>()
    
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        this.saveDefaultConfig()
        val foods = this.config.getStringList("foods")
        val effects = this.config.getStringList("effects")
        
        effects.forEach {
            value ->
            convertEffect(value)?.let { effectsOrigin.add(PotionEffect(it, this.config.getInt("duration") * 20, this.config.getInt("amplifier"))) }
        }
        
        foods.forEach {
            value ->
            convertFood(value)?.let { foodDic.put(it, getRndEffects()) }
        }
        logger.info("랜덤음식 활성화")
    }

    override fun onDisable() {
        logger.info("랜덤음식 비활성화")
    }
    
    //특정 이펙트만 나오는걸 방지하기 위해 사이클을 돌림
    private fun getRndEffects(): List<PotionEffect> {
        val count = this.config.getInt("maxStack")
        var tempList = mutableSetOf<PotionEffect>()
        for (i in 0 until count) {
            if (effectsList.isEmpty()) {
                effectsList = effectsOrigin.shuffled().toMutableList()
            }
            for (j in 0 until effectsList.size) {
                val effect = effectsList[j]
                if (tempList.add(effect)) {
                    effectsList.remove(effect)
                    break
                }
            }
        }
        return tempList.toList()
    }
    //하드코딩
    //콘피그 파일에 있는 음식 리스트를 아이템 타입으로 파싱
    private fun convertFood(input: String): Material? {
        when (input) {
            "minecraft:apple" -> {
                return Material.APPLE
            }
            "minecraft:golden_apple" -> {
                return Material.GOLDEN_APPLE
            }
            "minecraft:enchanted_golden_apple" -> {
                return Material.ENCHANTED_GOLDEN_APPLE
            }
            "minecraft:melon_slice" -> {
                return Material.MELON_SLICE
            }
            "minecraft:sweet_berries" -> {
                return Material.SWEET_BERRIES
            }
            "minecraft:glow_berries" -> {
                return Material.GLOW_BERRIES
            }
            "minecraft:chorus_fruit" -> {
                return Material.CHORUS_FRUIT
            }
            "minecraft:carrot" -> {
                return Material.CARROT
            }
            "minecraft:golden_carrot" -> {
                return Material.GOLDEN_CARROT
            }
            "minecraft:potato" -> {
                return Material.POTATO
            }
            "minecraft:baked_potato" -> {
                return Material.BAKED_POTATO
            }
            "minecraft:poisonous_potato" -> {
                return Material.POISONOUS_POTATO
            }
            "minecraft:beetroot" -> {
                return Material.BEETROOT
            }
            "minecraft:dried_kelp" -> {
                return Material.DRIED_KELP
            }
            "minecraft:beef" -> {
                return Material.BEEF
            }
            "minecraft:cooked_beef" -> {
                return Material.COOKED_BEEF
            }
            "minecraft:porkchop" -> {
                return Material.PORKCHOP
            }
            "minecraft:cooked_porkchop" -> {
                return Material.COOKED_PORKCHOP
            }
            "minecraft:mutton" -> {
                return Material.MUTTON
            }
            "minecraft:cooked_mutton" -> {
                return Material.COOKED_MUTTON
            }
            "minecraft:chicken" -> {
                return Material.CHICKEN
            }
            "minecraft:cooked_chicken" -> {
                return Material.COOKED_CHICKEN
            }
            "minecraft:rabbit" -> {
                return Material.RABBIT
            }
            "minecraft:cooked_rabbit" -> {
                return Material.COOKED_RABBIT
            }
            "minecraft:cod" -> {
                return Material.COD
            }
            "minecraft:cooked_cod" -> {
                return Material.COOKED_COD
            }
            "minecraft:salmon" -> {
                return Material.SALMON
            }
            "minecraft:cooked_salmon" -> {
                return Material.COOKED_SALMON
            }
            "minecraft:tropical_fish" -> {
                return Material.TROPICAL_FISH
            }
            "minecraft:pufferfish" -> {
                return Material.PUFFERFISH
            }
            "minecraft:bread" -> {
                return Material.BREAD
            }
            "minecraft:cookie" -> {
                return Material.COOKIE
            }
            "minecraft:pumpkin_pie" -> {
                return Material.PUMPKIN_PIE
            }
            "minecraft:rotten_flesh" -> {
                return Material.ROTTEN_FLESH
            }
            "minecraft:spider_eye" -> {
                return Material.SPIDER_EYE
            }
            "minecraft:mushroom_stew" -> {
                return Material.MUSHROOM_STEW
            }
            "minecraft:beetroot_soup" -> {
                return Material.BEETROOT_SOUP
            }
            "minecraft:rabbit_stew" -> {
                return Material.RABBIT_STEW
            }
            "minecraft:suspicious_stew" -> {
                return Material.SUSPICIOUS_STEW
            }
            "minecraft:honey_bottle" -> {
                return Material.HONEY_BOTTLE
            }
            "minecraft:potion" -> {
                return Material.POTION
            }
        }
        return null
    }
    //하드코딩22
    //콘피그 파일에 있는 효과 리스트를 효과 타입으로 파싱
    private fun convertEffect(input: String): PotionEffectType? {
        when (input) {
            "minecraft:speed" -> {
                return PotionEffectType.SPEED
            }
            "minecraft:haste" -> {
                return PotionEffectType.HASTE
            }
            "minecraft:jump_boost" -> {
                return PotionEffectType.JUMP_BOOST
            }
            "minecraft:strength" -> {
                return PotionEffectType.STRENGTH
            }
            "minecraft:resistance" -> {
                return PotionEffectType.RESISTANCE
            }
            "minecraft:regeneration" -> {
                return PotionEffectType.REGENERATION
            }
            "minecraft:fire_resistance" -> {
                return PotionEffectType.FIRE_RESISTANCE
            }
            "minecraft:water_breathing" -> {
                return PotionEffectType.WATER_BREATHING
            }
            "minecraft:night_vision" -> {
                return PotionEffectType.NIGHT_VISION
            }
            "minecraft:absorption" -> {
                return PotionEffectType.ABSORPTION
            }
            "minecraft:health_boost" -> {
                return PotionEffectType.HEALTH_BOOST
            }
            "minecraft:saturation" -> {
                return PotionEffectType.SATURATION
            }
            "minecraft:luck" -> {
                return PotionEffectType.LUCK
            }
            "minecraft:slow_falling" -> {
                return PotionEffectType.SLOW_FALLING
            }
            "minecraft:conduit_power" -> {
                return PotionEffectType.CONDUIT_POWER
            }
            "minecraft:dolphins_grace" -> {
                return PotionEffectType.DOLPHINS_GRACE
            }
            "minecraft:instant_health" -> {
                return PotionEffectType.INSTANT_HEALTH
            }
            "minecraft:invisibility" -> {
                return PotionEffectType.INVISIBILITY
            }
            "minecraft:hero_of_the_village" -> {
                return PotionEffectType.HERO_OF_THE_VILLAGE
            }
            "minecraft:slowness" -> {
                return PotionEffectType.SLOWNESS
            }
            "minecraft:mining_fatigue" -> {
                return PotionEffectType.MINING_FATIGUE
            }
            "minecraft:nausea" -> {
                return PotionEffectType.NAUSEA
            }
            "minecraft:blindness" -> {
                return PotionEffectType.BLINDNESS
            }
            "minecraft:hunger" -> {
                return PotionEffectType.HUNGER
            }
            "minecraft:weakness" -> {
                return PotionEffectType.WEAKNESS
            }
            "minecraft:poison" -> {
                return PotionEffectType.POISON
            }
            "minecraft:wither" -> {
                return PotionEffectType.WITHER
            }
            "minecraft:instant_damage" -> {
                return PotionEffectType.INSTANT_DAMAGE
            }
            "minecraft:glowing" -> {
                return PotionEffectType.GLOWING
            }
            "minecraft:levitation" -> {
                return PotionEffectType.LEVITATION
            }
            "minecraft:bad_omen" -> {
                return PotionEffectType.BAD_OMEN
            }
            "minecraft:darkness" -> {
                return PotionEffectType.DARKNESS
            }
            "minecraft:wind_charged" -> {
                return PotionEffectType.WIND_CHARGED
            }
            "minecraft:weaving" -> {
                return PotionEffectType.WEAVING
            }
            "minecraft:oozing" -> {
                return PotionEffectType.OOZING
            }
            "minecraft:infested" -> {
                return PotionEffectType.INFESTED
            }

        }
        return null
    }
    
    @EventHandler
    fun onEat(e: PlayerItemConsumeEvent) {
        val item = foodDic[e.item.type]
        item?.forEach { value ->
            value.apply(e.player)
            logger.info("${e.player}가 ${e.item.type}을 먹고 ${value.type} 효과에 걸림!")
        }
    }
}
