import React from 'react'
import DogProfileCard from '@/components/Mypage/DogProfileCard'
import {Swiper, SwiperSlide} from 'swiper/react'
import {FreeMode} from 'swiper'
import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'

function DogCardSwiper() {
  return (
    <Swiper
      slidesPerView={2}
      pagination={{
        clickable: true,
      }}
      modules={[FreeMode]}
      className="mySwiper"
      breakpoints={{
        1200: {
          slidesPerView: 3,
          spaceBetween: 10,
        },
        768: {
          slidesPerView: 2,
          spaceBetween: 10,
        },
        390: {
          slidesPerView: 2,
          spaceBetween: 10,
        },
        0: {
          spaceBetween: 10,
          slidesPerView: 2,
        },
      }}
    >
      <SwiperSlide>
        <DogProfileCard />
      </SwiperSlide>
      <SwiperSlide>
        <DogProfileCard />
      </SwiperSlide>
      <SwiperSlide>
        <DogProfileCard />
      </SwiperSlide>
      <SwiperSlide>
        <DogProfileCard />
      </SwiperSlide>
    </Swiper>
  )
}

export default DogCardSwiper
