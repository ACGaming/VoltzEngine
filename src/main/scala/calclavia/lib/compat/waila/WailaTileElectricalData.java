package calclavia.lib.compat.waila;

import calclavia.lib.prefab.tile.TileElectrical;
import calclavia.lib.utility.LanguageUtility;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tgame14
 * @since 12/04/14
 */
public class WailaTileElectricalData implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return new ItemStack(accessor.getBlock());
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileEntity tile = accessor.getTileEntity();
		if (!(tile instanceof TileElectrical))
		{
			return currenttip;
		}
		TileElectrical te = (TileElectrical) tile;
		currenttip.add(LanguageUtility.getLocal("info.energylevel.waila") + " " + String.valueOf(te.getEnergy(ForgeDirection.UNKNOWN)));
		currenttip.add(LanguageUtility.getLocal("info.energycapacity.waila") + " " + String.valueOf(te.getEnergyCapacity(ForgeDirection.UNKNOWN)));

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
}