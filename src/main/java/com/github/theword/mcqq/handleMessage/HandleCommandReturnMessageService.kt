package com.github.theword.mcqq.handleMessage

import org.bukkit.command.CommandSender

class HandleCommandReturnMessageService : HandleCommandReturnMessage {
    override fun handleCommandReturnMessage(`object`: Any, message: String) {
        val commandSender = `object` as CommandSender
        commandSender.sendMessage(message)
    }
}
