package board

import java.lang.IllegalArgumentException

open class SquareBoardImpl(widthArg : Int) : SquareBoard {

    private val _width : Int = widthArg
    protected var _list : Collection<Cell>
    init {
        _list = List<Cell>(widthArg*widthArg) {it ->
            Cell(it / widthArg + 1, it % widthArg + 1 )
        }
    }
    override val width: Int
        get() = _width //To change initializer of created properties use File | Settings | File Templates.

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return _list.firstOrNull() { it.i == i && it.j == j }
    }
    override fun getCell(i: Int, j: Int): Cell {
        if (i > width || j > width || i < 1 || j < 1)
            throw IllegalArgumentException()
        return _list.first() { it.i == i && it.j == j }
    }

    override fun getAllCells(): Collection<Cell> {
        return _list
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        var mutableList : MutableList<Cell> = mutableListOf<Cell>()
        for (j in jRange)
        {
            var temp = getCellOrNull(i,j)
            if (temp != null)
            {
                mutableList.add(temp)
            }
        }
        return mutableList.toList()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        var mutableList: MutableList<Cell> = mutableListOf<Cell>()
        for (i in iRange) {
            var temp = getCellOrNull(i, j)
            if (temp != null) {
                mutableList.add(temp)
            }
        }
        return mutableList.toList()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? =
        when (direction) {
            Direction.UP -> getCellOrNull(this.i-1,j)
            Direction.DOWN -> getCellOrNull(this.i+1,j)
            Direction.RIGHT -> getCellOrNull(this.i,j+1)
            Direction.LEFT -> getCellOrNull(i,j-1)
        }

}