package utils

data class TimedValue<T>(
    val value: T, val duration: Double
)

//Make it inline and make generic type as reified for using it as Type at runtime
inline fun <reified T> measureTimedValueCustom(block: () -> T): TimedValue<T> {
    val startTime = System.currentTimeMillis()
    println("Code block start time: $startTime")
    val t: T = block()
    val endTime = System.currentTimeMillis()
    println("Code block end time: $endTime")
    val duration = (endTime - startTime)/1000.000000
    return TimedValue(t, duration)
}