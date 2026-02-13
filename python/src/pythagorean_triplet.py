# Detect a² + b² = c² in a list of integers
from math import isqrt

def contains_pythagorean_triplet(values: list[int]):
	squared = [value ** 2 for value in set(values)]
	for index_a in range(len(squared)):
		for index_b in range(index_a+1, len(squared)):
			if squared[index_a] + squared[index_b] in squared:
				print("found: ", isqrt(squared[index_a]), ", ", isqrt(squared[index_b]), ", ", isqrt(squared[index_a] + squared[index_b]))
				return True

	return False

value_list = [3,4,5]
print("Does this list contain a Pythagorean Triplet?")
print(value_list)
print("Yes\n" if contains_pythagorean_triplet(value_list) else "No\n")


value_list_2 = [1,2,3,5,12]
print("Does this list contain a Pythagorean Triplet?")
print(value_list_2)
print("Yes\n" if contains_pythagorean_triplet(value_list_2) else "No\n")

empty_value_list = []
print("Does this list contain a Pythagorean Triplet?")
print(empty_value_list)
print("Yes\n" if contains_pythagorean_triplet(empty_value_list) else "No\n")