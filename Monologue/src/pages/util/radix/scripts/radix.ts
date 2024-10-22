const alphabetCharset = "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";

function radixVal(
  input: string,
  iRadix: number,
  oRadix: number,
  iCharset: string = alphabetCharset,
  oCharset: string = alphabetCharset,
) {
  try {
    if (iCharset.length < 2 || oCharset.length < 2) return "基数过小";
    if (iRadix > iCharset.length || oRadix > oCharset.length) return "字符集过短";
    let decimalVal = 0;
    for (let i = 0; i < input.length; i++) {
      decimalVal = decimalVal * iRadix + iCharset.indexOf(input[i]);
    }

    let result = "";
    if (decimalVal === 0) {
      return oCharset[0];
    }

    while (decimalVal > 0) {
      result = oCharset[decimalVal % oRadix] + result;
      decimalVal = Math.floor(decimalVal / oRadix);
    }

    return result;
  } catch (e) {
    console.error(e);
    return "转换失败";
  }
}

function decimalRadixVal(
  input: number,
  radix: number,
  iCharset: string = alphabetCharset,
  oCharset: string = alphabetCharset,
) {
  return radixVal(input.toString(), 10, radix, iCharset, oCharset);
}

export { radixVal, decimalRadixVal };
