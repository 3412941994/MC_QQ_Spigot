package com.github.theword.mcqq.commands.subCommands.client

import com.github.theword.mcqq.commands.SpigotSubCommand
import com.github.theword.mcqq.utils.Tool
import org.bukkit.command.CommandSender

class ReconnectCommand : ReconnectCommandAbstract(), SpigotSubCommand {
    override fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 2) {
            if (args[1].equals("reconnect", ignoreCase = true)) {
                Tool.websocketManager.reconnectWebsocketClients(false, commandSender)
                return true
            }
        } else if (args.size == 3) {
            if (args[2].equals("all", ignoreCase = true)) {
                Tool.websocketManager.reconnectWebsocketClients(true, commandSender)
                return true
            }
        }
        commandSender.sendMessage(usage)
        return false
    }

    override fun getSubCommands(commandSender: CommandSender, args: Array<String>): ArrayList<String> {
        return object : ArrayList<String>() {
            init {
                add("all")
            }
        }
    }

    override fun getPrefix(): String {
        return "client"
    }
}
