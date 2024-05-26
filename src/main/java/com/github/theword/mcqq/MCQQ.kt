package com.github.theword.mcqq

import com.github.theword.mcqq.commands.CommandExecutor
import com.github.theword.mcqq.constant.BaseConstant
import com.github.theword.mcqq.constant.WebsocketConstantMessage
import com.github.theword.mcqq.handleMessage.HandleApiService
import com.github.theword.mcqq.handleMessage.HandleCommandReturnMessageService
import com.github.theword.mcqq.utils.Tool
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class MCQQ : JavaPlugin() {
    override fun onLoad() {
        Tool.initTool(false, HandleApiService(), HandleCommandReturnMessageService())
    }

    override fun onEnable() {
        instance = this
        Tool.websocketManager.startWebsocket(false)
        Bukkit.getPluginManager().registerEvents(EventProcessor(), this)
        getCommand(BaseConstant.COMMAND_HEADER)?.setExecutor(CommandExecutor())
    }

    override fun onDisable() {
        Tool.websocketManager.stopWebsocket(1000, WebsocketConstantMessage.Client.CLOSING, null)
    }

    companion object {
        @JvmField
        var instance: JavaPlugin? = null
    }
}
