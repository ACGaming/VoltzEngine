package com.builtbroken.mc.framework.tile;

import com.builtbroken.mc.prefab.tile.interfaces.tile.ITile;

import java.util.HashMap;

/**
 * Handler for most functionality for Tile Framework System
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 5/15/2016.
 */
public class TileFramework
{
    /** Map of {@link ITile} to it's block property file, keep in mind properties are shared for meta value tiles */
    protected static final HashMap<Class<? extends ITile>, BlockProperties> classToData = new HashMap();
    /** Map of {@link ITile} to it's ID name, id should be MOD:NAME to prevent conflicts */
    protected static final HashMap<Class<? extends ITile>, String> tileNameToClass = new HashMap();
    /** Map of old TileEntity names to new reference objects, used to load old maps */
    protected static final HashMap<String, Class<? extends ITile>> legacyTEMap = new HashMap();

    /**
     * Gets the block property data object for the tile object
     *
     * @param aClass - class
     * @return property file if one was registered
     */
    public static BlockProperties getBlockDataFor(Class<? extends ITile> aClass)
    {
        if (classToData.containsKey(aClass))
        {
            return classToData.get(aClass);
        }
        return null;
    }
}