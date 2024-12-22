import toni.blahaj.*
import toni.blahaj.api.*

val templateSettings = object : BlahajSettings {
	// -------------------- Dependencies ---------------------- //
	override val depsHandler: BlahajDependencyHandler get() = object : BlahajDependencyHandler {
		override fun addGlobal(mod : ModData, deps: DependencyHandler) {
			deps.modImplementation("maven.modrinth:necronomicon:${project.property("deps.necronomicon_version")}")

			// Spell Engine Dependencies
			deps.modImplementation("maven.modrinth:spell-power:${project.property("deps.spellpower_version")}-fabric")
			deps.modImplementation("maven.modrinth:spell-engine:${project.property("deps.spellengine_version")}-fabric")
			deps.modImplementation("maven.modrinth:runes:${project.property("deps.runes_version")}-fabric")
			deps.modImplementation("maven.modrinth:cloth-config:${project.property("deps.clothconfig_version")}+fabric")
			deps.modImplementation("maven.modrinth:playeranimator:${project.property("deps.playeranimator_version")}-fabric")
			deps.modCompileOnly("maven.modrinth:trinkets:${project.property("deps.trinkets_version")}-fabric")
			deps.modImplementation("maven.modrinth:azurelib-armor:${project.property("deps.azurelibarmor_version")}-fabric")
			deps.implementation("com.github.ZsoltMolnarrr:TinyConfig:${project.property("deps.tiny_config_version")}")

			// Optional/Compat mod dependencies
			deps.modImplementation("maven.modrinth:better-combat:${project.property("deps.bettercombat_version")}-fabric")
			deps.modImplementation("maven.modrinth:betterend:${project.property("deps.betterend_version")}-fabric")
			deps.modImplementation("maven.modrinth:betternether:${project.property("deps.betternether_version")}-fabric")
			deps.modRuntimeOnly("maven.modrinth:bclib:${project.property("deps.bclib_version")}-fabric")
			deps.modImplementation("maven.modrinth:rogues-and-warriors:${project.property("deps.warriors_version")}-fabric")
		}

		override fun addFabric(mod : ModData, deps: DependencyHandler) {

		}

		override fun addForge(mod : ModData, deps: DependencyHandler) {

		}

		override fun addNeo(mod : ModData, deps: DependencyHandler) {

		}
	}

	// ---------- Curseforge/Modrinth Configuration ----------- //
	// For configuring the dependecies that will show up on your mod page.
	override val publishHandler: BlahajPublishDependencyHandler get() = object : BlahajPublishDependencyHandler {
		override fun addShared(mod : ModData, deps: DependencyContainer) {
			if (mod.isFabric) {
				deps.requires("fabric-api")
			}
		}

		override fun addCurseForge(mod : ModData, deps: DependencyContainer) {

		}

		override fun addModrinth(mod : ModData, deps: DependencyContainer) {

		}
	}
}

plugins {
	`maven-publish`
	application
	id("toni.blahaj") version "1.0.9"
	kotlin("jvm")
	kotlin("plugin.serialization")
	id("dev.kikugie.j52j") version "1.0"
	id("dev.architectury.loom")
	id("me.modmuss50.mod-publish-plugin")
	id("systems.manifold.manifold-gradle-plugin")
}

blahaj {
	sc = stonecutter
	settings = templateSettings
	init()
}

// Dependencies
repositories {
	maven("https://www.cursemaven.com")
	maven("https://api.modrinth.com/maven")
	maven("https://thedarkcolour.github.io/KotlinForForge/")
	maven("https://maven.kikugie.dev/releases")
	maven("https://maven.txni.dev/releases")
	maven("https://jitpack.io")
	maven("https://maven.neoforged.net/releases/")
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
	maven("https://maven.parchmentmc.org")
	maven("https://maven.su5ed.dev/releases")
	maven("https://maven.su5ed.dev/releases")
	maven("https://maven.fabricmc.net")
	maven("https://maven.shedaniel.me/")
}
