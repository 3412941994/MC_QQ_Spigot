package com.github.theword.mcqq.utils

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.*
import net.md_5.bungee.api.chat.hover.content.Entity
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text

class ParseJsonToEvent {
    private fun parsePerMessageToTextComponent(myBaseComponent: MyBaseComponent): TextComponent {
        val msgComponent = TextComponent()

        // 配置 BaseComponent 基本属性
        msgComponent.text = myBaseComponent.text
        msgComponent.color = getColor(myBaseComponent.color)
        msgComponent.isBold = myBaseComponent.isBold
        msgComponent.isItalic = myBaseComponent.isItalic
        msgComponent.isUnderlined = myBaseComponent.isUnderlined
        msgComponent.isStrikethrough = myBaseComponent.isStrikethrough
        msgComponent.isObfuscated = myBaseComponent.isObfuscated

        // 配置 TextComponent 额外属性
        if (myBaseComponent is MyTextComponent) {
            val myTextComponent = myBaseComponent
            if (myTextComponent.clickEvent != null) {
                val clickEvent = getClickEvent(myTextComponent)
                msgComponent.clickEvent = clickEvent
            }

            if (myTextComponent.hoverEvent != null) {
                var hoverEvent: HoverEvent? = null
                when (myTextComponent.hoverEvent.action) {
                    "show_text" -> {
                        val textComponent = parseMessageToTextComponent(myTextComponent.hoverEvent.baseComponentList)
                        val baseComponent = arrayOf<BaseComponent>(textComponent)
                        hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(baseComponent))
                    }

                    "show_item" -> {
                        val myHoverItem = myTextComponent.hoverEvent.item
                        val itemTag = ItemTag.ofNbt(myHoverItem.tag)
                        val item = Item(myHoverItem.id.toString(), myHoverItem.count, itemTag)
                        hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, item)
                    }

                    "show_entity" -> {
                        val myHoverEntity = myTextComponent.hoverEvent.entity
                        val nameComponent = parseMessageToTextComponent(myHoverEntity.name)
                        val entity = Entity(myHoverEntity.type, myHoverEntity.id, nameComponent)
                        hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ENTITY, entity)
                    }

                    else -> {}
                }
                msgComponent.hoverEvent = hoverEvent
            }
        }
        return msgComponent
    }

    /**
     * 将 MyBaseComponent 转换为 TextComponent
     *
     * @param myBaseComponentList 消息列表
     * @return TextComponent
     */
    fun parseMessageToTextComponent(myBaseComponentList: List<MyBaseComponent>): TextComponent {
        val component = TextComponent()
        val msgLogText = StringBuilder()

        for (myBaseComponent in myBaseComponentList) {
            val msgComponent = parsePerMessageToTextComponent(myBaseComponent)
            component.addExtra(msgComponent)

            if (myBaseComponent.text != "[MC_QQ] ") {
                msgLogText.append(myBaseComponent.text)
            }
        }
        Tool.logger.info(msgLogText.toString())
        return component
    }

    /**
     * @param color 颜色
     * @return ChatColor 对象
     */
    private fun getColor(color: String?): ChatColor {
        return when (color) {
            "black" -> ChatColor.BLACK
            "dark_blue" -> ChatColor.DARK_BLUE
            "dark_green" -> ChatColor.DARK_GREEN
            "dark_aqua" -> ChatColor.DARK_AQUA
            "dark_red" -> ChatColor.DARK_RED
            "dark_purple" -> ChatColor.DARK_PURPLE
            "gold" -> ChatColor.GOLD
            "gray" -> ChatColor.GRAY
            "dark_gray" -> ChatColor.DARK_GRAY
            "blue" -> ChatColor.BLUE
            "green" -> ChatColor.GREEN
            "aqua" -> ChatColor.AQUA
            "red" -> ChatColor.RED
            "light_purple" -> ChatColor.LIGHT_PURPLE
            "yellow" -> ChatColor.YELLOW
            "white" -> ChatColor.WHITE
            else -> ChatColor.WHITE
        }
    }

    companion object {
        private fun getClickEvent(myTextComponent: MyTextComponent): ClickEvent {
            var tempAction: ClickEvent.Action? = null
            when (myTextComponent.clickEvent.action) {
                "open_url" -> tempAction = ClickEvent.Action.OPEN_URL
                "open_file" -> tempAction = ClickEvent.Action.OPEN_FILE
                "run_command" -> tempAction = ClickEvent.Action.RUN_COMMAND
                "suggest_command" -> tempAction = ClickEvent.Action.SUGGEST_COMMAND
                "change_page" -> tempAction = ClickEvent.Action.CHANGE_PAGE
                "copy_to_clipboard" -> tempAction = ClickEvent.Action.COPY_TO_CLIPBOARD
                else -> {}
            }
            return ClickEvent(tempAction, myTextComponent.clickEvent.value)
        }
    }
}
