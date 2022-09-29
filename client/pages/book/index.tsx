import React from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import AuthButton from '@/components/AuthButton/AuthButton'

const index = () => {
  return (
    <BookingConfirm>
      <BookingUserTitle>
        <h1>예약자 정보</h1>
      </BookingUserTitle>
      <BookingConfirmContainer>
        <BookingConfirmLeft>
          <BookingUserInfo>
            <BookingUserInfoCard>
              <BookingUserInfoItem>
                <Label>이름</Label>
                <Input type="text" placeholder="실명을 입력하세요." />
              </BookingUserInfoItem>
              <BookingUserInfoItem>
                <Label>휴대전화번호</Label>
                <Input type="tel" placeholder="-빼고 숫자만 입력하세요" />
              </BookingUserInfoItem>
              <BookingUserInfoItem>
                <Label>이메일</Label>
                <Input type="email" placeholder="예약확정 안내가 전송됩니다." />
              </BookingUserInfoItem>
            </BookingUserInfoCard>
          </BookingUserInfo>
          <div>
            <GetDogInfo>
              <span>반려견 정보</span>
              <button>+ 정보 불러오기</button>
            </GetDogInfo>
          </div>
          <div>{/* dog profile card */}</div>
        </BookingConfirmLeft>
        <BookingConfirmRight>
          <BookingConfirmRightContainer>
            <BookingDetailsTitle>
              <h2>호텔링 예약 정보</h2>
            </BookingDetailsTitle>
            <BookingHotelDetails>
              <BookingHotelDetail>
                <h3>애견호텔</h3>
                <div>경기도 화성시 방교동 도그플래닛</div>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>반려동물</h3>
                <div>중형견 1마리</div>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>체크인</h3>
                <div>2022. 09. 22 (목) 오전 09:00</div>
              </BookingHotelDetail>
              <BookingHotelDetail>
                <h3>체크아웃</h3>
                <div>2022. 09. 24 (목) 오후 05:00 </div>
              </BookingHotelDetail>
              <div></div>
            </BookingHotelDetails>
            <BookingHotelPrice>
              <BookingHotelPriceTitle>
                <h3>총 결제 금액 (VAT포함)</h3>
                <h1>59,000원</h1>
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
    </BookingConfirm>
  )
}

export default index

const BookingConfirm = styled.div`
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
