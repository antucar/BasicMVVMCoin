package com.example.basicmvvm.model

data class ChatbotRequest(val model: String = "gpt-3.5-turbo", val messages: String, val max_tokens: Int = 2000)
