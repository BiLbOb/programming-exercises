// Packing rectangles into columns
// 10 columns wide
// place each rectangle in the topmost, leftmost position available
//
// This can be illustrated as follows:
//
// |000011222 |
// |000011222 |
// |    11222 |
// |    11    |
// |33333     |
// |33333     |
// |33333     |
// |          |
//
const rectangles = [
  { x: 0, y: 0, w: 4, h: 2 },
  { x: 4, y: 0, w: 2, h: 4 },
  { x: 6, y: 0, w: 3, h: 3 },
  { x: 0, y: 4, w: 5, h: 5 },
];

type Rectangle = { w: number; h: number };

type Position = { x: number; y: number };

export function fitRectangle(rect: Rectangle): Position {
  return { x: 0, y: 0 };
}

export function addRectangle(rect: Rectangle): Position {
  return { x: 0, y: 0 };
}
