package universalelectricity.core.electricity;

import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import universalelectricity.core.block.IConductor;
import universalelectricity.core.block.IConnectionProvider;

/**
 * The Electrical Network in interface form.
 * 
 * @author Calclavia
 * 
 */
public interface IElectricityNetwork
{
	/**
	 * Produces electricity in this electrical network.
	 * 
	 * @return Rejected energy in Joules.
	 */
	public float produce(ElectricityPack electricityPack, TileEntity... ignoreTiles);

	/**
	 * Gets the total amount of electricity requested/needed in the electricity network.
	 * 
	 * @param ignoreTiles The TileEntities to ignore during this calculation (optional).
	 */
	public float getRequest(TileEntity... ignoreTiles);

	/**
	 * @return Gets a list of TileEntities that implements IElectrical (machines).
	 * 
	 */
	public Set<TileEntity> getElectrical();

	/**
	 * @return A list of all conductors in this electrical network.
	 */
	public Set<IConductor> getConductors();

	/**
	 * @return The total amount of resistance of this electrical network. In Ohms.
	 */
	public float getTotalResistance();

	/**
	 * @return The lowest amount of current (amperage) that this electrical network can tolerate.
	 */
	public float getLowestCurrentCapacity();

	/**
	 * Cleans up and updates the list of conductors in the electricity network, removing dead ones.
	 */
	public void cleanUpConductors();

	/**
	 * Refreshes and recalculates wire connections in this electrical network.
	 * 
	 */
	public void refresh();

	/**
	 * Merges another electrical network into this one, destroying the other network's existence.
	 * 
	 * @param network
	 */
	public void merge(IElectricityNetwork network);

	/**
	 * Splits the electricity network at a given TileEntity position. Called usually when a wire is
	 * broken to split the electrical network.
	 * 
	 * @param splitPoint - The TileEntity that is being split.
	 */
	public void split(IConnectionProvider splitPoint);

}
