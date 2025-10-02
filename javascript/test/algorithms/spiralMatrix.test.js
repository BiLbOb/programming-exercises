const spiralMatrix = require('../../src/algorithms/spiralMatrix');

describe('spiralMatrix', () => {
    test('given a 1x1 unit matrix then return [1]', () => {
        expect(spiralMatrix([[1]])).toEqual([1]);
    });

    test('given a 3x1 unit matrix then return [1,2,3]', () => {
        expect(spiralMatrix([[1,2,3]])).toEqual([1,2,3]);
    });

    test('given a 1x3 unit matrix then return [1,2,3]', () => {
        expect(spiralMatrix([[1],[2],[3]])).toEqual([1,2,3]);
    });

    test('given a square matrix then return elements in spiral order', () => {
        expect(spiralMatrix([[1, 2, 3], [4, 5, 6], [7, 8, 9]])).toEqual([1, 2, 3, 6, 9, 8, 7, 4, 5]);
    });

    test('given a rectangular matrix then return elements in spiral order', () => {
        expect(spiralMatrix([[1, 2, 3], [4, 5, 6]])).toEqual([1, 2, 3, 6, 5, 4]);
    });
});