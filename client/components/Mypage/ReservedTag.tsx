import {addReviewState} from '@/recoil/editOpen'
import {postIdState} from '@/recoil/mypage'
import {flexCenter} from '@/styles/css'
import {afterReservation} from '@/types/mypage'
import React from 'react'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'

interface Props {
  title: string
  reservation?: afterReservation
}

function ReservedTag({title, reservation}: Props) {
  const [, setPostId] = useRecoilState(postIdState)
  const [, setAddReview] = useRecoilState(addReviewState)
  return (
    <Contaienr
      title={title}
      onClick={() => {
        setAddReview(true)
        if (reservation) {
          setPostId({
            postsId: reservation.postsId,
            roomPrice: reservation.roomPrice,
            title: reservation.title,
            url: reservation.url,
          })
        }
      }}
    >
      <p>{title}</p>
    </Contaienr>
  )
}

export default ReservedTag

const Contaienr = styled.div<{title: string}>`
  ${flexCenter}

  background: #6fa767;
  border-radius: 20px;
  width: 60px;
  height: 25px;

  cursor: ${({title}) => (title === '예약완료' ? 'auto' : 'pointer')};
  p {
    font-size: 12px;
    color: white;
  }

  @media (max-width: 768px) {
    width: 50px;
    height: 20px;

    p {
      font-size: 8px;
    }
  }
`
