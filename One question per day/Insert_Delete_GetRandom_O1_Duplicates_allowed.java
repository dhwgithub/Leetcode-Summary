/*
设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

注意: 允许出现重复元素。

    insert(val)：向集合中插入元素 val。
    remove(val)：当 val 存在时，从集合中移除一个 val。
    getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。

题解：
	使用列表存元素值，每次添加和删除（与最后一个元素交换值）时可以达到O（1）时间复杂度
	用Set存每个值对应的所有索引（可能重复），则需要Map来完成。
	
	map.get(val) 可以得到当前值的所有索引集合
	set.iterator().next()  可以得到当前索引的其中一个值（用来指示需要删除列表的哪一个值）
 */

class RandomizedCollection {

    int n ;  // 当前集合大小
    HashMap<Integer, Set<Integer>> map; // 值 ： 相等值的索引集合
    ArrayList<Integer> list;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.random = new Random();
        this.map = new HashMap();
        this.n = 0;
        this.list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set set = map.get(val);
        if(set == null)   
            set = new HashSet<>();

        list.add(val);  // 加入值

        set.add(n);  // 添加当前值索引
        map.put(val, set);

        n ++;

        return set.size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int lastIndex = n - 1;  // 得到最后1个值索引

            Set lastset = map.get(list.get(lastIndex));  // 得到最后一个值的索引集合
            Set set = map.get(val);  // 得到当前值的索引集合

            int currIndex = (int) set.iterator().next();  // 得到当前值索引（其中一个）

            // 进行删除操作1：先交换 再 删除列表最后一个元素
            swap(list, currIndex, lastIndex);  // 将当前值与列表最后一个值交换（便于O(1)时间复杂度删除）
            list.remove(n - 1);  // 将其在列表中删除

            // 进行删除操作2：先删除原值对应的索引 再 修改最后一个元素的索引
            set.remove(currIndex);  // 删除原值的一个索引
            if(set.size() == 0)   
                map.remove(val);  // 若不存在当前值的索引，则表示当前值已无，需要删除

            // 修改最后一个值的索引，因为把最后一个值和当前值进行了交换
            lastset.remove(n - 1);
            lastset.add(currIndex);

            n --;
        }else{
            return false;
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(n));
    }
    private void swap(List<Integer> list ,int i,int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */