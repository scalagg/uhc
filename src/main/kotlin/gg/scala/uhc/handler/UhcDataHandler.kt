package gg.scala.uhc.handler

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.lemon.Lemon
import gg.scala.uhc.player.UhcPlayer

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object UhcDataHandler
{
    lateinit var playerLayer: MongoStorageLayer<UhcPlayer>

    fun initialLoad()
    {
        val mongoConnection = UriMongoConnection(
            Lemon.instance.mongoConfig.uri
        )

        playerLayer = MongoStorageLayer(
            mongoConnection, "SolexGames",
            "uhc_players", UhcPlayer::class.java
        )
    }
}
