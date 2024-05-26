package com.github.theword.mcqq.commands.subCommands

import com.github.theword.mcqq.commands.CommandManager
import com.github.theword.mcqq.commands.SpigotSubCommand
import org.bukkit.command.CommandSender

class HelpCommand : HelpCommandAbstract(), SpigotSubCommand {
    override fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean {
        commandSender.sendMessage("-------------------")
        for (subCommand in CommandManager().spigotSubCommandList) {
            commandSender.sendMessage(subCommand.usage + "---" + subCommand.description)
        }
        commandSender.sendMessage("-------------------")
        return true
    }

    override fun getSubCommands(commandSender: CommandSender, args: Array<String>): List<String> {
        return ArrayList()
    }

    override fun getPrefix(): String {
        return ""
    }
}
