class Vector(object):
    def __init__(self, coordinates):
        try:
            if not coordinates:
                raise ValueError
            self.coordinates = tuple(coordinates)
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
        return Vector([scalar * x for x in self.coordinates])


if __name__ == '__main__':
    v1 = Vector([1, 2, 3])
    v2 = Vector([1, 2, 3])
    print(v1.add_vector(v2))

