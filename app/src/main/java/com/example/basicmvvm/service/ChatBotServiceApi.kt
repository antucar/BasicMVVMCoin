package com.example.basicmvvm.service


import com.example.basicmvvm.model.ChatbotRequest
import com.example.basicmvvm.model.ChatbotResponseEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatBotServiceApi() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val chatbotService = retrofit.create(ChatbotService::class.java)
    lateinit var ChatbotResponseDao: ChatbotResponseDao
    lateinit var chatbotRequest: ChatbotRequest

    fun getChatbotResponse(userMessage: String): String {
        try {
            val response = chatbotService.getChatbotResponse(
                ChatbotRequest(
                    model = "gpt-3.5-turbo",
                    messages = ChatbotRequest("gpt-3.5-turbo", messages = chatbotRequest.messages,2000).toString(),
                    max_tokens = 3000
                )
            )

            // Save the user message and the chatbot's response to the database
            val chatbotResponseEntity = ChatbotResponseEntity(userMessage, response)
            ChatbotResponseDao.insert(chatbotResponseEntity)

            return response
        } catch (e: Exception) {
            // Handle errors
            return "Error: ${e.message}"
        }
    }
}
