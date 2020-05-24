package leetcode.design_items;

/**
 *  题目：
 *      动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 *      在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，
 *      或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。
 *      请创建适用于这个系统的数据结构，实现各种操作方法，
 *      比如enqueue、dequeueAny、dequeueDog和dequeueCat。
 *      允许使用Java内置的LinkedList数据结构。
 *
 *      enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 *      dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 *
 *      收纳所的最大容量为20000
 *  思路：
 *      用两个队列分别存储猫和狗对象。当取所有最老的时候，从中各取出一个比较其编号，编号越小表示越老
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf obj = new AnimalShelf();
 * obj.enqueue(animal);
 * int[] param_2 = obj.dequeueAny();
 * int[] param_3 = obj.dequeueDog();
 * int[] param_4 = obj.dequeueCat();
 */
class AnimalShelf {

    private Queue<int[]> dogQue = null;
    private Queue<int[]> catQue = null;
    private int[] temp = new int[]{-1, -1};

    public AnimalShelf() {
        dogQue = new LinkedList<int[]>();
        catQue = new LinkedList<int[]>();
    }

    // animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
    public void enqueue(int[] animal) {
        if(animal[1] == 0){
            catQue.add(animal);
        }else{
            dogQue.add(animal);
        }
    }

    // 返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]
    public int[] dequeueAny() {
        if(catQue.isEmpty() && dogQue.isEmpty()){
            return temp;
        }else if(catQue.isEmpty()){
            return dogQue.poll();
        }else if(dogQue.isEmpty()){
            return catQue.poll();
        }else{
            int n1 = catQue.peek()[0];
            int n2 = dogQue.peek()[0];
            if(n1 < n2){
                return catQue.poll();
            }else{
                return dogQue.poll();
            }
        }
    }

    // 返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]
    public int[] dequeueDog() {
        if(dogQue.isEmpty()){
            return temp;
        }else{
            return dogQue.poll();
        }
    }

    // 返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]
    public int[] dequeueCat() {
        if(catQue.isEmpty()){
            return temp;
        }else{
            return catQue.poll();
        }
    }
}
public class Animal_shelter {
}
