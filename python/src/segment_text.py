"""
You’ve got a string like "datacamp" and a dictionary of words like ["data", "camp", "cam," "lack"]. 
Figure out if you can break that long string into valid words from the dictionary.
"""

def can_segment(text: str, fragments: list[str]):
	if len(text) == 0:
		return True

	for fragment in fragments:
		if text.startswith(fragment):
			print("found: " + fragment)
			# if this is the last part of the string, it all fits
			if len(text) == len(fragment):
				return True

			remainder = text[len(fragment):]
			return can_segment(remainder, fragments)

	# none of the fragments fit at the start, so fail
	return False

text = "datacamp"
fragments = ["data", "camp", "cam", "lack"]

print("Can '"+text+"' be segmented into these fragments")
print(fragments)
print("Yes\n" if can_segment(text, fragments) else "\n" )

text2 = "datalackcatcam"
print("Can '"+text2+"' be segmented into these fragments")
print(fragments)
print("Yes\n" if can_segment(text2, fragments) else "No\n" )

