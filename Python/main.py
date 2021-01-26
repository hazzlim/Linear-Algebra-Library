from math import sqrt, acos, degrees
from decimal import Decimal, getcontext

getcontext().prec = 30


class Vector(object):
    def __init__(self, coordinates):
        self.CANNOT_NORMALIZE_ZERO_VECTOR_MSG = "Cannot normalize the zero vector!"
        try:
            if not coordinates:
                raise ValueError
            self.coordinates = tuple([Decimal(x) for x in coordinates])
            self.dimension = len(coordinates)

        except ValueError:
            raise ValueError('The coordinates must be nonempty')

        except TypeError:
            raise TypeError('The coordinates must be an iterable')

    def __str__(self):
        return 'Vector: {}'.format(self.coordinates)

    def __eq__(self, v):
        return self.coordinates == v.coordinates

    def add_vector(self, other):
        return Vector([x + y for x, y in zip(self.coordinates, other.coordinates)])

    def subtract_vector(self, other):
        return Vector([x - y for x, y in zip(self.coordinates, other.coordinates)])

    def multiply_scalar(self, scalar):
        return Vector([Decimal(scalar) * x for x in self.coordinates])

    def magnitude(self):
        squares_sum = sum([x * x for x in self.coordinates])
        return Decimal.sqrt(squares_sum)

    def normalized(self):
        try:
            return self.multiply_scalar((Decimal(1.0) / self.magnitude()))
        except ZeroDivisionError:
            raise Exception(self.CANNOT_NORMALIZE_ZERO_VECTOR_MSG)

    def dot_product(self, other):
        return sum([x * y for x, y in zip(self.coordinates, other.coordinates)])

    def angle(self, other, in_degrees=False):
        try:
            n1 = self.normalized()
            n2 = other.normalized()
            theta = acos(n1.dot_product(n2))
            if in_degrees:
                theta = degrees(theta)

            return theta
        except Exception as e:
            if str(e) == self.CANNOT_NORMALIZE_ZERO_VECTOR_MSG:
                raise Exception("Cannot calculate angle with the zero vector")
            else:
                raise e


if __name__ == '__main__':
    v1 = Vector([7.887, 4.138])
    v2 = Vector([-8.802, 6.776])
    print(v1.dot_product(v2))
    v3 = Vector([-5.955, -4.904, -1.874])
    v4 = Vector([-4.496, -8.755, 7.103])
    print(v3.dot_product(v4))

    v5 = Vector([3.183, -7.627])
    v6 = Vector([-2.668, 5.319])
    print(v5.angle(v6))

    v15 = Vector([7.35, 0.221, 5.188])
    v16 = Vector([2.751, 8.259, 3.985])
    print(v15.angle(v16))


