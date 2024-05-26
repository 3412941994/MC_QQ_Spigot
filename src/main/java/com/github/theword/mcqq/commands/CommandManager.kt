package com.github.theword.mcqq.commands

import com.github.theword.mcqq.commands.subCommands.ClientCommand
import com.github.theword.mcqq.commands.subCommands.HelpCommand
import com.github.theword.mcqq.commands.subCommands.ReloadCommand
import com.github.theword.mcqq.commands.subCommands.ServerCommand
import com.github.theword.mcqq.commands.subCommands.client.ReconnectCommand
import lombok.Getter
import lombok.Setter

@Setter
@Getter
class CommandManager {
    var spigotSubCommandList: MutableList<SpigotSubCommand> = ArrayList()


    init {
        spigotSubCommandList.add(HelpCommand())
        spigotSubCommandList.add(ReloadCommand())
        spigotSubCommandList.add(ClientCommand())
        spigotSubCommandList.add(ServerCommand())

        spigotSubCommandList.add(ReconnectCommand())
    }
}
