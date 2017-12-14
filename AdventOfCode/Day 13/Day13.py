import unittest

def calculate_delay(lines):
    delay = 470000
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
    if get_location_at_time(length, time) == 0:
        return length * time
    return 0


def get_location_at_time(length, time):

    if length == 0 or time == 0:
        return 0

    if time < length:
        return time

    depth = 0
    forward = True

    for step in range(0,time):
        (depth,forward) = move(length, depth, forward)

    return depth

def move(length, depth, forward):
    if forward:
        if depth < length - 1:
            depth += 1
        else:
            depth -= 1
            forward = False
    else:
        if depth > 0:
            depth -= 1
        else:
            depth += 1
            forward = True
    return depth,forward

class Test_1(unittest.TestCase):

    def test_solution_1(self):
        with open('day13.txt') as f:
            content = f.readlines()
        severity = traverse(content)
        self.assertEquals(2508, severity)

    # def test_solution_2(self):
    #     with open('day13.txt') as f:
    #         content = f.readlines()
    #         delay = calculate_delay(content)
    #     self.assertEquals(2508, delay)

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
        self.assertEquals(0, get_location_at_time(length, 0))

    def test_check_location_at_time_1(self):
        length = 4
        self.assertEquals(1, get_location_at_time(length, 1))

    def test_check_location_at_time_1(self):
        length = 4
        self.assertEquals(2, get_location_at_time(length, 4))

    def test_check_location_at_time_1(self):
        length = 4
        self.assertEquals(1, get_location_at_time(length, 7))

    def test_check_location_at_time_1(self):
        length = 4
        self.assertEquals(1, get_location_at_time(length, 11))




