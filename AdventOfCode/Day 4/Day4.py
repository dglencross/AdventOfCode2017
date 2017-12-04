import unittest

def password_passes_no_duplicates(password):

    parts = password.split(' ')
    alreadySeen = []

    for part in parts:
        if part in alreadySeen:
            return False
        alreadySeen.append(part)

    return True

def password_passes_no_anagrams(password):

    parts = password.split(' ')
    alreadySeen = []

    for part in parts:
        if sorted(part) in alreadySeen:
            return False
        alreadySeen.append(sorted(part))

    return True

def count_valid_passwords(password_list):

    validCount = 0

    for password in password_list:
        if password_passes_no_duplicates(password):
            validCount = validCount + 1

    return validCount

def count_valid_passwords_2(password_list):

    validCount = 0

    for password in password_list:
        if password_passes_no_anagrams(password):
            validCount = validCount + 1

    return validCount

class Test_1(unittest.TestCase):
    def test_1(self):
        self.assertTrue(password_passes_no_duplicates('aa bb cc dd ee'))

    def test_2(self):
        self.assertFalse(password_passes_no_duplicates('aa bb cc dd aa'))

    def test_3(self):
        self.assertTrue(password_passes_no_duplicates('aa bb cc dd aaa'))

    def test_4(self):
        self.assertEquals(count_valid_passwords(['aa bb cc dd ee','aa bb cc dd aa', 'aa bb cc dd aaa']), 2)

class Test_2(unittest.TestCase):
    def test_1(self):
        self.assertTrue(password_passes_no_anagrams('aa bb cc dd ee'))

    def test_2(self):
        self.assertFalse(password_passes_no_anagrams('aa bb cc dd aa'))

    def test_3(self):
        self.assertFalse(password_passes_no_anagrams('dave bb cc dd evad'))

    def test_4(self):
        self.assertFalse(password_passes_no_anagrams('dave bb cc dd dave'))

class Test_Solution_1(unittest.TestCase):
    def test_answer(self):
        with open('passwords.txt') as f:
            lines = f.read().splitlines()
        self.assertEquals(count_valid_passwords(lines), 455)


class Test_Solution_2(unittest.TestCase):
    def test_answer(self):
        with open('passwords.txt') as f:
            lines = f.read().splitlines()
        self.assertEquals(count_valid_passwords_2(lines), 186)
