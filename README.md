# BastionBot v3.0
By **valentinoIAN (IanSynth)**

## What it Does

BastionBot is a multipurpose Discord bot for use in the Bastion speedrunning Discord server. It posts recently verified runs, posts when streamers begin streaming Bastion speedruns, and can respond to custom commands.

## Chat Commands

In any channel that BastionBot has access to, you can use the following commands: 

**!talkforaspell** : Posts a hyperlink (with embed) to a twitch clip. (WE TALK FOR A SPELL)  
**Gold auto-react** : BastionBot will mimic any reacts with the :Gold: emote.


## Architecture

* **Java 11** Runtime
* **Spring Boot** Framework
* **Feign** for speedrun.com API calls
* **[JDA](https://github.com/DV8FromTheWorld/JDA)** for Twitch API calls
* Deployed on **Azure App Service**
