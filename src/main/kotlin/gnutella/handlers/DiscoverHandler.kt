package gnutella.handlers

import gnutella.messages.Discover
import gnutella.messages.Send
import gnutella.peer.Peer
import java.util.*

class DiscoverHandler(
	private val peer: Peer,
	private var discover: Discover,
) : MessageHandler(discover) {
	override fun run() {
		discover = discover.cloneThis()
        
		discover.hops++
		discover.timeToLive--

		val previousPropagator = discover.propagator
		discover.propagator = peer

		// Get Posts
		val posts = peer.user.storage.findMatchingPosts(discover.keyword)

		if (posts.isNotEmpty()) {
			val response = Send(UUID.randomUUID(), peer, posts.toList(), true)

			peer.sendMessageTo(response, discover.source)
		}

		if (discover.timeToLive == 0)
			return

		// Forward ping to neighbours
		peer.forwardMessage(discover, previousPropagator)
	}
}
