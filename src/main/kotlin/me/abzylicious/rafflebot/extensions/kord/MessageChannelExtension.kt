package me.abzylicious.rafflebot.extensions.kord

import com.gitlab.kordlib.core.Kord
import com.gitlab.kordlib.core.entity.ReactionEmoji
import com.gitlab.kordlib.core.entity.channel.MessageChannel
import me.abzylicious.rafflebot.extensions.stdlib.isEmoji
import me.abzylicious.rafflebot.extensions.stdlib.isGuildEmote
import me.abzylicious.rafflebot.extensions.stdlib.toGuildEmote
import me.jakejmattson.discordkt.api.Discord
import me.jakejmattson.discordkt.api.annotations.Service
import me.jakejmattson.discordkt.api.extensions.toSnowflake

private lateinit var api: Kord

@Service
class ApiInitializer(discord: Discord) { init { api = discord.api } }

suspend fun MessageChannel.addReaction(messageId: String, reaction: String) {
    if (reaction.isGuildEmote())
        this.getMessage(messageId.toSnowflake()).addReaction(reaction.toGuildEmote()!!)

    if (reaction.isEmoji())
        this.getMessage(messageId.toSnowflake()).addReaction(ReactionEmoji.Unicode(reaction))
}