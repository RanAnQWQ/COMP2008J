# COMP2008J Mahjong Project

<h2>Introduction</h2>

<p>This is a basic implementation of the Mahjong game. Mahjong is a game for four people invented in ancient China. The game involves small rectangular pieces made of bamboo, bone, or plastic, engraved with patterns or characters. In the version of Mahjong used in this game, there are 134 cards per deck. The main types of Mahjong cards are Bing (Wenqian), Tiao (suozi), Wan (Wan Guan), and wind. This project realizes the basic functions of Mahjong and can be played in two modes: stand-alone and online mode.</p>

<h3>Summary</h3>

<p>Our Mahjong game features essential functions such as Chi, Pong, Kong, Ready Hand, and Win. Players can enjoy the game in single-player mode or connect over a local area network (LAN) for multiplayer sessions, either on the same computer or across multiple computers. Additionally, players have the option to choose their avatars and set their own nicknames, enhancing the personalized gaming experience.</p>

<h3>Features</h3>

<p>Our Mahjong game includes the following features:</p>

<ul>
    <li>Chi, Pong, and Kong: Execute basic moves like Chi (melding three consecutive tiles), Pong (melding three identical tiles), and Kong (melding four identical tiles).</li>
    <li>Ready Hand and Win: Prepare for winning by declaring Ready Hand and achieve victory by completing a valid hand.</li>
    <li>Single-Player Mode: Play against computer-controlled opponents for a solo gaming experience.</li>
    <li>Multiplayer Mode: Connect with other players over a local area network (LAN) to enjoy multiplayer sessions, either on the same computer or across multiple computers.</li>
    <li>Avatar Selection: Customize your gaming experience by choosing an avatar.</li>
    <li>Nickname Selection: Personalize your identity in the game by setting your own nickname.</li>
</ul>
<p>These features ensure a comprehensive and enjoyable Mahjong experience for all players.</p>

<h2>Environment Requirements</h2>

<p>Our Mahjong game  has the following dependencies and environmental requirements:</p>

<ul>
    <li>Operating System: The game is compatible with Windows, macOS, and Linux.</li>
    <li>Java Runtime Environment (JRE): Version 8 or above is required to run the game.</li>
    <li>Network: For multiplayer mode, a stable local area network (LAN) connection is necessary.</li>
    <li>Input Devices: A keyboard and mouse are required for gameplay.</li>
</ul>
<p>Ensure that your system meets these requirements to run the Mahjong game smoothly.</p>

<h2>Configuration-配置</h2>

<p>To configure the Mahjong game, follow these steps:</p>

<ol>
    <li>
        <strong>Download and Install Java:</strong>
        <ul>
            <li>Ensure you have Java Runtime Environment (JRE) version 8 or above installed on your system. You can download it from the <a href="https://www.java.com/en/download/">official Java website</a>.</li>
        </ul>
    </li>
    <li>
        <strong>Network Configuration for Multiplayer Mode:</strong>
        <ul>
            <li>For LAN multiplayer mode, ensure all participating computers are connected to the same local network.</li>
            <li>Configure the firewall settings on each computer to allow the Mahjong game to communicate over the network. Typically, you need to allow the game executable and Java to access the network.</li>
        </ul>
    </li>
    <li>
        <strong>Game Configuration:</strong>
        <ul>
            <li>Download the Mahjong game package and extract it to your desired location.</li>
            <li>Open the game configuration file (<code>config.properties</code> or similar) in a text editor to customize settings like screen resolution, sound volume, and default avatars. Example configuration entries might look like:</li>
        </ul>
        <pre><code>
screen.width=1920
screen.height=1080
sound.volume=75
default.avatar=avatar1.png
        </code></pre>
    </li>
    <li>
        <strong>Multiplayer Server Setup:</strong>
        <ul>
            <li>To host a multiplayer game, one player needs to start the game in server mode. This can usually be done through an option in the game menu or by running a specific command like:</li>
        </ul>
        <pre><code>java -jar MahjongGame.jar --mode server</code></pre>
        <ul>
            <li>Other players can join by selecting the multiplayer option and entering the server's IP address. The IP address can typically be found in the network settings of the server computer.</li>
        </ul>
    </li>
    <li>
        <strong>Avatar and Nickname Selection:</strong>
        <ul>
            <li>Upon launching the game, navigate to the profile settings to choose an avatar and set your nickname. These settings will be saved for future sessions.</li>
        </ul>
    </li>
</ol>

<p>Ensure you follow these configuration steps to set up the game according to your preferences and network environment.</p>


<h2>Usage-用法</h2>

<p>Describe the basic usage of the project, including screenshots and videos if possible.</p>

<h2>Development-开发</h2>

<p>Document how to develop the project, including API information and compatibility notes.</p>

<h2>Changelog-更新日志</h2>

<p>Provide a brief history of changes, replacements, or other updates.</p>

<h2>FAQ-常见问题</h2>

<p>Compile a list of common questions and answers to help new users.</p>

<h2>Support-支持</h2>

<p>Inform users where they can seek help, such as issue trackers, chat rooms, email addresses, etc.</p>

<h3>Doc-文档</h3>

<p>Link to additional documentation.</p>

<h3>Release Planning-版本规划</h3>

<p>Describe the future direction of the project.</p>

<h2>Contributing-贡献</h2>

<p>Provide guidelines for contributing to the project.</p>

<h3>Contributors 贡献者</h3>
<ul>
    <li>22207228 An Ran</li>
    <li>22207230 Shen Jinyan</li>
    <li>22207232 Zhu Qiyue</li>
    <li>22207236 Li Siying</li>
</ul>

<h2>Status 项目状态</h2>

<p>The project is now completed.</p>

