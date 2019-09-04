package board

class GameBoardImpl<T>(widthArg: Int) : SquareBoardImpl(widthArg), GameBoard<T> {

    var mapOfCells = HashMap<Cell, T?>()

    init {
        _list.forEach { cell -> set(cell, null) }

    }
    override fun get(cell: Cell): T? {

        if (mapOfCells.contains(cell))
            return mapOfCells[cell]
       return null
    }

    override fun set(cell: Cell, value: T?) {
        mapOfCells[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        //return mapOfCells.filter { (_,y)-> y == predicate }.keys
        return mapOfCells.filterValues(predicate).keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return mapOfCells.filterValues(predicate).keys.firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return mapOfCells.values.any(predicate)
        //var temp = find(predicate)
        //return temp != null
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return mapOfCells.values.all(predicate)
        //var temp = filter(predicate)
        //return (temp.count() == mapOfCells.values.count())

    }
}