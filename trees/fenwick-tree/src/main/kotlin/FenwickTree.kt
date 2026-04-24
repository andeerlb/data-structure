// LSB stands for "least significant bit". It is a common operation in Fenwick Trees to find the least significant bit of an integer,
// which can be done using the expression `i and -i`. 
// This gives us the value of the least significant bit that is set to 1 in the binary representation of `i`.
// The lsb is the rightmost bit that is set to 1 in the binary representation of a number. 
// For example, if `i` is 6 (which is 110 in binary), then `lsb(6)` would return 2 (which is 10 in binary), 
// because the least significant bit that is set to 1 in 6 is the second bit from the right.
private fun lsb(i: Int): Int = i and -i

class FenwickTree(values: IntArray) {

    private val size = values.size
    private val tree = IntArray(size + 1)
    private val data = values.copyOf()

    init {
        for (i in values.indices) {
            add(i, values[i])
        }
    }

    fun query(r: Int): Int {
        var sum = 0
        var i = r + 1

        // We traverse the tree from the given index upwards, adding the values at each node to the sum until we reach the root (index 0).
        // The loop continues until `i` becomes 0, which means we have traversed all the relevant nodes in the Fenwick Tree.
        // The `lsb(i)` function is used to determine how much to move up the tree at each step, effectively skipping over nodes that are not relevant to the current query.
        while (i > 0) {
            // We add the value at the current index `i` in the Fenwick Tree to our running total `sum`.
            sum += tree[i]
            // We then move up the tree by subtracting the least significant bit from `i`, which allows us to skip over nodes that are not relevant to the current query.
            i -= lsb(i)
        }

        return sum
    }

    // The `query(l: Int, r: Int)` function is designed to return the sum of the elements in the original array from index `l` to index `r`, inclusive.
    fun query(l: Int, r: Int): Int {
        // The function first checks if the left index `l` is less than or equal to the right index `r`. 
        // If this condition is not met, it throws an IllegalArgumentException with the message "Left index must be <= right index".
        require(l <= r) { "Left index must be <= right index" }
        // The function then calculates the sum from index 0 to index `r` by calling the `query(r)` function, and subtracts the sum from index 0 to index `l - 1` (if `l` is greater than 0) to get the sum of the elements from index `l` to index `r`.
        return query(r) - if (l > 0) query(l - 1) else 0
    }

    fun add(index: Int, delta: Int) {
        var i = index + 1

        while (i <= size) {
            tree[i] += delta
            i += lsb(i)
        }
    }

    fun update(index: Int, value: Int) {
        val delta = value - data[index]
        data[index] = value
        add(index, delta)
    }
}