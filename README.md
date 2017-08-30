# AK47 - Android Kotlin Utility Extensions

Small Kotlin extension functions for Android that make your app development with Kotlin more fun!

## Logging utilities

### Alternative syntax for logging

```kotlin
var name = "Ada Lovelace"
"Hello, this is $name".log("OMGLOG")
```

### Easier chaining

```kotlin
listOf("Ada", "Von Neumann", "Turing")
    .log("Original list")
    .filter { it.contains("a") }
    .log("Filtered list")
```

### Direct exception logging

```kotlin
try {
    throw Exception("Something nasty happened")
} catch (e: Exception) {
    e.log("Your exception tag")
}
```

### Log and ignore exceptions

```kotlin
ignoreErrors {
    throw IOException("File not found!")
}
```

### Easier execution time logging

```kotlin
logExecutionTime ("This is slow") {
    (0..100000).forEach { it.log("A number") }
}
```
```

## File utilities

### Get download directory in a single line

```kotlin
val downloadDir = getDownloadDirectory()
```

### Simple APK programmatically installation

```kotlin
File(getDownloadDirectory(), "BestApp.apk").installAPK(activity)
File(getDownloadDirectory(), "SystemApp.apk").installSystemAPK(activity)
File(getDownloadDirectory(), "StealthApp.apk").installAPKNoPrompt(activity)
```

### File's MD5 hash

```kotlin
File("IsThisFileLegit.apk").md5()
```

## Command shell execution

### Execute command as normal user or as superuser and get its output

```kotlin
"ls -l /data".runCommand()
"ls -l /system".runCommandAsSu()
```

## GUI and resources utilities

### Get a View by name

```kotlin
"tvName".findViewByName(activity)
```

### Get a String resource by name

```kotlin
"@string/that_useful_string".getStringResourceByName(activity)

## Binary utilities

### Simple byte array to hexadecimal conversion

```kotlin
listOf(1.toByte(), 2.toByte()).toTypedArray().toHex().log("My sweet little array")
```

### You can even choose big-endian or little-endian

```kotlin
listOf(1.toByte(), 2.toByte()).toTypedArray().toHexLittleEndian().log("My sweet little-endian array")
```