package gg.scala.uhc.configuration

import gg.scala.uhc.configuration.parser.ConfigEntryParser
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.CC
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class UhcConfigMenu : Menu()
{
    override fun getButtons(player: Player): Map<Int, Button>
    {
        return hashMapOf<Int, Button>().also { buttons ->
            UhcGameConfig.entries.forEach {
                buttons[buttons.size] = ConfigEntryButton(it)
            }
        }
    }

    private inner class ConfigEntryButton(
        private val entry: UhcConfigEntry
    ) : Button()
    {
        override fun getMaterial(player: Player): Material
        {
            return entry.indicator.parseMaterial() ?: Material.GRASS
        }

        override fun getName(player: Player): String
        {
            return "${CC.B_PRI}${entry.title}"
        }

        override fun getDescription(player: Player): List<String>
        {
            return mutableListOf<String>().also { description ->
                entry.description.forEach {
                    description.add("${CC.GRAY}$it")
                }

                description.add("")
                description.add("${CC.GRAY}Current Value:")
                description.add("${CC.WHITE}${entry.currentValue}")
                description.add("")

                description.add("${CC.YELLOW}Right-Click to edit.")
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView)
        {
            if (!clickType.name.contains("RIGHT"))
            {
                return
            }

            if (!player.hasPermission("uhc.edit-config"))
            {
                player.sendMessage("${CC.RED}You do not have permission to edit the UHC config!")
                return
            }

            player.closeInventory()

            ConfigEntryParser.promptPlayer(
                entry.type, player
            ) {
                entry.currentValue = it

                UhcConfigMenu().openMenu(player)
            }
        }
    }
}
