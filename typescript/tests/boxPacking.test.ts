import { addRectangle, fitRectangle } from '../src/algorithms/boxPacking';

describe('boxPacking', () => {
  describe('fitRectangle', () => {
    test('when there is insufficient space in prior rows then the rectangle should be placed at the end', () => {
      const result = fitRectangle({ w: 6, h: 4 });
      expect(result).toBe({ x: 0, y: 7 });
    });

    test('when there is sufficient space on the last rows then the rectangle should be placed on the last rows', () => {
      const result = fitRectangle({ w: 5, h: 3 });
      expect(result).toBe({ x: 5, y: 4 });
    });

    test('when there is gap with sufficient space then the rectangle should be placed in the gap', () => {
      const result = fitRectangle({ w: 3, h: 2 });
      expect(result).toBe({ x: 0, y: 2 });
    });
    
    test('when there is sufficient width but not height in a gap then the rectangle should be placed in a longer space', () => {
      const result = fitRectangle({ w: 4, h: 5 });
      expect(result).toBe({ x: 4, y: 4 });
    });

    test('when there is sufficient width but not height in a gap then the rectangle should be placed in a longer space', () => {
      const result = fitRectangle({ w: 4, h: 5 });
      expect(result).toBe({ x: 4, y: 4 });
    });
  });

describe('addRectangle', () => { 
    test('when multiple rectangles are added should take existing allocations into account', () => {
      const fillsHole = addRectangle({ w: 3, h: 2 });
      expect(fillsHole).toBe({ x: 0, y: 2 });

      const fitsOnRight = addRectangle({ w: 3, h: 2 });
      expect(fitsOnRight).toBe({ x: 6, y: 3 });
      
      const fitsUnderneath = addRectangle({ w: 3, h: 2 });
      expect(fitsUnderneath).toBe({ x: 5, y: 5 });

      const fitsDownRight = addRectangle({ w: 1, h: 5 });
      expect(fitsDownRight).toBe({ x: 9, y: 0 });

      const needsNewRow = addRectangle({ w: 4, h: 4 });
      expect(needsNewRow).toBe({ x: 0, y: 9 });

    });
});

});
