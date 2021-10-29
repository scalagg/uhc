package gg.scala.uhc.configuration

import gg.scala.uhc.add
import gg.scala.uhc.configuration.parser.ConfigEntryParser
import gg.scala.uhc.configuration.preset.PresetConfigMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class UhcConfigMenu : PaginatedMenu()
{
    init {
        placeholdBorders = true
    }

    override fun getButtonsStartOffset(): Int = 1
    override fun size(buttons: Map<Int, Button>): Int = 45

    override fun getGlobalButtons(player: Player): Map<Int, Button>
    {
        return hashMapOf(
            4 to ItemBuilder(Material.MAGMA_CREAM)
                .name("${CC.B_SEC}Config Presets")
                .addToLore(
                    "${CC.GRAY}View multiple config",
                    "${CC.GRAY}presets for you to apply.",
                    "",
                    "${CC.GREEN}[Click to view presets]"
                )
                .toButton { _, _ ->
                    PresetConfigMenu().openMenu(player)
                }
        )
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button>
    {
        return hashMapOf<Int, Button>().also { buttons ->
            UhcGameConfig.entries.forEach {
                buttons[buttons.size] = ConfigEntryButton(it)
            }
        }
    }

    override fun getPrePaginatedTitle(player: Player): String = if (player.hasPermission("uhc.edit-config"))
    {
        "Editing Configuration"
    } else
    {
        "Viewing Configuration"
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
                if (entry.description.isNotEmpty())
                {
                    entry.description.forEach {
                      description.add("${CC.GRAY}$it")
                    }

                    description.add("")
                }

                description.add(
                    "${CC.GRAY}Current Value:",
                    "${CC.WHITE}${entry.currentValue}",
                )

                if (player.hasPermission("uhc.edit-config"))
                {
                    description.add(
                        "",
                        "${CC.YELLOW}Right-Click to edit."
                    )
                }
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
