package resonant.lib.network.discriminator.view

import io.netty.buffer.ByteBuf
import resonant.lib.network.ByteBufWrapper._

/**
 * Designed by on Unity 3D's packet system.
 *
 * A NetworkView is attached to any object (tileEntity) that requires data synchronization between clients.
 * @param obj - The object being tracked.
 * @author Calclavia
 */
abstract class NetworkView(obj: AnyRef)
{
  var byteBuf: ByteBuf = _
  var reading = false

  def sync[T](value: T): T =
  {
    if (reading)
      return byteBuf.read(value)
    else
      byteBuf <<< value

    return value
  }

  /**
   * Sends a remote procedure call to all targets in the network.
   * @param method
   */
  def rpc(method: String, params: Any*)
  {

  }

  object TargetType extends Enumeration
  {
    type TargetType = Value
    val all, allExcludeSelf, server = Value
  }
}
