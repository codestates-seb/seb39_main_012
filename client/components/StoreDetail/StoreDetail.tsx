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
import CompanyReviewListCard from './CompanyReviewListCard'
import Map from '../Map/Map'
import 'react-date-range/dist/styles.css'
import 'react-date-range/dist/theme/default.css'
import {DateRange} from 'react-date-range'
import {format} from 'date-fns'
import koLocale from 'date-fns/locale/ko'
import {BiPlusCircle} from 'react-icons/bi'
import {BiMinusCircle} from 'react-icons/bi'
import AuthButton from '../AuthButton/AuthButton'

const StoreDetail = () => {
  const router = useRouter()
  const {postId} = router.query
  const [addLike, setAddLike] = useState(false)
  const [openDate, setOpenDate] = useState(false)
  const [openDogNum, setOpenDogNum] = useState(false)
  const [openTime, setOpenTime] = useState(false)
  const [reservationDate, setReservationDate] = useState('')
  const [date, setDate] = useState([
    {
      startDate: new Date(),
      endDate: new Date(),
      key: 'selection',
    },
  ])
  const [openOptions, setOpenOptions] = useState(false)
  const [options, setOptions] = useState({
    adult: 1,
    child: 0,
  })
  const [smallDogNum, setSmallDogNum] = useState(0)
  const [mediumDogNum, setMediumDogNum] = useState(0)
  const [largeDogNum, setLargeDogNum] = useState(0)
  const [checkInTimeAMPM, setCheckInTimeAMPM] = useState('오전')
  const [checkInTime, setCheckInTime] = useState('00:00')
  const [checkOutTimeAMPM, setCheckOutTimeAMPM] = useState('오후')
  const [checkOutTime, setCheckOutTime] = useState('00:00')

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

  function getDayOfDate(reservationDate: string) {
    const date = ['일', '월', '화', '수', '목', '금', '토']
    const getDayOfDate = date[new Date(reservationDate).getDay()]
    return getDayOfDate
  }

  return (
    <CompanyDetailContainer>
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
                <span>공유하기</span>
              </CompanyPageShare>
              <CompanyPageLike onClick={addLikeHandler}>
                <span>{addLike ? <AiFillHeart size="15" /> : <AiOutlineHeart size="15" />}</span>
                <span>찜하기</span>
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
            <CompanyLocationMap>
              {/* <Map latitude={35.976749396987046} longitude={126.99599512792346} /> */}
              <Map
                address={'경기 화성시 동탄기흥로257번 나길 34-4 1층'}
                companyName={'도그플래닛'}
              />
            </CompanyLocationMap>
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
            <CompanyReviewsPagination></CompanyReviewsPagination>
          </CompanyReviews>
        </CompanyDesc>
        <CompanyReservation>
          <CompanyReservationTop>
            <CompanyReservationTopContainer>
              <CompanyReservationTopTitle>상세 정보</CompanyReservationTopTitle>
              <CompanyReservationForm>
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    언제 호텔링이 필요한가요?
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={() => {
                      setOpenDate(!openDate)
                    }}
                  >
                    {openDate ? (
                      <span>{`${format(
                        date[0].startDate,
                        `yyyy년 MM월 dd일 (${getDayOfDate(
                          format(date[0].startDate, `yyyy-MM-dd`)
                        )})`
                      )} → ${format(
                        date[0].endDate,
                        `yyyy년 MM월 dd일 (${getDayOfDate(format(date[0].endDate, `yyyy-MM-dd`))})`
                      )} `}</span>
                    ) : (
                      <span>호텔링 날짜를 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                </CompanyReservationSubSection>
                {openDate && (
                  <CompanyReservationSelectCalendar>
                    <DateRange
                      editableDateInputs={true}
                      onChange={(item: any) => setDate([item.selection])}
                      moveRangeOnFirstSelection={false}
                      ranges={date}
                      locale={koLocale}
                      className="dateRange"
                    />
                  </CompanyReservationSelectCalendar>
                )}
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    체크인 / 체크아웃 시간
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={() => {
                      console.log('clicked')
                      setOpenTime(!openTime)
                    }}
                  >
                    {openTime ? (
                      <span>
                        체크인: {checkInTimeAMPM} {checkInTime} / 체크아웃: {checkOutTimeAMPM}{' '}
                        {checkOutTime}
                      </span>
                    ) : (
                      <span>체크인 / 체크아웃 시간을 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                  {openTime && (
                    <CompanyReservationTimeSelect>
                      <CompanyReservationTimeSelectContainer>
                        <CompanyReservationDetailTimeSelect>
                          <CompanyReservationTimeCheck>
                            <span>체크인 시간</span>
                          </CompanyReservationTimeCheck>
                          <CompanyReservationTimeSelectDivisions>
                            <CompanyReservationTimeDivision>
                              <div
                                onClick={() => {
                                  setCheckInTimeAMPM('오전')
                                }}
                              >
                                <span>오전</span>
                              </div>
                              <div
                                onClick={() => {
                                  setCheckInTimeAMPM('오후')
                                }}
                              >
                                <span>오후</span>
                              </div>
                            </CompanyReservationTimeDivision>

                            <CompanyReservationTimeItems>
                              {[
                                '12:00',
                                '01:00',
                                '02:00',
                                '03:00',
                                '04:00',
                                '05:00',
                                '06:00',
                                '07:00',
                                '08:00',
                                '09:00',
                                '10:00',
                                '11:00',
                              ].map((time, index) => {
                                return (
                                  <div
                                    key={index}
                                    onClick={() => {
                                      setCheckInTime(time)
                                    }}
                                  >
                                    <span>{time}</span>
                                  </div>
                                )
                              })}
                            </CompanyReservationTimeItems>
                          </CompanyReservationTimeSelectDivisions>
                        </CompanyReservationDetailTimeSelect>
                        {/* 체크아웃시간 */}
                        <CompanyReservationDetailTimeSelect>
                          <CompanyReservationTimeCheck>
                            {' '}
                            <span>체크아웃 시간</span>
                          </CompanyReservationTimeCheck>
                          <CompanyReservationTimeSelectDivisions>
                            <CompanyReservationTimeDivision>
                              <div
                                onClick={() => {
                                  setCheckOutTimeAMPM('오전')
                                }}
                              >
                                <span>오전</span>
                              </div>
                              <div
                                onClick={() => {
                                  setCheckOutTimeAMPM('오후')
                                }}
                              >
                                <span>오후</span>
                              </div>
                            </CompanyReservationTimeDivision>
                            <CompanyReservationTimeItems>
                              {[
                                '12:00',
                                '01:00',
                                '02:00',
                                '03:00',
                                '04:00',
                                '05:00',
                                '06:00',
                                '07:00',
                                '08:00',
                                '09:00',
                                '10:00',
                                '11:00',
                              ].map((time, index) => {
                                return (
                                  <div
                                    key={index}
                                    onClick={() => {
                                      setCheckOutTime(time)
                                    }}
                                  >
                                    <span>{time}</span>
                                  </div>
                                )
                              })}
                            </CompanyReservationTimeItems>
                          </CompanyReservationTimeSelectDivisions>
                        </CompanyReservationDetailTimeSelect>
                      </CompanyReservationTimeSelectContainer>
                    </CompanyReservationTimeSelect>
                  )}
                </CompanyReservationSubSection>
                <CompanyReservationSubSection>
                  <CompanyReservationSubSectionTitle>
                    맡기시는 반려동물
                  </CompanyReservationSubSectionTitle>
                  <CompanyReservationSubSectionInput
                    onClick={() => {
                      setOpenDogNum(!openDogNum)
                    }}
                  >
                    {openDogNum ? (
                      <span>
                        {`소형견: ${smallDogNum} / 중형견: ${mediumDogNum} / 대형견: ${largeDogNum}`}
                      </span>
                    ) : (
                      <span>반려동물 수를 선택해주세요.</span>
                    )}
                  </CompanyReservationSubSectionInput>
                  {openDogNum && (
                    <CompanyReservationHowManyDogs>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">소형견</div>
                          <div className="optionTextDesc">7kg 미만</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter minus"
                            onClick={() => {
                              smallDogNum === 0
                                ? setSmallDogNum(0)
                                : setSmallDogNum(smallDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{smallDogNum}</span>
                          <span
                            className="optionCounter plus"
                            onClick={() => {
                              smallDogNum === 5
                                ? setSmallDogNum(5)
                                : setSmallDogNum(smallDogNum + 1)
                            }}
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">중형견</div>
                          <div className="optionTextDesc">7kg~14.9kg 미만</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter plus"
                            onClick={() => {
                              mediumDogNum === 0
                                ? setMediumDogNum(0)
                                : setMediumDogNum(mediumDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{mediumDogNum}</span>
                          <span
                            className="optionCounter minus"
                            onClick={() => {
                              mediumDogNum === 5
                                ? setMediumDogNum(5)
                                : setMediumDogNum(mediumDogNum + 1)
                            }}
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptions>
                        <CompanyReservationDogOptionsTitle>
                          <div className="optionText">대형견</div>
                          <div className="optionTextDesc">15kg 이상</div>
                        </CompanyReservationDogOptionsTitle>
                        <CompanyReservationDogOptionCounter>
                          <span
                            className="optionCounter plus"
                            onClick={() => {
                              largeDogNum === 0
                                ? setLargeDogNum(0)
                                : setLargeDogNum(largeDogNum - 1)
                            }}
                          >
                            <BiMinusCircle color={'rgb(200, 230, 201)'} size="30" />
                          </span>
                          <span className="optionCounterNumber">{largeDogNum}</span>
                          <span
                            className="optionCounter minus"
                            onClick={() =>
                              largeDogNum === 5
                                ? setLargeDogNum(5)
                                : setLargeDogNum(largeDogNum + 1)
                            }
                          >
                            <BiPlusCircle color={'rgb(200, 230, 201)'} size="30.5" />
                          </span>
                        </CompanyReservationDogOptionCounter>
                      </CompanyReservationDogOptions>
                      <CompanyReservationDogOptionsNotice>
                        최대 5마리까지만 선택 가능합니다.
                      </CompanyReservationDogOptionsNotice>
                    </CompanyReservationHowManyDogs>
                  )}
                </CompanyReservationSubSection>
                <CompanyReservationSubSection>
                  <AuthButton title={'예약 요청'} width={'100%'} marginTop={'1rem'} />
                </CompanyReservationSubSection>
              </CompanyReservationForm>
            </CompanyReservationTopContainer>
          </CompanyReservationTop>
          <CompanyReservationMiddle>
            <CompanyReservationMiddleContainer>
              <CompanyReservationTable>
                <CompanyReservationTableLeft>이용요금</CompanyReservationTableLeft>
                <CompanyReservationTableRight>1박 케어</CompanyReservationTableRight>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>소형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>(7kg 미만)</CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>49,000원</CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>중형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>
                    (7kg~14.9kg)
                  </CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>59,000원</CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
              <CompanyReservationTable>
                <CompanyReservationTableOption>
                  <CompanyReservationTableOptionTitle>대형견</CompanyReservationTableOptionTitle>
                  <CompanyReservationTableOptionDesc>(15kg 이상)</CompanyReservationTableOptionDesc>
                </CompanyReservationTableOption>
                <CompanyReservationTableOptionPrice>79,000원</CompanyReservationTableOptionPrice>
              </CompanyReservationTable>
            </CompanyReservationMiddleContainer>
          </CompanyReservationMiddle>
          <CompanyReservationBottom></CompanyReservationBottom>
        </CompanyReservation>
      </CompanyInfoBottom>
    </CompanyDetailContainer>
  )
}

export default StoreDetail

const CompanyDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 4rem;
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
  color: rgb(174, 205, 163);
`

const CompanyPageShare = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  span:last-child {
    margin-left: 0.5rem;
    text-decoration: underline;
    font-weight: 600;
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
    font-weight: 600;
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
  gap: 5rem;
  margin-top: 8rem;
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
  margin-top: 8rem;
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
  margin-top: 8rem;
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
  margin: 3rem 0 0 0;
`

const CompanyReviews = styled.div`
  margin-top: 8rem;
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
  margin-top: 4.5rem;
`

const CompanyReservationTop = styled.div``

const CompanyReservationTopContainer = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 2rem;
`
const CompanyReservationTopTitle = styled.div`
  font-size: 1.8rem;
  color: ${colors.mainColor};
  font-weight: 700;
`
const CompanyReservationForm = styled.div`
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  width: 100%;
`
const CompanyReservationSubSection = styled.div`
  margin-top: 2.5em;
`

const CompanyReservationSubSectionTitle = styled.h3`
  font-size: 1.6rem;
  font-weight: 600;
  color: rgb(102, 102, 102);
  margin-left: 0.5rem;
`

const CompanyReservationSubSectionInput = styled.div`
  cursor: pointer;
  padding: 1.3rem;
  border: 1px solid rgb(235, 235, 235);
  width: 100%;
  box-sizing: border-box;
  margin-top: 1rem;
  border-radius: 8px;
  outline: none;
  line-height: 2rem;

  span {
    font-size: 1.4rem;
    color: ${colors.grey2};
  }

  :focus {
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`

const CompanyReservationSelectCalendar = styled.div`
  margin-top: 1rem;
  .dateRange {
    padding: 1.5rem 0;
    color: ${colors.mainColor};
    font-family: 나눔스퀘어, 'NanumSquare', 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
      'Open Sans', 'Helvetica Neue', sans-serif !important;

    .rdrDateDisplayWrapper {
      border-radius: 5px;
    }
    .rdrDateDisplay input {
      text-transform: uppercase;
      font-family: 나눔스퀘어, 'NanumSquare', 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
        'Open Sans', 'Helvetica Neue', sans-serif !important;
    }

    .rdrDateDisplay {
      color: rgba(186, 213, 178, 0.762) !important;
    }

    .rdrStartEdge,
    .rdrEndEdge {
      color: rgb(123, 176, 114) !important;
    }

    .rdrInRange {
      color: rgb(174, 205, 164) !important;
    }

    .rdrDayToday .rdrDayNumber {
      span::after {
        background: ${colors.mainColor} !important;
      }
    }
  }
`

const CompanyReservationTimeSelect = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 2.5rem 0;
  border-top: none;
`

const CompanyReservationTimeSelectContainer = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  gap: 20px;
`
const CompanyReservationDetailTimeSelect = styled.div``

const CompanyReservationTimeCheck = styled.div`
  font-size: 1.3rem;
  font-weight: 700;
  color: rgb(216, 216, 216);
  margin-bottom: 1rem;
`

const CompanyReservationTimeSelectDivisions = styled.div`
  display: flex;
  font-size: 1.5rem;
  border-top: 1px solid rgb(236, 236, 236);
`

const CompanyReservationTimeDivision = styled.div`
  cursor: pointer;

  div {
    margin-top: 1rem;
    color: rgb(164, 190, 155);
    font-weight: 700;
    padding: 0.2rem;

    :hover {
      background-color: ${colors.mainColor};
      color: rgb(255, 255, 255);
      border-radius: 3px;
    }
  }

  span {
    margin: 0.5rem;
    padding: 1rem;
  }
`

const CompanyReservationTimeItems = styled.div`
  color: rgb(111, 115, 118);
  /* font-weight: 500; */
  cursor: pointer;

  div {
    margin-top: 1rem;
    padding: 0.2rem;

    :hover {
      background-color: rgb(122, 176, 114);
      color: rgb(255, 255, 255);
      border-radius: 3px;
      font-weight: 600;
    }
  }
  span {
    margin: 0.5rem;
    padding: 1rem;
  }
`

const CompanyReservationHowManyDogs = styled.div`
  border: 1px solid rgb(235, 235, 235);
  border-top: none;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 2rem 5rem;
  font-size: 1.5rem;
`

const CompanyReservationDogOptions = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem 0;
`
const CompanyReservationDogOptionsTitle = styled.div`
  font-weight: 600;

  & > :nth-child(2) {
    font-size: 1.2rem;
    color: ${colors.grey2};
    margin-top: 0.7rem;
  }
`

const CompanyReservationDogOptionCounter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;

  span {
    display: flex;
    text-align: center;
  }

  .optionCounter {
    cursor: pointer;
  }
`

const CompanyReservationDogOptionsNotice = styled.div`
  font-size: 1.2rem;
  margin-top: 1rem;
  color: rgb(212, 212, 212);
`

const CompanyReservationMiddle = styled.div``

const CompanyReservationMiddleContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 3rem;
  border: 1px solid rgb(235, 235, 235);
  border-radius: 8px;
  padding: 0rem 6rem 2rem 2rem;
`

const CompanyReservationTable = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 2rem;
`

const CompanyReservationTableOption = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin-left: 1rem;
`

const CompanyReservationTableOptionPrice = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
`

const CompanyReservationTableOptionTitle = styled.div`
  font-size: 1.4rem;
`

const CompanyReservationTableOptionDesc = styled.div`
  font-size: 1rem;
  margin-top: 0.5rem;
`

const CompanyReservationTableLeft = styled.div`
  flex: 1;
  font-size: 1.8rem;
  color: ${colors.mainColor};
  font-weight: 700;
  margin-bottom: 1.5rem;
`
const CompanyReservationTableRight = styled.div`
  text-align: left;
  font-size: 1.5rem;
  color: rgb(177, 177, 177);
  font-weight: 700;
`

const CompanyReservationBottom = styled.div``
