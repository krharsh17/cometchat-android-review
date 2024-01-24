package com.example.cometchatandroidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chatuikit.conversationswithmessages.CometChatConversationsWithMessages

class ConversationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setConversationsConfig()
    }

    private fun setConversationsConfig() {
        val conversations = CometChatConversationsWithMessages(this)
        setContentView(conversations)
    }
}