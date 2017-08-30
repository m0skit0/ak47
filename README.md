# AK47 - Android Kotlin Utility Extensions

Small Kotlin extension functions for Android that make your app development with Kotlin more fun!

## Logging utilities

### Alternative syntax for logging

var name = "Ada Lovelace"
"Hello, this is $name".log("OMGLOG")

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

## GUI and resources utilities

### Get a View by name

```kotlin
"tvName".findViewByName(activity)
```

### Get a String resource by name

```kotlin
"@string/that_useful_string".getStringResourceByName(activity)
```

## File utilities

### Get download directory in a single line

val downloadDir = getDownloadDirectory()

### Simple APK programmatically installation

File(getDownloadDirectory(), "BestApp.apk").installAPK(activity)

## Binary utilities

### Simple byte array to hexadecimal conversion

listOf(1.toByte(), 2.toByte()).toTypedArray().toHex().log("My sweet little array")

