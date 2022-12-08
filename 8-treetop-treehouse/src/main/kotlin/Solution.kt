import java.util.concurrent.atomic.AtomicInteger

val treeId = AtomicInteger(0)

fun treehouse1(input: String): Int {
    val visibleTrees = emptyList<Tree>().toMutableList()
    val trees = parse(input)
    val transposed = transpose(trees)

    visibleTrees.addAll(trees.first())
    visibleTrees.addAll(transposed.first())
    visibleTrees.addAll(trees.last())
    visibleTrees.addAll(transposed.last())

    checkVisibility(trees, visibleTrees)
    checkVisibility(transposed, visibleTrees)
    checkVisibility(reverseInner(trees), visibleTrees)
    checkVisibility(reverseInner(transposed), visibleTrees)

    return visibleTrees.distinct().count()
}

fun treehouse2(input: String): Int {
    val trees = parse(input)

    return trees.flatten()
        .maxOfOrNull { checkTreeVisibility(it, trees) } ?: 0
}

fun parse(input: String): List<List<Tree>> {
    val row = AtomicInteger(0)
    return input.split("\n")
        .map {
            val currentRow = row.getAndIncrement()
            val col = AtomicInteger(0)
            it.toCharArray()
                .map { c -> c.toString() }
                .map { s -> Tree(s.toInt(), treeId.incrementAndGet(), currentRow, col.getAndIncrement()) }
        }
}

fun <T> transpose(source: List<List<T>>): List<List<T>> {
    val transposed = mutableListOf<List<T>>()

    source[0].indices.map {
        transposed.add(
            source.map { inner -> inner[it] }
        )
    }

    return transposed.toList()
}

fun <T> reverseInner(source: List<List<T>>): List<List<T>> {
    val mutable = mutableListOf<List<T>>()

    source.forEach {
        mutable.add(it.reversed())
    }

    return mutable.toList()
}

fun checkVisibility(input: List<List<Tree>>, result: MutableList<Tree>) {
    input.forEach {
        it.reduce { acc, tree ->
            if (tree.height > acc.height) {
                result.add(tree)
                return@reduce tree
            }
            return@reduce acc
        }
    }
}

fun checkTreeVisibility(tree: Tree, trees: List<List<Tree>>): Int {
    val up = checkUpwardVisibility(tree, trees)
    val down = checkDownwardVisibility( tree, trees)
    val left = checkLeftVisibility(tree, trees)
    val right = checkRightVisibility(tree, trees)

    return up * down * left * right
}

fun checkUpwardVisibility(tree: Tree, trees: List<List<Tree>>): Int {
    val treesInColuwm = trees.map { it[tree.col] }

    return if(tree.row == 0) {
        0
    } else {
        val visibility = AtomicInteger(0)
        val treesAbove = treesInColuwm.filter { it.row < tree.row }.reversed()

        for(t in treesAbove) {
            visibility.incrementAndGet()
            if (t.height >= tree.height) {
                break
            }
        }

        visibility.get()
    }
}

fun checkDownwardVisibility(tree: Tree, trees: List<List<Tree>>): Int {
    val treesInColuwm = trees.map { it[tree.col] }

    return if(tree.col == trees.size - 1) {
        0
    } else {
        val visibility = AtomicInteger(0)
        val treesAbove = treesInColuwm.filter { it.row > tree.row }

        for(t in treesAbove) {
            visibility.incrementAndGet()
            if (t.height >= tree.height) {
                break
            }
        }

        visibility.get()
    }
}

fun checkLeftVisibility(tree: Tree, trees: List<List<Tree>>): Int {
    val treesInColuwm = trees[tree.row]

    return if(tree.col == 0) {
        0
    } else {
        val visibility = AtomicInteger(0)
        val treesAbove = treesInColuwm.filter { it.col < tree.col }.reversed()

        for(t in treesAbove) {
            visibility.incrementAndGet()
            if (t.height >= tree.height) {
                break
            }
        }

        visibility.get()
    }
}

fun checkRightVisibility(tree: Tree, trees: List<List<Tree>>): Int {
    val treesInColuwm = trees[tree.row]

    return if(tree.col == trees[tree.row].size - 1) {
        0
    } else {
        val visibility = AtomicInteger(0)
        val treesAbove = treesInColuwm.filter { it.col > tree.col }

        for(t in treesAbove) {
            visibility.incrementAndGet()
            if (t.height >= tree.height) {
                break
            }
        }

        visibility.get()
    }
}

data class Tree(val height: Int, val treeId: Int, val row: Int, val col: Int)
