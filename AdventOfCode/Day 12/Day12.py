import unittest

def build_adjacency_matrix(input):

    matrix = [[0 for x in range(len(input))] for y in range(len(input))]

    for x in input:
        parts = x.split(' ')
        a = parts[0]
        bs = parts[2:]
        for y in bs:
            b_int = int(y.replace(',',''))
            a_int = int(a)

            matrix[a_int][b_int] = 1
            matrix[b_int][a_int] = 1

    return matrix

def count_entries(matrix, number):
    total = 1 # count our start position
    for i in range(0, len(matrix[number])):
        if number != i:
            if can_reach(matrix, number, i):
                total += 1
    return total

def count_clusters(matrix):
    nodes = []
    clusters = 0
    for i in range(0, len(matrix)):
        nodes.append(i)

    while len(nodes) > 0:
        node = nodes.pop()
        toRemove = []

        for otherNode in nodes:
            if can_reach(matrix, node, otherNode):
                toRemove.append(otherNode)
        for node in toRemove:
            nodes.remove(node)
        clusters += 1
    return clusters

def can_reach(matrix, a, b):
    doneSet = []
    todoSet = []

    todoSet.append(a)

    while len(todoSet) > 0:
        done = todoSet.pop()

        reachableNodes = get_reachable_nodes(matrix, done)

        doneSet.append(done)

        for node in reachableNodes:
            if node == b:
                return True
            if not node in doneSet:
                todoSet.append(node)

    return False

def get_reachable_nodes(matrix, a):
    reachableNodes = []
    for i in range (0, len(matrix[a])):
        if 1 == matrix[a][i]:
            reachableNodes.append(i)
    return reachableNodes

class Test_1(unittest.TestCase):

    def test_solution_1(self):
        with open("input_12.txt") as f:
            content = f.readlines()

        matrix = build_adjacency_matrix(content)
        self.assertEquals(175, count_entries(matrix, 0))

    def test_solution_2(self):
        with open("input_12.txt") as f:
            content = f.readlines()

        matrix = build_adjacency_matrix(content)
        self.assertEquals(213, count_clusters(matrix))

    def test_count_dummy_clusters(self):
        matrix = build_adjacency_matrix(get_dummy_input())
        self.assertEqual(2, count_clusters(matrix))

    def test_can_reach_0_2(self):
        matrix = build_adjacency_matrix(get_dummy_input())
        self.assertTrue(can_reach(matrix, 0, 2))

    def test_can_reach_0_6(self):
        matrix = build_adjacency_matrix(get_dummy_input())
        self.assertTrue(can_reach(matrix, 0, 6))

    def test_can_reach_0_1(self):
        matrix = build_adjacency_matrix(get_dummy_input())
        self.assertFalse(can_reach(matrix, 0, 1))

    def test_build_structure(self):
        input = get_dummy_input()

        matrix = build_adjacency_matrix(input)
        self.assertEquals(1, matrix[0][2])
        self.assertEquals(1, matrix[2][3])
        self.assertEquals(1, matrix[6][4])
        self.assertEquals(0, matrix[4][1])

    def test_count(self):
        input = get_dummy_input()
        matrix = build_adjacency_matrix(input)
        self.assertEquals(6, count_entries(matrix,0))

def get_dummy_input():
    input = []
    input.append("0 <-> 2")
    input.append("1 <-> 1")
    input.append("2 <-> 0, 3, 4")
    input.append("3 <-> 2, 4")
    input.append("4 <-> 2, 3, 6")
    input.append("5 <-> 6")
    input.append("6 <-> 4, 5")

    return input
