def sum_two(numbers: list[int], required: int ) -> tuple[int, int]:
    seen = {}
    for index in range(len(numbers)):
        number = numbers[index]
        other = required - number
        if other in seen:
            return seen[other], index
        seen[number] = index
    return -1, -1