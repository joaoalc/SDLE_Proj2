package gnutella.handlers

import gnutella.Constants
import social.User
import gnutella.messages.Query
import gnutella.messages.QueryHit
import gnutella.peer.Peer

class QueryHandler(
    private val peer: Peer,
    private var query: Query,
) : MessageHandler(query) {
    override fun run() {
        // Duplicate query received. Ignore.
        if (query in peer.cache) {
            if (Constants.LOGGING)
                println("Peer ${peer.user.username} | Received duplicate query.")

            return
        }

        // Add to cache
        peer.cache.addQuery(query)

        // Increment hops and decrement time to live
        query = query.cloneThis()
        query.hops++
        query.timeToLive--

        if (Constants.LOGGING)
            println("Peer ${peer.user.username} propagating")

        // We're the propagator now
        val prevPropagator = query.propagator
        query.propagator = peer

        // Send QueryHit back if node had the desired data
        if (User(query.keyword) in peer.user.storage.posts) {
            val response = QueryHit(query.ID, peer, peer.user.storage.digest(User(query.keyword)))

            peer.sendMessageTo(response, prevPropagator)
        }

        if (query.timeToLive == 0) {
            println("No time to live and/or num hops left in this message. Not propagating.")
            return
        }

        if (Constants.LOGGING)
            println("Peer " + peer.user.username + "'s Previous propagator is " + prevPropagator.user.username)

        peer.forwardMessage(query, prevPropagator)
    }
}