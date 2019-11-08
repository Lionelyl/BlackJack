package blackjack;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

public class BlackJackGame {

    public static void main(String[] args) {
         Player player = new Player();

        System.out.println("--------------------------------------\n");
        System.out.println("Welcome to BlackJack Game!\n");
        while (true) {
            if(player.getAccount()<=0){
                System.out.println("Game over");
                break;
            }
            System.out.println("--------------------------");
            System.out.println("1.继续游戏\n");
            System.out.println("2.退出游戏\n");
            //System.out.println("--------------------------------------\n");
            Scanner scanner = new Scanner(System.in);
            int op = scanner.nextInt();
            if(op == 1){
                PlayingGame playingGame = new PlayingGame(player);
                playingGame.playing();
            }
            else
                System.out.println("输入q结束游戏！");
        }

    }
}
