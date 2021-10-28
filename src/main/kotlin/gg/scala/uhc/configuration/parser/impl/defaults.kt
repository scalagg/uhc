package gg.scala.uhc.configuration.parser.impl

import gg.scala.uhc.configuration.parser.ConfigEntryParserAdapter

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class BooleanEntryParser : ConfigEntryParserAdapter<Boolean>
{
    override fun id(): String = "boolean"
    override fun parse(input: String): Boolean = input.toBooleanStrict()

    override fun examples(): Array<String> = arrayOf("true", "false")
}

class IntEntryParser : ConfigEntryParserAdapter<Int>
{
    override fun id(): String = "integer"
    override fun parse(input: String): Int = input.toInt()

    override fun examples(): Array<String> = arrayOf("1", "2")
}

class LongEntryParser : ConfigEntryParserAdapter<Long>
{
    override fun id(): String = "long"
    override fun parse(input: String): Long = input.toLong()

    override fun examples(): Array<String> = arrayOf("1235678")
}

class StringEntryParser : ConfigEntryParserAdapter<String>
{
    override fun id(): String = "string"
    override fun parse(input: String): String = input

    override fun examples(): Array<String> = arrayOf()
}
