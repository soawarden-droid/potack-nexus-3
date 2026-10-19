# POTACK NEXUS 3.0

**Created by xa**

Android starter project (Kotlin). Commands use a dot prefix (`.bold`, `.italic`, `.info`, `.ai`) and seven opt-in dotless cleanup aliases (`fck`, `awghan`, `white`, `black`, `potack`, `uzbik`, `clean`).

**Current status:** offline command preview and safe parser only. Telegram login, real deletion, and AI are **not yet implemented**. No action is performed on a Telegram account.

## Build

Open GitHub **Actions → Android APK → Run workflow**, or push a commit. Download the `potack-nexus-3-debug-apk` artifact after the build succeeds. Enable GitHub Actions in repository settings if necessary.

## Security

Dotless cleanup commands require an explicit numeric count and only generate a preview. A future real cleanup implementation must verify that messages belong to the logged-in user, show a dry run, and require confirmation before deleting.
