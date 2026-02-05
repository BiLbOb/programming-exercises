"""Fibonacci sequence generator using dynamic programming."""

from typing import List


def fibonacci_dp(n: int) -> int:
    """
    Generate the nth Fibonacci number using dynamic programming.
    
    Args:
        n: The position in the Fibonacci sequence (0-indexed)
        
    Returns:
        The nth Fibonacci number
        
    Raises:
        ValueError: If n is negative
    """
    if n < 0:
        raise ValueError("n must be non-negative")
    
    if n <= 1:
        return n
    
    # Use bottom-up dynamic programming
    dp = [0] * (n + 1)
    dp[1] = 1
    
    for i in range(2, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    
    return dp[n]


def fibonacci_sequence(n: int) -> List[int]:
    """
    Generate the first n Fibonacci numbers.
    
    Args:
        n: Number of Fibonacci numbers to generate
        
    Returns:
        List of the first n Fibonacci numbers
        
    Raises:
        ValueError: If n is negative
    """
    if n < 0:
        raise ValueError("n must be non-negative")
    
    if n == 0:
        return []
    
    sequence = [0] * n
    if n > 1:
        sequence[1] = 1
        
    for i in range(2, n):
        sequence[i] = sequence[i - 1] + sequence[i - 2]
    
    return sequence


if __name__ == "__main__":
    # Demo the functions
    print("First 10 Fibonacci numbers:")
    print(fibonacci_sequence(10))
    
    print(f"\n20th Fibonacci number: {fibonacci_dp(20)}")