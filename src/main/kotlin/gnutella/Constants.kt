package gnutella

import java.net.InetAddress

class Constants {
    companion object {
        const val MAX_HOPS = 3  // Number of maximum hops
        const val TTL = 3000    // Time to live in miliseconds
        const val HOST_CACHE_PORT = 55555
        val HOST_CACHE_ADDRESS: InetAddress = InetAddress.getByName("127.0.0.1")
        const val maxNeighbours = 5
        const val MAX_FRIENDS_TO_MESSAGE = 5 // Send querries to (up to) this many friends
        const val INITIAL_SEARCH_FOLLOWERS_TIME_MILIS = 15000
        const val SEARCH_FOLLOWERS_INTERVAL_MILLIS = 25000
    }
}
