package github.gilbertokpl.total.discord

import github.gilbertokpl.total.TotalEssentialsJava
import github.gilbertokpl.total.config.files.LangConfig
import github.gilbertokpl.total.config.files.MainConfig
import github.gilbertokpl.total.discord.exceptions.*
import github.gilbertokpl.total.discord.listeners.ChatDiscordEvent
import github.gilbertokpl.total.util.ColorUtil
import github.gilbertokpl.total.util.MainUtil
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.exceptions.ContextException
import net.dv8tion.jda.api.exceptions.ErrorResponseException
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import net.dv8tion.jda.internal.utils.JDALogger
import org.bukkit.Bukkit
import java.awt.Color
import javax.security.auth.login.LoginException

internal object Discord {

    var jda: JDA? = null

    fun randomColor(): Color = Color.getHSBColor(
        (Math.random() * 255 + 1).toFloat(),
        (Math.random() * 255 + 1).toFloat(),
        (Math.random() * 255 + 1).toFloat()
    )

    private val hashTextChannel = HashMap<String, TextChannel>()

    /**
     * Send message to discord with chat ID.
     *
     * @throws BotIsNotInitialized if bot is true but token is incorrect.
     * @throws ChatDoesNotExist if chat does not exist.
     */
    fun sendDiscordMessage(message: String, embed: Boolean) {
        sendDiscordMessage(message, MainConfig.discordbotIdDiscordChat, embed)
    }

    /**
     * Send message to discord with chat ID.
     *
     * @throws BotIsNotInitialized if bot is true but token is incorrect.
     * @throws ChatDoesNotExist if chat does not exist.
     */
    fun sendDiscordMessage(message: String, chatID: String, embed: Boolean) {
        if (!MainConfig.discordbotConnectDiscordChat) {
            return
        }

        if (hashTextChannel[chatID] == null) {
            TotalEssentialsJava.basePlugin.getTask().async {
                val newChat = setupDiscordChat(chatID) ?: throw ChatDoesNotExist()

                hashTextChannel[chatID] = newChat

                if (embed) {
                    val msg = EmbedBuilder().setDescription(message).setColor(randomColor())
                    newChat.sendMessageEmbeds(msg.build()).queue()
                    return@async
                }

                newChat.sendMessage(message).queue()

            }
            return
        }

        try {
            if (embed) {
                val msg = EmbedBuilder().setDescription(message).setColor(randomColor())
                hashTextChannel[chatID]!!.sendMessageEmbeds(msg.build()).queue()
                return
            }
            hashTextChannel[chatID]!!.sendMessage(message).queue()
        } catch (e: Throwable) {
            hashTextChannel.remove(chatID)
        }
    }


    fun sendDiscordMessage(userID: Long, message: String): Boolean {

        if (!MainConfig.discordbotConnectDiscordChat) {
            return false
        }

        val jda = getJdaCheck()

        try {
            jda.retrieveUserById(userID).complete()?.openPrivateChannel()?.queue { channel ->
                channel.sendMessage(message).queue()
            } ?: return false
        } catch (e: ContextException) {
            return false
        } catch (e: Exception) {
            return false
        }

        return true
    }

    fun checkIfRoleIdExist(roleId: Long): Boolean {

        if (!MainConfig.discordbotConnectDiscordChat) {
            return false
        }

        val jda = getJdaCheck()

        jda.getRoleById(roleId) ?: return false

        return true
    }


    /**
     * Add role to user.
     *
     * @throws BotIsNotInitialized if bot is true but token is incorrect.
     * @throws UserDoesNotExist if user does not exist.
     * @throws RoleDoesNotExist if role does not exist.
     */
    fun addUserRole(userID: Long, roleId: Long): Boolean {

        if (!MainConfig.discordbotConnectDiscordChat) {
            return false
        }

        val jda = getJdaCheck()

        try {
            val role = jda.getRoleById(roleId) ?: throw RoleDoesNotExist()
            jda.retrieveUserById(userID).queue {
                jda.getGuildById(jda.guilds[0].id)?.addRoleToMember(it, role)?.queue()
            }
        } catch (e: ContextException) {
            return false
        } catch (e: Exception) {
            return false
        } catch (e: ErrorResponseException) {
            return false
        } catch (e: Throwable) {
            return false
        }

        return true
    }

    /**
     * remove user role.
     *
     * @throws BotIsNotInitialized if bot is true but token is incorrect.
     * @throws UserDoesNotExist if user does not exist.
     * @throws RoleDoesNotExist if role does not exist.
     * @throws UserDoesNotHaveThisRole if user does not have this role.
     */
    fun removeUserRole(userID: Long, roleId: Long): Boolean {

        if (!MainConfig.discordbotConnectDiscordChat) {
            return false
        }

        val jda = getJdaCheck()

        try {
            val role = jda.getRoleById(roleId) ?: return false
            jda.retrieveUserById(userID).queue {
                jda.getGuildById(jda.guilds[0].id)?.removeRoleFromMember(it, role)?.queue()
            }
        } catch (e: ContextException) {
            return false
        } catch (e: Exception) {
            return false
        }

        return true
    }

    /**
     * Reload all chat ids.
     */
    fun reloadDiscordChat() {
        hashTextChannel.clear()
    }

    /**
     * Get chat id in use.
     *
     * @return chat in use, null if bot not in use.
     */
    fun getChatIdInUse(): String? {
        return hashTextChannel[MainConfig.discordbotIdDiscordChat]?.id
    }

    private fun getJdaCheck(): JDA {

        val jda = jda

        if (jda == null && MainConfig.discordbotConnectDiscordChat) {
            MainUtil.consoleMessage(
                ColorUtil.YELLOW.color + LangConfig.discordchatNoToken + ColorUtil.RESET.color
            )
            throw BotIsNotInitialized()
        }

        if (jda == null) {
            Bukkit.getServer().shutdown()
            throw BotIsNotInitialized()
        }

        return jda
    }

    fun startBot() {
        jda = try {
            JDABuilder.createLight(MainConfig.discordbotToken)
                .setAutoReconnect(true)
                .setLargeThreshold(50)
                .disableCache(CacheFlag.ACTIVITY)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(ChatDiscordEvent())
                .build()
        } catch (e: LoginException) {
            null
        } catch (e: Throwable) {
            null
        }
    }

    private fun setupDiscordChat(chatID: String): TextChannel? {

        val jda = getJdaCheck()
        val newChat =
            jda.getTextChannelById(chatID) ?: run {
                MainUtil.consoleMessage(
                    ColorUtil.YELLOW.color + LangConfig.discordchatNoChatId + ColorUtil.RESET.color
                )
                return null
            }
        return newChat
    }
}