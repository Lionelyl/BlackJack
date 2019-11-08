package blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<String> cards;
    private int totalPoint;
    private int cardAmount;
    private boolean bust = false;
    private int isBlackJack = 0;

    public Hand(){
        cards = new ArrayList<String>();
        cardAmount = 2;
    }

    public int getTotalPoint(){
        totalPoint = 0;
        for(String i : cards){
            char a = i.charAt(1);
            switch (a){
                case 'K':
                    totalPoint +=10;
                    break;
                case 'Q':
                    totalPoint +=10;
                    break;
                case 'J':
                    totalPoint +=10;
                    break;
                case '1':
                    totalPoint +=10;
                    break;
                case 'A'://maybe mistake
                    if(totalPoint + 11 >21)
                        totalPoint +=1;
                    else totalPoint +=11;
                    break;
                    default:
                        totalPoint += (a-'0');
            }
        }
        if(totalPoint >21) bust = true;
        return totalPoint;
    }
    //要牌，加入一张牌到手牌中
    public void addCard(String card){
        cards.add(card);
        cardAmount ++;
    }
    //打印手牌
    public void showCards(){
        System.out.println(cards.toString());
    }
    //判断手牌是否爆掉
    public boolean getBust(){return bust;}
    //判断是否为黑杰克
    public void judgeBlackJack(){
        if(totalPoint == 21 && cards.size()==2){
            char a = cards.get(0).charAt(1);
            char b = cards.get(1).charAt(1);
            if(a == 'A'&& b == 'J' || a =='J' && b == 'A')
                isBlackJack = 3;
            else if(a == 'A'&& b == 'Q' || a =='Q' && b == 'A')
                isBlackJack = 2;
            else
                isBlackJack = 1;

        }
    }
    public int getBlackJack(){ return isBlackJack;}
    public int getCardAmount() { return cardAmount;}

}