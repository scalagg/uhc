package gg.scala.uhc.configuration.parser

import gg.scala.uhc.configuration.parser.impl.BooleanEntryParser
import gg.scala.uhc.configuration.parser.impl.IntEntryParser
import gg.scala.uhc.configuration.parser.impl.LongEntryParser
import gg.scala.uhc.configuration.parser.impl.StringEntryParser
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.prompt.InputPrompt
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object ConfigEntryParser
{
    val adapters = mutableMapOf<Class<*>, ConfigEntryParserAdapter<*>>()

    fun initialLoad()
    {
        adapters[Boolean::class.java] = BooleanEntryParser()
        adapters[Int::class.java] = IntEntryParser()
        adapters[Long::class.java] = LongEntryParser()
        adapters[String::class.java] = StringEntryParser()
    }

    fun <T> promptPlayer(
        clazz: Class<T>, player: Player,
        lambda: (T) -> Unit
    )
    {
        val adapter = adapters[clazz]

        if (adapter == null)
        {
            player.sendMessage("${CC.RED}Something went wrong, please contact a developer.")
            return
        }

        InputPrompt().withText(
            "${CC.SEC}Please enter a valid ${CC.WHITE}${
                adapter.id()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }${CC.SEC}."
        ).acceptInput { _, input ->
            try
            {
                lambda.invoke(adapter.parse(input) as T)

                player.sendMessage("${CC.GREEN}You've set the value to ${CC.WHITE}$input${CC.GREEN}.")
            } catch (exception: Exception)
            {
                player.sendMessage(
                    "${CC.RED}You've entered an invalid ${adapter.id()}.",
                )

                if (adapter.examples().isNotEmpty())
                {
                    player.sendMessage("${CC.RED}Examples: ${CC.YELLOW}${adapter.examples().joinToString(", ")}.")
                }
            }
        }
    }
}
