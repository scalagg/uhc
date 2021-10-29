package gg.scala.uhc.configuration.preset

import gg.scala.uhc.add
import gg.scala.uhc.configuration.UhcConfigMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.Tasks
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
class PresetConfigMenu : Menu("Preset Configs")
{
    override fun onClose(player: Player, manualClose: Boolean)
    {
        if (manualClose)
        {
            Tasks.delayed(1L)
            {
                UhcConfigMenu().openMenu(player)
            }
        }
    }

    override fun getButtons(player: Player): Map<Int, Button>
    {
        return hashMapOf<Int, Button>().also { buttons ->
            PresetConfigHandler.handle.forEach {
                buttons[10 + buttons.size] = PresetConfigButton(it.value)
            }
        }
    }

    override fun size(buttons: Map<Int, Button>): Int = 27

    private inner class PresetConfigButton(
        private val config: PresetConfig
    ) : Button()
    {
        override fun getName(player: Player): String = CC.B_SEC + config.name()

        override fun getMaterial(player: Player): Material =
            config.xMaterial().parseMaterial() ?: Material.GOLDEN_APPLE

        override fun getDescription(player: Player): List<String>
        {
            return mutableListOf<String>().also {
                val wrapped = TextSplitter.split(config.description(), CC.GRAY, "")
                it.addAll(wrapped)
                it.add("")

                config.details().forEach { detail ->
                    it.add("${CC.GRAY} - $detail")
                }

                it.add(
                    "",
                    "${CC.YELLOW}Click to apply."
                )
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView)
        {
            player.sendMessage("${CC.GREEN}Attempting application of ${CC.YELLOW}${config.name()}${CC.GREEN}...")

            try
            {
                config.apply()
            } catch (ignored: Exception)
            {
                player.sendMessage("${CC.RED}Failed to apply ${CC.YELLOW}${config.name()}${CC.RED}!")
                return
            } finally
            {
                player.sendMessage("${CC.GREEN}You've applied the ${CC.D_AQUA}${config.name()}${CC.GREEN} preset to this UHC game.")
            }
        }
    }
}
