package com.github.theword.mcqq

import com.github.theword.mcqq.eventModels.spigot.*
import com.github.theword.mcqq.utils.Tool
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

internal class EventProcessor : Listener {
    /**
     * 监听玩家聊天
     */
    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        if (!event.isCancelled && Tool.config.isEnableChatMessage) {
            val spigotAsyncPlayerChatEvent = SpigotAsyncPlayerChatEvent(getSpigotPlayer(event.player), event.message)
            spigotAsyncPlayerChatEvent.setServerName(Tool.config.serverName)
            Tool.sendMessage(spigotAsyncPlayerChatEvent)
        }
    }

    /**
     * 监听玩家死亡事件
     */
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        if (Tool.config.isEnableDeathMessage) {
            val spigotPlayerDeathEvent = SpigotPlayerDeathEvent(getSpigotPlayer(event.entity), event.deathMessage)
            spigotPlayerDeathEvent.setServerName(Tool.config.serverName)
            Tool.sendMessage(spigotPlayerDeathEvent)
        }
    }

    /**
     * 监听玩家加入事件
     */
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (Tool.config.isEnableJoinMessage) {
            val spigotPlayerJoinEvent = SpigotPlayerJoinEvent(getSpigotPlayer(event.player))
            spigotPlayerJoinEvent.setServerName(Tool.config.serverName)
            Tool.sendMessage(spigotPlayerJoinEvent)
        }
    }

    /**
     * 监听玩家离开事件
     */
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        if (Tool.config.isEnableQuitMessage) {
            val spigotPlayerQuitEvent = SpigotPlayerQuitEvent(getSpigotPlayer(event.player))
            spigotPlayerQuitEvent.setServerName(Tool.config.serverName)
            Tool.sendMessage(spigotPlayerQuitEvent)
        }
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (Tool.config.isEnableCommandMessage) {
            var command = event.message
            if (!(command.startsWith("/l ") || command.startsWith("/login ") || command.startsWith("/register ") || command.startsWith("/reg ") || command.startsWith("mcqq "))) {
                command = command.replaceFirst("/".toRegex(), "")
                val spigotPlayerCommandPreprocessEvent = SpigotPlayerCommandPreprocessEvent(getSpigotPlayer(event.player), command)
                spigotPlayerCommandPreprocessEvent.setServerName(Tool.config.serverName)
                Tool.sendMessage(spigotPlayerCommandPreprocessEvent)
            }
        }
    }

    fun getSpigotPlayer(player: Player): SpigotPlayer {
        val spigotPlayer = SpigotPlayer()
        spigotPlayer.uuid = player.uniqueId.toString()
        spigotPlayer.nickname = player.name
        spigotPlayer.displayName = player.displayName
        spigotPlayer.playerListName = player.displayName
        spigotPlayer.address = Objects.requireNonNull(player.address).toString()
        spigotPlayer.healthScale = player.healthScale
        spigotPlayer.exp = player.exp
        spigotPlayer.totalExp = player.totalExperience
        spigotPlayer.level = player.level
        spigotPlayer.locale = player.locale
        spigotPlayer.ping = player.ping
        spigotPlayer.playerTime = player.playerTime
        spigotPlayer.isPlayerTimeRelative = player.isPlayerTimeRelative
        spigotPlayer.playerTimeOffset = player.playerTimeOffset
        spigotPlayer.walkSpeed = player.walkSpeed
        spigotPlayer.flySpeed = player.flySpeed
        spigotPlayer.isAllowFlight = player.allowFlight
        spigotPlayer.isSprinting = player.isSprinting
        spigotPlayer.isSneaking = player.isSneaking
        spigotPlayer.isFlying = player.isFlying
        spigotPlayer.isOp = player.isOp
        return spigotPlayer
    }
}
