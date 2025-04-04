package killua.dev.whounfollowedmeontwitter.Model.Login

import killua.dev.whounfollowedmeontwitter.datastore.writeApplicationUserAuth
import killua.dev.whounfollowedmeontwitter.datastore.writeApplicationUserCt0
import killua.dev.whounfollowedmeontwitter.datastore.writeApplicationUserID

class TwitterConfig : PlatformConfig {
    override val loginUrl = "https://x.com/i/flow/login"
    override val cookieDomain = "https://x.com"
    override val titleText = "Login your Twitter account"
    override val cookieRuleGroups = listOf(
        CookieRuleGroup(
            rules = listOf(
                CookieRule(
                    name = "ct0",
                    pattern = "ct0=([^;]+)",
                    saveFunction = { context, cookieInfo ->
                        println("ct0 = ${cookieInfo.value}")
                        context.writeApplicationUserCt0(cookieInfo.value)
                    }
                ),
                CookieRule(
                    name = "auth_token",
                    pattern = "auth_token=([^;]+)",
                    saveFunction = { context, cookieInfo ->
                        println("auth_token = ${cookieInfo.value}")
                        context.writeApplicationUserAuth(cookieInfo.value)
                    }
                ),
                CookieRule(
                    name = "twid",
                    pattern = "twid=([^;]+)",
                    saveFunction = { context, cookieInfo ->
                        val twid = cookieInfo.value.split("u=").last().split("\"")[0]
                        println("twid = $twid")
                        context.writeApplicationUserID(twid)
                    }
                )
            ),
            matchOne = false
        )
    )
}