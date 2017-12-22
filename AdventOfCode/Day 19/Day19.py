import unittest

def solution1(grid):
    x = get_start_location(grid)
    y = 0
    direction = 's'
    finished = False

    count = 0

    while not finished and count < 1000000:
        print str(x) + ',' + str(y) + ',' + str(direction)
        (x,y,direction) = get_next_location(grid, x, y, direction)
        count += 1

def get_start_location(grid):

    for i in range(0, len(grid[0])):
        if grid[0][i] == '|':
            return i
    return -1

def get_next_location(grid, x, y, direction):
    if grid[y][x] == '|' or grid[y][x] == '-':
        (x,y) = move_straight(x, y, direction)
        return x,y,direction

    if is_letter(grid, x, y):
        # todo: capture letter
        print grid[y][x]
        (x, y) = move_straight(x, y, direction)
        return x, y, direction

    if grid[y][x] == '+':
        direction = turn(grid, x, y, direction)
        (x,y) = move_straight(x, y, direction)
        return x,y,direction

def turn(grid, x, y, direction):
    neighbours = get_neighbours(grid, x, y, direction)

    for n in neighbours:
        if grid[n[1]][n[0]] != '':
            # this is the neighbour
            if n[0] > x:
                return 'e'
            if n[0] < x:
                return 'w'
            if n[1] > y:
                return 's'
            if n[1] < y:
                return 'n'

def get_neighbours(grid, x, y, direction):
    neighbours = []

    if x < len(grid[0]) and direction != 'w':
        neighbours.append((x + 1, y))
    if x - 1 >= 0 and direction != 'e':
        neighbours.append((x - 1, y))
    if y < len(grid) and direction != 'n':
        neighbours.append((x, y + 1))
    if y - 1 >= 0 and direction != 's':
        neighbours.append((x, y - 1))

    return neighbours

def move_straight(x, y, direction):
    if direction == 's':
        return x, y+1
    if direction == 'n':
        return x, y - 1
    if direction == 'w':
        return x - 1, y
    if direction == 'e':
        return x + 1, y

def is_letter(grid, x, y):

    item = grid[y][x]

    return item not in ['|', '-', '+','']


class Test_1(unittest.TestCase):

    def test_solution1(self):
        solution1(dummy_grid())

    def test_get_neighours(self):
        n = get_neighbours(dummy_grid(), 6, 3, 's')

        self.assertTrue((6, 4) in n)
        self.assertTrue((7, 3) in n)
        self.assertTrue((7, 3) in n)
        self.assertFalse((6, 2) in n)

    def test_change_direction(self):
        next_dir = turn(dummy_grid(), 6, 5, 's')

        self.assertEquals('e', next_dir)

    def test_meet_letter(self):

        (x, y, dir) = get_next_location(dummy_grid(), 6, 2, 's')

        self.assertEquals(6, x)
        self.assertEquals(3, y)
        self.assertEquals(dir, 's')

    def test_start_location(self):
        grid = dummy_grid()

        self.assertEquals(5, get_start_location(grid))

    def test_move_south(self):

        (x,y,dir) = get_next_location(dummy_grid(), 5, 0, 's')

        self.assertEquals(5, x)
        self.assertEquals(1, y)
        self.assertEquals(dir, 's')

    def test_move_north(self):
        (x, y, dir) = get_next_location(dummy_grid(), 5, 1, 'n')

        self.assertEquals(5, x)
        self.assertEquals(0, y)
        self.assertEquals(dir, 'n')

    def test_move_west(self):
        (x, y, dir) = get_next_location(dummy_grid(), 4, 3, 'w')

        self.assertEquals(3, x)
        self.assertEquals(3, y)
        self.assertEquals(dir, 'w')

    def test_move_east(self):
        (x, y, dir) = get_next_location(dummy_grid(), 4, 3, 'e')

        self.assertEquals(5, x)
        self.assertEquals(3, y)
        self.assertEquals(dir, 'e')

def dummy_grid():
    with open("Day19Dummy.txt") as textFile:
        grid = [line for line in textFile]
    return grid
