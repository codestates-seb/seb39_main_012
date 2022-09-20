import React from 'react'
import {colors} from '@/styles/colors'
import {AiFillStar} from 'react-icons/ai'

const ReviewStarGenerator = (reviewStarNum: number) => {
  return [1, 2, 3, 4, 5].map((n, i) => (
    <AiFillStar
      key={i}
      color={reviewStarNum >= i + 1 ? colors.mainColor : 'rgba(211, 211, 211)'}
      size="16"
    />
  ))
}

export default ReviewStarGenerator
