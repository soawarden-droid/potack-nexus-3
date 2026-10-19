package com.xa.potacknexus3

data class CommandResult(val recognized: Boolean, val title: String, val detail: String)

object CommandParser {
    private val deleteAliases = setOf("fck", "awghan", "white", "black", "potack", "uzbik", "clean")
    fun parse(raw: String): CommandResult {
        val text = raw.trim()
        if (text.isEmpty()) return CommandResult(false, "Ready", "Type a command to preview.")
        val parts = text.split(Regex("\\s+"), limit = 2)
        val word = parts[0].lowercase()
        val arg = parts.getOrElse(1) { "" }.trim()
        if (word in deleteAliases) {
            val n = arg.toIntOrNull()
            return if (n != null && n in 1..1000)
                CommandResult(true, "Cleanup preview", "Alias: $word • Requested: $n own messages. No deletion occurs in this alpha.")
            else CommandResult(true, "Count required", "Example: $word 50 (allowed 1–1000). Nothing deleted.")
        }
        if (!word.startsWith(".")) return CommandResult(false, "Not a command", "Use .commands, or a configured dotless cleanup alias.")
        return when (word) {
            ".help" -> CommandResult(true, "Commands", ".bold, .italic, .info, .search, .ai; cleanup: fck, awghan, white, black, potack, uzbik, clean + number")
            ".bold", ".italic" -> CommandResult(true, "Formatting preview", "${word.drop(1)}: ${arg.ifEmpty { "provide on, off, or text" }}. Telegram integration pending.")
            ".info" -> CommandResult(true, "Info", "Telegram account inspection is not yet connected.")
            ".search" -> CommandResult(true, "Search", "Provider integration pending. Query: $arg")
            ".ai" -> CommandResult(true, "AI", "AI provider integration pending. No text was transmitted.")
            else -> CommandResult(false, "Unknown command", "Type .help to see supported previews.")
        }
    }
}
