import unittest

def spin(stepSize, loopSize):

    startValue = 0
    values = [0]
    location = 0

    for i in range(1,loopSize):
        startValue += 1

        if startValue % 10000 == 0:
            print 'Inserting ' + str(startValue)

        location = get_next_insert_location(values, stepSize, location)
        values.insert(location, startValue)
        # print values

    return values

def get_value_after_0(stepSize, spins):

    startValue = 0
    location = 0
    currently_next_to = 0
    array_size = 1
    look_for_location = 0

    for i in range(0, spins+1):
        startValue += 1
        location = get_next_insert_location(array_size, stepSize, location)
        if location <= look_for_location:
            look_for_location += 1
        elif location == look_for_location + 1:
            currently_next_to = startValue
        array_size += 1

    return currently_next_to

def get_next_insert_location(values, stepSize, location):

    if isinstance(values, (list,)):
        length = len(values)
    else:
        length = values

    if length > (stepSize + location):
        return location + stepSize + 1
    else:
        return ((stepSize+location) % length) + 1

def get_value_after_2017(look_for, stepSize):
    values = spin(stepSize, 2018)

    for i in range(0,2018):
        if values[i] == look_for:
            return values[i+1]

class Test_1(unittest.TestCase):

    def test_spin_1(self):
        spin(3, 4)

    def test_dummy_input_1(self):
        self.assertEquals(638, get_value_after_2017(2017, 3))

    def test_solution_1(self):
        self.assertEquals(1025, get_value_after_2017(2017, 366))

    def test_dummy_input_2(self):
        self.assertEquals(431, get_value_after_2017(0, 366))

    def test_dummy_input_3(self):
        self.assertEquals(431, get_value_after_0(366, 2017))

    def test_solution_2(self):
        self.assertEquals(37803463, get_value_after_0(366, 50000000))

    def test_get_next_location_1(self):
        values = [0]
        self.assertEquals(1, get_next_insert_location(values, 3, 0))

    def test_get_next_location_2(self):
        values = [0,1]
        self.assertEquals(1, get_next_insert_location(values, 3, 1))

    def test_get_next_location_3(self):
        values = [0, 2, 1]
        self.assertEquals(2, get_next_insert_location(values, 3, 1))

