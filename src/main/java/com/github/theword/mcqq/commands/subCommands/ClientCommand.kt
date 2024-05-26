package com.github.theword.mcqq.commands.subCommands

import com.github.theword.mcqq.commands.SpigotSubCommand
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand
import org.bukkit.command.CommandSender

class ClientCommand : SpigotSubCommand {
    override fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            commandSender.sendMessage("§c请输入子命令")
            return false
        } else if (args[1].equals("reconnect", ignoreCase = true)) {
            return ReconnectCommand().onCommand(commandSender, args)
        }
        return false
    }

    override fun getSubCommands(commandSender: CommandSender, args: Array<String>): ArrayList<String> {
        return object : ArrayList<String>() {
            init {
                add("reconnect")
            }
        }
    }

    override fun getPrefix(): String {
        return ""
    }

    override fun getName(): String {
        return "client"
    }

    override fun getDescription(): String {
        return "Websocket Client 的命令"
    }

    override fun getUsage(): String {
        return "使用：/mcqq client <args>"
    }
}
