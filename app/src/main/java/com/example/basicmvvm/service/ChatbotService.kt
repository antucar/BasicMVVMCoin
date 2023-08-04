package com.example.basicmvvm.service

import com.example.basicmvvm.model.ChatbotRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatbotService {



    @Headers(
        "Authorization: Bearer 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",
        "OpenAI-Organization: org-L2kRJceh2zNsLStu5bG21XQR",
        "Content-Type: application/json"
    )


    @POST("v1/chat/completions")
    fun getChatbotResponse(@Body request: ChatbotRequest): String



}