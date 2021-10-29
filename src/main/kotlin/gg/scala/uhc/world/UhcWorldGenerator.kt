package gg.scala.uhc.world

import gg.scala.uhc.UhcSpigotPlugin
import io.papermc.lib.PaperLib
import me.lucko.helper.scheduler.threadlock.ServerThreadLock
import net.evilblock.cubed.util.bukkit.Tasks
import org.apache.commons.io.FileUtils
import org.bukkit.*
import org.bukkit.entity.Player
import java.io.File

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object UhcWorldGenerator
{
    var generated = false

    lateinit var world: World
    lateinit var spectator: Location

    fun generate(lambda: (Boolean) -> Unit)
    {
        val worldCreator = WorldCreator("uhc")
        worldCreator.generateStructures(false)

        try
        {
            world = Bukkit.createWorld(worldCreator)
        } catch (ignored: Exception)
        {
            FileUtils.deleteDirectory(
                File("uhc")
            )

            lambda.invoke(false)
            return
        }

        val lock = File("uhc", "gen.lock")
        lock.createNewFile()

        world.setGameRuleValue("doMobSpawning", "false")
        world.setGameRuleValue("doDaylightCycle", "false")
        world.setGameRuleValue("naturalRegeneration", "false")
        world.setGameRuleValue("doFireTick", "false")
        world.setGameRuleValue("difficulty", "0")
        world.time = 0

        for (x in -510..509)
        {
            for (z in -510..509)
            {
                val location = Location(
                    world, x.toDouble(), 60.0, z.toDouble()
                )

                if (!location.chunk.isLoaded)
                {
                    PaperLib.getChunkAtAsync(location).whenComplete { chunk, throwable ->
                        if (throwable != null)
                        {
                            UhcSpigotPlugin.instance.logger
                                .severe("Failed to load chunk async, fallback to sync loading. (" + throwable.message + ")")

                            try
                            {
                                Tasks.sync {
                                    location.world.loadChunk(chunk)
                                }
                            } catch (exception: Exception)
                            {
                                UhcSpigotPlugin.instance.logger
                                    .severe("Failed to load chunk sync. (" + exception.message + ")")
                            }
                        }
                    }
                }
            }
        }

        lambda.invoke(true)

        spectator = Location(
            world, 0.5, (world.getHighestBlockYAt(0, 0) + 15).toDouble(), 0.5
        )

        UhcBorderHandler.setBorder(100)
    }
}
