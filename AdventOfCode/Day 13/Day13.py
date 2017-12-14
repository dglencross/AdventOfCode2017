import unittest

def calculate_delay(lines):
    delay = 0
    severity = traverse_but_terminate_if_caught(lines, 0)

    while severity > 0:
        delay += 1
        if delay % 10000 == 0:
            print 'Trying delay of ' + str(delay) + ' picoseconds'
        severity = traverse_but_terminate_if_caught(lines, delay)

    return delay

def traverse_but_terminate_if_caught(lines, delay):
    severity = 0

    for i in lines:
        parts = i.split(': ')
        time = int(parts[0]) + delay
        length = int(parts[1])
        severity += collision_score(time, length)
        if severity > 0:
            return severity

    return severity

def traverse(lines):
    severity = 0

    for i in lines:
        parts = i.split(': ')
        time = int(parts[0])
        length = int(parts[1])
        severity += collision_score(time, length)

    return severity


def collision_score(time, length):
    if is_at_location_zero(time, length):
        return length * time
    return 0

def is_at_location_zero(time, length):
    if length == 0:
        return False

    if time == 0 or length == 1:
        return True

    if time % (length + (length - 2)) == 0:
        return True

    return False

class Test_1(unittest.TestCase):

    def test_solution_1(self):
        with open('day13.txt') as f:
            content = f.readlines()
        severity = traverse(content)
        self.assertEquals(2508, severity)

    def test_solution_2(self):
        with open('day13.txt') as f:
            content = f.readlines()
            delay = calculate_delay(content)
        self.assertEquals(3913186, delay)

    def test_dummy_input_part_1(self):
        with open('day13_dummy.txt') as f:
            content = f.readlines()
        severity = traverse(content)
        self.assertEquals(24, severity)

    def test_dummy_input_part_2(self):
        with open('day13_dummy.txt') as f:
            content = f.readlines()
        delay = calculate_delay(content)
        self.assertEquals(10, delay)

    def test_check_location_at_time_1(self):
        length = 4
        self.assertTrue(is_at_location_zero(0, length))

    def test_check_location_at_time_2(self):
        length = 4
        self.assertFalse(is_at_location_zero(1, length))

    def test_check_location_at_time_3(self):
        length = 4
        self.assertFalse(is_at_location_zero(2, length))

    def test_check_location_at_time_4(self):
        length = 4
        self.assertFalse(is_at_location_zero(1, length))

    def test_check_location_at_time_5(self):
        length = 4
        self.assertFalse(is_at_location_zero(11, length))

    def test_check_location_at_time_6(self):
        length = 4
        self.assertTrue(is_at_location_zero(12, length))
