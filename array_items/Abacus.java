package leetcode.array_items;

/**
 *  题意：
 *      珠玑妙算游戏（the game of master mind）的玩法如下。
 *      计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
 *      例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。
 *      作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；
 *      要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 *      给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，
 *      其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 *      len(solution) = len(guess) = 4
 *      solution和guess仅包含"R","G","B","Y"这4种字符
 *  思路：
 *      定义一个4长度的数组，分别表示每个颜色出现的次数。然后进行比较字符串，猜中的次数直接比较求和即可；
 *      对于伪猜中，遍历一遍颜色组合，统计每个字符出现的次数，并且排除猜中的颜色情况
 *      然后再遍历一遍猜测组合，每当找到一个颜色相同可以消除的，代表是一次伪猜中
 */
class Abacus1 {
    public int[] masterMind(String solution, String guess) {
        int[] num = new int[4];  // RYGB
        int yes = 0;
        int no = 0;
        for(int i=0; i<solution.length(); i++){
            char c = solution.charAt(i);
            char cc = guess.charAt(i);
            if(c == cc){
                yes += 1;
            }else{
                switch(c){
                    case 'R': num[0] += 1; break;
                    case 'Y': num[1] += 1; break;
                    case 'G': num[2] += 1; break;
                    case 'B': num[3] += 1; break;
                }
            }
        }
        for(int i=0; i<guess.length(); i++){
            char c = solution.charAt(i);
            char cc = guess.charAt(i);
            if(c != cc){
                switch(cc){
                    case 'R':
                        if(num[0] > 0){
                            num[0] -= 1;
                            no += 1;
                        }
                        break;
                    case 'Y':
                        if(num[1] > 0){
                            num[1] -= 1;
                            no += 1;
                        }
                        break;
                    case 'G':
                        if(num[2] > 0){
                            num[2] -= 1;
                            no += 1;
                        }
                        break;
                    case 'B':
                        if(num[3] > 0){
                            num[3] -= 1;
                            no += 1;
                        }
                        break;
                }
            }
        }
        return new int[]{yes, no};
    }
}
public class Abacus {
}
