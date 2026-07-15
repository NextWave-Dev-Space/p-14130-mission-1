package com

const val COMMAND_EXIT = "종료"
const val COMMAND_WRITE = "등록"
const val COMMAND_LIST = "목록"
const val COMMAND_DELETE_PREFIX = "삭제?id="

val NO_SPECIAL_CHAR_PATTERN = Regex("^[가-힣a-zA-Z0-9\\s.,!?]+$")

data class Quote(val id: Int, val content: String, val author: String)

val quotes = mutableListOf<Quote>()
var lastId = 0

fun main() {
    println("== 명언 앱 ==")

    while (true) {
        print("명령) ")
        val command = readlnOrNull() ?: break

        when {
            command == COMMAND_EXIT -> return
            command == COMMAND_WRITE -> write()
            command == COMMAND_LIST -> list()
            command.startsWith(COMMAND_DELETE_PREFIX) -> delete(command.removePrefix(COMMAND_DELETE_PREFIX))
        }
    }
}

fun write() {
    val content = readValidLine("명언")
    val author = readValidLine("작가")

    val id = generateNextId()
    quotes.add(Quote(id, content, author))

    println("${id}번 명언이 등록되었습니다.")
}

fun generateNextId(): Int {
    lastId++
    return lastId
}

fun list() {
    println("번호 / 작가 / 명언")
    println("----------------------")

    for (quote in quotes.asReversed()) {
        println("${quote.id} / ${quote.author} / ${quote.content}")
    }
}

fun delete(idText: String) {
    val id = idText.toIntOrNull() ?: return

    if (quotes.removeIf { it.id == id }) {
        println("${id}번 명언이 삭제되었습니다.")
    } else {
        println("${id}번 명언은 존재하지 않습니다.")
    }
}

fun readValidLine(label: String): String {
    while (true) {
        print("$label : ")
        val input = (readlnOrNull() ?: "").trim()

        if (input.isNotEmpty() && NO_SPECIAL_CHAR_PATTERN.matches(input)) {
            return input
        }

        println("$label 에는 특수문자를 입력할 수 없습니다. 다시 입력해주세요.")
    }
}