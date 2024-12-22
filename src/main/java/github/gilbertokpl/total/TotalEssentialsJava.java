package github.gilbertokpl.total;

import github.gilbertokpl.core.external.CorePlugin;
import github.gilbertokpl.total.cache.internal.InternalLoader;
import github.gilbertokpl.total.cache.local.PlayerData;
import github.gilbertokpl.total.cache.loop.ClearEntitiesLoop;
import github.gilbertokpl.total.cache.loop.PluginLoop;
import github.gilbertokpl.total.cache.sql.*;
import github.gilbertokpl.total.config.files.LangConfig;
import github.gilbertokpl.total.config.files.MainConfig;
import github.gilbertokpl.total.discord.Discord;
import github.gilbertokpl.total.util.*;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.*;

import static org.jetbrains.exposed.sql.transactions.ThreadLocalTransactionManagerKt.transaction;

public class TotalEssentialsJava extends JavaPlugin {

    public static TotalEssentialsJava instance;
    public static CorePlugin basePlugin;
    public static Permission permission;
    public static boolean lowVersion = false;

    public static TotalEssentialsJava getInstance() {
        return instance;
    }

    public static CorePlugin getBasePlugin() {
        return basePlugin;
    }

    public static Permission getPermission() {
        return permission;
    }

    public static boolean isLowVersion() {
        return lowVersion;
    }

    public static Boolean update = false;
    public static Boolean libModify = false;

    public static String jarPath = null;

    public static Boolean publicVer = true;

    @Override
    public void onLoad() {


        String version;

        //update

        try {
            if (publicVer) {
                version = getLatestVersion("GilbertoKPL", "TotalEssentials");
                if (!Objects.equals(version, this.getDescription().getVersion())) {
                    System.out.println("Existe uma nova versão disponivel = " + version + " baixando...");
                    jarPath = "plugins/TotalEssentials-" + version + ".jar";
                    boolean archive = downloadArchive("https://github.com/GilbertoKPL/TotalEssentials/releases/download/" + version + "/TotalEssentials-" + version + ".jar", jarPath);
                    if (archive) {
                        new File("plugins/TotalEssentials-" + this.getDescription().getVersion() + ".jar").deleteOnExit();
                        update = true;
                    }
                }
            }
            else {
                version = this.getDescription().getVersion();
            }

        } catch (IOException ignored) {
            version = this.getDescription().getVersion();
        }

        //lib Checker

        String classPath;

        String depend = "https://github.com/GilbertoKPL/TotalEssentials/releases/download/"+version+"/TotalEssentials-lib-"+version+".jar";
        String[] split = depend.split("/");
        String name = split[split.length - 1];
        String pathLib = "plugins/TotalEssentials/lib/";
        String newPath = pathLib + name;

        File file = new File(newPath);


        classPath = newPath.replace("plugins/", "");

        if (!file.exists()) {
            Bukkit.getServer().getConsoleSender().sendMessage("Baixando dependencia = %dependency%.".replace("%dependency%", name));
            downloadArchive(depend, newPath);
            libModify = true;
        }

        try {
            if (update || !Objects.equals(getManifest(), classPath)) {
                modifyManifest(classPath);
                update = true;
            }
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Você está utilizando Windowns Algumas funcionalidades do plugin não irão funcionar!");
        }

        if (update || libModify) {
            update = true;
            Bukkit.getServer().getConsoleSender().sendMessage("Reninciando para aplicar modificações");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
            return;
        }


        instance = this;
        basePlugin = new CorePlugin(this);

        MaterialUtil.INSTANCE.startMaterials();

        basePlugin.startConfig("github.gilbertokpl.total.config.files");

        if (MainConfig.moneyActivated) {
            Bukkit.getServicesManager().register(Economy.class, new EconomyHolder(), instance, ServicePriority.Highest);
        }
    }

    @Override
    public void onEnable() {

        if (update) return;

        basePlugin.start(
                "github.gilbertokpl.total.commands",
                "github.gilbertokpl.total.listeners",
                "github.gilbertokpl.total.cache.local",
                java.util.Arrays.asList(
                        KitsDataSQL.INSTANCE,
                        PlayerDataSQL.INSTANCE,
                        SpawnDataSQL.INSTANCE,
                        WarpsDataSQL.INSTANCE,
                        LoginDataSQL.INSTANCE,
                        VipDataSQL.INSTANCE,
                        VipKeysSQL.INSTANCE,
                        ShopDataSQL.INSTANCE
                )
        );

        try {
            permission = Bukkit.getServer().getServicesManager().getRegistration(Permission.class).getProvider();
        }
        catch (NoClassDefFoundError e) {
            instance.getLogger().severe("Falta instalar VAULT, o addon de VIP não ira funcionar direito!");
        }

        InternalLoader.INSTANCE.start(
                MainConfig.announcementsListAnnounce,
                LangConfig.deathmessagesCauseReplacer,
                LangConfig.deathmessagesEntityReplacer
        );

        MainUtil.INSTANCE.startInventories();

        if (Bukkit.getBukkitVersion().contains("1.5.2") || Bukkit.getVersion().contains("1.5.2")) {
            lowVersion = true;
        }

        JDALogger.setFallbackLoggerEnabled(false);

        Discord.INSTANCE.startBot();

        if (MainConfig.discordbotConnectDiscordChat) {
            Discord.INSTANCE.sendDiscordMessage(LangConfig.discordchatServerStart, true);
        }

        ClearEntitiesLoop.INSTANCE.start();

        PluginLoop.INSTANCE.start();

        instance.getServer().getLogger().setFilter(new Filter());

    }

    @Override
    public void onDisable() {

        if (update) return;

        for (Player p : basePlugin.getReflection().getPlayers()) {
            if (MainConfig.playtimeActivated) {
                long currentTimeMillis = System.currentTimeMillis();

                Long playTimeCacheValue = PlayerData.INSTANCE.getPlayTimeCache().get(p);
                long time = (playTimeCacheValue != null) ? playTimeCacheValue : 0L;

                Long playtimeLocalValue = PlayerData.INSTANCE.getPlaytimeLocal().get(p);
                long timePlayed = (playtimeLocalValue != null) ? playtimeLocalValue : 0L;

                long newTime = time + (currentTimeMillis - timePlayed);

                if (time > 94608000000L) {
                    newTime = time;
                }

                if (newTime > 94608000000L) {
                    newTime = 518400000L;
                }

                PlayerData.INSTANCE.getPlayTimeCache().set(p, newTime);
                PlayerData.INSTANCE.getPlaytimeLocal().set(p, 0L);
            }
        }

        MainUtil.INSTANCE.consoleMessage(ColorUtil.YELLOW.getColor() + LangConfig.generalSaveDataMessage + ColorUtil.RESET.getColor());
        basePlugin.stop();
        MainUtil.INSTANCE.consoleMessage(ColorUtil.YELLOW.getColor() + LangConfig.generalSaveDataSuccess + ColorUtil.RESET.getColor());

        TaskUtil.INSTANCE.disable();

        if (MainConfig.discordbotConnectDiscordChat) {
            Discord.INSTANCE.sendDiscordMessage(LangConfig.discordchatServerClose, true);
        }
    }

    public static boolean downloadArchive(String urlDownload, String path) {
        try {
            URL url = new URL(urlDownload);

            URLConnection connection = url.openConnection();

            try (InputStream inputStream = connection.getInputStream()) {

                File localArchive = new File(path);

                localArchive.getParentFile().mkdirs();

                try (FileOutputStream outputStream = new FileOutputStream(localArchive)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                }
            }

            return true;
        } catch (IOException e) {
            e.fillInStackTrace();
            return false;
        }

    }

    public static String getManifest() throws IOException {
        String originalJarPath = JarPath();
        JarFile jar = new JarFile(originalJarPath);
        Manifest originalManifest = jar.getManifest();

        return originalManifest.getMainAttributes().getValue("Class-Path");
    }

    public static void modifyManifest(String ClassPath) throws IOException {
        String jarFilePath = JarPath();
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Manifest manifest = jarFile.getManifest();

            if (manifest != null) {
                Attributes mainAttributes = manifest.getMainAttributes();
                mainAttributes.remove("Class-Path");
                mainAttributes.putValue("Class-Path", ClassPath);
                Manifest newManifest = new Manifest();
                newManifest.getMainAttributes().putAll(mainAttributes);
                createJarWithNewManifest(jarFilePath, newManifest);
            } else {
                throw new IOException();
            }
        }
    }

    private static void createJarWithNewManifest(String jarFilePath, Manifest newManifest) throws IOException {
        String tempJarFilePath = Files.createTempFile("teste", "").toString();

        try (JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(tempJarFilePath), newManifest)) {
            try (JarFile jarFile = new JarFile(jarFilePath)) {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.getName().equals(JarFile.MANIFEST_NAME)) {
                        try (InputStream entryStream = jarFile.getInputStream(entry)) {
                            jarOutputStream.putNextEntry(new JarEntry(entry.getName()));

                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = entryStream.read(buffer)) != -1) {
                                jarOutputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                }
            }
        }
        Files.move(Paths.get(tempJarFilePath), Paths.get(jarFilePath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static String JarPath() {

        if (jarPath != null) {
            File file = new File(jarPath);
            try {
                return file.getCanonicalPath();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        Class<?> currentClass = TotalEssentialsJava.class;
        CodeSource codeSource = currentClass.getProtectionDomain().getCodeSource();

        if (codeSource != null) {
            URL location = codeSource.getLocation();
            String path = location.getPath();
            try {
                path = new File(path).getCanonicalPath();
            } catch (Exception e) {
                e.fillInStackTrace();
            }

            return path;
        } else {
            return "";
        }
    }

    public static String getLatestVersion(String owner, String repo) throws IOException {
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest";
        URL url = new URL(apiUrl);

        try (InputStream inputStream = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder jsonContent = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            int startIndex = jsonContent.indexOf("\"tag_name\":\"") + 12;
            int endIndex = jsonContent.indexOf("\"", startIndex);
            return jsonContent.substring(startIndex, endIndex);
        }
    }

}
