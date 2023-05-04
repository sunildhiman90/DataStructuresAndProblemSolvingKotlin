package multithreading

import java.util.LinkedList
import kotlin.concurrent.thread

//TODO need to find out what is the issue with [thread] function

/**
 * using [Thread] class from kotlin, work properly, as we saw while using [thread] function,
 * sometimes producer not producing all items even before consumer start consuming
 * but class works fine
 */

//produce all items at once, and then consume
class ProducerConsumerMultipleItemsAtOnce {

    companion object {

        private var buffer = LinkedList<String>()
        var capacity = 5
        var lock = Object()

        private var producer = Thread {

            var data = "Test"
            var index = 1
            while (true) {
                synchronized(lock) {
                    if(buffer.size == capacity) lock.wait()

                    var item = "$data $index"
                    buffer.add(item)
                    index++
                    println("produced data: $item") //produce
                    println("buffer size: ${buffer.size}")
                    lock.notify() //its notifying on every produce, but still its running thats why consumer will not be able to use lock until it start waiting using lock.wait,
                    // it will start waiting when buffer becomes full,then consumer will be able to use lock because at that time it will be waiting

                    Thread.sleep(1000) //wait

                }
            }
        }

        private var consumer = Thread {
            while (true) {
                synchronized(lock) {
                    if(buffer.size == 0) lock.wait()

                    println("consumed data: ${buffer.poll()}") //consume
                    lock.notify()
                    //once producer starts waiting, it will become active, it acquire the lock and start consuming
                    //then it notify on every consume, but still its active, it will become idle, when it will call
                    // lock.wait, then it will become idle, and then again producer will start producing, and this will keep repeating because we are using while(true) loop

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

/**
 * First Producer starts:-
 * and acquire the lock, and start producing and notifying to single thread consumer on lock, but consumer will be able to
 * start consuming when producer becomes idle by calling [lock.wait]
 *
 * Then Consumer Starts:-
 * Then when buffer becomes full,  producer calls [lock.wait] and become idle, then consumer will acquire lock and start consuming
 * then it will start consuming item and notify to producer but producer will be able to start again when consumer becomes idle by calling lock.wait
 *
 */