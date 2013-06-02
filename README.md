Miney-Client
============

A social networking mod for Minecraft and the spiritual successor to [SocialMiner](http://www.minecraftforum.net/topic/769872-/).

Planned Features
----------------
- Friends list
- Messaging system
- Clans / Groups
- Mod listing / Usage statistics
- Automatic updating

Contributing
------------
I am currently looking for people who would like to contribute to the client. If you would like to contact me about helping you can message me at [#Miney](http://webchat.esper.net/?channels=Miney) on the [Esper IRC network](http://esper.net/).

FAQ
---
> Where is the server code?

Currently I plan to keep the client open source and the server closed source for security reasons.

> Why not have both closed source?

Lets face it, if I had the client closed source and people wanted to do something malicious they could just decompile it using MCP and tweak it so why not have it open source and let others contribute to the mod?

> How do I run the code?

The code is designed to be launched by [Miney-Updater](https://github.com/medsouz/Miney-Updater) instead of Forge Modloader. Doing this allows the mod to automatically update itself whenever Minecraft is restarted. To run the code you need both Miney-Updater and Miney-Client's source in the Minecraft Forge MCP src folder.

> You should totally add -insert-idea-here-!

I'd love to hear your ideas! Feel free to join [#Miney](http://webchat.esper.net/?channels=Miney) on the [Esper IRC network](http://esper.net/) and let me know your idea! :smile:

Build Status
------------
I am running a [Gitlab-CI](https://github.com/gitlabhq/gitlab-ci) instance that compiles the code whenever a commit is made. You can click the badge for more information.
[![build status](http://ci.medsouz.net/projects/1/status.png?ref=master)](http://ci.medsouz.net/projects/1?ref=master)
