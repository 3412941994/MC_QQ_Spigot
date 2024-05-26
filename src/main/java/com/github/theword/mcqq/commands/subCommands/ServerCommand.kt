package com.github.theword.mcqq.commands.subCommands

import com.github.theword.mcqq.commands.SpigotSubCommand
import org.bukkit.command.CommandSender

class ServerCommand : SpigotSubCommand {
    override fun onCommand(commandSender: CommandSender, args: Array<String>): Boolean {
        commandSender.sendMessage("Server commands are not implemented yet.")
        return false
    }

    override fun getSubCommands(commandSender: CommandSender, args: Array<String>): ArrayList<String> {
        return object : ArrayList<String>() {
        }
    }

    override fun getPrefix(): String {
        return ""
    }

    override fun getName(): String {
        return "server"
    }

    override fun getDescription(): String {
        return "Websocket Client 的命令"
    }

    override fun getUsage(): String {
        return "使用：/mcqq client <args>"
    }
}
