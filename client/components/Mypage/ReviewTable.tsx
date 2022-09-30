import {colors} from '@/styles/colors'
import React, {useState} from 'react'
import styled from 'styled-components'
import AddReviewModal from './AddReviewModal'

function ReviewTable() {
  const [isOpen, setIsOpen] = useState(false)
  return (
    <ReviewTableBox>
      {isOpen && <AddReviewModal setIsOpen={setIsOpen} />}
      <TableHead>
        <TableHeadNum>번호</TableHeadNum>
        <TableHeadClassfic>분류</TableHeadClassfic>
        <TableHeadTitle>제목</TableHeadTitle>
        <TableHeadWrite>작성자</TableHeadWrite>
        <TableHeadDate>작성일</TableHeadDate>
      </TableHead>
      <TableBody>
        <TableTr>
          <TableBodyNum>1</TableBodyNum>
          <TableBodyClassfic>호텔</TableBodyClassfic>
          <TableBodyTitle>호텔이 너무 좋아서 편안하게 여행 갔다가 왔어요</TableBodyTitle>
          <TableBodyWrite>김견주</TableBodyWrite>
          <TableBodyDate>2021.08.01</TableBodyDate>
          <TableButtonBox>
            <button onClick={() => setIsOpen(true)}>수정</button>
            <button>삭제</button>
          </TableButtonBox>
        </TableTr>
      </TableBody>
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
