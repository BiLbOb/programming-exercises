function maximumProduct(arr) {
    
    if (arr.length < 3) throw new Error("Requires an array of at least 3 numbers, but got: [" + arr + "]");

    // The highest product will be the product of the 3 biggest numbers if positive 
    //  but if 2 of the numbers are negative, we can use the two most negative 
    //  if their absolute value is bigger than the three most positive
    //  so find the biggest 3 numbers and the 2 most negative
    const {
        includesZeros,
        positive,
        negative
    } = arr.reduce(
        (result, next) => {
            if (next == 0) {
                return { ...result, includesZeros: true };
            } else if (next > 0) {
                result.positive.push(next)
            } else if (next < 0) {
                result.negative.push(next);
            }
            return result;
        },
        {
            includesZeros: false,
            positive: [],
            negative: []
        }
    );
    positive.sort((a, b) => b - a);
    negative.sort();
    
    if (positive.length == 0) {
        if (includesZeros) {
            return 0;
        }
        // If no positive numbers or zeros, there must be at least 3 negative numbers
        // The biggest will use the least negative numbers
        return negative[0] * negative[1] * negative[2];
    }

    // If we have at least 2 negative numbers, is the product of their absolute values
    //  greater than the 3 most positive?
    if (negative.length >= 2) {
        const absolutes = negative.map(n => Math.abs(n));
        
        const compareTo = positive.slice(0, 3);
        const smaller = compareTo.length > 1 ? compareTo.pop() : 1;
        const bigger = compareTo.length > 1 ? compareTo.pop() : 1;
        

        if (absolutes[1] * absolutes[0] > smaller * bigger) {
            return compareTo[0] * absolutes[1] * absolutes[0];
        }
    }

    return positive[0] * positive[1] * positive[2];
}

module.exports = maximumProduct;