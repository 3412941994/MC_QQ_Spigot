package com.github.theword.mcqq.handleMessage

import com.github.theword.mcqq.MCQQ
import com.github.theword.mcqq.returnBody.ActionbarReturnBody
import com.github.theword.mcqq.returnBody.MessageReturnBody
import com.github.theword.mcqq.returnBody.SendTitleReturnBody
import com.github.theword.mcqq.utils.ParseJsonToEvent
import net.md_5.bungee.api.ChatMessageType

class HandleApiService : HandleApi {
    private val parseJsonToEvent = ParseJsonToEvent()

    override fun handleBroadcastMessage(messageReturnBody: MessageReturnBody) {
        val textComponent = parseJsonToEvent.parseMessageToTextComponent(messageReturnBody.messageList)
        MCQQ.instance!!.server.spigot().broadcast(textComponent)
    }

    override fun handleSendTitleMessage(sendTitleReturnBody: SendTitleReturnBody) {
        for (player in MCQQ.instance!!.server.onlinePlayers) {
            player.sendTitle(
                    sendTitleReturnBody.sendTitle.title,
                    sendTitleReturnBody.sendTitle.subtitle,
                    sendTitleReturnBody.sendTitle.fadein,
                    sendTitleReturnBody.sendTitle.stay,
                    sendTitleReturnBody.sendTitle.fadeout
            )
        }
    }

    override fun handleActionBarMessage(actionbarReturnBody: ActionbarReturnBody) {
        val actionTextComponent = parseJsonToEvent.parseMessageToTextComponent(actionbarReturnBody.messageList)
        for (player in MCQQ.instance!!.server.onlinePlayers) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionTextComponent)
        }
    }
}
