package multithreading

import java.util.LinkedList
import kotlin.concurrent.thread

//using thread function from kotlin, does not work properly in everycase, sometimes not producing all items even before consumer start consumig

//produce all items at once, and then consume
class ProducerConsumer2 {

    /***
     *   Check [ProducerConsumerMultipleItemsAtOnce] class which implements this
     */
    companion object {

        private var buffer = LinkedList<String>()
        var capacity = 5
        var isProducing: Boolean = false

        var lock = Object()

        private var producer = thread(start = false) {

            var data = "Test"
            var index = 0
            while (true) {
                synchronized(lock) {
                    while(buffer.size == capacity) lock.wait()

                    var item = "$data $index"
                    buffer.add(item)
                    index++
                    println("produced data: $item") //produce
                    lock.notify()

                    Thread.sleep(1000) //wait

                }
            }
        }

        private var consumer = thread(start = false) {
            while (true) {
                synchronized(lock) {
                    while(buffer.size == 0) lock.wait()

                    println("consumed data: ${buffer.poll()}") //consume
                    lock.notify()

                    Thread.sleep(1000) //wait
                }
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            //println("main thread data initial value is $data")
            var counter = 0
            producer.start()
            consumer.start()

            //producer.join()
            //consumer.join()
        }
    }

}