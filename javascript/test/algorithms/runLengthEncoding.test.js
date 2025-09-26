const compressString = require('../../src/algorithms/runLengthEncoding');

describe('compressString', () => {
    test('should return original string when there are no duplicate chracters', () => {
        expect(compressString("abcde")).toBe("abcde");
    });

    test('should replace runs of each character when all characters are repeated', () => {
        expect(compressString("aaaabbbbcccc")).toBe("a4b4c4");
    });

    test('should return original string when compressed string is same length', () => {
        expect(compressString("aabbccddeeffgg")).toBe("aabbccddeeffgg");
    });

    test('should encode single characters when the resulting string is shorter', () => {
        expect(compressString("aabcccccaaa")).toBe("a2b1c5a3");
    });
});