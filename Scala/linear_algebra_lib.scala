import scala.math.sqrt
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
    def direction() : Option[Vector] = 
        magnitude match {
            case 0 => None
            case m => Some(this * (1.0 / magnitude))
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
**/

