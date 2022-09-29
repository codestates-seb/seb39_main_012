import React from 'react'
import {Swiper, SwiperSlide} from 'swiper/react'
import {Pagination, Navigation} from 'swiper'
import ConfirmCard from './ConfirmCard'
import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
function ConfirmCardSwiper() {
  return (
    <SwiperContainer>
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
          <ConfirmCard />
        </SwiperSlide>
        <SwiperSlide>
          <ConfirmCard />
        </SwiperSlide>
        <SwiperSlide>
          <ConfirmCard />
        </SwiperSlide>
        <SwiperSlide>
          <ConfirmCard />
        </SwiperSlide>
        <SwiperSlide>
          <ConfirmCard />
        </SwiperSlide>
        <SwiperSlide>
          <ConfirmCard />
        </SwiperSlide>
      </Swiper>
    </SwiperContainer>
  )
}

export default ConfirmCardSwiper

const SwiperContainer = styled.div`
  width: 100%;

  .swiper-slide:nth-child(odd) {
    button {
      background-color: #fcd981;
    }
    .DesignTitle {
      color: #fcd981;
    }
  }

  .swiper-slide:nth-child(even) {
    button {
      background-color: ${colors.mainColor};
    }
    .DesignTitle {
      color: ${colors.mainColor};
    }
  }
`
