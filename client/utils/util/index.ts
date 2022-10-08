export const toLocalScale = (num: number) => {
  return num.toLocaleString('ko-KR')
}

export const ratingCalc = (rating: number) => {
  if (rating === 0) {
    return 0
  }

  switch (rating) {
    case 20:
      return 1.0
    case 40:
      return 2.0
    case 60:
      return 3.0
    case 80:
      return 4.0
    case 100:
      return 5.0
  }
}

export const ratingDecodeCal = (rating: number) => {
  if (rating === 0) {
    return 0
  }

  switch (rating) {
    case 1:
      return 20
    case 2:
      return 40
    case 3:
      return 60
    case 4:
      return 80
    case 5:
      return 100
  }
}
