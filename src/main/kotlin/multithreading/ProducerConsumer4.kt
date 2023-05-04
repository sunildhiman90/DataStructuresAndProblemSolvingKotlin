package multithreading

import java.util.LinkedList



/**
 * using [Thread] class from kotlin, work properly, as we saw while using [thread] function,
 * sometimes producer not producing all items even before consumer start consuming
 * but class works fine
 */

//produce all items at once, and then consume, alternative way of using class and then define methods there
object ProducerConsumer4 {

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {

            val pc = PC()
            val producer = Thread {
                try {
                    pc.produce()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            val consumer = Thread {
                try {
                    pc.consume()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            producer.start()
            consumer.start()

            producer.join()
            consumer.join()
        }
    
    class PC {
        private var buffer = LinkedList<String>()
        var capacity = 5

        private val lock = Object()

        @Throws(InterruptedException::class)
        fun produce() {
            var data = "Test"
            var index = 1
            while (true) {
                //println("buffer.size=${buffer.size}") //using it here was causing the invalid flow, try uncommenting it and check yourself, producer will nto produce all items before consumer start consuming
                synchronized(lock) {
                    //if buffer is full, wait
                    if(buffer.size == capacity) lock.wait()

                    //produce
                    var item = "$data $index"
                    buffer.add(item)
                    index++
                    println("produced data: $item") //produce
                    //println("buffer.size=${buffer.size}")

                    //notify consumer
                    lock.notify()

                    //for making program understandable, it not needed in real implementation
                    Thread.sleep(1000)
                }
            }
        }

        @Throws(InterruptedException::class)
        fun consume() {
            while (true) {
                synchronized(lock) {
                    //if buffer is empty, wait
                    if(buffer.size == 0) lock.wait()

                    println("consumed data: ${buffer.poll()}") //consume

                    //notify producer
                    lock.notify()

                    //for making program understandable, it not needed in real implementation
                    Thread.sleep(1000)
                }
            }
        }

    }



}