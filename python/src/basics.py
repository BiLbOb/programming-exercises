from collections import Counter

count_test = "dog food god"
print("\nCounter Test: ") 
print(Counter(count_test))

from collections import defaultdict

counter = defaultdict(int)
for c in count_test:
    counter[c] += 1

print("Counter Test 2: ") 
print(Counter(counter))

"""
Write a function that takes a list of dictionaries (representing law firm clients) 
and returns a list of names for those who have a status of "active" and an age over 18.
"""

def filter_active_clients(clients):
    return [client["name"] for client in clients if (client["status"].lower() == "active" and client["age"] > 18)]

clients = [
    {"name": "Alice", "status": "active", "age": 30},
    {"name": "Bob", "status": "inactive", "age": 25},
    {"name": "Charlie", "status": "active", "age": 17},
    {"name": "David", "status": "active", "age": 20},
    {"name": "Donald", "status": "ACTIVE", "age": 42}
]

print("\nData filter test.\nActive clients:")
print(filter_active_clients(clients))


"""
Given a string of text, 
return a dictionary where keys are unique words 
and values are the number of times each word appears. 
Ignore case and punctuation.
"""

def unique_words(text: str):
    remove_punc = str.maketrans({
        ".": " ", 
        ",": " ", 
        ";": " ", 
        ":": " ", 
        "\"": " ", 
        "'": " ",
        "!": " ",
        "?": " ",
        "(": " ", 
        ")": " "
    })
    cleaned = text.translate(remove_punc)
    word_counts = Counter(cleaned.lower().split())
    return word_counts

phrase = "Who man dem, dem man down dere den do man?"
print("\nUnique word count")
print("Phrase: " + phrase)
print(unique_words(phrase))
