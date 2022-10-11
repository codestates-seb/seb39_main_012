import {reviewService} from '@/apis/ReviewAPI'
import {dataState} from '@/recoil/mypage'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {Review} from '@/types/mypage'
import React, {useState} from 'react'
import {toast} from 'react-toastify'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'
import Pagination from '../CeoPage/Pagination'
import EditReviewModal from './EditReviewModal'

interface Props {
  reviews: Review[]
  username: string
}

function ReviewTable({reviews, username}: Props) {
  const [isChange, setIsChange] = useRecoilState(dataState)
  const [isOpen, setIsOpen] = useState(false)
  const [limit] = useState(5)
  const [page, setPage] = useState(1)
  const offset = (page - 1) * limit // offset = 시작점
  const [curReview, setCurReview] = useState<Review>()

  return (
    <ReviewTableBox>
      {isOpen && <EditReviewModal setIsOpen={setIsOpen} curReview={curReview} />}
      <TableHead>
        <TableHeadNum>번호</TableHeadNum>
        <TableHeadClassfic>분류</TableHeadClassfic>
        <TableHeadTitle>제목</TableHeadTitle>
        <TableHeadWrite>작성자</TableHeadWrite>
        <TableHeadDate>작성일</TableHeadDate>
      </TableHead>
      <TableBody>
        {reviews.length === 0 ? (
          <NoContent>작성된 리뷰가 없습니다.</NoContent>
        ) : (
          reviews.slice(offset, offset + limit).map((review: Review) => (
            <TableTr key={review.id}>
              <TableBodyNum>{review.id}</TableBodyNum>
              <TableBodyClassfic>호텔</TableBodyClassfic>
              <TableBodyTitle>{review.title}</TableBodyTitle>
              <TableBodyWrite>{username}</TableBodyWrite>
              <TableBodyDate>{String(review.createdAt).slice(0, 10)}</TableBodyDate>
              <TableButtonBox>
                <button
                  onClick={() => {
                    setIsOpen(true)
                    setCurReview(review)
                  }}
                >
                  수정
                </button>
                <button
                  onClick={async () => {
                    if (window.confirm('정말 삭제하시겠습니까?')) {
                      const result = await reviewService.deleteReview(review.id)
                      if (result.status === 200) {
                        toast.success('리뷰가 삭제되었습니다.')
                        setIsChange(!isChange)
                      }
                    }
                  }}
                >
                  삭제
                </button>
              </TableButtonBox>
            </TableTr>
          ))
        )}
      </TableBody>
      <Pagination total={reviews.length} limit={limit} page={page} setPage={setPage} />
    </ReviewTableBox>
  )
}

export default ReviewTable

const ReviewTableBox = styled.div`
  width: 100%;
  margin-bottom: 30px;
`
const TableHead = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  height: 30px;
  background-color: ${colors.grey1};
  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 24px;
  text-align: center;

  @media (max-width: 768px) {
    font-size: 10px;
  }
`
const TableHeadNum = styled.div`
  width: 10%;
`
const TableHeadClassfic = styled.div`
  width: 10%;
`
const TableHeadTitle = styled.div`
  width: 50%;

  @media (max-width: 768px) {
    width: 35%;
  }
`
const TableHeadWrite = styled.div`
  width: 10%;
`
const TableHeadDate = styled.div`
  width: 10%;
`

const TableBody = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`

const TableTr = styled.div`
  width: 100%;
  display: flex;
  text-align: center;
  flex-wrap: wrap;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 24px;
  border-bottom: solid 1px ${colors.grey1};
  padding: 10px 0px;
  @media (max-width: 768px) {
    font-size: 8px;
  }

  @media (max-width: 390px) {
    font-size: 8px;
  }
`

const TableBodyNum = styled(TableHeadNum)``
const TableBodyClassfic = styled(TableHeadClassfic)``
const TableBodyTitle = styled(TableHeadTitle)``
const TableBodyWrite = styled(TableHeadWrite)``
const TableBodyDate = styled(TableHeadDate)``

const TableButtonBox = styled.div`
  display: flex;
  justify-content: center;
  gap: 10px;
  width: 10%;
  font-size: 10px;
  button {
    border-radius: 20px;
    border: none;
    width: 40%;
    white-space: nowrap;
    cursor: pointer;

    @media (max-width: 768px) {
      width: 24%;
      font-size: 5px;
    }
  }

  button:nth-child(1) {
    background-color: ${colors.mainColor};
    color: white;
  }

  @media (max-width: 768px) {
    width: 25%;
  }
`

const NoContent = styled.div`
  ${flexCenter};
  margin-top: 25px;
  width: 100%;
  font-size: 20px;
  color: gray;
  height: 50px;
`
