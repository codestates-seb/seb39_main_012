import React, {useState, useEffect} from 'react'
import {useRouter} from 'next/router'
import {colors} from '@/styles/colors'
import styled from 'styled-components'
import Image from 'next/image'
import SectionTitle from './SectionTitle'
import AvailableServiceCard from './AvailableServiceCard'
import {AiFillStar} from 'react-icons/ai'
import {AiFillHeart} from 'react-icons/ai'
import {AiOutlineHeart} from 'react-icons/ai'
import {FiShare} from 'react-icons/fi'
import {FiMapPin} from 'react-icons/fi'
import CompanyReviewCard from './CompanyReviewCard'
import ReviewStarGenerator from './ReviewStarGenerator'
import CompanyReviewListCard from './CompanyReviewListCard'

const StoreDetail = () => {
  const router = useRouter()
  const {postId} = router.query
  const [addLike, setAddLike] = useState(false)

  useEffect(() => {
    if (window.Kakao) {
      const kakao = window.Kakao
      if (!kakao.isInitialized()) {
        window.Kakao.init(process.env.NEXT_PUBLIC_KAKAO_API_KEY)
      }
    }
  }, [])

  const shareKakao = () => {
    // window.Kakao.Link.sendCustom
    window.Kakao.Share.sendScrap({
      requestUrl: location.origin + location.pathname,
      // requestUrl: location.href,
      // templateId: 83072,
      // })
      templateId: 83074,
      templateArgs: {
        title: '화성시 방교동 도그플래닛',
        description: '호텔링 바로 예약하기',
        pageId: postId,
        // image_url:''
        // pagePathname: location.pathname,
      },
      installTalk: true,
    })
  }

  const addLikeHandler = () => {
    setAddLike(!addLike)
  }

  return (
    <Container>
      <CompanyInfoTop>
        <CompanyInfoTitleBox>
          <CompanyTitleText>
            <h1>화성시 방교동 도그플래닛</h1>
          </CompanyTitleText>
          <CompanyTitleSub>
            <CompanyTitleSubLeft>
              <CompanyTitleSubLeftReview>
                <span>
                  <AiFillStar color={colors.mainColor} size="20" />
                </span>
                <span>4.8</span>
              </CompanyTitleSubLeftReview>
              <CompanyTitleSubLeftTag>
                <span>#소형견</span>
                <span>#산책</span>
                <span>#CCTV</span>
              </CompanyTitleSubLeftTag>
            </CompanyTitleSubLeft>
            <CompanyTitleSubRight>
              <CompanyPageShare onClick={shareKakao}>
                <span>
                  <FiShare />
                </span>
                <span>Share</span>
              </CompanyPageShare>
              <CompanyPageLike onClick={addLikeHandler}>
                <span>{addLike ? <AiFillHeart size="15" /> : <AiOutlineHeart size="15" />}</span>
                <span>Save</span>
              </CompanyPageLike>
            </CompanyTitleSubRight>
          </CompanyTitleSub>
        </CompanyInfoTitleBox>
        <CompanyImages>
          <CompanyImageLeft>
            <ImageWrapper>
              <Image
                src="/images/company1.png"
                alt="company-detail-image-1"
                width={640}
                height={505}
              />
            </ImageWrapper>
          </CompanyImageLeft>
          <CompanyImageRight>
            <ImageWrapper>
              <Image
                src="/images/company2.png"
                alt="company-detail-image-2"
                width={320}
                height={250}
              />
            </ImageWrapper>
            <ImageWrapper>
              <Image
                src="/images/company3.png"
                alt="company-detail-image-3"
                width={320}
                height={250}
              />
            </ImageWrapper>
            <ImageWrapper>
              <Image
                src="/images/company4.png"
                alt="company-detail-image-4"
                width={320}
                height={250}
              />
            </ImageWrapper>
            <ImageWrapper>
              <Image
                src="/images/company5.png"
                alt="company-detail-image-5"
                width={320}
                height={250}
              />
            </ImageWrapper>
          </CompanyImageRight>
        </CompanyImages>
      </CompanyInfoTop>
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
                iconImageSrc={'/images/authGoogle.png'}
                alt={'소형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'중형견 케어'}
                serviceContent={'7kg~14kg 미만'}
                iconImageSrc={'/images/authGoogle.png'}
                alt={'중형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'대형견 케어'}
                serviceContent={'15kg 이상'}
                iconImageSrc={'/images/authGoogle.png'}
                alt={'중형견 케어'}
              />
              <AvailableServiceCard
                serviceTitle={'노견 케어'}
                serviceContent={'8년이상 노견돌봄 가능'}
                iconImageSrc={'/images/authGoogle.png'}
                alt={'노견 케어'}
              />
            </AvailableServiceCards>
          </CompanyAvailableService>
          <CompanyLocation>
            <SectionTitle title={'위치'} />
            <CompanyLocationAddress>
              <span>
                <FiMapPin size={14} />
              </span>
              <span>경기 화성시 동탄기흥로257번 나길 34-4 1층</span>
            </CompanyLocationAddress>
            <CompanyLocationMap></CompanyLocationMap>
          </CompanyLocation>
          <CompanyReviews>
            <SectionTitle title={'리뷰'} sub1={'50'} sub2={'건'} />
            <CompanyReviewsCards>
              <CompanyReviewCard
                reviewImageSrc={'/images/review1.jpg'}
                alt={'company-review-image1'}
                reviewTitle={'너무 만족했습니다!'}
                reviewStarNum={5}
                reviewContent={
                  '럭키를 잘 케어해주셔서 덕분에 마음 편히 여름 휴가를 다녀올 수 있었습니다! 정말 감사합니다! :D'
                }
                reviewDate={'2022.08.21'}
                reviewerName={'뭉식구'}
              />
              <CompanyReviewCard
                reviewImageSrc={'/images/review2.jpg'}
                alt={'company-review-image2'}
                reviewTitle={'귀요미 덕화짱 호텔에서 잘 쉬다가 왔어여!'}
                reviewStarNum={4}
                reviewContent={
                  '덕화가 사회성이 많이 부족해서 호텔링하기 전에 걱정이 많았는데 오히려 몇일 친구들이랑 지내면서 사교성이 생긴 것 같네용 ㅎㅎ'
                }
                reviewDate={'2022.09.10'}
                reviewerName={'지수정'}
              />
              <CompanyReviewCard
                reviewImageSrc={'/images/review3.jpg'}
                alt={'company-review-image3'}
                reviewTitle={'반장이 맡겼습니다~~'}
                reviewStarNum={4}
                reviewContent={
                  '추석 연휴동안 여행을 가게되었는데 반장이 맡길 곳이 없어서 이번에 처음 호텔링 서비스 이용하게 되었습니다~! 시설도 깨끗하고 케어해주시는 분도 친절하셨습니다.'
                }
                reviewDate={'2022.09.21'}
                reviewerName={'정환민'}
              />
            </CompanyReviewsCards>
            <CompanyReviewsList>
              <CompanyReviewListCard
                reviewStarNum={5}
                reviewerName={'김민겸'}
                reviewDate={'2022.09.21'}
                reviewTitle={'울 댕댕이 호텔가서 친구들이랑 잘 놀아서 좋습니다~'}
                reviewContent={
                  '보내주신 사진보니까 저희집 댕댕이가 친구들이랑 아주 잘노네요 ㅎㅎ 도그플래닛인싸입니다 ㅋㅋ 처음 맡길 때는 너무 말썽부리지 않을까 걱정반 불안반으로 맡기게 되었는데 결론은 선택을 잘한 것 같습니다~~ 처음 호텔링 이용해보는건데 실외배변 하던 아이라 걱정이 정말 많았거든요...ㅠㅠ 초반에 밥이랑 간식을 안먹기는했지만 그래도 4일 동안 무탈하게 잘 지내다가 와서 너무 감사드립니다! 다음 달에도해외 출장이 잡혀있어서 3일 정도 더 호텔링 예정인데 믿고 맡기도록 하겠습니다! ^^ 사장님 너무 친절하세용~ 도그플래닛 추천입니닷~!'
                }
                reviewImageSrc1={'/images/review4.jpg'}
                reviewImageSrc2={'/images/review5.jpg'}
                reviewImageSrc3={'/images/review6.jpg'}
              />
              <CompanyReviewListCard
                reviewStarNum={4}
                reviewerName={'김민겸'}
                reviewDate={'2022.09.21'}
                reviewTitle={'울 댕댕이 호텔가서 친구들이랑 잘 놀아서 좋습니다~'}
                reviewContent={
                  '보내주신 사진보니까 저희집 댕댕이가 친구들이랑 아주 잘노네요 ㅎㅎ 도그플래닛인싸입니다 ㅋㅋ 처음 맡길 때는 너무 말썽부리지 않을까 걱정반 불안반으로 맡기게 되었는데 결론은 선택을 잘한 것 같습니다~~ 처음 호텔링 이용해보는건데 실외배변 하던 아이라 걱정이 정말 많았거든요...ㅠㅠ 초반에 밥이랑 간식을 안먹기는했지만 그래도 4일 동안 무탈하게 잘 지내다가 와서 너무 감사드립니다! 다음 달에도해외 출장이 잡혀있어서 3일 정도 더 호텔링 예정인데 믿고 맡기도록 하겠습니다! ^^ 사장님 너무 친절하세용~ 도그플래닛 추천입니닷~!'
                }
                reviewImageSrc1={'/images/review4.jpg'}
                reviewImageSrc2={'/images/review5.jpg'}
                reviewImageSrc3={'/images/review6.jpg'}
              />
              <CompanyReviewListCard
                reviewStarNum={3}
                reviewerName={'김민겸'}
                reviewDate={'2022.09.21'}
                reviewTitle={'울 댕댕이 호텔가서 친구들이랑 잘 놀아서 좋습니다~'}
                reviewContent={
                  '보내주신 사진보니까 저희집 댕댕이가 친구들이랑 아주 잘노네요 ㅎㅎ 도그플래닛인싸입니다 ㅋㅋ 처음 맡길 때는 너무 말썽부리지 않을까 걱정반 불안반으로 맡기게 되었는데 결론은 선택을 잘한 것 같습니다~~ 처음 호텔링 이용해보는건데 실외배변 하던 아이라 걱정이 정말 많았거든요...ㅠㅠ 초반에 밥이랑 간식을 안먹기는했지만 그래도 4일 동안 무탈하게 잘 지내다가 와서 너무 감사드립니다! 다음 달에도해외 출장이 잡혀있어서 3일 정도 더 호텔링 예정인데 믿고 맡기도록 하겠습니다! ^^ 사장님 너무 친절하세용~ 도그플래닛 추천입니닷~!'
                }
                reviewImageSrc1={'/images/review4.jpg'}
                reviewImageSrc2={'/images/review5.jpg'}
                reviewImageSrc3={'/images/review6.jpg'}
              />
            </CompanyReviewsList>
            <CompanyReviewsPagination>페이지네이션</CompanyReviewsPagination>
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
  margin-top: 2rem;
`

const CompanyInfoTop = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`

const CompanyInfoTitleBox = styled.div``

const CompanyTitleText = styled.div`
  h1 {
    font-size: 2.1rem;
    font-weight: 600;
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
  display: flex;
  justify-content: center;
  align-items: center;

  span:first-child {
  }

  span:last-child {
    margin-left: 0.5rem;
  }
`
const CompanyTitleSubLeftTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    color: ${colors.grey4};
    margin-left: 0.8rem;
  }
`

const CompanyTitleSubRight = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.4rem;
`

const CompanyPageShare = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  span:last-child {
    margin-left: 0.5rem;
    text-decoration: underline;
    font-weight: 700;
    cursor: pointer;
  }

  :hover {
    color: ${colors.mainColor};
  }
`
const CompanyPageLike = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 2rem;

  span:last-child {
    margin-left: 0.2rem;
    text-decoration: underline;
    font-weight: 700;
    cursor: pointer;
  }

  :hover {
    color: ${colors.mainColor};
  }
`

const CompanyImages = styled.div`
  margin-top: 2rem;
  cursor: pointer;
  display: flex;
  max-width: 128rem;
`

const CompanyImageLeft = styled.div`
  flex: 1;
  margin-right: 10px;
  border-radius: 3rem;
`
const CompanyImageRight = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  flex: 1;
`

const ImageWrapper = styled.div`
  border-radius: 1rem;
  flex: 1 1 40%;
  overflow: hidden;
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
  display: flex;
  margin-left: 0.5rem;
  color: ${colors.grey4};

  span:last-child {
    font-size: 1.6rem;
    margin-left: 0.2rem;
  }
`

const CompanyLocationMap = styled.div`
  display: flex;
`

const CompanyReviews = styled.div`
  margin-top: 5rem;
`

const CompanyReviewsCards = styled.div`
  display: flex;
  gap: 1rem;
`

const CompanyReviewsList = styled.div`
  margin: 5rem 0;
`

const CompanyReviewsPagination = styled.div``

const CompanyReservation = styled.div`
  flex: 1;
  margin-top: 5rem;
`
