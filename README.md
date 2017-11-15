# AK47 - Android Kotlin Utility Extensions

Small Kotlin extension functions for Android that make your app development with Kotlin more fun!

## Logging utilities

### Log debugs of any type with alternative syntax

```kotlin
"Ada Lovelace".log("OMGLOG")
0.log("INTLOG!")
listOf("Ada", "Boole", "Dijkstra").log("LISTLOG!")
arrayOf("Ada", "Boole", "Dijkstra").log("OMGARRAYSWORKTOO!")
```

### Easier chaining

```kotlin
listOf("Ada", "Von Neumann", "Turing")
    .log("Original list")
    .filter { it.contains("a") }
    .log("Filtered list")
```

### Supports different logging levels

```kotlin
"Ada Lovelace".logDebug("OMGLOG")
0.logInfo("INTLOG!")
listOf("Ada", "Boole", "Dijkstra").logWarning("LISTLOG!")
arrayOf("Ada", "Boole", "Dijkstra").logError("OMGARRAYSWORKTOO!")
```

### Direct exception logging with ERROR level

```kotlin
try {
    throw Exception("Something nasty happened")
} catch (e: Exception) {
    e.log("Your exception tag")
}
```

### Default tag value "LogUtils" for all log function extensions

```kotlin
"Ada Lovelace".log()
```

### Log and ignore exceptions

```kotlin
ignoreErrors {
    throw IOException("File not found!")
}
```

### Integrated tag for all classes

```kotlin
"Ada Lovelace".log(tag())
```

### Easier execution time logging

```kotlin
logExecutionTime {
    (0..100_000).forEach { it.log("A number") }
}
```

### Log call stack trace to this point

```kotlin
logCallTrace()
```

## Context utilities

### Check permission extension with block support and no need to check for Android version

```kotlin
activity.checkAllPermissions(setOf(CAMERA, WRITE_EXTERNAL_STORAGE)) {
    // Execute if all permissions granted
} ?: "Permissions not granted!".logWarning()
```

### No need to check for Android version when requesting permissions

```kotlin
activity.requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE), 123456)
```

## File utilities

### Get download directory in a single line

```kotlin
val downloadDir = getDownloadDirectory()
```

### Simple APK installation

```kotlin
File(getDownloadDirectory(), "BestApp.apk").installAPK(activity)
File(getDownloadDirectory(), "SystemApp.apk").installSystemAPK(activity)
File(getDownloadDirectory(), "StealthApp.apk").installAPKNoPrompt(activity)
```

### File's MD5 hash

```kotlin
File("IsThisFileLegit.apk").md5()
```

### GREP!

```kotlin
File("A File With Names.txt").grep("^J".toRegex())
```

## ifTrue/ifFalse

```kotlin
File("uselss file").delete().ifFalse { "Why I can't delete this useless file!".log() }
```

## Command shell execution

### Execute command as normal user or as superuser and get its output

```kotlin
"ls -l /data".runCommand()
"ls -l /system".runCommandAsSu()
```

## GUI and resources utilities

### Simpler toasts

```kotlin
toast("Hello there!")
longToast(R.string.translatedString)
```

### Get a View by name

```kotlin
"tvName".findViewByName(activity)
```

### Get a String resource by name

```kotlin
"@string/that_useful_string".getStringResourceByName(activity)
```

## System utilities

### Reboot or shutdown the device with one line

```kotlin
reboot()
shutdown()
```

## Binary utilities

### Simple byte array to hexadecimal conversion

```kotlin
listOf(1.toByte(), 2.toByte()).toTypedArray().toHex().log("My sweet little array")
```

### You can even choose big-endian or little-endian

```kotlin
listOf(1.toByte(), 2.toByte()).toTypedArray().toHexLittleEndian().log("My sweet little-endian array")
```