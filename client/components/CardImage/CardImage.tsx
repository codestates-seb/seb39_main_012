/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
import React from 'react'
import styled from 'styled-components'

interface Props {
  imgUrl: string
  mode: 'post' | 'my' | 'review'
  height?: string
}
const modeOptions = {
  post: {
    borderRadius: '20px',
  },
  my: {
    borderRadius: '10px 10px 0px 30px;',
  },
  review: {
    borderRadius: '20px',
  },
}

function CardImage({imgUrl, mode}: Props) {
  const option = modeOptions[mode]
  return (
    <CardBox borderRadius={option.borderRadius}>
      <img src={imgUrl} />
    </CardBox>
  )
}

export default CardImage

const CardBox = styled.div<{borderRadius: string; height?: string}>`
  width: 100%;
  height: 230px;

  img {
    object-fit: cover;
    height: 100%;
    width: 100%;
    border-radius: ${({borderRadius}) => borderRadius};
  }
`
