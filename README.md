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

<h2>Configuration</h2>

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
screen.width=1200
screen.height=800
        </code></pre>
    </li>
    <li>
        <strong>Single Computer Multiplayer Setup:</strong>
        <ul>
            <li>Run the <code>Server</code> class first to start the game server.</li>
            <li>Then run the <code>Main</code>, <code>Main1</code>, <code>Main2</code>, and <code>Main3</code> classes in sequence to open four game interfaces on the same computer.</li>
        </ul>
    </li>
    <li>
        <strong>Multiple Computer Multiplayer Setup:</strong>
        <ul>
            <li>One player starts the game in server mode by running the <code>Server</code> class. This player also starts a client by running the <code>Main</code> class.</li>
            <li>Other players join the game by running the <code>Main</code> class on their computers.</li>
            <li>All players must be on the same network segment (within the same local area network).</li>
            <li>Clients connect to the server using the IP address and port number <code>12345</code> of the computer running the server.</li>
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

<h2>Usage</h2>

<p>To get started with the Mahjong game, follow these basic usage instructions:</p>

<h3>Single-Player Mode</h3>
<ul>
    <li>Launch the game by running the <code>Main</code> class.</li>
    <li>Select "Start Game" from the main menu.</li>
    <li>Choose your avatar and set your nickname.</li>
    <li>Start the game and enjoy playing against computer-controlled opponents.</li>
</ul>

<h3>Multiplayer Mode</h3>
<h4>Using a Single Computer</h4>
<ul>
    <li>Run the <code>Server</code> class to start the game server.</li>
    <li>Run the <code>Main</code>, <code>Main1</code>, <code>Main2</code>, and <code>Main3</code> classes in sequence to open four game interfaces on the same computer.</li>
    <li>Each player can choose their avatar and set their nickname on their respective interfaces.</li>
</ul>

<h4>Using Multiple Computers</h4>
<ul>
    <li>One player starts the game server by running the <code>Server</code> class and then runs the <code>Main</code> class to join the game as a client.</li>
    <li>Other players join the game by running the <code>Main</code> class on their own computers.</li>
    <li>All players must enter the server's IP address and port number <code>12345</code> to connect.</li>
    <li>Ensure all players are connected to the same local network.</li>
    <li>Each player can choose their avatar and set their nickname upon joining the game.</li>
</ul>

<p>For a visual guide, refer to the screenshots and tutorial videos provided in the <em>docs</em> folder.</p>

<h3>Contributors</h3>
<ul>
    <li>22207228 An Ran</li>
    <li>22207230 Shen Jinyan</li>
    <li>22207232 Zhu Qiyue</li>
    <li>22207236 Li Siying</li>
</ul>

<h2>Status</h2>

<p>The project is now completed.</p>

