import {colors} from '@/styles/colors'
import React from 'react'
import styled from 'styled-components'
import Image from 'next/image'
import SectionTitle from './SectionTitle'
import AvailableServiceCard from './AvailableServiceCard'

const StoreDetail = () => {
  return (
    <Container>
      <CompanyIntoTop>
        <CompanyInfoTitleBox>
          <CompanyTitleText>
            <h1>화성시 방교동 도그플래닛</h1>
          </CompanyTitleText>
          <CompanyTitleSub>
            <CompanyTitleSubLeft>
              <CompanyTitleSubLeftReview>
                <span>별</span>
                <span>4.8</span>
              </CompanyTitleSubLeftReview>
              <CompanyTitleSubLeftTag>
                <span>#소형견</span>
                <span>#산책</span>
                <span>#CCTV</span>
              </CompanyTitleSubLeftTag>
            </CompanyTitleSubLeft>
            <CompanyTitleSubRight>
              <CompanyPageShare>
                <span>쉐어아이콘</span>
                <span>Share</span>
              </CompanyPageShare>
              <CompanyPageLike>
                <span>하트아이콘</span>
                <span>Save</span>
              </CompanyPageLike>
            </CompanyTitleSubRight>
          </CompanyTitleSub>
        </CompanyInfoTitleBox>
        {/* company image */}
        <CompanyImages>
          <CompanyImagesLeft>
            <ImageWrapper>
              <Image
                src="/images/company1.png"
                alt="company-detail-image-1"
                width={600}
                height={500}
              />
            </ImageWrapper>
          </CompanyImagesLeft>
          <CompanyImagesMiddle>
            <ImageWrapper>
              <Image
                src="/images/company2.png"
                alt="company-detail-image-2"
                width={300}
                height={250}
              />
            </ImageWrapper>
            <ImageWrapper>
              <Image
                src="/images/company3.png"
                alt="company-detail-image-3"
                width={300}
                height={250}
              />
            </ImageWrapper>
          </CompanyImagesMiddle>
          <CompanyImagesRight>
            <ImageWrapper>
              <Image
                src="/images/company4.png"
                alt="company-detail-image-4"
                width={300}
                height={250}
              />
            </ImageWrapper>
            <ImageWrapper>
              <Image
                src="/images/company5.png"
                alt="company-detail-image-5"
                width={300}
                height={250}
              />
            </ImageWrapper>
          </CompanyImagesRight>
        </CompanyImages>
      </CompanyIntoTop>
      {/* 하단작업 */}
      <CompanyInfoBottom>
        <CompanyDesc>
          <CompanyIntro>
            <SectionTitle title={'소개'} />
            <CompanyIntroBg>
              <p>
                도그플래닛을 이용해 주셔서 대단히 감사드리며, 사랑과 정성을 다해 반려견을
                돌보겠습니다. (다음 위탁 동의 사항을 꼭 숙지하신 후에 이용해 주시길 바랍니다.) ​
                ★도그플래닛은 15kg미만 중·소형견 전용 호텔입니다.★ 호텔 입실은 오후 7시 30분까지
                입니다. 반려견의 적응을 위해 마감시간 보다 30분 일찍 입실해주세요:) ​ 1. 중성화가
                되지 않은 강아지는 이용이 불가능합니다. ​ 2. 영역 표시(마킹)을 하는 수컷은 기저귀를
                착용할 수 있습니다. ​ 3. 짖음이 심하거나 입질, 공격성이 있는 반려견은 이용이 제한될
                수 있습니다. ​ 4. 도그플래닛은 위탁받은 반려견과 다른 반려견의 건강을 위하여
                위탁하고자 하는 반려견은 반드시 내·외부 기생충에 감염되어 있지 않아야 하며, 각종
                예방접종이 완료되어야 합니다. *종합 (DHPPL or DHPPI), 코로나, 켄넬코프, 신종플루,
                광견병 등 5차 접종 이후 항체 검사가 완료되지 않은 반려견은 이용이 불가합니다.* (
                ※1년 미만의 강아지는 접종 수첩 확인 및 항체검사확인 필요 ※ ) ​ 5. 입실 후
                공격적인성향을 보이거나 지나치게 소심한 반려견은 격리 및 퇴실 조치 될 수 있습니다. ​
                6. 호텔에 입실하는 반려견을 위해 평소 먹던 사료를 준비해주세요. *사료가 갑자기
                바뀌면 설사를 보일 수 있습니다. 아무 사료나 잘 먹는 아이라면 호텔에 있는 사료가
                제공됩니다.* * 예민한 반려견은 방석, 장난감, 보호자님의 냄새가 배어있는 옷가지 등을
                가져오시면 도움이 됩니다.* ​ 7. 약을 먹고 있거나, 특이사항 및 주의사항이 있는
                반려견은 특이사항 작성란에 꼭 작성하여 주시거나, 미리 알려주시기 바랍니다. ​ 8.
                위탁자가 위탁 기간 중이나 찾아가실 때 목욕(또는 미용)을 원하시는 경우, 목욕비(혹은
                미용비)는 별도로 부담하셔야 합니다. ​ 9. 도그플래닛에서는 위탁 기간 중 발생할 수
                있는 모든 응급상황이나 치료를 요하는 경우에 위탁자의 동의하에 치료를 하고 만약,
                연락이 안 될 경우에는 진료기록을 남겨 진료 기록에 근거하여 위탁자는 치료비를 별도로
                부담하셔야 합니다. ​ 10. 반려동물이 노령일 경우 노환이나 각종 지병으로 위탁 기간 중
                지병의 악화나 스트레스로 원치 않은 상황이 발생할 수 있음을 미리 알려드립니다. ​ 11.
                예정된 위탁 기간이 지났는데도 상호 연락이 되지 않거나 예정된 기간이 5일 이상 경과
                시에는 상기 반려견의 주인임을 포기함(법적 소유권 포기)로 간주 본 매장에서 임의
                처리합니다.
              </p>
            </CompanyIntroBg>
          </CompanyIntro>
          <CompanyAvailableService>
            <SectionTitle title={'이용 가능 서비스'} />
            <AvailableServiceCards>
              <AvailableServiceCard
                serviceTitle={'소형견 케어'}
                serviceContent={'7kg 미만'}
                iconImage={'/images/authGoogle.png'}
                alt={'소형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'소형견 케어'}
                serviceContent={'7kg 미만'}
                iconImage={'/images/authGoogle.png'}
                alt={'소형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'소형견 케어'}
                serviceContent={'7kg 미만'}
                iconImage={'/images/authGoogle.png'}
                alt={'소형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'소형견 케어'}
                serviceContent={'7kg 미만'}
                iconImage={'/images/authGoogle.png'}
                alt={'소형견 케어'}
              />
            </AvailableServiceCards>
          </CompanyAvailableService>
          <CompanyLocation>
            <SectionTitle title={'위치'} />
            <CompanyLocationAddress>
              <span>아이콘</span>
              <span>경기 화성시 동탄기흥로257번 나길 34-4 1층</span>
            </CompanyLocationAddress>

            <CompanyLocationMap>KAKAOMAP</CompanyLocationMap>
          </CompanyLocation>
          <CompanyReviews>
            <SectionTitle title={'리뷰 50건'} />
            <div></div>
            <div></div>
          </CompanyReviews>
        </CompanyDesc>
        <CompanyReservation>
          5<div>상세정보</div>
          <div>이용요금</div>
          <div>예약가능날짜</div>
        </CompanyReservation>
      </CompanyInfoBottom>
    </Container>
  )
}

export default StoreDetail

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  max-width: 120rem;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
    'Open Sans', 'Helvetica Neue', sans-serif;
`

const CompanyIntoTop = styled.div`
  flex: 1;
`

const CompanyInfoTitleBox = styled.div``

const CompanyTitleText = styled.div`
  h1 {
    font-size: 2.2rem;
    font-weight: 500;
  }
`

const CompanyTitleSub = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
`

const CompanyTitleSubLeft = styled.div`
  display: flex;
  font-size: 1.5rem;
`

const CompanyTitleSubLeftReview = styled.div`
  margin-right: 1rem;

  span:first-child {
  }
  span:last-child {
    margin-left: 0.5rem;
  }
`
const CompanyTitleSubLeftTag = styled.div`
  span {
    color: ${colors.grey4};
    margin-left: 0.5rem;
  }
`

const CompanyTitleSubRight = styled.div`
  display: flex;
  font-size: 1.2rem;
`

const CompanyPageShare = styled.div`
  span:last-child {
    margin-left: 0.5rem;
    text-decoration: underline;
    font-weight: 700;
    cursor: pointer;
  }
`
const CompanyPageLike = styled.div`
  margin-left: 2rem;
  span:first-child {
  }
  span:last-child {
    margin-left: 0.5rem;
    text-decoration: underline;
    font-weight: 700;
    cursor: pointer;
  }
`

const CompanyImages = styled.div`
  display: flex;
  margin-top: 2rem;
  cursor: pointer;
`
const ImageWrapper = styled.div`
  border-radius: 1.5rem;
  overflow: hidden;
  margin: 0.8rem 0.8rem 0 0;
`

const CompanyImagesLeft = styled.div`
  flex: 2;
`

const CompanyImagesMiddle = styled.div`
  flex: 1;
`

const CompanyImagesRight = styled.div`
  flex: 1;
`

const CompanyInfoBottom = styled.div`
  flex: 4;
  display: flex;
  margin-top: 5rem;
`
const CompanyDesc = styled.div`
  flex: 2;
`

const CompanyIntro = styled.div`
  p {
    font-size: 1.5rem;
    line-height: 3rem;
  }
`

const CompanyIntroBg = styled.div`
  background-color: ${colors.grey1};
  padding: 4rem;
  border-radius: 1rem;
  color: ${colors.grey4};
`

const CompanyAvailableService = styled.div`
  margin-top: 5rem;
`
const AvailableServiceCards = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;

  & > div:nth-child(odd) {
    background-color: ${colors.subColor2};
  }

  & > div:nth-child(even) {
    background-color: ${colors.subColor1};
  }
`

const CompanyLocation = styled.div`
  margin-top: 5rem;
  display: flex;
  flex-direction: column;
`

const CompanyLocationAddress = styled.div`
  span:last-child {
    font-size: 1.6rem;
    color: ${colors.grey4};
    margin-left: 1rem;
  }
`

const CompanyLocationMap = styled.div`
  display: flex;
`

const CompanyReservation = styled.div`
  flex: 1;
`
const CompanyReviews = styled.div`
  margin-top: 5rem;
`
