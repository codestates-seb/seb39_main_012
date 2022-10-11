/* eslint-disable @typescript-eslint/no-explicit-any */
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import AuthButton from '@/components/AuthButton/AuthButton'
import {DogCard, Users} from '@/types/mypage'
import {userService} from '@/apis/MyPageAPI'
import LocalStorage from '@/utils/util/localStorage'
import {booking} from '@/types/book'
import {getDayOfDate} from '@/components/StoreDetail/StoreDetail'
import {bookingService} from '@/apis/bookingAPI'
import {useRouter} from 'next/router'
import Footer from '@/components/Layout/Footer/Footer'
import DogProfile from '@/components/Book/DogProfile'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import {toast} from 'react-toastify'

const Book = () => {
  const router = useRouter()
  const postId = router.query.postId
  const [userInfo, setUserInfo] = useState<Users | undefined>()
  const [dogsInfo, setDogsInfo] = useState<DogCard[] | undefined>()
  const [tempBookingInfo, setTempBookingInfo] = useState<booking | undefined>()
  const [clickedDog, setClickedDog] = useState(false)
  const [dogId, setDogId] = useState<number[]>([])

  useEffect(() => {
    userService.getMyPage().then((result) => {
      setUserInfo(result.data.users)
      setDogsInfo(result.data.users.dogCardList)
    })

    const tempBooking = JSON.parse(LocalStorage.getItem('tempBooking') || (null as any))
    setTempBookingInfo(tempBooking)
  }, [])

  if (!userInfo || !dogsInfo) {
    return <LoadingSpinner></LoadingSpinner>
  }

  const selectDogHandler = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault()
    setClickedDog(!clickedDog)
  }

  const dayTransformer = (date: string) => {
    const splitDates = (date || '').split('-')
    const year = splitDates[0]
    const month = splitDates[1]
    const day = splitDates[2]
    return `${year}년 ${month}월 ${day}일 (${getDayOfDate(date)})`
  }

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const reservationCreateDto = {
      ...tempBookingInfo,
    }

    const reservationUserInfoDto = {
      dogCardsId: dogId,
      userInfo: {
        name: userInfo?.username,
        phone: userInfo?.phone,
        email: userInfo?.email,
      },
    }

    const requestForm = {
      reservationCreateDto,
      reservationUserInfoDto,
    }

    if (dogId.length === 0) {
      toast.error('반려견을 선택해주세요.')
    }

    // if (dogId.length !== dogCount) {
    //   toast.error('반려견 수가 일치해야합니다.')
    // }

    const result = await bookingService.postBooking(Number(postId), requestForm as any)

    if (result.status === 201) {
      router.push(`/book_confirm/${result.data.reservedId}`)
      LocalStorage.removeItem('tempBooking')
    } else {
      toast.error('예약에 실패했습니다.')
    }
  }

  return (
    <BookingConfirm onSubmit={onSubmit}>
      <BookingUserTitle>
        <h1>예약자 정보</h1>
      </BookingUserTitle>
      <BookingConfirmContainer>
        <BookingConfirmLeft>
          <BookingUserInfo>
            <BookingUserInfoCard>
              <BookingUserInfoItem>
                <Label>이름</Label>
                <Input
                  type="text"
                  value={userInfo?.username}
                  placeholder="실명을 입력하세요."
                  readOnly
                />
              </BookingUserInfoItem>
              <BookingUserInfoItem>
                <Label>휴대전화번호</Label>
                <Input
                  type="tel"
                  value={userInfo?.phone}
                  placeholder="-빼고 숫자만 입력하세요"
                  readOnly
                />
              </BookingUserInfoItem>
              <BookingUserInfoItem>
                <Label>이메일</Label>
                <Input
                  type="email"
                  value={userInfo?.email}
                  placeholder="예약확정 안내가 전송됩니다."
                  readOnly
                />
              </BookingUserInfoItem>
            </BookingUserInfoCard>
          </BookingUserInfo>
          <div>
            <GetDogInfo>
              <span>반려견 정보</span>
              <button onClick={selectDogHandler}>+ 정보 불러오기</button>
            </GetDogInfo>
          </div>
          <DogProfileField>
            {clickedDog && (
              <>
                <GetDogInfoText>호텔링을 맡길 반려견을 선택해주세요.</GetDogInfoText>
                <DogCardBox>
                  {dogsInfo.map((dog) => (
                    <DogProfile key={dog.id} dog={dog as any} setDogId={setDogId} />
                  ))}
                </DogCardBox>
              </>
            )}
          </DogProfileField>
        </BookingConfirmLeft>
        <BookingConfirmRight>
          <BookingConfirmRightContainer>
            <BookingDetailsTitle>
              <h2>호텔링 예약 정보</h2>
            </BookingDetailsTitle>
            <BookingHotelDetails>
              <BookingHotelDetail>
                <h3>애견호텔</h3>
                <div>{tempBookingInfo?.companyName}</div>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>반려동물</h3>
                <NumberOfDogs>
                  <DogNumber>
                    {tempBookingInfo?.dto.map.small
                      ? `소형견: ${tempBookingInfo?.dto.map.small}마리 \n`
                      : ''}
                  </DogNumber>
                  <DogNumber>
                    {tempBookingInfo?.dto.map.medium
                      ? `중형견: ${tempBookingInfo?.dto.map.medium}마리 \n`
                      : ''}
                  </DogNumber>
                  <DogNumber>
                    {tempBookingInfo?.dto.map.big
                      ? `대형견: ${tempBookingInfo?.dto.map.big}마리 \n`
                      : ''}
                  </DogNumber>
                </NumberOfDogs>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>체크인</h3>
                <div>
                  {`${dayTransformer(tempBookingInfo?.dto.checkInDate as string)} ${
                    tempBookingInfo?.dto.checkInTime
                  }`}
                </div>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>체크아웃</h3>
                <div>
                  {`${dayTransformer(tempBookingInfo?.dto.checkOutDate as string)} ${
                    tempBookingInfo?.dto.checkOutTime
                  } `}
                </div>
              </BookingHotelDetail>
              <div></div>
            </BookingHotelDetails>
            <BookingHotelPrice>
              <BookingHotelPriceTitle>
                <h3>총 결제 금액 (VAT포함)</h3>
                <h1>
                  {tempBookingInfo?.totalPrice
                    .toString()
                    .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',')}
                  원
                </h1>
              </BookingHotelPriceTitle>
              <BookingHotelPriceInfo>
                <p>· 해당 예약가는 세금, 봉사료가 포함된 금액입니다.</p>
                <p>· 결제 완료 후 예약자 이름으로 체크인 하시면 됩니다.</p>
              </BookingHotelPriceInfo>
              <BookingHotelPriceButton>
                <AuthButton title={'결제하기'} height={'6rem'} />
              </BookingHotelPriceButton>
            </BookingHotelPrice>
          </BookingConfirmRightContainer>
        </BookingConfirmRight>
      </BookingConfirmContainer>
      <Footer />
    </BookingConfirm>
  )
}

export default Book

const BookingConfirm = styled.form`
  width: 100%;
  margin-top: 2rem;
`

const BookingConfirmContainer = styled.div`
  display: flex;
  gap: 50px;
`

const BookingConfirmLeft = styled.div`
  flex: 3;
  display: flex;
  flex-direction: column;
`

const BookingUserTitle = styled.div`
  padding: 1rem 0;

  h1 {
    font-size: 1.7rem;
    font-weight: 600;
  }
`

const BookingUserInfo = styled.div`
  border-top: 0.5px solid rgb(229, 229, 229);
  padding-top: 4rem;
`

const BookingUserInfoCard = styled.div`
  margin-left: 1rem;
  display: flex;
  flex-direction: column;
`

const BookingUserInfoItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1.5rem;
  width: 90%;
`

const Label = styled.label`
  flex: 1;
  font-size: 1.4rem;
  font-weight: 500;
  color: ${colors.grey4};
  margin-right: 1rem;
`

const Input = styled.input`
  flex: 5;
  width: 80%;
  height: 2.5rem;
  border: 1px solid rgb(229, 229, 229);
  border-radius: 0.3rem;
  padding: 0.5rem 1rem;
  font-size: 1.3rem;
  color: rgb(34, 34, 34);
  outline: none;

  ::placeholder {
    color: rgb(215, 215, 215);
    font-size: 1.2rem;
  }

  :focus {
    border: 1px solid ${colors.mainColor};
  }
`

const GetDogInfo = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  border-bottom: 0.5px solid rgb(229, 229, 229);
  padding: 1rem 0;
  margin-top: 2rem;

  span {
    font-size: 1.7rem;
    font-weight: 600;
  }

  button {
    background-color: ${colors.mainColor};
    outline: none;
    border: none;
    border-radius: 1rem;
    color: rgb(255, 255, 255);
    font-size: 1rem;
    padding: 0.5rem 1rem;
    cursor: pointer;

    &:hover {
      background-color: rgba(111, 167, 103, 0.8);
    }
  }
`

const DogProfileField = styled.div``

const DogCardBox = styled.div`
  display: flex;
  gap: 1rem;
`

const GetDogInfoText = styled.div`
  margin: 2rem 0;
  font-size: 1.3rem;
  color: ${colors.grey4};
`

const BookingConfirmRight = styled.div`
  flex: 1;
  padding: 2rem;
  border: 1px solid rgb(229, 229, 229);
  border-radius: 1rem;
`

const BookingConfirmRightContainer = styled.div``

const BookingDetailsTitle = styled.div`
  color: ${colors.mainColor};
  font-size: 1.4rem;
  font-weight: 700;
`

const BookingHotelDetails = styled.div`
  margin: 2.5rem 0;
  border-bottom: 2px dotted ${colors.mainColor};
`

const BookingHotelDetail = styled.div`
  margin-bottom: 4rem;

  h3 {
    font-size: 1.2rem;
    color: rgb(176, 176, 176);
    font-weight: 600;
    margin-bottom: 0.8rem;
  }

  div {
    font-size: 1.6rem;
    font-weight: 700;
  }
`

const NumberOfDogs = styled.div`
  margin-top: 0.5rem;
`

const DogNumber = styled.div`
  margin-bottom: 1rem;
`

const BookingHotelPrice = styled.div`
  h3 {
    font-size: 1.2rem;
    color: rgb(164, 164, 164);
    font-weight: 600;
    margin-bottom: 0.8rem;
  }

  h1 {
    font-size: 2.5rem;
    font-weight: 800;
  }
`

const BookingHotelPriceTitle = styled.div`
  h3 {
    color: rgb(176, 176, 176);
  }
`

const BookingHotelPriceInfo = styled.div`
  margin: 1rem 0;
  p {
    color: rgb(196, 196, 196);
    font-size: 1.2rem;
    line-height: 1.5rem;
    margin-bottom: 0.5rem;
  }
`

const BookingHotelPriceButton = styled.div`
  margin: 2rem 0;
`
