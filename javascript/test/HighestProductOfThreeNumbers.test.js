const maximumProduct = require('../src/algorithms/HighestProductOfThreeNumbers');

describe('maximumProduct', () => {
    test('should return 300 for [-10, -10, 1, 3, 2]', () => {
        expect(maximumProduct([-10, -10, 1, 3, 2])).toBe(300);
    });

    test('should return 0 for [0, -1, -2, -3, -4]', () => {
        expect(maximumProduct([0, -1, -2, -3, -4])).toBe(0);
    });

    test('should return -6 for [-5, -4, -3, -2, -1]', () => {
        expect(maximumProduct([-5, -4, -3, -2, -1])).toBe(-6);
    });

    test('should return 315 for [1, 3, 5, 7, 9]', () => {
        expect(maximumProduct([1, 3, 5, 7, 9])).toBe(315);
    });
});