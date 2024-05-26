package com.github.theword.mcqq.commands.subCommands

import com.github.theword.mcqq.commands.SpigotSubCommand
import com.github.theword.mcqq.utils.Tool
import org.bukkit.command.CommandSender

class ReloadCommand : ReloadCommandAbstract(), SpigotSubCommand {
    override fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean {
        Tool.websocketManager.reloadWebsocket(false, commandSender)
        return true
    }

    override fun getSubCommands(commandSender: CommandSender, args: Array<String>): List<String> {
        return ArrayList()
    }

    override fun getPrefix(): String {
        return ""
    }
}
