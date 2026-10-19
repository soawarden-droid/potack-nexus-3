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
        if (word in deleteAliases || word == ".clean" || word == ".delete") {
            val n = if (arg.isEmpty()) 1 else arg.toIntOrNull()
            return if (n != null && n in 1..1000)
                CommandResult(true, "Cleanup preview", "Alias: $word • Requested: $n own messages. No deletion occurs in this alpha.")
            else CommandResult(true, "Invalid count", "Use $word or $word 50 (allowed 1–1000). Nothing deleted.")
        }
        if (!word.startsWith(".")) return CommandResult(false, "Not a command", "Use .commands, or a configured dotless cleanup alias.")
        return when (word) {
            ".help", ".commands" -> CommandResult(true, "Commands", "Dotless delete: fck, awghan, white, black, potack, uzbik, clean (defaults to 1). Formatting: .bold .italic .underline .strike .mono .spoiler .code .quote .link .format. Telegram: .info .me .chatinfo .msginfo .id .ping .status .uptime .edit .copy .forward .pin .unpin .schedule .draft .searchchat .findmedia .saved .export .profile .accounts .login .logout. Web: .search .wiki .define .weather .urlinfo. AI: .ai .ask .summarize .rewrite .grammar .translate .explain .codehelp .brainstorm .prompt. Local: .time .date .uuid .hash .count .calc .encode .decode .json .notes .remind. Most commands await integration.")
            ".bold", ".italic" -> CommandResult(true, "Formatting preview", "${word.drop(1)}: ${arg.ifEmpty { "provide on, off, or text" }}. Telegram integration pending.")
            ".info" -> CommandResult(true, "Info", "Telegram account inspection is not yet connected.")
            ".search" -> CommandResult(true, "Search", "Provider integration pending. Query: $arg")
            ".ai" -> CommandResult(true, "AI", "AI provider integration pending. No text was transmitted.")
            ".time" -> CommandResult(true, "Local time", java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date()))
            ".date" -> CommandResult(true, "Local date", java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date()))
            ".uuid" -> CommandResult(true, "UUID", java.util.UUID.randomUUID().toString())
            ".count" -> CommandResult(true, "Characters", arg.length.toString())
            ".hash" -> CommandResult(true, "SHA-256", java.security.MessageDigest.getInstance("SHA-256").digest(arg.toByteArray()).joinToString("") { "%02x".format(it.toInt() and 255) })
            ".encode" -> CommandResult(true, "URL encoded", java.net.URLEncoder.encode(arg, "UTF-8"))
            ".decode" -> CommandResult(true, "URL decoded", try { java.net.URLDecoder.decode(arg, "UTF-8") } catch (e: Exception) { "Invalid encoded text" })
            else -> if (word in setOf(".underline", ".strike", ".mono", ".spoiler", ".code", ".quote", ".link", ".format", ".me", ".chatinfo", ".msginfo", ".id", ".ping", ".status", ".uptime", ".edit", ".copy", ".forward", ".pin", ".unpin", ".schedule", ".draft", ".searchchat", ".findmedia", ".saved", ".export", ".profile", ".accounts", ".login", ".logout", ".wiki", ".define", ".weather", ".urlinfo", ".ask", ".summarize", ".rewrite", ".grammar", ".translate", ".explain", ".codehelp", ".brainstorm", ".prompt", ".calc", ".json", ".notes", ".remind")) CommandResult(true, "Not yet implemented", "$word is planned, but no external action was performed.") else CommandResult(false, "Unknown command", "Type .help")
        }
    }
}
