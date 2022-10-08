import React from 'react'
import ReservationCard from '@/components/Mypage/ReservationCard'
import {Swiper, SwiperSlide} from 'swiper/react'
import {Navigation} from 'swiper'
import {BeforeReservation} from '@/types/mypage'

interface Props {
  beforeReservations: BeforeReservation[]
}

function ReservationSwiper({beforeReservations}: Props) {
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
        {beforeReservations.map((reservation, idx) => (
          <SwiperSlide key={idx}>
            <ReservationCard reservation={reservation} />
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  )
}

export default ReservationSwiper
