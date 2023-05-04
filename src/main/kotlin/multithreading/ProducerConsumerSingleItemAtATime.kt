package multithreading

import java.util.LinkedList
import kotlin.concurrent.thread
import kotlin.jvm.Throws

//produce single item and then consume that item, then produce next item then consume and so on..
//In this case we dont need capacity,
// because every single item produce by producer will consumed by consumer immediately
class ProducerConsumerSingleItemAtATime {

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
                    if(buffer.size < capacity) {
                        var item = "$data $index"
                        buffer.add(item)
                        index++
                        println("produced data: $item") //produce
                        lock.notify() //notify consumer

                        //wait for consumer to consume
                        lock.wait()
                    } /*else lock.wait()*/
                    Thread.sleep(1000) //wait
                }
            }
        }

        private var consumer = thread(start = false) {
            while (true) {
                synchronized(lock) {
                    if(buffer.isNotEmpty()) {
                        println("consumed data: ${buffer.poll()}") //consume item
                        lock.notify() //notify producer

                        //wait for producer to produce next item
                        lock.wait()
                    }/* else lock.wait()*/
                    Thread.sleep(1000) //wait
                }
            }
        }

        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            //println("main thread data initial value is $data")
            var counter = 0
            producer.start()
            consumer.start()

            producer.join()
            consumer.join()
        }
    }

}

/**
 * First Producer starts:-
 * and acquire the lock, and produce one item and notify to single thread consumer on lock,
 * and wait for consumer to consume using lock.wait,
 *
 * Then Consumer Starts:-
 * Then consumer start, consume item, notify producer
 * and wait using lock.wait for producer to produce next item
 *
 * then again producer will produce, notify and wait for consume
 * then again consumer will consumer, notify and wait for produce next item
 *
 * and this process keeps going on indefinitely
 *
 */