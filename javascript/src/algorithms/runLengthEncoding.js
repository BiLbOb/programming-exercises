function compressString(str) {
    const characters = str.split("");
    const {
        partial,
        nextBit,
    } = characters.reduce(
        (acc, val) => {
            if (acc.current == val) {
                const newCount = acc.count + 1;
                return {
                    ...acc,
                    count: newCount,
                    nextBit: (val + newCount),
                };
            } else {
                return {
                    ...acc,
                    count: 1,
                    current: val,
                    partial: acc.partial + acc.nextBit,
                    nextBit: (val + 1),
                };
            }
        },
        {
            current: "",
            count: 0,
            partial: "",
            nextBit: "",
        }
    );
    // We need to append the last "block" manually
    const compressed = partial + nextBit;

    return (compressed.length < str.length) ? compressed : str;
}

module.exports = compressString;