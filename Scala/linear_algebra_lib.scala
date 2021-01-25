// Types
type Coordinate = Double
type Scalar = Double

// Vector Class
case class Vector(coordinates: List[Coordinate]) 

// Generic function to operate on two zipped Vectors
def vectorOp(v1: Vector, v2: Vector, f: (Coordinate, Coordinate) => Coordinate) = 
    Vector((v1.coordinates, v2.coordinates).zipped.map(f)) 

// Vector Operations
implicit def vectorOperations(v1: Vector) = new {
    def +(v2: Vector) : Vector = vectorOp(v1, v2, _ + _)
    def -(v2: Vector) : Vector = vectorOp(v1, v2, _ - _) 
    def *(sc: Scalar) : Vector = Vector(v1.coordinates.map(_ * sc))
}

// Scalar Operations
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
**/

