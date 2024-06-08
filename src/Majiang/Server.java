package Majiang;

import GameTable.ShuffleMajiang;
import Player.NetworkPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int PORT = 12345;
    private static final int MAX_CLIENTS = 4;
    private static List<Socket> socketList = new ArrayList<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
    private static List<NetworkPlayer> networkPlayers = new ArrayList<>(4);
    private static int currentClient;
    private static AtomicInteger pengCount = new AtomicInteger(0);
    private static AtomicInteger gangCount = new AtomicInteger(0);
    private static AtomicInteger chiCount = new AtomicInteger(0);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        List<ClientHandler> clients = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started, listening on port " + PORT);

            int clientCount = 0;
            while (clientCount < MAX_CLIENTS) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + (clientCount + 1) + " connected: " + clientSocket.getRemoteSocketAddress());

                // Create a processor for each client and assign an identity
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientCount + 1);
                clients.add(clientHandler);
                executorService.submit(clientHandler);

                clientCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final int clientId;

        public ClientHandler(Socket clientSocket, int clientId) {
            this.clientSocket = clientSocket;
            this.clientId = clientId;
            socketList.add(clientSocket);
        }

        @Override
        public void run() {
            try {
                // This is where the client's communication logic is handled
                System.out.println("Client " + clientId + " is being handled by thread " + Thread.currentThread().getName());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                networkPlayers.add(new NetworkPlayer(this.clientId));
                System.out.println("Client " + clientId + " connected.");
                out.println("Client-" + this.clientId);

                String input;
                if ((input = in.readLine())!=null){
                    System.out.println(input);
                    if (input.startsWith("Avatar")){
                        int clientId = Integer.parseInt(input.split("-")[1]);
                        for (NetworkPlayer networkPlayer : networkPlayers) {
                            if (networkPlayer.clientId == clientId){
                                System.out.println("Client"+clientId+"-avatar-"+input.split("-")[2]);
                                networkPlayer.avatar = input.split("-")[2];
                            }
                        }
                    }
                }

                if (this.clientId != MAX_CLIENTS) {
                    out.println("Waiting-Please Waiting!");
                }

                if(this.clientId == MAX_CLIENTS){
                    //Decide who shakes the dice
                    int sum = new Random().nextInt(12) + 2;
                    int host = sum%4==0?4:sum%4;
                    sendToAll("Host-"+host);
                    networkPlayers.get((sum%4==0?3:sum%4-1)).host = true;
                    currentClient = host;

                    //deal cards
                    shuffleMajiang();
                    System.out.println(ShuffleMajiang.maJiangs);
                    haveFirstBoard();
                    for (NetworkPlayer networkPlayer : networkPlayers) {
                        sendToAll("Majiangs&Avatar-" + networkPlayer.clientId + "-" + networkPlayer.playerMajiangs.size() + "-" + networkPlayer.playerMajiangs+ "-" + networkPlayer.avatar);
                    }
                }

                while ((input = in.readLine())!=null){
                    System.out.println(input);

                    if (input.startsWith("Discard")){
                        Integer clientId = Integer.parseInt(input.split("-")[1]);
                        Integer card = Integer.parseInt(input.split("-")[2]);
                        networkPlayers.get(clientId-1).playerMajiangs.remove(card);

                        //Notify the client that the user has played
                        sendToAll(input + "-" + networkPlayers.get(clientId-1).playerMajiangs.size());
                        continue;
                    }

                    if (input.startsWith("Peng")){
                        //peng
                        int clientId1 = Integer.parseInt(input.split("-")[1]);
                        Integer card = Integer.parseInt(input.split("-")[2]);
                        Boolean isPeng = Boolean.valueOf(input.split("-")[3]);

                        if (isPeng){
                            System.out.println(clientId1 + " Can Peng");

                            //chi, peng, gang
                            //switch user
                            currentClient = clientId1;
                            sendToAll("Peng-"+currentClient+"-"+card);

                            continue;
                        }else {
                            pengCount.incrementAndGet();
                        }

                    }

                    if (input.startsWith("Gang")){
                        //gang
                        int clientId1 = Integer.parseInt(input.split("-")[1]);
                        Integer card = Integer.parseInt(input.split("-")[2]);
                        Boolean isGang = Boolean.valueOf(input.split("-")[3]);

                        if (isGang){
                            System.out.println(clientId1 + " Can Gang");
                            //switch user
                            currentClient = clientId1;
                            sendToAll("Gang-"+currentClient+"-"+card);

                            continue;
                        }else {
                            gangCount.incrementAndGet();
                        }
                    }

                    if (input.startsWith("Chi")){
                        //eat
                        int clientId1 = Integer.parseInt(input.split("-")[1]);
                        Integer card = Integer.parseInt(input.split("-")[2]);
                        Boolean isChi = Boolean.valueOf(input.split("-")[3]);

                        if (isChi){
                            System.out.println(clientId1 + " Can Chi");

                            //switch user
                            currentClient = clientId1;
                            sendToAll("Chi-"+currentClient+"-"+card);

                        }else {
                            chiCount.incrementAndGet();
                        }
                    }


                    if (input.startsWith("Finish")){
                        pengCount.set(0);
                        gangCount.set(0);
                        chiCount.set(0);

                        // Clear the card of the person who peng or gang
                        int clientId = Integer.parseInt(input.split("-")[2]);
                        String cards = input.split("-")[3];
                        String[] split = cards.split(",");
                        for (String card : split) {
                            Integer i = Integer.parseInt(card);
                            networkPlayers.get(clientId-1).playerMajiangs.remove(i);
                        }

                        //Deal the cards to the man of the gang
                        if ("Gang".equals(input.split("-")[1])){
                            Integer card = ShuffleMajiang.maJiangs.get(0);
                            ShuffleMajiang.maJiangs.remove(0);
                            NetworkPlayer networkPlayer = networkPlayers.get(clientId - 1);
                            networkPlayer.playerMajiangs.add(card);
                        }

                        Collections.sort(networkPlayers.get(clientId-1).playerMajiangs);
                        sendToAll(input+"-"+currentClient);
                    }

                    if ((pengCount.intValue()==3
                            && gangCount.intValue()==3
                            && chiCount.intValue()==3
                    )
                            || input.startsWith("Skip")) {
                        pengCount.set(0);
                        gangCount.set(0);
                        chiCount.set(0);

                        //switch user
                        if (input.startsWith("Skip")){
                            //User whose operation is skipped
                            String clientId = input.split("-")[1];
                            if (currentClient != Integer.parseInt(clientId)){
                                currentClient = currentClient+1>4?1:currentClient+1;
                            }
                        }else {
                            currentClient = currentClient+1>4?1:currentClient+1;
                        }


                        System.out.println("Next is "+currentClient);

                        //deal tile
                        Integer card = ShuffleMajiang.maJiangs.get(0);
                        ShuffleMajiang.maJiangs.remove(0);
                        Collections.sort(networkPlayers.get(clientId-1).playerMajiangs);
                        NetworkPlayer networkPlayer = networkPlayers.get(currentClient - 1);
                        networkPlayer.playerMajiangs.add(card);

                        sendToAll("Current-"+currentClient+"-"+networkPlayer.playerMajiangs+"-"+networkPlayer.playerMajiangs.size());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendToAll(String msg){
        System.out.println("SendToAll-"+msg);
        for (Socket socket : socketList) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void shuffleMajiang() throws InterruptedException {
        //create majiangs and add them into maJiangs
        ShuffleMajiang.maJiangs = new ArrayList<>(132);
        for (int i = 11; i < 47; i++) {
            // 4 packs of Majiangs
            for ( int j = 1; j <= 4; j++) {
                // don't have the cards of 20, 30, 40
                if (i == 20 || i == 30 || i == 40){
                    break;
                }
                // add the card to the maJiangs
                ShuffleMajiang.maJiangs.add(i);
            }
        }

        // shuffle the cards that have been created in order
        Collections.shuffle(ShuffleMajiang.maJiangs);
    }

    public static void haveFirstBoard(){
        // 4 turns in total
        // the first 3 turns will get 12 cards each
        for (int i = 0; i < 3; i++) {
            // player, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                networkPlayers.get(0).gainMajiang();
            }
            // computer1, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                networkPlayers.get(1).gainMajiang();
            }
            // computer2, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                networkPlayers.get(2).gainMajiang();
            }
            // computer3, get 4 cards at a time
            for (int j = 0; j < 4; j++) {
                networkPlayers.get(3).gainMajiang();
            }
        }
        // the last turn will get one more card each
        for (int i = 0; i < 4; i++) {
            networkPlayers.get(i).gainMajiang();
        }

        for (NetworkPlayer networkPlayer : networkPlayers) {
            if (networkPlayer.host)networkPlayer.gainMajiang();
            Collections.sort(networkPlayer.getPlayerMajiangs());
            System.out.println(networkPlayer.getPlayerMajiangs());
        }
    }


}
