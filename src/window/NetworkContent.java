package window;

import Player.InitNetworkPlayer;
import Player.NetworkPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.*;

import static window.NetworkWindow.rivers;

public class NetworkContent extends JFrame {

    public static NetworkWindow networkWindow;

    public AddNetworkTile addNetworkTile;

    public static String host;
    public static int hostNumber;

    public Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JLabel tipLabel = new JLabel();
    public String clientId;
    private boolean isHost;

    public NetworkContent() {
        addNetworkTile = new AddNetworkTile();
    }

    public void aGame(String ip, int port) throws InterruptedException {  // the methods to create a game

        try {
            socket = new Socket(ip, port);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String firstResp = in.readLine();
            if (firstResp.contains("Client-")){
                System.out.println(firstResp);
                String msg = firstResp.split("-")[1];
                clientId = msg;
            }

            //NetworkWindow networkWindow = new NetworkWindow();  // initial the game window
            networkWindow = new NetworkWindow(NetworkContent.this);
            if (networkWindow.player==null)networkWindow.setHuman(new NetworkPlayer(Integer.parseInt(clientId)));
            //dice_button(sum, networkWindow.gamePanel);
            // initial the players

            new Thread(() -> {
                boolean init = false;
                while (true) {
                    try {
                        String response = in.readLine();
                        if (response == null) {
                            // 如果没有数据可读，通常是因为socket已经关闭
                            break;
                        }

                        System.out.println(response);

                        //等待其他玩家
                        if (response.startsWith("Waiting") && !init){
                            String msg = response.split("-")[1];
                            System.out.println(msg);

                            tipLabel.setText(msg);
                            Font font = new Font("Arial", Font.BOLD, 36);
                            tipLabel.setFont(font);
                            tipLabel.setForeground(Color.red);
                            tipLabel.setBounds(450,250,300,50);
                            networkWindow.gamePanel.add(tipLabel);
                            networkWindow.gamePanel.repaint();

                            init = true;

                            continue;
                        }

                        //确认东家
                        if (response.startsWith("Host")){
                            String msg = response.split("-")[1];

                            //如果与客户端id一致，则作为东家
                            if (msg.equals(clientId)){
                                isHost = true;
                                networkWindow.canClick = true;
                            }

                            new InitNetworkPlayer();

                            showArrow(Integer.parseInt(this.clientId), Integer.parseInt(msg));

                            networkWindow.gamePanel.remove(tipLabel);

                            continue;
                        }

                        //发牌和处理头像
                        if (response.startsWith("Majiangs&Avatar")){
                            String clientId = response.split("-")[1];
                            Integer size = Integer.parseInt(response.split("-")[2]);
                            String majiangs = response.split("-")[3];
                            String avatar = response.split("-")[4];
                            if (this.clientId.equals(clientId)){
                                System.out.println(majiangs);
                                String[] array = majiangs.replace("[", "").replace("]", "").split(", ");
                                for (String s : array) {
                                    networkWindow.player.playerMajiangs.add(Integer.parseInt(s));
                                }
                                networkWindow.addTileToWindow(networkWindow.player.playerMajiangs);
                            }else {
                                switch (this.clientId){
                                    case "1":
                                        switch (clientId) {
                                            case "2":
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("1", avatar);
                                                break;
                                            case "3":
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("3", avatar);
                                                break;
                                            case "4":
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("2", avatar);
                                                break;
                                        }
                                        break;
                                    case "2":
                                        switch (clientId) {
                                            case "3":
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("1", avatar);
                                                break;
                                            case "4":
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("3", avatar);
                                                break;
                                            case "1":
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("2", avatar);
                                                break;
                                        }
                                        break;
                                    case "3":
                                        switch (clientId) {
                                            case "4":
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("1", avatar);
                                                break;
                                            case "1":
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("3", avatar);
                                                break;
                                            case "2":
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("2", avatar);
                                                break;
                                        }
                                        break;
                                    case "4":
                                        switch (clientId) {
                                            case "1":
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("1", avatar);
                                                break;
                                            case "2":
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("3", avatar);
                                                break;
                                            case "3":
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                networkWindow.addOtherAvatar("2", avatar);
                                                break;
                                        }
                                        break;
                                }
                            }
                            continue;
                        }

                        //出牌
                        if (response.startsWith("Discard")){
                            String clientId = response.split("-")[1];
                            Integer card = Integer.parseInt(response.split("-")[2]);
                            Integer size = Integer.parseInt(response.split("-")[3]);
                            if (!this.clientId.equals(clientId)){
                                switch (this.clientId){
                                    case "1":
                                        switch (clientId) {
                                            case "2":
                                                networkWindow.otherPlayTile(700, 210+260,
                                                        Boolean.FALSE, 1, card);
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "3":
                                                networkWindow.otherPlayTile(678, 200, Boolean.TRUE, 2, card);
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "4":
                                                networkWindow.otherPlayTile(380, 210, Boolean.FALSE, 3, card);
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                break;
                                        }

                                        break;
                                    case "2":
                                        switch (clientId) {
                                            case "3":
                                                networkWindow.otherPlayTile(700, 210+260,
                                                        Boolean.FALSE, 1, card);
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "4":
                                                networkWindow.otherPlayTile(678, 200, Boolean.TRUE, 2, card);
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "1":
                                                networkWindow.otherPlayTile(380, 210, Boolean.FALSE, 3, card);
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                break;
                                        }
                                        break;
                                    case "3":
                                        switch (clientId) {
                                            case "4":
                                                networkWindow.otherPlayTile(700, 210+260,
                                                        Boolean.FALSE, 1, card);
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "1":
                                                networkWindow.otherPlayTile(678, 200, Boolean.TRUE, 2, card);
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "2":
                                                networkWindow.otherPlayTile(380, 210, Boolean.FALSE, 3, card);
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                break;
                                        }
                                        break;
                                    case "4":
                                        switch (clientId) {
                                            case "1":
                                                networkWindow.otherPlayTile(700, 210+260,
                                                        Boolean.FALSE, 1, card);
                                                addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "2":
                                                networkWindow.otherPlayTile(678, 200, Boolean.TRUE, 2, card);
                                                addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                break;
                                            case "3":
                                                networkWindow.otherPlayTile(380, 210, Boolean.FALSE, 3, card);
                                                addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                break;
                                        }
                                        break;
                                }

                                //检查是否杠和碰
                                if (networkWindow.gang_button(networkWindow.player.isGang(card))){
                                    out.println("Gang-"+this.clientId+"-"+card+"-"+true);
                                    System.out.println("Gang-"+this.clientId+"-"+card+"-"+true);
                                }else {
                                    out.println("Gang-"+this.clientId+"-"+card+"-"+false);
                                    System.out.println("Gang-"+this.clientId+"-"+card+"-"+false);
                                }

                                if (networkWindow.peng_button(networkWindow.player.isPeng(card))){
                                    out.println("Peng-"+this.clientId+"-"+card+"-"+true);
                                    System.out.println("Peng-"+this.clientId+"-"+card+"-"+true);
                                }else {
                                    out.println("Peng-"+this.clientId+"-"+card+"-"+false);
                                    System.out.println("Peng-"+this.clientId+"-"+card+"-"+false);
                                }

                                //检查是否吃
                                boolean chiFlag = ("1".equals(this.clientId)&&"4".equals(clientId))
                                        || ("2".equals(this.clientId)&&"1".equals(clientId))
                                        || ("3".equals(this.clientId)&&"2".equals(clientId))
                                        || ("4".equals(this.clientId)&&"3".equals(clientId));
                                if (chiFlag && networkWindow.chi_button(networkWindow.player.isChi(card))){
                                    out.println("Chi-"+this.clientId+"-"+card+"-"+true);
                                    System.out.println("Chi-"+this.clientId+"-"+card+"-"+true);
                                }else {
                                    out.println("Chi-"+this.clientId+"-"+card+"-"+false);
                                    System.out.println("Chi-"+this.clientId+"-"+card+"-"+false);
                                }
                            }

                            continue;
                        }

                        //碰和杠
                        if (response.startsWith("Peng")){
                            String clientId = response.split("-")[1];
                            Integer card = Integer.parseInt(response.split("-")[2]);
                            if (this.clientId.equals(clientId)){
                                networkWindow.skip_button(true);
                                networkWindow.pengButton.addActionListener(e -> {
                                    networkWindow.removeButton();
                                    System.out.println(clientId + " Click Peng");
                                    networkWindow.player.Peng(card, networkWindow.scaledWidth, networkWindow.scaledHeight,
                                            networkWindow.gamePanel, 4, networkWindow.addNetworkTile);
                                    networkWindow.addTileToWindow(networkWindow.player.playerMajiangs);
                                    networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                    StringBuilder msg = new StringBuilder("Finish-Peng-" + this.clientId + "-");
                                    for (int i = 0; i < 3; i++) {
                                        if (i!=2){
                                            msg.append(card).append(",");
                                        }else {
                                            msg.append(card);
                                        }
                                    }
                                    out.println(msg.toString());
                                });

                            }
                        }else if (response.startsWith("Gang")){
                            String clientId = response.split("-")[1];
                            Integer card = Integer.parseInt(response.split("-")[2]);
                            if (this.clientId.equals(clientId)){
                                networkWindow.skip_button(true);
                                networkWindow.gangButton.addActionListener(e -> {
                                    System.out.println(clientId + " Click Gang");
                                    networkWindow.removeButton();
                                    networkWindow.player.Gang(card, networkWindow.scaledWidth, networkWindow.scaledHeight,
                                            networkWindow.gamePanel, 4, networkWindow.addNetworkTile);
                                    networkWindow.addTileToWindow(networkWindow.player.playerMajiangs);
                                    networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                    StringBuilder msg = new StringBuilder("Finish-Gang-" + this.clientId + "-");
                                    for (int i = 0; i < 4; i++) {
                                        if (i!=3){
                                            msg.append(card).append(",");
                                        }else {
                                            msg.append(card);
                                        }
                                    }
                                    out.println(msg.toString());
                                });

                            }
                        }else if (response.startsWith("Chi")){
                            String clientId = response.split("-")[1];
                            Integer card = Integer.parseInt(response.split("-")[2]);
                            if (this.clientId.equals(clientId)){
                                networkWindow.skip_button(true);
                                networkWindow.chiButton.addActionListener(e -> {
                                    System.out.println(clientId + " Click Chi");
                                    networkWindow.removeButton();
                                    NetworkPlayer player = networkWindow.player;
                                    List<Integer> list = player.Chi(player.isChi(card), networkWindow.chi_choice(player, player.isChi(card)), card, networkWindow);
                                    networkWindow.addTileToWindow(networkWindow.player.playerMajiangs);
                                    networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                    StringBuilder msg = new StringBuilder("Finish-Chi-" + this.clientId + "-");
                                    for (int i = 0; i < list.size(); i++) {
                                        if (i!=3){
                                            msg.append(list.get(i)).append(",");
                                        }else {
                                            msg.append(list.get(i));
                                        }
                                    }
                                    out.println(msg.toString());
                                });

                            }
                        }else if (response.startsWith("Finish")){
                            String[] split = response.split("-");
                            String clientId =  split[2];
                            String card =  split[3]; //card,card,card
                            List<String> list = Arrays.asList(card.split(","));
                            String discardClientId =  split[4];
                            if (!this.clientId.equals(clientId)){
                                //清理河中的牌
                                //展示杠或碰牌
                                switch (this.clientId){
                                    case "1":
                                        switch (clientId) {
                                            case "2":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            1);
                                                }
                                                break;
                                            case "3":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            2);
                                                }
                                                break;
                                            case "4":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            3);
                                                }
                                                break;
                                        }

                                        networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                        break;
                                    case "2":
                                        switch (clientId) {
                                            case "3":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            1);
                                                }
                                                break;
                                            case "4":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            2);
                                                }
                                                break;
                                            case "1":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            3);
                                                }
                                                break;
                                        }
                                        networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                        break;
                                    case "3":
                                        switch (clientId) {
                                            case "4":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            1);
                                                }
                                                break;
                                            case "1":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            2);
                                                }
                                                break;
                                            case "2":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            3);
                                                }
                                                break;
                                        }
                                        networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                        break;
                                    case "4":
                                        switch (clientId) {
                                            case "1":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            1);
                                                }
                                                break;
                                            case "2":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            2);
                                                }
                                                break;
                                            case "3":
                                                for (String s : list) {
                                                    networkWindow.addNetworkTile.addTileToDisplay(Integer.parseInt(s), networkWindow.scaledWidth,
                                                            networkWindow.scaledHeight, networkWindow.gamePanel,
                                                            3);
                                                }
                                                break;
                                        }
                                        networkWindow.gamePanel.remove(rivers.get(rivers.size()-1));
                                        break;
                                }

                            }else {
                                networkWindow.addTileToWindow(networkWindow.player.playerMajiangs);
                                networkWindow.canClick = true;
                            }
                        } else {
                            //发牌和切换玩家
                            if (response.startsWith("Current")) {
                                String currentId = response.split("-")[1];
                                String playerMajiangs = response.split("-")[2];
                                Integer size = Integer.parseInt(response.split("-")[3]);

                                showArrow(Integer.parseInt(this.clientId), Integer.parseInt(currentId));

                                if (this.clientId.equals(currentId)){
                                    //发牌
                                    System.out.println(playerMajiangs);
                                    String[] array = playerMajiangs.replace("[", "").replace("]", "").split(", ");
                                    List<Integer> list = new ArrayList<>();
                                    for (String s : array) {
                                        list.add(Integer.parseInt(s));
                                    }

                                    NetworkPlayer player = networkWindow.player;
                                    if (player.isHu(list)){
                                        out.println("Win-"+this.clientId);
                                        return;
                                    }else if (player.isTing){
                                        Integer card = list.get(list.size() - 1);
                                        // 将牌添加到河中
                                        addNetworkTile.addTileToRiverX(new ImageIcon(new ImageIcon(networkWindow.tilemap.getTilePath(card)).getImage()
                                                        .getScaledInstance(networkWindow.scaledWidth, networkWindow.scaledHeight,
                                                                Image.SCALE_SMOOTH)), card, networkWindow.discardStartX,
                                                networkWindow.discardStartY, networkWindow.maxTilesPerRow, networkWindow.gamePanel,4);

                                        out.println("Discard-" + this.clientId + "-" + card);
                                    }else {
                                        networkWindow.addTileToWindow(list);

                                        //允许出牌
                                        networkWindow.canClick = true;
                                    }
                                }else {
                                    switch (this.clientId){
                                        case "1":
                                            switch (currentId) {
                                                case "2":
                                                    addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "3":
                                                    addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "4":
                                                    addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                    break;
                                            }
                                            break;
                                        case "2":
                                            switch (currentId) {
                                                case "3":
                                                    addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "4":
                                                    addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "1":
                                                    addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                    break;
                                            }
                                            break;
                                        case "3":
                                            switch (currentId) {
                                                case "4":
                                                    addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "1":
                                                    addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "2":
                                                    addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                    break;
                                            }
                                            break;
                                        case "4":
                                            switch (currentId) {
                                                case "1":
                                                    addNetworkTile.addNetwork1Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "2":
                                                    addNetworkTile.addNetwork2Tile(size, networkWindow.gamePanel);
                                                    break;
                                                case "3":
                                                    addNetworkTile.addNetwork3Tile(size, networkWindow.gamePanel);
                                                    break;
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void init(String host){
        if(host.equals("East")){
            //hostNumber=0;
            hostNumber=4;

        }else if(host.equals("South")){
            //hostNumber=1;
            hostNumber=3;

        }else if(host.equals("West")){
            //hostNumber=2;
            hostNumber=2;

        }else if(host.equals("North")){
            //hostNumber=3;
            hostNumber=1;

        }
        networkWindow.startRobotPlaySequence(hostNumber);
    }

    ///////////////////////method of help button including menu,restart and rules in game window///////////////////////////
    void helpButtons(ImagePanel gamePanel) {  //written by Siying.Li
        JButton helpButton = new JButton("HELP");  //set the help button including three buttons
        Font helpFont = new Font("Arial", Font.BOLD, 24);
        helpButton.setFocusPainted(false);
        helpButton.setFont(helpFont);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);
        helpButton.addActionListener(new ActionListener() {  //if click the game button, the menu window will be closed and the game window will be open
            public void actionPerformed(ActionEvent e) {
                JFrame helpWindow = new JFrame("Help");
                helpWindow.setSize(240, 240);
                helpWindow.setLayout(new GridLayout(3, 1));

                JButton menuButton = new JButton("Main Menu");  //set the menu button
                menuButton.setFocusPainted(false);
                Font backFont = new Font("Arial", Font.BOLD, 30);
                menuButton.setSize(80, 80);
                menuButton.setFont(backFont);
                menuButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the menu button, the game window will be closed and the main menu window will be open
                        helpWindow.dispose();
                        networkWindow.dispose();

                        new MainMenuWindow();
                    }
                });
                helpWindow.add(menuButton);

                JButton restartButton = new JButton("Restart");  //set the restart button
                restartButton.setFocusPainted(false);
                Font restartFont = new Font("Arial", Font.BOLD, 30);
                restartButton.setSize(80, 80);
                restartButton.setFont(restartFont);
                restartButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the restart button, the game window will reopen for a new game
                        helpWindow.dispose();
                        networkWindow.dispose();
//                        try {
//                            aGame();
//                        } catch (InterruptedException ex) {
//                            throw new RuntimeException(ex);
//                        }
                    }
                });
                helpWindow.add(restartButton);

                JButton rulesButton = new JButton("Rules");  //set the rules button
                rulesButton.setFocusPainted(false);
                Font rulesFont = new Font("Arial", Font.BOLD, 30);
                rulesButton.setSize(80, 80);
                rulesButton.setFont(rulesFont);
                rulesButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the rules button, the game window will not be closed and the rules window will be open
                        new RulesWindow().setVisible(true);
                    }
                });
                helpWindow.add(rulesButton);
                helpWindow.setLocationRelativeTo(null);
                helpWindow.setVisible(true);
            }
        });
        helpButton.setBounds(1100, 0, 100, 50);  //set the button site and size
        gamePanel.add(helpButton);
    }

    ////////////////////////////dice button///////////////////////////
    public void dice_button(int sum, ImagePanel gamePanel) {  //written by Jinyan.Shen
        JButton num = new JButton("THROW DICE");
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        num.setFocusPainted(false);
        num.setFont(dice_font);
        num.setHorizontalAlignment(SwingConstants.CENTER);
        num.setBounds(475, 0, 200, 50);
        gamePanel.add(num);
        // set the button to notice the host.

        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component comp : gamePanel.getComponents()) {// the players' tiles will be showed after clicking the dice button
                    if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null && !comp.isVisible()) {
                        comp.setVisible(true);
                        comp.setEnabled(true);
                        addNetworkTile.showNetworkTile();
                    }
                }

                for (Component comp : gamePanel.getComponents()) {
                    if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                    }
                }


                // set a timer, let the number of dice and the arrow label remain 5s and remove;
                init(host);
                System.out.println("host:"+host);
                System.out.println("hostnumber"+hostNumber);
            }
        });
    }

    public void showArrow(int clientId, int host){
        Image scaledArrow;
        ImageIcon scaledArrowIcon;
        JLabel arrowLabel;
        ImagePanel gamePanel = networkWindow.gamePanel;

        ImageIcon arrowIcon = choose_arrow(clientId, host);
        scaledArrow = arrowIcon.getImage().getScaledInstance(arrowIcon.getIconWidth() / 15, arrowIcon.getIconHeight() / 15, Image.SCALE_DEFAULT);
        scaledArrowIcon = new ImageIcon(scaledArrow);
        arrowLabel = new JLabel(scaledArrowIcon);
        // set the arrowLabel object to display (and remove after 5s);

        arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrowLabel.setVerticalAlignment(SwingConstants.CENTER);
        //set horizontal center & vertical center;

        gamePanel.setLayout(null);
        //remove the default lay

        int labelWidth = scaledArrowIcon.getIconWidth();
        int labelHeight = scaledArrowIcon.getIconHeight();
        int panelWidth = gamePanel.getWidth();
        int panelHeight = gamePanel.getHeight();
        int x = (panelWidth - labelWidth) / 2;
        int y = (panelHeight - labelHeight) / 2;
        arrowLabel.setBounds(x, y, labelWidth, labelHeight);
        //compute the position of the arrow;

        gamePanel.add(arrowLabel);
        // add the label picture to game screen;

        JLabel sumLabel = new JLabel("" + host);
        Font label_font = new Font("Arial", Font.BOLD, 50);
        sumLabel.setLocation(560, 250);
        sumLabel.setFont(label_font);
        sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumLabel.setSize(70, 50);
        sumLabel.setBackground(Color.WHITE);
        sumLabel.setOpaque(true);
        gamePanel.add(sumLabel);
        revalidate();
        repaint();
        // set the label, show the sum of 2 dices;

        // remove the original button once the label occur;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    gamePanel.remove(sumLabel);
                    gamePanel.remove(arrowLabel);
                    gamePanel.revalidate();
                    gamePanel.repaint();
                });
                timer.cancel();
            }
        }, 2000);
    }

    public static ImageIcon choose_arrow(int clientId, int sum) {
        ImageIcon arrowIcon;

        switch (clientId){
            case 1:
                switch (sum % 4) {
                    case 1:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowDown.png");
                        break;
                    //East;

                    case 2:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowRight.png");
                        break;
                    //North;

                    case 3:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowTop.png");
                        break;
                    //West;

                    default:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowLeft.png");
                        break;
                    //South;

                }
                break;
            //East;

            case 2:
                switch (sum % 4) {
                    case 1:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowLeft.png");
                        break;
                    //East;

                    case 2:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowDown.png");
                        break;
                    //North;

                    case 3:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowRight.png");
                        break;
                    //West;

                    default:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowTop.png");
                        break;
                    //South;

                }
                break;
            //North;

            case 3:
                switch (sum % 4) {
                    case 1:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowTop.png");
                        break;
                    //East;

                    case 2:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowLeft.png");
                        break;
                    //North;

                    case 3:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowDown.png");
                        break;
                    //West;

                    default:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowRight.png");
                        break;
                    //South;

                }
                break;
            //West;

            default:
                switch (sum % 4) {
                    case 1:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowRight.png");
                        break;
                    //East;

                    case 2:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowTop.png");
                        break;
                    //North;

                    case 3:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowLeft.png");
                        break;
                    //West;

                    default:
                        arrowIcon = new ImageIcon("src/window/Arrow/ArrowDown.png");
                        break;
                    //South;

                }
                break;
        }


        return arrowIcon;
    }
    //---------------------not the first game--------------------
    public String getFirstHost() {
        int sum;

        try {
            sum = networkWindow.getsum();
        } catch (NullPointerException e) {
            System.out.println("The game hasn't started yet, no host, waiting for throwing dice!");
            return null;
        }
        System.out.println("sum:"+sum);
        switch (sum % 4) {
            case 1:
                host="East";
                break;
            case 2:
                host = "North";
                break;
            case 3:
                host="West";
                break;
            default:
                host="South";
                break;
        }

        return host;
    }

    // Store the chosen host in the first round;
}
