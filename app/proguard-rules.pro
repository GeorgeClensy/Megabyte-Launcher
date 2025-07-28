# Keep your launcher activity
-keep class com.geecee.megabytelauncher.MainActivity { *; }
-keep class com.geecee.megabytelauncher.** { *; }

# Remove all logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# Allow R8 to remove unused resources
-dontwarn androidx.**
-dontwarn com.google.**
-dontwarn kotlin.**

# Optimize aggressively
-optimizations !code/simplification/arithmetic
