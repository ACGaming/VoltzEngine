package com.builtbroken.mc.lib.world.edit;

import com.builtbroken.mc.api.event.TriggerCause;
import com.builtbroken.mc.api.event.WorldChangeActionEvent;
import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.lib.transform.vector.Location;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

import java.util.Collection;
import java.util.Iterator;

/**
 * Low priority Multi-thread for IWorldChangeActions
 * <p/>
 * Calculates the impact then removes X amount of blocks at the end of the world tick
 *
 * @author Darkguardsman
 */
public class ThreadWorldChangeAction extends Thread
{
    /** Location of the Blast */
    public final Location position;
    /** Blast instance */
    public final IWorldChangeAction blast;
    /** Trigger cause of the blast */
    public final TriggerCause triggerCause;
    /** Blocks per tick limiter */
    public int blocksPerTick = 20;

    /** Blocks to remove from the world */
    private Collection<BlockEdit> effectedBlocks;

    /**
     * Constructor, nothing should be null and blast should be created with the center equaling
     * vec param. If its isn't it will cause events triggered to return the incorrect results.
     *
     * @param vec          - location of the blast, should be the center
     * @param blast        - blast instance used to remove blocks and build a list
     * @param triggerCause - cause of the explosion
     */
    public ThreadWorldChangeAction(Location vec, IWorldChangeAction blast, TriggerCause triggerCause)
    {
        super("WorldChangeAction[" + vec + ", " + blast + "]");
        this.position = vec;
        this.blast = blast;
        this.triggerCause = triggerCause;
        this.setPriority(Thread.NORM_PRIORITY);
    }

    @Override
    public void run()
    {
        try
        {
            //Collects the init list of blocks from the blast
            effectedBlocks = blast.getEffectedBlocks();

            //Triggers an event allowing other mods to edit the block list
            MinecraftForge.EVENT_BUS.post(new WorldChangeActionEvent.FinishedCalculatingEffectEvent(position, effectedBlocks, blast, triggerCause));

            //If we have blocks to edit then register with the event handler
            if (effectedBlocks != null && !effectedBlocks.isEmpty())
            {
                FMLCommonHandler.instance().bus().register(this);
            }
        } catch (Exception e)
        {
            Engine.instance.logger().error("World Change action thread[" + this + "] has failed to execute correctly.", e);
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        try
        {
            if (event.side == Side.SERVER && event.phase == TickEvent.Phase.END)
            {
                Iterator<BlockEdit> it = effectedBlocks.iterator();
                int c = 0;
                while (it.hasNext() && c++ <= blocksPerTick)
                {
                    try
                    {
                        blast.handleBlockPlacement(it.next());
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    it.remove();
                }
            }
            if (effectedBlocks == null || effectedBlocks.isEmpty())
            {
                FMLCommonHandler.instance().bus().unregister(this);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}