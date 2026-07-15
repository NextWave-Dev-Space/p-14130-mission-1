package com

const val COMMAND_EXIT = "종료"

fun main() {
    println("== 명언 앱 ==")

    while (true) {
        print("명령) ")
        val command = readlnOrNull() ?: break

        when (command) {
            COMMAND_EXIT -> return
        }
    }
}