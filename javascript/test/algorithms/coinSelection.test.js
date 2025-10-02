const { coinChange } = require('../../src/algorithms/coinSelection');

describe('coinChange', () => {
    test('should use 2 coins when making 4 from ones, twos and threes', () => {
        expect(coinChange([1, 2, 3], 4)).toBe(2);
    });

    test('should use 1 coin when total matches highest denomination', () => {
        expect(coinChange([1, 2, 5], 5)).toBe(1);
    });

    test('should use two fives when making 10 from twos, threes, fives and sixes', () => {
        expect(coinChange([2, 5, 3, 6], 10)).toBe(2);
    });

    test('should use 1 coin when total matches a denomination', () => {
        expect(coinChange([1, 2, 3, 5], 3)).toBe(1);
    });

    test('should return -1 when total cannot be made from denominations', () => {
        expect(coinChange([2, 6], 7)).toBe(-1);
    });

    test('should find solution when denominations are large', () => {
        expect(coinChange([186, 419, 83, 408], 6249)).toBe(20);
    });
});