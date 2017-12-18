import unittest

def spin(stepSize, loopSize):

    startValue = 0
    values = [0]
    location = 0

    for i in range(1,loopSize):
        startValue += 1
        location = get_next_insert_location(values, stepSize, location)
        values.insert(location, startValue)
        # print values

    return values

def get_next_insert_location(values, stepSize, location):
    for i in range(0,stepSize):
        if location + 1 < len(values):
            location += 1
            # print 'stepping to ' + str(location)
        else:
            location = 0
            # print 'stepping to ' + str(location)
    return location + 1

def get_value_after_2017(stepSize):
    values = spin(stepSize, 2018)

    for i in range(0,2018):
        if values[i] == 2017:
            return values[i+1]

class Test_1(unittest.TestCase):

    def test_spin_1(self):
        spin(3, 4)

    def test_dummy_input_1(self):
        self.assertEquals(638, get_value_after_2017(3))

    def test_solution_1(self):
        self.assertEquals(1025, get_value_after_2017(366))

    def test_get_next_location_1(self):
        values = [0]
        self.assertEquals(1, get_next_insert_location(values, 3, 0))

    def test_get_next_location_2(self):
        values = [0,1]
        self.assertEquals(1, get_next_insert_location(values, 3, 1))

    def test_get_next_location_3(self):
        values = [0, 2, 1]
        self.assertEquals(2, get_next_insert_location(values, 3, 1))


