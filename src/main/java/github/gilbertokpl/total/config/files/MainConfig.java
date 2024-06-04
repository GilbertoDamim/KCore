package github.gilbertokpl.total.config.files;

import github.gilbertokpl.core.external.config.annotations.*;
import github.gilbertokpl.core.external.config.def.DefaultConfig;
import github.gilbertokpl.core.external.config.types.LangTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ConfigPattern(
        name = "MainConfig"
)
public class MainConfig implements DefaultConfig {

    //general

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações gerais", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "General settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Selected Lang to run your plugin;\n" +
                            "available: pt_BR, en_US", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Selected Lang to run your plugin;\n" +
                            "available: pt_BR, en_US", lang = LangTypes.EN_US)
            }
    )
    public static String generalSelectedLang = "pt_BR";
    @Comments(
            {
                    @Comment(annotations = "Nome do seu servidor.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Server name", lang = LangTypes.EN_US)
            }
    )
    public static String generalServerName = "unamed";
    @Comments(
            {
                    @Comment(annotations = "Auto Update", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Auto Update", lang = LangTypes.EN_US)
            }
    )
    public static Boolean generalAutoUpdate = true;

    @Comments(
            {
                    @Comment(annotations = "Anti-VPN", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Anti-VPN", lang = LangTypes.EN_US)
            }
    )
    public static Boolean generalAntiVpn = true;
    @Comments(
            {
                    @Comment(annotations = "Utilize sistema custom de salvar itemstack, com nbt e type (apenas crucible com o plugin evernifecore)", lang = LangTypes.PT_BR),
                    @Comment(annotations = "itemstack", lang = LangTypes.EN_US)
            }
    )
    public static Boolean generalCustomItemStack = false;

    @Comments(
            {
                    @Comment(annotations = "Lista de IDs dos donos do servidor para quando for utilizado o comando reset ter a dupla verificação", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> generalResetList = Collections.singletonList("0");

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações do banco de dados.", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Database config.", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Selecione a sua conexão, Disponiveis H2 (Local) e Mysql (Nuvem).", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Select you connection, avaliable H2(Local) and Mysql(CloudOnline).", lang = LangTypes.EN_US)
            }
    )
    public static String databaseType = "H2";
    @Comments(
            {
                    @Comment(annotations = "Mude o final do database para não entrar em conflito com outros servidores e usar o mesmo login.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Rename the final of database to switch between servers and keep same login", lang = LangTypes.EN_US)
            }
    )
    public static String databaseManager = "S1";
    @Comments(
            {
                    @Comment(annotations = "Se selecionou MYSQL coloque essas informações para conexão.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If you selected MYSQL, enter this information for connection.", lang = LangTypes.EN_US)
            }
    )
    public static String databaseSqlIp = "localhost";
    public static String databaseSqlPort = "3306";
    public static String databaseSqlUsername = "!u0_essentialsk";
    public static String databaseSqlDatabase = "!s0_essentialsk";
    public static String databaseSqlPassword = "!essentialsk123";


    //stackMobs

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações do StackMobs", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O Stackamobs será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Boolean stackmobsActivated = false;
    @Comments(
            {
                    @Comment(annotations = "Lista de mobs que irão estocar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<Integer> stackmobsStackList = Collections.singletonList(54);
    @Comments(
            {
                    @Comment(annotations = "dar replace no nome do mob.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> stackmobsNameReplacer = Collections.singletonList("54:Zumbi");

    @Comments(
            {
                    @Comment(annotations = "Raio que ira estocar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Integer stackmobsRadius = 10;

    @Comments(
            {
                    @Comment(annotations = "Quantidade máxima.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Integer stackmobsMax = 200;
    @Comments(
            {
                    @Comment(annotations = "Lista de mobs que irão estocar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static String stackmobsNameTag = "&6&l%name% %quantity%X";
    @Comments(
            {
                    @Comment(annotations = "Lista de mobs que irão estocar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> stackmobsBlockedWorlds = Collections.singletonList("Spawn");


    //Commands

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações do VIP", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "VIP settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O VIP será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The VIP will be enabled.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean vipActivated = true;

    //playtime

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O Limitador será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The playtime will be enabled.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean limitActivated = false;

    //playtime

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O playtime será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The playtime will be enabled.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean playtimeActivated = true;

    //authCommand

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos logar / registrar", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "login and register command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be enabled.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean authActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Quantidade máxima de tentativas de logar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Maximum number of login attempts.", lang = LangTypes.EN_US)
            }
    )
    public static Integer authMaxAttempts = 3;
    @Comments(
            {
                    @Comment(annotations = "Quantidade máxima de registros por ip.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Maximum number of records per ip.", lang = LangTypes.EN_US)
            }
    )
    public static Integer authMaxRegister = 1;

    @Comments(
            {
                    @Comment(annotations = "Quantidade maxima de mensagens para o player logar ou registrar, entre cada é 10 segundos.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Maximum number of messages for the player to log in or register, between each is 10 seconds.", lang = LangTypes.EN_US)
            }
    )
    public static Integer authSendMessage = 3;

    //announceCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos announce", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "announce command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be enabled.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean announceActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Tempo em segundos para executar o comando novamente.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time in seconds to run the command again.", lang = LangTypes.EN_US)
            }
    )
    public static int announceCooldown = 300;

    //backCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos back", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Back command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean backActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Lista de mundos bloqueados de salvarem no back, use o nome do mundo exemplo: world.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List of worlds blocked from saving in the back, use the world name example: world.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> backDisabledWorlds = Collections.singletonList("essentialsk");

    //craftCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos craft", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Craft command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean craftActivated = true;

    //clearEntitiesCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comando clear items", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Kit command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean clearentitiesActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Lista de mundos para limpar o chão.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> clearentitiesWorlds = Collections.singletonList("world");
    @Comments(
            {
                    @Comment(annotations = "Lista de items para não excluir em material, para pegar o material > /material.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static List<String> clearentitiesItemsNotClear = Collections.singletonList("dirt");
    @Comments(
            {
                    @Comment(annotations = "Tempo em minutos para executar um limpar chão", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Integer clearentitiesTime = 10;

    //kitCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos kits", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Kit command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), será enviado um menu ao ‘player’.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), a menu will be sent to the ‘player’.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsMenuKit = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), será enviado o tempo em pequena escala, exemplo segundos para s.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), the time will be sent in small scale, ex. seconds for s.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsUseShortTime = false;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true) dropar items caso o invetário esteja cheio.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true) drop items if the inventory is full.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsDropItemsInCatch = false;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), será Equipado a armadura caso tenha no kit.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If it is on (true), the armor will be Equipped if you have it in the kit.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsEquipArmorInCatch = true;

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), será enviado ao player os kits disponiveis para pegar ao logar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "jkl", lang = LangTypes.EN_US)
            }
    )
    public static Boolean kitsSendAvailableKitsOnLogin = true;

    //homeCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos homes", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Home command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean homesActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Quantidade de homes maximas caso não tenha a permissão.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Maximum number of men if you do not have the permission.", lang = LangTypes.EN_US)
            }
    )
    public static int homesDefaultLimitHomes = 3;
    @Comments(
            {
                    @Comment(annotations = "Tempo para teleportar, tem permissão para burlar isso.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time to teleport, you are allowed to bypass.", lang = LangTypes.EN_US)
            }
    )
    public static int homesTimeToTeleport = 5;
    @Comments(
            {
                    @Comment(annotations = "Lista de mundos bloqueados de usar sethome, use o nome do mundo exemplo: world.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List of worlds blocked from using sethome, use world name example: world.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> homesBlockWorlds = Collections.singletonList("essentialsk");

    //warpCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos warps", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Warps command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean warpsActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Tempo para teleportar, tem permissão para burlar isso.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time to teleport, you are allowed to bypass.", lang = LangTypes.EN_US)
            }
    )
    public static int warpsTimeToTeleport = 5;

    //echestCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos echest", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Enderchest command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean echestActivated = true;

    //feedCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos feed", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Feed command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean feedActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Só vai executar se a sua fome não estiver cheia, em versões antias pode bugar no true.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "It will only execute if your hunger is not full, in old versions it can bug in true.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean feedNeedEatBelow = false;

    //flyCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos fly", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Fly command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean flyActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Lista de mundos bloqueados de usar o fly, use o nome do mundo exemplo: world.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List of worlds blocked from using fly, use world name example: world.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> flyDisabledWorlds = Collections.singletonList("TotalEssentials");

    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos gamemodes", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Gamemodes command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean gamemodeActivated = true;

    //hatCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos hat", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Hat command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean hatActivated = true;

    //healCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos heal", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Heal command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean healActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Só vai executar se a sua vida não estiver cheia, em versões antias pode bugar no true.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "It will only run if your health is not full, in old versions it can bug in true.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean healNeedHealBelow = false;

    //invseeCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos invsee", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Invsee command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean invseeActivated = true;

    //lightCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos light", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Light command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )

    public static Boolean lightActivated = true;

    //moneyCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos money", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Money command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean moneyActivated = false;
    @Comments(
            {
                    @Comment(annotations = "Dinheiro que o player ganhará ao entrar a primeira vez.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Money the player will earn upon entering the first time.", lang = LangTypes.EN_US)
            }
    )
    public static Integer moneyDefault = 1000;
    @Comments(
            {
                    @Comment(annotations = "mostre o dinheiro em formato extenso.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Boolean moneyExtended = true;

    //nickCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos nicks", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Nick command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean nicksActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true) players poderão usar o mesmo nick.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true) players will be able to use the same nick.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean nicksCanPlayerHaveSameNick = false;
    @Comments(
            {
                    @Comment(annotations = "Lista de nicks bloqueados.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List of blocked nicks.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> nicksBlockedNicks = Collections.singletonList("essentialsk");

    //onlineCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos online", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Online command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean onlineActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando online contará também os players de vanish.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), Online command will also count vanish players.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean onlineCountRemoveVanish = false;

    //spawnCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos spawn", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Spawn command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean spawnActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado o player será mandado para o spawn no login.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled the player will be sent to spawn on login.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean spawnSendToSpawnOnLogin = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado o player será mandado para o spawn quando ele morre.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If activated the player will be sent to spawn when he dies.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean spawnSendToSpawnOnDeath = true;
    @Comments(
            {
                    @Comment(annotations = "Tempo para teleportar, tem permissão para burlar isso.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time to teleport, you are allowed to bypass.", lang = LangTypes.EN_US)
            }
    )
    public static int spawnTimeToTeleport = 5;

    //speedCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos speed", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Speed ​​command settings", lang = LangTypes.EN_US)
            }
    )

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean speedActivated = true;

    //tpCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos tp", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "TP command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean tpActivated = true;

    //tpaCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos tpa", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "TPA command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean tpaActivated = true;
    @Comments(
            {
                    @Comment(annotations = "Tempo para aceitar o pedido.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time to accept the order.", lang = LangTypes.EN_US)
            }
    )
    public static int tpaTimeToAccept = 10;
    @Comments(
            {
                    @Comment(annotations = "Tempo para teleportar, tem permissão para burlar isso.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time to teleport, you are allowed to bypass.", lang = LangTypes.EN_US)
            }
    )
    public static int tpaTimeToTeleport = 5;

    //tphereCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos tphere", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "tphere command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean tphereActivated = true;

    //trashCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos trash", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Trash command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean trashActivated = true;

    //vanishCommand
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações dos comandos vanish", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Vanish command settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O comando será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The command will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean vanishActivated = true;

    //discord
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações discord", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Discord settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Token do Bot.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "BotToken", lang = LangTypes.EN_US)
            }
    )
    public static String discordbotToken = "123456";
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O discord conectará com o chat do discord.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If it is on (true), Discord will connect to the Discord chat.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean discordbotConnectDiscordChat = false;
    @Comments(
            {
                    @Comment(annotations = "Caso o connect esteja ativado terá que colocar o id do chat conectado aqui.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If connect is activated, you will have to put the id of the connected chat here.", lang = LangTypes.EN_US)
            }
    )
    public static String discordbotIdDiscordChat = "123456";

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O discord conectará com o registro chat do discord.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Boolean discordbotConnectRegisterChat = false;
    @Comments(
            {
                    @Comment(annotations = "Caso o connect esteja ativado terá que colocar o id do registro chat para armazenar os ips.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static String discordbotIdRegisterChat = "123456";
    @Comments(
            {
                    @Comment(annotations = "Comando que ira detectar para enviar ao Discord.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Command that will detect to send to Discord.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> discordbotCommandChat = Arrays.asList("/g", "/global");
    @Comments(
            {
                    @Comment(annotations = "Tamanho máximo da mensagem que o Discord conseguirá enviar ao server.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Maximum message size that Discord will be able to send to the server.", lang = LangTypes.EN_US)
            }
    )
    public static int discordbotMaxMessageLenght = 80;
    @Comments(
            {
                    @Comment(annotations = "Mandar mensagem ao discord (embed) que o ‘player’ logou.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Message discord (embed) that the player has logged in.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean discordbotSendLoginMessage = false;
    @Comments(
            {
                    @Comment(annotations = "Mandar mensagem ao discord (embed) que o ‘player‘ saiu. Mandar mensagem ao discord (embed) que o ‘player‘ saiu.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Message discord (embed) that the 'player' has left. Message discord (embed) that the 'player' has left.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean discordbotSendLeaveMessage = false;
    @Comments(
            {
                    @Comment(annotations = "Mudar o assunto do canal a cada 10 minutos, com a mensagem setada na sua lang.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Change the channel subject every 10 minutes, with the message set in your lang.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean discordbotSendTopicUpdate = false;

//systems

    //motd
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações da MOTD", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "MOTD Settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O Motd será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The MOTD will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean motdEnabled = true;
    @Comments(
            {
                    @Comment(annotations = "Adicionar lista para ver no motd use (/ n> 1.5.2) para pular linhas;\n" +
                            "Lista de motds aletórias, caso queira estático utilize só 1 linha;\n" +
                            "Variaveis -> %players_online%.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Add list to see in motd use (/n>1.5.2) to skip lines;\n" +
                            "List of random motds, if you want static use only 1 line;\n" +
                            "Variables -> %players_online%.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> motdListMotd = Arrays.asList(
            "&3Minecraft Server \n&3Está aberto com players %players_online% online!",
            "&9Minecraft Server \n&9Está aberto com players %players_online% online!",
            "&eMinecraft Server \n&eEstá aberto com players %players_online% online!",
            "&fMinecraft Server \n&fEstá aberto com players %players_online% online!"
    );

    @Comments(
            {
                    @Comment(annotations = "Adicionar lista para ver no motd com whitelist on, use (/ n> 1.5.2) para pular linhas;\n" +
                            "Lista de motds aletórias, caso queira estático utilize só 1 linha;\n" +
                            "Variaveis -> %players_online%.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Add list to see in motd use (/n>1.5.2) to skip lines;\n" +
                            "List of random motds, if you want static use only 1 line;\n" +
                            "Variables -> %players_online%.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> motdListMotdWhitelist = Arrays.asList(
            "&3Minecraft Server \n&4Está fechado",
            "&9Minecraft Server \n&cEstá fechado"
    );

    //auto messages
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações das mensagens automáticas", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Automatic message settings", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), as mensagens automáticas serão ativadas.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The auto-messages will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean announcementsEnabled = true;
    @Comments(
            {
                    @Comment(annotations = "Tempo entre as mensagens em minutos para enviar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Time between messages in minutes to send.", lang = LangTypes.EN_US)
            }
    )
    public static int announcementsTime = 5;
    @Comments(
            {
                    @Comment(annotations = "Lista de anuncios para ser enviado, variaveis -> %players_online%.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List of announcements to be sent, variables -> %players_online%.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> announcementsListAnnounce = Arrays.asList("&eSeu servidor está utilizando EssentiasK obrigado por utilizar.",
            "&9Obrigado por utilizar meu plugin!", "&3No servidor tem %players_online% players online!");

    //messages
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Configurações das mensagens que podem ser ativadas", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "Message settings that can be enabled", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), as mensagens do deathmessages serão ativadas.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The deathmessages will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean messagesDeathmessagesMessage = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), as mensagens do login será ativada.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The login-messages will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean messagesLoginMessage = true;
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), as mensagens do leave será ativada.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The leave-messages will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean messagesLeaveMessage = true;

    //shop
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O loja será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The shop will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean shopActivated = true;

    //shop
    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O /cor será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The color will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean colorActivated = true;

    //antiafk

    @Comments(
            {
                    @Comment(annotations = "Se estiver ativado (true), O AntiAfk será ativado.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The AntiAFK will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antiafkEnabled = true;

    @Comments(
            {
                    @Comment(annotations = "Qual warp será teleportado, se não existir ira para o spawn.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "If enabled (true), The AntiAFK will be actived.", lang = LangTypes.EN_US)
            }
    )
    public static String antiafkWarp = "afk";

    @Comments(
            {
                    @Comment(annotations = "Tempo de check do plugin", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Long antiafkTimeToCheck = 5L;

    @Comments(
            {
                    @Comment(annotations = "Tempo para kickar ou mandar para o spawn", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Long antiafkTimeToExecute = 15L;

    @Comments(
            {
                    @Comment(annotations = "Se ativado, vai mandar para o spawn avez de kickar", lang = LangTypes.PT_BR),
                    @Comment(annotations = "", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antiafkSendToSpawn = true;

    //antibugs
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Neste local, contém os recursos antibugs que podem ser ativados ou desativados.", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "This location contains the anti-bug features that can be turned on or off.", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o jogador é impedido de subir na cama;\n" +
                            "Permissão Bypass -> totalessentials.bypass.bed", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the player is prevented from getting on the bed;\n" +
                            "Permission Bypass -> totalessentials.bypass.bed", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockBed = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o jogador é impedido de subir nos veículos;\n" +
                            "Permissão Bypass -> totalessentials.bypass.vehicles", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the player is prevented from boarding vehicles;\n" +
                            "Permission Bypass -> totalessentials.bypass.vehicles", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockClimbingOnVehicles = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o jogador é impedido de usar NameTag em mobs;\n" +
                            "Permissão Bypass -> totalessentials.bypass.nametag", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the player is prevented from using NameTag on mobs;\n" +
                            "Permission Bypass -> totalessentials.bypass.nametag", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockNametag = false;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, os mobs são proibidos de pegar itens do chão.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, mobs are prohibited from picking up items from the ground.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockMobCatch = false;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, bloqueia o jogador de ir além da borda com Ender Pearls.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, blocks the player from going over the edge with Ender Pearls.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockGoingEdgeEnderpearl = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, bloqueia o jogador de ir para o teto do Nether;\n" +
                            "Permissão Bypass -> totalessentials.bypass.netherceiling", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, blocks the player from going to the Nether ceiling;\n" +
                            "Permission Bypass -> totalessentials.bypass.netherceiling", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockPlayerGoToNetherCeiling = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, os blocos de jogadores se teletransportam em portais;\n" +
                            "Permissão Bypass -> totalessentials.bypass.teleportportal", lang = LangTypes.PT_BR),
                    @Comment(annotations = "TEST" + "Permission Bypass ->", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockPlayerTeleportPortal = false;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, bloqueia todas as entidades criar portais.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, blocks all entities from creating portals.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean antibugsBlockCreatePortal = false;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, bloquear os player de utilizarem esses comandos;\n" +
                            "Permissão Bypass -> totalessentials.bypass.blockedcmd", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, block players from using these commands;\n" +
                            "Permission Bypass -> totalessentials.bypass.blockedcmd", lang = LangTypes.EN_US)
            }
    )
    public static List<String> antibugsBlockCmds = Arrays.asList("/pl", "/plugins");

    //containers
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Neste local, contém as opções do container, lista de valores em (indisponível por enquanto)", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "This location contains container options, list of values ​​in (unavailable for now)", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Cancelar o uso de shift nos contêineres da lista;\n" +
                            "Permissão Bypass -> totalessentials.bypass.shiftcontainer", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Override using shift in list containers;\n" +
                            "Permission Bypass -> totalessentials.bypass.shiftcontainer", lang = LangTypes.EN_US)
            }
    )
    public static Boolean containersBlockShiftEnable = false;
    @Comments(
            {
                    @Comment(annotations = "Lista para bloquear shift.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Lock-shift List.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> containersBlockShift = Collections.singletonList("ANVIL");
    @Comments(
            {
                    @Comment(annotations = "Cancelar abrir os contêineres da lista;\n" +
                            "Permissão Bypass -> totalessentials.bypass.opencontainer", lang = LangTypes.PT_BR),
                    @Comment(annotations = "Cancel open list containers;\n" +
                            "Permission Bypass -> totalessentials.bypass.opencontainer", lang = LangTypes.EN_US)
            }
    )
    public static Boolean containersBlockOpenEnable = false;
    @Comments(
            {
                    @Comment(annotations = "Lista para bloquear de abrir.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "List to lock from opening.", lang = LangTypes.EN_US)
            }
    )
    public static List<String> containersBlockOpen = Collections.singletonList("ANVIL");

    //addons
    @PrimaryComments(
            {
                    @PrimaryComment(primaryAnnotations = "Neste local, possui addons para minecraft.", lang = LangTypes.PT_BR),
                    @PrimaryComment(primaryAnnotations = "This place has addons for minecraft.", lang = LangTypes.EN_US)
            }
    )
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, a bigorna é infinita.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the anvil is infinite.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsInfinityAnvil = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o jogador é teletransportado para o spawn se o jogador cair no void.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the player is teleported to the spawn if the player falls into the void.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsBlockPlayerGoToVoid = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, os itens dropados não podem explodir.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, dropped items cannot explode.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsBlockExplodeItems = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o jogador não pode quebrar a plantação quando cair.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, the player cannot break the crop when it falls.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsBlockPlayerBreakPlantationFall = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, o fogo não pode se propagar.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, fire cannot propagate.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsBlockPropagationFire = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, pode ser colocado & no nome do ‘item’, tem permissão para isso;\n" +
                            "Permissão -> totalessentials.color.&0 (Exemplo).", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this resource, you can put & in the 'item' name, have permission to do so;\n" +
                            "Permission -> totalessentials.color.&0 (Ex).", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsColorInAnvil = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, pode ser colocado & no sinal, tem uma permissão para isso;\n" +
                            "Permissão -> totalessentials.color.&0 (Exemplo).", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this resource, you can put & in the sign, have a permission for that;\n" +
                            "Permission -> totalessentials.color.&0 (Ex).", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsColorInSign = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, pode ser colocado & no bate-papo, tem permissão para isso.Neste recurso, pode ser colocado & no bate-papo, tem permissão para isso.\n" +
                            "Permissão -> totalessentials.color.&0 (Exemplo).", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, can be placed & in chat, have permission to do so. In this feature, can be placed & in chat, have permission to do so.\n" +
                            "Permission -> totalessentials.color.&0 (Ex).", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsColorInChat = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, evita que o jogador perca XP na morte.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, it prevents the player from losing XP on death.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsPlayerPreventLoseXp = true;
    @Comments(
            {
                    @Comment(annotations = "Neste recurso, evita que começe a chover.", lang = LangTypes.PT_BR),
                    @Comment(annotations = "In this feature, it prevents it from starting to rain.", lang = LangTypes.EN_US)
            }
    )
    public static Boolean addonsDisableRain = false;

    @NotNull
    @Override
    public String getDatabaseSqlDatabase() {
        return databaseSqlDatabase;
    }

    @NotNull
    @Override
    public String getDatabaseSqlIp() {
        return databaseSqlIp;
    }

    @NotNull
    @Override
    public String getDatabaseSqlPassword() {
        return databaseSqlPassword;
    }

    @NotNull
    @Override
    public String getDatabaseSqlPort() {
        return databaseSqlPort;
    }

    @NotNull
    @Override
    public String getDatabaseSqlUsername() {
        return databaseSqlUsername;
    }

    @NotNull
    @Override
    public String getDatabaseType() {
        return databaseType;
    }

    @Override
    public boolean getGeneralAutoUpdate() {
        return generalAutoUpdate;
    }

    @NotNull
    @Override
    public String getGeneralSelectedLang() {
        return generalSelectedLang;
    }

    @NotNull
    @Override
    public String getGeneralServerName() {
        return generalServerName;
    }
}
