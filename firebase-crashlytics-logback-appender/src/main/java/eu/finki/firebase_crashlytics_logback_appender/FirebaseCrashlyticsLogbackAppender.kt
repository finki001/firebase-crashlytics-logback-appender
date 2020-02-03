package eu.finki.firebase_crashlytics_logback_appender

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import com.google.firebase.crashlytics.FirebaseCrashlytics

class FirebaseCrashlyticsLogbackAppender : AppenderBase<ILoggingEvent>() {

    lateinit var encoder: PatternLayoutEncoder

    override fun append(eventObject: ILoggingEvent) {
        if (!isStarted) {
            return
        }
        FirebaseCrashlytics.getInstance().log(encoder.layout.doLayout(eventObject))
    }

    init {
        Thread.getDefaultUncaughtExceptionHandler()?.let {
            Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
                try {
                    Thread.sleep(100)
                } catch (ignored: InterruptedException) {
                }
                it.uncaughtException(thread, throwable)
            }
        }

    }
}