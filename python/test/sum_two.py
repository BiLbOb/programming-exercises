import unittest

from src.sum_two import sum_two


class MyTestCase(unittest.TestCase):
    def test_finds_sums_when_simple_case(self):
        self.assertEqual(sum_two([2,5,8,12], 7 ), (0,1))

    def test_finds_sums_when_solution_is_harder(self):
        self.assertEqual(sum_two([1,2,5,8,12,17], 14 ), (1,4))

    def test_finds_sums_when_negative_numbers_included(self):
        self.assertEqual(sum_two([1,-2,5,-8,12,17], 15 ), (1,5))

    def test_returns_failure_result_when_no_solution(self):
        self.assertEqual(sum_two([2,5,8,12], 6 ), (-1,-1))

    def test_returns_failure_result_when_numbers_provided(self):
        self.assertEqual(sum_two([], 6 ), (-1,-1))

    def test_returns_failure_result_when_single_number_provided(self):
        self.assertEqual(sum_two([6], 6 ), (-1,-1))


if __name__ == '__main__':
    unittest.main()
