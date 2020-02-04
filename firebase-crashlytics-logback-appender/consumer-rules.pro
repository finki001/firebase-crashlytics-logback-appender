-keep class eu.finki.logback.appender.FirebaseCrashlyticsLogbackAppender
-keepclassmembers class eu.finki.logback.appender.FirebaseCrashlyticsLogbackAppender {
	public *** *Encoder(...);
}