# InvSee Helper – Minecraft 1.21.11 Fabric

Funktionen:
- Sneaken + Rechtsklick auf einen anderen Spieler -> `/invsee NAME`
- Chat: Wenn der angeklickte Spielername eine `insertion` mit dem Minecraft-Namen enthält -> `/invsee NAME`

## Bauen unter Windows

Voraussetzung: Java 21 und Gradle.

Im Projektordner:
```powershell
gradle build
```

Die fertige JAR liegt danach hier:
`build/libs/invseehelper-1.0.0.jar`

Dann die JAR in:
`%appdata%\.minecraft\mods`

legen.

Wichtig:
Der Server muss `/invsee` bereitstellen und du brauchst die entsprechenden Rechte. Die Mod umgeht keine Serverrechte.

## Hinweis zum Chat-Klick

Minecraft-Chat ist versionsabhängig. Diese Version nutzt den vom Chat bereitgestellten `Style.insertion`-Namen. Bei Servern, die Spielernamen nicht als klickbare Einfüge-Elemente ausgeben, kann der Chat-Klick daher nicht automatisch den Namen erkennen. Die Sneak-Rechtsklick-Funktion funktioniert davon unabhängig.