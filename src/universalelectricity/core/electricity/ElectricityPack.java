package universalelectricity.core.electricity;

/**
 * A simple way to store electrical data.
 * 
 * @author Calclavia
 * 
 */
public class ElectricityPack implements Cloneable
{
	public float amperes;
	public float voltage;

	public ElectricityPack(float amperes, float voltage)
	{
		this.amperes = amperes;
		this.voltage = voltage;
	}

	public ElectricityPack()
	{
		this(0, 0);
	}

	public static ElectricityPack getFromWatts(float watts, float voltage)
	{
		return new ElectricityPack(watts / voltage, voltage);
	}

	public float getWatts()
	{
		return getWatts(amperes, voltage);
	}

	public float getConductance()
	{
		return getConductance(amperes, voltage);
	}

	public float getResistance()
	{
		return getResistance(amperes, voltage);
	}

	public static float getJoules(float watts, float seconds)
	{
		return watts * seconds;
	}

	public static float getJoules(float amps, float voltage, float seconds)
	{
		return amps * voltage * seconds;
	}

	public static float getWattsFromJoules(float joules, float seconds)
	{
		return joules / seconds;
	}

	public static float getAmps(float watts, float voltage)
	{
		return watts / voltage;
	}

	public static float getAmps(float ampHours)
	{
		return ampHours * 3600;
	}

	public static float getAmpsFromWattHours(float wattHours, float voltage)
	{
		return getWatts(wattHours) / voltage;
	}

	public static float getWattHoursFromAmpHours(float ampHours, float voltage)
	{
		return ampHours * voltage;
	}

	public static float getAmpHours(float amps)
	{
		return amps / 3600;
	}

	public static float getWatts(float amps, float voltage)
	{
		return amps * voltage;
	}

	public static float getWatts(float wattHours)
	{
		return wattHours * 3600;
	}

	public static float getWattHours(float watts)
	{
		return watts / 3600;
	}

	public static float getWattHours(float amps, float voltage)
	{
		return getWattHours(getWatts(amps, voltage));
	}

	public static float getResistance(float amps, float voltage)
	{
		return voltage / amps;
	}

	public static float getConductance(float amps, float voltage)
	{
		return amps / voltage;
	}

	@Override
	public String toString()
	{
		return "ElectricityPack [Amps:" + this.amperes + " Volts:" + this.voltage + "]";
	}

	@Override
	public ElectricityPack clone()
	{
		return new ElectricityPack(this.amperes, this.voltage);
	}

	public boolean isEqual(ElectricityPack electricityPack)
	{
		return this.amperes == electricityPack.amperes && this.voltage == electricityPack.voltage;
	}
}
