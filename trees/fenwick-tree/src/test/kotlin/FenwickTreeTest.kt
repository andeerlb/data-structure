import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FenwickTreeTest {

    @Test
    fun `should build correctly and return prefix sums`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val ft = FenwickTree(arr)

        assertEquals(1, ft.query(0))
        assertEquals(3, ft.query(1))  // 1 + 2
        assertEquals(6, ft.query(2))  // 1 + 2 + 3
        assertEquals(15, ft.query(4)) // full sum
    }

    @Test
    fun `should return correct range sums`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val ft = FenwickTree(arr)

        assertEquals(6, ft.query(0, 2)) // 1+2+3
        assertEquals(9, ft.query(1, 3)) // 2+3+4
        assertEquals(5, ft.query(4, 4)) // single element
    }

    @Test
    fun `should update values correctly`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val ft = FenwickTree(arr)

        ft.update(2, 10) // change index 2 (3 -> 10)

        assertEquals(13, ft.query(2)) // 1 + 2 + 10
        assertEquals(22, ft.query(4)) // total updated sum
    }

    @Test
    fun `should handle multiple updates`() {
        val arr = intArrayOf(1, 1, 1, 1, 1)
        val ft = FenwickTree(arr)

        ft.update(0, 5)
        ft.update(4, 10)

        assertEquals(5, ft.query(0))
        assertEquals(7, ft.query(2))  // fixed
        assertEquals(18, ft.query(4))
    }

    @Test
    fun `should handle negative numbers`() {
        val arr = intArrayOf(5, -2, 3, -1, 2)
        val ft = FenwickTree(arr)

        assertEquals(5, ft.query(0))
        assertEquals(3, ft.query(1)) // 5 + (-2)
        assertEquals(7, ft.query(4)) // total sum
    }

    @Test
    fun `should throw on invalid range`() {
        val arr = intArrayOf(1, 2, 3)
        val ft = FenwickTree(arr)

        assertThrows(IllegalArgumentException::class.java) {
            ft.query(2, 1)
        }
    }
}