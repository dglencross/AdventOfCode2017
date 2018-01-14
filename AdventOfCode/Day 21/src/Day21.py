import numpy as np
import unittest
import math


def combine_two_arrays_along_side(a1, a2):
    return np.concatenate((a1, a2), axis=1)


def combine_four_arrays(a1, a2, a3, a4):
    b1 = combine_two_arrays_along_side(a1, a2)
    b2 = combine_two_arrays_along_side(a3, a4)

    return np.concatenate((b1, b2), axis=0)


def combine_n_arrays(arrays):
    n = len(arrays)
    size = math.sqrt(n)

    result = []
    count = 0

    while count < n:
        subcount = 0
        line = []
        while subcount < size:
            if len(line) == 0:
                if arrays[count] is None:
                    return
                line = list(arrays[count])
            else:

                for a in line:
                    if isinstance(a, (list,)):
                        for x in a:
                            if x != '#' and x != '.':
                                a.remove(x)

                for a in arrays[count]:
                    if isinstance(a, (list,)):
                        for x in a:
                            if x != '#' and x != '.':
                                a.remove(x)
                line = combine_two_arrays_along_side(line, list(arrays[count]))
            subcount += 1
            count += 1
        if len(result) == 0:
            result = line
        else:
            result = np.concatenate((result, line), axis=0)

    if len(result) > n:
        final_size = len(result) / len(result[0])
        sub_size = len(result[0])
        # this is where I need to combine these, this should never really be the case

    return result


def matches_rule(array, rule):

    if np.array_equal(array, rule):
        return True

    flip_horizontal = np.fliplr(rule)
    if np.array_equal(array, flip_horizontal.tolist()):
        return True

    flip_horizontal = np.rot90(flip_horizontal)
    if np.array_equal(array, flip_horizontal.tolist()):
        return True

    flip_horizontal = np.rot90(flip_horizontal)
    
    if np.array_equal(array, flip_horizontal.tolist()):
        return True

    flip_horizontal = np.rot90(flip_horizontal)
    if np.array_equal(array, flip_horizontal.tolist()):
        return True

    flip_vertical = np.flipud(rule)
    if np.array_equal(array, flip_vertical.tolist()):
        return True
    flip_vertical = np.rot90(flip_vertical)

    if np.array_equal(array, flip_vertical.tolist()):
        return True
    flip_vertical = np.rot90(flip_vertical)
    if np.array_equal(array, flip_vertical.tolist()):
        return True
    flip_vertical = np.rot90(flip_vertical)
    if np.array_equal(array, flip_vertical.tolist()):
        return True
    rotated_rule = np.rot90(rule)
    if np.array_equal(array, rotated_rule.tolist()):
        return True
    rotated_rule = np.rot90(rotated_rule)
    if np.array_equal(array, rotated_rule.tolist()):
        return True
    rotated_rule = np.rot90(rotated_rule)
    if np.array_equal(array, rotated_rule.tolist()):
        return True

    return False


def translate_to_array(line):
    s = line.split('/')

    array = []
    for bit in s:
        subarray = []
        for letter in bit:
            subarray.append(letter)
        array.append(subarray)

    return array


def expand_once(rules, array):
    return get_expansion(rules, array)


def expand(rules, array, iterations):

    print 'iterations remaining: ' + str(iterations)

    for x in range(0, iterations):
        if len(array) == 2 or len(array) == 3:
            array = expand_once(rules, array)
        elif len(array) % 2 == 0:
            splits = split_into_arrays_of_two(array)
            for i in range(0, len(splits)):
                result = expand_once(rules, splits[i])
                splits[i] = result
            array = combine_n_arrays(splits)
        elif len(array) % 3 == 0:
            splits = split_into_arrays_of_three(array)
            for i in range(0, len(splits)):
                result = expand_once(rules, splits[i])
                splits[i] = result
            array = combine_n_arrays(splits)

    # print str(array) + '\n'
    return array


def get_expansion(lines, array):

    for line in lines:
        line = line.replace(" ", "")
        parts = line.split("=>")

        for a in array:
            for x in a:
                if x != '#' and x != '.':
                    a.remove(x)

        if isinstance(array, (list,)):
            array = np.array(array)
        if matches_rule(array.tolist(), translate_to_array(parts[0])):
            return translate_to_array(parts[1])

    raise ValueError('Failed to find a matching rule, this cannot happen')


def real_rules():
    with open("Day21.txt") as textFile:
        grid = [line for line in textFile]
    return grid

def single_rules():
    with open("Day21.txt") as textFile:
        grid = [line for line in textFile]
    return grid


def dummy_rules():
    with open("Day21Dummy.txt") as textFile:
        grid = [line for line in textFile]
    return grid


def count_hashes(array):
    count = 0

    for subarray in array:
        for j in subarray:
            if '#' == j:
                count += 1

    return count


def split_into_arrays_of_two(array):
    x = 0
    y = 0
    result = []

    while y < len(array):
        while x < len(array):
            subarray = np.array([[array[y][x], array[y][x + 1]], [array[y + 1][x], array[y + 1][x + 1]]])
            result.append(subarray)
            x += 2
        x = 0
        y += 2

    return result

def split_into_arrays_of_three(array):
    x = 0
    y = 0
    result = []

    while y < len(array):
        while x < len(array):
            subarray = np.array([[array[y][x], array[y][x + 1], array[y][x + 2]],
                                 [array[y + 1][x], array[y + 1][x + 1], array[y + 1][x + 2]],
                                 [array[y + 2][x], array[y + 2][x + 1], array[y + 2][x + 2]]])
            result.append(subarray)
            x += 3
        x = 0
        y += 3

    return result

class Test_1(unittest.TestCase):
    def test_expand_three_once(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule_lines = dummy_rules()

        result = expand_once(rule_lines, start_array)

        self.assertEquals('#', result[0][0])
        self.assertEquals('.', result[0][1])
        self.assertEquals('.', result[0][2])
        self.assertEquals('#', result[0][3])

        self.assertEquals('.', result[1][0])
        self.assertEquals('.', result[1][1])
        self.assertEquals('.', result[1][2])
        self.assertEquals('.', result[1][3])

        self.assertEquals('.', result[2][0])
        self.assertEquals('.', result[2][1])
        self.assertEquals('.', result[2][2])
        self.assertEquals('.', result[2][3])

        self.assertEquals('#', result[3][0])
        self.assertEquals('.', result[3][1])
        self.assertEquals('.', result[3][2])
        self.assertEquals('#', result[3][3])

    def test_expand_three_one_iteration(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule_lines = dummy_rules()

        result = expand(rule_lines, start_array, 1)

        self.assertEquals('#', result[0][0])
        self.assertEquals('.', result[0][1])
        self.assertEquals('.', result[0][2])
        self.assertEquals('#', result[0][3])

        self.assertEquals('.', result[1][0])
        self.assertEquals('.', result[1][1])
        self.assertEquals('.', result[1][2])
        self.assertEquals('.', result[1][3])

        self.assertEquals('.', result[2][0])
        self.assertEquals('.', result[2][1])
        self.assertEquals('.', result[2][2])
        self.assertEquals('.', result[2][3])

        self.assertEquals('#', result[3][0])
        self.assertEquals('.', result[3][1])
        self.assertEquals('.', result[3][2])
        self.assertEquals('#', result[3][3])

    def test_expand_three_two_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule_lines = dummy_rules()

        result = expand(rule_lines, start_array, 2)

        self.assertEquals('#', result[0][0])
        self.assertEquals('#', result[0][1])
        self.assertEquals('.', result[0][2])
        self.assertEquals('#', result[0][3])
        self.assertEquals('#', result[0][4])
        self.assertEquals('.', result[0][5])

        self.assertEquals('#', result[1][0])
        self.assertEquals('.', result[1][1])
        self.assertEquals('.', result[1][2])
        self.assertEquals('#', result[1][3])
        self.assertEquals('.', result[1][4])
        self.assertEquals('.', result[1][5])

        self.assertEquals('.', result[2][0])
        self.assertEquals('.', result[2][1])
        self.assertEquals('.', result[2][2])
        self.assertEquals('.', result[2][3])
        self.assertEquals('.', result[2][4])
        self.assertEquals('.', result[2][5])

        self.assertEquals('#', result[3][0])
        self.assertEquals('#', result[3][1])
        self.assertEquals('.', result[3][2])
        self.assertEquals('#', result[3][3])
        self.assertEquals('#', result[3][4])
        self.assertEquals('.', result[3][5])

        self.assertEquals('#', result[4][0])
        self.assertEquals('.', result[4][1])
        self.assertEquals('.', result[4][2])
        self.assertEquals('#', result[4][3])
        self.assertEquals('.', result[4][4])
        self.assertEquals('.', result[4][5])

        self.assertEquals('.', result[5][0])
        self.assertEquals('.', result[5][1])
        self.assertEquals('.', result[5][2])
        self.assertEquals('.', result[5][3])
        self.assertEquals('.', result[5][4])
        self.assertEquals('.', result[5][5])

    def test_count_after_two_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = dummy_rules()

        result = expand(rule_lines, start_array, 2)

        self.assertEquals(12, count_hashes(result))

    # 0 pre: 3
    # 0 post: 4
    # 1 pre: 4
    # 1 post: 6
    # 2 pre: 6
    # 2 post: 9
    # 3 pre: 9
    # 3 post: 12
    # 4 pre: 12
    # 4 post: 18


    def test_size_after_one_iteration_dummy(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = dummy_rules()

        result = expand(rule_lines, start_array, 1)

        print str(result)
        self.assertEquals(4, len(result))

    def test_size_after_one_iteration(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = real_rules()

        result = expand(rule_lines, start_array, 1)

        print str(result)
        self.assertEquals(4, len(result))

    def test_size_after_two_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = real_rules()

        result = expand(rule_lines, start_array, 2)

        print str(result)
        self.assertEquals(6, len(result))

    def test_size_after_three_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = real_rules()

        result = expand(rule_lines, start_array, 3)

        self.assertEquals(9, len(result))

    def test_size_after_four_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = real_rules()

        result = expand(rule_lines, start_array, 4)

        self.assertEquals(12, len(result))

    def test_size_after_five_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        rule_lines = real_rules()

        result = expand(rule_lines, start_array, 5)

        self.assertEquals(18, len(result))

    def test_count_after_five_iterations(self):

        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule_lines = single_rules()

        result = expand(rule_lines, start_array, 5)

        print len(result)
        print len(result[0])

        self.assertEquals(142, count_hashes(result))

    def test_count_after_eighteen_iterations(self):
        start_array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule_lines = single_rules()

        result = expand(rule_lines, start_array, 18)

        print len(result)
        print len(result[0])

        self.assertEquals(142, count_hashes(result))

    def test_split_4_by_3(self):
        array = np.array([['#', '.', '.', '#'], ['.', '.', '.', '.'], ['.', '.', '.', '.'], ['#', '.', '.', '#']])

        arrays = split_into_arrays_of_two(array)

        self.assertEquals('#', arrays[0][0][0])
        self.assertEquals('.', arrays[0][0][1])
        self.assertEquals('.', arrays[0][1][0])
        self.assertEquals('.', arrays[0][1][1])

        self.assertEquals('.', arrays[1][0][0])
        self.assertEquals('#', arrays[1][0][1])
        self.assertEquals('.', arrays[1][1][0])
        self.assertEquals('.', arrays[1][1][1])

        self.assertEquals('.', arrays[2][0][0])
        self.assertEquals('.', arrays[2][0][1])
        self.assertEquals('#', arrays[2][1][0])
        self.assertEquals('.', arrays[2][1][1])

        self.assertEquals('.', arrays[3][0][0])
        self.assertEquals('.', arrays[3][0][1])
        self.assertEquals('.', arrays[3][1][0])
        self.assertEquals('#', arrays[3][1][1])

    def test_split_4(self):
        array = np.array([['#', '.', '.', '#'], ['.', '.', '.', '.'], ['.', '.', '.', '.'], ['#', '.', '.', '#']])

        arrays = split_into_arrays_of_two(array)

        self.assertEquals('#', arrays[0][0][0])
        self.assertEquals('.', arrays[0][0][1])
        self.assertEquals('.', arrays[0][1][0])
        self.assertEquals('.', arrays[0][1][1])

        self.assertEquals('.', arrays[1][0][0])
        self.assertEquals('#', arrays[1][0][1])
        self.assertEquals('.', arrays[1][1][0])
        self.assertEquals('.', arrays[1][1][1])

        self.assertEquals('.', arrays[2][0][0])
        self.assertEquals('.', arrays[2][0][1])
        self.assertEquals('#', arrays[2][1][0])
        self.assertEquals('.', arrays[2][1][1])

        self.assertEquals('.', arrays[3][0][0])
        self.assertEquals('.', arrays[3][0][1])
        self.assertEquals('.', arrays[3][1][0])
        self.assertEquals('#', arrays[3][1][1])

    def test_concat_two(self):
        a1 = np.array([[1, 2], [3, 4]])
        a2 = np.array([[5, 6], [7, 8]])

        combined = combine_two_arrays_along_side(a1, a2)

        self.assertEquals(1, combined[0][0])
        self.assertEquals(2, combined[0][1])
        self.assertEquals(5, combined[0][2])
        self.assertEquals(6, combined[0][3])

        self.assertEquals(3, combined[1][0])
        self.assertEquals(4, combined[1][1])
        self.assertEquals(7, combined[1][2])
        self.assertEquals(8, combined[1][3])

    def test_concat_four(self):
        a1 = np.array([[1, 2], [3, 4]])
        a2 = np.array([[5, 6], [7, 8]])
        a3 = np.array([[9, 10], [11, 12]])
        a4 = np.array([[13, 14], [15, 16]])

        combined = combine_four_arrays(a1, a2, a3, a4)

        self.assertEquals(1, combined[0][0])
        self.assertEquals(2, combined[0][1])
        self.assertEquals(5, combined[0][2])
        self.assertEquals(6, combined[0][3])

        self.assertEquals(3, combined[1][0])
        self.assertEquals(4, combined[1][1])
        self.assertEquals(7, combined[1][2])
        self.assertEquals(8, combined[1][3])

        self.assertEquals(9, combined[2][0])
        self.assertEquals(10, combined[2][1])
        self.assertEquals(13, combined[2][2])
        self.assertEquals(14, combined[2][3])

        self.assertEquals(11, combined[3][0])
        self.assertEquals(12, combined[3][1])
        self.assertEquals(15, combined[3][2])
        self.assertEquals(16, combined[3][3])

    def test_concat_four_by_n(self):
        a1 = np.array([[1, 2], [3, 4]])
        a2 = np.array([[5, 6], [7, 8]])
        a3 = np.array([[9, 10], [11, 12]])
        a4 = np.array([[13, 14], [15, 16]])

        combined = combine_n_arrays([a1, a2, a3, a4])

        self.assertEquals(1, combined[0][0])
        self.assertEquals(2, combined[0][1])
        self.assertEquals(5, combined[0][2])
        self.assertEquals(6, combined[0][3])

        self.assertEquals(3, combined[1][0])
        self.assertEquals(4, combined[1][1])
        self.assertEquals(7, combined[1][2])
        self.assertEquals(8, combined[1][3])

        self.assertEquals(9, combined[2][0])
        self.assertEquals(10, combined[2][1])
        self.assertEquals(13, combined[2][2])
        self.assertEquals(14, combined[2][3])

        self.assertEquals(11, combined[3][0])
        self.assertEquals(12, combined[3][1])
        self.assertEquals(15, combined[3][2])
        self.assertEquals(16, combined[3][3])

    def test_concat_four_by_three_by_n(self):
        a1 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a2 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a3 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a4 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])

        combined = combine_n_arrays([a1, a2, a3, a4])

        self.assertEquals('#', combined[0][0])
        self.assertEquals('#', combined[0][1])
        self.assertEquals('.', combined[0][2])
        self.assertEquals('#', combined[0][3])
        self.assertEquals('#', combined[0][4])
        self.assertEquals('.', combined[0][5])

        self.assertEquals('#', combined[1][0])
        self.assertEquals('.', combined[1][1])
        self.assertEquals('.', combined[1][2])
        self.assertEquals('#', combined[1][3])
        self.assertEquals('.', combined[1][4])
        self.assertEquals('.', combined[1][5])

        self.assertEquals('.', combined[2][0])
        self.assertEquals('.', combined[2][1])
        self.assertEquals('.', combined[2][2])
        self.assertEquals('.', combined[2][3])
        self.assertEquals('.', combined[2][4])
        self.assertEquals('.', combined[2][5])

        self.assertEquals('#', combined[3][0])
        self.assertEquals('#', combined[3][1])
        self.assertEquals('.', combined[3][2])
        self.assertEquals('#', combined[3][3])
        self.assertEquals('#', combined[3][4])
        self.assertEquals('.', combined[3][5])

        self.assertEquals('#', combined[4][0])
        self.assertEquals('.', combined[4][1])
        self.assertEquals('.', combined[4][2])
        self.assertEquals('#', combined[4][3])
        self.assertEquals('.', combined[4][4])
        self.assertEquals('.', combined[4][5])

        self.assertEquals('.', combined[5][0])
        self.assertEquals('.', combined[5][1])
        self.assertEquals('.', combined[5][2])
        self.assertEquals('.', combined[5][3])
        self.assertEquals('.', combined[5][4])
        self.assertEquals('.', combined[5][5])

    def test_concat_four_by_three(self):
        a1 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a2 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a3 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])
        a4 = np.array([['#', '#', '.'], ['#', '.', '.'], ['.', '.', '.']])

        combined = combine_four_arrays(a1, a2, a3, a4)

        self.assertEquals('#', combined[0][0])
        self.assertEquals('#', combined[0][1])
        self.assertEquals('.', combined[0][2])
        self.assertEquals('#', combined[0][3])
        self.assertEquals('#', combined[0][4])
        self.assertEquals('.', combined[0][5])

        self.assertEquals('#', combined[1][0])
        self.assertEquals('.', combined[1][1])
        self.assertEquals('.', combined[1][2])
        self.assertEquals('#', combined[1][3])
        self.assertEquals('.', combined[1][4])
        self.assertEquals('.', combined[1][5])

        self.assertEquals('.', combined[2][0])
        self.assertEquals('.', combined[2][1])
        self.assertEquals('.', combined[2][2])
        self.assertEquals('.', combined[2][3])
        self.assertEquals('.', combined[2][4])
        self.assertEquals('.', combined[2][5])

        self.assertEquals('#', combined[3][0])
        self.assertEquals('#', combined[3][1])
        self.assertEquals('.', combined[3][2])
        self.assertEquals('#', combined[3][3])
        self.assertEquals('#', combined[3][4])
        self.assertEquals('.', combined[3][5])

        self.assertEquals('#', combined[4][0])
        self.assertEquals('.', combined[4][1])
        self.assertEquals('.', combined[4][2])
        self.assertEquals('#', combined[4][3])
        self.assertEquals('.', combined[4][4])
        self.assertEquals('.', combined[4][5])

        self.assertEquals('.', combined[5][0])
        self.assertEquals('.', combined[5][1])
        self.assertEquals('.', combined[5][2])
        self.assertEquals('.', combined[5][3])
        self.assertEquals('.', combined[5][4])
        self.assertEquals('.', combined[5][5])

    def test_match_array_exact(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_match_array_flip_horizontal(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule = np.fliplr(rule)

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_match_array_flip_verticle(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule = np.flipud(rule)

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_match_array_rotate_90(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule = np.rot90(rule)

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_match_array_rotate_180(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule = np.rot90(rule)
        rule = np.rot90(rule)

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_match_array_rotate_270(self):
        rule = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])
        rule = np.rot90(rule)
        rule = np.rot90(rule)
        rule = np.rot90(rule)

        input = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(matches_rule(input, rule))

    def test_translate_line_three(self):
        line = '.#./..#/###'
        array = np.array([['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']])

        self.assertTrue(np.array_equal(array, translate_to_array(line)))

    def test_translate_line_two(self):
        line = '../.#'
        array = np.array([['.', '.'], ['.', '#']])

        self.assertTrue(np.array_equal(array, translate_to_array(line)))
