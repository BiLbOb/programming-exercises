// See https://app.programiz.pro/community-challenges/challenge/javascript-matrix-spiral/info
// The spiral order starts from the top-left corner and circles around the matrix clockwise until all elements are listed.
function spiralMatrix(matrix) {
    if (matrix.length == 0) {
        return matrix;
    }
    if (matrix[0].length == 0) {
        return [];
    }
    const height = matrix.length;
    const width = matrix[0].length;

    var minCol = 0;
    var maxCol = width - 1;
    var minRow = 0;
    var maxRow = height - 1;
    var spiral = [];
    while (minCol <= maxCol && minRow <= maxRow) {
        // Run along the top (matrix[minRow][minCol] -> matrix[minRow][maxCol])
        for (var col = minCol; col <= maxCol; col++) {
            console.log("adding matrix[" + minRow + "][" + col + "] along top");
            spiral.push(matrix[minRow][col]);
        }
        // ... increment minRow...
        minRow++;
        if( minRow > maxRow ) break;

        // ... then down the right (matrix[minRow][maxCol] -> matrix[maxRow][maxCol])
        for (var row = minRow; row <= maxRow; row++) {
            console.log("adding matrix[" + row + "][" + maxCol + "] down RHS");
            spiral.push(matrix[row][maxCol]);
        }
        //  decrement maxCol
        maxCol--;
        if( minCol > maxCol ) break;

        // then along the bottom (matrix[maxRow][maxCol] -> matrix[maxRow][minCol])
        for (var col = maxCol; col >= minCol; col--) {
            console.log("adding matrix[" + maxRow + "][" + col + "] along bottom");
            spiral.push(matrix[maxRow][col]);
        }
        //  decrement maxRow
        maxRow--;
        if( minRow > maxRow ) break;

        // then go up the left hand side (matrix[maxRow][minCol] -> matrix[minRow][minCol])
        for (var row = maxRow; row >= minRow; row--) {
            console.log("adding matrix[" + row + "][" + minCol + "] down LHS");
            spiral.push(matrix[row][minCol]);
        }
        //  increment minCol
        minCol++;
    }
    // repeat until (minCol > maxCol) OR (minRow > maxRow)
     return spiral;
}

module.exports = spiralMatrix;