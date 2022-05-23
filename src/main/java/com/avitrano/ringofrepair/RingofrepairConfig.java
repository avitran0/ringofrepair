package com.avitrano.ringofrepair;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import static com.avitrano.ringofrepair.Ringofrepair.CONFIG;
import static com.avitrano.ringofrepair.Ringofrepair.MODID;

@Config(name = MODID)
public class RingofrepairConfig implements ConfigData {
    @Comment("delay between repairs, in ticks (20 ticks = 1 second)")
    int repairDelay = 60;
    int repairDelayNetherite = 20;

    public static ConfigHolder<RingofrepairConfig> init()
    {
        ConfigHolder<RingofrepairConfig> holder = AutoConfig.register(RingofrepairConfig.class, JanksonConfigSerializer::new);

        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((s, m) ->
                AutoConfig.getConfigHolder(RingofrepairConfig.class).load());

        return holder;
    }
}
