package gg.scala.uhc.scenario

import gg.scala.uhc.add
import gg.scala.uhc.configuration.UhcConfigMenu
import gg.scala.uhc.configuration.UhcGameConfig
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.CC
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
class ScenarioMenu : PaginatedMenu()
{
    init {
        placeholdBorders = true
        updateAfterClick = true
    }

    override fun size(buttons: Map<Int, Button>): Int = 54

    override fun getAllPagesButtonSlots(): List<Int> = UhcConfigMenu.BUTTON_SLOTS
    override fun getAllPagesButtons(player: Player): Map<Int, Button>
    {
        return hashMapOf<Int, Button>().also { buttons ->
            ScenarioHandler.scenarios.forEach {
                buttons[buttons.size] = ScenarioButton(it.value)
            }
        }
    }

    override fun getPrePaginatedTitle(player: Player): String = "Scenarios"

    private inner class ScenarioButton(
        private val scenario: Scenario
    ) : Button()
    {
        override fun getMaterial(player: Player): Material =
            scenario.material().parseMaterial() ?: Material.CHEST

        override fun getName(player: Player): String =
            CC.AQUA + scenario.name

        override fun getDescription(player: Player): List<String>
        {
            return mutableListOf<String>().also {
                scenario.description.forEach { detail ->
                    it.add(CC.GRAY + detail)
                }

                it.add(
                    "",
                    "${CC.GRAY}This scenario is ${
                        if (ScenarioHandler.selected.contains(scenario.name))
                        {
                            "${CC.GREEN}enabled"
                        } else
                        {
                            "${CC.RED}disabled"
                        }
                    }${CC.GRAY}."
                )

                if (player.hasPermission("uhc.edit-scenarios"))
                {
                    it.add(
                        "", "${CC.YELLOW}Click to toggle."
                    )
                }
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView)
        {
            if (!player.hasPermission("uhc.edit-scenarios"))
            {
                return
            }

            val status = ScenarioHandler.selected.contains(scenario.name)

            if (status)
            {
                ScenarioHandler.selected.remove(scenario.name)
            } else
            {
                ScenarioHandler.selected.add(scenario.name)
            }
        }
    }
}
