package gnutella

import java.net.InetAddress

class Constants {
    companion object {
        const val TTL = 5    // Maximum hops of message
        const val HOST_CACHE_PORT = 55555
        val HOST_CACHE_ADDRESS: InetAddress = InetAddress.getByName("127.0.0.1")
        const val MIN_NEIGHBOURS = 2
        const val MAX_NEIGHBOURS = 5
        const val MAX_FRIENDS_TO_MESSAGE = 5 // Send querries to (up to) this many friends
        const val INITIAL_SEARCH_FOLLOWERS_TIME_MILIS = 15000
        const val SEARCH_FOLLOWERS_INTERVAL_MILLIS = 1000
        const val MIN_PING_INTERVAL = 3 // In seconds
        const val MAX_PING_INTERVAL = 4 // In seconds
        const val LOGGING = true
    }
}
