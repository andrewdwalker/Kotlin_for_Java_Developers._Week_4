package rationals

import java.math.BigInteger


class Rational(numerator : BigInteger, denominator : BigInteger) : Comparable<Rational>
{
    private var _numerator : BigInteger
    private var _denominator : BigInteger
    init{
        var gcd = numerator.gcd(denominator)
        _numerator = numerator / gcd
        _denominator = denominator / gcd
        if (_denominator < 0.toBigInteger() )
        {
            _denominator = -_denominator
            _numerator = -_numerator
        }
    }

    
    // overloading plus function
    operator fun div(other: Rational) : Rational {
        //return Point(x + p.x, y + p.y)
        return Rational(_numerator * other._denominator,
                _denominator * other._numerator)
    }

    operator fun plus(other: Rational) : Rational {
        //return Point(x + p.x, y + p.y)
        return Rational(_numerator * other._denominator +
                other._numerator * _denominator,
                _denominator * other._denominator)
    }

    operator fun minus(other: Rational) : Rational {
        return plus(-other)

    }

    operator fun times(other: Rational) : Rational {
        //return Point(x + p.x, y + p.y)
        return Rational(_numerator * other._numerator,
                _denominator * other._denominator)
    }

    operator fun unaryMinus() : Rational {
        return Rational(-_numerator, _denominator)
    }

    override fun toString(): String {
        return if (this._denominator == 1.toBigInteger())
            _numerator.toString()
        else {
            "$_numerator/$_denominator"
        }
    }
    override fun equals(other: Any?): Boolean {
        if (other == null ||
                other !is Rational ||
                _numerator != other._numerator || _denominator != other._denominator) return false

        return true
    }

    override fun compareTo(other: Rational): Int {
        //var leftSide = Rational(_numerator * other._denominator,
        //        _denominator * other._denominator)
        //var rightSide = Rational(other._numerator * _denominator,
        //        other._denominator * _denominator)
        //return (leftSide._numerator - rightSide._numerator).toInt() // this won't work due to roundoff error
        var leftSide = _numerator * other._denominator
        var rightSide = other._numerator * _denominator
        if  ((leftSide - rightSide) > 0.toBigInteger())
        {
            return 1
        }
        else if ((leftSide - rightSide) < 0.toBigInteger())
            return -1
        else
            return 0
    }

    override fun hashCode(): Int {
        var result = _numerator.hashCode()
        result = 31 * result + _denominator.hashCode()
        return result
    }

    //operator fun rangeTo(other: Rational)

/*


 */
}
infix fun Int.divBy(c: Int): Rational {
    return Rational(this.toBigInteger(), c.toBigInteger())
}

infix fun Long.divBy(c: Long): Rational {
    return Rational(this.toBigInteger(), c.toBigInteger())
}
infix fun BigInteger.divBy(c: BigInteger): Rational {
    return Rational(this, c)
}

fun String.toRational() : Rational {
    if (this.contains("/"))
    {
        var numbers = this.split("/")
        val numerator = numbers[0].toBigInteger()
        val denominator = numbers[1].toBigInteger()
        return Rational(numerator,denominator)
    }
    else
    {
        return Rational(this.toBigInteger(),1.toBigInteger())
    }
}
fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}