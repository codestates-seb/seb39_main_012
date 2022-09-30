import React from 'react'
import ReservedCard from '@/components/Mypage/ReservedCard'
import {Swiper, SwiperSlide} from 'swiper/react'
import {Navigation} from 'swiper'

function ReservationSwiper() {
  return (
    <>
      <Swiper
        slidesPerView={1}
        spaceBetween={0}
        modules={[Navigation]}
        className="mySwiper"
        pagination={{
          clickable: true,
        }}
        breakpoints={{
          1200: {
            slidesPerView: 4,
            spaceBetween: 15,
          },
          768: {
            slidesPerView: 3,
            spaceBetween: 10,
          },
          500: {
            slidesPerView: 2,
            spaceBetween: 0,
          },
          0: {
            slidesPerView: 2,
            spaceBetween: 0,
          },
        }}
      >
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
        <SwiperSlide>
          <ReservedCard />
        </SwiperSlide>
      </Swiper>
    </>
  )
}

export default ReservationSwiper
