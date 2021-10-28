package gg.scala.uhc.configuration.parser

/**
 * Parses a player's input and
 * returns an instance of type [T].
 *
 * @author GrowlyX
 * @since 10/28/2021
 */
interface ConfigEntryParserAdapter<T>
{
    fun id(): String
    fun parse(input: String): T

    fun examples(): Array<String>
}
