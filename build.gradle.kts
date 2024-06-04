import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.github.goooler.shadow") version "8.1.7"
}

val base = "github.gilbertokpl.library"

version = "1.1.5"


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://m2.dv8tion.net/releases")
    maven("https://maven.elmakers.com/repository/")
    maven("https://jitpack.io")
}

dependencies {

    //vault
    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        exclude("org.bukkit", "bukkit")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    compileOnly(fileTree(mapOf("dir" to "$buildDir\\..\\localjar", "include" to listOf("*.jar"))))

    //spigot
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT") {
        exclude("commons-lang", "commons-lang")
        exclude("commons-io", "commons-io")
        exclude("org.yaml", "snakeyaml")
        exclude("com.google.code.gson", "gson")
        exclude("org.slf4j", "jcl-over-slf4j")
    }


    //exposed
    implementation("org.jetbrains.exposed:exposed-core:0.50.1") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    implementation("org.jetbrains.exposed:exposed-dao:0.50.1") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    implementation("org.jetbrains.exposed:exposed-jdbc:0.50.1") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }


    //H2 database
    implementation("com.github.h2database:h2database:version-2.2.224") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    //Mysql with MariaDB driver database
    implementation("org.mariadb.jdbc:mariadb-java-client:3.4.0") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }
    //implementation to mysql - MariaDB
    implementation("com.zaxxer:HikariCP:4.0.3") {
            exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    //remove all connections of slf4
    implementation("org.slf4j:slf4j-nop:2.0.13")

    //simple yaml to help in yaml
    implementation("me.carleslc.Simple-YAML:Simple-Yaml:1.7.3") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    //host info
    implementation("com.github.oshi:oshi-core:6.6.1") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    implementation("net.dv8tion:JDA:5.0.0-beta.24") {
        exclude("club.minnced","opus-java")
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

    implementation("org.json:json:20240205") {
        exclude("org.slf4j", "slf4j-api")
        exclude("org.slf4j", "jcl-over-slf4j")
    }

}

tasks.shadowJar {
    archiveFileName.set(rootProject.name + "-" + project.version.toString() + ".jar")
    destinationDirectory.set(File("$projectDir/jar/plugins"))

    manifest {
        attributes(
            "Plugin-Version" to project.version.toString(),
            "Plugin-Creator" to "Gilberto",
            "Plugin-Name" to "TotalEssentials",
            "Plugin-Github" to "https://github.com/GilbertoKPL/TotalEssentials",
            "Class-Path" to "TotalEssentials/lib/TotalEssentials-lib-$version.jar"
        )
    }


    //relocate all libs
    relocate("org.apache.commons.lang3", "$base.lang3")
    relocate("oshi", "$base.oshi")
    relocate("gnu.trove", "$base.trove")
    relocate("net.dv8tion", "$base.dv8tion")
    relocate("com.neovisionaries", "$base.neovisionaries")
    relocate("org.apache.commons.io", "$base.io")
    relocate("org.yaml", "$base.yaml")
    relocate("com.google.gson", "$base.gson")
    relocate("org.simpleyaml", "$base.yaml")
    relocate("com.zaxxer.hikari", "$base.hikari")
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}