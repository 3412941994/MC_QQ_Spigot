package com.github.theword.mcqq.commands

import com.github.theword.mcqq.commands.subCommands.HelpCommand
import com.github.theword.mcqq.constant.BaseConstant
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class CommandExecutor : TabExecutor {
    private val commandManager = CommandManager()

    override fun onCommand(commandSender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            return HelpCommand().onCommand(commandSender, args)
        } else {
            commandManager.spigotSubCommandList.firstOrNull {
                it.name.equals(args[0], ignoreCase = true)
            }?.let {
                return it.onCommand(commandSender, args)
            }
        }
        return false
    }

    override fun onTabComplete(commandSender: CommandSender, command: Command, s: String, args: Array<String>): List<String> {
        if (command.name.equals(BaseConstant.COMMAND_HEADER, ignoreCase = true)) {
            return when (args.size) {
                1 -> {
                    commandManager.spigotSubCommandList
                            .filter { it.getPrefix().isEmpty() }
                            .map { it.name }
                }

                2, 3 -> {
                    val index = args.size - 1
                    commandManager.spigotSubCommandList
                            .firstOrNull { it.name.equals(args[index], ignoreCase = true) }
                            ?.getSubCommands(commandSender, args)
                            ?: emptyList()
                }

                else -> emptyList()
            }
        }
        return emptyList()
    }
}
