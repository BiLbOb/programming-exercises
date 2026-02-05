"""Tests for Fibonacci sequence functions."""

import pytest
from src.algorithms.fibonacci import fibonacci_dp, fibonacci_sequence


class TestFibonacciDP:
    """Tests for fibonacci_dp function."""
    
    def test_base_cases(self):
        """Test base cases for Fibonacci sequence."""
        assert fibonacci_dp(0) == 0
        assert fibonacci_dp(1) == 1
    
    def test_small_numbers(self):
        """Test small Fibonacci numbers."""
        assert fibonacci_dp(2) == 1
        assert fibonacci_dp(3) == 2
        assert fibonacci_dp(4) == 3
        assert fibonacci_dp(5) == 5
        assert fibonacci_dp(6) == 8
    
    def test_larger_numbers(self):
        """Test larger Fibonacci numbers."""
        assert fibonacci_dp(10) == 55
        assert fibonacci_dp(15) == 610
        assert fibonacci_dp(20) == 6765
    
    def test_negative_input(self):
        """Test that negative input raises ValueError."""
        with pytest.raises(ValueError, match="n must be non-negative"):
            fibonacci_dp(-1)


class TestFibonacciSequence:
    """Tests for fibonacci_sequence function."""
    
    def test_empty_sequence(self):
        """Test empty sequence."""
        assert fibonacci_sequence(0) == []
    
    def test_single_element(self):
        """Test single element sequence."""
        assert fibonacci_sequence(1) == [0]
    
    def test_small_sequences(self):
        """Test small sequences."""
        assert fibonacci_sequence(2) == [0, 1]
        assert fibonacci_sequence(3) == [0, 1, 1]
        assert fibonacci_sequence(5) == [0, 1, 1, 2, 3]
    
    def test_longer_sequence(self):
        """Test longer sequence."""
        expected = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
        assert fibonacci_sequence(10) == expected
    
    def test_negative_input(self):
        """Test that negative input raises ValueError."""
        with pytest.raises(ValueError, match="n must be non-negative"):
            fibonacci_sequence(-1)