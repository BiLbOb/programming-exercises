from collections import Counter

count_test = "dog food god"
print("Counter Test: ") 
print(Counter(count_test))


# Checking for palindromes
def check_palindrome(test_string: str):
    cleaned = "".join(c for c in test_string if c.isalnum()).lower()
    return cleaned == cleaned[::-1]

is_palindrome = "taco cat"
print(is_palindrome + ": " + ("palindrome" if check_palindrome(is_palindrome) else "not"))

not_palinrome = "dog food god"
print(not_palinrome + ": " + ("palindrome" if check_palindrome(not_palinrome) else "not"))
