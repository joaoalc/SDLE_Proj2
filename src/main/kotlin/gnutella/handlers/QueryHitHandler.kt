package gnutella.handlers

import gnutella.Constants
import social.User
import gnutella.messages.Get
import gnutella.messages.QueryHit
import gnutella.peer.Peer
import java.util.*

class QueryHitHandler(
	private val peer: Peer,
	private val queryHit: QueryHit,
) : MessageHandler(queryHit) {
	override fun run() {
		val query = peer.cache.getCorrespondingQueryOrNull(queryHit)

		if (query != null) {
			val user = User(query.keyword)

			// TODO: Place in is following and the commented if (?)
			peer.addFriendMessage(queryHit, peer)
			// We're following the user
			if (peer.user.isFollowing(user)) {
				//peer.addFriendMessage(queryHit, peer)
				// Select only the wanted posts (by removing the ones we already have)
				val wanted = queryHit.digest - peer.user.storage.digest(user)

				// There are wanted posts
				if (wanted.postIDs.isNotEmpty()) {
					val get = Get(UUID.randomUUID(), peer, wanted)

					peer.sendMessageTo(get, queryHit.source)
				}
			}
			/*if(queryHit.digest.user.username == peer.user.username){
				peer.addFriendMessage(queryHit, peer)
			}*/

			if (peer.sentQueryIDs.contains(queryHit.ID) && Constants.LOGGING) {
                println("Peer ${peer.user.username} | Received a QueryHit response to it's previous query (MsgID = $queryHit.ID)")
				
                return
			}

			if (Constants.LOGGING)
				println("Peer ${peer.user.username} | Received known queryHit")

			peer.sendMessageTo(
				QueryHit(
					query.ID,
					queryHit.source,
					queryHit.digest
				),
				query.propagator
			)
		}
        
		else if (Constants.LOGGING)
			println("Peer ${peer.user.username} | Received unknown $queryHit")
	}
}