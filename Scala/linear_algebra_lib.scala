import scala.math.sqrt
import scala.math.acos
import scala.math.toDegrees
import scala.util.Try

// Types
type Coordinate = Double
type Scalar = Double

// Vector Case Class
case class Vector(coordinates: List[Coordinate]) {

    def +(v2: Vector) : Vector = 
        Vector((coordinates, v2.coordinates).zipped.map(_ + _))
    def -(v2: Vector) : Vector = 
        Vector((coordinates, v2.coordinates).zipped.map(_ - _))
    def *(sc: Scalar) : Vector = 
        Vector(coordinates.map(_ * sc))
    def magnitude() : Double =
        sqrt(coordinates.map(x => x * x).sum)
    def normalized() : Option[Vector] = 
        if (magnitude == 0) None else Some(this * (1.0 / magnitude))
    def dot(v2: Vector) : Double = 
       (coordinates, v2.coordinates).zipped.map(_ * _).sum
    def angle(v2: Vector, in_degrees : Boolean = false) : Option[Double] =
        (normalized, v2.normalized) match {
            case (None, _) | (_, None) => None
            case (Some(m1), Some(m2)) =>
                val rad = acos(m1 dot m2)
                if (in_degrees) Some(toDegrees(rad)) else Some(rad)    
        }   
}

// Scalar Operations (for convenience)
implicit def scalarVectorOperations(sc: Scalar) = new {
    def *(v: Vector) : Vector = Vector(v.coordinates.map(_ * sc))
}

/** 
Some Tests:

val v1 = Vector(List(8.218, -9.341))
val v2 = Vector(List(-1.129, 2.111))
v1 + v2

val v3 = Vector(List(7.119, 8.215))
val v4 = Vector(List(-8.223, 0.878))
v3 - v4

val sc1 = 7.41
val v5 = Vector(List(1.671, -1.012, -0.318))
sc1 * v5

val v6 = Vector(List(-0.221, 7.437))
v6.magnitude

val v7 = Vector(List(8.813, -1.331, -6.247))
v7.magnitude

val v8 = Vector(List(5.581, -2.136))
v8.direction

val v9 = Vector(List(1.996, 3.108, -4.554))
v9.direction

val v10 = Vector(List(0, 0))
v10.magnitude
v10.direction

val v11 = Vector(List(7.887, 4.138))
val v12 = Vector(List(-8.802, 6.776))
v11 dot v12
val v13 = Vector(List(-5.955, -4.904, -1.874))
val v14 = Vector(List(-4.496, -8.755, 7.103))
v13 dot v14

val va = Vector(List(3.183, -7.627))
val vb = Vector(List(-2.668, 5.319))
va angle vb

val v15 = Vector(List(7.35, 0.221, 5.188))
val v16 = Vector(List(2.751, 8.259, 3.985))
v15.angle(v16, true)
**/

