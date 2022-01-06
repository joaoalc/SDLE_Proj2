package gnutella.handlers

import gnutella.messages.Ping
import gnutella.peer.Neighbour
import gnutella.peer.Peer

class PingHandler(
    private val peer: Peer,
    private val ping: Ping,
) : MessageHandler(ping) {
    override fun run() {
        if (ping.timeToLive == 0 || ping.hops == 0) {
            println("No time to live and/or num hops left in this message. Not propagating.")
            return
        }

        // Duplicate query received. Ignore.
        if (peer.cache.containsPing(ping))
            return

        peer.cache.addPing(ping)

        // Increment hops and decrement time to live
        ping.hops++
        ping.timeToLive--

        // Don't propagate if it's reached the hop limit
        if (ping.timeToLive <= 0)
            return

        // We're the propagator now
        val prevPropagator = ping.propagatorId
        ping.propagatorId = peer.user.username

        // Forward ping to neighbours
        peer.forwardMessage(ping, prevPropagator)
    }
}