# variable # integers -> sum off squares
def sum_of_squares(*args):
	sum = 0 
	for value in args:
		sum += value ** 2
	return sum

# print( sum_of_squares(1,2))

def same_xs_as_ys(text: str):
	sum_xs = 0
	sum_ys = 0
	for c in text:
		clean_c = c.lower()
		if clean_c == "x":
			sum_xs += 1
		elif clean_c == "y":
			sum_ys+= 1
	return sum_xs == sum_ys

# print(same_xs_as_ys("xxXyY"))

# fn takes str removes consecutive chars
def remove_consecutive_chars(text:str):
	if len(text) == 0:
		return text

	no_consecutive_chars = text[0]
	for index in range(1,len(text)):
		if text[index-1] != text[index]:
			no_consecutive_chars += text[index]
	return no_consecutive_chars

print(remove_consecutive_chars("1"))