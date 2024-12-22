package github.gilbertokpl.total.util

import github.gilbertokpl.total.cache.local.PlayerData
import github.gilbertokpl.total.config.files.LangConfig
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.OfflinePlayer

class EconomyHolder : Economy {
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getName(): String {
        return "TotalEconomy"
    }

    override fun hasBankSupport(): Boolean {
        return false
    }

    override fun fractionalDigits(): Int {
        return 0
    }

    override fun format(amount: Double): String {
        return MoneyUtil.coinReplacer(amount)
    }

    override fun currencyNamePlural(): String {
        return LangConfig.moneySymbol
    }

    override fun currencyNameSingular(): String {
        return LangConfig.moneySymbol
    }

    override fun hasAccount(playerName: String): Boolean {
        return PlayerData.checkIfPlayerExists(playerName)
    }

    override fun hasAccount(player: OfflinePlayer): Boolean {
        return PlayerData.checkIfPlayerExists(player.name ?: "")
    }

    override fun hasAccount(playerName: String, worldName: String): Boolean {
        return hasAccount(playerName)
    }

    override fun hasAccount(player: OfflinePlayer, worldName: String): Boolean {
        return hasAccount(player)
    }

    override fun getBalance(playerName: String): Double {
        return PlayerData.moneyCache[playerName] ?: 0.0
    }

    override fun getBalance(player: OfflinePlayer): Double {
        return getBalance(player.name ?: "")
    }

    override fun getBalance(playerName: String, world: String?): Double {
        return getBalance(playerName)
    }

    override fun getBalance(player: OfflinePlayer, world: String?): Double {
        return getBalance(player)
    }

    override fun has(playerName: String, amount: Double): Boolean {
        return amount <= (PlayerData.moneyCache[playerName] ?: return false)
    }

    override fun has(player: OfflinePlayer, amount: Double): Boolean {
        return has(player.name ?: "", amount)
    }

    override fun has(playerName: String, worldName: String?, amount: Double): Boolean {
        return has(playerName, amount)
    }

    override fun has(player: OfflinePlayer, worldName: String?, amount: Double): Boolean {
        return has(player, amount)
    }

    override fun withdrawPlayer(playerName: String, worldName: String?, amount: Double): EconomyResponse {
        return withdrawPlayer(playerName, amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer, worldName: String?, amount: Double): EconomyResponse {
        return withdrawPlayer(player, amount)
    }

    override fun withdrawPlayer(playerName: String, amount: Double): EconomyResponse {
        return MoneyUtil.withdrawPlayer(playerName, amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        return MoneyUtil.withdrawPlayer(player.name ?: "", amount)
    }

    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        return if (playerName != null) {
            return MoneyUtil.depositPlayer(playerName, amount)
        } else {
            EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, null)
        }
    }

    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        val playerName = player?.name
        return if (playerName != null) {
            return MoneyUtil.depositPlayer(playerName, amount)
        } else {
            EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, null)
        }
    }

    override fun depositPlayer(playerName: String, amount: Double): EconomyResponse {
        return MoneyUtil.depositPlayer(playerName, amount)
    }

    override fun depositPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        return MoneyUtil.depositPlayer(player.name ?: "", amount)
    }

    override fun createBank(name: String?, player: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun deleteBank(name: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun bankBalance(name: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun getBanks(): MutableList<String> {
        return mutableListOf()
    }

    override fun createPlayerAccount(playerName: String?): Boolean {
        if (playerName != null) {
            PlayerData.createNewPlayerData(playerName)
            return true
        }
        return false
    }

    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        val playerName = player?.name
        if (playerName != null) {
            PlayerData.createNewPlayerData(playerName)
            return true
        }
        return false
    }

    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        if (playerName != null) {
            PlayerData.createNewPlayerData(playerName)
            return true
        }
        return false
    }

    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        val playerName = player?.name
        if (playerName != null) {
            PlayerData.createNewPlayerData(playerName)
            return true
        }
        return false
    }
}