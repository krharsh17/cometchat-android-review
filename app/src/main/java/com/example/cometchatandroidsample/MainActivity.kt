package com.example.cometchatandroidsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chatuikit.shared.cometchatuikit.CometChatUIKit
import com.cometchat.chatuikit.shared.cometchatuikit.UIKitSettings
import com.example.cometchatandroidsample.ui.theme.CometChatAndroidSampleAppTheme

class MainActivity : ComponentActivity() {
    val TAG = "MainActivity"

    private fun initEventHandlers() {
        val goToLoginBtn = this.findViewById<AppCompatButton>(R.id.go_to_login)
        goToLoginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val goToSignupBtn = this.findViewById<AppCompatButton>(R.id.go_to_signup)
        goToSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun initCometChatUiKit() {
        val uiKitSettings = UIKitSettings.UIKitSettingsBuilder()
            .setRegion(Constants.REGION)
            .setAppId(Constants.APP_ID)
            .setAuthKey(Constants.AUTH_KEY)
            .subscribePresenceForAllUsers().build()

        CometChatUIKit.init(this, uiKitSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(successString: String?) {}
            override fun onError(e: CometChatException?) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCometChatUiKit()
        initEventHandlers()
        checkLoginStatus()

    }

    private fun checkLoginStatus() {
        if (CometChat.getLoggedInUser() != null) {
            // User already logged in
            Log.d(TAG, "User is already logged in: " + CometChat.getLoggedInUser())
            startActivity(Intent(this@MainActivity, ConversationsActivity::class.java))
        }
    }
}
