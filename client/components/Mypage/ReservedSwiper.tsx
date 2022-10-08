import React from 'react'
import {Swiper, SwiperSlide} from 'swiper/react'
import {Navigation} from 'swiper'
import ReservedCard from './ReservedCard'
import {afterReservation} from '@/types/mypage'

interface Props {
  afterReservations: afterReservation[]
}

function ReservedSwiper({afterReservations}: Props) {
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
        {afterReservations.map((reservation, idx) => (
          <SwiperSlide key={idx}>
            <ReservedCard reservation={reservation} />
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  )
}

export default ReservedSwiper
