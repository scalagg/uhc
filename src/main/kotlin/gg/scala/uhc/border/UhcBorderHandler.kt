package gg.scala.uhc.border

import gg.scala.uhc.UhcSpigotPlugin
import io.papermc.lib.PaperLib
import org.bukkit.*
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object UhcBorderHandler
{
    var current = 500
    var seconds = 120

    private val blockedWallBlocks = listOf(
        Material.LOG, Material.LOG_2, Material.LEAVES, Material.LEAVES_2,
        Material.AIR, Material.WATER, Material.STATIONARY_WATER, Material.LAVA, Material.STATIONARY_LAVA,
        Material.HUGE_MUSHROOM_1, Material.HUGE_MUSHROOM_2, Material.DOUBLE_PLANT, Material.LONG_GRASS,
        Material.VINE, Material.YELLOW_FLOWER, Material.RED_ROSE, Material.CACTUS, Material.DEAD_BUSH,
        Material.SUGAR_CANE_BLOCK, Material.ICE, Material.SNOW
    )

    fun setBorder(border: Int)
    {
        val world = Bukkit.getWorld("uhc")
        current = border

        addBedrockBorder(world.name, border, 6)

        world.worldBorder.setCenter(0.0, 0.0)
        world.worldBorder.size = (border * 2).toDouble()

        if (border == 10)
        {
            seconds = -1
        }

        handlePlayers(world, border)
    }

    private fun handlePlayers(world: World, border: Int)
    {
        for (player in Bukkit.getOnlinePlayers())
        {
            if (player.world.name.equals("uhc", ignoreCase = true))
            {
                if (player.location.blockX > border)
                {
                    handleEffects(player)

                    PaperLib.teleportAsync(
                        player, Location(
                            world,
                            (border - 2).toDouble(), player.location.blockY.toDouble(),
                            player.location.blockZ.toDouble()
                        )
                    )

                    if (player.location.blockY < world.getHighestBlockYAt(
                            player.location.blockX,
                            player.location.blockZ
                        )
                    )

                        PaperLib.teleportAsync(
                            player, Location(
                                world,
                                player.location.blockX.toDouble(),
                                (world.getHighestBlockYAt(
                                    player.location.blockX,
                                    player.location.blockZ
                                ) + 2).toDouble(),
                                player.location.blockZ.toDouble()
                            )
                        )
                }

                if (player.location.blockZ > border)
                {
                    handleEffects(player)

                    PaperLib.teleportAsync(
                        player, Location(
                            world,
                            player.location.blockX.toDouble(),
                            player.location.blockY.toDouble(),
                            (border - 2).toDouble()
                        )
                    )

                    if (player.location.blockY < world.getHighestBlockYAt(
                            player.location.blockX,
                            player.location.blockZ
                        )
                    )
                    {
                        PaperLib.teleportAsync(
                            player, Location(
                                world,
                                player.location.blockX.toDouble(),
                                (world.getHighestBlockYAt(
                                    player.location.blockX,
                                    player.location.blockZ
                                ) + 2).toDouble(),
                                player.location.blockZ.toDouble()
                            )
                        )
                    }
                }

                if (player.location.blockX < -border)
                {
                    handleEffects(player)

                    PaperLib.teleportAsync(
                        player, Location(
                            world,
                            (-border + 2).toDouble(), player.location.blockY.toDouble(),
                            player.location.blockZ.toDouble()
                        )
                    )

                    if (player.location.blockY < world.getHighestBlockYAt(
                            player.location.blockX,
                            player.location.blockZ
                        )
                    )
                    {
                        PaperLib.teleportAsync(
                            player, Location(
                                world,
                                player.location.blockX.toDouble(),
                                (world.getHighestBlockYAt(
                                    player.location.blockX,
                                    player.location.blockZ
                                ) + 2).toDouble(),
                                player.location.blockZ.toDouble()
                            )
                        )
                    }
                }

                if (player.location.blockZ < -border)
                {
                    handleEffects(player)

                    PaperLib.teleportAsync(
                        player, Location(
                            world,
                            player.location.blockX.toDouble(),
                            player.location.blockY.toDouble(),
                            (-border + 2).toDouble()
                        )
                    )

                    if (player.location.blockY < world.getHighestBlockYAt(
                            player.location.blockX,
                            player.location.blockZ
                        )
                    )
                    {
                        PaperLib.teleportAsync(
                            player, Location(
                                world,
                                player.location.blockX.toDouble(),
                                (world.getHighestBlockYAt(
                                    player.location.blockX,
                                    player.location.blockZ
                                ) + 2).toDouble(),
                                player.location.blockZ.toDouble()
                            )
                        )
                    }
                }
            }
        }
    }

    private fun handleEffects(player: Player)
    {
        player.playSound(player.location, Sound.ORB_PICKUP, 1.0f, 2.0f)

        player.sendMessage(ChatColor.RED.toString() + "You've been teleported to a valid location within the world border.")
    }

    private fun addBedrockBorder(world: String, radius: Int, blocksHigh: Int)
    {
        for (i in 0 until blocksHigh)
        {
            Bukkit.getScheduler()
                .runTaskLater(UhcSpigotPlugin.instance, { this.addBedrockBorder(world, radius) }, i.toLong())
        }
    }

    private fun figureOutBlockToMakeBedrock(world: String, x: Int, z: Int)
    {
        val block = Bukkit.getWorld(world).getHighestBlockAt(x, z)
        var below = block.getRelative(BlockFace.DOWN)
        while (blockedWallBlocks.contains(below.type) && below.y > 1)
        {
            below = below.getRelative(BlockFace.DOWN)
        }
        below.getRelative(BlockFace.UP).type = Material.BEDROCK
    }

    private fun addBedrockBorder(world: String, radius: Int)
    {
        object : BukkitRunnable()
        {
            private var counter = -radius - 1

            private var phase1 = false
            private var phase2 = false
            private var phase3 = false

            override fun run()
            {
                if (!phase1)
                {
                    val maxCounter = counter + 500
                    val x = -radius - 1
                    var z = counter

                    while (z <= radius && counter <= maxCounter)
                    {
                        figureOutBlockToMakeBedrock(world, x, z)
                        z++
                        counter++
                    }

                    if (counter >= radius)
                    {
                        counter = -radius - 1
                        phase1 = true
                    }
                    return
                }

                if (!phase2)
                {
                    val maxCounter = counter + 500
                    var z = counter

                    while (z <= radius && counter <= maxCounter)
                    {
                        figureOutBlockToMakeBedrock(world, radius, z)
                        z++
                        counter++
                    }
                    if (counter >= radius)
                    {
                        counter = -radius - 1
                        phase2 = true
                    }
                    return
                }
                if (!phase3)
                {
                    val maxCounter = counter + 500
                    val z = -radius - 1
                    var x = counter
                    while (x <= radius && counter <= maxCounter)
                    {
                        if (x == radius || x == -radius - 1)
                        {
                            x++
                            counter++
                            continue
                        }
                        figureOutBlockToMakeBedrock(world, x, z)
                        x++
                        counter++
                    }
                    if (counter >= radius)
                    {
                        counter = -radius - 1
                        phase3 = true
                    }
                    return
                }
                val maxCounter = counter + 500
                var x = counter
                while (x <= radius && counter <= maxCounter)
                {
                    if (x == radius || x == -radius - 1)
                    {
                        x++
                        counter++
                        continue
                    }
                    figureOutBlockToMakeBedrock(world, x, radius)
                    x++
                    counter++
                }
                if (counter >= radius)
                {
                    cancel()
                }
            }
        }.runTaskTimer(UhcSpigotPlugin.instance, 0L, 5L)
    }
}
