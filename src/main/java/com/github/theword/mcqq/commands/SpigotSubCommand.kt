package com.github.theword.mcqq.commands

import org.bukkit.command.CommandSender

interface SpigotSubCommand : SubCommand {
    fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean

    fun getSubCommands(commandSender: CommandSender, args: Array<String>): List<String>

    fun getPrefix(): String
}
