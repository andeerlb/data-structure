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

        while (i > 0) {
            sum += tree[i]
            i -= lsb(i)
        }

        return sum
    }

    fun query(l: Int, r: Int): Int {
        require(l <= r) { "Left index must be <= right index" }
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