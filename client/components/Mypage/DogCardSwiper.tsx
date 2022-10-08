import React from 'react'
import DogProfileCard from '@/components/Mypage/DogProfileCard'
import {Swiper, SwiperSlide} from 'swiper/react'
import {FreeMode} from 'swiper'
import {DogCard} from '@/types/mypage'
import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'

interface Props {
  dogs: DogCard[]
}

function DogCardSwiper({dogs}: Props) {
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
      {dogs.map((dog) => (
        <SwiperSlide key={dog.id}>
          <DogProfileCard dog={dog} />
        </SwiperSlide>
      ))}
    </Swiper>
  )
}

export default DogCardSwiper
