package github.gilbertokpl.total.config.files;


import github.gilbertokpl.core.external.config.annotations.Value;
import github.gilbertokpl.core.external.config.annotations.Values;
import github.gilbertokpl.core.external.config.def.DefaultLang;
import github.gilbertokpl.core.external.config.types.LangTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LangConfig implements DefaultLang {

    @Values(
            {
                    @Value(value = "&a[Server]&r ", lang = LangTypes.PT_BR),
                    @Value(value = "&a[Server]&r ", lang = LangTypes.EN_US)
            }
    )
    public static String generalServerPrefix;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse comando é somente para player!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis command is for player only!", lang = LangTypes.EN_US)
            }
    )
    public static String generalOnlyPlayerCommand;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse comando é somente para console!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis command is for console only!", lang = LangTypes.EN_US)
            }
    )
    public static String generalOnlyConsoleCommand;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem permissão para executar esse comando.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou do not have permissions to run this command.", lang = LangTypes.EN_US)
            }
    )
    public static String generalNotPerm;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem permissão para fazer isso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou don't have permissions.", lang = LangTypes.EN_US)
            }
    )
    public static String generalNotPermAction;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse player não está online.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis player is not online.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPlayerNotOnline;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse player não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis player does not exist.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPlayerNotExist;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse player já existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis player exist.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPlayerExist;
    @Values(
            {
                    @Value(value = "&9[EssentialsK Ajuda]", lang = LangTypes.PT_BR),
                    @Value(value = "&9[EssentialsK Help]", lang = LangTypes.EN_US)
            }
    )
    public static String generalCommandsUsage;
    @Values(
            {
                    @Value(value = "'&9> &e%command%'", lang = LangTypes.PT_BR),
                    @Value(value = "'&9> &e%command%'", lang = LangTypes.EN_US)
            }
    )
    public static String generalCommandsUsageList;
    @Values(
            {
                    @Value(value = "%prefix%&cCaracteres proibidos", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cForbidden simbols", lang = LangTypes.EN_US)
            }
    )
    public static String generalSpecialCaracteresDisabled;
    @Values(
            {
                    @Value(value = "%prefix%&cFalta %time% para executar novamente esse comando.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThere are %time% left to run this command again.", lang = LangTypes.EN_US)
            }
    )
    public static String generalCooldownMoreTime;
    @Values(
            {
                    @Value(value = "%prefix%&cAdicione o Vault para o funcionamento do plugin!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cAdd Vault for plugin working!", lang = LangTypes.EN_US)
            }
    )
    public static String generalVaultNotExist;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê já está esperando um teleporte.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou are already expecting a teleport.", lang = LangTypes.EN_US)
            }
    )
    public static String generalInTeleport;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê será teleportado para -> %local% em %time% segundos!.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String generalTimeToTeleport;
    @Values(
            {
                    @Value(value = "%prefix%&cMundo %world% não existe, spawn desativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cWorld %world% no existing, spawn disabled.", lang = LangTypes.EN_US)
            }
    )
    public static String generalWorldNotExistSpawn;
    @Values(
            {
                    @Value(value = "%prefix%&cMundo %world% não existe, warp %warp% desativada.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cWorld %world% no existing, warp %warp% disabled.", lang = LangTypes.EN_US)
            }
    )
    public static String generalWorldNotExistWarp;
    @Values(
            {
                    @Value(value = "%prefix%&eConfig reninciada com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eReloaded config successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String generalConfigReload;
    @Values(
            {
                    @Value(value = "&aIP: &f%ip%|&aOS: &f%os%, &aversão &f%os_version%|&aNome CPU: &f%cpu_name%|&aNúcleos: &f%cores% &anúcleos|&aNúcleos Liberados: &f%cores_server% &anúcleos|&aUso do CPU : &f%cpu_usage% %|&aClock CPU: &f%cpu_clock_min% / %cpu_clock_max% mhz|&aMemória: &f%used_mem% / %max_mem% gb|&aMemória Liberada: &f%used_server_mem% / %max_server_mem% gb|&aHD Usado: &f%used_hd%/%max_hd% gb|&aGPU: &f%gpu%", lang = LangTypes.PT_BR),
                    @Value(value = "&aIP: &f%ip%|&aOS: &f%os%, &aversion &f%os_version%|&aCPU Name: &f%cpu_name%|&aCores: &f%cores% &anúcleos|&aReleased Cores: &f%cores_server% &anúcleos|&aCPU Usage : &f%cpu_usage% %|&aClock CPU: &f%cpu_clock_min% / %cpu_clock_max% mhz|&aMemory: &f%used_mem% / %max_mem% gb|&aFree memory: &f%used_server_mem% / %max_server_mem% gb|&aHD: &f%used_hd%/%max_hd% gb|&aGPU: &f%gpu%", lang = LangTypes.EN_US)
            }
    )
    public static List<String> generalHostConfig;
    @Values(
            {
                    @Value(value = "%prefix%&ePegando informações da host... Aguarde!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&ecollecting information from host... Please wait!", lang = LangTypes.EN_US)
            }
    )
    public static String generalHostWait;
    @Values(
            {
                    @Value(value = "%prefix%&cPlugin não encontrado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cPlugin not found.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPluginNotFound;
    @Values(
            {
                    @Value(value = "%prefix%&ePlugin descarregado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&ePlugin unloaded successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPluginUnload;
    @Values(
            {
                    @Value(value = "%prefix%&cPlugin descarregado com problemas.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cPlugin unloaded with problems.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPluginUnloadProblems;
    @Values(
            {
                    @Value(value = "%prefix%&ePlugin carregado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&ePlugin loaded successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPluginLoad;
    @Values(
            {
                    @Value(value = "%prefix%&cPlugin carregado com problemas.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cPlugin loaded with problems.", lang = LangTypes.EN_US)
            }
    )
    public static String generalPluginLoadProblems;
    @Values(
            {
                    @Value(value = "Salvando informações.", lang = LangTypes.PT_BR),
                    @Value(value = "Saving information.", lang = LangTypes.EN_US)
            }
    )
    public static String generalSaveDataMessage;
    @Values(
            {
                    @Value(value = "Salvo com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "Saved successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String generalSaveDataSuccess;

    @Values(
            {
                    @Value(value = "%prefix%&aEnviado código ao discord do dono, apenas pegar e dar /e reset [código].", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String generalResetMessage;
    @Values(
            {
                    @Value(value = "%prefix%&cNão tem nenhum dono setado na config.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String generalResetMessageNotSet;

    @Values(
            {
                    @Value(value = "Utilize o comando '/e reset %value%' para resetar as informações do servidor.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String generalResetDiscordMessage;

    //time
    @Values(
            {
                    @Value(value = "segundos", lang = LangTypes.PT_BR),
                    @Value(value = "seconds", lang = LangTypes.EN_US)
            }
    )
    public static String timeSeconds;
    @Values(
            {
                    @Value(value = "segundo", lang = LangTypes.PT_BR),
                    @Value(value = "second", lang = LangTypes.EN_US)
            }
    )
    public static String timeSecond;
    @Values(
            {
                    @Value(value = "s", lang = LangTypes.PT_BR),
                    @Value(value = "s", lang = LangTypes.EN_US)
            }
    )
    public static String timeSecondShort;
    @Values(
            {
                    @Value(value = "minutos", lang = LangTypes.PT_BR),
                    @Value(value = "minutes", lang = LangTypes.EN_US)
            }
    )
    public static String timeMinutes;
    @Values(
            {
                    @Value(value = "minuto", lang = LangTypes.PT_BR),
                    @Value(value = "minute", lang = LangTypes.EN_US)
            }
    )
    public static String timeMinute;
    @Values(
            {
                    @Value(value = "m", lang = LangTypes.PT_BR),
                    @Value(value = "m", lang = LangTypes.EN_US)
            }
    )
    public static String timeMinuteShort;
    @Values(
            {
                    @Value(value = "horas", lang = LangTypes.PT_BR),
                    @Value(value = "hours", lang = LangTypes.EN_US)
            }
    )
    public static String timeHours;
    @Values(
            {
                    @Value(value = "hora", lang = LangTypes.PT_BR),
                    @Value(value = "hour", lang = LangTypes.EN_US)
            }
    )
    public static String timeHour;
    @Values(
            {
                    @Value(value = "h", lang = LangTypes.PT_BR),
                    @Value(value = "h", lang = LangTypes.EN_US)
            }
    )
    public static String timeHourShort;
    @Values(
            {
                    @Value(value = "dias", lang = LangTypes.PT_BR),
                    @Value(value = "days", lang = LangTypes.EN_US)
            }
    )
    public static String timeDays;
    @Values(
            {
                    @Value(value = "dia", lang = LangTypes.PT_BR),
                    @Value(value = "day", lang = LangTypes.EN_US)
            }
    )
    public static String timeDay;
    @Values(
            {
                    @Value(value = "d", lang = LangTypes.PT_BR),
                    @Value(value = "d", lang = LangTypes.EN_US)
            }
    )
    public static String timeDayShort;

    //auth

    @Values(
            {
                    @Value(value = "%prefix%&cVocê logou com VPN, por favor desative!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String authVpn;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê logou com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have successfully logged in!", lang = LangTypes.EN_US)
            }
    )
    public static String authLoggedIn;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê foi registrado com sucesso", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have been successfully registered!", lang = LangTypes.EN_US)
            }
    )
    public static String authRegisterSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&cSenha incorreta!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cIncorrect password!", lang = LangTypes.EN_US)
            }
    )
    public static String authIncorrectPassword;
    @Values(
            {
                    @Value(value = "&cVocê errou mais de %quant% vezes!", lang = LangTypes.PT_BR),
                    @Value(value = "&cYou got it wrong more than %quant% times!", lang = LangTypes.EN_US)
            }
    )
    public static String authKickMessage;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê escreveu duas senhas diferentes!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou wrote two different passwords!", lang = LangTypes.EN_US)
            }
    )
    public static String authDifferentPasswords;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê já registrou o máximo de contas permitidas que é %max%!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have already registered the maximum allowed accounts which is %max%!", lang = LangTypes.EN_US)
            }
    )
    public static String authMaxRegister;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê ultrapassou o máximo de caracteres possiveis da senha que é de 16", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have exceeded the maximum number of characters possible for the password, which is 16!", lang = LangTypes.EN_US)
            }
    )
    public static String authPasswordMaxLength;

    @Values(
            {
                    @Value(value = "%prefix%&cNo minimo a senha tem que ter 5 caracteres", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cAt least the password must be 5 characters long.", lang = LangTypes.EN_US)
            }
    )
    public static String authPasswordMinLength;

    @Values(
            {
                    @Value(value = "%prefix%&aLogado pelo sistema de auto login!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aLogged in by automatic login system", lang = LangTypes.EN_US)
            }
    )
    public static String authAutoLogin;

    @Values(
            {
                    @Value(value = "%prefix%&aLogue com o comando /logar <senha>", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aLog in with the command /login <password>", lang = LangTypes.EN_US)
            }
    )
    public static String authLoginMessage;

    @Values(
            {
                    @Value(value = "%prefix%&aRegistre com o comando /registrar <senha> <senha>!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aRegister with the command /register <password> <password>!", lang = LangTypes.EN_US)
            }
    )
    public static String authRegisterMessage;

    @Values(
            {
                    @Value(value = "&cVocê foi kickado por passar do tempo limite de login ou registro!", lang = LangTypes.PT_BR),
                    @Value(value = "&cYou've been kicked for exceeding the login or registration timeout!", lang = LangTypes.EN_US)
            }
    )
    public static String authKickMessageTime;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê logou o %player% com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have successfully logged in to %player!", lang = LangTypes.EN_US)
            }
    )
    public static String authOtherLogin;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê registrou o %player% com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have successfully registered %player%!", lang = LangTypes.EN_US)
            }
    )
    public static String authOtherRegister;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê alterou a senha com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have successfully changed your password.!", lang = LangTypes.EN_US)
            }
    )
    public static String authChangePass;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê alterou a senha do %player% com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou have successfully changed the %player% password!", lang = LangTypes.EN_US)
            }
    )
    public static String authOtherChangePass;

    @Values(
            {
                    @Value(value = "%prefix%&cO %player% já está logado!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&c%player% is already logged in!!", lang = LangTypes.EN_US)
            }
    )
    public static String authOtherAlreadyLogged;

    @Values(
            {
                    @Value(value = "%prefix%&aÚltimo ip logado : %ip%!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String authIpMessage;

    //LIMIT

    @Values(
            {
                    @Value(value = "&cEste grupo não existe no servidor", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String limitGroupDoNotExist;

    //PLAYTIME


    @Values(
            {
                    @Value(value = "&eTempo Jogado:|&e%time%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> playtimeInventoryItemsLore;
    @Values(
            {
                    @Value(value = "&ePlayer: &f%player%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String playtimeInventoryItemsName;
    @Values(
            {
                    @Value(value = "&eVoltar página", lang = LangTypes.PT_BR),
                    @Value(value = "&eBack page", lang = LangTypes.EN_US)
            }
    )
    public static String playtimeInventoryIconBackName;
    @Values(
            {
                    @Value(value = "&ePróxima página", lang = LangTypes.PT_BR),
                    @Value(value = "&eNext page", lang = LangTypes.EN_US)
            }
    )
    public static String playtimeInventoryIconNextName;

    @Values(
            {
                    @Value(value = "%prefix%&e%player% jogou por %time%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String playtimeMessage;

    //shop

    @Values(
            {
                    @Value(value = "&aAberto", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopOpen;
    @Values(
            {
                    @Value(value = "&cFechado", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopClosed;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa loja não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopNotExist;
    @Values(
            {
                    @Value(value = "%prefix%&eSua loja foi setada com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopCreateShopSuccess;
    @Values(
            {
                    @Value(value = "%open%|&eVisitas -> %visits%|&eClique para ir a loja", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> shopInventoryItemsLore;
    @Values(
            {
                    @Value(value = "&eLoja do &f%player%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopInventoryItemsName;
    @Values(
            {
                    @Value(value = "&eVoltar página", lang = LangTypes.PT_BR),
                    @Value(value = "&eBack page", lang = LangTypes.EN_US)
            }
    )
    public static String shopInventoryIconBackName;
    @Values(
            {
                    @Value(value = "&ePróxima página", lang = LangTypes.PT_BR),
                    @Value(value = "&eNext page", lang = LangTypes.EN_US)
            }
    )
    public static String shopInventoryIconNextName;
    @Values(
            {
                    @Value(value = "%prefix%&eNenhuma loja criada ainda.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopNotExistShop;
    @Values(
            {
                    @Value(value = "%prefix%&aVocê foi teleportado para a loja do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopTeleport;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa loja se encontra fechada!.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopClosedMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê mudou o estado da para %open%&e.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopSwitchMessage;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê precisa criar uma loja primeiro!.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopNotCreated;

    @Values(
            {
                    @Value(value = "&aSetar sua loja nesta posição.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopLoreSet;

    @Values(
            {
                    @Value(value = "&aAo cliar aqui você mudará o estado de sua loja!.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String shopLoreSwitch;

    //kits
    @Values(
            {
                    @Value(value = "%prefix%&cEsse kit não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis kit don't exist", lang = LangTypes.EN_US)
            }
    )
    public static String kitsNotExist;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse kit já existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis kit already exists.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsExist;
    @Values(
            {
                    @Value(value = "%prefix%&eKit %kit% &efoi criado com sucesso, para edita-lo utilize /editarkit %kit%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eKit %kit% &ewas created successfully, to edit it use /editkit %kit%", lang = LangTypes.EN_US)
            }
    )
    public static String kitsCreateKitSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eKit %kit% &edeletado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eKit %kit% &esuccessfully deleted.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsDelKitSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eKit %kit% &efoi editado com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eKit %kit% &ehas been edited successfully!", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&cTamanho maximo do nome do kit 16 caracteres.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cMaximum length of the kit name 16 characters.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsNameLength;
    @Values(
            {
                    @Value(value = "&fNome do kit -> %realname%|&eClique para ver o kit", lang = LangTypes.PT_BR),
                    @Value(value = "&fKit name -> %realname%|&eClick to see the kit", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsInventoryItemsLore;
    @Values(
            {
                    @Value(value = "&eKit &f%kitrealname%", lang = LangTypes.PT_BR),
                    @Value(value = "&eKit &f%kitrealname%", lang = LangTypes.EN_US)
            }
    )
    public static String kitsInventoryItemsName;
    @Values(
            {
                    @Value(value = "&eClique aqui para editar o kit", lang = LangTypes.PT_BR),
                    @Value(value = "&eClick here to edit the kit", lang = LangTypes.EN_US)
            }
    )
    public static String kitsInventoryIconEditKitName;
    @Values(
            {
                    @Value(value = "&eVoltar página", lang = LangTypes.PT_BR),
                    @Value(value = "&eBack page", lang = LangTypes.EN_US)
            }
    )
    public static String kitsInventoryIconBackName;
    @Values(
            {
                    @Value(value = "&ePróxima página", lang = LangTypes.PT_BR),
                    @Value(value = "&eNext page", lang = LangTypes.EN_US)
            }
    )
    public static String kitsInventoryIconNextName;
    @Values(
            {
                    @Value(value = "%prefix%&eEditKit - Items", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eEditKit - Items", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryItemsName;
    @Values(
            {
                    @Value(value = "&fClique para editar|&fAo clicar você poderá|&fEditar todos os items", lang = LangTypes.PT_BR),
                    @Value(value = "&fClick to edit|&fBy clicking you can|&fEdit all items", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsEditKitInventoryItemsLore;
    @Values(
            {
                    @Value(value = "%prefix%&eTempo do kit setado para %time%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eKit cooldown is %time%.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitTime;
    @Values(
            {
                    @Value(value = "&eEditKit - Tempo", lang = LangTypes.PT_BR),
                    @Value(value = "&eEditKit - Cooldown", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryTimeName;
    @Values(
            {
                    @Value(value = "&fClique para editar|&fAo clicar você poderá|&fEditar o tempo de espera|&fPara pegar novamente", lang = LangTypes.PT_BR),
                    @Value(value = "&fClick to edit|&fBy clicking you can|&fEdit cooldown time|&fTo pick up again", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsEditKitInventoryTimeLore;
    @Values(
            {
                    @Value(value = "%prefix%&eDigite no chat qual será o tempo de espera para pegar novamente o kit, formato tempo/unidade -> Exemplo 30s, unidades -> [s,m,h,d]", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eType in the chat what the cooldown time will be to pick up the kit again, format time/unity -> Ex. 30s, unity -> [s,m,h,d]", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryTimeMessage;
    @Values(
            {
                    @Value(value = "&eEditKit - Nome", lang = LangTypes.PT_BR),
                    @Value(value = "&eEditKit - Name", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryNameName;
    @Values(
            {
                    @Value(value = "&fClique para editar|&fAo clicar você poderá|&fEditar o nome do kit", lang = LangTypes.PT_BR),
                    @Value(value = "&fClick to edit|&fBy clicking you can|&fEdit kit name", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsEditKitInventoryNameLore;
    @Values(
            {
                    @Value(value = "%prefix%&eDigite no chat qual será o novo nome fictício para seu kit pode utilizar cores, caso queira mudar o nome criado terá que criar um novo kit...", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eType in the chat what will be the name for your kit, you can use colors, if you want to change the name created, you will have to create a new kit...", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryNameMessage;
    @Values(
            {
                    @Value(value = "&eEditKit - Peso", lang = LangTypes.PT_BR),
                    @Value(value = "&eEditKit - Weight", lang = LangTypes.EN_US)
            }
    )


    public static String kitsEditKitInventoryWeightName;
    @Values(
            {
                    @Value(value = "&fClique para editar|&fAo clicar você poderá|&fEditar o peso do kit", lang = LangTypes.PT_BR),
                    @Value(value = "&fClick to edit|&fBy clicking you can|&fEdit kit weight", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsEditKitInventoryWeightLore;
    @Values(
            {
                    @Value(value = "%prefix%&eDigite no chat qual será o novo peso, lembre-se quanto maior o peso mais na frente o kit aparecerá na GUI.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eType in the chat what the new weight will be, remember the higher the weight the further the kit will appear in the GUI.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsEditKitInventoryWeightMessage;
    @Values(
            {
                    @Value(value = "&cFalta %time% para pegar seu kit novamente", lang = LangTypes.PT_BR),
                    @Value(value = "&c%time% left to get your kit again", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGetMessage;
    @Values(
            {
                    @Value(value = "&cClique aqui para pegar o seu kit", lang = LangTypes.PT_BR),
                    @Value(value = "&cClick here to get your kit", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGetIcon;
    @Values(
            {
                    @Value(value = "&cVocê não consegue pegar esse kit", lang = LangTypes.PT_BR),
                    @Value(value = "&cYou can't get this kit", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGetIconNotCatch;
    @Values(
            {
                    @Value(value = "&cVocê não tem permissão|", lang = LangTypes.PT_BR),
                    @Value(value = "&cYou don't have permissions|", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsGetIconLoreNotPerm;
    @Values(
            {
                    @Value(value = "&fFalta|&f%time%|&fPara pegar seu kit", lang = LangTypes.PT_BR),
                    @Value(value = "&fWait %time%|&fTo get your kit", lang = LangTypes.EN_US)
            }
    )
    public static List<String> kitsGetIconLoreTime;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê pegou o kit `%kit%&e` com sucesso", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eyou get the kit `%kit%&e` successfully", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGetSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&cLibere mais %slots% slots para pegar esse kit!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&crelease %slots% slots to get this kit!", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGetNoSpace;

    @Values(
            {
                    @Value(value = "%prefix%&eVocê deu o %kit% &epara o(a) %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou gived %kit% &efor %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGiveKitMessageOther;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém do além deu o kit `%kit%&e` para você.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond gave the kit `%kit%&e` for you.", lang = LangTypes.EN_US)
            }
    )
    public static String kitsGiveKitMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eLista de kits %kits%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eKit list %kits%", lang = LangTypes.EN_US)
            }
    )
    public static String kitsList;
    @Values(
            {
                    @Value(value = "%prefix%&eCrie seu kit com /criarkit (nome do kit).", lang = LangTypes.PT_BR),
                    @Value(value = "Create your kit with /createkit (kit name).", lang = LangTypes.EN_US)
            }
    )
    public static String kitsNotExistKits;

    //vips
    @Values(
            {
                    @Value(value = "%prefix%&eVocê criou um novo vip! '%vip%'.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCreateNew;
    @Values(
            {
                    @Value(value = "%prefix%&aVocê criou uma key vip! '%key%'.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCreateNewKey;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse grupo não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsGroupNotExist;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa key não existe, tente outra!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsKeyNotExist;
    @Values(
            {
                    @Value(value = "%prefix%&aVocê ativou o %vip% de %days% dias com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsActivate;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse Vip já existe!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsExist;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse Vip não existe!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsNotExist;

    @Values(
            {
                    @Value(value = "&9> VIP '&e%vipName%&9' - &e%vipTime%", lang = LangTypes.PT_BR),
                    @Value(value = "&9> VIP '&e%vipName%' - %vipTime%", lang = LangTypes.EN_US)
            }
    )
    public static String VipsTimeMessage;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê trocou para o vip %vipName%!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsSwitch;

    @Values(
            {
                    @Value(value = "Utilize o comando '/vip token %value%' para ativar a integração com o discord do servidor", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eUtilize o comando '/vip token <token>' para ativar a integração, o token foi enviado ao seu discord.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordLocalMessage;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse token não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordTokenError;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê ativou a integração com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordTokenActivate;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse grupo não existe no discord.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordRoleError;

    @Values(
            {
                    @Value(value = "%prefix%&aVocê acabou de setar o id do grupo do vip.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordRoleActivate;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse userid não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordUserIdNotExist;

    @Values(
            {
                    @Value(value = "O %player% acabou de ativar o vip %vip% por %time%!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsDiscordActivateMessage;

    @Values(
            {
                    @Value(value = "%prefix%&aO %player% acabou de ativar o vip %vip% por %time%!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsActivateMessage;

    @Values(
            {
                    @Value(value = "%prefix%&aItems do vip %vip% atualizados com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsUpdateItems;

    @Values(
            {
                    @Value(value = "%prefix%&cTire todos items do inventario '/vip items' para pegar esse vip.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsClearItemsInventory;

    @Values(
            {
                    @Value(value = "%prefix%&cEsse player já tem items no '/vip items' por isso ele não pode receber esse vip.", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsClearItemsOtherInventory;

    @Values(
            {
                    @Value(value = "%prefix%&eLista de comandos:", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCommandsListMessage;
    @Values(
            {
                    @Value(value = "&9> '&e%command%&9'", lang = LangTypes.PT_BR),
                    @Value(value = "&9> '&e%command%'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCommandsList;

    @Values(
            {
                    @Value(value = "%prefix%&aComando adicionado!", lang = LangTypes.PT_BR),
                    @Value(value = "&9> '&e%command%'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCommandsAdd;

    @Values(
            {
                    @Value(value = "%prefix%&aComando retirado!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String VipsCommandsRemove;

    @Values(
            {
                    @Value(value = "%prefix%&eTempo dos vips:!", lang = LangTypes.PT_BR),
                    @Value(value = "'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsTimeFirstMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eTempo dos vips do %player%:!", lang = LangTypes.PT_BR),
                    @Value(value = "'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsTimeFirstOtherMessage;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê atualmente não tem nenhum vip ativo!", lang = LangTypes.PT_BR),
                    @Value(value = "'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsTimeNoVip;

    @Values(
            {
                    @Value(value = "%prefix%&eLista de vips:", lang = LangTypes.PT_BR),
                    @Value(value = "not lang", lang = LangTypes.EN_US)
            }
    )
    public static String VipsListMessage;
    @Values(
            {
                    @Value(value = "&9> '&e%vip%&9'", lang = LangTypes.PT_BR),
                    @Value(value = "&9> '&e%vip%'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsList;

    @Values(
            {
                    @Value(value = "%prefix%&cEle não tem este vip ativo!", lang = LangTypes.PT_BR),
                    @Value(value = "'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsRemoveNoVip;

    @Values(
            {
                    @Value(value = "%prefix%&aVip Removido com sucesso!", lang = LangTypes.PT_BR),
                    @Value(value = "'", lang = LangTypes.EN_US)
            }
    )
    public static String VipsRemove;

    //nicks
    @Values(
            {
                    @Value(value = "%prefix%&cTamanho maximo do nome é de 16 caracteres.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cMaximum name length is 16 characters.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksNameLength;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse nick está proibido de utilizar.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis nick is prohibited from using.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksBlocked;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse nick já existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThat nickname already exists.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksExist;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de setar seu nick de %nick%&e.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just set your nick from %nick%&e.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de apagar seu nick.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just deleted your nick.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksRemovedSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de setar o nick do player para %nick%&e.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just set the player's nick to %nick%&e.", lang = LangTypes.EN_US)
            }
    )
    public static String nickOtherSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém do além acaba de setar o seu nick de %nick%&e.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond just set your nick of %nick%&e.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksOtherPlayerSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de apagar o nick do player.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just deleted the player's nick.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksRemovedOtherSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém do além acaba de apagar seu nick.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond just deleted your nick.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksRemovedOtherPlayerSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem nick para remover.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have no nick to remove.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksAlreadyOriginal;
    @Values(
            {
                    @Value(value = "%prefix%&cEle não tem nick para remover.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cHe has no nick to remove.", lang = LangTypes.EN_US)
            }
    )
    public static String nicksAlreadyOriginalOther;

    //home
    @Values(
            {
                    @Value(value = "%prefix%&cTamanho maximo do nome da home é de 16 caracteres.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cMaximum length of home name is 16 characters.", lang = LangTypes.EN_US)
            }
    )
    public static String homesNameLength;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa home já existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis home already exists.", lang = LangTypes.EN_US)
            }
    )
    public static String homesNameAlreadyExist;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa home não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis home does not exist.", lang = LangTypes.EN_US)
            }
    )
    public static String homesNameDontExist;

    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de apagar sua home %home%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just deleted your home %home%.", lang = LangTypes.EN_US)
            }
    )
    public static String homesRemoved;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de apagar uma home %home% do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just deleted a home %home% from %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String homesOtherRemoved;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de criar sua home %home%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just created your home %home%.", lang = LangTypes.EN_US)
            }
    )
    public static String homesCreated;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de criar uma home %home% do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just created a %player% home %home%.", lang = LangTypes.EN_US)
            }
    )
    public static String homesOtherCreated;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê não pode criar mais homes você já alcançou seu limite de %limit%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou cannot create more homes you have already reached your %limit% limit", lang = LangTypes.EN_US)
            }
    )
    public static String homesLimitMessage;
    @Values(
            {
                    @Value(value = "%prefix%&cEsse mundo está bloqueado de criar homes", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis world is locked from creating men", lang = LangTypes.EN_US)
            }
    )
    public static String homesBlockedWorld;
    @Values(
            {
                    @Value(value = "%prefix%&eLista de homes -> %list%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eHomes -> %list%", lang = LangTypes.EN_US)
            }
    )
    public static String homesList;
    @Values(
            {
                    @Value(value = "%prefix%&eLista de homes do %player% -> %list%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eHomes from %player% -> %list%", lang = LangTypes.EN_US)
            }
    )
    public static String homesOtherList;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado com sucesso para sua home %home%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully teleported to your home %home%", lang = LangTypes.EN_US)
            }
    )
    public static String homesTeleported;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado com sucesso para home %home% do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully teleported to %player%'s home %home%.", lang = LangTypes.EN_US)
            }
    )
    public static String homesTeleportedOther;

    //warps
    @Values(
            {
                    @Value(value = "%prefix%&cTamanho maximo do nome da warp é de 16 caracteres.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cMaximum warp name length is 16 characters.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsNameLength;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa warp já existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis warp already exists.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsNameAlreadyExist;
    @Values(
            {
                    @Value(value = "%prefix%&cEssa warp não existe.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis warp does not exist.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsNameDontExist;

    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado com sucesso para warp %warp%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSuccessfully teleported to warp %warp%.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsTeleported;
    @Values(
            {
                    @Value(value = "%prefix%&eLista de warps -> %list%", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eWarps -> %list%", lang = LangTypes.EN_US)
            }
    )
    public static String warpsList;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de criar uma warp chamada %warp%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just created a warp %warp%.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsCreated;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acabou de deletar um warp %warp%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just deleted a %warp% warp.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsRemoved;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê já está esperando um teleporte.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&c	You are already expecting a teleport.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsInTeleport;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado para o warp %warp% do além.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have been teleported to the %warp% warp from beyond.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsTeleportedOtherMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eO %player% foi teleportado para warp %warp% com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player% has been teleported to warp %warp% successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String warpsTeleportedOtherSuccess;

    //tp
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado com sucesso", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou were successfully teleported", lang = LangTypes.EN_US)
            }
    )
    public static String tpTeleportedSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado do além", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou were teleported from beyond", lang = LangTypes.EN_US)
            }
    )
    public static String tpTeleportedOtherSuccess;

    //tpa
    @Values(
            {
                    @Value(value = "%prefix%&eVocê mandou com sucesso o pedido de teleporte para %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully sent the teleport request to %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de receber um pedido de teleporte de %player%, você tem %time% segundos para aceita-lo com /tpaccept ou rejeita-lo com /tpdeny.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just received a teleport request from %player%, you have %time% seconds to accept /tpaccept or reject it with /tpdeny.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaOtherReceived;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê acabou de enviar um TPA aguarde para enviar novamente.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have just submitted a TPA, please wait to submit again.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaAlreadySend;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem nenhum pedido para aceitar.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have no requests to accept.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaNotAnyRequest;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem nenhum pedido para cancelar.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou have no requests to cancel.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaNotAnyRequestToDeny;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de aceitar o pedido de %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just accepted %player%'s request.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestAccepted;
    @Values(
            {
                    @Value(value = "%prefix%&eO %player% acaba de aceitar seu pedido de teleporte, teleportando em %time% segundos.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player% just accepted your teleport request, teleporting in %time% seconds", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestOtherAccepted;
    @Values(
            {
                    @Value(value = "%prefix%&eO %player% acaba de aceitar seu pedido de teleporte, teleportando.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player% just accepted your teleport request, teleporting.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestOtherNoDelayAccepted;
    @Values(
            {
                    @Value(value = "%prefix%&cO player já tem o pedido ativo de outra pessoa aguarde.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThe player already has someone else's active request, please wait.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaAlreadyInAccept;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de cancelar o pedido de %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just canceled the request for %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestDeny;
    @Values(
            {
                    @Value(value = "%prefix%&eSeu pedido acaba de ser cancelado pelo(a) %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour request has just been canceled by %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestOtherDeny;
    @Values(
            {
                    @Value(value = "%prefix%&eSeu pedido de TPA foi cancelado porque o %player% não aceitou.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour TPA request was canceled because %player% did not accept it.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaRequestOtherDenyTime;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não pode enviar tpa para você mesmo.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou cannot send tpa to yourself.", lang = LangTypes.EN_US)
            }
    )
    public static String tpaSameName;

    //echest
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acabou de abrir seu enderChest.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just opened your enderChest.", lang = LangTypes.EN_US)
            }
    )
    public static String echestSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acabou de abrir o enderChest de %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just opened the %player% enderChest.", lang = LangTypes.EN_US)
            }
    )
    public static String echestOtherSuccess;

    //gamemode
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acabou de entrar no gameMode %gamemode%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just entered gameMode %gamemode%.", lang = LangTypes.EN_US)
            }
    )
    public static String gamemodeUseSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguem do além setou seu gameMode de %gamemode%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond set your gameMode to %gamemode%.", lang = LangTypes.EN_US)
            }
    )
    public static String gamemodeUseOtherSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê já está nesse gamemode.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou are already in this gamemode.", lang = LangTypes.EN_US)
            }
    )
    public static String gamemodeSameGamemode;
    @Values(
            {
                    @Value(value = "%prefix%&cEle já está nesse gamemode.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cHe is already in this gamemode.", lang = LangTypes.EN_US)
            }
    )
    public static String gamemodeSameOtherGamemode;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de setar o gamemode do(a) %player% para %gamemode%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have just set %player%'s gamemode to %gamemode%.", lang = LangTypes.EN_US)
            }
    )
    public static String gamemodeSuccessOtherMessage;

    //vanish
    @Values(
            {
                    @Value(value = "%prefix%&eSeu vanish foi ativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour vanish has been activated successfully.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishActive;
    @Values(
            {
                    @Value(value = "%prefix%&eSeu vanish foi desativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour vanish has been successfully deactivated.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém ativou seu vanish.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone activated your vanish.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishOtherActive;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém desativou seu vanish.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone deactivated their vanish.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishOtherDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eO Vanish do(a) %player% foi ativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player%'s Vanish has been activated.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishActivatedOther;
    @Values(
            {
                    @Value(value = "%prefix%&eO Vanish do(a) %player% foi desativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player%'s Vanish has been disabled.", lang = LangTypes.EN_US)
            }
    )
    public static String vanishDisabledOther;

    //feed
    @Values(
            {
                    @Value(value = "%prefix%&eVocê encheu com sucesso sua barra de comida.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully filled your foodbar.", lang = LangTypes.EN_US)
            }
    )
    public static String feedMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê já está cheio seu guloso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou're already full your sweet tooth.", lang = LangTypes.EN_US)
            }
    )
    public static String feedFullMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém do além encheu sua barra de comida.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond filled your food bar.", lang = LangTypes.EN_US)
            }
    )
    public static String feedOtherMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eO player já está com a barra de comida cheia.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe player already has a full food bar.", lang = LangTypes.EN_US)
            }
    )
    public static String feedOtherFullMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê encheu com sucesso a barra de comida do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully filled the %player% food bar.", lang = LangTypes.EN_US)
            }
    )
    public static String feedSuccessOtherMessage;

    //heal
    @Values(
            {
                    @Value(value = "%prefix%&eVocê encheu com sucesso sua barra de vida.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully filled your life bar.", lang = LangTypes.EN_US)
            }
    )
    public static String healMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê já está com vida cheia.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou are already full of life.", lang = LangTypes.EN_US)
            }
    )
    public static String healFullMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém do além encheu sua barra de vida.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone from beyond filled your life bar.", lang = LangTypes.EN_US)
            }
    )
    public static String healOtherMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eO player já está com vida cheio.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe player is already at full health.", lang = LangTypes.EN_US)
            }
    )
    public static String healOtherFullMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê encheu com sucesso a barra de vida do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully filled %player%'s health bar.", lang = LangTypes.EN_US)
            }
    )
    public static String healSuccessOtherMessage;

    //light
    @Values(
            {
                    @Value(value = "%prefix%&eA Luz foi ativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe Light has been activated.", lang = LangTypes.EN_US)
            }
    )
    public static String lightActive;
    @Values(
            {
                    @Value(value = "%prefix%&eA Luz foi desativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe Light has been deactivated.", lang = LangTypes.EN_US)
            }
    )
    public static String lightDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém ativou sua Luz.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone activated your Light.", lang = LangTypes.EN_US)
            }
    )
    public static String lightOtherActive;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém desativou sua Luz.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone turned off your Light.", lang = LangTypes.EN_US)
            }
    )
    public static String lightOtherDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eA Luz do(a) %player% foi ativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe %player% Light has been activated.", lang = LangTypes.EN_US)
            }
    )
    public static String lightActivatedOther;
    @Values(
            {
                    @Value(value = "%prefix%&eA Luz do(a) %player% foi desativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe %player% light has been turned off.", lang = LangTypes.EN_US)
            }
    )
    public static String lightDisabledOther;

    //back
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem nenhuma localização salva para voltar.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou don't have any saved locations to go back to.", lang = LangTypes.EN_US)
            }
    )
    public static String backNotToBack;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê voltou a sua ultima localização.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have returned to your last location.", lang = LangTypes.EN_US)
            }
    )
    public static String backSuccess;

    //spawn
    @Values(
            {
                    @Value(value = "%prefix%&eVocê teleportou com sucesso para o spawn.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully teleported to spawn.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém teleportou você para o spawn.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone teleported you to spawn.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnOtherMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê teleportou com sucesso o(a) %player% para o spawn.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully teleported %player% to spawn.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnSuccessOtherMessage;
    @Values(
            {
                    @Value(value = "%prefix%&cO Spawn ainda não está setado, utilize /setspawn para setar.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cSpawn is not set yet, use /setspawn to set it.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnNotSet;
    @Values(
            {
                    @Value(value = "%prefix%&eSpawn setado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSpawn successfully set.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnSetMessage;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê já está esperando um teleporte.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou are already expecting a teleport.", lang = LangTypes.EN_US)
            }
    )
    public static String spawnInTeleport;

    //fly
    @Values(
            {
                    @Value(value = "%prefix%&eSeu fly foi ativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour fly has been activated.", lang = LangTypes.EN_US)
            }
    )
    public static String flyActive;
    @Values(
            {
                    @Value(value = "%prefix%&eSeu fly foi desativado com sucesso.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYour fly has been disabled.", lang = LangTypes.EN_US)
            }
    )
    public static String flyDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém ativou seu fly.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone activated your fly.", lang = LangTypes.EN_US)
            }
    )
    public static String flyOtherActive;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém desativou seu fly.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone has disabled your fly.", lang = LangTypes.EN_US)
            }
    )
    public static String flyOtherDisable;
    @Values(
            {
                    @Value(value = "%prefix%&eO fly do(a) %player% foi ativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe %player% fly has been activated.", lang = LangTypes.EN_US)
            }
    )
    public static String flyActivatedOther;
    @Values(
            {
                    @Value(value = "%prefix%&eO fly do(a) %player% foi desativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe %player% fly has been disabled.", lang = LangTypes.EN_US)
            }
    )
    public static String flyDisabledOther;
    @Values(
            {
                    @Value(value = "%prefix%&cEste mundo está com fly desativado.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThis world is fly off.", lang = LangTypes.EN_US)
            }
    )
    public static String flyDisabledWorld;

    //online
    @Values(
            {
                    @Value(value = "%prefix%&eExiste %amount% players online.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThere are %amount% players online.", lang = LangTypes.EN_US)
            }
    )
    public static String onlineMessage;

    //tphere
    @Values(
            {
                    @Value(value = "%prefix%&eVocê teleportou com sucesso o(a) %player% para você.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have successfully teleported %player% to yourself.", lang = LangTypes.EN_US)
            }
    )
    public static String tphereTeleportedSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê foi teleportado do além.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou were teleported from beyond.", lang = LangTypes.EN_US)
            }
    )
    public static String tphereTeleportedOtherSuccess;

    //trash
    @Values(
            {
                    @Value(value = "&cLixeira", lang = LangTypes.PT_BR),
                    @Value(value = "&cTrash", lang = LangTypes.EN_US)
            }
    )
    public static String trashMenuName;

    @Values(
            {
                    @Value(value = "%prefix%&eSua velocidade foi setado para %value%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eHis speed has been set to %value%.", lang = LangTypes.EN_US)
            }
    )
    public static String speedSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eSua velocidade voltou para o padrão.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eHis speed has returned to default.", lang = LangTypes.EN_US)
            }
    )
    public static String speedRemove;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém setou sua velocidade para %value%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone set your speed to %value%.", lang = LangTypes.EN_US)
            }
    )
    public static String speedOtherSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eAlguém setou sua velocidade para padrão.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSomeone set your speed to default.", lang = LangTypes.EN_US)
            }
    )
    public static String speedOtherRemove;
    @Values(
            {
                    @Value(value = "%prefix%&eA Velocidade do(a) %player% foi foi setado para %value%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player% Speed ​​has been set to %value%.", lang = LangTypes.EN_US)
            }
    )
    public static String speedSuccessOther;
    @Values(
            {
                    @Value(value = "%prefix%&eA Velocidade do(a) %player% voltou para o padrão.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e%player%'s Speed ​​has returned to default.", lang = LangTypes.EN_US)
            }
    )
    public static String speedRemoveOther;
    @Values(
            {
                    @Value(value = "%prefix%&eA Velocidade pode ser setado de 0 a 10.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eSpeed ​​can be set from 0 to 10.", lang = LangTypes.EN_US)
            }
    )
    public static String speedIncorrectValue;

    //hat
    @Values(
            {
                    @Value(value = "%prefix%&eVocê acaba de colocar um Chapéu.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou just put on a Hat.", lang = LangTypes.EN_US)
            }
    )
    public static String hatSuccess;
    @Values(
            {
                    @Value(value = "%prefix%&eFoi só encontrado ar em sua mão, coloque algum item!", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eIt was only found air in your hand, put some item!", lang = LangTypes.EN_US)
            }
    )
    public static String hatNotFound;

    //antiafk

    @Values(
            {
                    @Value(value = "%prefix%&cVocê foi teleportado para o spawn por inatividade!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String antiafkMessage;

    //clearitems

    @Values(
            {
                    @Value(value = "%prefix%&cLimpando o chão em %time%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String ClearitemsMessage;

    @Values(
            {
                    @Value(value = "%prefix%&cChão Limpo!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String ClearitemsFinishMessage;

    //material

    @Values(
            {
                    @Value(value = "%prefix%&aNome do material: %material%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String MaterialName;


    //money
    @Values(
            {
                    @Value(value = "%prefix%&eVocê tem %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have %unity% %money%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyMessage;
    @Values(
            {
                    @Value(value = "%prefix%&eO %player% tem %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eThe %player% has %unity% %money%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyMessageOther;
    @Values(
            {
                    @Value(value = "$ ", lang = LangTypes.PT_BR),
                    @Value(value = "$ ", lang = LangTypes.EN_US)
            }
    )
    public static String moneySymbol;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê pagou %unity% %money% para %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou paid %money% %unity% to %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyPay;
    @Values(
            {
                    @Value(value = "%prefix%&aVocê recebeu %unity% %money% do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou received %money% %unity% from %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyPayOther;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não pode pagar você mesmo.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou can't pay yourself.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyPaySame;
    @Values(
            {
                    @Value(value = "%prefix%&eVocê setou o dinheiro do %player% para %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eYou have set %player% money to %money% %unity%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneySet;
    @Values(
            {
                    @Value(value = "%prefix%&cAlguém do além setou seu dinheiro para %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cSomeone from beyond set their money to %money% %unity%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneySetOther;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê tirou %unity% %money% do %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou took %money% %unity% from %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyTake;
    @Values(
            {
                    @Value(value = "%prefix%&cAlguém do além tirou do seu dinheiro %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cSomeone from beyond took %money% %unity% of your money.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyTakeOther;
    @Values(
            {
                    @Value(value = "%prefix%&aVocê deu %unity% %money% ao %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aYou gave %money% %unity% to %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyAdd;
    @Values(
            {
                    @Value(value = "%prefix%&aAlguém do além adicionou ao seu dinheiro %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&aSomeone from beyond added %money% %unity% to your money.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyAddOther;
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem dinheiro suficiente, falta %unity% %money%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou don't have enough money, you lack %money% %unity%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyMissing;
    @Values(
            {
                    @Value(value = "%prefix%&eRank dos mais ricos - 10.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&eRank of the richest - 10.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyTopMessage;
    @Values(
            {
                    @Value(value = "&9> &e%position% - %player%, &e%unity% &a%money%.", lang = LangTypes.PT_BR),
                    @Value(value = "&9> &e%position% - %player%, &a%money% &e%unity%.", lang = LangTypes.EN_US)
            }
    )
    public static String moneyTop;

    //invsee
    @Values(
            {
                    @Value(value = "%prefix%&cVocê não pode ver o seu próprio inventário.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cYou cannot see your own inventory.", lang = LangTypes.EN_US)
            }
    )
    public static String invseeSameName;
    @Values(
            {
                    @Value(value = "%prefix%&cO player Saiu e por isso foi fechado o inventário.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThe player left and so the inventory was closed.", lang = LangTypes.EN_US)
            }
    )
    public static String invseePlayerLeave;

    //death messages
    @Values(
            {
                    @Value(value = "%prefix%&cO %player% morreu para o(a) %killer%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThe %player% died for the %killer%.", lang = LangTypes.EN_US)
            }
    )
    public static String deathmessagesPlayerKillPlayer;
    @Values(
            {
                    @Value(value = "%prefix%&cO %player% morreu para o(a) %entity%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThe %player% died for the %entity%.", lang = LangTypes.EN_US)
            }
    )
    public static String deathmessagesEntityKillPlayer;
    @Values(
            {
                    @Value(value = "%prefix%&cO %player% morreu.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&cThe %player% died.", lang = LangTypes.EN_US)
            }
    )
    public static String deathmessagesNothingKillPlayer;
    @Values(
            {
                    @Value(value = "&cCausa %cause% não está registrada em cause-replacer", lang = LangTypes.PT_BR),
                    @Value(value = "&cCause %cause% is not registered in cause-replacer", lang = LangTypes.EN_US)
            }
    )
    public static String deathmessagesCauseNotExist;
    @Values(
            {
                    @Value(value = "AXOLOTL-Axolote|BEE-Abelha|BLAZE-Blaze|CAVE_SPIDER-Aranha da Caverna|COD-Bacalhau|CREEPER-Creeper|DRAGON_FIREBALL-Bola de Fogo|ENDER_DRAGON-Dragão do Fim|ENDERMAN-Enderman|ENDERMITE-Endermite|EVOKER-Invocador|GHAST-Ghast|GIANT-Zumbi Gigante|GUARDIAN-Guardião|HOGLIN-Hoglin|HUSK-Zumbi do Deserto|ILLUSIONER-Ilusionista|IRON_GOLEM-Golem de Ferro|MAGMA_CUBE-Cubo de Magma|PIGLIN-Piglin|PIGLIN_BRUTE-Piglin B|PIG_ZOMBIE-Porco Zumbi|PILLAGER-Saqueador|PUFFERFISH-Baiacu|RAVAGER-Devastador|SHULKER-Shulker|SHULKER_BULLET-Dardo de Shulker|SKELETON-Esqueleto|SLIME-Slime|SPIDER-Aranha|STRIDER-Lavagante|STRAY-Esqueleto Vagante|VEX-Fantasma|VINDICATOR-Vingador|WITCH-Bruxa|WITHER-Wither|WITHER_SKELETON-Esqueleto Wither|WITHER_SKULL-Cabeça do Wither|WOLF-Lobo|ZOGLIN-Zoglin|ZOMBIE-Zumbi|ZOMBIE_VILLAGER-AldeZumbi|ZOMBIFIED_PIGLIN-Piglin Zumbi", lang = LangTypes.PT_BR),
                    @Value(value = "AXOLOTL-Axolotl|BEE-Bee|BLAZE-Blaze|CAVE_SPIDER-SpiderCave|COD-Cod|CREEPER-Creeper|DRAGON_FIREBALL-Fireball|ENDER_DRAGON-EnderDragon|ENDERMAN-Enderman|ENDERMITE-Endermite|EVOKER-Evoker|GHAST-Ghast|GIANT-Giant|GUARDIAN-Guardian|HOGLIN-Hoglin|HUSK-Husk|ILLUSIONER-Illusioner|IRON_GOLEM-IronGolem|MAGMA_CUBE-MagmaCube|PIGLIN-Piglin|PIGLIN_BRUTE-Piglin B|PIG_ZOMBIE-ZumbiPig|PILLAGER-Pillager|PUFFERFISH-Pufferfish|RAVAGER-Ravager|SHULKER-Shulker|SHULKER_BULLET-ShulkerBullet|SKELETON-Esqueleto|SLIME-Slime|SPIDER-Spider|STRIDER-Strider|STRAY-Stray|VEX-Vex|VINDICATOR-Vindicator|WITCH-Witch|WITHER-Wither|WITHER_SKELETON-Wither skeleton|WITHER_SKULL-Skull Wither|WOLF-Wolf|ZOGLIN-Zoglin|ZOMBIE-Zumbie|ZOMBIE_VILLAGER-ZumbiVillager|ZOMBIFIED_PIGLIN-ZumbiePiglin", lang = LangTypes.EN_US)
            }
    )
    public static List<String> deathmessagesEntityReplacer;
    @Values(
            {
                    @Value(value = "|SUICIDE-%prefix%&cO %player% se suicidiou-se.|POISON-%prefix%&cO %player% Morreu envenenado.|STARVATION-%prefix%&cO %player% Morreu de fome.|FALL-%prefix%&cO %player% caiu de um lugar alto.|DROWNING-%prefix%&cO %player% morreu afogado.|PROJECTILE-%prefix%&cO %player% Tomou uma flechada.|FIRE_TICK-%prefix%&cO %player% Pegou fogo.|FIRE-%prefix%&cO %player% Morreu Queimado.|ARROWS-%prefix%&cO %player% Tomou uma flechada.|CACTUS-%prefix%&cO %player% Tentou abraçar um cacto.|ENTITY_EXPLOSION-%prefix%&cO %player% Explodiu.|LIGHTNING-%prefix%&cO %player% Recebeu uma descarga elétrica e morreu.|SUFFOCATION-%prefix%&cO %player% Morreu sufocado.|LAVA-%prefix%&cO %player% Achou que lava era água e foi dar um mergulho.|MAGIC-%prefix%&cO %player% Foi vítima de bruxaria das brabas.|WITHER-%prefix%&cO %player% Foi morto por um WitherBoss.|BLOCK_EXPLOSION-%prefix%&cO %player% Explodiu.|VOID-%prefix%&cO %player% Morreu.", lang = LangTypes.PT_BR),
                    @Value(value = "|SUICIDE-%prefix%&c%player% committed suicide.|POISON-%prefix%&c%player% He died of poison.|STARVATION-%prefix%&c%player% Starved to death.|FALL-%prefix%&c%player% fell from a high place.|DROWNING-%prefix%&c%player% he drowned.|PROJECTILE-%prefix%&c%player% Took an arrow.|FIRE_TICK-%prefix%&c%player% Caught fire.|FIRE-%prefix%&c%player% He died burnt.|ARROWS-%prefix%&c%player% Took an arrow.|CACTUS-%prefix%&c%player% Tried to hug a cactus.|ENTITY_EXPLOSION-%prefix%&cO %player% Blow up.|LIGHTNING-%prefix%&c%player% He received an electrical shock and died.|SUFFOCATION-%prefix%&c%player% suffocated to death.|LAVA-%prefix%&c%player% He thought lava was water and went for a swim.|MAGIC-%prefix%&c%player% He was a victim of witchcraft from the brabas.|WITHER-%prefix%&c%player% He was killed by a WitherBoss.|BLOCK_EXPLOSION-%prefix%&c%player% blow up.|VOID-%prefix%&c%player% Dead.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> deathmessagesCauseReplacer;


    @Values(
            {
                    @Value(value = "%prefix%&e[+] %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e[+] %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String messagesEnterMessage;
    @Values(
            {
                    @Value(value = "%prefix%&e[-] %player%.", lang = LangTypes.PT_BR),
                    @Value(value = "%prefix%&e[-] %player%.", lang = LangTypes.EN_US)
            }
    )
    public static String messagesLeaveMessage;

    @Values(
            {
                    @Value(value = "&e[Anuncio] &9%name%&e: %message%", lang = LangTypes.PT_BR),
                    @Value(value = "&e[Announcement] &9%name%&e: %message%", lang = LangTypes.EN_US)
            }
    )
    public static String announceSendAnnounce;

    @Values(
            {
                    @Value(value = "Token errado ou faltando, coloque certo para enviar ao discord, ou desative a opção de enviar ao chat do discord.", lang = LangTypes.PT_BR),
                    @Value(value = "Wrong or missing token, put it right to send to discord, or disable the option to send to discord chat.", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatNoToken;
    @Values(
            {
                    @Value(value = "Chat do discord especificado não existe, coloque um certo ou desative!", lang = LangTypes.PT_BR),
                    @Value(value = "Specified discord chat does not exist, set one right or disable!", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatNoChatId;
    @Values(
            {
                    @Value(value = "**`%group% %player%`**: %message%", lang = LangTypes.PT_BR),
                    @Value(value = "**`%group% %player%`**: %message%", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatMessageToDiscordPattern;
    @Values(
            {
                    @Value(value = "&e[Disc] %player%: %message%", lang = LangTypes.PT_BR),
                    @Value(value = "&e[Disc] %player%: %message%", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatDiscordToServerPattern;
    @Values(
            {
                    @Value(value = "%author%, Sua mensagem não foi enviada ao servidor pois tinha mais que %lenght% caracteres", lang = LangTypes.PT_BR),
                    @Value(value = "%author%, Your message was not sent to the server as it was longer than %length% characters", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatMessageNotSendToServer;
    @Values(
            {
                    @Value(value = "%player% Entrou no servidor.", lang = LangTypes.PT_BR),
                    @Value(value = "%player% Joined the server.", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatDiscordSendLoginMessage;
    @Values(
            {
                    @Value(value = "%player% Saiu do servidor.", lang = LangTypes.PT_BR),
                    @Value(value = "%player% Left the server.", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatDiscordSendLeaveMessage;
    @Values(
            {
                    @Value(value = "Online: %online% players, Tempo Online: %online_time%, Atualizado: %time%", lang = LangTypes.PT_BR),
                    @Value(value = "Online: %online% players, Online time: %online_time%, Updated: %time%", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatDiscordTopic;
    @Values(
            {
                    @Value(value = "Servidor Iniciado", lang = LangTypes.PT_BR),
                    @Value(value = "Server started", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatServerStart;
    @Values(
            {
                    @Value(value = "Servidor fechado", lang = LangTypes.PT_BR),
                    @Value(value = "Server closed", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatServerClose;

    @Values(
            {
                    @Value(value = "Player: %player%, IP: %ip%, País: %country%, Estado: %state%, Cidade: %city%.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatSendPlayerLocale;

    @Values(
            {
                    @Value(value = "Trocou ip -> Player: %player%, IP: %ip%, País: %country%, Estado: %state%, Cidade: %city%.", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String discordchatSendPlayerLocalAtt;

    @Values(
            {
                    @Value(value = "%prefix%&eLista de cores disponíveis: %colors%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String colorSendList;

    @Values(
            {
                    @Value(value = "%prefix%&eCor setada com sucesso! : %color%", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String colorSet;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê não tem permissão para utilizar essa cor!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String colorNotSet;

    @Values(
            {
                    @Value(value = "%prefix%&cVocê acabou de remover a sua cor do chat!", lang = LangTypes.PT_BR),
                    @Value(value = "", lang = LangTypes.EN_US)
            }
    )
    public static String colorRemove;


    @Nullable
    @Override
    public String getGeneralCommandsUsage() {
        return generalCommandsUsage;
    }

    @Nullable
    @Override
    public String getGeneralCommandsUsageList() {
        return generalCommandsUsageList;
    }

    @Nullable
    @Override
    public String getGeneralCooldownMoreTime() {
        return generalCooldownMoreTime;
    }

    @Nullable
    @Override
    public String getGeneralNotPerm() {
        return generalNotPerm;
    }

    @Nullable
    @Override
    public String getGeneralNotPermAction() {
        return generalNotPermAction;
    }

    @Nullable
    @Override
    public String getGeneralOnlyConsoleCommand() {
        return generalOnlyConsoleCommand;
    }

    @Nullable
    @Override
    public String getGeneralOnlyPlayerCommand() {
        return generalOnlyPlayerCommand;
    }

    @Nullable
    @Override
    public String getGeneralPlayerNotExist() {
        return generalPlayerNotExist;
    }

    @Nullable
    @Override
    public String getGeneralPlayerNotOnline() {
        return generalPlayerNotOnline;
    }

    @Nullable
    @Override
    public String getGeneralServerPrefix() {
        return generalServerPrefix;
    }

    @Nullable
    @Override
    public String getTimeDay() {
        return timeDay;
    }

    @Nullable
    @Override
    public String getTimeDayShort() {
        return timeDayShort;
    }

    @Nullable
    @Override
    public String getTimeDays() {
        return timeDays;
    }

    @Nullable
    @Override
    public String getTimeHour() {
        return timeHour;
    }

    @Nullable
    @Override
    public String getTimeHourShort() {
        return timeHourShort;
    }

    @Nullable
    @Override
    public String getTimeHours() {
        return timeHours;
    }

    @Nullable
    @Override
    public String getTimeMinute() {
        return timeMinute;
    }

    @Nullable
    @Override
    public String getTimeMinuteShort() {
        return timeMinuteShort;
    }

    @Nullable
    @Override
    public String getTimeMinutes() {
        return timeMinutes;
    }

    @Nullable
    @Override
    public String getTimeSecond() {
        return timeSecond;
    }

    @Nullable
    @Override
    public String getTimeSecondShort() {
        return timeSecondShort;
    }

    @Nullable
    @Override
    public String getTimeSeconds() {
        return timeSeconds;
    }
}
