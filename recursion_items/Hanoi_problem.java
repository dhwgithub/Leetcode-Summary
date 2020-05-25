package leetcode.recursion_items;

import java.util.List;

/**
 *  题目：
 *      在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 *      一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 *          (1) 每次只能移动一个盘子;
 *          (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 *          (3) 盘子只能叠在比它大的盘子上。
 *      请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *      你需要原地修改栈。A中盘子的数目不大于14个。
 *  思路：
 *      若要将A全部移到C，首先需要将A上的n-1个通过C移到B上，然后将A上第n个移到C；
 *      然后将B中上面n-1个通过C移到A上，再将B中第n个移到C上；
 *      以此类推
 *      当A中只有一个时，直接移动即可
 */
class Hanoi_problem1 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);  // 将A中上A.size()通过B移动到C上
    }

    public void move(int n, List<Integer> A, List<Integer> B, List<Integer> C){
        if(n == 1){
            C.add(A.remove(A.size()-1));
            return ;
        }
        move(n-1, A, C, B);
        C.add(A.remove(A.size()-1));
        move(n-1, B, A, C);
    }
}
public class Hanoi_problem {
}
