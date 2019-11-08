package blackjack;

import java.util.Scanner;

public class PlayingGame {
    private Deck deck;
    private Player player;
   
    public PlayingGame(Player player) {
        this.player = player;
    }
    //开始玩家和庄家的一轮对局
    public void playing() {
        //initialization
        //banker = new Banker();
        Hand playerhand = new Hand();
        Hand bankerhand = new Hand();

        //洗牌
        deck = new Deck();
        deck.shuffle();

        //押注
        Scanner scanner = new Scanner(System.in);
        System.out.println("Now,the balance you hava: " + player.getAccount());
        System.out.println("please enter the bet: ");
        int bet = scanner.nextInt();
        while (true) {
            if (bet > player.getAccount()) {
                System.out.println("You don't have enough money!");
                System.out.println("Please enter the bet again:");
                bet = scanner.nextInt();
            } else
                break;
        }
        player.setAccount(-bet);

        //发牌
        int index = 0;
        playerhand.addCard(deck.getCards(index++));
        playerhand.addCard(deck.getCards(index++));
        String card = deck.getCards(index++);
        bankerhand.addCard(card);
        System.out.println("Banker cards:\n[" + card + ", ** ]");
        System.out.println("Banker Points: " + bankerhand.getTotalPoint());
        bankerhand.addCard(deck.getCards(index++));
        System.out.println("--------------------------");
        System.out.println("\nPlayer cards:");
        playerhand.showCards();
        System.out.println("Player Points: " + playerhand.getTotalPoint());

     /*  int index =0;
       playerhand.addCard("♠10");
       playerhand.addCard("♠A");
       bankerhand.addCard("♥10");
       bankerhand.addCard("♥7");
        bankerhand.addCard("♥4");
        playerhand.showCards();
       System.out.println("Banker Points: " + bankerhand.getTotalPoint());
        bankerhand.showCards();
       System.out.println("Player Points: " + playerhand.getTotalPoint());*/
        //进行游戏
        while (true) {
            //player turn
            System.out.println("1.Hit");
            System.out.println("2.Stand");
            int op = scanner.nextInt();
            if (op == 1) {
                playerhand.addCard(deck.getCards(index++));
                playerhand.showCards();
                System.out.println("TotalPoint: " + playerhand.getTotalPoint());
                if (playerhand.getBust()) {
                    System.out.println("You lose!");
                    break;
                }
            }
            //banker turn
            else if (op == 2) {
                System.out.println("--------------------------");
                System.out.println("Banker's turn");
                bankerhand.showCards();
                System.out.println("Banker Points: " + bankerhand.getTotalPoint());
                while (bankerhand.getTotalPoint() < 17) {
                    bankerhand.addCard(deck.getCards(index++));
                    bankerhand.showCards();
                    System.out.println("Banker Points: " + bankerhand.getTotalPoint());
                    if (bankerhand.getTotalPoint() >= 17 && bankerhand.getTotalPoint() <= 21)
                        break;
                    else if (bankerhand.getTotalPoint() > 21) {
                        System.out.println("You wins!");
                        player.setAccount(bet * 2);
                        break;
                    }
                }
                break;
            } else
                System.out.println("Please enter 1 or 2");
        }
        //judge
        playerhand.judgeBlackJack();
        bankerhand.judgeBlackJack();
        if (!playerhand.getBust() && !bankerhand.getBust()) { //双方都没有爆
            //玩家点数大
            if (playerhand.getTotalPoint() > bankerhand.getTotalPoint()) {
                System.out.println("You wins");
                //玩家是否为黑杰克
                if (playerhand.getBlackJack() != 0)
                    player.setAccount((int) (bet * 2.5));
                else
                    player.setAccount(bet * 2);

            }//庄家点数大
            else if (playerhand.getTotalPoint() < bankerhand.getTotalPoint()) {
                System.out.println("You lose");
                //庄家是否为黑杰克
                if (bankerhand.getBlackJack() != 0)
                    player.setAccount((int) (-bet * 0.5));

            }//点数相同
            else if (playerhand.getTotalPoint() == bankerhand.getTotalPoint()) {
                //点数都为21时，判断是否为黑杰克，并计较黑杰克的大小
                if (playerhand.getTotalPoint() == 21) {
                    if(playerhand.getCardAmount()!= 2 &&  bankerhand.getCardAmount()!=2){
                        if(playerhand.getCardAmount() > bankerhand.getCardAmount()){
                            System.out.println("You wins");
                            player.setAccount(bet * 2);
                        }
                        else if (playerhand.getCardAmount() == bankerhand.getCardAmount()){
                            System.out.println("It's a draw");
                            player.setAccount(bet);
                        }
                        else {
                            System.out.println("You lose");
                        }
                    }
                    else if (playerhand.getBlackJack() > bankerhand.getBlackJack()) {
                        System.out.println("You wins");
                        player.setAccount((int) (bet * 2.5));
                    } else if (playerhand.getBlackJack() < bankerhand.getBlackJack()) {
                        System.out.println("You lose");
                        player.setAccount((int) (-bet * 0.5));
                    } else {
                        System.out.println("It's a draw");
                        player.setAccount(bet);
                    }
                } else {
                    System.out.println("It's a draw");
                    player.setAccount(bet);
                }
            }
        }
        System.out.println("You account:" + player.getAccount());
    }
}
