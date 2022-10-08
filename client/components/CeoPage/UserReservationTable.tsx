import {ceoService} from '@/apis/CeoPageAPI'
import {dataState} from '@/recoil/mypage'
import {colors} from '@/styles/colors'
import {flexCenter} from '@/styles/css'
import {Reservation} from '@/types/ceopage'
import React, {useState} from 'react'
import {toast} from 'react-toastify'
import {useRecoilState} from 'recoil'
import styled from 'styled-components'
import Pagination from './Pagination'

interface Props {
  reservations: Reservation[]
  title: string
}

function UserReservationTable({reservations, title}: Props) {
  const [isChange, setIsChange] = useRecoilState(dataState)
  const [limit, setLimit] = useState(5)
  const [page, setPage] = useState(1)
  const offset = (page - 1) * limit // offset = 시작점

  return (
    <ReviewTableBox>
      <TableHead>
        <TableHeadNum>예약번호</TableHeadNum>
        <TableHeadTitle>점포명</TableHeadTitle>
        <TableHeadCheckIn>체크인</TableHeadCheckIn>
        <TableHeadCheckOut>체크아웃</TableHeadCheckOut>
        <TableHeadUsers>예약자</TableHeadUsers>
        <TableHeadEtc></TableHeadEtc>
      </TableHead>
      <TableBody>
        {reservations.length === 0 && <NoContent>예약된 유저가 없습니다.</NoContent>}
        {reservations.slice(offset, offset + limit).map((reservation) => (
          <TableTr key={reservation.reservedId}>
            <TableBodyNum>{reservation.reservedId}</TableBodyNum>
            <TableBodyTitle>{title}</TableBodyTitle>
            <TableBodyCheckIn>
              {reservation.checkInDate + ' ' + reservation.checkInTime}
            </TableBodyCheckIn>
            <TableBodyCheckOut>
              {reservation.checkOutDate + ' ' + reservation.checkOutTime}
            </TableBodyCheckOut>
            <TableBodyUsers>
              {reservation.userInfo.name + '(' + reservation.userInfo.phone + ')'}
            </TableBodyUsers>
            <TableBodyEtc>
              <button
                onClick={async () => {
                  if (window.confirm('예약을 취소하시겠습니까?')) {
                    const result = await ceoService.deleteUserReservation(reservation.reservedId)

                    if (result.status === 204) {
                      toast.success('예약이 취소되었습니다.')
                      setIsChange(!isChange)
                      return
                    }
                    toast.error('예약 취소에 실패했습니다.')
                  }
                }}
              >
                취소하기
              </button>
            </TableBodyEtc>
          </TableTr>
        ))}
      </TableBody>
      <Pagination total={reservations.length} limit={limit} page={page} setPage={setPage} />
    </ReviewTableBox>
  )
}

export default UserReservationTable

const ReviewTableBox = styled.div`
  width: 100%;
  margin-bottom: 30px;
`

// 15 20 15 15 15 20
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
  width: 15%;
`
const TableHeadTitle = styled.div`
  width: 20%;
`
const TableHeadCheckIn = styled.div`
  width: 15%;

  @media (max-width: 768px) {
    width: 35%;
  }
`
const TableHeadCheckOut = styled.div`
  width: 15%;
`

const TableHeadUsers = styled.div`
  width: 15%;
`

const TableHeadEtc = styled.div`
  width: 20%;
`

// Body

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
const TableBodyTitle = styled(TableHeadTitle)``
const TableBodyCheckIn = styled(TableHeadCheckIn)``
const TableBodyCheckOut = styled(TableHeadCheckOut)``
const TableBodyUsers = styled(TableHeadUsers)``
const TableBodyEtc = styled(TableHeadEtc)`
  button {
    padding: 6px 0;
    border-radius: 20px;
    border: none;
    width: 80px;
    white-space: nowrap;
    cursor: pointer;
  }
`

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
  width: 90%;
  font-size: 20px;
  color: gray;
  height: 50px;
`
