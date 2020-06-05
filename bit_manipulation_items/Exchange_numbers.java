package leetcode.bit_manipulation_items;

/**
 *  题意：
 *      编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 *      numbers.length == 2
 *  思路1：
 *      加法。
 *      求得两数A，B之和赋值给A，再用A（代表两数和）减去B（代表原B）赋值给B，此时B值即为原数A的值
 *      再用A（代表两数和）减去B（代表原A）赋值给A，此时A即为原数B的值
 *      a = a + b
 *      b = a - b
 *      a = a - b
 *  思路2：
 *      异或。
 *      a = a ^ b
 *      b = a ^ b
 *      a = a ^ b
 */
class Exchange_numbers2 {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }
}
class Exchange_numbers1 {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] + numbers[1];
        numbers[1] = numbers[0] - numbers[1];
        numbers[0] = numbers[0] - numbers[1];
        return numbers;
    }
}
public class Exchange_numbers {
}
