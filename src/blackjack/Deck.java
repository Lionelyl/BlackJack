package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Deck {
    private ArrayList<String> color;
    private ArrayList<String> number;
    private HashMap<Integer,String> map;
    private ArrayList<Integer> cards;

    //初始化牌
   public Deck(){
        color = new ArrayList<String>();
        Collections.addAll(color,"♠","♣","♦","♥");

        number = new ArrayList<String>();
        Collections.addAll(number,"A","2","3","4","5","6","7","8","9","10","J","Q","K");

        map = new HashMap<Integer, String>();
        int index = 0;
        for(String acolor:color)
            for(String anumber:number){
                map.put(index++,acolor+anumber);
            }
        cards = new ArrayList<Integer>();
            for(int i = 0; i < 52; i++){
                cards.add(i);
            }
   }
   //洗牌
   public void shuffle() {
       Collections.shuffle(cards);
   }
   //获取一张牌
   public String getCards(int i){
       return map.get(cards.get(i));
   }

}
