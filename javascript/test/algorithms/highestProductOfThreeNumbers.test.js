const maximumProduct = require('../../src/algorithms/HighestProductOfThreeNumbers');

describe('maximumProduct', () => {
    test('should multiply the highest three numbers when at least three positive numbers provided', () => {
        expect(maximumProduct([1, 3, 5, 7, 9])).toBe(5 * 7 * 9);
    });

    test('should multiply the least negitive values when all numbers are negative', () => {
        expect(maximumProduct([-5, -4, -3, -2, -1])).toBe(-6);
    });

    test('should return zero when zero is included and all other numbers are negative', () => {
        expect(maximumProduct([0, -1, -2, -3, -4])).toBe(0);
    });

    test('should use the negative numbers when the product is greater than using all positive numbers', () => {
        expect(maximumProduct([-10, 1, 3, -10, 2])).toBe(300);
    });

    test('should multiply the positive numbers when the two most negative numbers give a smaller product', () => {
        expect(maximumProduct([-10, 15, 12, 9, -10, 1, 3, 2])).toBe(15 * 12 * 9);
    });

    test('should use the most negative numbers when the there are multiple negative numbers and these produce the biggest product', () => {
        expect(maximumProduct([-7, -10, 1, 3, -2, 2, -10])).toBe(300);
    });

    test('should use the negative numbers when the product is greater than the product of the 2nd and 3rd biggest positive numbers', () => {
        expect(maximumProduct([-10, 9, 12, -8, 7])).toBe(10 * 8 * 12);
    });

    test('should throw an error when less than three numbers provided', () => {
        expect(() => maximumProduct([1, 2])).toThrow(new Error("Requires an array of at least 3 numbers, but got: [1,2]"));
    });
});